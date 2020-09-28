package br.com.pipa.userscorepointscontrol.cache.impl;

import br.com.pipa.userscorepointscontrol.cache.UserScoreCache;
import br.com.pipa.userscorepointscontrol.model.UserScore;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class UserScoreCacheImpl implements UserScoreCache {

    private final ConcurrentHashMap<Integer, UserScore> inMemoryCache = new ConcurrentHashMap<>();

    public void addUserScore(UserScore userScore) {
        if (userScore == null)  return;
        UserScore existUS = inMemoryCache.get(userScore.getUserId());
        if(Objects.nonNull(existUS)) {
            Integer newScore = existUS.getScore() + userScore.getScore();
            userScore.setScore(newScore);
        }
        inMemoryCache.put(userScore.getUserId(),userScore);
    }

    public void removeUserScore(Integer userId) {
        inMemoryCache.remove(userId);
    }

    public UserScore getUserScore(Integer userId) {
        List<UserScore> scoreList = getAllUsers();
        UserScore resultUserScore = scoreList.stream()
                .filter(item -> item.getUserId().equals(userId)).findFirst().orElse(null);
        if(Objects.isNull(resultUserScore)) return null;
        return resultUserScore;
    }

    public List<UserScore> getAllUsers(){
        int[] index = {1};
        return inMemoryCache.values().stream()
                        .sorted(Comparator.comparing(UserScore::getScore).reversed())
                        .map(userScore -> {
                            userScore.setPosition(index[0]++);
                            return userScore;
                        })
                        .collect(Collectors.toList());
    }

    public void clear() {
        inMemoryCache.clear();
    }

}
