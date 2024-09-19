package com.suu.service.profile.service;

import com.suu.service.profile.dto.MatchSearchQuery;
import com.suu.service.profile.dto.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    UserProfile getUserProfile(Long userId);
    UserProfile updateUserProfile(Long userId, UserProfile userProfile);

    List<UserProfile> searchProfile(MatchSearchQuery query);

    UserProfile updateProfilePicture(Long userId, String filename, boolean setAsPrimary);
}
