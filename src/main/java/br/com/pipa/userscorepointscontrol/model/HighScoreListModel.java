package br.com.pipa.userscorepointscontrol.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class HighScoreListModel {
    @NonNull
    private List<UserScore> highscores;
}
