package com.crio.LeaderBoard.ServiceImpl;

import com.crio.LeaderBoard.Service.BadgeStrategy;

public class CodeNinjaBadgeStrategy implements BadgeStrategy {

  @Override
  public String giveBadge() {
    return "Code Ninja";
  }

  @Override
  public boolean applyTo(int score) {
    return score >= 1 && score < 30;
  }

}
