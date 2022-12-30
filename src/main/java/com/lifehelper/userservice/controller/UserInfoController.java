package com.lifehelper.userservice.controller;

import com.lifehelper.userservice.model.entity.UserInfo;
import com.lifehelper.userservice.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userInfoService.getUserInfoByUserId(userId));
    }

}
