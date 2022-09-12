package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.blocks.*;
import xyz.pixelatedw.mineminenomi.blocks.traps.DarknessBlock;
import xyz.pixelatedw.mineminenomi.blocks.traps.SunaSandBlock;
import xyz.pixelatedw.mineminenomi.items.WantedPosterItem;
import xyz.pixelatedw.mineminenomi.items.dials.*;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;



@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
  public static final Block OPE = (Block)new OpeBlock();
  public static final Block OPE_MID = (Block)new OpeMidBlock();
  public static final Block SUNA_SAND = (Block)new SunaSandBlock();
  public static final Block WAX = new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(8.0F, 3.0F).harvestTool(ToolType.PICKAXE));
  public static final Block CANDY = (Block)new CandyBlock();
  public static final Block POISON = (Block)new PoisonBlock();
  public static final Block DEMON_POISON = (Block)new DemonPoisonBlock();
  public static final Block STRING_WALL = (Block)new StringWallBlock();
  public static final Block STRING_MID = (Block)new StringMidBlock();
  public static final Block BARRIER = (Block)new BarrierBlock();
  public static final Block DARKNESS = (Block)new DarknessBlock();
  public static final Block ORI_BARS = (Block)new OriBarsBlock();
  public static final Block MUCUS = (Block)new MucusBlock();
  public static final Block HARDENED_SNOW = (Block)new HardenedSnowBlock();

  
  public static final Block KAIROSEKI = new Block(Block.Properties.create(ModMaterials.KAIROSEKI_MATERIAL).hardnessAndResistance(40.0F, 600.0F).harvestTool(ToolType.PICKAXE));
  public static final Block KAIROSEKI_ORE = (Block)new KairosekiOreBlock();
  public static final Block KAIROSEKI_BARS = (Block)new CustomBarsBlock();

  
  public static final Block SKY_BLOCK = (Block)new SkyBlockBlock();
  public static final Block SPONGE_CAKE = (Block)new SpongeCakeBlock();
  public static final Block WANTED_POSTER = (Block)new WantedPosterBlock();
  public static final Block WANTED_POSTER_PACKAGE = (Block)new WantedPosterPackageBlock();
  public static final Block CUSTOM_SPAWNER = (Block)new CustomSpawnerBlock();
  public static final Block DEN_DEN_MUSHI = (Block)new DenDenMushiBlock();
  public static final Block CANNON = (Block)new CannonBlock();
  public static final Block OIL_SPILL = (Block)new OilSpillBlock();
  public static final Block DESIGN_BARREL = (Block)new DesignBarrelBlock();

  
  public static final Block AXE_DIAL = (Block)new DialBlock();
  public static final Block IMPACT_DIAL = (Block)new DialBlock();
  public static final Block FLASH_DIAL = (Block)new DialBlock(Block.Properties.create(Material.CLAY).hardnessAndResistance(0.2F).lightValue(10));
  public static final Block BREATH_DIAL = (Block)new DialBlock();
  public static final Block EISEN_DIAL = (Block)new DialBlock();
  public static final Block MILKY_DIAL = (Block)new DialBlock();
  public static final Block FLAME_DIAL = (Block)new DialBlock();
  public static final Block REJECT_DIAL = (Block)new DialBlock();

  
  public static final Block ABILITY_PROTECTION = (Block)new AblProtectionBlock();
  public static final Block CHALLENGE_ARENA = (Block)new ChallengeArenaBlock();

  
  public static final Block PONEGLYPH = (Block)new PoneglyphBlock();


  
  static {
    WyRegistry.registerBlock(OPE, "Ope");
    WyRegistry.registerBlock(OPE_MID, "Ope Mid");
    WyRegistry.registerBlock(KAIROSEKI, "Kairoseki Block");
    WyRegistry.registerBlock(KAIROSEKI_ORE, "Kairoseki Ore");
    WyRegistry.registerBlock(SKY_BLOCK, "Sky Block");
    WyRegistry.registerBlock(SPONGE_CAKE, "Sponge Cake");
    WyRegistry.registerBlock(KAIROSEKI_BARS, "Kairoseki Bars");
    WyRegistry.registerBlock(SUNA_SAND, "Suna Sand");
    WyRegistry.registerBlock(WAX, "Wax Block");
    WyRegistry.registerBlock(CANDY, "Candy Block");
    WyRegistry.registerBlock(POISON, "Poison");
    WyRegistry.registerBlock(MUCUS, "Mucus");
    WyRegistry.registerBlock(DEMON_POISON, "Demon Poison");
    WyRegistry.registerBlock(STRING_WALL, "String Wall");
    WyRegistry.registerBlock(STRING_MID, "String Mid");
    WyRegistry.registerBlock(BARRIER, "Barrier");
    WyRegistry.registerBlock(DARKNESS, "Darkness");
    WyRegistry.registerBlock(ORI_BARS, "Ori Bars");
    WyRegistry.registerBlock(CUSTOM_SPAWNER, "Custom Spawner");
    WyRegistry.registerBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package");
    WyRegistry.registerBlock(WANTED_POSTER, "Wanted Poster");
    WyRegistry.registerBlock(AXE_DIAL, "Axe Dial");
    WyRegistry.registerBlock(BREATH_DIAL, "Breath Dial");
    WyRegistry.registerBlock(FLAME_DIAL, "Flame Dial");
    WyRegistry.registerBlock(REJECT_DIAL, "Reject Dial");
    WyRegistry.registerBlock(IMPACT_DIAL, "Impact Dial");
    WyRegistry.registerBlock(FLASH_DIAL, "Flash Dial");
    WyRegistry.registerBlock(EISEN_DIAL, "Eisen Dial");
    WyRegistry.registerBlock(MILKY_DIAL, "Milky Dial");
    WyRegistry.registerBlock(ABILITY_PROTECTION, "Ability Protection");
    WyRegistry.registerBlock(DEN_DEN_MUSHI, "Den Den Mushi");
    WyRegistry.registerBlock(CANNON, "Cannon");
    WyRegistry.registerBlock(PONEGLYPH, "Poneglyph");
    WyRegistry.registerBlock(CHALLENGE_ARENA, "Challenge Arena");
    WyRegistry.registerBlock(OIL_SPILL, "Oil Spill");
    WyRegistry.registerBlock(DESIGN_BARREL, "Design Barrel");
    WyRegistry.registerBlock(HARDENED_SNOW, "Hardened Snow");

    
    registerItemBlock(OPE, "Ope", false);
    registerItemBlock(OPE_MID, "Ope Mid", false);
    registerItemBlock(KAIROSEKI, "Kairoseki Block", true);
    registerItemBlock(KAIROSEKI_ORE, "Kairoseki Ore", true);
    registerItemBlock(SKY_BLOCK, "Sky Block", true);
    registerItemBlock(SPONGE_CAKE, "Sponge Cake", false);
    registerItemBlock(KAIROSEKI_BARS, "Kairoseki Bars", true);
    registerItemBlock(SUNA_SAND, "Suna Sand", false);
    registerItemBlock(WAX, "Wax Block", false);
    registerItemBlock(CANDY, "Candy Block", false);
    registerItemBlock(POISON, "Poison", false);
    registerItemBlock(MUCUS, "Mucus", false);
    registerItemBlock(DEMON_POISON, "Demon Poison", false);
    registerItemBlock(STRING_WALL, "String Wall", false);
    registerItemBlock(STRING_MID, "String Mid", false);
    registerItemBlock(BARRIER, "Barrier", false);
    registerItemBlock(DARKNESS, "Darkness", false);
    registerItemBlock(ORI_BARS, "Ori Bars", false);
    registerItemBlock(CUSTOM_SPAWNER, "Custom Spawner", false);
    registerItemBlock(WANTED_POSTER_PACKAGE, "Wanted Poster Package", true);
    registerItemBlock(DEN_DEN_MUSHI, "Den Den Mushi", true);
    registerItemBlock(CANNON, "Cannon", true);
    registerItemBlock(PONEGLYPH, "Poneglyph", true);
    registerItemBlock(OIL_SPILL, "Oil Spill", false);
    registerItemBlock(DESIGN_BARREL, "Design Barrel", false);
    registerItemBlock(HARDENED_SNOW, "Hardened Snow", false);
    registerCustomItemBlock(WANTED_POSTER, "Wanted Poster", new WantedPosterItem());
    registerCustomItemBlock(AXE_DIAL, "Axe Dial", new AxeDialItem());
    registerCustomItemBlock(BREATH_DIAL, "Breath Dial", new BreathDialItem());
    registerCustomItemBlock(FLAME_DIAL, "Flame Dial", new FlameDialItem());
    registerCustomItemBlock(REJECT_DIAL, "Reject Dial", new RejectDialItem());
    registerCustomItemBlock(IMPACT_DIAL, "Impact Dial", new ImpactDialItem());
    registerCustomItemBlock(FLASH_DIAL, "Flash Dial", new FlashDialItem());
    registerCustomItemBlock(EISEN_DIAL, "Eisen Dial", new EisenDialItem());
    registerCustomItemBlock(MILKY_DIAL, "Milky Dial", new MilkyDialItem());
    
    setFlammeableBlock(CANDY, 30, 60);
    setFlammeableBlock(WAX, 100, 200);
    setFlammeableBlock(HARDENED_SNOW, 100, 200);
    setFlammeableBlock(MUCUS, 600, 1000);
  }

  
  private static Item registerItemBlock(Block block, String localizedName, boolean isInCreative) {
    String resourceName = WyHelper.getResourceName(localizedName);
    if (isInCreative) {
      return WyRegistry.registerItem((Item)new BlockItem(block, (new Item.Properties()).group(ModCreativeTabs.MISC)), resourceName);
    }
    return WyRegistry.registerItem((Item)new BlockItem(block, new Item.Properties()), resourceName);
  }

  
  private static void setFlammeableBlock(Block block, int encouragement, int flammability) {
    FireBlock fireBlock = (FireBlock)Blocks.FIRE;
    fireBlock.setFireInfo(block, encouragement, flammability);
  }

  
  public static <T extends BlockItem> T registerCustomItemBlock(Block block, String localizedName, T itemBlock) {
    String resourceName = WyHelper.getResourceName(localizedName);
    WyRegistry.registerItem((Item)itemBlock, resourceName);
    
    return itemBlock;
  }
}


