package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class DevilFruitCapability
{
  @CapabilityInject(IDevilFruit.class)
  public static final Capability<IDevilFruit> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IDevilFruit.class, new Capability.IStorage<IDevilFruit>()
        {
          
          public INBT writeNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();
            
            props.putString("devilFruit", instance.getDevilFruit());
            props.putBoolean("isLogia", instance.isLogia());
            props.putBoolean("hasYamiPower", instance.hasYamiPower());
            props.putString("zoanPoint", instance.getZoanPoint());
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;
            
            instance.setDevilFruit(props.getString("devilFruit"));
            instance.setLogia(props.getBoolean("isLogia"));
            instance.setYamiPower(props.getBoolean("hasYamiPower"));
            instance.setZoanPoint(props.getString("zoanPoint"));
          }
        },DevilFruitBase::new);
  }


  
  public static IDevilFruit get(LivingEntity entity) {
    return (IDevilFruit)entity.getCapability(INSTANCE, null).orElse(new DevilFruitBase());
  }
}


