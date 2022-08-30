/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg.RadicalBeamProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class RadicalBeamAbility extends ChargeableAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new RadicalBeamAbility();
/*    */   
/*    */   private static final int COLA_REQUIRED = 30;
/*    */ 
/*    */   
/*    */   public RadicalBeamAbility() {
/* 25 */     super("Radical Beam", AbilityHelper.getRacialCategory());
/* 26 */     setMaxCooldown(15.0D);
/* 27 */     setMaxChargeTime(3.0D);
/* 28 */     setDescription("The user launches a powerful beam of energy at the opponent\nConsumes §230§r cola");
/*    */     
/* 30 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 31 */     this.duringChargingEvent = this::duringChargingEvent;
/* 32 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int i) {
/* 37 */     if (i == 39) {
/* 38 */       player.world.playMovingSound(null, (Entity)player, ModSounds.PRE_CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 43 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 44 */     if (props.getCola() - 30 < 0) {
/*    */       
/* 46 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     player.world.playMovingSound(null, (Entity)player, ModSounds.CHARGE_CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 56 */     player.world.playMovingSound(null, (Entity)player, ModSounds.CYBORG_BEAM_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 57 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 59 */     RadicalBeamProjectile proj = new RadicalBeamProjectile(player.world, (LivingEntity)player);
/* 60 */     player.world.addEntity((Entity)proj);
/* 61 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.75F, 1.0F);
/*    */     
/* 63 */     props.alterCola(-30);
/* 64 */     WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\RadicalBeamAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */