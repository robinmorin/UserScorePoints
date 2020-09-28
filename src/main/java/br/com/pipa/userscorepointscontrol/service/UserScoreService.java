package br.com.pipa.userscorepointscontrol.service;

import br.com.pipa.userscorepointscontrol.exception.DataNotFoundException;
import br.com.pipa.userscorepointscontrol.model.HighScoreListModel;
import br.com.pipa.userscorepointscontrol.model.UserPointRequest;
import br.com.pipa.userscorepointscontrol.model.UserScore;

public interface UserScoreService {

    void addUserPoint(UserPointRequest userPointRequest);

    UserScore getUserPosition(Integer userId) throws DataNotFoundException;

    HighScoreListModel getScoreListModel() throws DataNotFoundException;

}
