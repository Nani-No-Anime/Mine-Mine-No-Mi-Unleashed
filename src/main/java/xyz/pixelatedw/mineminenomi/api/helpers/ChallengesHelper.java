/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChallengesHelper
/*    */ {
/*    */   @Nullable
/*    */   public static Challenge getFirstAvailableChallenge(PlayerEntity player, String category) {
/* 19 */     List<Challenge> challenges = new ArrayList<>();
/* 20 */     ModChallenges.CHALLENGES.getEntries().stream().forEach(ro -> challenges.add((Challenge)ro.get()));
/* 21 */     Map<String, List<Challenge>> map = (Map<String, List<Challenge>>)challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
/*    */     
/* 23 */     if (!map.containsKey(category)) {
/* 24 */       return null;
/*    */     }
/* 26 */     for (Challenge ch : map.get(category)) {
/*    */       
/* 28 */       if (ch.isComplete()) {
/*    */         continue;
/*    */       }
/* 31 */       if (ch.isLocked(player)) {
/*    */         break;
/*    */       }
/* 34 */       return ch;
/*    */     } 
/*    */     
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\ChallengesHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */