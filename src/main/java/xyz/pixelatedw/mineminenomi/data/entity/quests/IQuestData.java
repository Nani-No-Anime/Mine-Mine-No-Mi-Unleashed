package xyz.pixelatedw.mineminenomi.data.entity.quests;

import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

import java.util.List;

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


