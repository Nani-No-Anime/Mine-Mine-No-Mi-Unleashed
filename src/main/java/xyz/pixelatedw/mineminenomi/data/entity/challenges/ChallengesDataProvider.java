package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ChallengesDataProvider implements ICapabilitySerializable<CompoundNBT> {
  private IChallengesData instance = (IChallengesData)ChallengesDataCapability.INSTANCE.getDefaultInstance();


  
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return ChallengesDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
  }


  
  public CompoundNBT serializeNBT() {
    return (CompoundNBT)ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, this.instance, null);
  }


  
  public void deserializeNBT(CompoundNBT nbt) {
    ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
  }
}


