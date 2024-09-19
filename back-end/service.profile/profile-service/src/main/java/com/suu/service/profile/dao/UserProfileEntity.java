package com.suu.service.profile.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_profile")
public class UserProfileEntity {
    @Id
    private String id;
    private Long userId;
    private String name;
    private Integer age;
    private String gender;
    private List<String> interests;
    private String bio;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Double[] location;
    private List<String> photos;
    private String primaryPicture;
    private List<String> preferredGenders;
    private List<String> preferredLocations;
    private Double searchRadius;
    private LocalDateTime created;
    private LocalDateTime updated;
}