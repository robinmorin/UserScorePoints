package br.com.pipa.userscorepointscontrol.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserScore {
    @NonNull
    private Integer userId;
    @NonNull
    private Integer score;
    private Integer position;
}
