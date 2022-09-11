package xyz.pixelatedw.mineminenomi.data.entity.quests;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class QuestDataProvider
  implements ICapabilitySerializable<CompoundNBT> {
  private IQuestData instance = (IQuestData)QuestDataCapability.INSTANCE.getDefaultInstance();


  
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return QuestDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> this.instance));
  }


  
  public CompoundNBT serializeNBT() {
    return (CompoundNBT)QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, this.instance, null);
  }


  
  public void deserializeNBT(CompoundNBT nbt) {
    QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, this.instance, null, (INBT)nbt);
  }
}


