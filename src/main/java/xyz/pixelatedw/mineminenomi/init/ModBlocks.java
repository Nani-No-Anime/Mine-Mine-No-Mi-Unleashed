/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.FireBlock;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.item.BlockItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraftforge.common.ToolType;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.AblProtectionBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.BarrierBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.CandyBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.CannonBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.ChallengeArenaBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.CustomBarsBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.CustomSpawnerBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.DemonPoisonBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.DenDenMushiBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.DesignBarrelBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.DialBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.HardenedSnowBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.KairosekiOreBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.MucusBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.OilSpillBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.OpeBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.OpeMidBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.OriBarsBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.PoisonBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.PoneglyphBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.SkyBlockBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.SpongeCakeBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.StringMidBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.StringWallBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.WantedPosterPackageBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.traps.DarknessBlock;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.traps.SunaSandBlock;
/*     */ import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.AxeDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.BreathDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.EisenDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.FlameDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.FlashDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.ImpactDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.MilkyDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.dials.RejectDialItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModBlocks
/*     */ {
/*  57 */   public static final Block OPE = (Block)new OpeBlock();
/*  58 */   public static final Block OPE_MID = (Block)new OpeMidBlock();
/*  59 */   public static final Block SUNA_SAND = (Block)new SunaSandBlock();
/*  60 */   public static final Block WAX = new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(8.0F, 3.0F).harvestTool(ToolType.PICKAXE));
/*  61 */   public static final Block CANDY = (Block)new CandyBlock();
/*  62 */   public static final Block POISON = (Block)new PoisonBlock();
/*  63 */   public static final Block DEMON_POISON = (Block)new DemonPoisonBlock();
/*  64 */   public static final Block STRING_WALL = (Block)new StringWallBlock();
/*  65 */   public static final Block STRING_MID = (Block)new StringMidBlock();
/*  66 */   public static final Block BARRIER = (Block)new BarrierBlock();
/*  67 */   public static final Block DARKNESS = (Block)new DarknessBlock();
/*  68 */   public static final Block ORI_BARS = (Block)new OriBarsBlock();
/*  69 */   public static final Block MUCUS = (Block)new MucusBlock();
/*  70 */   public static final Block HARDENED_SNOW = (Block)new HardenedSnowBlock();
/*     */ 
/*     */   
/*  73 */   public static final Block KAIROSEKI = new Block(Block.Properties.create(ModMaterials.KAIROSEKI_MATERIAL).hardnessAndResistance(40.0F, 600.0F).harvestTool(ToolType.PICKAXE));
/*  74 */   public static final Block KAIROSEKI_ORE = (Block)new KairosekiOreBlock();
/*  75 */   public static final Block KAIROSEKI_BARS = (Block)new CustomBarsBlock();
/*     */ 
/*     */   
/*  78 */   public static final Block SKY_BLOCK = (Block)new SkyBlockBlock();
/*  79 */   public static final Block SPONGE_CAKE = (Block)new SpongeCakeBlock();
/*  80 */   public static final Block WANTED_POSTER = (Block)new WantedPosterBlock();
/*  81 */   public static final Block WANTED_POSTER_PACKAGE = (Block)new WantedPosterPackageBlock();
/*  82 */   public static final Block CUSTOM_SPAWNER = (Block)new CustomSpawnerBlock();
/*  83 */   public static final Block DEN_DEN_MUSHI = (Block)new DenDenMushiBlock();
/*  84 */   public static final Block CANNON = (Block)new CannonBlock();
/*  85 */   public static final Block OIL_SPILL = (Block)new OilSpillBlock();
/*  86 */   public static final Block DESIGN_BARREL = (Block)new DesignBarrelBlock();
/*     */ 
/*     */   
/*  89 */   public static final Block AXE_DIAL = (Block)new DialBlock();
/*  90 */   public static final Block IMPACT_DIAL = (Block)new DialBlock();
/*  91 */   public static final Block FLASH_DIAL = (Block)new DialBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(0.2F).lightValue(10));
/*  92 */   public static final Block BREATH_DIAL = (Block)new DialBlock();
/*  93 */   public static final Block EISEN_DIAL = (Block)new DialBlock();
/*  94 */   public static final Block MILKY_DIAL = (Block)new DialBlock();
/*  95 */   public static final Block FLAME_DIAL = (Block)new DialBlock();
/*  96 */   public static final Block REJECT_DIAL = (Block)new DialBlock();
/*     */ 
/*     */   
/*  99 */   public static final Block ABILITY_PROTECTION = (Block)new AblProtectionBlock();
/* 100 */   public static final Block CHALLENGE_ARENA = (Block)new ChallengeArenaBlock();
/*     */ 
/*     */   
/* 103 */   public static final Block PONEGLYPH = (Block)new PoneglyphBlock();
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 108 */     WyRegistry.registerBlock(OPE, "Ope");
/* 109 */     WyRegistry.registerBlock(OPE_MID, "Ope Mid");
/* 110 */     WyRegistry.registerBlock(KAIROSEKI, "Kairoseki Block");
/* 111 */     WyRegistry.registerBlock(KAIROSEKI_ORE, "Kairoseki Ore");
/* 112 */     WyRegistry.registerBlock(SKY_BLOCK, "Sky Block");
/* 113 */     WyRegistry.registerBlock(SPONGE_CAKE, "Sponge Cake");
/* 114 */     WyRegistry.registerBlock(KAIROSEKI_BARS, "Kairoseki Bars");
/* 115 */     WyRegistry.registerBlock(SUNA_SAND, "Suna Sand");
/* 116 */     WyRegistry.registerBlock(WAX, "Wax Block");
/* 117 */     WyRegistry.registerBlock(CANDY, "Candy Block");
/* 118 */     WyRegistry.registerBlock(POISON, "Poison");
/* 119 */     WyRegistry.registerBlock(MUCUS, "Mucus");
/* 120 */     WyRegistry.registerBlock(DEMON_POISON, "Demon Poison");
/* 121 */     WyRegistry.registerBlock(STRING_WALL, "String Wall");
/* 122 */     WyRegistry.registerBlock(STRING_MID, "String Mid");
/* 123 */     WyRegistry.registerBlock(BARRIER, "Barrier");
/* 124 */     WyRegistry.registerBlock(DARKNESS, "Darkness");
/* 125 */     WyRegistry.registerBlock(ORI_BARS, "Ori Bars");
/* 126 */     WyRegistry.registerBlock(CUSTOM_SPAWNER, "Custom Spawner");
/* 127 */     WyRegistry.registerBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
/* 128 */     WyRegistry.registerBlock(WANTED_POSTER, "Wanted Poster");
/* 129 */     WyRegistry.registerBlock(AXE_DIAL, "Axe Dial");
/* 130 */     WyRegistry.registerBlock(BREATH_DIAL, "Breath Dial");
/* 131 */     WyRegistry.registerBlock(FLAME_DIAL, "Flame Dial");
/* 132 */     WyRegistry.registerBlock(REJECT_DIAL, "Reject Dial");
/* 133 */     WyRegistry.registerBlock(IMPACT_DIAL, "Impact Dial");
/* 134 */     WyRegistry.registerBlock(FLASH_DIAL, "Flash Dial");
/* 135 */     WyRegistry.registerBlock(EISEN_DIAL, "Eisen Dial");
/* 136 */     WyRegistry.registerBlock(MILKY_DIAL, "Milky Dial");
/* 137 */     WyRegistry.registerBlock(ABILITY_PROTECTION, "Ability Protection");
/* 138 */     WyRegistry.registerBlock(DEN_DEN_MUSHI, "Den Den Mushi");
/* 139 */     WyRegistry.registerBlock(CANNON, "Cannon");
/* 140 */     WyRegistry.registerBlock(PONEGLYPH, "Poneglyph");
/* 141 */     WyRegistry.registerBlock(CHALLENGE_ARENA, "Challenge Arena");
/* 142 */     WyRegistry.registerBlock(OIL_SPILL, "Oil Spill");
/* 143 */     WyRegistry.registerBlock(DESIGN_BARREL, "Design Barrel");
/* 144 */     WyRegistry.registerBlock(HARDENED_SNOW, "Hardened Snow");
/*     */ 
/*     */     
/* 147 */     registerItemBlock(OPE, "Ope", false);
/* 148 */     registerItemBlock(OPE_MID, "Ope Mid", false);
/* 149 */     registerItemBlock(KAIROSEKI, "Kairoseki Block", true);
/* 150 */     registerItemBlock(KAIROSEKI_ORE, "Kairoseki Ore", true);
/* 151 */     registerItemBlock(SKY_BLOCK, "Sky Block", true);
/* 152 */     registerItemBlock(SPONGE_CAKE, "Sponge Cake", false);
/* 153 */     registerItemBlock(KAIROSEKI_BARS, "Kairoseki Bars", true);
/* 154 */     registerItemBlock(SUNA_SAND, "Suna Sand", false);
/* 155 */     registerItemBlock(WAX, "Wax Block", false);
/* 156 */     registerItemBlock(CANDY, "Candy Block", false);
/* 157 */     registerItemBlock(POISON, "Poison", false);
/* 158 */     registerItemBlock(MUCUS, "Mucus", false);
/* 159 */     registerItemBlock(DEMON_POISON, "Demon Poison", false);
/* 160 */     registerItemBlock(STRING_WALL, "String Wall", false);
/* 161 */     registerItemBlock(STRING_MID, "String Mid", false);
/* 162 */     registerItemBlock(BARRIER, "Barrier", false);
/* 163 */     registerItemBlock(DARKNESS, "Darkness", false);
/* 164 */     registerItemBlock(ORI_BARS, "Ori Bars", false);
/* 165 */     registerItemBlock(CUSTOM_SPAWNER, "Custom Spawner", false);
/* 166 */     registerItemBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package", true);
/* 167 */     registerItemBlock(DEN_DEN_MUSHI, "Den Den Mushi", true);
/* 168 */     registerItemBlock(CANNON, "Cannon", true);
/* 169 */     registerItemBlock(PONEGLYPH, "Poneglyph", true);
/* 170 */     registerItemBlock(OIL_SPILL, "Oil Spill", false);
/* 171 */     registerItemBlock(DESIGN_BARREL, "Design Barrel", false);
/* 172 */     registerItemBlock(HARDENED_SNOW, "Hardened Snow", false);
/* 173 */     registerCustomItemBlock(WANTED_POSTER, "Wanted Poster", new WantedPosterItem());
/* 174 */     registerCustomItemBlock(AXE_DIAL, "Axe Dial", new AxeDialItem());
/* 175 */     registerCustomItemBlock(BREATH_DIAL, "Breath Dial", new BreathDialItem());
/* 176 */     registerCustomItemBlock(FLAME_DIAL, "Flame Dial", new FlameDialItem());
/* 177 */     registerCustomItemBlock(REJECT_DIAL, "Reject Dial", new RejectDialItem());
/* 178 */     registerCustomItemBlock(IMPACT_DIAL, "Impact Dial", new ImpactDialItem());
/* 179 */     registerCustomItemBlock(FLASH_DIAL, "Flash Dial", new FlashDialItem());
/* 180 */     registerCustomItemBlock(EISEN_DIAL, "Eisen Dial", new EisenDialItem());
/* 181 */     registerCustomItemBlock(MILKY_DIAL, "Milky Dial", new MilkyDialItem());
/*     */     
/* 183 */     setFlammeableBlock(CANDY, 30, 60);
/* 184 */     setFlammeableBlock(WAX, 100, 200);
/* 185 */     setFlammeableBlock(HARDENED_SNOW, 100, 200);
/* 186 */     setFlammeableBlock(MUCUS, 600, 1000);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item registerItemBlock(Block block, String localizedName, boolean isInCreative) {
/* 191 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 192 */     if (isInCreative) {
/* 193 */       return WyRegistry.registerItem((Item)new BlockItem(block, (new Item.Properties()).group(ModCreativeTabs.MISC)), resourceName);
/*     */     }
/* 195 */     return WyRegistry.registerItem((Item)new BlockItem(block, new Item.Properties()), resourceName);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setFlammeableBlock(Block block, int encouragement, int flammability) {
/* 200 */     FireBlock fireBlock = (FireBlock)Blocks.FIRE;
/* 201 */     fireBlock.setFireInfo(block, encouragement, flammability);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends BlockItem> T registerCustomItemBlock(Block block, String localizedName, T itemBlock) {
/* 206 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 207 */     WyRegistry.registerItem((Item)itemBlock, resourceName);
/*     */     
/* 209 */     return itemBlock;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */