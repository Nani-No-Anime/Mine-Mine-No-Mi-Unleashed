package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateEntityOwnerPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.UUID;

public class HanaHandsEntity
  extends Entity {
  private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(HanaHandsEntity.class, DataSerializers.VARINT);
  
  private int warmupDelayTicks = 30;
  private int lifeTicks = 22;
  
  private float damage;
  private boolean sentEvent;
  private boolean clientSideAttackStarted;
  private LivingEntity caster;
  private LivingEntity target;
  private UUID casterUuid;
  private Ability parentAbility;
  
  public HanaHandsEntity(EntityType<? extends HanaHandsEntity> type, World world) {
    super(type, world);
  }

  
  public HanaHandsEntity(World world) {
    this(HanaProjectiles.HANDS, world);
  }

  
  public HanaHandsEntity(World world, Ability ability) {
    this(world);
    this.parentAbility = ability;
  }

  
  public void setCaster(LivingEntity caster) {
    this.caster = caster;
    this.casterUuid = caster.getUniqueID();
  }

  
  public LivingEntity getCaster() {
    if (this.caster == null && this.casterUuid != null && this.world instanceof ServerWorld) {
      
      Entity entity = ((ServerWorld)this.world).getEntityByUuid(this.casterUuid);
      if (entity instanceof LivingEntity)
      {
        this.caster = (LivingEntity)entity;
      }
    } 
    
    return this.caster;
  }

  
  public void setTarget(LivingEntity target) {
    this.target = target;
    setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
  }

  
  public void setWarmup(int warmup) {
    this.warmupDelayTicks = warmup;
  }

  
  public void setDamage(float damage) {
    this.damage = damage;
  }

  
  public void setClutch() {
    this.dataManager.set(TYPE, Integer.valueOf(0));
  }

  
  public void setSlap() {
    this.dataManager.set(TYPE, Integer.valueOf(1));
  }

  
  public int getHandsType() {
    return ((Integer)this.dataManager.get(TYPE)).intValue();
  }


  
  protected void registerData() {
    this.dataManager.register(TYPE, Integer.valueOf(0));
  }


  
  protected void readAdditional(CompoundNBT compound) {
    this.warmupDelayTicks = compound.getInt("Warmup");
    this.dataManager.set(TYPE, Integer.valueOf(compound.getInt("Type")));
    if (compound.hasUniqueId("OwnerUUID")) {
      this.casterUuid = compound.getUniqueId("OwnerUUID");
    }
  }

  
  protected void writeAdditional(CompoundNBT compound) {
    compound.putInt("Warmup", this.warmupDelayTicks);
    compound.putInt("Type", ((Integer)this.dataManager.get(TYPE)).intValue());
    if (this.casterUuid != null) {
      compound.putUniqueId("OwnerUUID", this.casterUuid);
    }
  }

  
  public void tick() {
    super.tick();
    if (this.world.isRemote) {
      
      if (this.clientSideAttackStarted) {
        
        this.lifeTicks--;
        if (this.lifeTicks == 14)
        {
          for (int i = 0; i < 12; i++)
          {
            double d0 = getPosX() + (this.rand.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
            double d1 = getPosY() + 0.05D + this.rand.nextDouble();
            double d2 = getPosZ() + (this.rand.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
            double d3 = (this.rand.nextDouble() * 2.0D - 1.0D) * 0.3D;
            double d4 = 0.3D + this.rand.nextDouble() * 0.3D;
            double d5 = (this.rand.nextDouble() * 2.0D - 1.0D) * 0.3D;
            this.world.addParticle(ParticleTypes.CRIT, d0, d1 + 1.0D, d2, d3, d4, d5);
          }
        
        }
      } 
    } else if (--this.warmupDelayTicks < 0) {
      
      if (this.warmupDelayTicks == -8)
      {
        if (this.target != null) {
          damage(this.target);
        }
      }
      if (!this.sentEvent) {
        
        if (getCaster() != null)
          WyNetwork.sendToAllAround(new SUpdateEntityOwnerPacket(getEntityId(), getCaster().getEntityId()), this); 
        this.world.setEntityState(this, (byte)4);
        this.sentEvent = true;
      } 
      
      if (--this.lifeTicks < 0)
      {
        remove();
      }
    } 
  }


  
  private void damage(LivingEntity target) {
    LivingEntity caster = getCaster();
    if (target.isAlive() && !target.isInvulnerable() && caster != null && target != caster) {
      target.attackEntityFrom(ModDamageSource.causeAbilityDamage(caster, this.parentAbility, "player").setDamageBypassesArmor(), this.damage);
    }
  }

  
  @OnlyIn(Dist.CLIENT)
  public void handleStatusUpdate(byte id) {
    super.handleStatusUpdate(id);
    if (id == 4) {
      
      this.clientSideAttackStarted = true;
      if (!isSilent())
      {
        this.world.playSound(getPosX(), getPosY(), getPosZ(), ModSounds.SNAP_SFX, getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.2F + 0.85F, false);
      }
    } 
  }


  
  @OnlyIn(Dist.CLIENT)
  public float getAnimationProgress(float partialTicks) {
    if (!this.clientSideAttackStarted) {
      return 0.0F;
    }
    
    int i = this.lifeTicks - 2;
    return (i <= 0) ? 1.0F : (1.0F - (i - partialTicks) / 20.0F);
  }



  
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}


