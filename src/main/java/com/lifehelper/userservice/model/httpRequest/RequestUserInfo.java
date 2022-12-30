package com.lifehelper.userservice.model.httpRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserInfo {
    private String firstName;
    private String lastName;
    private String email;
}
