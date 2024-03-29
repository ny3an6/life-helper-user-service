package com.lifehelper.userservice.service;

import com.lifehelper.userservice.exception.InvalidDataException;
import com.lifehelper.userservice.model.entity.UserInfo;
import com.lifehelper.userservice.model.security.JwtAuthentication;
import com.lifehelper.userservice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfo getUserInfoByUserId(Long userId) {
        return userInfoRepository.findUserInfoByUserId(userId)
                .orElseThrow(() -> new InvalidDataException("user not found"));
    }

    public JwtAuthentication getAuthenticationInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
