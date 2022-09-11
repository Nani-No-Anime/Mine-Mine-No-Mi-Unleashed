package xyz.pixelatedw.mineminenomi.abilities.karu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class KarmaAbility extends PassiveAbility implements IOnDamageAbility, IExtraUpdateData {
  public static final KarmaAbility INSTANCE = new KarmaAbility();
  
  public static final float MAX_KARMA = 100.0F;
  
  private float karma = 0.0F;
  private float prevKarma = 0.0F;

  
  public KarmaAbility() {
    super("Karma", AbilityHelper.getDevilFruitCategory());
  }


  
  public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
    if (source.isMagicDamage()) {
      return true;
    }
    if (this.karma >= 100.0F) {
      return true;
    }
    if (source instanceof ModDamageSource) {
      
      boolean isInternal = ((ModDamageSource)source).isInternalDamage();
      if (!isInternal)
      {
        addKarma(entity, (float)amount);
      }
    }
    else {
      
      addKarma(entity, (float)amount);
    } 
    
    return true;
  }

  
  public void addKarma(LivingEntity entity, float amount) {
    this.prevKarma = this.karma;
    this.karma = MathHelper.clamp(this.karma + amount, 0.0F, 100.0F);
    if (entity instanceof PlayerEntity) {
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket((PlayerEntity)entity, (Ability)this), entity);
    }
  }
  
  public float getKarma() {
    return this.karma;
  }

  
  public void setPrevKarma(float prevKarma) {
    this.prevKarma = prevKarma;
  }

  
  public float getPrevKarma() {
    return this.prevKarma;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putFloat("karma", this.karma);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.karma = nbt.getFloat("karma");
  }
}


