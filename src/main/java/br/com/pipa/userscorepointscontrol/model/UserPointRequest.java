package br.com.pipa.userscorepointscontrol.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserPointRequest {
    private final Integer userId;
    private final Integer points;
}
