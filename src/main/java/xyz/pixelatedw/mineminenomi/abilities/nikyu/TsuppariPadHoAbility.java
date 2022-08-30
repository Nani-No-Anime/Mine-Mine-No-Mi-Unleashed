/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class TsuppariPadHoAbility extends RepeaterAbility implements IAnimatedAbility {
/* 18 */   public static final Ability INSTANCE = (Ability)new TsuppariPadHoAbility();
/*    */ 
/*    */   
/*    */   public TsuppariPadHoAbility() {
/* 22 */     super("Tsuppari Pad Ho", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(7.5D);
/* 24 */     setMaxRepeaterCount(5, 4);
/* 25 */     setDescription("Launches a barrage of paw-shaped shockwaves at the opponent");
/* 26 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 27 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 28 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 32 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     player.world.playMovingSound(null, (Entity)player, ModSounds.MULTIPLE_PAD_HO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 42 */     for (int i = 0; i < 6; i++) {
/*    */       
/* 44 */       PadHoProjectile proj = new PadHoProjectile(player.world, (LivingEntity)player);
/* 45 */       proj.setChangeHurtTime(true);
/* 46 */       proj.setDamage(5.0F);
/* 47 */       player.world.addEntity((Entity)proj);
/* 48 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 15.0F);
/*    */     } 
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 56 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 62 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\TsuppariPadHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */