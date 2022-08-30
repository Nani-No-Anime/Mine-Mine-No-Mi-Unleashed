/*    */ package xyz.pixelatedw.mineminenomi.abilities.zou;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class IvoryDartAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
/* 27 */   public static final IvoryDartAbility INSTANCE = new IvoryDartAbility();
/*    */   
/* 29 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
/*    */   
/*    */   private int initialY;
/*    */ 
/*    */   
/*    */   public IvoryDartAbility() {
/* 35 */     super("Ivory Dart", AbilityHelper.getDevilFruitCategory());
/* 36 */     setDescription("Launches the user forward in their elephant form, causing damage and destruction");
/* 37 */     setMaxCooldown(14.0D);
/*    */     
/* 39 */     this.onUseEvent = this::onUseEvent;
/* 40 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 45 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 46 */       return false;
/*    */     }
/* 48 */     clearTargets();
/*    */     
/* 50 */     this.initialY = (int)player.getPosY();
/* 51 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 4.0D, 4.0D);
/* 52 */     player.setMotion(speed.x, player.getMotion().getY(), speed.z);
/* 53 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 60 */     if (canDealDamage() && player.getPosY() >= this.initialY) {
/*    */       
/* 62 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 63 */       list.remove(player);
/*    */       
/* 65 */       list.forEach(entity -> {
/*    */             if (isTarget(entity)) {
/*    */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
/*    */             }
/*    */           });
/*    */       
/* 71 */       for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 4)) {
/*    */         
/* 73 */         if (location.getY() >= player.getPosY())
/*    */         {
/* 75 */           if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
/*    */           {
/* 77 */             PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
/*    */           }
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 86 */     return (this.cooldown > getMaxCooldown() * 0.7D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 92 */     return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\IvoryDartAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */