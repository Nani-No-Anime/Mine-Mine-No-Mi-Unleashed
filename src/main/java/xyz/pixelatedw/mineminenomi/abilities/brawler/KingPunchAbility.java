/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.brawler.KingPunchProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.brawler.KingPunchChargeParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KingPunchAbility extends ChargeableAbility {
/* 19 */   public static final KingPunchAbility INSTANCE = new KingPunchAbility();
/*    */   
/* 21 */   private static final KingPunchChargeParticleEffect PARTICLES = new KingPunchChargeParticleEffect();
/*    */   
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public KingPunchAbility() {
/* 26 */     super("King Punch", AbilityHelper.getStyleCategory());
/* 27 */     setDescription("A punch of exceptionally concentrated strength, capable of releasing an immense amount of air pressure, but needs a long time to fully charge");
/* 28 */     setMaxCooldown(50.0D);
/* 29 */     setMaxChargeTime(50.0D);
/* 30 */     setCancelable();
/*    */     
/* 32 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 33 */     this.onEndChargingEvent = this::onEndChargingEvent;
/* 34 */     this.duringChargingEvent = this::duringChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 39 */     this.cancelled = false;
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 45 */     if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
/*    */       
/* 47 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/* 48 */       this.cancelled = true;
/* 49 */       endCharging(player);
/*    */     } 
/*    */     
/* 52 */     if (player.hurtResistantTime > 0) {
/*    */       
/* 54 */       this.cancelled = true;
/* 55 */       endCharging(player);
/*    */     } 
/*    */     
/* 58 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*    */     
/* 60 */     if (chargeTimer % 2 == 0) {
/* 61 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 66 */     int charge = getMaxChargeTime() - getChargeTime();
/*    */     
/* 68 */     if (this.cancelled || charge < 300) {
/* 69 */       return true;
/*    */     }
/* 71 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 73 */     KingPunchProjectile kingPunchProjectile = new KingPunchProjectile(player.world, (LivingEntity)player);
/* 74 */     kingPunchProjectile.setDamage(charge / 12.0F);
/* 75 */     ((AbilityProjectileEntity)kingPunchProjectile).onBlockImpactEvent = (pos -> {
                 //error: cannot find symbol fix
                 AbilityProjectileEntity proj= kingPunchProjectile;
/*    */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, proj.getPosX(), proj.getPosY(), proj.getPosZ(), 1.0F + charge / 70.0F);
/*    */         
/*    */         explosion.setStaticDamage(0.0F);
/*    */         explosion.setExplosionSound(false);
/*    */         explosion.setDamageOwner(false);
/*    */         explosion.setDestroyBlocks(true);
/*    */         explosion.setFireAfterExplosion(false);
/*    */         explosion.setDamageEntities(false);
/*    */         explosion.doExplosion();
/*    */       });
/* 86 */     player.world.addEntity((Entity)kingPunchProjectile);
/* 87 */     kingPunchProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 88 */     player.removePotionEffect(ModEffects.MOVEMENT_BLOCKED);
/*    */     
/* 90 */     int cooldown = (int)Math.round(charge / 20.0D) + 5;
/* 91 */     setMaxCooldown(cooldown);
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\KingPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */