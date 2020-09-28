package br.com.pipa.userscorepointscontrol.service.impl;

import br.com.pipa.userscorepointscontrol.cache.UserScoreCache;
import br.com.pipa.userscorepointscontrol.exception.DataNotFoundException;
import br.com.pipa.userscorepointscontrol.model.HighScoreListModel;
import br.com.pipa.userscorepointscontrol.model.UserPointRequest;
import br.com.pipa.userscorepointscontrol.model.UserScore;
import br.com.pipa.userscorepointscontrol.service.UserScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserScoreServiceImpl implements UserScoreService {

    private final UserScoreCache scoreCache;

    public void addUserPoint(UserPointRequest userPointRequest) {
        UserScore userScoreObject = new UserScore(userPointRequest.getUserId(), userPointRequest.getPoints());
        scoreCache.addUserScore(userScoreObject);
        log.info("Add user success");
    }

    public UserScore getUserPosition(Integer userId) throws DataNotFoundException {
        UserScore resultUserScore = scoreCache.getUserScore(userId);
        if(Objects.isNull(resultUserScore))
            throw new DataNotFoundException("Usuario não existe");
        log.info("User {} Position {} ",resultUserScore.getUserId(),resultUserScore.getPosition());
        return resultUserScore;
    }

    public HighScoreListModel getScoreListModel() throws DataNotFoundException {
        List<UserScore> allUsers = scoreCache.getAllUsers();
        if(Objects.isNull(allUsers) || allUsers.isEmpty())
            throw new DataNotFoundException("Lista de Usuarios vazia");
        log.info("List successfully return");
        return new HighScoreListModel(allUsers);
    }
}
