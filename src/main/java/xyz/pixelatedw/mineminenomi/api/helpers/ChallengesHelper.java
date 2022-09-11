package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.RegistryObject;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.init.ModChallenges;



public class ChallengesHelper
{
  @Nullable
  public static Challenge getFirstAvailableChallenge(PlayerEntity player, String category) {
    List<Challenge> challenges = new ArrayList<>();
    ModChallenges.CHALLENGES.getEntries().stream().forEach(ro -> challenges.add((Challenge)ro.get()));
    Map<String, List<Challenge>> map = (Map<String, List<Challenge>>)challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
    
    if (!map.containsKey(category)) {
      return null;
    }
    for (Challenge ch : map.get(category)) {
      
      if (ch.isComplete()) {
        continue;
      }
      if (ch.isLocked(player)) {
        break;
      }
      return ch;
    } 
    
    return null;
  }
}


