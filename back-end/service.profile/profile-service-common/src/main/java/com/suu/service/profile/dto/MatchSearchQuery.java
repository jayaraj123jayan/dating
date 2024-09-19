package com.suu.service.profile.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MatchSearchQuery {
    private List<Long> userIds;
    private Double[] locations;
    private Double radius;
    private List<String> genders;
    private List<String> interests;
    private int pageSize;
    private int pageOffset;
}
