/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.DaiFunkaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class DaiFunkaAbility extends ChargeableAbility implements IPunchOverlayAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new DaiFunkaAbility();
/* 20 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0));
/*    */ 
/*    */   
/*    */   public DaiFunkaAbility() {
/* 24 */     super("Dai Funka", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(7.0D);
/* 26 */     setMaxChargeTime(1.0D);
/* 27 */     setDescription("Transforms the user's fist into pure magma before expanding and throwing it forward");
/*    */     
/* 29 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 34 */     DaiFunkaProjectile proj = new DaiFunkaProjectile(player.world, (LivingEntity)player);
/* 35 */     player.world.addEntity((Entity)proj);
/* 36 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/* 37 */     player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 45 */     return isCharging() ? OVERLAY : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\DaiFunkaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */