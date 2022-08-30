/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.camp;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkyIslandCampPieces
/*     */ {
/*  34 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "sky_islands/camp");
/*     */   
/*  36 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  37 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  38 */     .build();
/*     */   
/*  40 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  41 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  42 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  46 */     components.add(new Piece(templateManager, PIECE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     private StructureProcessor processor;
/*     */     private BlockPos centerPosition;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/*  58 */       super(ModFeatures.Pieces.SKY_ISLAND_CAMP_PIECE, nbt);
/*  59 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  60 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  61 */       int centerX = nbt.getShort("CenterX");
/*  62 */       int centerY = nbt.getShort("CenterY");
/*  63 */       int centerZ = nbt.getShort("CenterZ");
/*  64 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  65 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/*  66 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
/*  71 */       super(ModFeatures.Pieces.SKY_ISLAND_CAMP_PIECE, 0);
/*  72 */       this.rotation = Rotation.NONE;
/*  73 */       this.resourceLocation = res;
/*  74 */       BlockPos blockpos = (BlockPos)SkyIslandCampPieces.POSITION_OFFSET.get(this.resourceLocation);
/*  75 */       this.centerPosition = pos;
/*  76 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  77 */       this.processor = proc;
/*  78 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  84 */       super.readAdditional(nbt);
/*  85 */       nbt.putString("Template", this.resourceLocation.toString());
/*  86 */       nbt.putString("Rot", this.rotation.name());
/*  87 */       nbt.putInt("CenterX", this.centerPosition.getX());
/*  88 */       nbt.putInt("CenterY", this.centerPosition.getY());
/*  89 */       nbt.putInt("CenterZ", this.centerPosition.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  94 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/*  95 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/*  96 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 105 */       if (function.equals("campers_spawn")) {
/*     */         
/* 107 */         int spawn = rand.nextInt(4);
/* 108 */         if (spawn > 0) {
/* 109 */           StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
/* 110 */         } else if (spawn == 0) {
/* 111 */           StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 118 */       if (this.centerPosition == null) {
/*     */         
/* 120 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 121 */         return false;
/*     */       } 
/*     */       
/* 124 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandCampPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 125 */       BlockPos offset = (BlockPos)SkyIslandCampPieces.POSITION_OFFSET.get(this.resourceLocation);
/* 126 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 128 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\skyisland\camp\SkyIslandCampPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */