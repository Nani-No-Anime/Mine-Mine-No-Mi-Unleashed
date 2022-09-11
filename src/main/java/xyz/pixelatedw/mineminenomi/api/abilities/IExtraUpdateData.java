package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.nbt.CompoundNBT;

public interface IExtraUpdateData {
  CompoundNBT getExtraData();
  
  void setExtraData(CompoundNBT paramCompoundNBT);
}


