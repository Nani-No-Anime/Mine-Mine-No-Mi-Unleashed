package xyz.pixelatedw.mineminenomi.challenges;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public abstract class Challenge
  extends ForgeRegistryEntry<Challenge> {
  private String title;
  private String category = "No Category";
  private boolean isCompleted;
  private String arenaName;
  private String objective;
  private boolean hasStarted;
  private int timeLimit;
  private ArenaData arenaData;
  protected Reward reward = new Reward();
  protected Difficulty difficulty = Difficulty.STANDARD;
  
  private List<Challenge> requirements = new ArrayList<>();
  private List<String> factionLocked = new ArrayList<>();
  
  protected IAfterStarting onStartEvent = (player, world, data) -> true;
  protected IAfterCompleting onCompleteEvent = player -> {
    
    };
  
  public Challenge(String title) {
    this.title = title;
    this.timeLimit = 10;
  }












  
  @Nullable
  public Challenge create() {
    try {
      return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
    }
    catch (Exception ex) {
      
      ex.printStackTrace();
      
      return null;
    } 
  }

  
  public boolean equals(Object challenge) {
    if (!(challenge instanceof Challenge)) {
      return false;
    }
    return getId().equalsIgnoreCase(((Challenge)challenge).getId());
  }

  
  public void start(PlayerEntity player) {
    if (!(player instanceof ServerPlayerEntity)) {
      return;
    }
    if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
      return;
    }
    ServerWorld nextWorld = player.getServer().getWorld(DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES));
    
    if (this.arenaData.isInUse) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ARENA_IN_USE, new Object[0]));
      
      return;
    } 
    this.hasStarted = this.onStartEvent.onStart((ServerPlayerEntity)player, (World)nextWorld, this.arenaData);
    
    if (!this.hasStarted) {
      return;
    }
    this.arenaData.owner = player.getUniqueID();
    
    (new ChallengeCountdownMessageThread((ServerPlayerEntity)player, this.title)).start();
  }

  
  public void complete(PlayerEntity player) {
    if (!(player instanceof ServerPlayerEntity)) {
      return;
    }
    this.onCompleteEvent.onComplete((ServerPlayerEntity)player);
    this.hasStarted = false;
    
    List<String> rewards = new ArrayList<>();
    if (!isComplete())
      rewards = this.reward.giveRewards(player); 
    (new ChallengeWinMessageThread((ServerPlayerEntity)player, rewards)).start();
    
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    for (Ability ability : props.getEquippedAbilities()) {
      
      if (ability != null) {
        
        if (ability instanceof ContinuousAbility)
          ((ContinuousAbility)ability).endContinuity(player); 
        if (ability instanceof RepeaterAbility)
          ((RepeaterAbility)ability).setRepeaterCount(0); 
        ability.startStandby();
      } 
    } 
    this.isCompleted = true;
    WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), ChallengesDataCapability.get(player)), player);
  }





  
  public String getId() {
    return "challenge.mineminenomi." + WyHelper.getResourceName(this.title);
  }

  
  public String getTitle() {
    return this.title;
  }

  
  public String getCategory() {
    return this.category;
  }

  
  public Challenge setCategory(String category) {
    this.category = category;
    return this;
  }

  
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  
  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  
  public String getObjective() {
    return this.objective;
  }

  
  public void setObjective(String objective) {
    this.objective = objective;
  }

  
  public boolean hasStarted() {
    return this.hasStarted;
  }




  
  public int getTimeLimit() {
    return this.timeLimit;
  }

  
  public void setTimeLimit(int time) {
    this.timeLimit = time;
  }

  
  public void setComplete(boolean flag) {
    this.isCompleted = flag;
  }

  
  public boolean isComplete() {
    return this.isCompleted;
  }

  
  public void addRequirements(Challenge... requirements) {
    for (Challenge req : requirements) {
      addRequirement(req);
    }
  }
  
  public void addRequirement(Challenge req) {
    if (!this.requirements.contains(req)) {
      this.requirements.add(req);
    }
  }
  
  public void lockedForFaction(String faction) {
    if (!this.factionLocked.contains(faction)) {
      this.factionLocked.add(faction);
    }
  }
  
  public void lockedForFactions(String... factions) {
    for (String faction : factions) {
      
      if (!this.factionLocked.contains(faction)) {
        this.factionLocked.add(faction);
      }
    } 
  }
  
  public boolean isLocked(PlayerEntity player) {
    IChallengesData challengeProps = ChallengesDataCapability.get(player);
    
    if (this.requirements.size() <= 0) {
      return false;
    }
    for (Challenge challenge : this.requirements) {
      
      if (challengeProps.getChallenge(challenge) == null || !challengeProps.getChallenge(challenge).isComplete())
      {
        return true;
      }
    } 
    
    return false;
  }

  
  public void setArena(ArenaData data) {
    this.arenaData = data;
    this.arenaName = data.arenaName;
  }

  
  public ArenaData getArenaData() {
    return this.arenaData;
  }

  
  public String getArenaName() {
    return this.arenaName;
  }
  
  public abstract EntityType getTarget();
  
  public static interface IAfterStarting
    extends Serializable
  {
    boolean onStart(ServerPlayerEntity param1ServerPlayerEntity, World param1World, ArenaData param1ArenaData);
  }
  
  public static interface IAfterCompleting
    extends Serializable {
    void onComplete(ServerPlayerEntity param1ServerPlayerEntity);
  }
  
  public static class Reward {
    private int doriki;
    private int belly;
    private int bounty;
    private List<ItemStack> items = new ArrayList<>();

    
    public int getDoriki() {
      return this.doriki;
    }

    
    public Reward setDoriki(int doriki) {
      this.doriki = doriki;
      return this;
    }

    
    public int getBelly() {
      return this.belly;
    }

    
    public Reward setBelly(int belly) {
      this.belly = belly;
      return this;
    }

    
    public int getBounty() {
      return this.bounty;
    }

    
    public Reward setBounty(int bounty) {
      this.bounty = bounty;
      return this;
    }

    
    public List<ItemStack> getItems() {
      return this.items;
    }

    
    public Reward addItem(ItemStack item) {
      this.items.add(item);
      return this;
    }

    
    public List<String> giveRewards(PlayerEntity player) {
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      List<String> rewards = new ArrayList<>();
      
      if (this.doriki > 0) {
        
        props.alterDoriki(this.doriki);
        rewards.add("Doriki " + this.doriki);
      } 
      
      if (this.belly > 0) {
        
        props.alterBelly(this.belly);
        rewards.add("Belly " + this.belly);
      } 
      
      if (this.bounty > 0) {
        
        props.alterBounty(this.bounty);
        rewards.add("Bounty " + this.bounty);
      } 
      
      for (ItemStack stack : this.items) {


        
        rewards.add(stack.getDisplayName().getFormattedText() + ((stack.getCount() > 1) ? (" - " + stack.getCount()) : ""));
        player.addItemStackToInventory(stack);
      } 
      
      return rewards;
    }
  }
  
  public enum Difficulty
  {
    STANDARD("Standard");
    
    private String name;

    
    Difficulty(String name) {
      this.name = name;
    }

    
    public String getName() {
      return this.name;
    }
  }
}


