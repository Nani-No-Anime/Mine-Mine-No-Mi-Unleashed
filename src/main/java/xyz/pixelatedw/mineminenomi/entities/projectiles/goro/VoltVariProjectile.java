/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*     */ 
/*     */ public class VoltVariProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*     */   public VoltVariProjectile(World world) {
/*  25 */     super(GoroProjectiles.VOLT_VARI, world);
/*     */   }
/*     */   private ExplosionAbility explosion;
/*     */   
/*     */   public VoltVariProjectile(EntityType type, World world) {
/*  30 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoltVariProjectile(World world, double x, double y, double z) {
/*  35 */     super(GoroProjectiles.VOLT_VARI, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoltVariProjectile(World world, LivingEntity player, float power) {
/*  40 */     super(GoroProjectiles.VOLT_VARI, world, player);
/*     */     
/*  42 */     setMaxLife(40);
/*  43 */     setPassThroughEntities();
/*     */     
/*  45 */     this.onTickEvent = this::onTickEvent;
/*  46 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*     */     
/*  48 */     this.explosion = AbilityHelper.newExplosion(getThrower(), this.world, 0.0D, 0.0D, 0.0D, 0.0F);
/*  49 */     this.explosion.setStaticDamage(5.0F + power / 10.0F);
/*  50 */     this.explosion.setExplosionSound(true);
/*  51 */     this.explosion.setDamageOwner(false);
/*  52 */     this.explosion.setDestroyBlocks(true);
/*  53 */     this.explosion.setStaticBlockResistance(0.1F);
/*  54 */     this.explosion.setDamageEntities(true);
/*  55 */     this.explosion.setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeIndirectDamageFromSource((ThrowableEntity)this));
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  60 */     float voltage = ((Float)this.dataManager.get(SIZE)).floatValue();
/*  61 */     if (voltage > 2.0F) {
/*     */       
/*  63 */       this.explosion.setExplosionPos(hit.getX(), hit.getY(), hit.getZ());
/*  64 */       this.explosion.doExplosion();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  70 */     float voltage = ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */     
/*  72 */     if (!this.world.isRemote)
/*     */     {
/*  74 */       for (int i = 0; i < 5; i++) {
/*     */         
/*  76 */         ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.GORO2 : ModParticleTypes.GORO;
/*     */         
/*  78 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/*  79 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/*  80 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*     */         
/*  82 */         GenericParticleData data = new GenericParticleData(particleToUse);
/*  83 */         data.setLife(8);
/*  84 */         data.setSize(4.0F * voltage / 100.0F);
/*  85 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerData() {
/*  93 */     super.registerData();
/*  94 */     this.dataManager.register(SIZE, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 100 */     setDamage(10.0F + size * 2.0F);
/* 101 */     setBoundingBox(getBoundingBox().grow((size / 100.0F)));
/* 102 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 100.0F)));
/* 103 */     float power = ((Float)this.dataManager.get(SIZE)).floatValue() / 20.0F;
/* 104 */     this.explosion.setExplosionSize(power * 2.0F);
/* 105 */     this.explosion.setExplodedBlocksLimit((int)(power * 150.0F));
/* 106 */     this.explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(power * 0.9F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 112 */     return 0.7F + ((Float)this.dataManager.get(SIZE)).floatValue() / 10.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\VoltVariProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */