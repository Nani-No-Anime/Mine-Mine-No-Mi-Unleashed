/*     */ package xyz.pixelatedw.mineminenomi.challenges;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public abstract class Challenge
/*     */   extends ForgeRegistryEntry<Challenge> {
/*     */   private String title;
/*  38 */   private String category = "No Category";
/*     */   private boolean isCompleted;
/*     */   private String arenaName;
/*     */   private String objective;
/*     */   private boolean hasStarted;
/*     */   private int timeLimit;
/*     */   private ArenaData arenaData;
/*  45 */   protected Reward reward = new Reward();
/*  46 */   protected Difficulty difficulty = Difficulty.STANDARD;
/*     */   
/*  48 */   private List<Challenge> requirements = new ArrayList<>();
/*  49 */   private List<String> factionLocked = new ArrayList<>();
/*     */   
/*     */   protected IAfterStarting onStartEvent = (player, world, data) -> true;
/*     */   protected IAfterCompleting onCompleteEvent = player -> {
/*     */     
/*     */     };
/*     */   
/*     */   public Challenge(String title) {
/*  57 */     this.title = title;
/*  58 */     this.timeLimit = 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Challenge create() {
/*     */     try {
/*  76 */       return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */     }
/*  78 */     catch (Exception ex) {
/*     */       
/*  80 */       ex.printStackTrace();
/*     */       
/*  82 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object challenge) {
/*  88 */     if (!(challenge instanceof Challenge)) {
/*  89 */       return false;
/*     */     }
/*  91 */     return getId().equalsIgnoreCase(((Challenge)challenge).getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public void start(PlayerEntity player) {
/*  96 */     if (!(player instanceof ServerPlayerEntity)) {
/*     */       return;
/*     */     }
/*  99 */     if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*     */       return;
/*     */     }
/* 102 */     ServerWorld nextWorld = player.getServer().getWorld(DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES));
/*     */     
/* 104 */     if (this.arenaData.isInUse) {
/*     */       
/* 106 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ARENA_IN_USE, new Object[0]));
/*     */       
/*     */       return;
/*     */     } 
/* 110 */     this.hasStarted = this.onStartEvent.onStart((ServerPlayerEntity)player, (World)nextWorld, this.arenaData);
/*     */     
/* 112 */     if (!this.hasStarted) {
/*     */       return;
/*     */     }
/* 115 */     this.arenaData.owner = player.getUniqueID();
/*     */     
/* 117 */     (new ChallengeCountdownMessageThread((ServerPlayerEntity)player, this.title)).start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void complete(PlayerEntity player) {
/* 122 */     if (!(player instanceof ServerPlayerEntity)) {
/*     */       return;
/*     */     }
/* 125 */     this.onCompleteEvent.onComplete((ServerPlayerEntity)player);
/* 126 */     this.hasStarted = false;
/*     */     
/* 128 */     List<String> rewards = new ArrayList<>();
/* 129 */     if (!isComplete())
/* 130 */       rewards = this.reward.giveRewards(player); 
/* 131 */     (new ChallengeWinMessageThread((ServerPlayerEntity)player, rewards)).start();
/*     */     
/* 133 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 134 */     for (Ability ability : props.getEquippedAbilities()) {
/*     */       
/* 136 */       if (ability != null) {
/*     */         
/* 138 */         if (ability instanceof ContinuousAbility)
/* 139 */           ((ContinuousAbility)ability).endContinuity(player); 
/* 140 */         if (ability instanceof RepeaterAbility)
/* 141 */           ((RepeaterAbility)ability).setRepeaterCount(0); 
/* 142 */         ability.startStandby();
/*     */       } 
/*     */     } 
/* 145 */     this.isCompleted = true;
/* 146 */     WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), ChallengesDataCapability.get(player)), player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getId() {
/* 155 */     return "challenge.mineminenomi." + WyHelper.getResourceName(this.title);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 160 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCategory() {
/* 165 */     return this.category;
/*     */   }
/*     */ 
/*     */   
/*     */   public Challenge setCategory(String category) {
/* 170 */     this.category = category;
/* 171 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Difficulty getDifficulty() {
/* 176 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficulty(Difficulty difficulty) {
/* 181 */     this.difficulty = difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObjective() {
/* 186 */     return this.objective;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjective(String objective) {
/* 191 */     this.objective = objective;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStarted() {
/* 196 */     return this.hasStarted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeLimit() {
/* 204 */     return this.timeLimit;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeLimit(int time) {
/* 209 */     this.timeLimit = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setComplete(boolean flag) {
/* 214 */     this.isCompleted = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 219 */     return this.isCompleted;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRequirements(Challenge... requirements) {
/* 224 */     for (Challenge req : requirements) {
/* 225 */       addRequirement(req);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addRequirement(Challenge req) {
/* 230 */     if (!this.requirements.contains(req)) {
/* 231 */       this.requirements.add(req);
/*     */     }
/*     */   }
/*     */   
/*     */   public void lockedForFaction(String faction) {
/* 236 */     if (!this.factionLocked.contains(faction)) {
/* 237 */       this.factionLocked.add(faction);
/*     */     }
/*     */   }
/*     */   
/*     */   public void lockedForFactions(String... factions) {
/* 242 */     for (String faction : factions) {
/*     */       
/* 244 */       if (!this.factionLocked.contains(faction)) {
/* 245 */         this.factionLocked.add(faction);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isLocked(PlayerEntity player) {
/* 251 */     IChallengesData challengeProps = ChallengesDataCapability.get(player);
/*     */     
/* 253 */     if (this.requirements.size() <= 0) {
/* 254 */       return false;
/*     */     }
/* 256 */     for (Challenge challenge : this.requirements) {
/*     */       
/* 258 */       if (challengeProps.getChallenge(challenge) == null || !challengeProps.getChallenge(challenge).isComplete())
/*     */       {
/* 260 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArena(ArenaData data) {
/* 269 */     this.arenaData = data;
/* 270 */     this.arenaName = data.arenaName;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArenaData getArenaData() {
/* 275 */     return this.arenaData;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getArenaName() {
/* 280 */     return this.arenaName;
/*     */   }
/*     */   
/*     */   public abstract EntityType getTarget();
/*     */   
/*     */   public static interface IAfterStarting
/*     */     extends Serializable
/*     */   {
/*     */     boolean onStart(ServerPlayerEntity param1ServerPlayerEntity, World param1World, ArenaData param1ArenaData);
/*     */   }
/*     */   
/*     */   public static interface IAfterCompleting
/*     */     extends Serializable {
/*     */     void onComplete(ServerPlayerEntity param1ServerPlayerEntity);
/*     */   }
/*     */   
/*     */   public static class Reward {
/*     */     private int doriki;
/*     */     private int belly;
/*     */     private int bounty;
/* 300 */     private List<ItemStack> items = new ArrayList<>();
/*     */ 
/*     */     
/*     */     public int getDoriki() {
/* 304 */       return this.doriki;
/*     */     }
/*     */ 
/*     */     
/*     */     public Reward setDoriki(int doriki) {
/* 309 */       this.doriki = doriki;
/* 310 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getBelly() {
/* 315 */       return this.belly;
/*     */     }
/*     */ 
/*     */     
/*     */     public Reward setBelly(int belly) {
/* 320 */       this.belly = belly;
/* 321 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getBounty() {
/* 326 */       return this.bounty;
/*     */     }
/*     */ 
/*     */     
/*     */     public Reward setBounty(int bounty) {
/* 331 */       this.bounty = bounty;
/* 332 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<ItemStack> getItems() {
/* 337 */       return this.items;
/*     */     }
/*     */ 
/*     */     
/*     */     public Reward addItem(ItemStack item) {
/* 342 */       this.items.add(item);
/* 343 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<String> giveRewards(PlayerEntity player) {
/* 348 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 349 */       List<String> rewards = new ArrayList<>();
/*     */       
/* 351 */       if (this.doriki > 0) {
/*     */         
/* 353 */         props.alterDoriki(this.doriki);
/* 354 */         rewards.add("Doriki " + this.doriki);
/*     */       } 
/*     */       
/* 357 */       if (this.belly > 0) {
/*     */         
/* 359 */         props.alterBelly(this.belly);
/* 360 */         rewards.add("Belly " + this.belly);
/*     */       } 
/*     */       
/* 363 */       if (this.bounty > 0) {
/*     */         
/* 365 */         props.alterBounty(this.bounty);
/* 366 */         rewards.add("Bounty " + this.bounty);
/*     */       } 
/*     */       
/* 369 */       for (ItemStack stack : this.items) {
/*     */ 
/*     */ 
/*     */         
/* 373 */         rewards.add(stack.getDisplayName().getFormattedText() + ((stack.getCount() > 1) ? (" - " + stack.getCount()) : ""));
/* 374 */         player.addItemStackToInventory(stack);
/*     */       } 
/*     */       
/* 377 */       return rewards;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Difficulty
/*     */   {
/* 383 */     STANDARD("Standard");
/*     */     
/*     */     private String name;
/*     */ 
/*     */     
/*     */     Difficulty(String name) {
/* 389 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 394 */       return this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\challenges\Challenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */