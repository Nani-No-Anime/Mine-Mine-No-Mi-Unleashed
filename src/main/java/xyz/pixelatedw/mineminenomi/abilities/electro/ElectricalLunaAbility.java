/*    */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.electro.ElectricalLunaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class ElectricalLunaAbility extends ChargeableAbility {
/* 15 */   public static final ElectricalLunaAbility INSTANCE = new ElectricalLunaAbility();
/* 16 */   private static final ElectroChargingParticleEffect PARTICLES = new ElectroChargingParticleEffect();
/*    */   
/*    */   private static final int COOLDOWN = 10;
/*    */   
/*    */   public ElectricalLunaAbility() {
/* 21 */     super("Electrical Luna", AbilityHelper.getRacialCategory());
/* 22 */     setMaxCooldown(10.0D);
/* 23 */     setMaxChargeTime(2.0D);
/* 24 */     setDescription("Discharges a lightning stream from the ground beneath the user");
/* 25 */     addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
/*    */     
/* 27 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 28 */     this.duringChargingEvent = this::duringChargingEvent;
/* 29 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int i) {
/* 34 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 39 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/* 40 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/* 41 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 42 */       return false;
/*    */     }
/* 44 */     if (!eleclawEnabled) {
/*    */       
/* 46 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 51 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/* 52 */     setMaxChargeTime(sulongEnabled ? 1.0D : 2.0D);
/* 53 */     setMaxCooldown(sulongEnabled ? 5.0D : 10.0D);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 60 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/* 61 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/*    */     
/* 63 */     if (eleclawEnabled) {
/*    */       
/* 65 */       SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 66 */       boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*    */       
/* 68 */       float damage = (30 + (sulongEnabled ? 30 : 0));
/* 69 */       int maxLife = 20 + (sulongEnabled ? 20 : 0);
/*    */       
/* 71 */       ElectricalLunaProjectile proj = new ElectricalLunaProjectile(player.world, (LivingEntity)player);
/* 72 */       proj.setDamage(damage);
/* 73 */       proj.setMaxLife(maxLife);
/* 74 */       player.world.addEntity((Entity)proj);
/* 75 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, sulongEnabled ? 3.0F : 2.0F, 1.0F);
/*    */       
/* 77 */       eleclawAbility.reduceUsage(player, 2);
/*    */     } 
/*    */     
/* 80 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalLunaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */