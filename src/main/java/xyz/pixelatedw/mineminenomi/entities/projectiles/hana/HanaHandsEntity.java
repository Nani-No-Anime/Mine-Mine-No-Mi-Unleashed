/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateEntityOwnerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class HanaHandsEntity
/*     */   extends Entity {
/*  27 */   private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(HanaHandsEntity.class, DataSerializers.VARINT);
/*     */   
/*  29 */   private int warmupDelayTicks = 30;
/*  30 */   private int lifeTicks = 22;
/*     */   
/*     */   private float damage;
/*     */   private boolean sentEvent;
/*     */   private boolean clientSideAttackStarted;
/*     */   private LivingEntity caster;
/*     */   private LivingEntity target;
/*     */   private UUID casterUuid;
/*     */   private Ability parentAbility;
/*     */   
/*     */   public HanaHandsEntity(EntityType<? extends HanaHandsEntity> type, World world) {
/*  41 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HanaHandsEntity(World world) {
/*  46 */     this(HanaProjectiles.HANDS, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public HanaHandsEntity(World world, Ability ability) {
/*  51 */     this(world);
/*  52 */     this.parentAbility = ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCaster(LivingEntity caster) {
/*  57 */     this.caster = caster;
/*  58 */     this.casterUuid = caster.getUniqueID();
/*     */   }
/*     */ 
/*     */   
/*     */   public LivingEntity getCaster() {
/*  63 */     if (this.caster == null && this.casterUuid != null && this.world instanceof ServerWorld) {
/*     */       
/*  65 */       Entity entity = ((ServerWorld)this.world).getEntityByUuid(this.casterUuid);
/*  66 */       if (entity instanceof LivingEntity)
/*     */       {
/*  68 */         this.caster = (LivingEntity)entity;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     return this.caster;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(LivingEntity target) {
/*  77 */     this.target = target;
/*  78 */     setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarmup(int warmup) {
/*  83 */     this.warmupDelayTicks = warmup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(float damage) {
/*  88 */     this.damage = damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClutch() {
/*  93 */     this.dataManager.set(TYPE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSlap() {
/*  98 */     this.dataManager.set(TYPE, Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHandsType() {
/* 103 */     return ((Integer)this.dataManager.get(TYPE)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/* 109 */     this.dataManager.register(TYPE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditional(CompoundNBT compound) {
/* 115 */     this.warmupDelayTicks = compound.getInt("Warmup");
/* 116 */     this.dataManager.set(TYPE, Integer.valueOf(compound.getInt("Type")));
/* 117 */     if (compound.hasUniqueId("OwnerUUID")) {
/* 118 */       this.casterUuid = compound.getUniqueId("OwnerUUID");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeAdditional(CompoundNBT compound) {
/* 124 */     compound.putInt("Warmup", this.warmupDelayTicks);
/* 125 */     compound.putInt("Type", ((Integer)this.dataManager.get(TYPE)).intValue());
/* 126 */     if (this.casterUuid != null) {
/* 127 */       compound.putUniqueId("OwnerUUID", this.casterUuid);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 133 */     super.tick();
/* 134 */     if (this.world.isRemote) {
/*     */       
/* 136 */       if (this.clientSideAttackStarted) {
/*     */         
/* 138 */         this.lifeTicks--;
/* 139 */         if (this.lifeTicks == 14)
/*     */         {
/* 141 */           for (int i = 0; i < 12; i++)
/*     */           {
/* 143 */             double d0 = getPosX() + (this.rand.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
/* 144 */             double d1 = getPosY() + 0.05D + this.rand.nextDouble();
/* 145 */             double d2 = getPosZ() + (this.rand.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
/* 146 */             double d3 = (this.rand.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 147 */             double d4 = 0.3D + this.rand.nextDouble() * 0.3D;
/* 148 */             double d5 = (this.rand.nextDouble() * 2.0D - 1.0D) * 0.3D;
/* 149 */             this.world.addParticle((IParticleData)ParticleTypes.CRIT, d0, d1 + 1.0D, d2, d3, d4, d5);
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 154 */     } else if (--this.warmupDelayTicks < 0) {
/*     */       
/* 156 */       if (this.warmupDelayTicks == -8)
/*     */       {
/* 158 */         if (this.target != null) {
/* 159 */           damage(this.target);
/*     */         }
/*     */       }
/* 162 */       if (!this.sentEvent) {
/*     */         
/* 164 */         if (getCaster() != null)
/* 165 */           WyNetwork.sendToAllAround(new SUpdateEntityOwnerPacket(getEntityId(), getCaster().getEntityId()), this); 
/* 166 */         this.world.setEntityState(this, (byte)4);
/* 167 */         this.sentEvent = true;
/*     */       } 
/*     */       
/* 170 */       if (--this.lifeTicks < 0)
/*     */       {
/* 172 */         remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void damage(LivingEntity target) {
/* 180 */     LivingEntity caster = getCaster();
/* 181 */     if (target.isAlive() && !target.isInvulnerable() && caster != null && target != caster) {
/* 182 */       target.attackEntityFrom(ModDamageSource.causeAbilityDamage(caster, this.parentAbility, "player").setDamageBypassesArmor(), this.damage);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void handleStatusUpdate(byte id) {
/* 189 */     super.handleStatusUpdate(id);
/* 190 */     if (id == 4) {
/*     */       
/* 192 */       this.clientSideAttackStarted = true;
/* 193 */       if (!isSilent())
/*     */       {
/* 195 */         this.world.playSound(getPosX(), getPosY(), getPosZ(), ModSounds.SNAP_SFX, getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.2F + 0.85F, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getAnimationProgress(float partialTicks) {
/* 204 */     if (!this.clientSideAttackStarted) {
/* 205 */       return 0.0F;
/*     */     }
/*     */     
/* 208 */     int i = this.lifeTicks - 2;
/* 209 */     return (i <= 0) ? 1.0F : (1.0F - (i - partialTicks) / 20.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 216 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaHandsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */