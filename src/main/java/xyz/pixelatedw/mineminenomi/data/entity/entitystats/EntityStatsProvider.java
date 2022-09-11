package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class EntityStatsProvider
  implements ICapabilitySerializable<CompoundNBT> {
  private IEntityStats instance = (IEntityStats)EntityStatsCapability.INSTANCE.getDefaultInstance();


  
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return EntityStatsCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
  }


  
  public CompoundNBT serializeNBT() {
    return (CompoundNBT)EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, this.instance, null);
  }


  
  public void deserializeNBT(CompoundNBT nbt) {
    EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, this.instance, null, (INBT)nbt);
  }
}


