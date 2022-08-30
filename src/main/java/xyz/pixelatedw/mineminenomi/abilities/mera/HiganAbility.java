/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HiganProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class HiganAbility extends RepeaterAbility implements IAnimatedAbility {
/* 14 */   public static final Ability INSTANCE = (Ability)new HiganAbility();
/*    */ 
/*    */   
/*    */   public HiganAbility() {
/* 18 */     super("Higan", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Turns the user's fingertips into flames and shoots bullets made of fire from them");
/* 20 */     setMaxCooldown(9.0D);
/* 21 */     setMaxRepeaterCount(10, 2);
/*    */     
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     HiganProjectile proj = new HiganProjectile(player.world, (LivingEntity)player);
/* 29 */     player.world.addEntity((Entity)proj);
/* 30 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 38 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 44 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */