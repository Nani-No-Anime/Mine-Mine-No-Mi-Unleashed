package xyz.pixelatedw.mineminenomi.wypi.data.ability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AbilityDataProvider
  implements ICapabilitySerializable<CompoundNBT> {
  private IAbilityData instance = (IAbilityData)AbilityDataCapability.INSTANCE.getDefaultInstance();


  
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return AbilityDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
  }


  
  public CompoundNBT serializeNBT() {
    return (CompoundNBT)AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, this.instance, null);
  }


  
  public void deserializeNBT(CompoundNBT nbt) {
    AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
  }
}


