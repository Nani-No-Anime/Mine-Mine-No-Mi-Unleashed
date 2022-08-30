/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.SnowLayerBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class HikenProjectile extends AbilityProjectileEntity {
/*  28 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE });
/*     */ 
/*     */   
/*     */   public HikenProjectile(World world) {
/*  32 */     super(MeraProjectiles.HIKEN, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HikenProjectile(EntityType type, World world) {
/*  37 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HikenProjectile(World world, double x, double y, double z) {
/*  42 */     super(MeraProjectiles.HIKEN, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public HikenProjectile(World world, LivingEntity player) {
/*  47 */     super(MeraProjectiles.HIKEN, world, player);
/*     */     
/*  49 */     setDamage(50.0F);
/*  50 */     setCanGetStuckInGround();
/*  51 */     setPassThroughEntities();
/*  52 */     setMaxLife(32);
/*  53 */     setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/*     */     
/*  55 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  56 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  61 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/*  62 */     explosion.setStaticDamage(25.0F);
/*  63 */     explosion.setHeightDifference(30);
/*  64 */     explosion.disableExplosionKnockback();
/*  65 */     explosion.setFireAfterExplosion(true);
/*  66 */     explosion.setExplosionSound(false);
/*  67 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  68 */     explosion.setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/*  69 */     explosion.doExplosion();
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  74 */     if (this.ticksExisted > 2) {
/*     */       
/*  76 */       BlockPos pos = getPosition();
/*  77 */       this.onBlockImpactEvent.onImpact(pos);
/*     */     } 
/*     */     
/*  80 */     if (areEyesInFluid(FluidTags.WATER) && CommonConfig.INSTANCE.getDestroyWater()) {
/*     */       
/*  82 */       List<BlockPos> coords = AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.AIR, GRIEF_RULE);
/*  83 */       for (BlockPos blockPos : coords) {
/*     */         
/*  85 */         WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)getEntityWorld(), blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
/*  86 */         getEntityWorld().addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.getY() + 1.1D, blockPos.getZ(), 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     if (!this.world.isRemote) {
/*     */       int i;
/*  92 */       for (i = 0; i < 20; i++) {
/*     */         
/*  94 */         double offsetX = WyHelper.randomDouble() * 2.0D;
/*  95 */         double offsetY = WyHelper.randomDouble() * 2.0D;
/*  96 */         double offsetZ = WyHelper.randomDouble() * 2.0D;
/*     */         
/*  98 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/*  99 */         data.setMotion((getMotion()).x / 10.0D, (getMotion()).y / 10.0D, (getMotion()).z / 10.0D);
/* 100 */         data.setLife(60);
/* 101 */         data.setSize(3.0F);
/* 102 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/* 105 */       for (i = 0; i < 10; i++) {
/*     */         
/* 107 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 108 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 109 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/* 111 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 112 */         data.setLife(7);
/* 113 */         data.setSize(1.2F);
/* 114 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HikenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */