/*     */ package xyz.pixelatedw.mineminenomi.data.entity.quests;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ 
/*     */ 
/*     */ public class QuestDataBase
/*     */   implements IQuestData
/*     */ {
/*  14 */   private Quest[] inProgressQuests = new Quest[4];
/*  15 */   private List<Quest> finishedQuests = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addInProgressQuest(Quest quest) {
/*  20 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/*  22 */       Quest ogQuest = this.inProgressQuests[i];
/*  23 */       if (ogQuest == null) {
/*     */         
/*  25 */         this.inProgressQuests[i] = quest;
/*  26 */         return true;
/*     */       } 
/*     */     } 
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setInProgressQuest(int slot, Quest quest) {
/*  35 */     Quest ogQuest = getInProgressQuest(quest);
/*  36 */     if (ogQuest == null && slot <= 4) {
/*     */       
/*  38 */       this.inProgressQuests[slot] = quest;
/*  39 */       return true;
/*     */     } 
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeInProgressQuest(Quest quest) {
/*  47 */     Quest ogQuest = getInProgressQuest(quest);
/*  48 */     if (ogQuest != null)
/*     */     {
/*  50 */       for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */         
/*  52 */         Quest inProgressQuest = this.inProgressQuests[i];
/*  53 */         if (inProgressQuest != null && inProgressQuest.equals(ogQuest)) {
/*     */           
/*  55 */           inProgressQuest.resetProgress();
/*  56 */           this.inProgressQuests[i] = null;
/*  57 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasInProgressQuest(Quest quest) {
/*  67 */     return Arrays.<Quest>stream(this.inProgressQuests)
/*  68 */       .parallel()
/*  69 */       .filter(qst -> (qst != null))
/*  70 */       .anyMatch(qst -> qst.equals(quest));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getInProgressQuest(T quest) {
/*  76 */     return (T)Arrays.<Quest>stream(this.inProgressQuests)
/*  77 */       .parallel()
/*  78 */       .filter(qst -> (qst != null))
/*  79 */       .filter(qst -> qst.equals(quest))
/*  80 */       .findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getInProgressQuest(int slot) {
/*  86 */     return (T)this.inProgressQuests[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInProgressQuestSlot(Quest quest) {
/*  92 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/*  94 */       if (this.inProgressQuests[i] != null && this.inProgressQuests[i].equals(quest)) {
/*  95 */         return i;
/*     */       }
/*     */     } 
/*  98 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Objective> getInProgressObjectives() {
/* 104 */     List<Objective> objectives = new ArrayList<>();
/*     */     
/* 106 */     for (Quest quest : getInProgressQuests()) {
/*     */       
/* 108 */       if (quest != null)
/*     */       {
/*     */         
/* 111 */         if (!quest.isComplete())
/*     */         {
/* 113 */           for (Objective obj : quest.getObjectives()) {
/*     */             
/* 115 */             if (!obj.isHidden() && !obj.isLocked() && !obj.isComplete())
/*     */             {
/* 117 */               objectives.add(obj);
/*     */             }
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/* 123 */     return objectives;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quest[] getInProgressQuests() {
/* 129 */     return this.inProgressQuests;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearInProgressQuests() {
/* 135 */     for (int i = 0; i < this.inProgressQuests.length; i++) {
/*     */       
/* 137 */       Quest quest = this.inProgressQuests[i];
/* 138 */       if (quest != null)
/*     */       {
/* 140 */         this.inProgressQuests[i] = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countInProgressQuests() {
/* 148 */     return ((List)Arrays.<Quest>stream(this.inProgressQuests)
/* 149 */       .parallel()
/* 150 */       .filter(quest -> (quest != null))
/* 151 */       .collect(Collectors.toList()))
/* 152 */       .size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addFinishedQuest(Quest quest) {
/* 158 */     Quest ogQuest = getFinishedQuest(quest);
/* 159 */     if (ogQuest == null) {
/*     */       
/* 161 */       this.finishedQuests.add(quest);
/* 162 */       return true;
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeFinishedQuest(Quest quest) {
/* 170 */     Quest ogQuest = getFinishedQuest(quest);
/* 171 */     if (ogQuest != null) {
/*     */       
/* 173 */       this.finishedQuests.remove(ogQuest);
/* 174 */       return true;
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFinishedQuest(Quest quest) {
/* 182 */     this.finishedQuests.removeIf(qst -> (qst == null));
/* 183 */     return this.finishedQuests.parallelStream().anyMatch(qst -> qst.equals(quest));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Quest> T getFinishedQuest(T quest) {
/* 189 */     this.finishedQuests.removeIf(qst -> (qst == null));
/* 190 */     return (T)this.finishedQuests.parallelStream().filter(qst -> qst.equals(quest)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Quest> getFinishedQuests() {
/* 196 */     this.finishedQuests.removeIf(qst -> (qst == null));
/* 197 */     return (List<Quest>)this.finishedQuests.parallelStream().collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearFinishedQuests() {
/* 203 */     this.finishedQuests.removeIf(qst -> (qst == null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int countFinishedQuests() {
/* 209 */     this.finishedQuests.removeIf(qst -> (qst == null));
/* 210 */     return this.finishedQuests.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\QuestDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */