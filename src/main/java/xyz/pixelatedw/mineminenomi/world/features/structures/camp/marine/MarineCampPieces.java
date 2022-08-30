/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.camp.marine;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
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
/*     */ import net.minecraft.world.gen.Heightmap;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarineCampPieces
/*     */ {
/*  37 */   private static final ResourceLocation SMALL_TENT = new ResourceLocation("mineminenomi", "marine/small_tent");
/*  38 */   private static final ResourceLocation LARGE_TENT = new ResourceLocation("mineminenomi", "marine/large_tent");
/*  39 */   private static final ResourceLocation FIRE_PLACE = new ResourceLocation("mineminenomi", "props/fire_place");
/*     */   
/*  41 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.of(SMALL_TENT, new BlockPos(3, 0, 3), LARGE_TENT, new BlockPos(0, 0, 0), FIRE_PLACE, new BlockPos(0, 0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, Random rand, List<StructurePiece> components) {
/*  49 */     List<BlockPos> list = new ArrayList<>();
/*  50 */     boolean hasExtraTents = false;
/*     */     
/*  52 */     if (WyHelper.randomDouble() < 0.1D) {
/*  53 */       hasExtraTents = true;
/*     */     }
/*  55 */     components.add(new Piece(templateManager, FIRE_PLACE, pos, Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*     */     
/*  57 */     components.add(new Piece(templateManager, LARGE_TENT, pos.add(13, 0, 0), Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  58 */     if (hasExtraTents) {
/*     */       
/*  60 */       components.add(new Piece(templateManager, LARGE_TENT, pos.add(-19, 0, 6), Rotation.COUNTERCLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  61 */       list.add(pos.add(-19, 0, 6));
/*     */     } 
/*     */     
/*  64 */     list.add(pos.add(13, 0, 0));
/*     */     
/*  66 */     for (int i = 0; i < (hasExtraTents ? 6 : 4); i++) {
/*     */       
/*  68 */       BlockPos piecePos = trySpawnTent(pos, 0, list);
/*  69 */       if (piecePos != null) {
/*     */         
/*  71 */         components.add(new Piece(templateManager, SMALL_TENT, piecePos, Rotation.randomRotation(rand), (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  72 */         list.add(piecePos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static BlockPos trySpawnTent(BlockPos pos, int attempts, List<BlockPos> list) {
/*  78 */     if (attempts > 20)
/*  79 */       return null; 
/*  80 */     for (BlockPos tentPos : list) {
/*     */       
/*  82 */       if (Math.sqrt(tentPos.distanceSq((Vec3i)pos)) < 9.0D)
/*  83 */         return trySpawnTent(pos, ++attempts, list); 
/*     */     } 
/*  85 */     BlockPos random = new BlockPos(WyHelper.randomWithRange(-40, 40), 0.0D, WyHelper.randomWithRange(-40, 40));
/*  86 */     BlockPos piecePos = pos.add(random.getX(), 0, random.getZ());
/*  87 */     if (Math.sqrt(pos.distanceSq((Vec3i)piecePos)) < 15.0D) {
/*  88 */       return trySpawnTent(pos, ++attempts, list);
/*     */     }
/*  90 */     return piecePos;
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     private StructureProcessor processor;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/* 101 */       super(ModFeatures.Pieces.MARINE_CAMP_PIECE, nbt);
/* 102 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/* 103 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/* 104 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/* 105 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot, StructureProcessor proc) {
/* 110 */       super(ModFeatures.Pieces.MARINE_CAMP_PIECE, 0);
/* 111 */       this.rotation = rot;
/* 112 */       this.resourceLocation = res;
/* 113 */       this.templatePosition = pos;
/* 114 */       this.processor = proc;
/* 115 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/* 121 */       super.readAdditional(nbt);
/* 122 */       nbt.putString("Template", this.resourceLocation.toString());
/* 123 */       nbt.putString("Rot", this.rotation.name());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/* 128 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/* 129 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 130 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 137 */       if (function.equals("small_tent_chest")) {
/* 138 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_SMALL_TENT_CHEST);
/*     */       }
/* 140 */       if (function.equals("large_tent_chest")) {
/* 141 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_LARGE_TENT_CHEST);
/*     */       }
/*     */       
/* 144 */       if (function.equals("small_tent_spawn")) {
/* 145 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 147 */       if (function.equals("large_tent_spawn")) {
/* 148 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 2);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 154 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 155 */       BlockPos offset = new BlockPos(0, 0, 0);
/* 156 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 158 */       BlockPos blockpos2 = this.templatePosition;
/* 159 */       int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ());
/* 160 */       this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
/*     */       
/* 162 */       boolean flag = super.create(world, generator, random, bb, chunkPos);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       this.templatePosition = blockpos2;
/* 168 */       return flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\camp\marine\MarineCampPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */