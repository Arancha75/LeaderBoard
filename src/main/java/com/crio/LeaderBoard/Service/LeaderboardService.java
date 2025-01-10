package com.crio.LeaderBoard.Service;

import java.util.List;

import com.crio.LeaderBoard.Entity.User;

public interface LeaderboardService {
    List<User> getLeaderboard(); 
    User getUserDetails(String userId);
    User addUser(String userId, String username);
    User updateUserScore(String userId, int score);
    User addBadge(String userId, String badge);
    User removeBadge(String userId, String badge);
    void deleteUser(String userId);

}
