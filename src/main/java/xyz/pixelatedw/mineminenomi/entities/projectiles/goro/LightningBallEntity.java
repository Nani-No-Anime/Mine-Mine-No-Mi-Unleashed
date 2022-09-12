package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.renderers.entities.LightningBallEntityRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LightningBallEntity
  extends Entity
{
  public List<LightningBallEntityRenderer.LightningRendererPropieties> entities = new ArrayList<>();
  protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
  protected static final DataParameter<Integer> ALIVE_TICKS = EntityDataManager.createKey(LightningEntity.class, DataSerializers.VARINT);
  protected static final DataParameter<Float> LIGHTNING_LENGTH = EntityDataManager.createKey(LightningEntity.class, DataSerializers.FLOAT);
  
  public LightningBallEntity(World worldIn) {
    super(GoroProjectiles.LIGHTNING_BALL, worldIn);
  }


  
  public LightningBallEntity(EntityType<LightningBallEntity> entityType, World world) {
    super(entityType, world);
    this.ignoreFrustumCheck = true;
  }

  
  public LightningBallEntity(Entity entity, double posX, double posY, double PosZ, float rotationYaw, float rotationPitch) {
    this(GoroProjectiles.LIGHTNING_BALL, entity.world);
    
    setLocationAndAngles(posX, posY, PosZ, rotationYaw, rotationPitch);
  }

  
  public long getSeed() {
    return (new Random()).nextLong();
  }

  
  public void tick() {
    super.tick();
    
    if (this.ticksExisted >= getAliveTicks()) {
      remove();
    }
  }

  
  @OnlyIn(Dist.CLIENT)
  public boolean isInRangeToRenderDist(double distance) {
    double d0 = 64.0D * getRenderDistanceWeight();
    return (distance < d0 * d0);
  }

  
  protected void registerData() {
    this.dataManager.register(SIZE, Float.valueOf(0.2F));
    this.dataManager.register(ALIVE_TICKS, Integer.valueOf(200));
    this.dataManager.register(LIGHTNING_LENGTH, Float.valueOf(1.0F));
  }



  
  protected void readAdditional(CompoundNBT compound) {}



  
  protected void writeAdditional(CompoundNBT compound) {}


  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
  
  public void setSize(float size) {
    this.dataManager.set(SIZE, Float.valueOf(size));
  }
  
  public void setLightningLength(float size) {
    this.dataManager.set(LIGHTNING_LENGTH, Float.valueOf(size));
  }
  
  public float getSize() {
    return ((Float)this.dataManager.get(SIZE)).floatValue();
  }
  
  public float getLightningLength() {
    return ((Float)this.dataManager.get(LIGHTNING_LENGTH)).floatValue();
  }
  
  public int getAliveTicks() {
    return ((Integer)this.dataManager.get(ALIVE_TICKS)).intValue();
  }
  
  public void setAliveTicks(int ticks) {
    this.dataManager.set(ALIVE_TICKS, Integer.valueOf(ticks));
  }
}


