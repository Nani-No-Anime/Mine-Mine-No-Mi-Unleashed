/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
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
/*     */ public class BottomHalfBodyEntity
/*     */   extends OPEntity {
/*  26 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BottomHalfBodyEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  27 */   private Ability parentAbility = null;
/*     */ 
/*     */   
/*     */   public BottomHalfBodyEntity(World world) {
/*  31 */     super(ModEntities.BOTTOM_HALF_BODY, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentAbility(Ability ability) {
/*  36 */     this.parentAbility = ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  41 */     this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
/*  42 */     EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getOwnerUUID() {
/*  47 */     return ((Optional<UUID>)getDataManager().get(OWNER)).get();
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/*  52 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  58 */     PlayerEntity owner = getOwner();
/*  59 */     if (owner == null) {
/*  60 */       return false;
/*     */     }
/*  62 */     owner.attackEntityFrom(DamageSource.MAGIC, amount);
/*  63 */     setHealth(owner.getHealth());
/*     */     
/*  65 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     if (!this.world.isRemote) {
/*     */       
/*  73 */       PlayerEntity owner = getOwner();
/*  74 */       boolean hasParentAbilityActive = false;
/*     */       
/*  76 */       if (owner != null) {
/*     */         
/*  78 */         IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
/*  79 */         Ability parentAbilityInstance = abilityProps.getEquippedAbility(this.parentAbility);
/*  80 */         if (parentAbilityInstance != null && parentAbilityInstance.isContinuous())
/*     */         {
/*  82 */           hasParentAbilityActive = true;
/*     */         }
/*     */       } 
/*     */       
/*  86 */       if (owner == null || !owner.isAlive() || this.parentAbility == null || !hasParentAbilityActive) {
/*     */         
/*  88 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/*  92 */       if (getDistance((Entity)getOwner()) > 5.0F) {
/*  93 */         getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
/*     */       }
/*     */     } 
/*  96 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/* 102 */     super.registerData();
/* 103 */     this.dataManager.register(OWNER, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/* 109 */     super.registerAttributes();
/* 110 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/* 111 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
/* 112 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 118 */     super.writeAdditional(compound);
/* 119 */     if (this.dataManager.get(OWNER) != null)
/*     */     {
/* 121 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 128 */     super.readAdditional(compound);
/* 129 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 135 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\BottomHalfBodyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */