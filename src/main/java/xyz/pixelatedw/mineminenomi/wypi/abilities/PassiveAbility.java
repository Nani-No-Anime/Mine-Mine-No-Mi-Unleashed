/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public abstract class PassiveAbility
/*    */   extends Ability {
/*    */   protected IDuringPassive duringPassiveEvent = player -> {
/*    */     
/*    */     };
/*    */   private boolean isPaused = false;
/*    */   
/*    */   public PassiveAbility(String name, APIConfig.AbilityCategory category) {
/* 19 */     super(name, category);
/* 20 */     hideInGUI(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPause(boolean flag) {
/* 25 */     this.isPaused = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPaused() {
/* 30 */     return this.isPaused;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(PlayerEntity player) {}
/*    */ 
/*    */   
/*    */   public void tick(PlayerEntity player) {
/* 38 */     if (!canUse(player)) {
/*    */       return;
/*    */     }
/* 41 */     if (this.isPaused) {
/*    */       return;
/*    */     }
/* 44 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/* 45 */     this.duringPassiveEvent.duringPassive(player);
/*    */     
/* 47 */     if (isOnCooldown())
/* 48 */       cooldown(player); 
/* 49 */     player.world.getProfiler().endSection();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(PlayerEntity player) {
/* 55 */     if (getCategory() == AbilityHelper.getDevilFruitCategory() && AbilityHelper.isAffectedByWater((LivingEntity)player)) {
/* 56 */       return false;
/*    */     }
/* 58 */     return super.canUse(player);
/*    */   }
/*    */   
/*    */   public static interface IDuringPassive extends Serializable {
/*    */     void duringPassive(PlayerEntity param1PlayerEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\PassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */