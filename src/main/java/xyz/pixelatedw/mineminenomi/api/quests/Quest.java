/*     */ package xyz.pixelatedw.mineminenomi.api.quests;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class Quest
/*     */   extends ForgeRegistryEntry<Quest> {
/*     */   private String title;
/*     */   private String description;
/*  26 */   private List<Objective> objectives = new ArrayList<>();
/*  27 */   private List<Quest> requirements = new ArrayList<>();
/*     */   
/*     */   protected IStarting onStartEvent = player -> true;
/*     */   
/*     */   protected ICompleting onCompleteEvent = player -> true;
/*     */   
/*     */   protected IShouldRestart shouldRestartEvent = player -> false;
/*     */   
/*     */   public Quest(String id, String title) {
/*  36 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Quest create() {
/*     */     try {
/*  48 */       return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */     }
/*  50 */     catch (Exception ex) {
/*     */       
/*  52 */       ex.printStackTrace();
/*     */       
/*  54 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object quest) {
/*  60 */     if (!(quest instanceof Quest)) {
/*  61 */       return false;
/*     */     }
/*  63 */     return getId().equalsIgnoreCase(((Quest)quest).getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeQuestItem(PlayerEntity player, Item item, int amount) {
/*  68 */     int id = WyHelper.getIndexOfItemStack(item, (IInventory)player.inventory);
/*     */     
/*  70 */     if (id < 0) {
/*     */       
/*  72 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Missing quest items: %s", new Object[] { (new TranslationTextComponent(item.getTranslationKey(), new Object[0])).getFormattedText() }));
/*  73 */       return false;
/*     */     } 
/*     */     
/*  76 */     player.inventory.getStackInSlot(id).shrink(amount);
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkRestart(PlayerEntity player) {
/*  86 */     return this.shouldRestartEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean triggerCompleteEvent(PlayerEntity player) {
/*  91 */     return this.onCompleteEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean triggerStartEvent(PlayerEntity player) {
/*  96 */     return this.onStartEvent.check(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRequirements(Quest... requirements) {
/* 101 */     for (Quest req : requirements) {
/* 102 */       addRequirement(req);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addRequirement(Quest req) {
/* 107 */     if (!this.requirements.contains(req)) {
/* 108 */       this.requirements.add(req);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addObjectives(Objective... objectives) {
/* 113 */     for (Objective obj : objectives) {
/* 114 */       addObjective(obj);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addObjective(Objective objective) {
/* 119 */     if (!this.objectives.contains(objective)) {
/* 120 */       this.objectives.add(objective);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Objective> getObjectives() {
/* 125 */     return this.objectives;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 130 */     return this.objectives.stream().allMatch(objective -> (!objective.isOptional() && objective.isComplete()));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/* 135 */     int maxProgress = this.objectives.size();
/* 136 */     int completed = ((List)this.objectives.stream().filter(objective -> (!objective.isOptional() && objective.isComplete())).collect(Collectors.toList())).size();
/*     */     
/* 138 */     double progress = completed / maxProgress;
/*     */     
/* 140 */     return progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetProgress() {
/* 145 */     this.objectives.stream().forEach(o -> o.setProgress(0.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String desc) {
/* 150 */     this.description = desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 155 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/* 160 */     return WyHelper.getResourceName(this.title);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 165 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked(IQuestData props) {
/* 170 */     if (this.requirements.size() <= 0) {
/* 171 */       return false;
/*     */     }
/* 173 */     boolean isLocked = false;
/* 174 */     for (Quest quest : this.requirements) {
/*     */       
/* 176 */       if (!props.hasFinishedQuest(quest)) {
/*     */         
/* 178 */         isLocked = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 183 */     return isLocked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT save() {
/* 193 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/* 195 */     nbt.putString("id", getId());
/* 196 */     ListNBT objectivesData = new ListNBT();
/* 197 */     for (Objective obj : getObjectives())
/*     */     {
/* 199 */       objectivesData.add(obj.save());
/*     */     }
/* 201 */     nbt.put("objectives", (INBT)objectivesData);
/*     */     
/* 203 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 208 */     ListNBT objectivesData = nbt.getList("objectives", 10);
/* 209 */     for (int i = 0; i < objectivesData.size(); i++) {
/*     */       
/* 211 */       CompoundNBT questData = objectivesData.getCompound(i);
/* 212 */       ((Objective)getObjectives().get(i)).load(questData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface ICompleting extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IStarting extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IShouldRestart extends Serializable {
/*     */     boolean check(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\quests\Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */