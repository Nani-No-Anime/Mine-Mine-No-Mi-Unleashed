/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ public class DenDenMushiEntity extends AnimalEntity implements IDynamicRenderer {
/*  31 */   private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(DenDenMushiEntity.class, DataSerializers.VARINT);
/*  32 */   private String[] textures = new String[] { "den_den_mushi1", "den_den_mushi2", "den_den_mushi3" };
/*     */ 
/*     */   
/*     */   public DenDenMushiEntity(World worldIn) {
/*  36 */     super(ModEntities.DEN_DEN_MUSHI, worldIn);
/*     */     
/*  38 */     this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 0.75D));
/*  39 */     this.goalSelector.addGoal(6, (Goal)new WaterAvoidingRandomWalkingGoal(this, 1.0D));
/*  40 */     this.goalSelector.addGoal(7, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 6.0F));
/*  41 */     this.goalSelector.addGoal(8, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  47 */     super.registerData();
/*  48 */     getDataManager().register(TEXTURE_ID, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  54 */     super.registerAttributes();
/*  55 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
/*  56 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.12D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  63 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  65 */     if (this.textures != null && this.textures.length > 0) {
/*  66 */       setTexture(this.rand.nextInt(this.textures.length));
/*     */     }
/*  68 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(PlayerEntity player, Hand hand) {
/*  74 */     ItemStack stack = player.getHeldItem(hand);
/*  75 */     if (stack.getItem() == Items.IRON_INGOT) {
/*     */       
/*  77 */       ItemStack denStack = new ItemStack((IItemProvider)ModBlocks.DEN_DEN_MUSHI);
/*  78 */       denStack.getOrCreateTag().putInt("type", getTextureId());
/*  79 */       player.inventory.addItemStackToInventory(denStack);
/*  80 */       stack.shrink(1);
/*  81 */       remove();
/*  82 */       return true;
/*     */     } 
/*     */     
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT nbt) {
/*  91 */     super.writeAdditional(nbt);
/*  92 */     nbt.putInt("Texture", getTextureId());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT nbt) {
/*  98 */     super.readAdditional(nbt);
/*  99 */     setTexture(nbt.getInt("Texture"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AgeableEntity createChild(AgeableEntity ageable) {
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTextureId() {
/* 110 */     return ((Integer)getDataManager().get(TEXTURE_ID)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setTexture(int texture) {
/* 115 */     getDataManager().set(TEXTURE_ID, Integer.valueOf(texture));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMobTexture() {
/* 121 */     return this.textures[getTextureId()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultTexture() {
/* 127 */     return this.textures[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\DenDenMushiEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */