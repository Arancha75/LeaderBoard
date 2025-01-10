package com.crio.LeaderBoard.Manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.crio.LeaderBoard.Service.BadgeStrategy;
import com.crio.LeaderBoard.ServiceImpl.CodeChampBadgeStrategy;
import com.crio.LeaderBoard.ServiceImpl.CodeMasterBadgeStrategy;
import com.crio.LeaderBoard.ServiceImpl.CodeNinjaBadgeStrategy;

public class BadgeManager {
    private static BadgeManager instance;
    private final List<BadgeStrategy> badgeStrategies;

    private BadgeManager(){
        badgeStrategies = new ArrayList<>();
        badgeStrategies.add(new CodeMasterBadgeStrategy());
        badgeStrategies.add(new CodeChampBadgeStrategy());
        badgeStrategies.add(new CodeNinjaBadgeStrategy());
    }

    public static BadgeManager getInstance(){
        if(instance == null){
            synchronized(BadgeManager.class){
                if(instance == null){
                    instance = new BadgeManager();
                }
            }
        }
        return instance;
    }

    public Set<String> assignBadges(int score) {
        Set<String> badges = new HashSet<>();
        for (BadgeStrategy strategy : badgeStrategies) {
            if (strategy.applyTo(score)) {  // Check if the strategy applies to the given score
                badges.add(strategy.giveBadge());  // Add the badge to the set
            }
        }
        return badges;  // Return the set of badges
    }

}
