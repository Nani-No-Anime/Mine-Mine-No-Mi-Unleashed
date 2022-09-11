package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HakiDataProvider implements ICapabilitySerializable<CompoundNBT> {
  private IHakiData instance = (IHakiData)HakiDataCapability.INSTANCE.getDefaultInstance();


  
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return HakiDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
  }


  
  public CompoundNBT serializeNBT() {
    return (CompoundNBT)HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, this.instance, null);
  }


  
  public void deserializeNBT(CompoundNBT nbt) {
    HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
  }
}


