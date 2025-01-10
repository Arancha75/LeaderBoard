package com.crio.LeaderBoard.ServiceImpl;

import com.crio.LeaderBoard.Service.BadgeStrategy;

public class CodeChampBadgeStrategy implements BadgeStrategy {

  @Override
  public String giveBadge() {
    return "Code Champ";
  }

  @Override
  public boolean applyTo(int score) {
    return score >= 30 && score < 60;
  }

}
