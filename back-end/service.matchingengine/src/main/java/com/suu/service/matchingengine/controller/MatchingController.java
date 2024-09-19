package com.suu.service.matchingengine.controller;

import com.suu.service.matchingengine.service.MatchingService;
import com.suu.service.profile.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/matching")
public class MatchingController {
    @Autowired
    private MatchingService matchingService;
    @GetMapping("/user/{userId}/get-matching-profile")
    ResponseEntity<List<UserProfile>> getMatchingProfiles(@PathVariable Long userId, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int offset) {
        List<UserProfile> profiles = matchingService.getMatchingProfiles(userId, pageSize, offset);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}
