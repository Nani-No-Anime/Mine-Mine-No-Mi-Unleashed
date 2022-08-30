/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public abstract class RunningSmashAbility
/*    */   extends PassiveAbility {
/* 15 */   private double smashArea = 1.5D;
/* 16 */   private float smashDamage = 2.0F;
/*    */ 
/*    */   
/*    */   public RunningSmashAbility(String name, APIConfig.AbilityCategory category, double area, float damage) {
/* 20 */     super(name, category);
/*    */     
/* 22 */     this.duringPassiveEvent = this::duringPassiveEvent;
/* 23 */     hideInGUI(false);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringPassiveEvent(PlayerEntity player) {
/* 28 */     if (player.isSprinting()) {
/*    */       
/* 30 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, this.smashArea);
/* 31 */       targets.remove(player);
/*    */       
/* 33 */       for (LivingEntity target : targets) {
/*    */         
/* 35 */         Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/* 36 */         target.attackEntityFrom(DamageSource.causePlayerDamage(player), this.smashDamage);
/* 37 */         target.setMotion(speed.x, 0.2D, speed.z);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\RunningSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */