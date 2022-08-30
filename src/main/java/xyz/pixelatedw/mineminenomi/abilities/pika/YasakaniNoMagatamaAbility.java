/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.YasakaniNoMagatamaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class YasakaniNoMagatamaAbility extends RepeaterAbility implements IAnimatedAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new YasakaniNoMagatamaAbility();
/*    */ 
/*    */   
/*    */   public YasakaniNoMagatamaAbility() {
/* 23 */     super("Yasakani no Magatama", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(14.0D);
/* 25 */     setMaxRepeaterCount(25, 3);
/* 26 */     setDescription("Fires a torrent of deadly light particles, causing huge destruction");
/*    */     
/* 28 */     this.onUseEvent = this::onUseEvent;
/* 29 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity playerEntity) {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 40 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/* 41 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 46 */     for (int i = 0; i < WyHelper.randomWithRange(3, 6); i++) {
/*    */       
/* 48 */       YasakaniNoMagatamaProjectile proj = new YasakaniNoMagatamaProjectile(player.world, (LivingEntity)player);
/* 49 */       player.world.addEntity((Entity)proj);
/* 50 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.5F, 30.0F);
/*    */     } 
/*    */     
/* 53 */     player.world.playSound(null, player.getPosition(), ModSounds.PIKA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnimation getAnimation() {
/* 61 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 67 */     return isContinuous();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\YasakaniNoMagatamaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */