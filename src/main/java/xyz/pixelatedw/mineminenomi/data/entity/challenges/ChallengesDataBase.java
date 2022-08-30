/*    */ package xyz.pixelatedw.mineminenomi.data.entity.challenges;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ 
/*    */ public class ChallengesDataBase
/*    */   implements IChallengesData
/*    */ {
/* 14 */   private List<Challenge> challenges = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean addChallenge(Challenge challenge) {
/* 19 */     Challenge ogChallenge = getChallenge(challenge);
/* 20 */     if (ogChallenge == null) {
/*    */       
/* 22 */       this.challenges.add(challenge);
/* 23 */       return true;
/*    */     } 
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean removeChallenge(Challenge challenge) {
/* 31 */     Challenge ogChallenge = getChallenge(challenge);
/* 32 */     if (ogChallenge != null) {
/*    */       
/* 34 */       this.challenges.remove(ogChallenge);
/* 35 */       return true;
/*    */     } 
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasChallenge(Challenge challenge) {
/* 43 */     this.challenges.removeIf(chl -> (chl == null));
/* 44 */     return this.challenges.parallelStream().anyMatch(chl -> chl.equals(challenge));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends Challenge> T getChallenge(T challenge) {
/* 50 */     this.challenges.removeIf(qst -> (qst == null));
/* 51 */     return (T)this.challenges.parallelStream().filter(chl -> chl.equals(challenge)).findFirst().orElse(null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends Challenge> T getChallenge(String challengeKey) {
/* 57 */     this.challenges.removeIf(qst -> (qst == null));
/* 58 */     return (T)this.challenges.parallelStream().filter(chl -> chl.getId().equalsIgnoreCase(challengeKey)).findFirst().orElse(null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Challenge> getChallenges() {
/* 64 */     this.challenges.removeIf(chl -> (chl == null));
/* 65 */     return (List<Challenge>)this.challenges.parallelStream().collect(Collectors.toList());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<String, List<Challenge>> getGroupedChallenges() {
/* 71 */     return (Map<String, List<Challenge>>)this.challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void clearChallenges() {
/* 77 */     this.challenges.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int countChallenges() {
/* 83 */     this.challenges.removeIf(chl -> (chl == null));
/* 84 */     return this.challenges.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<Challenge> getCurrentChallenge(PlayerEntity player, String category) {
/* 90 */     return ((List<Challenge>)getGroupedChallenges().get(category)).stream().filter(ch -> (!ch.isLocked(player) && !ch.isComplete())).findFirst();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\ChallengesDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */