/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SharedHitChecks {
/*    */   public static final HitEntityObjective.ICheckHit HAS_SWORD;
/*    */   
/*    */   static {
/* 12 */     HAS_SWORD = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/*    */         return ItemsHelper.isSword(heldItem);
/*    */       });
/*    */     
/* 18 */     HAS_BOW = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/*    */         return ItemsHelper.isBow(heldItem);
/*    */       });
/*    */     
/* 24 */     IS_KICKING = ((player, target, source, amount) -> {
/*    */         ItemStack heldItem = player.getHeldItemMainhand();
/*    */         
/* 27 */         return (heldItem.isEmpty() && EntityStatsCapability.get((LivingEntity)player).isBlackLeg());
/*    */       });
/*    */     
/* 30 */     SWEEP_ATTACK_CHECK = ((player, target, source, amount) -> source.getDamageType().equalsIgnoreCase("sweep_damage"));
/*    */   }
/*    */   public static final HitEntityObjective.ICheckHit HAS_BOW;
/*    */   public static final HitEntityObjective.ICheckHit IS_KICKING;
/*    */   public static final HitEntityObjective.ICheckHit SWEEP_ATTACK_CHECK;
/*    */   
/*    */   public static final HitEntityObjective.ICheckHit checkAbilitySource(Ability ability) {
/* 37 */     return (player, target, source, amount) -> 
/*    */       
/* 39 */       (source instanceof AbilityDamageSource && ((AbilityDamageSource)source).getAbilitySource().equals(ability));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\SharedHitChecks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */