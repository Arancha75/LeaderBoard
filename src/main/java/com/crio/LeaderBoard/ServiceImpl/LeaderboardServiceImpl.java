package com.crio.LeaderBoard.ServiceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.LeaderBoard.Entity.User;
import com.crio.LeaderBoard.Manager.BadgeManager;
import com.crio.LeaderBoard.Repository.UserRepository;
import com.crio.LeaderBoard.Service.LeaderboardService;

@Service
public class LeaderboardServiceImpl implements LeaderboardService{

    @Autowired
    private UserRepository userRepository;
    private final BadgeManager badgeManager = BadgeManager.getInstance();

    @Override
    public List<User> getLeaderboard() {
        // TODO Auto-generated method stub
        return userRepository.findAllByScoreOrderByScoreDesc();
    }

    @Override
    public User getUserDetails(String userId) {
        // TODO Auto-generated method stub
         User user = userRepository.findById(userId).orElse(null);
         return user;
    }

    @Override
    public User addUser(String userId, String username) {
        // TODO Auto-generated method stub
        if(userRepository.existsById(userId)){
            throw new IllegalArgumentException("User already exists");
        }
        User user = new User(userId, username);
        return userRepository.save(user);
       
    }

    @Override
    public User updateUserScore(String userId, int score) {
        if(score < 0 || score > 100){
            throw new IllegalArgumentException("Invalid score");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setScore(score);
        Set<String> badges = badgeManager.assignBadges(score);
        user.setBadges(badges);
        // TODO Auto-generated method stub
        return userRepository.save(user);
      
    }

    @Override
    public User addBadge(String userId, String badge) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getBadges().contains(badge)){
            throw new IllegalArgumentException("Badge already exists");
        }
        user.getBadges().add(badge);
        return userRepository.save(user);
    }

    // TODO Auto-generated method stub
        

    @Override
    public User removeBadge(String userId, String badge) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(user.getBadges().contains(badge)){
            user.getBadges().remove(badge);
        }
        else{
            throw new IllegalArgumentException("Badge does not exist");
        }

        return userRepository.save(user);
    }
    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
        
    }

}
