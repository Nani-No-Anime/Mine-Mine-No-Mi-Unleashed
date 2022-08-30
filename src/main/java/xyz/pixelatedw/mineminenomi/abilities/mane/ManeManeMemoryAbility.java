/*     */ package xyz.pixelatedw.mineminenomi.abilities.mane;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.ManeManeMemoryZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class ManeManeMemoryAbility extends PunchAbility implements IExtraUpdateData, IMorphAbility {
/*  36 */   public static final ManeManeMemoryAbility INSTANCE = new ManeManeMemoryAbility();
/*     */   
/*  38 */   private static final ManeMemory EMPTY_MEMORY = new ManeMemory();
/*     */   
/*  40 */   private List<ManeMemory> memories = new ArrayList<>();
/*     */   
/*     */   private int memoryId;
/*     */   
/*     */   public ManeManeMemoryAbility() {
/*  45 */     super("Mane Mane Memory", AbilityHelper.getDevilFruitCategory());
/*  46 */     setDescription("By hitting another player the user saves their properties.\n\n§2SHIFT-USE§r: Switches between saved targets");
/*     */     
/*  48 */     setMaxCooldown(1.0D);
/*  49 */     setStoppingAfterHit(false);
/*     */     
/*  51 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  52 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  53 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  58 */     if (player.isCrouching() && !this.memories.isEmpty()) {
/*     */       
/*  60 */       if (this.memoryId + 1 >= this.memories.size()) {
/*  61 */         this.memoryId = 0;
/*     */       } else {
/*  63 */         this.memoryId++;
/*  64 */       }  player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MANE_SWITCH_MEMORY, new Object[] { ((ManeMemory)this.memories.get(this.memoryId)).getDisplayName(), Integer.valueOf(this.memoryId + 1), Integer.valueOf(this.memories.size()) }));
/*  65 */       endContinuity(player);
/*  66 */       return false;
/*     */     } 
/*     */     
/*  69 */     if (!this.memories.isEmpty() && getMemory().isValidMemory()) {
/*     */       
/*  71 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  72 */       props.setZoanPoint(getTransformation().getForm());
/*     */       
/*  74 */       setState(Ability.State.CONTINUOUS);
/*  75 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), props), (LivingEntity)player);
/*  76 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
/*  77 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*  78 */       player.world.playMovingSound(null, (Entity)player, ModSounds.MANE_SWITCH, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  79 */       player.refreshDisplayName();
/*     */     } 
/*     */     
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/*  87 */     if (target instanceof PlayerEntity)
/*     */     {
/*  89 */       if (!this.memories.stream().anyMatch(mem -> mem.getUUID().equals(target.getUniqueID()))) {
/*     */         
/*  91 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MANE_ADDED_MEMORY, new Object[] { target.getDisplayName().getFormattedText() }));
/*  92 */         ManeMemory memory = ManeMemory.from((PlayerEntity)target);
/*  93 */         this.memories.add(memory);
/*     */       } 
/*     */     }
/*     */     
/*  97 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 102 */     if (getMemory().isValidMemory()) {
/*     */       
/* 104 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 105 */       props.setZoanPoint("");
/* 106 */       WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/* 107 */       player.refreshDisplayName();
/*     */     } 
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ManeMemory getMemory() {
/* 114 */     if (this.memories.isEmpty())
/* 115 */       return EMPTY_MEMORY; 
/* 116 */     return this.memories.get(this.memoryId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 122 */     return (ZoanInfo)ManeManeMemoryZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 128 */     return (isContinuous() && ManeManeMemoryZoanInfo.INSTANCE.isActive(entity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 134 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/* 136 */     nbt.putInt("memoryId", this.memoryId);
/*     */     
/* 138 */     ListNBT list = new ListNBT();
/* 139 */     for (ManeMemory memory : this.memories)
/*     */     {
/* 141 */       list.add(memory.saveTargetData());
/*     */     }
/* 143 */     nbt.put("memories", (INBT)list);
/*     */     
/* 145 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 151 */     this.memoryId = nbt.getInt("memoryId");
/*     */     
/* 153 */     this.memories.clear();
/* 154 */     ListNBT list = (ListNBT)nbt.get("memories");
/* 155 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 157 */       CompoundNBT memoryNBT = list.getCompound(i);
/* 158 */       ManeMemory memory = new ManeMemory();
/* 159 */       memory.loadTargetData(memoryNBT);
/* 160 */       this.memories.add(memory);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class ManeMemory
/*     */   {
/* 166 */     private UUID uuid = ModValues.NIL_UUID;
/* 167 */     private String displayName = "";
/*     */     private int doriki;
/* 169 */     private String faction = ""; private String race = ""; private String subRace = ""; private String fightingStyle = ""; private float kenbunshokuExp;
/*     */     private float busoshokuHardeningExp;
/*     */     private float busoshokuImbuingExp;
/*     */     
/*     */     public static ManeMemory from(PlayerEntity target) {
/* 174 */       ManeMemory memory = new ManeMemory();
/*     */       
/* 176 */       IEntityStats stats = EntityStatsCapability.get((LivingEntity)target);
/* 177 */       IHakiData hakiData = HakiDataCapability.get((LivingEntity)target);
/*     */       
/* 179 */       memory.uuid = target.getUniqueID();
/* 180 */       memory.displayName = target.getDisplayName().getFormattedText();
/* 181 */       memory.doriki = stats.getDoriki();
/* 182 */       memory.faction = stats.getFaction();
/* 183 */       memory.race = stats.getRace();
/* 184 */       memory.subRace = stats.getSubRace();
/* 185 */       memory.fightingStyle = stats.getFightingStyle();
/* 186 */       memory.kenbunshokuExp = hakiData.getKenbunshokuHakiExp();
/* 187 */       memory.busoshokuHardeningExp = hakiData.getBusoshokuHardeningHakiExp();
/* 188 */       memory.busoshokuImbuingExp = hakiData.getBusoshokuImbuingHakiExp();
/*     */       
/* 190 */       return memory;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isValidMemory() {
/* 195 */       return !this.uuid.equals(ModValues.NIL_UUID);
/*     */     }
/*     */ 
/*     */     
/*     */     public UUID getUUID() {
/* 200 */       return this.uuid;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDisplayName() {
/* 205 */       return this.displayName;
/*     */     }
/*     */ 
/*     */     
/*     */     public CompoundNBT saveTargetData() {
/* 210 */       CompoundNBT nbt = new CompoundNBT();
/*     */       
/* 212 */       nbt.putUniqueId("uuid", this.uuid);
/* 213 */       nbt.putString("displayName", this.displayName);
/* 214 */       nbt.putInt("doriki", this.doriki);
/* 215 */       nbt.putString("faction", this.faction);
/* 216 */       nbt.putString("race", this.race);
/* 217 */       nbt.putString("subRace", this.subRace);
/* 218 */       nbt.putString("fightingStyle", this.fightingStyle);
/* 219 */       nbt.putFloat("kenbunshokuExp", this.kenbunshokuExp);
/* 220 */       nbt.putFloat("busoshokuHardeningExp", this.busoshokuHardeningExp);
/* 221 */       nbt.putFloat("busoshokuImbuingExp", this.busoshokuImbuingExp);
/*     */       
/* 223 */       return nbt;
/*     */     }
/*     */ 
/*     */     
/*     */     public void loadTargetData(CompoundNBT nbt) {
/* 228 */       this.uuid = nbt.getUniqueId("uuid");
/* 229 */       this.displayName = nbt.getString("displayName");
/* 230 */       this.doriki = nbt.getInt("doriki");
/* 231 */       this.faction = nbt.getString("faction");
/* 232 */       this.race = nbt.getString("race");
/* 233 */       this.subRace = nbt.getString("subRace");
/* 234 */       this.fightingStyle = nbt.getString("fightingStyle");
/* 235 */       this.kenbunshokuExp = nbt.getFloat("kenbunshokuExp");
/* 236 */       this.busoshokuHardeningExp = nbt.getFloat("busoshokuHardeningExp");
/* 237 */       this.busoshokuImbuingExp = nbt.getFloat("busoshokuImbuingExp");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mane\ManeManeMemoryAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */