package xyz.pixelatedw.mineminenomi.abilities.mane;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.entities.zoan.ManeManeMemoryZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManeManeMemoryAbility extends PunchAbility implements IExtraUpdateData, IMorphAbility {
  public static final ManeManeMemoryAbility INSTANCE = new ManeManeMemoryAbility();
  
  private static final ManeMemory EMPTY_MEMORY = new ManeMemory();
  
  private List<ManeMemory> memories = new ArrayList<>();
  
  private int memoryId;
  
  public ManeManeMemoryAbility() {
    super("Mane Mane Memory", AbilityHelper.getDevilFruitCategory());
    setDescription("By hitting another player the user saves their properties.\n\n§2SHIFT-USE§r: Switches between saved targets");
    
    setMaxCooldown(1.0D);
    setStoppingAfterHit(false);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isCrouching() && !this.memories.isEmpty()) {
      
      if (this.memoryId + 1 >= this.memories.size()) {
        this.memoryId = 0;
      } else {
        this.memoryId++;
      }  player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MANE_SWITCH_MEMORY, new Object[] { ((ManeMemory)this.memories.get(this.memoryId)).getDisplayName(), Integer.valueOf(this.memoryId + 1), Integer.valueOf(this.memories.size()) }));
      endContinuity(player);
      return false;
    } 
    
    if (!this.memories.isEmpty() && getMemory().isValidMemory()) {
      
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
      props.setZoanPoint(getTransformation().getForm());
      
      setState(Ability.State.CONTINUOUS);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), props), (LivingEntity)player);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      player.world.playMovingSound(null, (Entity)player, ModSounds.MANE_SWITCH, SoundCategory.PLAYERS, 1.0F, 1.0F);
      player.refreshDisplayName();
    } 
    
    return true;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    if (target instanceof PlayerEntity)
    {
      if (!this.memories.stream().anyMatch(mem -> mem.getUUID().equals(target.getUniqueID()))) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MANE_ADDED_MEMORY, new Object[] { target.getDisplayName().getFormattedText() }));
        ManeMemory memory = ManeMemory.from((PlayerEntity)target);
        this.memories.add(memory);
      } 
    }
    
    return 1.0F;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (getMemory().isValidMemory()) {
      
      IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
      props.setZoanPoint("");
      WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
      player.refreshDisplayName();
    } 
    return true;
  }

  
  public ManeMemory getMemory() {
    if (this.memories.isEmpty())
      return EMPTY_MEMORY; 
    return this.memories.get(this.memoryId);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)ManeManeMemoryZoanInfo.INSTANCE;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return (isContinuous() && ManeManeMemoryZoanInfo.INSTANCE.isActive(entity));
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    
    nbt.putInt("memoryId", this.memoryId);
    
    ListNBT list = new ListNBT();
    for (ManeMemory memory : this.memories)
    {
      list.add(memory.saveTargetData());
    }
    nbt.put("memories", (INBT)list);
    
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.memoryId = nbt.getInt("memoryId");
    
    this.memories.clear();
    ListNBT list = (ListNBT)nbt.get("memories");
    for (int i = 0; i < list.size(); i++) {
      
      CompoundNBT memoryNBT = list.getCompound(i);
      ManeMemory memory = new ManeMemory();
      memory.loadTargetData(memoryNBT);
      this.memories.add(memory);
    } 
  }
  
  public static class ManeMemory
  {
    private UUID uuid = ModValues.NIL_UUID;
    private String displayName = "";
    private int doriki;
    private String faction = ""; private String race = ""; private String subRace = ""; private String fightingStyle = ""; private float kenbunshokuExp;
    private float busoshokuHardeningExp;
    private float busoshokuImbuingExp;
    
    public static ManeMemory from(PlayerEntity target) {
      ManeMemory memory = new ManeMemory();
      
      IEntityStats stats = EntityStatsCapability.get((LivingEntity)target);
      IHakiData hakiData = HakiDataCapability.get((LivingEntity)target);
      
      memory.uuid = target.getUniqueID();
      memory.displayName = target.getDisplayName().getFormattedText();
      memory.doriki = stats.getDoriki();
      memory.faction = stats.getFaction();
      memory.race = stats.getRace();
      memory.subRace = stats.getSubRace();
      memory.fightingStyle = stats.getFightingStyle();
      memory.kenbunshokuExp = hakiData.getKenbunshokuHakiExp();
      memory.busoshokuHardeningExp = hakiData.getBusoshokuHardeningHakiExp();
      memory.busoshokuImbuingExp = hakiData.getBusoshokuImbuingHakiExp();
      
      return memory;
    }

    
    public boolean isValidMemory() {
      return !this.uuid.equals(ModValues.NIL_UUID);
    }

    
    public UUID getUUID() {
      return this.uuid;
    }

    
    public String getDisplayName() {
      return this.displayName;
    }

    
    public CompoundNBT saveTargetData() {
      CompoundNBT nbt = new CompoundNBT();
      
      nbt.putUniqueId("uuid", this.uuid);
      nbt.putString("displayName", this.displayName);
      nbt.putInt("doriki", this.doriki);
      nbt.putString("faction", this.faction);
      nbt.putString("race", this.race);
      nbt.putString("subRace", this.subRace);
      nbt.putString("fightingStyle", this.fightingStyle);
      nbt.putFloat("kenbunshokuExp", this.kenbunshokuExp);
      nbt.putFloat("busoshokuHardeningExp", this.busoshokuHardeningExp);
      nbt.putFloat("busoshokuImbuingExp", this.busoshokuImbuingExp);
      
      return nbt;
    }

    
    public void loadTargetData(CompoundNBT nbt) {
      this.uuid = nbt.getUniqueId("uuid");
      this.displayName = nbt.getString("displayName");
      this.doriki = nbt.getInt("doriki");
      this.faction = nbt.getString("faction");
      this.race = nbt.getString("race");
      this.subRace = nbt.getString("subRace");
      this.fightingStyle = nbt.getString("fightingStyle");
      this.kenbunshokuExp = nbt.getFloat("kenbunshokuExp");
      this.busoshokuHardeningExp = nbt.getFloat("busoshokuHardeningExp");
      this.busoshokuImbuingExp = nbt.getFloat("busoshokuImbuingExp");
    }
  }
}


