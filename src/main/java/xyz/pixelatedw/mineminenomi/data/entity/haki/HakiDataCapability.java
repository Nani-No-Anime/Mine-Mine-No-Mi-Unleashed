package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class HakiDataCapability
{
  @CapabilityInject(IHakiData.class)
  public static final Capability<IHakiData> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IHakiData.class, new Capability.IStorage<IHakiData>()
        {
          
          public INBT writeNBT(Capability<IHakiData> capability, IHakiData instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();
            
            props.putFloat("kenHakiExp", instance.getKenbunshokuHakiExp());
            props.putFloat("busoHardeningHakiExp", instance.getBusoshokuHardeningHakiExp());
            props.putFloat("busoImbuingHakiExp", instance.getBusoshokuImbuingHakiExp());
            props.putInt("hakiOveruse", instance.getHakiOveruse());
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IHakiData> capability, IHakiData instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;
            
            instance.setKenbunshokuHakiExp(props.getFloat("kenHakiExp"));
            instance.setBusoshokuHardeningHakiExp(props.getFloat("busoHardeningHakiExp"));
            instance.setBusoshokuImbuingHakiExp(props.getFloat("busoImbuingHakiExp"));
            instance.setHakiOveruse(props.getInt("hakiOveruse"));
          }
        },HakiDataBase::new);
  }



  
  public static IHakiData get(LivingEntity entity) {
    return (IHakiData)entity.getCapability(INSTANCE, null).orElse(new HakiDataBase());
  }
}


