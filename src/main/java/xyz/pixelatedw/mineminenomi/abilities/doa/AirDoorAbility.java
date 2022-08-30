/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class AirDoorAbility extends DamagedContinuousAbility {
/* 15 */   public static final AirDoorAbility INSTANCE = new AirDoorAbility();
/*    */ 
/*    */   
/*    */   public AirDoorAbility() {
/* 19 */     super("Air Door", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("The user travels into an air dimension and is invincible during that time");
/* 21 */     setThreshold(60.0D);
/*    */     
/* 23 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 24 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 25 */     this.onDamagedEvent = this::onDamaged;
/*    */   }
/*    */   
/*    */   private boolean onDamaged(LivingEntity entity, DamageSource damageSource, double amount) {
/* 29 */     return damageSource.isDamageAbsolute();
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1200, 0, false, false));
/* 35 */     player.world.playSound(null, player.getPosition(), ModSounds.DOA_IN_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 42 */     player.removePotionEffect(Effects.INVISIBILITY);
/* 43 */     player.world.playSound(null, player.getPosition(), ModSounds.DOA_OUT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 45 */     double cooldown = (10.0F + this.continueTime / 40.0F);
/* 46 */     setMaxCooldown(cooldown);
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\AirDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */