/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiBoxItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.BellyPouchItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.BottleOfRumItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.BubblyCoralItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.CharacterCreatorItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.CigarItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.ColaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.ColorPaletteItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.DandelionItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.GoldDenDenMushiItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.HandcuffsItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.HeartItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.SakeBottleItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.SakeCupItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.SeaKingMeatItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.ShadowItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.UltraColaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.VivreCardItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.WateringCanItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModItems
/*     */ {
/*  34 */   public static final Item CHARACTER_CREATOR = (Item)new CharacterCreatorItem();
/*  35 */   public static final Item KAIROSEKI = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  36 */   public static final Item DENSE_KAIROSEKI = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*     */   
/*  38 */   public static final Item SHADOW = (Item)new ShadowItem();
/*  39 */   public static final Item DANDELION = (Item)new DandelionItem();
/*  40 */   public static final Item HEART = (Item)new HeartItem();
/*  41 */   public static final Item BELLY_POUCH = (Item)new BellyPouchItem();
/*  42 */   public static final Item KEY = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  43 */   public static final Item VIVRE_CARD = (Item)new VivreCardItem();
/*  44 */   public static final Item BUBBLY_CORAL = (Item)new BubblyCoralItem();
/*  45 */   public static final Item COLOR_PALETTE = (Item)new ColorPaletteItem((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  46 */   public static final Item WATERING_CAN = (Item)new WateringCanItem();
/*  47 */   public static final Item STRAW_DOLL = (Item)new StrawDollItem();
/*  48 */   public static final Item GOLD_DEN_DEN_MUSHI = (Item)new GoldDenDenMushiItem();
/*  49 */   public static final Item NORMAL_HANDCUFFS = (Item)new HandcuffsItem();
/*  50 */   public static final Item KAIROSEKI_HANDCUFFS = (Item)new HandcuffsItem();
/*  51 */   public static final Item CHAIN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  52 */   public static final Item DEVIL_FRUIT_ENCYCLOPEDIA = (Item)new DFEncyclopediaItem();
/*     */ 
/*     */   
/*  55 */   public static final AkumaNoMiBoxItem TIER_1_BOX = new AkumaNoMiBoxItem(1);
/*  56 */   public static final AkumaNoMiBoxItem TIER_2_BOX = new AkumaNoMiBoxItem(2);
/*  57 */   public static final AkumaNoMiBoxItem TIER_3_BOX = new AkumaNoMiBoxItem(3);
/*     */ 
/*     */   
/*  60 */   public static final Item SEA_KING_MEAT = (Item)new SeaKingMeatItem();
/*  61 */   public static final Item COLA = (Item)new ColaItem();
/*  62 */   public static final Item ULTRA_COLA = (Item)new UltraColaItem();
/*  63 */   public static final Item SAKE_BOTTLE = (Item)new SakeBottleItem();
/*  64 */   public static final Item SAKE_CUP = (Item)new SakeCupItem();
/*  65 */   public static final Item BOTTLE_OF_RUM = (Item)new BottleOfRumItem();
/*     */ 
/*     */   
/*  68 */   public static final Item CIGAR = (Item)new CigarItem(1);
/*  69 */   public static final Item CIGARETTE = (Item)new CigarItem(15);
/*  70 */   public static final Item SMOKING_PIPE = (Item)new CigarItem(15);
/*  71 */   public static final Item CIGAR_LESS = (Item)new CigarItem(10);
/*     */ 
/*     */   
/*  74 */   public static final Item BULLET = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  75 */   public static final Item KAIROSEKI_BULLET = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  76 */   public static final Item KUJA_ARROW = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  77 */   public static final Item POP_GREEN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  78 */   public static final Item POP_GREEN_DEVIL = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  79 */   public static final Item POP_GREEN_RAFFLESIA = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  80 */   public static final Item POP_GREEN_TAKE_JAVELIN = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  81 */   public static final Item POP_GREEN_BAKUHATSU_SO = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  82 */   public static final Item POP_GREEN_HUMANDRAKE = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  83 */   public static final Item POP_GREEN_TRAMPOLIA = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  84 */   public static final Item POP_GREEN_IMPACT_WOLF = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*  85 */   public static final Item CANNON_BALL = new Item((new Item.Properties()).group(ModCreativeTabs.MISC));
/*     */ 
/*     */   
/*     */   static {
/*  89 */     WyRegistry.registerItem(KAIROSEKI, "Kairoseki");
/*  90 */     WyRegistry.registerItem(DENSE_KAIROSEKI, "Dense Kairoseki");
/*     */     
/*  92 */     WyRegistry.registerItem(SHADOW, "Shadow");
/*  93 */     WyRegistry.registerItem(DANDELION, "Dandelion");
/*  94 */     WyRegistry.registerItem(KEY, "Key");
/*  95 */     WyRegistry.registerItem(CHARACTER_CREATOR, "Character Creator");
/*  96 */     WyRegistry.registerItem(BELLY_POUCH, "Belly Pouch");
/*  97 */     WyRegistry.registerItem(SEA_KING_MEAT, "Sea King Meat");
/*  98 */     WyRegistry.registerItem(COLA, "Cola");
/*  99 */     WyRegistry.registerItem(ULTRA_COLA, "Ultra Cola");
/* 100 */     WyRegistry.registerItem(HEART, "Heart");
/* 101 */     WyRegistry.registerItem(BULLET, "Bullet");
/* 102 */     WyRegistry.registerItem(KAIROSEKI_BULLET, "Kairoseki Bullet");
/* 103 */     WyRegistry.registerItem(KUJA_ARROW, "Kuja Arrow");
/* 104 */     WyRegistry.registerItem(POP_GREEN, "Pop Green");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     WyRegistry.registerItem((Item)TIER_1_BOX, "Wooden Box");
/* 113 */     WyRegistry.registerItem((Item)TIER_2_BOX, "Iron Box");
/* 114 */     WyRegistry.registerItem((Item)TIER_3_BOX, "Golden Box");
/* 115 */     WyRegistry.registerItem(BUBBLY_CORAL, "Bubbly Coral");
/* 116 */     WyRegistry.registerItem(VIVRE_CARD, "Vivre Card");
/* 117 */     WyRegistry.registerItem(COLOR_PALETTE, "Color Palette");
/* 118 */     WyRegistry.registerItem(WATERING_CAN, "Watering Can");
/* 119 */     WyRegistry.registerItem(SAKE_BOTTLE, "Sake Bottle");
/* 120 */     WyRegistry.registerItem(SAKE_CUP, "Sake Cup");
/* 121 */     WyRegistry.registerItem(CIGAR, "Three Cigars");
/* 122 */     WyRegistry.registerItem(CIGAR_LESS, "Cigar");
/* 123 */     WyRegistry.registerItem(CIGARETTE, "Cigarette");
/* 124 */     WyRegistry.registerItem(SMOKING_PIPE, "Smoking Pipe");
/* 125 */     WyRegistry.registerItem(CANNON_BALL, "Cannon Ball");
/* 126 */     WyRegistry.registerItem(STRAW_DOLL, "Straw Doll");
/* 127 */     WyRegistry.registerItem(GOLD_DEN_DEN_MUSHI, "Golden Den Den Mushi");
/* 128 */     WyRegistry.registerItem(BOTTLE_OF_RUM, "Bottle of Rum");
/* 129 */     WyRegistry.registerItem(NORMAL_HANDCUFFS, "Handcuffs");
/* 130 */     WyRegistry.registerItem(KAIROSEKI_HANDCUFFS, "Kairoseki Handcuffs");
/* 131 */     WyRegistry.registerItem(CHAIN, "Chain");
/* 132 */     WyRegistry.registerItem(DEVIL_FRUIT_ENCYCLOPEDIA, "Devil Fruit Encyclopedia");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */