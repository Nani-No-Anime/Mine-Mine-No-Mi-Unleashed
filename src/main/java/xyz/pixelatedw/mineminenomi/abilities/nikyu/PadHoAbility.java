/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PadHoAbility extends Ability implements IAnimatedAbility {
/* 15 */   public static final Ability INSTANCE = new PadHoAbility();
/*    */ 
/*    */   
/*    */   public PadHoAbility() {
/* 19 */     super("Pad Ho", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(1.5D);
/* 21 */     setDescription("Launches a paw-shaped shockwave at the opponent");
/*    */     
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     player.world.playMovingSound(null, (Entity)player, ModSounds.PAD_HO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 29 */     PadHoProjectile proj = new PadHoProjectile(player.world, (LivingEntity)player);
/* 30 */     player.world.addEntity((Entity)proj);
/* 31 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 1.0F);
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 39 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 45 */     return (isOnCooldown() && getCooldown() > getMaxCooldown() - 5.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\PadHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */