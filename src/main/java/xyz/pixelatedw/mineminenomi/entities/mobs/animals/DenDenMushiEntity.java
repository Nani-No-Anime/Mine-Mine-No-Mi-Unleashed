package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

import javax.annotation.Nullable;

public class DenDenMushiEntity extends AnimalEntity implements IDynamicRenderer {
  private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(DenDenMushiEntity.class, DataSerializers.VARINT);
  private String[] textures = new String[] { "den_den_mushi1", "den_den_mushi2", "den_den_mushi3" };

  
  public DenDenMushiEntity(World worldIn) {
    super(ModEntities.DEN_DEN_MUSHI, worldIn);
    
    this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 0.75D));
    this.goalSelector.addGoal(6, (Goal)new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    this.goalSelector.addGoal(7, (Goal)new LookAtGoal(this, PlayerEntity.class, 6.0F));
    this.goalSelector.addGoal(8, (Goal)new LookRandomlyGoal(this));
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(TEXTURE_ID, Integer.valueOf(0));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.12D);
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (this.textures != null && this.textures.length > 0) {
      setTexture(this.rand.nextInt(this.textures.length));
    }
    return spawnData;
  }


  
  public boolean processInteract(PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (stack.getItem() == Items.IRON_INGOT) {
      
      ItemStack denStack = new ItemStack((IItemProvider)ModBlocks.DEN_DEN_MUSHI);
      denStack.getOrCreateTag().putInt("type", getTextureId());
      player.inventory.addItemStackToInventory(denStack);
      stack.shrink(1);
      remove();
      return true;
    } 
    
    return false;
  }


  
  public void writeAdditional(CompoundNBT nbt) {
    super.writeAdditional(nbt);
    nbt.putInt("Texture", getTextureId());
  }


  
  public void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    setTexture(nbt.getInt("Texture"));
  }


  
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }

  
  public int getTextureId() {
    return ((Integer)getDataManager().get(TEXTURE_ID)).intValue();
  }

  
  protected void setTexture(int texture) {
    getDataManager().set(TEXTURE_ID, Integer.valueOf(texture));
  }


  
  public String getMobTexture() {
    return this.textures[getTextureId()];
  }


  
  public String getDefaultTexture() {
    return this.textures[0];
  }
}


