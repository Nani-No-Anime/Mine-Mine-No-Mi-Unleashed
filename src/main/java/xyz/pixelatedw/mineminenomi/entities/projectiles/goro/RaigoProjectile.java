/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.projectile.ThrowableEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class RaigoProjectile extends AbilityProjectileEntity {
/*     */   private boolean explodedOnBlock = false;
/*     */   private boolean dealtAOE = false;
/*     */   private boolean closeToFloor = false;
/*  36 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/*     */ 
/*     */   
/*     */   private static final int EXPLOSION_RADIUS = 20;
/*     */ 
/*     */ 
/*     */   
/*     */   public RaigoProjectile(World world) {
/*  44 */     super(GoroProjectiles.RAIGO, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public RaigoProjectile(EntityType type, World world) {
/*  49 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public RaigoProjectile(World world, double x, double y, double z) {
/*  54 */     super(GoroProjectiles.RAIGO, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public RaigoProjectile(World world, LivingEntity player) {
/*  59 */     super(GoroProjectiles.RAIGO, world, player);
/*     */     
/*  61 */     setDamage(100.0F);
/*  62 */     setMaxLife(256);
/*  63 */     setCollisionSize(8.0D);
/*  64 */     setPassThroughEntities();
/*  65 */     setCanGetStuckInGround();
/*  66 */     setTargetResetTime(120);
/*     */     
/*  68 */     setDamageSource((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeIndirectDamageFromSource((ThrowableEntity)this));
/*     */     
/*  70 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*  71 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos hit) {
/*  76 */     AbilityHelper.createSphere(this.world, getPosition().down(2), 20, 20, false, Blocks.AIR, 3, GRIEF_RULE);
/*     */     
/*  78 */     if (!this.dealtAOE) {
/*     */       
/*  80 */       List<Entity> list = WyHelper.getEntitiesNear(getPosition(), this.world, 40.0D, new Class[] { Entity.class });
/*  81 */       list.remove(getThrower());
/*     */       
/*  83 */       for (Entity target : list) {
/*     */         
/*  85 */         if (target instanceof ThrowableEntity || target instanceof net.minecraft.entity.projectile.AbstractArrowEntity) {
/*  86 */           target.remove();
/*     */         }
/*  88 */         if (target instanceof LivingEntity) {
/*     */           
/*  90 */           ((LivingEntity)target).hurtTime = target.hurtResistantTime = 0;
/*  91 */           target.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)getThrower()), 100.0F);
/*  92 */           Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(5.0D, 0.0D, 5.0D);
/*  93 */           target.setMotion(speed.x, 1.0D, speed.z);
/*  94 */           target.velocityChanged = true;
/*     */         } 
/*     */       } 
/*  97 */       this.dealtAOE = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/* 103 */     if (!this.world.isRemote) {
/*     */       
/* 105 */       for (int i = 0; i < 25; i++) {
/*     */         
/* 107 */         ParticleType<GenericParticleData> particleToUse = (this.ticksExisted % 2 == 0) ? ModParticleTypes.GORO2 : ModParticleTypes.GORO;
/*     */         
/* 109 */         double offsetX = WyHelper.randomDouble() * 5.0D;
/* 110 */         double offsetY = WyHelper.randomDouble();
/* 111 */         double offsetZ = WyHelper.randomDouble() * 5.0D;
/*     */         
/* 113 */         GenericParticleData data = new GenericParticleData(particleToUse);
/* 114 */         data.setLife(20);
/* 115 */         data.setSize(7.0F);
/* 116 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*     */       } 
/*     */       
/* 119 */       setRotation(0.0F, 90.0F);
/* 120 */       if (!this.closeToFloor) {
/*     */         
/* 122 */         BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)this, 30.0D);
/* 123 */         if (blockRayTraceResult.getType().equals(RayTraceResult.Type.BLOCK)) {
/*     */           
/* 125 */           setMaxLife(16);
/* 126 */           this.closeToFloor = true;
/*     */         } 
/*     */       } 
/*     */       
/* 130 */       if (this.ticksExisted % 5 == 0)
/*     */       {
/* 132 */         for (int j = 0; j < 10; j++) {
/*     */           
/* 134 */           float boltLength = (float)WyHelper.randomWithRange(36, 50);
/*     */ 
/*     */           
/* 137 */           LightningEntity bolt = new LightningEntity((Entity)this, getPosX(), getPosY(), getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(-90, 90), boltLength, boltLength);
/*     */           
/* 139 */           bolt.setAngle(20);
/* 140 */           bolt.setAliveTicks(20);
/* 141 */           bolt.setDamage(0.0F);
/* 142 */           bolt.setExplosion(0, false);
/* 143 */           bolt.setSize(boltLength / 800.0F);
/* 144 */           bolt.setBranches(3);
/* 145 */           bolt.setSegments(10);
/* 146 */           this.world.addEntity(bolt);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\RaigoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */