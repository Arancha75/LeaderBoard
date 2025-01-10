package com.crio.LeaderBoard.ServiceImpl;

import com.crio.LeaderBoard.Service.BadgeStrategy;

public class CodeMasterBadgeStrategy implements BadgeStrategy {

  @Override
  public String giveBadge() {
    return "Code Master";
  }

  @Override
  public boolean applyTo(int score) {
    return score >= 60 && score < 100;
  }

}
