/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.item.minecart.HopperMinecartEntity;
/*     */ import net.minecraft.entity.passive.FoxEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Foods;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.IFruitColor;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.FruitType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.EatDevilFruitEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.DFItemEntity;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AkumaNoMiItem
/*     */   extends Item
/*     */   implements IFruitColor
/*     */ {
/*     */   private String name;
/*     */   private int tier;
/*     */   public FruitType type;
/*     */   public Ability[] abilities;
/*     */   private static final int GENERIC_FRUIT_VARIATIONS = 10;
/*  76 */   private static final String[] STEM_COLORS = new String[] { "#ccc774", "#8a5216", "#025f00", "#aecd6d", "#f56fe3", "#f93434" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IItemPropertyGetter typeProperty;
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
/*     */   public AkumaNoMiItem(String name, int tier, FruitType type, Ability... abilitiesArray) {
/*  98 */     super((new Item.Properties()).group(ModCreativeTabs.DEVIL_FRUITS).maxStackSize(1).food(Foods.APPLE)); this.typeProperty = ((itemStack, world, livingEntity) -> { if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) { removeBaseColor(itemStack); removeStemColor(itemStack); return -1.0F; }  if (world == null && livingEntity != null) { world = livingEntity.world; } else if (world == null && livingEntity == null) { return 1.0F; }  applyRandomness(world, itemStack); return itemStack.getOrCreateTag().getInt("type");
/*  99 */       }); this.name = name;
/* 100 */     this.type = type;
/* 101 */     this.abilities = abilitiesArray;
/* 102 */     addPropertyOverride(new ResourceLocation("type"), this.typeProperty);
/*     */     
/* 104 */     this.tier = tier;
/*     */     
/* 106 */     if (this.type == FruitType.LOGIA)
/* 107 */       ModValues.logias.add(this); 
/* 108 */     ModValues.devilfruits.add(this);
/* 109 */     AbilityCommandGroup.create(this.name.toUpperCase().replaceAll("[\\'\\:\\-\\,\\#]", "").replaceAll(" ", "_"), () -> this.abilities);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 115 */     player.setActiveHand(hand);
/* 116 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity) {
/* 122 */     if (!(livingEntity instanceof PlayerEntity)) {
/* 123 */       return itemStack;
/*     */     }
/* 125 */     PlayerEntity player = (PlayerEntity)livingEntity;
/*     */     
/* 127 */     EatDevilFruitEvent.Pre preEvent = new EatDevilFruitEvent.Pre(player, itemStack);
/* 128 */     if (MinecraftForge.EVENT_BUS.post((Event)preEvent)) {
/* 129 */       return itemStack;
/*     */     }
/* 131 */     if (!player.world.isRemote) {
/*     */       
/* 133 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 134 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 135 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/* 136 */       ExtendedWorldData worldData = ExtendedWorldData.get(world);
/*     */       
/* 138 */       AkumaNoMiItem eatenItem = (AkumaNoMiItem)itemStack.getItem();
/* 139 */       String eatenFruit = DevilFruitHelper.getDevilFruitKey(eatenItem);
/*     */       
/* 141 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && worldData.getAteFruits().containsValue(eatenFruit)) {
/*     */         
/* 143 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_FRUIT_ALREADY_USED, new Object[0]));
/* 144 */         itemStack.shrink(1);
/* 145 */         return itemStack;
/*     */       } 
/*     */       
/* 148 */       boolean hasFruit = !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit());
/* 149 */       boolean hasYami = devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI);
/*     */       
/* 151 */       if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/*     */         
/* 153 */         AkumaNoMiItem randomFruit = eatenItem.getShiftedFruit(world);
/* 154 */         String randomFruitKey = DevilFruitHelper.getDevilFruitKey(randomFruit);
/* 155 */         eatenItem = randomFruit;
/* 156 */         eatenFruit = randomFruitKey;
/*     */       } 
/*     */       
/* 159 */       if (!CommonConfig.INSTANCE.isYamiPowerEnabled() && hasFruit) {
/*     */         
/* 161 */         applyCurseDeath(player);
/* 162 */         itemStack.shrink(1);
/* 163 */         return itemStack;
/*     */       } 
/*     */       
/* 166 */       if (CommonConfig.INSTANCE.isYamiPowerEnabled()) {
/*     */ 
/*     */ 
/*     */         
/* 170 */         if (hasFruit && eatenItem != ModAbilities.YAMI_YAMI_NO_MI && !hasYami) {
/*     */           
/* 172 */           applyCurseDeath(player);
/* 173 */           itemStack.shrink(1);
/* 174 */           worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
/* 175 */           worldData.removeDevilFruitInWorld(eatenFruit);
/* 176 */           return itemStack;
/*     */         } 
/*     */ 
/*     */         
/* 180 */         if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI && hasYami) {
/*     */           
/* 182 */           applyCurseDeath(player);
/* 183 */           itemStack.shrink(1);
/* 184 */           worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
/* 185 */           worldData.removeDevilFruitInWorld(eatenFruit);
/* 186 */           return itemStack;
/*     */         } 
/*     */ 
/*     */         
/* 190 */         if (hasFruit && !devilFruitProps.getDevilFruit().equalsIgnoreCase("yami_yami") && devilFruitProps.hasYamiPower()) {
/*     */           
/* 192 */           applyCurseDeath(player);
/* 193 */           itemStack.shrink(1);
/* 194 */           worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
/* 195 */           worldData.removeDevilFruitInWorld(eatenFruit);
/* 196 */           return itemStack;
/*     */         } 
/*     */       } 
/*     */       
/* 200 */       if (this.type == FruitType.LOGIA) {
/* 201 */         devilFruitProps.setLogia(true);
/*     */       }
/*     */       
/* 204 */       if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI) {
/*     */ 
/*     */         
/* 207 */         devilFruitProps.setLogia(false);
/* 208 */         if (CommonConfig.INSTANCE.isYamiPowerEnabled()) {
/* 209 */           devilFruitProps.setYamiPower(true);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 214 */       else if (devilFruitProps.hasYamiPower()) {
/*     */         
/* 216 */         devilFruitProps.setLogia(false);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 221 */       if (eatenItem == ModAbilities.HITO_HITO_NO_MI) {
/*     */         
/* 223 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_ENLIGHTENMENT, new Object[0]));
/* 224 */         entityStatsProps.setRace("human");
/*     */         
/* 226 */         AbilityHelper.validateStyleMoves(player);
/* 227 */         AbilityHelper.validateRacialMoves(player);
/*     */         
/* 229 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
/*     */       } 
/*     */ 
/*     */       
/* 233 */       if (eatenItem != ModAbilities.YOMI_YOMI_NO_MI) {
/*     */         
/* 235 */         for (Ability a : eatenItem.abilities) {
/*     */           
/* 237 */           if (!AbilityHelper.verifyIfAbilityIsBanned(a) && abilityDataProps.getUnlockedAbility(a) == null)
/*     */           {
/* 239 */             abilityDataProps.addUnlockedAbility(a);
/*     */           }
/*     */         } 
/*     */         
/* 243 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
/* 244 */         WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
/*     */       } 
/*     */       
/* 247 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */         
/* 249 */         worldData.addAteDevilFruit(player, this);
/* 250 */         worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
/*     */       } 
/*     */ 
/*     */       
/* 254 */       if (!hasFruit || (hasYami && CommonConfig.INSTANCE.isYamiPowerEnabled())) {
/*     */         
/* 256 */         DFEncyclopediaEntry elements = eatenItem.getRandomElements(world);
/* 257 */         ItemsHelper.updateEncyclopediae(player, eatenFruit, elements);
/*     */         
/* 259 */         devilFruitProps.setDevilFruit(eatenFruit);
/* 260 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
/*     */       } 
/*     */       
/* 263 */       player.getFoodStats().addStats(12, 4.0F);
/*     */     } 
/*     */     
/* 266 */     EatDevilFruitEvent.Post postEvent = new EatDevilFruitEvent.Post(player, itemStack);
/* 267 */     MinecraftForge.EVENT_BUS.post((Event)postEvent);
/*     */     
/* 269 */     itemStack.shrink(1);
/*     */     
/* 271 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
/* 277 */     if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */       
/* 279 */       for (int i = 0; i < this.abilities.length; i++) {
/* 280 */         if (!AbilityHelper.verifyIfAbilityIsBanned(this.abilities[i]) && this.abilities[i] != null && !(this.abilities[i] instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
/* 281 */           list.add(new StringTextComponent(TextFormatting.GRAY + I18n.format("ability.mineminenomi." + WyHelper.getResourceName(this.abilities[i].getName()), new Object[0]))); 
/*     */       } 
/* 283 */       list.add(new StringTextComponent(""));
/* 284 */       list.add(new StringTextComponent(this.type.getColor() + this.type.getName()));
/*     */     }
/*     */     else {
/*     */       
/* 288 */       list.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomEntity(ItemStack stack) {
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity createEntity(World world, Entity entity, ItemStack itemStack) {
/* 301 */     if (((ItemEntity)entity).getThrowerId() == null) {
/* 302 */       return null;
/*     */     }
/* 304 */     applyRandomness(world, itemStack);
/*     */     
/* 306 */     DFItemEntity newEntity = new DFItemEntity(world, (entity.getPositionVec()).x, (entity.getPositionVec()).y, (entity.getPositionVec()).z, itemStack);
/* 307 */     newEntity.setPickupDelay(40);
/* 308 */     newEntity.setMotion(entity.getMotion());
/* 309 */     return (Entity)newEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entity) {
/* 315 */     if (entity.world.isRemote) {
/* 316 */       return false;
/*     */     }
/* 318 */     ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
/*     */     
/* 320 */     boolean chunkExistsCheck = !entity.world.chunkExists(entity.getPosition().getX() >> 4, entity.getPosition().getZ() >> 4);
/* 321 */     boolean entityBurningCheck = entity.isBurning();
/* 322 */     boolean entityInVoidCheck = (entity.getPosition().getY() < -1);
/*     */     
/* 324 */     if (chunkExistsCheck || entityBurningCheck || entityInVoidCheck) {
/*     */       
/* 326 */       entity.remove();
/* 327 */       worldData.removeDevilFruitInWorld(this);
/*     */     } 
/*     */     
/* 330 */     boolean shouldRemove = false;
/*     */     
/* 332 */     List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(entity.getPosition(), entity.world, 2);
/*     */     
/* 334 */     for (BlockPos pos : blockPosList) {
/*     */       
/* 336 */       TileEntity te = entity.world.getTileEntity(pos);
/*     */       
/* 338 */       if (te instanceof net.minecraft.tileentity.HopperTileEntity) {
/*     */         
/* 340 */         shouldRemove = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 345 */     List<Entity> hopperMinecarts = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 0.5D, new Class[] { HopperMinecartEntity.class });
/*     */     
/* 347 */     if (hopperMinecarts.size() > 0) {
/* 348 */       shouldRemove = true;
/*     */     }
/* 350 */     List<Entity> foxes = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 1.5D, new Class[] { FoxEntity.class });
/*     */     
/* 352 */     if (foxes.size() > 0) {
/* 353 */       shouldRemove = true;
/*     */     }
/* 355 */     if (shouldRemove) {
/*     */       
/* 357 */       entity.remove();
/*     */       
/* 359 */       if (entity.getThrowerId() != null) {
/*     */         
/* 361 */         PlayerEntity thrower = entity.world.getPlayerByUuid(entity.getThrowerId());
/* 362 */         if (thrower != null) {
/* 363 */           thrower.inventory.addItemStackToInventory(itemStack);
/*     */         } else {
/* 365 */           worldData.removeDevilFruitInWorld(this);
/*     */         } 
/*     */       } else {
/* 368 */         worldData.removeDevilFruitInWorld(this);
/*     */       } 
/*     */     } 
/* 371 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyRandomness(World world, ItemStack itemStack) {
/* 376 */     if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT)
/*     */     {
/* 378 */       if (!hasBaseColor(itemStack) || !hasStemColor(itemStack) || RandomFruitEvents.Client.DIRTY) {
/*     */         
/* 380 */         removeBaseColor(itemStack);
/* 381 */         removeStemColor(itemStack);
/* 382 */         AkumaNoMiItem randomFruit = getShiftedFruit(world);
/* 383 */         DFEncyclopediaEntry randomElements = randomFruit.getRandomElements(world);
/* 384 */         setBaseColor(itemStack, ((Color)randomElements.getBaseColor().get()).getRGB());
/* 385 */         setStemColor(itemStack, ((Color)randomElements.getStemColor().get()).getRGB());
/* 386 */         int type = ((Integer)randomElements.getShape().get()).intValue();
/* 387 */         itemStack.getOrCreateTag().putInt("type", type);
/* 388 */         itemStack.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT, new Object[0]));
/* 389 */         RandomFruitEvents.Client.DIRTY = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public DFEncyclopediaEntry getRandomElements(World world) {
/* 396 */     long seed = world.getSeed() + getTranslationKey().hashCode();
/* 397 */     if (world.isRemote)
/* 398 */       seed = ((Long)RandomFruitEvents.Client.FRUIT_SEEDS.get(Integer.valueOf(getTranslationKey().hashCode()))).longValue(); 
/* 399 */     Random rand = new Random(seed);
/*     */     
/* 401 */     float red = rand.nextFloat();
/* 402 */     float green = rand.nextFloat();
/* 403 */     float blue = rand.nextFloat();
/* 404 */     Optional<Color> baseColor = Optional.of(new Color(red, green, blue));
/*     */     
/* 406 */     String hexStemColor = STEM_COLORS[rand.nextInt(STEM_COLORS.length)];
/* 407 */     Optional<Color> stemColor = Optional.of(WyHelper.hexToRGB(hexStemColor));
/*     */     
/* 409 */     Optional<Integer> type = Optional.of(Integer.valueOf(rand.nextInt(10) + 1));
/*     */     
/* 411 */     return DFEncyclopediaEntry.of(type, baseColor, stemColor);
/*     */   }
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getShiftedFruit(World world) {
/* 416 */     int seed = Math.max(1, (int)(world.getSeed() % 10L));
/* 417 */     int pos = ModValues.devilfruits.indexOf(this);
/* 418 */     int shifted = (pos + seed) % ModValues.devilfruits.size();
/* 419 */     AkumaNoMiItem randomFruit = ModValues.devilfruits.get(shifted);
/*     */ 
/*     */ 
/*     */     
/* 423 */     return randomFruit;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AkumaNoMiItem getReverseShiftedFruit(World world) {
/* 429 */     for (AkumaNoMiItem fruit : ModValues.devilfruits) {
/*     */       
/* 431 */       AkumaNoMiItem shiftedFruit = fruit.getShiftedFruit(world);
/* 432 */       if (shiftedFruit == this) {
/* 433 */         return fruit;
/*     */       }
/*     */     } 
/* 436 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void applyCurseDeath(PlayerEntity player) {
/* 441 */     player.attackEntityFrom(ModDamageSource.DEVILS_CURSE, player.getMaxHealth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTier() {
/* 446 */     return this.tier;
/*     */   }
/*     */ 
/*     */   
/*     */   public FruitType getType() {
/* 451 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDevilFruitName() {
/* 456 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\AkumaNoMiItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */