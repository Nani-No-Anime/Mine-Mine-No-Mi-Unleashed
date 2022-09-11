package xyz.pixelatedw.mineminenomi.items;

import java.awt.Color;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.minecart.HopperMinecartEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.api.IFruitColor;
import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
import xyz.pixelatedw.mineminenomi.api.enums.FruitType;
import xyz.pixelatedw.mineminenomi.api.events.EatDevilFruitEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.DFItemEntity;
import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;





public class AkumaNoMiItem
  extends Item
  implements IFruitColor
{
  private String name;
  private int tier;
  public FruitType type;
  public Ability[] abilities;
  private static final int GENERIC_FRUIT_VARIATIONS = 10;
  private static final String[] STEM_COLORS = new String[] { "#ccc774", "#8a5216", "#025f00", "#aecd6d", "#f56fe3", "#f93434" };








  
  private IItemPropertyGetter typeProperty;









  
  public AkumaNoMiItem(String name, int tier, FruitType type, Ability... abilitiesArray) {
    super((new Item.Properties()).group(ModCreativeTabs.DEVIL_FRUITS).maxStackSize(1).food(Foods.APPLE)); this.typeProperty = ((itemStack, world, livingEntity) -> { if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) { removeBaseColor(itemStack); removeStemColor(itemStack); return -1.0F; }  if (world == null && livingEntity != null) { world = livingEntity.world; } else if (world == null && livingEntity == null) { return 1.0F; }  applyRandomness(world, itemStack); return itemStack.getOrCreateTag().getInt("type");
      }); this.name = name;
    this.type = type;
    this.abilities = abilitiesArray;
    addPropertyOverride(new ResourceLocation("type"), this.typeProperty);
    
    this.tier = tier;
    
    if (this.type == FruitType.LOGIA)
      ModValues.logias.add(this); 
    ModValues.devilfruits.add(this);
    AbilityCommandGroup.create(this.name.toUpperCase().replaceAll("[\\'\\:\\-\\,\\#]", "").replaceAll(" ", "_"), () -> this.abilities);
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    player.setActiveHand(hand);
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity) {
    if (!(livingEntity instanceof PlayerEntity)) {
      return itemStack;
    }
    PlayerEntity player = (PlayerEntity)livingEntity;
    
    EatDevilFruitEvent.Pre preEvent = new EatDevilFruitEvent.Pre(player, itemStack);
    if (MinecraftForge.EVENT_BUS.post((Event)preEvent)) {
      return itemStack;
    }
    if (!player.world.isRemote) {
      
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
      ExtendedWorldData worldData = ExtendedWorldData.get(world);
      
      AkumaNoMiItem eatenItem = (AkumaNoMiItem)itemStack.getItem();
      String eatenFruit = DevilFruitHelper.getDevilFruitKey(eatenItem);
      
      if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && worldData.getAteFruits().containsValue(eatenFruit)) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_FRUIT_ALREADY_USED, new Object[0]));
        itemStack.shrink(1);
        return itemStack;
      } 
      
      boolean hasFruit = !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit());
      boolean hasYami = devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI);
      
      if (CommonConfig.INSTANCE.getRandomizedFruits()) {
        
        AkumaNoMiItem randomFruit = eatenItem.getShiftedFruit(world);
        String randomFruitKey = DevilFruitHelper.getDevilFruitKey(randomFruit);
        eatenItem = randomFruit;
        eatenFruit = randomFruitKey;
      } 
      
      if (!CommonConfig.INSTANCE.isYamiPowerEnabled() && hasFruit) {
        
        applyCurseDeath(player);
        itemStack.shrink(1);
        return itemStack;
      } 
      
      if (CommonConfig.INSTANCE.isYamiPowerEnabled()) {


        
        if (hasFruit && eatenItem != ModAbilities.YAMI_YAMI_NO_MI && !hasYami) {
          
          applyCurseDeath(player);
          itemStack.shrink(1);
          worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
          worldData.removeDevilFruitInWorld(eatenFruit);
          return itemStack;
        } 

        
        if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI && hasYami) {
          
          applyCurseDeath(player);
          itemStack.shrink(1);
          worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
          worldData.removeDevilFruitInWorld(eatenFruit);
          return itemStack;
        } 

        
        if (hasFruit && !devilFruitProps.getDevilFruit().equalsIgnoreCase("yami_yami") && devilFruitProps.hasYamiPower()) {
          
          applyCurseDeath(player);
          itemStack.shrink(1);
          worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
          worldData.removeDevilFruitInWorld(eatenFruit);
          return itemStack;
        } 
      } 
      
      if (this.type == FruitType.LOGIA) {
        devilFruitProps.setLogia(true);
      }
      
      if (eatenItem == ModAbilities.YAMI_YAMI_NO_MI) {

        
        devilFruitProps.setLogia(false);
        if (CommonConfig.INSTANCE.isYamiPowerEnabled()) {
          devilFruitProps.setYamiPower(true);
        
        }
      
      }
      else if (devilFruitProps.hasYamiPower()) {
        
        devilFruitProps.setLogia(false);
      } 


      
      if (eatenItem == ModAbilities.HITO_HITO_NO_MI) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_ENLIGHTENMENT, new Object[0]));
        entityStatsProps.setRace("human");
        
        AbilityHelper.validateStyleMoves(player);
        AbilityHelper.validateRacialMoves(player);
        
        WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
      } 

      
      if (eatenItem != ModAbilities.YOMI_YOMI_NO_MI) {
        
        for (Ability a : eatenItem.abilities) {
          
          if (!AbilityHelper.verifyIfAbilityIsBanned(a) && abilityDataProps.getUnlockedAbility(a) == null)
          {
            abilityDataProps.addUnlockedAbility(a);
          }
        } 
        
        WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
        WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
      } 
      
      if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
        
        worldData.addAteDevilFruit(player, this);
        worldData.removeDevilFruitInInventory(player.getUniqueID(), eatenFruit);
      } 

      
      if (!hasFruit || (hasYami && CommonConfig.INSTANCE.isYamiPowerEnabled())) {
        
        DFEncyclopediaEntry elements = eatenItem.getRandomElements(world);
        ItemsHelper.updateEncyclopediae(player, eatenFruit, elements);
        
        devilFruitProps.setDevilFruit(eatenFruit);
        WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
      } 
      
      player.getFoodStats().addStats(12, 4.0F);
    } 
    
    EatDevilFruitEvent.Post postEvent = new EatDevilFruitEvent.Post(player, itemStack);
    MinecraftForge.EVENT_BUS.post((Event)postEvent);
    
    itemStack.shrink(1);
    
    return itemStack;
  }


  
  public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4) {
    if (!RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
      
      for (int i = 0; i < this.abilities.length; i++) {
        if (!AbilityHelper.verifyIfAbilityIsBanned(this.abilities[i]) && this.abilities[i] != null && !(this.abilities[i] instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility))
          list.add(new StringTextComponent(TextFormatting.GRAY + I18n.format("ability.mineminenomi." + WyHelper.getResourceName(this.abilities[i].getName()), new Object[0]))); 
      } 
      list.add(new StringTextComponent(""));
      list.add(new StringTextComponent(this.type.getColor() + this.type.getName()));
    }
    else {
      
      list.clear();
    } 
  }


  
  public boolean hasCustomEntity(ItemStack stack) {
    return true;
  }


  
  public Entity createEntity(World world, Entity entity, ItemStack itemStack) {
    if (((ItemEntity)entity).getThrowerId() == null) {
      return null;
    }
    applyRandomness(world, itemStack);
    
    DFItemEntity newEntity = new DFItemEntity(world, (entity.getPositionVec()).x, (entity.getPositionVec()).y, (entity.getPositionVec()).z, itemStack);
    newEntity.setPickupDelay(40);
    newEntity.setMotion(entity.getMotion());
    return (Entity)newEntity;
  }


  
  public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entity) {
    if (entity.world.isRemote) {
      return false;
    }
    ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);
    
    boolean chunkExistsCheck = !entity.world.chunkExists(entity.getPosition().getX() >> 4, entity.getPosition().getZ() >> 4);
    boolean entityBurningCheck = entity.isBurning();
    boolean entityInVoidCheck = (entity.getPosition().getY() < -1);
    
    if (chunkExistsCheck || entityBurningCheck || entityInVoidCheck) {
      
      entity.remove();
      worldData.removeDevilFruitInWorld(this);
    } 
    
    boolean shouldRemove = false;
    
    List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(entity.getPosition(), entity.world, 2);
    
    for (BlockPos pos : blockPosList) {
      
      TileEntity te = entity.world.getTileEntity(pos);
      
      if (te instanceof net.minecraft.tileentity.HopperTileEntity) {
        
        shouldRemove = true;
        
        break;
      } 
    } 
    List<Entity> hopperMinecarts = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 0.5D, new Class[] { HopperMinecartEntity.class });
    
    if (hopperMinecarts.size() > 0) {
      shouldRemove = true;
    }
    List<Entity> foxes = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 1.5D, new Class[] { FoxEntity.class });
    
    if (foxes.size() > 0) {
      shouldRemove = true;
    }
    if (shouldRemove) {
      
      entity.remove();
      
      if (entity.getThrowerId() != null) {
        
        PlayerEntity thrower = entity.world.getPlayerByUuid(entity.getThrowerId());
        if (thrower != null) {
          thrower.inventory.addItemStackToInventory(itemStack);
        } else {
          worldData.removeDevilFruitInWorld(this);
        } 
      } else {
        worldData.removeDevilFruitInWorld(this);
      } 
    } 
    return false;
  }

  
  private void applyRandomness(World world, ItemStack itemStack) {
    if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT)
    {
      if (!hasBaseColor(itemStack) || !hasStemColor(itemStack) || RandomFruitEvents.Client.DIRTY) {
        
        removeBaseColor(itemStack);
        removeStemColor(itemStack);
        AkumaNoMiItem randomFruit = getShiftedFruit(world);
        DFEncyclopediaEntry randomElements = randomFruit.getRandomElements(world);
        setBaseColor(itemStack, ((Color)randomElements.getBaseColor().get()).getRGB());
        setStemColor(itemStack, ((Color)randomElements.getStemColor().get()).getRGB());
        int type = ((Integer)randomElements.getShape().get()).intValue();
        itemStack.getOrCreateTag().putInt("type", type);
        itemStack.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT, new Object[0]));
        RandomFruitEvents.Client.DIRTY = false;
      } 
    }
  }

  
  public DFEncyclopediaEntry getRandomElements(World world) {
    long seed = world.getSeed() + getTranslationKey().hashCode();
    if (world.isRemote)
      seed = ((Long)RandomFruitEvents.Client.FRUIT_SEEDS.get(Integer.valueOf(getTranslationKey().hashCode()))).longValue(); 
    Random rand = new Random(seed);
    
    float red = rand.nextFloat();
    float green = rand.nextFloat();
    float blue = rand.nextFloat();
    Optional<Color> baseColor = Optional.of(new Color(red, green, blue));
    
    String hexStemColor = STEM_COLORS[rand.nextInt(STEM_COLORS.length)];
    Optional<Color> stemColor = Optional.of(WyHelper.hexToRGB(hexStemColor));
    
    Optional<Integer> type = Optional.of(Integer.valueOf(rand.nextInt(10) + 1));
    
    return DFEncyclopediaEntry.of(type, baseColor, stemColor);
  }

  
  public AkumaNoMiItem getShiftedFruit(World world) {
    int seed = Math.max(1, (int)(world.getSeed() % 10L));
    int pos = ModValues.devilfruits.indexOf(this);
    int shifted = (pos + seed) % ModValues.devilfruits.size();
    AkumaNoMiItem randomFruit = ModValues.devilfruits.get(shifted);


    
    return randomFruit;
  }

  
  @Nullable
  public AkumaNoMiItem getReverseShiftedFruit(World world) {
    for (AkumaNoMiItem fruit : ModValues.devilfruits) {
      
      AkumaNoMiItem shiftedFruit = fruit.getShiftedFruit(world);
      if (shiftedFruit == this) {
        return fruit;
      }
    } 
    return null;
  }

  
  private void applyCurseDeath(PlayerEntity player) {
    player.attackEntityFrom(ModDamageSource.DEVILS_CURSE, player.getMaxHealth());
  }

  
  public int getTier() {
    return this.tier;
  }

  
  public FruitType getType() {
    return this.type;
  }

  
  public String getDevilFruitName() {
    return this.name;
  }
}


