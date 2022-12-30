package com.lifehelper.userservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifehelper.userservice.model.entity.UserInfo;
import com.lifehelper.userservice.model.kafka.KafkaRegistrationDto;
import com.lifehelper.userservice.repository.UserInfoRepository;
import com.lifehelper.userservice.utils.KafkaConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RegistrationListener {

    private final ObjectMapper objectMapper;

    private final UserInfoRepository userInfoRepository;

    @KafkaListener(groupId = KafkaConstant.AUTHENTICATION_GROUP_ID, topics = KafkaConstant.AUTH_TO_USER_INFO_SERVICE_REGISTRATION_EVENT)
    public void proceed(@Payload String payload) throws JsonProcessingException {
        KafkaRegistrationDto request = objectMapper.readValue(payload, KafkaRegistrationDto.class);
        UserInfo userInfo = UserInfo.builder() // TODO: сделать builder метод в сущности
                .userId(request.getUserId())
                .state(request.getState())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .createdDateTime(request.getCreatedDateTime())
                .build();
        userInfoRepository.save(userInfo);
    }
}
