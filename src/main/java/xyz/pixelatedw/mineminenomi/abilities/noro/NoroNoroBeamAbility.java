/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.noro.NoroNoroBeamProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class NoroNoroBeamAbility extends Ability implements IAnimatedAbility {
/* 16 */   public static final Ability INSTANCE = new NoroNoroBeamAbility();
/*    */ 
/*    */   
/*    */   public NoroNoroBeamAbility() {
/* 20 */     super("Noro Noro Beam", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(3.0D);
/* 22 */     setDescription("Shoots a beam of photons at the opponent, completely slowing them down (multiple hits stack the Slowness effect)");
/*    */     
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 29 */     NoroNoroBeamProjectile proj = new NoroNoroBeamProjectile(player.world, (LivingEntity)player);
/* 30 */     player.world.addEntity((Entity)proj);
/* 31 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
/* 32 */     player.world.playSound(null, player.getPosition(), ModSounds.NORO_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 40 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 46 */     return (isOnCooldown() && getCooldown() > WyHelper.percentage(80.0D, getMaxCooldown()));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\NoroNoroBeamAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */