/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DoppelmanEntity extends OPEntity {
/*  46 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(DoppelmanEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  47 */   private static final DataParameter<Integer> SHADOWS = EntityDataManager.createKey(DoppelmanEntity.class, DataSerializers.VARINT);
/*     */   public boolean isAggressive = true;
/*  49 */   public List<LivingEntity> forcedTargets = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public DoppelmanEntity(World world) {
/*  53 */     super(ModEntities.DOPPELMAN, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  59 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  60 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  61 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  62 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  63 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, AbstractMarineEntity.class, 8.0F));
/*  64 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, AbstractPirateEntity.class, 8.0F));
/*  65 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, AbstractBanditEntity.class, 8.0F));
/*  66 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  68 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  74 */     super.registerAttributes();
/*  75 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/*  76 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  77 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/*  78 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
/*  79 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
/*  80 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  86 */     super.registerData();
/*  87 */     this.dataManager.register(OWNER, Optional.empty());
/*  88 */     this.dataManager.register(SHADOWS, Integer.valueOf(0));
/*  89 */     EntityStatsCapability.get((LivingEntity)this).setHeart(false);
/*  90 */     EntityStatsCapability.get((LivingEntity)this).setShadow(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damageSource, float damageValue) {
/*  96 */     if (damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == getOwner()) {
/*  97 */       return false;
/*     */     }
/*  99 */     return super.attackEntityFrom(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity target) {
/* 105 */     float damage = (float)getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() + (getShadows() * 4);
/* 106 */     int knockback = 0;
/*     */     
/* 108 */     if (target instanceof LivingEntity) {
/*     */       
/* 110 */       damage += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((LivingEntity)target).getCreatureAttribute());
/* 111 */       knockback = (int)(knockback + EnchantmentHelper.getKnockbackModifier((LivingEntity)this));
/*     */     } 
/*     */     
/* 114 */     boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this), damage);
/*     */     
/* 116 */     if (flag)
/*     */     {
/* 118 */       if (knockback > 0) {
/*     */         
/* 120 */         target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F));
/* 121 */         setMotion(getMotion().mul(0.6D, 1.0D, 0.6D));
/*     */       } 
/*     */     }
/*     */     
/* 125 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 131 */     if (!this.world.isRemote) {
/*     */       
/* 133 */       if (getOwner() == null) {
/*     */         
/* 135 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/* 139 */       if (getDistance((Entity)getOwner()) > 10.0F) {
/* 140 */         getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
/*     */       }
/* 142 */       if (getDistance((Entity)getOwner()) > 128.0F) {
/* 143 */         setPositionAndUpdate(getOwner().getPosX(), getOwner().getPosY(), getOwner().getPosZ());
/*     */       }
/* 145 */       IEntityStats ownerProps = EntityStatsCapability.get((LivingEntity)getOwner());
/* 146 */       IDevilFruit ownerDFProps = DevilFruitCapability.get((LivingEntity)getOwner());
/* 147 */       List<LivingEntity> doppelmanAttackList = this.isAggressive ? WyHelper.getEntitiesNear(getPosition(), this.world, 10.0D, new Class[] { PlayerEntity.class, AbstractMarineEntity.class, AbstractPirateEntity.class, MonsterEntity.class }) : (!this.forcedTargets.isEmpty() ? this.forcedTargets : new ArrayList<>());
/* 148 */       LivingEntity target = null;
/*     */       
/* 150 */       if (!ownerDFProps.hasDevilFruit(ModAbilities.KAGE_KAGE_NO_MI)) {
/* 151 */         remove();
/*     */       }
/* 153 */       if (!doppelmanAttackList.isEmpty() && (getAttackTarget() == null || !getAttackTarget().isAlive())) {
/*     */         
/* 155 */         if (doppelmanAttackList.contains(getOwner())) {
/* 156 */           doppelmanAttackList.remove(getOwner());
/*     */         }
/* 158 */         if (ownerProps.isMarine()) {
/* 159 */           doppelmanAttackList = (List<LivingEntity>)doppelmanAttackList.stream().filter(x -> !(x instanceof AbstractMarineEntity)).collect(Collectors.toList());
/*     */         }
/* 161 */         target = doppelmanAttackList.stream().findFirst().orElse(null);
/*     */       } 
/*     */       
/* 164 */       if (target != null) {
/* 165 */         setAttackTarget(target);
/*     */       }
/* 167 */       if (!this.forcedTargets.isEmpty()) {
/*     */         
/* 169 */         Iterator<LivingEntity> it = this.forcedTargets.iterator();
/* 170 */         while (it.hasNext()) {
/*     */           
/* 172 */           LivingEntity forcedTarget = it.next();
/* 173 */           if (forcedTarget == null || !forcedTarget.isAlive()) {
/* 174 */             it.remove();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 179 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(PlayerEntity player, Hand hand) {
/* 185 */     if (player == getOwner()) {
/*     */       
/* 187 */       ItemStack itemStack = player.getHeldItem(hand);
/*     */       
/* 189 */       if (itemStack != null && itemStack.getItem() == ModItems.SHADOW && itemStack.getCount() >= 10 && getShadows() < 6) {
/*     */         
/* 191 */         itemStack.setCount(itemStack.getCount() - 10);
/* 192 */         addShadow();
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 202 */     super.writeAdditional(compound);
/* 203 */     if (this.dataManager.get(OWNER) != null)
/* 204 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
/* 205 */     compound.putInt("ShadowsAte", ((Integer)this.dataManager.get(SHADOWS)).intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 211 */     super.readAdditional(compound);
/* 212 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/* 213 */     this.dataManager.set(SHADOWS, Integer.valueOf(compound.getInt("ShadowsAte")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 218 */     this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
/* 219 */     EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 224 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addShadow() {
/* 229 */     this.dataManager.set(SHADOWS, Integer.valueOf(((Integer)this.dataManager.get(SHADOWS)).intValue() + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShadow(int value) {
/* 234 */     this.dataManager.set(SHADOWS, Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShadows() {
/* 239 */     return ((Integer)this.dataManager.get(SHADOWS)).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\DoppelmanEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */