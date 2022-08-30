/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.commands.FGCommand;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public abstract class Ability
/*     */   extends ForgeRegistryEntry<Ability> {
/*  40 */   private String name = "";
/*     */   private String displayName;
/*  42 */   private String textureName = "";
/*     */   private ITextComponent tooltip;
/*     */   protected double cooldown;
/*     */   protected double maxCooldown;
/*     */   protected double disableTicks;
/*  47 */   protected double maxDisableTicks = 200.0D;
/*  48 */   private APIConfig.AbilityCategory category = APIConfig.AbilityCategory.ALL;
/*  49 */   private AbilityUnlock unlock = AbilityUnlock.PROGRESSION;
/*  50 */   private State state = State.STANDBY;
/*  51 */   private State previousState = State.STANDBY;
/*     */   private boolean hideInGUI = false;
/*     */   private boolean forcedState = false;
/*     */   @Deprecated
/*     */   private boolean needsClientSide = false;
/*  56 */   private int[] pools = new int[0];
/*  57 */   protected double timeProgression = 1.0D;
/*     */   
/*  59 */   protected final Random random = new Random(); protected IOnUse onUseEvent = player -> true;
/*     */   protected IDuringCooldown duringCooldownEvent = (player, cooldown) -> {
/*     */     
/*     */     };
/*     */   protected IOnEndCooldown onEndCooldownEvent = player -> {
/*     */     
/*     */     };
/*     */   
/*     */   public Ability(String name, APIConfig.AbilityCategory category) {
/*  68 */     this.name = name;
/*  69 */     this.category = category;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  78 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/*  81 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/*     */     
/*  83 */     if (isOnCooldown() && getCooldown() <= 10.0D) {
/*  84 */       stopCooldown(player);
/*     */     }
/*  86 */     if (!isOnStandby()) {
/*     */       return;
/*     */     }
/*  89 */     AbilityUseEvent event = new AbilityUseEvent(player, this);
/*  90 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */       return;
/*     */     }
/*  93 */     if (!isStateForced() && this.onUseEvent.onUse(player)) {
/*     */       
/*  95 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  96 */       checkAbilityPool(player, State.COOLDOWN);
/*     */       
/*  98 */       if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/*  99 */         WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
/*     */       }
/* 101 */       startCooldown(player);
/* 102 */       props.setPreviouslyUsedAbility(this);
/* 103 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */     } 
/* 105 */     player.world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnStandby() {
/* 114 */     return (this.state == State.STANDBY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnCooldown() {
/* 119 */     return (this.state == State.COOLDOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassiveEnabled() {
/* 124 */     return (this.state == State.PASSIVE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isContinuous() {
/* 129 */     return (this.state == State.CONTINUOUS && !isStateForced());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCharging() {
/* 134 */     return (this.state == State.CHARGING && !isStateForced());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDisabled() {
/* 139 */     return (this.state == State.DISABLED);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startStandby() {
/* 144 */     this.previousState = this.state;
/* 145 */     this.state = State.STANDBY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDisable() {
/* 150 */     startDisable(20);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDisable(int ticks) {
/* 155 */     this.previousState = this.state;
/* 156 */     this.state = State.DISABLED;
/* 157 */     this.maxDisableTicks = ticks;
/* 158 */     this.disableTicks = this.maxDisableTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDisableTicks() {
/* 163 */     return this.disableTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisableTicks(double ticks) {
/* 168 */     this.disableTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCooldown(PlayerEntity player) {
/* 173 */     this.previousState = this.state;
/* 174 */     this.state = State.COOLDOWN;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopCooldown(PlayerEntity player) {
/* 179 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 182 */     checkAbilityPool(player, State.STANDBY);
/*     */     
/* 184 */     this.cooldown = this.maxCooldown;
/* 185 */     this.previousState = this.state;
/* 186 */     this.state = State.STANDBY;
/* 187 */     if (!isStateForced()) {
/*     */       
/* 189 */       this.onEndCooldownEvent.onEndCooldown(player);
/* 190 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */     } 
/* 192 */     setForcedState(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setState(State state) {
/* 197 */     this.previousState = this.state;
/* 198 */     this.state = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public State getState() {
/* 203 */     return this.state;
/*     */   }
/*     */ 
/*     */   
/*     */   public State getPreviousState() {
/* 208 */     return this.previousState;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForcedState(boolean flag) {
/* 213 */     this.forcedState = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStateForced() {
/* 218 */     return this.forcedState;
/*     */   }
/*     */ 
/*     */   
/*     */   public void hideInGUI(boolean flag) {
/* 223 */     this.hideInGUI = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHideInGUI() {
/* 228 */     return this.hideInGUI;
/*     */   }
/*     */ 
/*     */   
/*     */   public void needsClientSide() {
/* 233 */     this.needsClientSide = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isClientSide() {
/* 238 */     return this.needsClientSide;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInPool(AbilityPool... pools) {
/* 243 */     int[] intPools = Arrays.<AbilityPool>stream(pools).mapToInt(AbilityPool::id).toArray();
/* 244 */     addInPool(intPools);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addInPool(int... pools) {
/* 249 */     this.pools = pools;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getPools() {
/* 254 */     return this.pools;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInPool() {
/* 259 */     return ((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue() ? ((this.pools != null && this.pools.length > 0)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInPool(AbilityPool pool) {
/* 264 */     return isInPool(pool.id());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInPool(int poolId) {
/* 269 */     if (!((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue()) {
/* 270 */       return false;
/*     */     }
/* 272 */     return Arrays.stream(this.pools).anyMatch(i -> (poolId == i));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sharesPoolWith(Ability ability) {
/* 277 */     if (!((Boolean)CommonConfig.INSTANCE.sharedCooldowns.get()).booleanValue()) {
/* 278 */       return false;
/*     */     }
/* 280 */     boolean flag = false;
/* 281 */     for (int pool : ability.getPools()) {
/*     */       
/* 283 */       if (isInPool(pool)) {
/*     */         
/* 285 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 290 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxCooldown(double cooldown) {
/* 299 */     this.maxCooldown = cooldown * 20.0D;
/* 300 */     this.cooldown = this.maxCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxCooldown() {
/* 305 */     return this.maxCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCooldown(double cooldown) {
/* 310 */     this.cooldown = cooldown * 20.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCooldown() {
/* 315 */     return this.cooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeProgression(double timeScale) {
/* 320 */     this.timeProgression = timeScale;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTimeProgression() {
/* 325 */     return this.timeProgression;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(String desc) {
/* 330 */     setDescription((ITextComponent)new StringTextComponent(desc));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDescription(ITextComponent tooltip) {
/* 335 */     this.tooltip = tooltip;
/*     */     
/* 337 */     if (this instanceof IFormRequiredAbility) {
/* 338 */       this.tooltip.appendSibling(((IFormRequiredAbility)this).addFormRequirement());
/*     */     }
/*     */   }
/*     */   
/*     */   public ITextComponent getDescription() {
/* 343 */     return this.tooltip;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 348 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getI18nKey() {
/* 353 */     String resourceName = WyHelper.getResourceName(getName());
/* 354 */     return "ability." + APIConfig.projectId + "." + resourceName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/* 359 */     return !WyHelper.isNullOrEmpty(this.displayName) ? this.displayName : getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayName(String name) {
/* 364 */     this.displayName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomTexture() {
/* 369 */     return !WyHelper.isNullOrEmpty(this.textureName);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomTexture() {
/* 374 */     return this.textureName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomTexture(String texture) {
/* 379 */     this.textureName = WyHelper.getResourceName(texture);
/*     */   }
/*     */ 
/*     */   
/*     */   public APIConfig.AbilityCategory getCategory() {
/* 384 */     return this.category;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnlockType(AbilityUnlock unlockType) {
/* 389 */     this.unlock = unlockType;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbilityUnlock getUnlockType() {
/* 394 */     return this.unlock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableTick(PlayerEntity player) {
/* 403 */     if (isDisabled() && this.disableTicks > 0.0D)
/*     */     {
/* 405 */       this.disableTicks--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cooldown(PlayerEntity player) {
/* 414 */     if (WyPatreon.isDevBuild() && FGCommand.NO_COOLDOWN)
/*     */     {
/* 416 */       stopCooldown(player);
/*     */     }
/*     */     
/* 419 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/*     */     
/* 421 */     if (isOnCooldown() && this.cooldown > 0.0D) {
/*     */       
/* 423 */       this.cooldown -= 1.0D * getTimeProgression();
/* 424 */       if (!player.world.isRemote && getPreviousState() != State.DISABLED && !isStateForced()) {
/* 425 */         this.duringCooldownEvent.duringCooldown(player, (int)this.cooldown);
/*     */       }
/* 427 */     } else if (isOnCooldown() && this.cooldown <= 0.0D) {
/*     */       
/* 429 */       stopCooldown(player);
/*     */     } 
/*     */     
/* 432 */     player.world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkAbilityPool(PlayerEntity player, State state) {
/* 437 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 438 */     if (isInPool() && !isStateForced())
/*     */     {
/* 440 */       for (Ability abl : props.getEquippedAbilities()) {
/*     */         
/* 442 */         if (abl != null && abl != this && abl.isInPool() && abl.sharesPoolWith(this)) {
/*     */           
/* 444 */           double[] values = null;
/* 445 */           boolean forced = true;
/*     */           
/* 447 */           if (state == State.COOLDOWN) {
/* 448 */             values = new double[] { getCooldown() / 20.0D, getCooldown() / 20.0D };
/* 449 */           } else if (state == State.CHARGING && abl instanceof ChargeableAbility && this instanceof ChargeableAbility) {
/* 450 */             values = new double[] { (((ChargeableAbility)this).getChargeTime() / 20), (((ChargeableAbility)this).getMaxChargeTime() / 20) };
/* 451 */           } else if (state == State.CONTINUOUS && abl instanceof ContinuousAbility && this instanceof ContinuousAbility) {
/* 452 */             values = new double[] { (((ContinuousAbility)this).getContinueTime() / 20), (((ContinuousAbility)this).getThreshold() / 20) };
/* 453 */           } else if (state == State.STANDBY) {
/* 454 */             forced = false;
/*     */           } 
/* 456 */           abl.previousState = getState();
/* 457 */           abl.state = state;
/*     */           
/* 459 */           abl.forcedState = forced;
/*     */           
/* 461 */           if (values != null) {
/* 462 */             WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl, state, values), (LivingEntity)player);
/*     */           } else {
/* 464 */             WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, abl), (LivingEntity)player);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCooldownPercentage() {
/* 475 */     return this.cooldown / this.maxCooldown * 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getInvertedCooldownPercentage() {
/* 483 */     return (1.0D - this.cooldown / this.maxCooldown) * 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object abl) {
/* 489 */     if (!(abl instanceof Ability)) {
/* 490 */       return false;
/*     */     }
/* 492 */     return getName().equalsIgnoreCase(((Ability)abl).getName());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Ability create() {
/*     */     try {
/* 500 */       return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */     }
/* 502 */     catch (Exception ex) {
/*     */       
/* 504 */       System.out.println("Exception raised for " + getDisplayName());
/* 505 */       ex.printStackTrace();
/*     */       
/* 507 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Ability deepClone(Ability abl) {
/* 512 */     Ability newAbility = abl.create();
/*     */ 
/*     */     
/* 515 */     newAbility.unlock = abl.getUnlockType();
/*     */     
/* 517 */     return newAbility;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Ability get(ResourceLocation res) {
/* 523 */     Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(res);
/* 524 */     return ability;
/*     */   } public static interface IOnEndCooldown extends Serializable {
/*     */     void onEndCooldown(PlayerEntity param1PlayerEntity); } public static interface IDuringCooldown extends Serializable {
/*     */     void duringCooldown(PlayerEntity param1PlayerEntity, int param1Int); }
/*     */   public boolean canUse(PlayerEntity player) {
/* 529 */     ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*     */ 
/*     */     
/* 532 */     if (worldData.isInsideRestrictedArea(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())) {
/*     */       
/* 534 */       boolean isWhitelsited = CommonConfig.INSTANCE.getProtectionWhitelistedAbilities().contains(this);
/* 535 */       if (!isWhitelsited) {
/*     */         
/* 537 */         if (!(this instanceof PassiveAbility))
/* 538 */           player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_HERE, new Object[0])); 
/* 539 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 544 */     if (this instanceof IFormRequiredAbility) {
/*     */       
/* 546 */       int forms = (((IFormRequiredAbility)this).getRequiredForms(player)).length;
/* 547 */       if (forms > 0) {
/*     */         
/* 549 */         boolean flag = false;
/* 550 */         List<String> names = (List<String>)Arrays.<ZoanInfo>stream(((IFormRequiredAbility)this).getRequiredForms(player)).map(zoan -> zoan.getDisplayName()).collect(Collectors.toList());
/* 551 */         for (ZoanInfo info : ((IFormRequiredAbility)this).getRequiredForms(player)) {
/*     */           
/* 553 */           if (info.isActive((LivingEntity)player)) {
/*     */             
/* 555 */             flag = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 560 */         if (!flag) {
/*     */           
/* 562 */           if (!(this instanceof PassiveAbility)) {
/*     */             
/* 564 */             if (forms == 1)
/* 565 */               player.sendStatusMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, new Object[] { getName(), names.get(0) }), false); 
/* 566 */             if (forms >= 2)
/* 567 */               player.sendStatusMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, new Object[] { getName(), names.get(0), names.get(1) }), false); 
/*     */           } 
/* 569 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 574 */     return true;
/*     */   }
/*     */   
/*     */   public static interface IOnUse
/*     */     extends Serializable {
/*     */     boolean onUse(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public enum State {
/* 583 */     STANDBY, DISABLED,
/*     */     
/* 585 */     COOLDOWN, PASSIVE, CONTINUOUS, CHARGING;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */