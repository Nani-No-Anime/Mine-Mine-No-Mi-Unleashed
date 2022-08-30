/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KaenBoshiProjectile extends AbilityProjectileEntity {
/*    */   public KaenBoshiProjectile(World world) {
/* 26 */     super(SniperProjectiles.KAEN_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KaenBoshiProjectile(EntityType type, World world) {
/* 31 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KaenBoshiProjectile(World world, double x, double y, double z) {
/* 36 */     super(SniperProjectiles.KAEN_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public KaenBoshiProjectile(World world, LivingEntity player) {
/* 41 */     super(SniperProjectiles.KAEN_BOSHI, world, player);
/*    */     
/* 43 */     setDamage(8.0F);
/* 44 */     setPhysical(false);
/* 45 */     setAffectedByImbuing();
/* 46 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 47 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 48 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 53 */     if (hitEntity instanceof CreeperEntity) {
/*    */       
/* 55 */       ((CreeperEntity)hitEntity).ignite();
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
/* 60 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 61 */       hitEntity.setFire(10);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 66 */     if (this.world.getBlockState(hit).getBlock() == ModBlocks.OIL_SPILL) {
/*    */       
/* 68 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
/* 69 */       explosion.setStaticDamage(10.0F);
/* 70 */       explosion.setFireAfterExplosion(true);
/* 71 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/*    */       
/* 73 */       explosion.doExplosion();
/*    */       
/*    */       return;
/*    */     } 
/* 77 */     AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 82 */     if (!this.world.isRemote)
/*    */     {
/* 84 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 86 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 87 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 88 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */         
/* 90 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 91 */         data.setLife(10);
/* 92 */         data.setSize(0.5F);
/* 93 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.2D + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\KaenBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */