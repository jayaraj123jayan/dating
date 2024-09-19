package com.suu.service.profile.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserProfile {
    private Long userId;
    private String name;
    private Integer age;
    private String gender;
    private List<String> interests;
    private String bio;
    private Double[] location;
    private List<String> photos;
    private String primaryPicture;
    private List<String> preferredGenders;
    private List<String> preferredLocations;
    private Double searchRadius;
    private LocalDateTime created;
    private LocalDateTime updated;
}
