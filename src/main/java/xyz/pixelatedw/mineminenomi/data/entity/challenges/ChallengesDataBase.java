package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;

public class ChallengesDataBase
  implements IChallengesData
{
  private List<Challenge> challenges = new ArrayList<>();


  
  public boolean addChallenge(Challenge challenge) {
    Challenge ogChallenge = getChallenge(challenge);
    if (ogChallenge == null) {
      
      this.challenges.add(challenge);
      return true;
    } 
    return false;
  }


  
  public boolean removeChallenge(Challenge challenge) {
    Challenge ogChallenge = getChallenge(challenge);
    if (ogChallenge != null) {
      
      this.challenges.remove(ogChallenge);
      return true;
    } 
    return false;
  }


  
  public boolean hasChallenge(Challenge challenge) {
    this.challenges.removeIf(chl -> (chl == null));
    return this.challenges.parallelStream().anyMatch(chl -> chl.equals(challenge));
  }


  
  public <T extends Challenge> T getChallenge(T challenge) {
    this.challenges.removeIf(qst -> (qst == null));
    return (T)this.challenges.parallelStream().filter(chl -> chl.equals(challenge)).findFirst().orElse(null);
  }


  
  public <T extends Challenge> T getChallenge(String challengeKey) {
    this.challenges.removeIf(qst -> (qst == null));
    return (T)this.challenges.parallelStream().filter(chl -> chl.getId().equalsIgnoreCase(challengeKey)).findFirst().orElse(null);
  }


  
  public List<Challenge> getChallenges() {
    this.challenges.removeIf(chl -> (chl == null));
    return (List<Challenge>)this.challenges.parallelStream().collect(Collectors.toList());
  }


  
  public Map<String, List<Challenge>> getGroupedChallenges() {
    return (Map<String, List<Challenge>>)this.challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
  }


  
  public void clearChallenges() {
    this.challenges.clear();
  }


  
  public int countChallenges() {
    this.challenges.removeIf(chl -> (chl == null));
    return this.challenges.size();
  }


  
  public Optional<Challenge> getCurrentChallenge(PlayerEntity player, String category) {
    return ((List<Challenge>)getGroupedChallenges().get(category)).stream().filter(ch -> (!ch.isLocked(player) && !ch.isComplete())).findFirst();
  }
}


