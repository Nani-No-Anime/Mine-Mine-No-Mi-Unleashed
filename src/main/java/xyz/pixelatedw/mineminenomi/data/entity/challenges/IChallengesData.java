package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IChallengesData {
  boolean addChallenge(Challenge paramChallenge);
  
  boolean removeChallenge(Challenge paramChallenge);
  
  boolean hasChallenge(Challenge paramChallenge);
  
  <T extends Challenge> T getChallenge(T paramT);
  
  <T extends Challenge> T getChallenge(String paramString);
  
  List<Challenge> getChallenges();
  
  Map<String, List<Challenge>> getGroupedChallenges();
  
  void clearChallenges();
  
  int countChallenges();
  
  Optional<Challenge> getCurrentChallenge(PlayerEntity paramPlayerEntity, String paramString);
}


