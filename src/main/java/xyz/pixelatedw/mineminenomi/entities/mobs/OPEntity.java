/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IExtensibleEnum;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public abstract class OPEntity
/*     */   extends CreatureEntity implements IDynamicRenderer {
/*     */   protected String[] textures;
/*     */   protected boolean needsEntityDataUpdate;
/*  39 */   private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(OPEntity.class, DataSerializers.STRING);
/*  40 */   private static final DataParameter<Boolean> HAS_BUSOSHOKU_HAKI_ACTIVE = EntityDataManager.createKey(OPEntity.class, DataSerializers.BOOLEAN);
/*  41 */   private static final DataParameter<Boolean> HAS_FULLBODY_HAKI_ACTIVE = EntityDataManager.createKey(OPEntity.class, DataSerializers.BOOLEAN);
/*  42 */   private static final DataParameter<Integer> ANIMATION = EntityDataManager.createKey(OPEntity.class, DataSerializers.VARINT);
/*  43 */   private static final AttributeModifier GENERIC_BUSO_ATTACK_MULTIPLIER = (new AttributeModifier(UUID.fromString("41120ad1-c457-44d5-ac4c-896e83e1333f"), "Generic Buso Haki Strength Multiplier", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
/*  44 */   private static final AttributeModifier FULLBODY_BUSO_ATTACK_MULTIPLIER = (new AttributeModifier(UUID.fromString("5d4e0e3d-2580-47c0-bbcf-19c4ee04eb48"), "Fullbody Buso Haki Strength Multiplier", 0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
/*  45 */   private static final AttributeModifier FULLBODY_BUSO_DEFENSE_MULTIPLIER = (new AttributeModifier(UUID.fromString("49252c18-dcd6-4b3f-9fbc-df1962f999ab"), "Fullbody Buso Haki Defense Multiplier", 0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
/*     */   private int doriki;
/*  47 */   protected int threat = 2;
/*     */   private int belly;
/*     */   private Goal currentGoal;
/*     */   private Goal previousGoal;
/*     */   private boolean isFishman;
/*     */   
/*     */   public OPEntity(EntityType type, World world) {
/*  54 */     this(type, world, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public OPEntity(EntityType type, World world, String[] textures) {
/*  59 */     super(type, world);
/*  60 */     this.experienceValue = this.threat;
/*  61 */     this.textures = textures;
/*  62 */     chooseTexture();
/*  63 */     this.isFishman = EntityStatsCapability.get((LivingEntity)this).isFishman();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  69 */     super.registerAttributes();
/*  70 */     getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  76 */     super.registerData();
/*  77 */     getDataManager().register(TEXTURE, "");
/*  78 */     getDataManager().register(ANIMATION, Integer.valueOf(0));
/*  79 */     getDataManager().register(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(false));
/*  80 */     getDataManager().register(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT nbt) {
/*  86 */     super.writeAdditional(nbt);
/*  87 */     nbt.putInt("doriki", this.doriki);
/*  88 */     nbt.putInt("belly", this.belly);
/*  89 */     nbt.putInt("threat", this.threat);
/*  90 */     nbt.putInt("animation", getAnimation());
/*     */     
/*  92 */     nbt.putString("texture", getMobTexture());
/*     */     
/*  94 */     nbt.putBoolean("hasBusoHaki", hasBusoHaki());
/*  95 */     nbt.putBoolean("hasFullbodyHaki", hasFullbodyHaki());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT nbt) {
/* 101 */     super.readAdditional(nbt);
/* 102 */     this.doriki = nbt.getInt("doriki");
/* 103 */     this.belly = nbt.getInt("belly");
/* 104 */     this.threat = nbt.getInt("threat");
/* 105 */     setAnimation(nbt.getInt("animation"));
/*     */     
/* 107 */     setTexture(nbt.getString("texture"));
/*     */     
/* 109 */     setBusoHaki(nbt.getBoolean("hasBusoHaki"));
/* 110 */     setFullbodyHaki(nbt.getBoolean("hasFullbodyHaki"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultTexture() {
/* 116 */     if (this.textures != null)
/* 117 */       return this.textures[0]; 
/* 118 */     return "null";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 125 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 127 */     setLeftHanded(false);
/*     */     
/* 129 */     if (WyHelper.isNullOrEmpty(getMobTexture())) {
/* 130 */       setTexture(getDefaultTexture());
/*     */     }
/* 132 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
/* 141 */     Block aboveBlock = world.getBlockState(pos.up()).getBlock();
/* 142 */     Block belowBlock = world.getBlockState(pos.down()).getBlock();
/* 143 */     boolean flag1 = !world.getBlockState(pos).isAir((IBlockReader)world, pos);
/* 144 */     boolean flag2 = (pos.getY() < 150);
/* 145 */     boolean isOutside = (aboveBlock.getBlock() == Blocks.AIR);
/* 146 */     boolean flag3 = (belowBlock.getBlock() == Blocks.GRASS_BLOCK || belowBlock.getBlock() == Blocks.SAND || belowBlock.getBlock() == Blocks.STONE);
/* 147 */     float weight = (64 / pos.getY() * 10);
/* 148 */     return (flag1 && flag2 && flag3 && isOutside) ? weight : 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/* 154 */     return (getBlockPathWeight(new BlockPos((Entity)this), (IWorldReader)world) > 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 160 */     if (hasCustomName() && !CommonConfig.INSTANCE.getDespawnWithNametag()) {
/* 161 */       return false;
/*     */     }
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shouldDrown() {
/* 168 */     return !this.isFishman;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMobTexture() {
/* 174 */     return (String)getDataManager().get(TEXTURE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTexture(String id) {
/* 179 */     getDataManager().set(TEXTURE, id);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void chooseTexture() {
/* 184 */     if (this.textures != null && this.textures.length > 0) {
/*     */       
/* 186 */       int id = this.rand.nextInt(this.textures.length);
/* 187 */       setTexture(this.textures[id]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDoriki() {
/* 193 */     return this.doriki;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDoriki(double value) {
/* 198 */     this.doriki = (int)Math.floor(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBelly() {
/* 203 */     return this.belly;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBelly(double value) {
/* 208 */     this.belly = (int)Math.floor(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnimation() {
/* 213 */     return ((Integer)this.dataManager.get(ANIMATION)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnimation(int value) {
/* 218 */     this.dataManager.set(ANIMATION, Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBusoHaki() {
/* 223 */     return ((Boolean)this.dataManager.get(HAS_BUSOSHOKU_HAKI_ACTIVE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusoHaki(boolean value) {
/* 228 */     this.dataManager.set(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFullbodyHaki() {
/* 233 */     return ((Boolean)this.dataManager.get(HAS_FULLBODY_HAKI_ACTIVE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFullbodyHaki(boolean value) {
/* 238 */     this.dataManager.set(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public Goal getCurrentGoal() {
/* 243 */     return this.currentGoal;
/*     */   }
/*     */ 
/*     */   
/*     */   public Goal getPreviousGoal() {
/* 248 */     return this.previousGoal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentGoal(Goal goal) {
/* 253 */     this.currentGoal = goal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPreviousGoal(Goal goal) {
/* 258 */     this.previousGoal = goal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addThreat(int threat) {
/* 263 */     this.threat += threat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThreat() {
/* 268 */     return this.threat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThreat(int threat) {
/* 273 */     this.threat = threat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void queueEntityDataUpdate() {
/* 278 */     this.needsEntityDataUpdate = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 284 */     super.tick();
/*     */     
/* 286 */     if (!this.world.isRemote) {
/*     */       
/* 288 */       if (hasBusoHaki()) {
/*     */         
/* 290 */         if (!getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(GENERIC_BUSO_ATTACK_MULTIPLIER)) {
/* 291 */           getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(GENERIC_BUSO_ATTACK_MULTIPLIER);
/*     */         
/*     */         }
/*     */       }
/* 295 */       else if (getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(GENERIC_BUSO_ATTACK_MULTIPLIER)) {
/* 296 */         getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(GENERIC_BUSO_ATTACK_MULTIPLIER);
/*     */       } 
/*     */       
/* 299 */       if (hasFullbodyHaki()) {
/*     */         
/* 301 */         if (!getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER))
/* 302 */           getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER); 
/* 303 */         if (!getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER)) {
/* 304 */           getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER);
/*     */         }
/*     */       } else {
/*     */         
/* 308 */         if (getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).hasModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER))
/* 309 */           getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(FULLBODY_BUSO_ATTACK_MULTIPLIER); 
/* 310 */         if (getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER)) {
/* 311 */           getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(FULLBODY_BUSO_DEFENSE_MULTIPLIER);
/*     */         }
/*     */       } 
/* 314 */       if (getRidingEntity() instanceof net.minecraft.entity.item.BoatEntity && getAttackTarget() != null)
/*     */       {
/* 316 */         stopRiding();
/*     */       }
/*     */       
/* 319 */       if (this.needsEntityDataUpdate) {
/*     */         
/* 321 */         IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 322 */         WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(getEntityId(), props), (LivingEntity)this);
/* 323 */         this.needsEntityDataUpdate = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Animation
/*     */     implements IExtensibleEnum {
/* 330 */     NONE(0),
/* 331 */     FLINTLOCK_POINTING(1),
/* 332 */     CLEAVE_ATTACK(2),
/* 333 */     SHOCKWAVE(3),
/* 334 */     BLOCK(4);
/*     */     
/*     */     private int id;
/*     */ 
/*     */     
/*     */     Animation(int id) {
/* 340 */       this.id = id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getId() {
/* 345 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Animation create(String name, int id) {
/* 350 */       throw new IllegalStateException("Enum not extended");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\OPEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */