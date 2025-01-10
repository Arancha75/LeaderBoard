package com.crio.LeaderBoard.Service;

public interface BadgeStrategy {
    String giveBadge();
    boolean applyTo(int score);
}
