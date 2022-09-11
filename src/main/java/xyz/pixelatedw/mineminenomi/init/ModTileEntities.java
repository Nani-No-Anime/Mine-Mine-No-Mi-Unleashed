package xyz.pixelatedw.mineminenomi.init;

import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;









@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
  public static final TileEntityType ROOM = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.RoomTileEntity::new, new Block[] { ModBlocks.OPE_MID }).build(null);
  public static final TileEntityType TORIKAGO = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity::new, new Block[] { ModBlocks.STRING_MID }).build(null);
  public static final TileEntityType CUSTOM_SPAWNER = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity::new, new Block[] { ModBlocks.CUSTOM_SPAWNER }).build(null);
  public static final TileEntityType WANTED_POSTER_PACKAGE = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity::new, new Block[] { ModBlocks.WANTED_POSTER_PACKAGE }).build(null);
  public static final TileEntityType WANTED_POSTER = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity::new, new Block[] { ModBlocks.WANTED_POSTER }).build(null);
  public static final TileEntityType ABILITY_PROTECTION = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity::new, new Block[] { ModBlocks.ABILITY_PROTECTION }).build(null);
  public static final TileEntityType DEN_DEN_MUSHI = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity::new, new Block[] { ModBlocks.DEN_DEN_MUSHI }).build(null);
  public static final TileEntityType CANNON = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity::new, new Block[] { ModBlocks.CANNON }).build(null);
  public static final TileEntityType PONEGLYPH = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity::new, new Block[] { ModBlocks.PONEGLYPH }).build(null);
  public static final TileEntityType CHALLENGE_ARENA = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity::new, new Block[] { ModBlocks.CHALLENGE_ARENA }).build(null);

  
  static {
    WyRegistry.registerTileEntity(ROOM, "room");
    WyRegistry.registerTileEntity(TORIKAGO, "torikago");
    WyRegistry.registerTileEntity(CUSTOM_SPAWNER, "custom_spawner");
    WyRegistry.registerTileEntity(WANTED_POSTER_PACKAGE, "wanted_poster_package");
    WyRegistry.registerTileEntity(WANTED_POSTER, "wanted_poster");
    WyRegistry.registerTileEntity(ABILITY_PROTECTION, "ability_protection");
    WyRegistry.registerTileEntity(DEN_DEN_MUSHI, "den_den_mushi");
    WyRegistry.registerTileEntity(CANNON, "cannon");
    WyRegistry.registerTileEntity(PONEGLYPH, "poneglyph");
    WyRegistry.registerTileEntity(CHALLENGE_ARENA, "challenge_arena");
  }
}


