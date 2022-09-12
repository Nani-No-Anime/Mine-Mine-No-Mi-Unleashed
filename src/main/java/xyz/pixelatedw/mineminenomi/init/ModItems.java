package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.items.*;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;



@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
  public static final Item CHARACTER_CREATOR = (Item)new CharacterCreatorItem();
  public static final Item KAIROSEKI = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item DENSE_KAIROSEKI = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  
  public static final Item SHADOW = (Item)new ShadowItem();
  public static final Item DANDELION = (Item)new DandelionItem();
  public static final Item HEART = (Item)new HeartItem();
  public static final Item BELLY_POUCH = (Item)new BellyPouchItem();
  public static final Item KEY = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item VIVRE_CARD = (Item)new VivreCardItem();
  public static final Item BUBBLY_CORAL = (Item)new BubblyCoralItem();
  public static final Item COLOR_PALETTE = (Item)new ColorPaletteItem((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item WATERING_CAN = (Item)new WateringCanItem();
  public static final Item STRAW_DOLL = (Item)new StrawDollItem();
  public static final Item GOLD_DEN_DEN_MUSHI = (Item)new GoldDenDenMushiItem();
  public static final Item NORMAL_HANDCUFFS = (Item)new HandcuffsItem();
  public static final Item KAIROSEKI_HANDCUFFS = (Item)new HandcuffsItem();
  public static final Item CHAIN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item DEVIL_FRUIT_ENCYCLOPEDIA = (Item)new DFEncyclopediaItem();

  
  public static final AkumaNoMiBoxItem TIER_1_BOX = new AkumaNoMiBoxItem(1);
  public static final AkumaNoMiBoxItem TIER_2_BOX = new AkumaNoMiBoxItem(2);
  public static final AkumaNoMiBoxItem TIER_3_BOX = new AkumaNoMiBoxItem(3);

  
  public static final Item SEA_KING_MEAT = (Item)new SeaKingMeatItem();
  public static final Item COLA = (Item)new ColaItem();
  public static final Item ULTRA_COLA = (Item)new UltraColaItem();
  public static final Item SAKE_BOTTLE = (Item)new SakeBottleItem();
  public static final Item SAKE_CUP = (Item)new SakeCupItem();
  public static final Item BOTTLE_OF_RUM = (Item)new BottleOfRumItem();

  
  public static final Item CIGAR = (Item)new CigarItem(1);
  public static final Item CIGARETTE = (Item)new CigarItem(15);
  public static final Item SMOKING_PIPE = (Item)new CigarItem(15);
  public static final Item CIGAR_LESS = (Item)new CigarItem(10);

  
  public static final Item BULLET = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item KAIROSEKI_BULLET = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item KUJA_ARROW = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_DEVIL = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_RAFFLESIA = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_TAKE_JAVELIN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_BAKUHATSU_SO = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_HUMANDRAKE = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_TRAMPOLIA = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item POP_GREEN_IMPACT_WOLF = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
  public static final Item CANNON_BALL = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));

  
  static {
    WyRegistry.registerItem(KAIROSEKI, "Kairoseki");
    WyRegistry.registerItem(DENSE_KAIROSEKI, "Dense Kairoseki");
    
    WyRegistry.registerItem(SHADOW, "Shadow");
    WyRegistry.registerItem(DANDELION, "Dandelion");
    WyRegistry.registerItem(KEY, "Key");
    WyRegistry.registerItem(CHARACTER_CREATOR, "Character Creator");
    WyRegistry.registerItem(BELLY_POUCH, "Belly Pouch");
    WyRegistry.registerItem(SEA_KING_MEAT, "Sea King Meat");
    WyRegistry.registerItem(COLA, "Cola");
    WyRegistry.registerItem(ULTRA_COLA, "Ultra Cola");
    WyRegistry.registerItem(HEART, "Heart");
    WyRegistry.registerItem(BULLET, "Bullet");
    WyRegistry.registerItem(KAIROSEKI_BULLET, "Kairoseki Bullet");
    WyRegistry.registerItem(KUJA_ARROW, "Kuja Arrow");
    WyRegistry.registerItem(POP_GREEN, "Pop Green");






    
    WyRegistry.registerItem((Item)TIER_1_BOX, "Wooden Box");
    WyRegistry.registerItem((Item)TIER_2_BOX, "Iron Box");
    WyRegistry.registerItem((Item)TIER_3_BOX, "Golden Box");
    WyRegistry.registerItem(BUBBLY_CORAL, "Bubbly Coral");
    WyRegistry.registerItem(VIVRE_CARD, "Vivre Card");
    WyRegistry.registerItem(COLOR_PALETTE, "Color Palette");
    WyRegistry.registerItem(WATERING_CAN, "Watering Can");
    WyRegistry.registerItem(SAKE_BOTTLE, "Sake Bottle");
    WyRegistry.registerItem(SAKE_CUP, "Sake Cup");
    WyRegistry.registerItem(CIGAR, "Three Cigars");
    WyRegistry.registerItem(CIGAR_LESS, "Cigar");
    WyRegistry.registerItem(CIGARETTE, "Cigarette");
    WyRegistry.registerItem(SMOKING_PIPE, "Smoking Pipe");
    WyRegistry.registerItem(CANNON_BALL, "Cannon Ball");
    WyRegistry.registerItem(STRAW_DOLL, "Straw Doll");
    WyRegistry.registerItem(GOLD_DEN_DEN_MUSHI, "Golden Den Den Mushi");
    WyRegistry.registerItem(BOTTLE_OF_RUM, "Bottle of Rum");
    WyRegistry.registerItem(NORMAL_HANDCUFFS, "Handcuffs");
    WyRegistry.registerItem(KAIROSEKI_HANDCUFFS, "Kairoseki Handcuffs");
    WyRegistry.registerItem(CHAIN, "Chain");
    WyRegistry.registerItem(DEVIL_FRUIT_ENCYCLOPEDIA, "Devil Fruit Encyclopedia");
  }
}


