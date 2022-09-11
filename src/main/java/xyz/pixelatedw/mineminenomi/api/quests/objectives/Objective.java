package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public abstract class Objective
{
  private String title;
  private String description;
  private boolean isHidden;
  private boolean isOptional;
  private double maxProgress = 1.0D;
  
  private double progress;
  private boolean hasEvent;
  private boolean hasStartedEvent;
  private List<Objective> requirements = new ArrayList<>();
  
  public IStartEvent onStartEvent = player -> {
    
    };
  public IRestartEvent onRestartEvent = player -> true;
  
  public Objective(String title) {
    this.title = title;
  }






  
  public void triggerStartEvent(PlayerEntity player) {
    this.hasStartedEvent = true;
    this.onStartEvent.start(player);
  }

  
  public void triggerRestartEvent(PlayerEntity player) {
    this.hasStartedEvent = false;
    this.onRestartEvent.restart(player);
  }






  
  public void setProgress(double progress) {
    if (progress <= getMaxProgress()) {
      this.progress = progress;
    } else {
      this.progress = getMaxProgress();
    } 
  }
  
  public void alterProgress(double progress) {
    this.progress = MathHelper.clamp(this.progress + progress, 0.0D, getMaxProgress());
  }

  
  public double getProgress() {
    return this.progress;
  }

  
  public void setMaxProgress(double progress) {
    this.maxProgress = progress;
  }

  
  public double getMaxProgress() {
    return this.maxProgress;
  }

  
  public Objective addRequirements(Objective... objectives) {
    for (Objective obj : objectives) {
      addRequirement(obj);
    }
    return this;
  }

  
  public Objective addRequirement(Objective objective) {
    if (!this.requirements.contains(objective)) {
      this.requirements.add(objective);
    }
    return this;
  }

  
  public Objective setDescription(String desc) {
    this.description = desc;
    return this;
  }

  
  public Objective setOptional() {
    this.isOptional = true;
    return this;
  }

  
  public boolean isOptional() {
    return this.isOptional;
  }

  
  public Objective markHidden() {
    this.isHidden = true;
    return this;
  }

  
  public String getId() {
    return WyHelper.getResourceName(this.title);
  }

  
  public boolean isHidden() {
    return (this.isHidden && isLocked());
  }

  
  public String getTitle() {
    return this.title;
  }

  
  public String getDescription() {
    return this.description;
  }

  
  public boolean isComplete() {
    return (this.progress >= this.maxProgress);
  }

  
  public boolean isLocked() {
    if (this.requirements.size() <= 0) {
      return false;
    }
    if (this.requirements.stream().allMatch(objective -> (!objective.isOptional() && objective.isComplete()))) {
      return false;
    }
    return true;
  }

  
  public String getLocalizedTitle() {
    String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
    return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()) })).getFormattedText();
  }

  
  public void setHasEvent(boolean flag) {
    this.hasEvent = flag;
  }

  
  public boolean hasEvent() {
    return this.hasEvent;
  }

  
  public boolean hasStartedEvent() {
    return this.hasStartedEvent;
  }






  
  public CompoundNBT save() {
    CompoundNBT nbt = new CompoundNBT();
    
    nbt.putString("id", getId());
    nbt.putBoolean("isHidden", this.isHidden);
    nbt.putDouble("progress", this.progress);
    nbt.putBoolean("hasStartedEvent", this.hasStartedEvent);
    
    return nbt;
  }

  
  public void load(CompoundNBT nbt) {
    this.isHidden = nbt.getBoolean("isHidden");
    this.progress = nbt.getDouble("progress");
    this.hasStartedEvent = nbt.getBoolean("hasStartedEvent");
  }
  
  @FunctionalInterface
  public static interface IRestartEvent {
    boolean restart(PlayerEntity param1PlayerEntity);
  }
  
  @FunctionalInterface
  public static interface IStartEvent {
    void start(PlayerEntity param1PlayerEntity);
  }
}


