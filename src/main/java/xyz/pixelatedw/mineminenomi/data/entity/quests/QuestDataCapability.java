package xyz.pixelatedw.mineminenomi.data.entity.quests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;


public class QuestDataCapability
{
  @CapabilityInject(IQuestData.class)
  public static final Capability<IQuestData> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>()
        {
          
          public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();
            
            ListNBT questsInTracker = new ListNBT();
            for (int i = 0; i < (instance.getInProgressQuests()).length; i++) {
              
              Quest quest = instance.getInProgressQuests()[i];
              if (quest != null)
                questsInTracker.add(quest.save()); 
            } 
            props.put("quests_in_tracker", (INBT)questsInTracker);
            
            ListNBT finishedQuests = new ListNBT();
            for (int j = 0; j < instance.getFinishedQuests().size(); j++) {
              
              Quest quest = instance.getFinishedQuests().get(j);
              CompoundNBT questNbt = new CompoundNBT();
              questNbt.putString("id", quest.getId());
              finishedQuests.add(questNbt);
            } 
            props.put("finished_quests", (INBT)finishedQuests);
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;
            
            instance.clearInProgressQuests();
            instance.clearFinishedQuests();
            
            ListNBT trackerQuests = props.getList("quests_in_tracker", 10);
            for (int i = 0; i < trackerQuests.size(); i++) {
              
              CompoundNBT nbtQuests = trackerQuests.getCompound(i);
              Quest quest = (Quest)GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(APIConfig.projectId, nbtQuests.getString("id")));
              
              try {
                Quest newQuest = quest.create();
                newQuest.load(nbtQuests);
                instance.setInProgressQuest(i, newQuest);
              }
              catch (Exception e) {
                
                WyDebug.debug("Unregistered quest: " + nbtQuests.getString("id"));
              } 
            } 
            
            ListNBT finishedQuests = props.getList("finished_quests", 10);
            for (int j = 0; j < finishedQuests.size(); j++) {
              
              CompoundNBT nbtQuests = finishedQuests.getCompound(j);
              Quest quest = (Quest)GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(APIConfig.projectId, nbtQuests.getString("id")));
              
              try {
                instance.addFinishedQuest(quest.create());
              }
              catch (Exception e) {
                
                WyDebug.debug("Unregistered quest: " + nbtQuests.getString("id"));
              } 
            } 
          }
        },QuestDataBase::new);
  }


  
  public static IQuestData get(PlayerEntity entity) {
    return (IQuestData)entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
  }

  
  public static LazyOptional<IQuestData> getLazy(PlayerEntity entity) {
    return entity.getCapability(INSTANCE, null);
  }
}


