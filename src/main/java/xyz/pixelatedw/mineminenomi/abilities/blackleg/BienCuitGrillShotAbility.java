/*    */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.BienCuitGrillShotParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BienCuitGrillShotAbility extends Ability implements IMultiTargetAbility {
/* 25 */   public static final BienCuitGrillShotAbility INSTANCE = new BienCuitGrillShotAbility();
/*    */   
/* 27 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BienCuitGrillShotParticleEffect();
/*    */ 
/*    */   
/*    */   public BienCuitGrillShotAbility() {
/* 31 */     super("Bien Cuit: Grill Shot", AbilityHelper.getStyleCategory());
/* 32 */     setMaxCooldown(20.0D);
/* 33 */     setDescription("A strong kick that launches the user forwards and creates a grill-patterened particle to appear, which sets anyone touching it on fire");
/*    */     
/* 35 */     this.onUseEvent = this::onUseEvent;
/* 36 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 41 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 42 */       return false;
/*    */     }
/* 44 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 45 */     Ability diableJambeAbility = props.getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
/* 46 */     if (diableJambeAbility == null || !diableJambeAbility.isContinuous()) {
/*    */       
/* 48 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_DIABLE_JAMBE, new Object[0]));
/* 49 */       return false;
/*    */     } 
/*    */     
/* 52 */     clearTargets();
/*    */     
/* 54 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 55 */     player.setMotion(speed.x, 0.3D, speed.z);
/* 56 */     player.velocityChanged = true;
/* 57 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 59 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 66 */     if (canDealDamage()) {
/*    */       
/* 68 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.8D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 69 */       targets.remove(player);
/*    */       
/* 71 */       for (LivingEntity entity : targets) {
/*    */         
/* 73 */         if (isTarget(entity)) {
/*    */           
/* 75 */           entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 30.0F);
/* 76 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/* 77 */           entity.setMotion(speed.x, 0.2D, speed.z);
/* 78 */           entity.velocityChanged = true;
/* 79 */           entity.setFire(2);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 88 */     return (getCooldownPercentage() > 90.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\BienCuitGrillShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */