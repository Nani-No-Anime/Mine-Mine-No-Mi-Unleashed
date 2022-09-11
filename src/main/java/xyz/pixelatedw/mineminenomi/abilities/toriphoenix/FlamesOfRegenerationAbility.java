package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class FlamesOfRegenerationAbility extends PassiveAbility implements IExtraUpdateData, IOnDamageAbility {
  public static final FlamesOfRegenerationAbility INSTANCE = new FlamesOfRegenerationAbility();
  
  private static final int MAX_ENERGY = 100;
  private static final LogiaParticleEffect PARTICLES = new LogiaParticleEffect(ModParticleTypes.BLUE_FLAME);
  
  private double energy = 100.0D;

  
  public FlamesOfRegenerationAbility() {
    super("Flames of Regeneration", AbilityHelper.getDevilFruitCategory());
    setDescription("Protects the user and heals them back up when damage is taken, has an initial reserve of 100 §2Energy§r which increases with time and decreses with each heal");
    hideInGUI(false);
    
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    if (player.world.isRemote) {
      return;
    }
    if (player.isSleeping() && player.ticksExisted % 10 == 0) {
      addEnergy((LivingEntity)player, 10.0D);
    }
    if (player.ticksExisted % 40 == 0)
    {
      if (this.energy - 4.0D >= 0.0D && player.getHealth() < player.getMaxHealth()) {
        
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), Math.random(), 0.0D, Math.random());
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() - Math.random(), player.getPosZ(), Math.random(), 0.0D, Math.random());
        PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + Math.random(), player.getPosZ(), Math.random(), 0.0D, Math.random());
        
        player.heal(4.0F);
        addEnergy((LivingEntity)player, -5.0D);
      } 
    }
    
    addEnergy((LivingEntity)player, 0.05D);
  }


  
  public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
    if (this.energy - amount >= 0.0D) {
      
      PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
      PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() - Math.random(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
      PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() + Math.random(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
      
      entity.heal((float)amount);
      addEnergy(entity, -amount);
      
      return false;
    } 
    
    return true;
  }

  
  public double getEnergy() {
    return this.energy;
  }

  
  public void addEnergy(LivingEntity entity, double energy) {
    this.energy = MathHelper.clamp(this.energy + energy, 0.0D, 100.0D);
    if (entity instanceof PlayerEntity) {
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket((PlayerEntity)entity, (Ability)this), entity);
    }
  }

  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putDouble("energy", this.energy);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.energy = nbt.getDouble("energy");
  }
}


