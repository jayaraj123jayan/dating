package com.suu.service.profile.controller;

import com.suu.service.profile.dto.MatchSearchQuery;
import com.suu.service.profile.dto.UserProfile;
import com.suu.service.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("user/{userId}")
    private UserProfile gerUserProfile(@PathVariable Long userId) {
        return profileService.getUserProfile(userId);
    }
    @PostMapping("search")
    private List<UserProfile> searchProfile(@RequestBody MatchSearchQuery query) {
        return profileService.searchProfile(query);
    }
    @PutMapping("user/{userId}")
    private UserProfile gerUserProfile(@PathVariable Long userId, @RequestBody @Valid UserProfile userProfile) {
        return profileService.updateUserProfile(userId, userProfile);
    }

    @PutMapping("user/{userId}/profile-picture")
    private UserProfile updateProfilePicture(@PathVariable Long userId, @RequestParam String filename,
                                             @RequestParam(defaultValue = "false", required = false) boolean setAsPrimary) {
        return profileService.updateProfilePicture(userId, filename, setAsPrimary);
    }
}
