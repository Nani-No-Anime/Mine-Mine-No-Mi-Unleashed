/*     */ package xyz.pixelatedw.mineminenomi.wypi.data.ability;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbilityDataCapability
/*     */ {
/*     */   @CapabilityInject(IAbilityData.class)
/*  33 */   public static final Capability<IAbilityData> INSTANCE = null;
/*     */ 
/*     */   
/*     */   public static void register() {
/*  37 */     CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
/*     */         {
/*     */           
/*     */           public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
/*     */           {
/*  42 */             CompoundNBT props = new CompoundNBT();
/*     */ 
/*     */             
/*     */             try {
/*  46 */               ListNBT unlockedAbilities = new ListNBT();
/*  47 */               for (int i = 0; i < instance.getUnlockedAbilities(APIConfig.AbilityCategory.ALL).size(); i++) {
/*     */                 
/*  49 */                 Ability ability = instance.<Ability>getUnlockedAbilities(APIConfig.AbilityCategory.ALL).get(i);
/*  50 */                 String name = WyHelper.getResourceName(ability.getName());
/*  51 */                 CompoundNBT nbtAbility = new CompoundNBT();
/*  52 */                 nbtAbility.putString("name", name);
/*  53 */                 nbtAbility.putString("displayName", WyHelper.isNullOrEmpty(ability.getDisplayName()) ? "" : ability.getDisplayName());
/*  54 */                 nbtAbility.putIntArray("pools", ability.getPools());
/*  55 */                 nbtAbility.putString("unlock", ability.getUnlockType().name());
/*  56 */                 if (ability instanceof PassiveAbility)
/*     */                 {
/*  58 */                   nbtAbility.putBoolean("isPaused", ((PassiveAbility)ability).isPaused());
/*     */                 }
/*  60 */                 if (ability instanceof IExtraUpdateData) {
/*     */                   
/*  62 */                   CompoundNBT nbt = ((IExtraUpdateData)ability).getExtraData();
/*  63 */                   nbtAbility.put("extraData", (INBT)nbt);
/*     */                 } 
/*  65 */                 unlockedAbilities.add(nbtAbility);
/*     */               } 
/*  67 */               props.put("unlocked_abilities", (INBT)unlockedAbilities);
/*     */               
/*  69 */               ListNBT equippedAbilities = new ListNBT();
/*  70 */               for (int j = 0; j < (instance.getEquippedAbilities()).length; j++) {
/*     */                 
/*  72 */                 Ability t = instance.getEquippedAbilities()[j];
/*  73 */                 if (t != null) {
/*     */                   
/*  75 */                   String name = WyHelper.getResourceName(t.getName());
/*  76 */                   CompoundNBT nbtAbility = new CompoundNBT();
/*  77 */                   nbtAbility.putString("name", name);
/*  78 */                   nbtAbility.putString("displayName", (t.getDisplayName() == null) ? "" : t.getDisplayName());
/*  79 */                   nbtAbility.putInt("pos", j);
/*  80 */                   nbtAbility.putString("state", t.getState().toString());
/*  81 */                   nbtAbility.putIntArray("pools", t.getPools());
/*  82 */                   nbtAbility.putDouble("cooldown", t.getCooldown());
/*  83 */                   nbtAbility.putDouble("maxCooldown", t.getMaxCooldown());
/*  84 */                   nbtAbility.putBoolean("isForced", t.isStateForced());
/*  85 */                   if (t instanceof ContinuousAbility) {
/*     */                     
/*  87 */                     nbtAbility.putDouble("continueTimer", ((ContinuousAbility)t).getContinueTime());
/*  88 */                     nbtAbility.putDouble("threshold", ((ContinuousAbility)t).getThreshold());
/*     */                   } 
/*  90 */                   if (t instanceof ChargeableAbility)
/*  91 */                     nbtAbility.putDouble("chargeTimer", ((ChargeableAbility)t).getChargeTime()); 
/*  92 */                   if (t instanceof IExtraUpdateData) {
/*     */                     
/*  94 */                     CompoundNBT nbt = ((IExtraUpdateData)t).getExtraData();
/*  95 */                     nbtAbility.put("extraData", (INBT)nbt);
/*     */                   } 
/*  97 */                   equippedAbilities.add(nbtAbility);
/*     */                 }
/*     */                 else {
/*     */                   
/* 101 */                   CompoundNBT nbtAbility = new CompoundNBT();
/* 102 */                   nbtAbility.putInt("pos", j);
/* 103 */                   equippedAbilities.add(nbtAbility);
/*     */                 } 
/*     */               } 
/* 106 */               props.put("equipped_abilities", (INBT)equippedAbilities);
/*     */               
/* 108 */               props.putInt("combat_bar_set", instance.getCombatBarSet());
/*     */             }
/* 110 */             catch (Exception ex) {
/*     */               
/* 112 */               ex.printStackTrace();
/*     */             } 
/*     */             
/* 115 */             return (INBT)props;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt) {
/* 121 */             CompoundNBT props = (CompoundNBT)nbt;
/*     */ 
/*     */             
/*     */             try {
/* 125 */               instance.clearEquippedAbilities(APIConfig.AbilityCategory.ALL);
/* 126 */               instance.clearUnlockedAbilities(APIConfig.AbilityCategory.ALL);
/*     */               
/* 128 */               ListNBT unlockedAbilities = props.getList("unlocked_abilities", 10);
/* 129 */               for (int i = 0; i < unlockedAbilities.size(); i++) {
/*     */                 
/* 131 */                 CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);
/*     */                 
/*     */                 try {
/* 134 */                   Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", nbtAbility.getString("name")))).create();
/* 135 */                   if (ability != null) {
/*     */                     
/* 137 */                     int[] pools = nbtAbility.getIntArray("pools");
/* 138 */                     ability.addInPool(pools);
/* 139 */                     AbilityUnlock unlockType = AbilityUnlock.valueOf(nbtAbility.getString("unlock"));
/* 140 */                     ability.setUnlockType(unlockType);
/* 141 */                     String displayName = nbtAbility.getString("displayName");
/* 142 */                     ability.setDisplayName(displayName);
/* 143 */                     if (ability instanceof PassiveAbility)
/*     */                     {
/* 145 */                       ((PassiveAbility)ability).setPause(nbtAbility.getBoolean("isPaused"));
/*     */                     }
/* 147 */                     if (ability instanceof IExtraUpdateData) {
/*     */                       
/* 149 */                       CompoundNBT extraData = nbtAbility.getCompound("extraData");
/* 150 */                       ((IExtraUpdateData)ability).setExtraData(extraData);
/*     */                     } 
/* 152 */                     instance.loadUnlockedAbility(ability);
/*     */                   } 
/* 154 */                 } catch (Exception e) {
/*     */                   
/* 156 */                   WyDebug.debug("Unregistered ability: " + nbtAbility.getString("name"));
/*     */                 } 
/*     */               } 
/*     */               
/* 160 */               ListNBT equippedAbilities = props.getList("equipped_abilities", 10);
/* 161 */               List<Ability> activeAbilitiesUnlocked = (List<Ability>)instance.<Ability>getUnlockedAbilities(APIConfig.AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
/* 162 */               for (int j = 0; j < equippedAbilities.size(); j++) {
/*     */                 
/* 164 */                 CompoundNBT nbtAbility = equippedAbilities.getCompound(j);
/* 165 */                 Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.projectId, nbtAbility.getString("name")));
/* 166 */                 if (ability != null) {
/*     */                   
/* 168 */                   for (Ability abl : activeAbilitiesUnlocked) {
/*     */                     
/* 170 */                     if (abl.equals(ability))
/*     */                     {
/* 172 */                       Ability.State state = Ability.State.valueOf(nbtAbility.getString("state"));
/* 173 */                       int cooldown = (int)(nbtAbility.getDouble("cooldown") / 20.0D);
/* 174 */                       int maxCooldown = (int)(nbtAbility.getDouble("maxCooldown") / 20.0D);
/* 175 */                       int pos = nbtAbility.getInt("pos");
/* 176 */                       int[] pools = nbtAbility.getIntArray("pools");
/* 177 */                       String displayName = nbtAbility.getString("displayName");
/* 178 */                       boolean isForced = nbtAbility.getBoolean("isForced");
/* 179 */                       if (state == null)
/* 180 */                         state = Ability.State.STANDBY; 
/* 181 */                       abl.setState(state);
/* 182 */                       abl.setMaxCooldown(maxCooldown);
/* 183 */                       abl.setCooldown(cooldown);
/* 184 */                       abl.addInPool(pools);
/* 185 */                       abl.setDisplayName(displayName);
/* 186 */                       abl.setForcedState(isForced);
/* 187 */                       if (abl instanceof ContinuousAbility) {
/*     */                         
/* 189 */                         int continueTime = (int)(nbtAbility.getDouble("continueTime") / 20.0D);
/* 190 */                         ((ContinuousAbility)abl).setContinueTime(continueTime);
/* 191 */                         int threshold = (int)(nbtAbility.getDouble("threshold") / 20.0D);
/* 192 */                         ((ContinuousAbility)abl).setThreshold(threshold);
/*     */                       } 
/* 194 */                       if (abl instanceof ChargeableAbility) {
/*     */                         
/* 196 */                         int chargeTime = (int)(nbtAbility.getDouble("chargeTime") / 20.0D);
/* 197 */                         ((ChargeableAbility)abl).setChargeTime(chargeTime);
/*     */                       } 
/* 199 */                       if (abl instanceof IExtraUpdateData) {
/*     */                         
/* 201 */                         CompoundNBT extraData = nbtAbility.getCompound("extraData");
/* 202 */                         ((IExtraUpdateData)abl).setExtraData(extraData);
/*     */                       } 
/*     */                       
/* 205 */                       instance.setEquippedAbility(pos, abl);
/*     */                     }
/*     */                   
/*     */                   } 
/* 209 */                 } else if (ability == null) {
/*     */                   
/* 211 */                   int pos = nbtAbility.getInt("pos");
/* 212 */                   instance.setEquippedAbility(pos, null);
/*     */                 } 
/*     */               } 
/*     */               
/* 216 */               instance.setCombatBarSet(props.getInt("combat_bar_set"));
/*     */             }
/* 218 */             catch (Exception ex) {
/*     */               
/* 220 */               ex.printStackTrace();
/*     */             } 
/*     */           }
/*     */         }, AbilityDataBase::new );
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IAbilityData get(LivingEntity entity) {
/* 229 */     return (IAbilityData)entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
/*     */   }
/*     */ 
/*     */   
/*     */   public static LazyOptional<IAbilityData> getLazy(LivingEntity entity) {
/* 234 */     return entity.getCapability(INSTANCE, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\data\ability\AbilityDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */