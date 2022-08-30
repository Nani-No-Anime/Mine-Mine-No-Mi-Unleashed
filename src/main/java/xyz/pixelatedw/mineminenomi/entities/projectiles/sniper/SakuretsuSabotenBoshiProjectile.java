/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SakuretsuSabotenBoshiProjectile extends AbilityProjectileEntity {
/*    */   public SakuretsuSabotenBoshiProjectile(World world) {
/* 19 */     super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SakuretsuSabotenBoshiProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SakuretsuSabotenBoshiProjectile(World world, double x, double y, double z) {
/* 29 */     super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SakuretsuSabotenBoshiProjectile(World world, LivingEntity player) {
/* 34 */     super(SniperProjectiles.SAKURETSU_SABOTEN_BOSHI, world, player);
/*    */     
/* 36 */     setDamage(15.0F);
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/*    */     
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 45 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 5.0F);
/* 46 */     explosion.setStaticDamage(20.0F);
/* 47 */     explosion.setExplosionSound(true);
/* 48 */     explosion.setDamageOwner(false);
/* 49 */     explosion.setDestroyBlocks(false);
/* 50 */     explosion.setFireAfterExplosion(false);
/* 51 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
/* 52 */     explosion.setDamageEntities(false);
/* 53 */     explosion.doExplosion();
/*    */     
/* 55 */     int flag = 50;
/*    */     
/* 57 */     for (int i = 0; i < 8; i++) {
/*    */       
/* 59 */       int a1 = (int)WyHelper.randomWithRange(-5, 5);
/* 60 */       int a2 = (int)WyHelper.randomWithRange(-5, 5);
/*    */       
/* 62 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 3), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/* 63 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 2), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/* 64 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() - 1), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/* 65 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), (int)getPosY(), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/* 66 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() + 1), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/* 67 */       AbilityHelper.placeBlockIfAllowed(this.world, ((int)getPosX() + a1), ((int)getPosY() + 2), ((int)getPosZ() + a2), Blocks.CACTUS, flag, DefaultProtectionRules.AIR_FOLIAGE);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\SakuretsuSabotenBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */