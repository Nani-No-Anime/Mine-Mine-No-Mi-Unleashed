/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.IndirectEntityDamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*     */ 
/*     */ public class SagariNoRyuseiProjectile extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*     */   public SagariNoRyuseiProjectile(World world) {
/*  26 */     super(ZushiProjectiles.SAGARI_NO_RYUSEI, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public SagariNoRyuseiProjectile(EntityType type, World world) {
/*  31 */     super(type, world);
/*     */   }
/*     */   private static final int MAX_DAMAGE = 95;
/*     */   
/*     */   public SagariNoRyuseiProjectile(World world, double x, double y, double z) {
/*  36 */     super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public SagariNoRyuseiProjectile(World world, LivingEntity player) {
/*  41 */     super(ZushiProjectiles.SAGARI_NO_RYUSEI, world, player);
/*     */     
/*  43 */     setDamage(95.0F);
/*  44 */     setArmorPiercing();
/*  45 */     setMaxLife(256);
/*  46 */     setPhysical(false);
/*  47 */     setHurtThrower();
/*     */     
/*  49 */     setDamageSource((new IndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
/*  50 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  51 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  56 */     float mult = getSize() / 30.0F;
/*  57 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 20.0F * mult);
/*  58 */     explosion.setStaticDamage(90.0F * mult);
/*  59 */     explosion.addRemovedBlocksToList();
/*  60 */     explosion.setDamageOwner(true);
/*  61 */     explosion.setDamageSource((new IndirectEntityDamageSource("explosion", (Entity)this, null)).setExplosion());
/*  62 */     explosion.setFireAfterExplosion(true);
/*  63 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(20.0F * mult)));
/*  64 */     explosion.doExplosion();
/*     */     
/*  66 */     int size = 0;
/*  67 */     for (FallingBlockEntity entity : explosion.removedBlocks) {
/*     */       
/*  69 */       entity.setMotion(WyHelper.randomWithRange(-1, 1) / 2.0D * mult, (0.75D + 
/*  70 */           WyHelper.randomDouble()) * mult, 
/*  71 */           WyHelper.randomWithRange(-1, 1) / 2.0D * mult);
/*  72 */       entity.velocityChanged = true;
/*  73 */       entity.shouldDropItem = false;
/*  74 */       entity.fallTime = 1;
/*  75 */       this.world.addEntity((Entity)entity);
/*  76 */       size++;
/*     */       
/*  78 */       if (size > 256) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onTickEvent() {
/*  85 */     float mult = getSize() / 30.0F;
/*     */     
/*  87 */     if (1 > this.ticksExisted) {
/*  88 */       setBoundingBox(getBoundingBox().grow(mult));
/*     */     }
/*  90 */     if (!this.world.isRemote) {
/*     */       
/*  92 */       setDamage(95.0F * mult);
/*     */       
/*  94 */       for (int i = 0; i < 25; i++) {
/*     */         
/*  96 */         ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.MOKU : ModParticleTypes.MERA;
/*     */         
/*  98 */         double offsetX = WyHelper.randomDouble() * 5.0D * mult;
/*  99 */         double offsetY = WyHelper.randomDouble();
/* 100 */         double offsetZ = WyHelper.randomDouble() * 5.0D * mult;
/*     */         
/* 102 */         GenericParticleData data = new GenericParticleData(particleToUse);
/* 103 */         data.setLife(20);
/* 104 */         data.setSize(7.0F * mult);
/*     */         
/* 106 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerData() {
/* 113 */     super.registerData();
/* 114 */     this.dataManager.register(SIZE, Float.valueOf(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(float size) {
/* 119 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 30.0F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 125 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zushi\SagariNoRyuseiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */