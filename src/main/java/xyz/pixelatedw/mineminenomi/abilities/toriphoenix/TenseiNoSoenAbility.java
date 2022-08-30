/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoenParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class TenseiNoSoenAbility extends ChargeableAbility implements IFormRequiredAbility {
/* 26 */   public static final TenseiNoSoenAbility INSTANCE = new TenseiNoSoenAbility();
/*    */   
/* 28 */   private static final ParticleEffect PARTICLES1 = (ParticleEffect)new TenseiNoSoenParticleEffect();
/* 29 */   private static final ParticleEffect PARTICLES2 = (ParticleEffect)new TenseiNoSoen2ParticleEffect();
/*    */ 
/*    */   
/*    */   public TenseiNoSoenAbility() {
/* 33 */     super("Tensei no Soen", AbilityHelper.getDevilFruitCategory());
/* 34 */     setMaxChargeTime(3.0D);
/* 35 */     setMaxCooldown(30.0D);
/* 36 */     setDescription("While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave");
/*    */     
/* 38 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 39 */     this.duringChargingEvent = this::duringChargingEvent;
/* 40 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 45 */     if (player.onGround) {
/*    */       
/* 47 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
/* 48 */       return false;
/*    */     } 
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int time) {
/* 56 */     PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 58 */     if (!player.onGround && time == 1) {
/*    */       
/* 60 */       setChargeTime(1);
/* 61 */       player.abilities.isFlying = false;
/* 62 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/* 63 */       player.setMotion((player.getMotion()).x, -700.0D, (player.getMotion()).z);
/* 64 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 65 */       player.fallDistance = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 71 */     player.fallDistance = 0.0F;
/*    */     
/* 73 */     PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 75 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 76 */     list.remove(player);
/*    */     
/* 78 */     for (LivingEntity target : list) {
/* 79 */       target.attackEntityFrom(ModDamageSource.causePlayerDamage(player), 50.0F);
/*    */     }
/* 81 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 87 */     return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\TenseiNoSoenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */