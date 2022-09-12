package xyz.pixelatedw.mineminenomi.api.quests;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Quest
  extends ForgeRegistryEntry<Quest> {
  private String title;
  private String description;
  private List<Objective> objectives = new ArrayList<>();
  private List<Quest> requirements = new ArrayList<>();
  
  protected IStarting onStartEvent = player -> true;
  
  protected ICompleting onCompleteEvent = player -> true;
  
  protected IShouldRestart shouldRestartEvent = player -> false;
  
  public Quest(String id, String title) {
    this.title = title;
  }






  
  @Nullable
  public Quest create() {
    try {
      return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
    }
    catch (Exception ex) {
      
      ex.printStackTrace();
      
      return null;
    } 
  }

  
  public boolean equals(Object quest) {
    if (!(quest instanceof Quest)) {
      return false;
    }
    return getId().equalsIgnoreCase(((Quest)quest).getId());
  }

  
  public boolean removeQuestItem(PlayerEntity player, Item item, int amount) {
    int id = WyHelper.getIndexOfItemStack(item, (IInventory)player.inventory);
    
    if (id < 0) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent("Missing quest items: %s", new Object[] { (new TranslationTextComponent(item.getTranslationKey(), new Object[0])).getFormattedText() }));
      return false;
    } 
    
    player.inventory.getStackInSlot(id).shrink(amount);
    return true;
  }





  
  public boolean checkRestart(PlayerEntity player) {
    return this.shouldRestartEvent.check(player);
  }

  
  public boolean triggerCompleteEvent(PlayerEntity player) {
    return this.onCompleteEvent.check(player);
  }

  
  public boolean triggerStartEvent(PlayerEntity player) {
    return this.onStartEvent.check(player);
  }

  
  public void addRequirements(Quest... requirements) {
    for (Quest req : requirements) {
      addRequirement(req);
    }
  }
  
  public void addRequirement(Quest req) {
    if (!this.requirements.contains(req)) {
      this.requirements.add(req);
    }
  }
  
  public void addObjectives(Objective... objectives) {
    for (Objective obj : objectives) {
      addObjective(obj);
    }
  }
  
  public void addObjective(Objective objective) {
    if (!this.objectives.contains(objective)) {
      this.objectives.add(objective);
    }
  }
  
  public List<Objective> getObjectives() {
    return this.objectives;
  }

  
  public boolean isComplete() {
    return this.objectives.stream().allMatch(objective -> (!objective.isOptional() && objective.isComplete()));
  }

  
  public double getProgress() {
    int maxProgress = this.objectives.size();
    int completed = ((List)this.objectives.stream().filter(objective -> (!objective.isOptional() && objective.isComplete())).collect(Collectors.toList())).size();
    
    double progress = completed / maxProgress;
    
    return progress;
  }

  
  public void resetProgress() {
    this.objectives.stream().forEach(o -> o.setProgress(0.0D));
  }

  
  public void setDescription(String desc) {
    this.description = desc;
  }

  
  public String getDescription() {
    return this.description;
  }

  
  public String getId() {
    return WyHelper.getResourceName(this.title);
  }

  
  public String getTitle() {
    return this.title;
  }

  
  public boolean isLocked(IQuestData props) {
    if (this.requirements.size() <= 0) {
      return false;
    }
    boolean isLocked = false;
    for (Quest quest : this.requirements) {
      
      if (!props.hasFinishedQuest(quest)) {
        
        isLocked = true;
        
        break;
      } 
    } 
    return isLocked;
  }






  
  public CompoundNBT save() {
    CompoundNBT nbt = new CompoundNBT();
    
    nbt.putString("id", getId());
    ListNBT objectivesData = new ListNBT();
    for (Objective obj : getObjectives())
    {
      objectivesData.add(obj.save());
    }
    nbt.put("objectives", (INBT)objectivesData);
    
    return nbt;
  }

  
  public void load(CompoundNBT nbt) {
    ListNBT objectivesData = nbt.getList("objectives", 10);
    for (int i = 0; i < objectivesData.size(); i++) {
      
      CompoundNBT questData = objectivesData.getCompound(i);
      ((Objective)getObjectives().get(i)).load(questData);
    } 
  }
  
  public static interface ICompleting extends Serializable {
    boolean check(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IStarting extends Serializable {
    boolean check(PlayerEntity param1PlayerEntity);
  }
  
  public static interface IShouldRestart extends Serializable {
    boolean check(PlayerEntity param1PlayerEntity);
  }
}


