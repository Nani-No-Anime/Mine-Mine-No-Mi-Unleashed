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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*     */ 
/*     */ public class DaiEnkaiEnteiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*  29 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE });
/*     */ 
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(World world) {
/*  33 */     super(MeraProjectiles.DAI_ENKAI_ENTEI, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(EntityType type, World world) {
/*  38 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(World world, double x, double y, double z) {
/*  43 */     super(MeraProjectiles.DAI_ENKAI_ENTEI, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public DaiEnkaiEnteiProjectile(World world, LivingEntity player) {
/*  48 */     super(MeraProjectiles.DAI_ENKAI_ENTEI, world, player);
/*     */     
/*  50 */     setDamage(80.0F);
/*  51 */     setDamageSource(ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/*     */     
/*  53 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  54 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  59 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 14.0F);
/*  60 */     explosion.setStaticDamage(80.0F);
/*  61 */     explosion.setStaticBlockResistance(0.25F);
/*  62 */     explosion.setFireAfterExplosion(true);
/*  63 */     explosion.setSmokeParticles(new CommonExplosionParticleEffect(15));
/*  64 */     explosion.setDamageSource(ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/*  65 */     explosion.doExplosion();
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  70 */     if (areEyesInFluid(FluidTags.WATER) && CommonConfig.INSTANCE.getDestroyWater()) {
/*     */       
/*  72 */       List<BlockPos> coords = AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 9, Blocks.AIR, GRIEF_RULE);
/*  73 */       for (BlockPos blockPos : coords) {
/*     */         
/*  75 */         WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)getEntityWorld(), blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
/*  76 */         getEntityWorld().addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.getY() + 1.1D, blockPos.getZ(), 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     if (!this.world.isRemote) {
/*     */       int i;
/*  82 */       for (i = 0; i < 20; i++) {
/*     */         
/*  84 */         double offsetX = WyHelper.randomDouble();
/*  85 */         double offsetY = WyHelper.randomDouble();
/*  86 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  88 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/*  89 */         data.setLife(6);
/*  90 */         data.setSize(1.3F);
/*  91 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/*  94 */       for (i = 0; i < 2; i++) {
/*     */         
/*  96 */         double offsetX = WyHelper.randomDouble();
/*  97 */         double offsetY = WyHelper.randomDouble();
/*  98 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/* 100 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 101 */         data.setLife(4);
/* 102 */         data.setSize(1.2F);
/* 103 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerData() {
/* 111 */     super.registerData();
/* 112 */     this.dataManager.register(SIZE, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseSize() {
/* 117 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(((Float)this.dataManager.get(SIZE)).floatValue() + 0.1F, 17.5F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 123 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 17.5F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 129 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\DaiEnkaiEnteiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */