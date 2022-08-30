/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class PhysicalBodyEntity
/*     */   extends OPEntity
/*     */ {
/*  28 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(PhysicalBodyEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  29 */   private Ability parentAbility = null;
/*     */ 
/*     */   
/*     */   public PhysicalBodyEntity(World world) {
/*  33 */     super(ModEntities.PHYSICAL_BODY, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentAbility(Ability ability) {
/*  38 */     this.parentAbility = ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  43 */     this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
/*  44 */     EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
/*  45 */     EntityStatsCapability.get((LivingEntity)this).setRace(EntityStatsCapability.get(owner).getRace());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  51 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? ((Optional<UUID>)getDataManager().get(OWNER)).get() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PlayerEntity getOwner() {
/*  57 */     UUID uuid = getOwnerUUID();
/*  58 */     if (uuid != null)
/*  59 */       return this.world.getPlayerByUuid(uuid); 
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  66 */     PlayerEntity owner = getOwner();
/*  67 */     if (owner == null) {
/*  68 */       return false;
/*     */     }
/*  70 */     owner.attackEntityFrom(DamageSource.MAGIC, amount);
/*  71 */     setHealth(owner.getHealth());
/*     */     
/*  73 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  79 */     if (!this.world.isRemote) {
/*     */       
/*  81 */       PlayerEntity owner = getOwner();
/*  82 */       boolean hasParentAbilityActive = false;
/*     */       
/*  84 */       if (owner != null) {
/*     */         
/*  86 */         IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
/*  87 */         Ability parentAbilityInstance = abilityProps.getEquippedAbility(this.parentAbility);
/*  88 */         if (parentAbilityInstance != null && parentAbilityInstance.isContinuous())
/*     */         {
/*  90 */           hasParentAbilityActive = true;
/*     */         }
/*     */       } 
/*     */       
/*  94 */       if (owner == null || !owner.isAlive() || this.parentAbility == null || !hasParentAbilityActive) {
/*     */         
/*  96 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 101 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/* 107 */     super.registerData();
/* 108 */     this.dataManager.register(OWNER, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/* 114 */     super.registerAttributes();
/* 115 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
/* 116 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 122 */     super.writeAdditional(compound);
/* 123 */     if (this.dataManager.get(OWNER) != null)
/*     */     {
/* 125 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 132 */     super.readAdditional(compound);
/* 133 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 139 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\PhysicalBodyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */