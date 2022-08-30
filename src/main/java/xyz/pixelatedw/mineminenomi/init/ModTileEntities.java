/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.tileentity.TileEntityType;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModTileEntities
/*    */ {
/* 21 */   public static final TileEntityType ROOM = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.RoomTileEntity::new, new Block[] { ModBlocks.OPE_MID }).build(null);
/* 22 */   public static final TileEntityType TORIKAGO = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity::new, new Block[] { ModBlocks.STRING_MID }).build(null);
/* 23 */   public static final TileEntityType CUSTOM_SPAWNER = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity::new, new Block[] { ModBlocks.CUSTOM_SPAWNER }).build(null);
/* 24 */   public static final TileEntityType WANTED_POSTER_PACKAGE = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity::new, new Block[] { ModBlocks.WANTED_POSTER_PACKAGE }).build(null);
/* 25 */   public static final TileEntityType WANTED_POSTER = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity::new, new Block[] { ModBlocks.WANTED_POSTER }).build(null);
/* 26 */   public static final TileEntityType ABILITY_PROTECTION = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity::new, new Block[] { ModBlocks.ABILITY_PROTECTION }).build(null);
/* 27 */   public static final TileEntityType DEN_DEN_MUSHI = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity::new, new Block[] { ModBlocks.DEN_DEN_MUSHI }).build(null);
/* 28 */   public static final TileEntityType CANNON = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity::new, new Block[] { ModBlocks.CANNON }).build(null);
/* 29 */   public static final TileEntityType PONEGLYPH = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity::new, new Block[] { ModBlocks.PONEGLYPH }).build(null);
/* 30 */   public static final TileEntityType CHALLENGE_ARENA = WyRegistry.createTileEntity(xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity::new, new Block[] { ModBlocks.CHALLENGE_ARENA }).build(null);
/*    */ 
/*    */   
/*    */   static {
/* 34 */     WyRegistry.registerTileEntity(ROOM, "room");
/* 35 */     WyRegistry.registerTileEntity(TORIKAGO, "torikago");
/* 36 */     WyRegistry.registerTileEntity(CUSTOM_SPAWNER, "custom_spawner");
/* 37 */     WyRegistry.registerTileEntity(WANTED_POSTER_PACKAGE, "wanted_poster_package");
/* 38 */     WyRegistry.registerTileEntity(WANTED_POSTER, "wanted_poster");
/* 39 */     WyRegistry.registerTileEntity(ABILITY_PROTECTION, "ability_protection");
/* 40 */     WyRegistry.registerTileEntity(DEN_DEN_MUSHI, "den_den_mushi");
/* 41 */     WyRegistry.registerTileEntity(CANNON, "cannon");
/* 42 */     WyRegistry.registerTileEntity(PONEGLYPH, "poneglyph");
/* 43 */     WyRegistry.registerTileEntity(CHALLENGE_ARENA, "challenge_arena");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModTileEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */