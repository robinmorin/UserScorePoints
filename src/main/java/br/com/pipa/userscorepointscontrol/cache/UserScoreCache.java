package br.com.pipa.userscorepointscontrol.cache;

import br.com.pipa.userscorepointscontrol.model.UserScore;

import java.util.List;

public interface UserScoreCache {

    void addUserScore(UserScore userScore);

    void removeUserScore(Integer userId);

    UserScore getUserScore(Integer userId);

    List<UserScore> getAllUsers();

    void clear();

}
