/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.stream.IntStream;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.FallingBlockEntity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileShootEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class SablesProjectile extends AbilityProjectileEntity {
/*  27 */   public Vec3d firstVector = null;
/*  28 */   public Vec3d vector = null;
/*     */   boolean shoot = false;
/*  30 */   public float mult = 1.0F;
/*  31 */   private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();
/*     */ 
/*     */   
/*     */   public SablesProjectile(World world) {
/*  35 */     super(SunaProjectiles.SABLES, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public SablesProjectile(EntityType type, World world) {
/*  40 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public SablesProjectile(World world, double x, double y, double z) {
/*  45 */     super(SunaProjectiles.SABLES, world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public SablesProjectile(World world, LivingEntity player) {
/*  50 */     super(SunaProjectiles.SABLES, world, player);
/*     */     
/*  52 */     setDamage(0.0F);
/*  53 */     setMaxLife(300);
/*  54 */     setPhysical(false);
/*  55 */     setPassThroughEntities();
/*     */     
/*  57 */     this.onTickEvent = this::onTickEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void onTickEvent() {
/*  62 */     if (!this.world.isRemote) {
/*     */       
/*  64 */       if (this.firstVector == null)
/*     */       {
/*  66 */         this.firstVector = getPositionVec();
/*     */       }
/*     */       
/*  69 */       PARTICLES.mult = this.mult;
/*  70 */       PARTICLES.spawn(this.world, getPosX(), getPosY() - 10.0D, getPosZ(), (getMotion()).x, (getMotion()).y, (getMotion()).z);
/*     */       
/*  72 */       float growthXZ = 4.0F + 6.0F * this.mult;
/*  73 */       float growthY = 10.0F + 12.0F * this.mult;
/*     */       
/*  75 */       ArrayList<FallingBlockEntity> list = new ArrayList<>();
/*  76 */       AxisAlignedBB box = (new AxisAlignedBB(new BlockPos(getPositionVec()))).grow(growthXZ, growthY, growthXZ);
/*  77 */       for (Entity entity : (getThrower()).world.getEntitiesWithinAABB(Entity.class, box, e -> (e != getThrower()))) {
/*     */         
/*  79 */         entity.setMotion((entity.getMotion()).x + (getPosX() - entity.getPosX()) / 25.0D, 
/*  80 */             (entity.getMotion()).y + (getPosY() - entity.getPosY()) / 25.0D, 
/*  81 */             (entity.getMotion()).z + (getPosZ() - entity.getPosZ()) / 25.0D);
/*  82 */         entity.velocityChanged = true;
/*     */         
/*  84 */         if (entity instanceof FallingBlockEntity) {
/*     */           
/*  86 */           if (!list.contains(entity))
/*     */           {
/*  88 */             list.add((FallingBlockEntity)entity);
/*     */           }
/*  90 */           if (list.size() > 900)
/*     */           {
/*  92 */             IntStream.range(0, 100).forEach(i -> {
/*     */                   ((FallingBlockEntity)list.get(i)).setMotion((entity.getMotion()).x + getPosX() - entity.getPosX(), (entity.getMotion()).y + getPosY() - entity.getPosY(), (entity.getMotion()).z + getPosZ() - entity.getPosZ());
/*     */ 
/*     */                   
/*     */                   list.remove(i);
/*     */                 });
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 102 */         if (getDistance(entity) < 2.0F) {
/* 103 */           entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, 10.0F * this.mult);
/*     */         }
/*     */       } 
/* 106 */       box = (new AxisAlignedBB(new BlockPos(getPositionVec()))).grow((growthXZ / 2.0F), 15.0D, (growthXZ / 2.0F));
/*     */ 
/*     */       
/* 109 */       if (CommonConfig.INSTANCE.isAbilityGriefingEnabled()) {
/*     */         double x;
/* 111 */         for (x = box.minX; x < box.maxX; x++) {
/* 112 */           double y; for (y = box.minY; y < box.maxY; y++) {
/* 113 */             double z; for (z = box.minZ; z < box.maxZ; z++) {
/*     */               
/* 115 */               BlockPos blockPos = new BlockPos(x, y, z);
/* 116 */               BlockState state = this.world.getBlockState(blockPos);
/* 117 */               if (!state.isAir((IBlockReader)this.world, blockPos) && this.rand.nextFloat() > 0.995D && DefaultProtectionRules.CORE_FOLIAGE_ORE.isPresent(state)) {
/*     */                 
/* 119 */                 FallingBlockEntity fallingBlock = new FallingBlockEntity(this.world, x, y, z, state);
/* 120 */                 fallingBlock.setMotion(0.0D, WyHelper.randomDouble() / 3.0D, 0.0D);
/* 121 */                 fallingBlock.velocityChanged = true;
/* 122 */                 fallingBlock.shouldDropItem = false;
/* 123 */                 fallingBlock.fallTime = 1;
/* 124 */                 this.world.addEntity((Entity)fallingBlock);
/* 125 */                 this.world.removeBlock(blockPos, false);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 130 */       }  if (this.vector != null && !this.shoot) {
/*     */         
/* 132 */         Vec3d dist = getPositionVec().subtract(this.vector).add(0.0D, -1.0D, 0.0D);
/* 133 */         double speedReduction = 20.0D;
/* 134 */         double speed = 0.4D;
/* 135 */         double xSpeed = Math.min(speed, -dist.x / speedReduction);
/* 136 */         double ySpeed = Math.min(speed, -dist.y / speedReduction);
/* 137 */         double zSpeed = Math.min(speed, -dist.z / speedReduction);
/* 138 */         setMotion(xSpeed, ySpeed, zSpeed);
/*     */       } 
/*     */ 
/*     */       
/* 142 */       if (isWet() && this.ticksExisted % 20 == 0) {
/*     */         
/* 144 */         this.mult = (float)(this.mult - 0.1D);
/* 145 */         if (0.0F > this.mult) {
/* 146 */           remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
/* 154 */     this.shoot = true;
/*     */     
/* 156 */     ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
/* 157 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */       return;
/*     */     }
/* 160 */     Vec3d dist = entityThrower.getPositionVec().add(0.0D, entityThrower.getEyeHeight(), 0.0D).subtract(this.vector).add(0.0D, -1.0D, 0.0D);
/* 161 */     double speedReduction = 4.0D;
/* 162 */     double xSpeed = -dist.x / speedReduction;
/* 163 */     double zSpeed = -dist.z / speedReduction;
/* 164 */     setMotion(MathHelper.clamp(xSpeed, -1.0D, 1.0D), 0.0D, MathHelper.clamp(zSpeed, -1.0D, 1.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\SablesProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */