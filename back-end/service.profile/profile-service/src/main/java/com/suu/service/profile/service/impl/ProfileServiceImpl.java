package com.suu.service.profile.service.impl;

import com.suu.service.profile.dao.UserProfileEntity;
import com.suu.service.profile.dto.MatchSearchQuery;
import com.suu.service.profile.dto.UserProfile;
import com.suu.service.profile.exception.NotFoundException;
import com.suu.service.profile.mapper.UserProfileMapper;
import com.suu.service.profile.repository.UserProfileRepository;
import com.suu.service.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserProfileMapper userProfileMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<UserProfile> searchProfile(MatchSearchQuery searchQuery) {
        Query query = new Query();

        // Add criteria for gender
        List<String> genders = searchQuery.getGenders();
        Double radius = searchQuery.getRadius() == null ? 10.0: searchQuery.getRadius();
        Double[] locations = searchQuery.getLocations();

        query.addCriteria(Criteria.where("userId").nin(searchQuery.getUserIds()));
        if (genders != null && !genders.isEmpty()) {
            query.addCriteria(Criteria.where("gender").in(genders));
        }

        if(locations != null && locations.length !=1) {
            Point point = new Point(locations[0], locations[1]);
            query.addCriteria(Criteria.where("location").nearSphere(point)
                    .maxDistance(radius / 6378.1));
        }
        query.with(PageRequest.of(searchQuery.getPageOffset(), searchQuery.getPageSize()));
//
//
//        // Add criteria for interests
//        if (searchQuery.getInterests() != null && !searchQuery.getInterests().isEmpty()) {
//            query.addCriteria(Criteria.where("interests").in(searchQuery.getInterests()));
//        }

        return userProfileMapper.toDtos(mongoTemplate.find(query, UserProfileEntity.class));
    }

    @Override
    public UserProfile updateProfilePicture(Long userId, String filename, boolean setAsPrimary) {
        final var entity = getUserProfileEntity(userId);
        if(setAsPrimary) {
            entity.setPrimaryPicture(filename);
        } else {
            if(entity.getPhotos() == null || entity.getPhotos().isEmpty()) {
                entity.setPhotos(new ArrayList<>());
            }
            entity.getPhotos().add(filename);
        }
        return userProfileMapper.toDto(userProfileRepository.save(entity));
    }

    @Override
    public UserProfile getUserProfile(Long userId) {
        return userProfileMapper.toDto(getUserProfileEntity(userId));
    }

    private UserProfileEntity getUserProfileEntity(Long userId) {
        return userProfileRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("No profile found for the requested userId"));
    }

    @Override
    public UserProfile updateUserProfile(Long userId, UserProfile userProfile) {
        final var entity = userProfileMapper.toEntity(userProfile);
        entity.setUserId(userId);
        userProfileRepository.findByUserId(userId).ifPresent(existing -> entity.setId(existing.getId()));
        return userProfileMapper.toDto(userProfileRepository.save(entity));
    }
}
