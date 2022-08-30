package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;

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


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\IChallengesData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */