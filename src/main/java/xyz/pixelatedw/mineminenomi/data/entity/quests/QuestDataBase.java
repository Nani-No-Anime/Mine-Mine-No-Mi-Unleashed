package xyz.pixelatedw.mineminenomi.data.entity.quests;

import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class QuestDataBase
  implements IQuestData
{
  private Quest[] inProgressQuests = new Quest[4];
  private List<Quest> finishedQuests = new ArrayList<>();


  
  public boolean addInProgressQuest(Quest quest) {
    for (int i = 0; i < this.inProgressQuests.length; i++) {
      
      Quest ogQuest = this.inProgressQuests[i];
      if (ogQuest == null) {
        
        this.inProgressQuests[i] = quest;
        return true;
      } 
    } 
    return false;
  }


  
  public boolean setInProgressQuest(int slot, Quest quest) {
    Quest ogQuest = getInProgressQuest(quest);
    if (ogQuest == null && slot <= 4) {
      
      this.inProgressQuests[slot] = quest;
      return true;
    } 
    return false;
  }


  
  public boolean removeInProgressQuest(Quest quest) {
    Quest ogQuest = getInProgressQuest(quest);
    if (ogQuest != null)
    {
      for (int i = 0; i < this.inProgressQuests.length; i++) {
        
        Quest inProgressQuest = this.inProgressQuests[i];
        if (inProgressQuest != null && inProgressQuest.equals(ogQuest)) {
          
          inProgressQuest.resetProgress();
          this.inProgressQuests[i] = null;
          return true;
        } 
      } 
    }
    return false;
  }


  
  public boolean hasInProgressQuest(Quest quest) {
    return Arrays.<Quest>stream(this.inProgressQuests)
      .parallel()
      .filter(qst -> (qst != null))
      .anyMatch(qst -> qst.equals(quest));
  }


  
  public <T extends Quest> T getInProgressQuest(T quest) {
    return (T)Arrays.<Quest>stream(this.inProgressQuests)
      .parallel()
      .filter(qst -> (qst != null))
      .filter(qst -> qst.equals(quest))
      .findFirst().orElse(null);
  }


  
  public <T extends Quest> T getInProgressQuest(int slot) {
    return (T)this.inProgressQuests[slot];
  }


  
  public int getInProgressQuestSlot(Quest quest) {
    for (int i = 0; i < this.inProgressQuests.length; i++) {
      
      if (this.inProgressQuests[i] != null && this.inProgressQuests[i].equals(quest)) {
        return i;
      }
    } 
    return -1;
  }


  
  public List<Objective> getInProgressObjectives() {
    List<Objective> objectives = new ArrayList<>();
    
    for (Quest quest : getInProgressQuests()) {
      
      if (quest != null)
      {
        
        if (!quest.isComplete())
        {
          for (Objective obj : quest.getObjectives()) {
            
            if (!obj.isHidden() && !obj.isLocked() && !obj.isComplete())
            {
              objectives.add(obj);
            }
          } 
        }
      }
    } 
    return objectives;
  }


  
  public Quest[] getInProgressQuests() {
    return this.inProgressQuests;
  }


  
  public void clearInProgressQuests() {
    for (int i = 0; i < this.inProgressQuests.length; i++) {
      
      Quest quest = this.inProgressQuests[i];
      if (quest != null)
      {
        this.inProgressQuests[i] = null;
      }
    } 
  }


  
  public int countInProgressQuests() {
    return ((List)Arrays.<Quest>stream(this.inProgressQuests)
      .parallel()
      .filter(quest -> (quest != null))
      .collect(Collectors.toList()))
      .size();
  }


  
  public boolean addFinishedQuest(Quest quest) {
    Quest ogQuest = getFinishedQuest(quest);
    if (ogQuest == null) {
      
      this.finishedQuests.add(quest);
      return true;
    } 
    return false;
  }


  
  public boolean removeFinishedQuest(Quest quest) {
    Quest ogQuest = getFinishedQuest(quest);
    if (ogQuest != null) {
      
      this.finishedQuests.remove(ogQuest);
      return true;
    } 
    return false;
  }


  
  public boolean hasFinishedQuest(Quest quest) {
    this.finishedQuests.removeIf(qst -> (qst == null));
    return this.finishedQuests.parallelStream().anyMatch(qst -> qst.equals(quest));
  }


  
  public <T extends Quest> T getFinishedQuest(T quest) {
    this.finishedQuests.removeIf(qst -> (qst == null));
    return (T)this.finishedQuests.parallelStream().filter(qst -> qst.equals(quest)).findFirst().orElse(null);
  }


  
  public List<Quest> getFinishedQuests() {
    this.finishedQuests.removeIf(qst -> (qst == null));
    return (List<Quest>)this.finishedQuests.parallelStream().collect(Collectors.toList());
  }


  
  public void clearFinishedQuests() {
    this.finishedQuests.removeIf(qst -> (qst == null));
  }


  
  public int countFinishedQuests() {
    this.finishedQuests.removeIf(qst -> (qst == null));
    return this.finishedQuests.size();
  }
}


