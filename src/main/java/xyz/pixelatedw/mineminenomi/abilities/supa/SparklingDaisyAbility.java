/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SparklingDaisyAbility
/*    */   extends Ability implements IMultiTargetAbility {
/* 21 */   public static final SparklingDaisyAbility INSTANCE = new SparklingDaisyAbility();
/*    */   
/* 23 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
/*    */   
/*    */   private int initialY;
/*    */ 
/*    */   
/*    */   public SparklingDaisyAbility() {
/* 29 */     super("Sparkling Daisy", AbilityHelper.getDevilFruitCategory());
/* 30 */     setDescription("Launches the user forward, slicing anything in their path");
/* 31 */     setMaxCooldown(7.0D);
/*    */     
/* 33 */     this.onUseEvent = this::onUseEvent;
/* 34 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 39 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 40 */       return false;
/*    */     }
/* 42 */     clearTargets();
/* 43 */     this.initialY = (int)player.getPosY();
/* 44 */     double[] motion = AbilityHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 45 */     player.setMotion(motion[0], (player.getMotion()).y, motion[1]);
/* 46 */     player.velocityChanged = true;
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldownEvent(PlayerEntity player, int cooldownTimer) {
/* 53 */     if (getCooldown() > getMaxCooldown() * 0.9D && player.getPosY() >= this.initialY) {
/*    */       
/* 55 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 56 */       list.remove(player);
/* 57 */       list.forEach(entity -> {
/*    */             if (isTarget(entity)) {
/*    */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 25.0F);
/*    */             }
/*    */           });
/*    */       
/* 63 */       for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 3)) {
/*    */         
/* 65 */         if (location.getY() >= player.getPosY())
/*    */         {
/* 67 */           if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
/*    */           {
/* 69 */             PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
/*    */           }
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SparklingDaisyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */