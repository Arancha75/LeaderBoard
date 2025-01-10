package com.crio.LeaderBoard.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.LeaderBoard.Entity.User;
import com.crio.LeaderBoard.Service.LeaderboardService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<User>> getLeaderboard() {
        List<User> users = leaderboardService.getLeaderboard();
       if(users.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable String userId) {
        User user = leaderboardService.getUserDetails(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestParam String userId, @RequestParam String username) {
        try {
            User user = leaderboardService.addUser(userId, username);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @RequestParam int score) {
        try {
            User user = leaderboardService.updateUserScore(userId, score);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{userId}/badge")
    public ResponseEntity<User> addBadge(@PathVariable String userId, @RequestParam String badge) {
        try {
            User user = leaderboardService.addBadge(userId, badge);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        leaderboardService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/badge")
    public ResponseEntity<User> removeBadge(@PathVariable String userId, @RequestParam String badge) {
        try {
            User user = leaderboardService.removeBadge(userId, badge);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}