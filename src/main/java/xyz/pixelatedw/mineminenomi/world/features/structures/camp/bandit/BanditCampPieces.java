/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.camp.bandit;
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
/*     */ public class BanditCampPieces
/*     */ {
/*  37 */   private static final ResourceLocation SMALL_TENT = new ResourceLocation("mineminenomi", "bandit/small_tent");
/*  38 */   private static final ResourceLocation LARGE_TENT = new ResourceLocation("mineminenomi", "bandit/large_tent");
/*  39 */   private static final ResourceLocation FIRE_PLACE = new ResourceLocation("mineminenomi", "props/fire_place");
/*  40 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = (Map<ResourceLocation, BlockPos>)ImmutableMap.of(SMALL_TENT, new BlockPos(3, 0, 3), LARGE_TENT, new BlockPos(0, 0, 0), FIRE_PLACE, new BlockPos(0, 0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, Random rand, List<StructurePiece> components) {
/*  46 */     List<BlockPos> list = new ArrayList<>();
/*  47 */     boolean hasExtraTents = false;
/*     */     
/*  49 */     if (WyHelper.randomDouble() < 0.1D) {
/*  50 */       hasExtraTents = true;
/*     */     }
/*  52 */     components.add(new Piece(templateManager, FIRE_PLACE, pos, Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*     */     
/*  54 */     components.add(new Piece(templateManager, LARGE_TENT, pos.add(13, 0, 0), Rotation.CLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  55 */     if (hasExtraTents) {
/*     */       
/*  57 */       components.add(new Piece(templateManager, LARGE_TENT, pos.add(-19, 0, 6), Rotation.COUNTERCLOCKWISE_90, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  58 */       list.add(pos.add(-19, 0, 6));
/*     */     } 
/*     */     
/*  61 */     list.add(pos.add(13, 0, 0));
/*     */     
/*  63 */     for (int i = 0; i < (hasExtraTents ? 6 : 4); i++) {
/*     */       
/*  65 */       BlockPos piecePos = trySpawnTent(pos, 0, list);
/*  66 */       if (piecePos != null) {
/*     */         
/*  68 */         components.add(new Piece(templateManager, SMALL_TENT, piecePos, Rotation.randomRotation(rand), (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*  69 */         list.add(piecePos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static BlockPos trySpawnTent(BlockPos pos, int attempts, List<BlockPos> list) {
/*  75 */     if (attempts > 20)
/*  76 */       return null; 
/*  77 */     for (BlockPos tentPos : list) {
/*     */       
/*  79 */       if (Math.sqrt(tentPos.distanceSq((Vec3i)pos)) < 9.0D)
/*  80 */         return trySpawnTent(pos, ++attempts, list); 
/*     */     } 
/*  82 */     BlockPos random = new BlockPos(WyHelper.randomWithRange(-40, 40), 0.0D, WyHelper.randomWithRange(-40, 40));
/*  83 */     BlockPos piecePos = pos.add(random.getX(), 0, random.getZ());
/*  84 */     if (Math.sqrt(pos.distanceSq((Vec3i)piecePos)) < 15.0D) {
/*  85 */       return trySpawnTent(pos, ++attempts, list);
/*     */     }
/*  87 */     return piecePos;
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
/*  98 */       super(ModFeatures.Pieces.BANDIT_CAMP_PIECE, nbt);
/*  99 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/* 100 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/* 101 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/* 102 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot, StructureProcessor proc) {
/* 107 */       super(ModFeatures.Pieces.BANDIT_CAMP_PIECE, 0);
/* 108 */       this.rotation = rot;
/* 109 */       this.resourceLocation = res;
/* 110 */       this.templatePosition = pos;
/* 111 */       this.processor = proc;
/* 112 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/* 118 */       super.readAdditional(nbt);
/* 119 */       nbt.putString("Template", this.resourceLocation.toString());
/* 120 */       nbt.putString("Rot", this.rotation.name());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/* 125 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/* 126 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 127 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 134 */       if (function.equals("small_tent_chest")) {
/* 135 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_SMALL_TENT_CHEST);
/*     */       }
/* 137 */       if (function.equals("large_tent_chest")) {
/* 138 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.CAMP_LARGE_TENT_CHEST);
/*     */       }
/*     */       
/* 141 */       if (function.equals("small_tent_spawn")) {
/* 142 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 144 */       if (function.equals("large_tent_spawn")) {
/* 145 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 2);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 151 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 152 */       BlockPos offset = new BlockPos(0, 0, 0);
/* 153 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 155 */       BlockPos blockpos2 = this.templatePosition;
/* 156 */       int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ());
/* 157 */       this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
/*     */       
/* 159 */       boolean flag = super.create(world, generator, random, bb, chunkPos);
/*     */       
/* 161 */       this.templatePosition = blockpos2;
/* 162 */       return flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\camp\bandit\BanditCampPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */