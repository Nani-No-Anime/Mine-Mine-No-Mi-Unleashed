/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SharedKillChecks {
/*    */   public static final KillEntityObjective.ICheckKill HAS_SWORD;
/*    */   public static final KillEntityObjective.ICheckKill HAS_BOW;
/*    */   
/*    */   static {
/* 13 */     HAS_SWORD = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/*    */         return ItemsHelper.isSword(heldItem);
/*    */       });
/*    */     
/* 19 */     HAS_BOW = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/*    */         return ItemsHelper.isBow(heldItem);
/*    */       });
/*    */     
/* 25 */     HAS_EMPTY_HAND = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/*    */         return heldItem.isEmpty();
/*    */       });
/*    */     
/* 31 */     IS_KICKING = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/* 34 */         return (heldItem.isEmpty() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
/*    */       });
/*    */     
/* 37 */     HAS_CANNON_BALL = ((player, target, source) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         return (heldItem.getItem() == ModItems.CANNON_BALL);
/*    */       });
/*    */   }
/*    */   public static final KillEntityObjective.ICheckKill HAS_EMPTY_HAND; public static final KillEntityObjective.ICheckKill IS_KICKING; public static final KillEntityObjective.ICheckKill HAS_CANNON_BALL;
/* 43 */   public static final KillEntityObjective.ICheckKill HAS_BRALWER_HAND_CHECK = HAS_EMPTY_HAND.or(HAS_CANNON_BALL); public static final KillEntityObjective.ICheckKill AIRBORNE_ENEMY_CHECK; public static final KillEntityObjective.ICheckKill PLAYER_RUNNING_CHECK; public static final KillEntityObjective.ICheckKill CRITICAL_KILL_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_ENEMY_CHECK; public static final KillEntityObjective.ICheckKill ON_FIRE_PLAYER_CHECK;
/*    */   static {
/* 45 */     AIRBORNE_ENEMY_CHECK = ((player, target, source) -> 
/*    */       
/* 47 */       (!target.onGround && !target.isInWater()));
/*    */ 
/*    */     
/* 50 */     PLAYER_RUNNING_CHECK = ((player, target, source) -> player.isSprinting());
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     CRITICAL_KILL_CHECK = ((player, target, source) -> 
/*    */       
/* 57 */       (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger()));
/*    */ 
/*    */ 
/*    */     
/* 61 */     ON_FIRE_ENEMY_CHECK = ((player, target, source) -> (target.getFireTimer() > 0));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     ON_FIRE_PLAYER_CHECK = ((player, target, source) -> (player.getFireTimer() > 0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final KillEntityObjective.ICheckKill checkAbilitySource(Ability ability) {
/* 73 */     return (player, target, source) -> 
/*    */       
/* 75 */       (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\SharedKillChecks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */