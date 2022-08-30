/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.skyisland.house;
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
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFeatures;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkyIslandHousePieces
/*     */ {
/*  37 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "sky_islands/house");
/*     */   
/*  39 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  40 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  41 */     .build();
/*     */   
/*  43 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  44 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  45 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  49 */     components.add(new Piece(templateManager, PIECE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
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
/*  61 */       super(ModFeatures.Pieces.SKY_ISLAND_HOUSE_PIECE, nbt);
/*  62 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  63 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  64 */       int centerX = nbt.getShort("CenterX");
/*  65 */       int centerY = nbt.getShort("CenterY");
/*  66 */       int centerZ = nbt.getShort("CenterZ");
/*  67 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  68 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/*  69 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
/*  74 */       super(ModFeatures.Pieces.SKY_ISLAND_HOUSE_PIECE, 0);
/*  75 */       this.rotation = Rotation.NONE;
/*  76 */       this.resourceLocation = res;
/*  77 */       BlockPos blockpos = (BlockPos)SkyIslandHousePieces.POSITION_OFFSET.get(this.resourceLocation);
/*  78 */       this.centerPosition = pos;
/*  79 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  80 */       this.processor = proc;
/*  81 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  87 */       super.readAdditional(nbt);
/*  88 */       nbt.putString("Template", this.resourceLocation.toString());
/*  89 */       nbt.putString("Rot", this.rotation.name());
/*  90 */       nbt.putInt("CenterX", this.centerPosition.getX());
/*  91 */       nbt.putInt("CenterY", this.centerPosition.getY());
/*  92 */       nbt.putInt("CenterZ", this.centerPosition.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  97 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/*  98 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandHousePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/*  99 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 108 */       if (function.equals("islander_spawn")) {
/*     */         
/* 110 */         int spawn = rand.nextInt(3);
/* 111 */         if (spawn == 0) {
/* 112 */           StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.CIVILIAN, 3);
/* 113 */         } else if (spawn == 1) {
/* 114 */           StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.SKYPIEAN, StructuresHelper.StructureSpawnType.TRADER, 1);
/* 115 */         } else if (spawn == 2) {
/*     */           
/* 117 */           world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
/* 118 */           if (world.getTileEntity(pos) instanceof CustomSpawnerTileEntity) {
/*     */             
/* 120 */             CustomSpawnerTileEntity spawner = null;
/* 121 */             spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
/* 122 */             spawner.setSpawnerLimit(1);
/* 123 */             spawner.setSpawnerMob(ModEntities.ART_OF_WEATHER_TRAINER);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 132 */       if (this.centerPosition == null) {
/*     */         
/* 134 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 135 */         return false;
/*     */       } 
/*     */       
/* 138 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)SkyIslandHousePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 139 */       BlockPos offset = (BlockPos)SkyIslandHousePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 140 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 142 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\skyisland\house\SkyIslandHousePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */