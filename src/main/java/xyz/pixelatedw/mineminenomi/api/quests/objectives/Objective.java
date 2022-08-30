/*     */ package xyz.pixelatedw.mineminenomi.api.quests.objectives;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public abstract class Objective
/*     */ {
/*     */   private String title;
/*     */   private String description;
/*     */   private boolean isHidden;
/*     */   private boolean isOptional;
/*  19 */   private double maxProgress = 1.0D;
/*     */   
/*     */   private double progress;
/*     */   private boolean hasEvent;
/*     */   private boolean hasStartedEvent;
/*  24 */   private List<Objective> requirements = new ArrayList<>();
/*     */   
/*     */   public IStartEvent onStartEvent = player -> {
/*     */     
/*     */     };
/*     */   public IRestartEvent onRestartEvent = player -> true;
/*     */   
/*     */   public Objective(String title) {
/*  32 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerStartEvent(PlayerEntity player) {
/*  42 */     this.hasStartedEvent = true;
/*  43 */     this.onStartEvent.start(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerRestartEvent(PlayerEntity player) {
/*  48 */     this.hasStartedEvent = false;
/*  49 */     this.onRestartEvent.restart(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProgress(double progress) {
/*  59 */     if (progress <= getMaxProgress()) {
/*  60 */       this.progress = progress;
/*     */     } else {
/*  62 */       this.progress = getMaxProgress();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void alterProgress(double progress) {
/*  67 */     this.progress = MathHelper.clamp(this.progress + progress, 0.0D, getMaxProgress());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/*  72 */     return this.progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxProgress(double progress) {
/*  77 */     this.maxProgress = progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxProgress() {
/*  82 */     return this.maxProgress;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective addRequirements(Objective... objectives) {
/*  87 */     for (Objective obj : objectives) {
/*  88 */       addRequirement(obj);
/*     */     }
/*  90 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective addRequirement(Objective objective) {
/*  95 */     if (!this.requirements.contains(objective)) {
/*  96 */       this.requirements.add(objective);
/*     */     }
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective setDescription(String desc) {
/* 103 */     this.description = desc;
/* 104 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective setOptional() {
/* 109 */     this.isOptional = true;
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOptional() {
/* 115 */     return this.isOptional;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective markHidden() {
/* 120 */     this.isHidden = true;
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/* 126 */     return WyHelper.getResourceName(this.title);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 131 */     return (this.isHidden && isLocked());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 136 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 141 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 146 */     return (this.progress >= this.maxProgress);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 151 */     if (this.requirements.size() <= 0) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (this.requirements.stream().allMatch(objective -> (!objective.isOptional() && objective.isComplete()))) {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedTitle() {
/* 162 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
/* 163 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()) })).getFormattedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasEvent(boolean flag) {
/* 168 */     this.hasEvent = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEvent() {
/* 173 */     return this.hasEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStartedEvent() {
/* 178 */     return this.hasStartedEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT save() {
/* 188 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/* 190 */     nbt.putString("id", getId());
/* 191 */     nbt.putBoolean("isHidden", this.isHidden);
/* 192 */     nbt.putDouble("progress", this.progress);
/* 193 */     nbt.putBoolean("hasStartedEvent", this.hasStartedEvent);
/*     */     
/* 195 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 200 */     this.isHidden = nbt.getBoolean("isHidden");
/* 201 */     this.progress = nbt.getDouble("progress");
/* 202 */     this.hasStartedEvent = nbt.getBoolean("hasStartedEvent");
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IRestartEvent {
/*     */     boolean restart(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartEvent {
/*     */     void start(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\quests\objectives\Objective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */