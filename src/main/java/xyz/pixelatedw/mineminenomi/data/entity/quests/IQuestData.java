package xyz.pixelatedw.mineminenomi.data.entity.quests;

import java.util.List;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

public interface IQuestData {
  boolean addInProgressQuest(Quest paramQuest);
  
  boolean setInProgressQuest(int paramInt, Quest paramQuest);
  
  boolean removeInProgressQuest(Quest paramQuest);
  
  boolean hasInProgressQuest(Quest paramQuest);
  
  <T extends Quest> T getInProgressQuest(T paramT);
  
  <T extends Quest> T getInProgressQuest(int paramInt);
  
  int getInProgressQuestSlot(Quest paramQuest);
  
  List<Objective> getInProgressObjectives();
  
  Quest[] getInProgressQuests();
  
  void clearInProgressQuests();
  
  int countInProgressQuests();
  
  boolean addFinishedQuest(Quest paramQuest);
  
  boolean removeFinishedQuest(Quest paramQuest);
  
  boolean hasFinishedQuest(Quest paramQuest);
  
  <T extends Quest> T getFinishedQuest(T paramT);
  
  List<Quest> getFinishedQuests();
  
  void clearFinishedQuests();
  
  int countFinishedQuests();
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\IQuestData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */