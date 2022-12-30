package com.lifehelper.userservice.model.kafka;

import com.lifehelper.userservice.model.entity.State;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaRegistrationDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDateTime createdDateTime;
    private State state;
}
