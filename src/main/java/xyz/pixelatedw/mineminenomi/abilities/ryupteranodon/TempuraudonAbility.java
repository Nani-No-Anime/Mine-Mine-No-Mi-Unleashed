/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.TempuraudonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class TempuraudonAbility extends ChargeableAbility implements IFormRequiredAbility {
/* 16 */   public static final TempuraudonAbility INSTANCE = new TempuraudonAbility();
/*    */ 
/*    */   
/*    */   public TempuraudonAbility() {
/* 20 */     super("Tempuraudon", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("Stretches its head back, releasing it really fast and acting as a sniper");
/* 22 */     setMaxChargeTime(1.5D);
/* 23 */     setMaxCooldown(12.0D);
/*    */     
/* 25 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 30 */     TempuraudonProjectile proj = new TempuraudonProjectile(player.world, (LivingEntity)player);
/* 31 */     player.world.addEntity((Entity)proj);
/* 32 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 0.0F);
/* 33 */     player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 41 */     return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE, (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\TempuraudonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */