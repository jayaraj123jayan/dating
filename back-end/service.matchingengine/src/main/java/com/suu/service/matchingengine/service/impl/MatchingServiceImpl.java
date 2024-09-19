package com.suu.service.matchingengine.service.impl;

import com.suu.service.matchingengine.service.MatchingService;
import com.suu.service.matchingengine.feign.client.ProfileServiceClient;
import com.suu.service.profile.dto.MatchSearchQuery;
import com.suu.service.profile.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchingServiceImpl implements MatchingService {
    @Autowired
    private ProfileServiceClient profileServiceClient;
    @Override
    public List<UserProfile> getMatchingProfiles(Long userId, int pageSize, int offset) {
        final ResponseEntity<UserProfile> resp = profileServiceClient.getUserProfileService(userId);
        if(!resp.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }
        final var profile = resp.getBody();
        final var query = getMatchQuery(profile, pageSize, offset);
        final var matches = profileServiceClient.searchProfile(query);
        if(!matches.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException();
        }
        return matches.getBody();
    }

    private MatchSearchQuery getMatchQuery(UserProfile profile, int pageSize, int offset) {
        return MatchSearchQuery.builder()
                .userIds(List.of(profile.getUserId()))
                .genders(profile.getPreferredGenders())
                .locations(profile.getLocation())
                .pageSize(pageSize)
                .pageOffset(offset)
                .radius(profile.getSearchRadius())
                .build();
    }
}
