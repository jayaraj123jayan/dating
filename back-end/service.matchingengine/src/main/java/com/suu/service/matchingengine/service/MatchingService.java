package com.suu.service.matchingengine.service;

import com.suu.service.profile.dto.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchingService {
    List<UserProfile> getMatchingProfiles(Long userId, int pageSize, int offset);
}
