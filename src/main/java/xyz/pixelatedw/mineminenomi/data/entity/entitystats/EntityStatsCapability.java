package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EntityStatsCapability
{
  @CapabilityInject(IEntityStats.class)
  public static final Capability<IEntityStats> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
        {

          
          public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();
            
            props.putInt("doriki", instance.getDoriki());
            props.putInt("cola", instance.getCola());
            props.putInt("maxCola", instance.getMaxCola());
            props.putInt("ultraCola", instance.getUltraCola());
            props.putInt("loyalty", instance.getLoyalty());
            props.putDouble("damageMultiplier", instance.getDamageMultiplier());
            props.putLong("bounty", instance.getBounty());
            props.putLong("belly", instance.getBelly());
            props.putLong("extol", instance.getExtol());
            props.putString("faction", instance.getFaction());
            props.putString("race", instance.getRace());
            props.putString("subRace", instance.getSubRace());
            props.putString("fightingStyle", instance.getFightingStyle());
            props.putBoolean("hasShadow", instance.hasShadow());
            props.putBoolean("hasHeart", instance.hasHeart());
            props.putBoolean("hasStrawDoll", instance.hasStrawDoll());
            props.putBoolean("isInCombatMode", instance.isInCombatMode());
            props.putBoolean("isRogue", instance.isRogue());
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;
            
            instance.setDoriki(props.getInt("doriki"));
            instance.setCola(props.getInt("cola"));
            instance.setMaxCola(props.getInt("maxCola"));
            instance.setUltraCola(props.getInt("ultraCola"));
            instance.setLoyalty(props.getInt("loyalty"));
            instance.setDamageMultiplier(props.getDouble("damageMultiplier"));
            instance.setBelly(props.getLong("belly"));
            instance.setBounty(props.getLong("bounty"));
            instance.setExtol(props.getLong("extol"));
            instance.setFaction(props.getString("faction"));
            instance.setRace(props.getString("race"));
            instance.setSubRace(props.getString("subRace"));
            instance.setFightingStyle(props.getString("fightingStyle"));
            instance.setShadow(props.getBoolean("hasShadow"));
            instance.setHeart(props.getBoolean("hasHeart"));
            instance.setStrawDoll(props.getBoolean("hasStrawDoll"));
            instance.setCombatMode(props.getBoolean("isInCombatMode"));
            instance.setRogue(props.getBoolean("isRogue"));
          }
        },EntityStatsBase::new);
  }


  
  public static IEntityStats get(LivingEntity entity) {
    return (IEntityStats)entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
  }
}


