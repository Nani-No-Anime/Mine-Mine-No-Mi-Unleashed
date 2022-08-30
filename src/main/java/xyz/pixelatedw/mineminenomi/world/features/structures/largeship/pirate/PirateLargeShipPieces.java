/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MutableBoundingBox;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.gen.ChunkGenerator;
/*     */ import net.minecraft.world.gen.feature.structure.StructurePiece;
/*     */ import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
/*     */ import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.PlacementSettings;
/*     */ import net.minecraft.world.gen.feature.template.StructureProcessor;
/*     */ import net.minecraft.world.gen.feature.template.Template;
/*     */ import net.minecraft.world.gen.feature.template.TemplateManager;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PirateLargeShipPieces
/*     */ {
/*  33 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "pirate/large_ship");
/*     */   
/*  35 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  36 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  37 */     .build();
/*     */   
/*  39 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  40 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  41 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, Rotation rot, List<StructurePiece> components) {
/*  45 */     components.add(new Piece(templateManager, PIECE, pos, rot));
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/*  55 */       super(ModFeatures.Pieces.PIRATE_LARGE_SHIP_PIECE, nbt);
/*  56 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  57 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  58 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot) {
/*  63 */       super(ModFeatures.Pieces.PIRATE_LARGE_SHIP_PIECE, 0);
/*  64 */       this.rotation = rot;
/*  65 */       this.resourceLocation = res;
/*  66 */       BlockPos blockpos = (BlockPos)PirateLargeShipPieces.POSITION_OFFSET.get(this.resourceLocation);
/*  67 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  68 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  74 */       super.readAdditional(nbt);
/*  75 */       nbt.putString("Template", this.resourceLocation.toString());
/*  76 */       nbt.putString("Rot", this.rotation.name());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  81 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/*  82 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)PirateLargeShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
/*  83 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/*  90 */       if (function.equals("captain_chest")) {
/*  91 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_PIRATE_SHIP_CAPTAIN_CHEST);
/*     */       }
/*  93 */       if (function.equals("supplies_chest")) {
/*  94 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_PIRATE_SHIP_SUPPLIES_CHEST);
/*     */       }
/*  96 */       if (function.equals("treasure_chest")) {
/*  97 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_PIRATE_SHIP_TREASURE_CHEST);
/*     */       }
/*  99 */       if (function.equals("weapons_chest")) {
/* 100 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_PIRATE_SHIP_WEAPONS_CHEST);
/*     */       }
/*     */       
/* 103 */       if (function.equals("deck_spawn")) {
/* 104 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 106 */       if (function.equals("wheel_spawn")) {
/* 107 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/* 109 */       if (function.equals("storage_spawn_1")) {
/* 110 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/* 112 */       if (function.equals("storage_spawn_2")) {
/* 113 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 10);
/*     */       }
/* 115 */       if (function.equals("bedroom_spawn")) {
/* 116 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 118 */       if (function.equals("captain_room_spawn_1")) {
/* 119 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 121 */       if (function.equals("captain_room_spawn_2")) {
/* 122 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.PIRATE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 131 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)PirateLargeShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
/* 132 */       BlockPos offset = (BlockPos)PirateLargeShipPieces.POSITION_OFFSET.get(this.resourceLocation);
/* 133 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 135 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largeship\pirate\PirateLargeShipPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */