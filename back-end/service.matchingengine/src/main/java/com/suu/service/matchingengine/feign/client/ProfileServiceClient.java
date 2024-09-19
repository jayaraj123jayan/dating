package com.suu.service.matchingengine.feign.client;

import com.suu.service.profile.dto.MatchSearchQuery;
import com.suu.service.profile.dto.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "profile-service-client", url = "${peer-service.profile-service.url}")
public interface ProfileServiceClient {

    @RequestMapping(method = RequestMethod.GET, path = "v1/profile/user/{userId}")
    ResponseEntity<UserProfile> getUserProfileService(@PathVariable Long userId);

    @RequestMapping(method = RequestMethod.POST, path = "v1/profile/search")
    ResponseEntity<List<UserProfile>> searchProfile(@RequestBody MatchSearchQuery query);
}
