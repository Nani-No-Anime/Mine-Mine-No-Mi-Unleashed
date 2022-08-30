/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.marine;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarineSmallBasePieces
/*     */ {
/*  35 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "marine/small_base");
/*     */   
/*  37 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  38 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  39 */     .build();
/*     */   
/*  41 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  42 */     .put(PIECE, new BlockPos(0, 0, 0))
/*  43 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  47 */     components.add(new Piece(templateManager, PIECE, pos, (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
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
/*  59 */       super(ModFeatures.Pieces.MARINE_SMALL_BASE_PIECE, nbt);
/*  60 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  61 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  62 */       int centerX = nbt.getShort("CenterX");
/*  63 */       int centerY = nbt.getShort("CenterY");
/*  64 */       int centerZ = nbt.getShort("CenterZ");
/*  65 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  66 */       this.processor = (StructureProcessor)BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
/*  67 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc) {
/*  72 */       super(ModFeatures.Pieces.MARINE_SMALL_BASE_PIECE, 0);
/*  73 */       this.rotation = Rotation.NONE;
/*  74 */       this.resourceLocation = res;
/*  75 */       BlockPos blockpos = (BlockPos)MarineSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/*  76 */       this.centerPosition = pos;
/*  77 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  78 */       this.processor = proc;
/*  79 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  85 */       super.readAdditional(nbt);
/*  86 */       nbt.putString("Template", this.resourceLocation.toString());
/*  87 */       nbt.putString("Rot", this.rotation.name());
/*  88 */       nbt.putInt("CenterX", this.centerPosition.getX());
/*  89 */       nbt.putInt("CenterY", this.centerPosition.getY());
/*  90 */       nbt.putInt("CenterZ", this.centerPosition.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  95 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/*  96 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/*  97 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 104 */       if (function.equals("captain_chest")) {
/* 105 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_MARINE_BASE_CAPTAIN_CHEST);
/*     */       }
/* 107 */       if (function.equals("dorm_supplies")) {
/* 108 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_MARINE_BASE_DORM_CHEST);
/*     */       }
/* 110 */       if (function.equals("food_supplies")) {
/* 111 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_MARINE_BASE_FOOD_CHEST);
/*     */       }
/*     */       
/* 114 */       if (function.equals("desk_spawn")) {
/* 115 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 1);
/*     */       }
/* 117 */       if (function.equals("main_hall_spawn")) {
/* 118 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 120 */       if (function.equals("bedrooms_spawn")) {
/* 121 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.BOMBER, 3);
/*     */       }
/* 123 */       if (function.equals("upstairs_spawn")) {
/* 124 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 126 */       if (function.equals("captain_room_spawn")) {
/* 127 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 133 */       if (this.centerPosition == null) {
/*     */         
/* 135 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 136 */         return false;
/*     */       } 
/*     */       
/* 139 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 140 */       BlockPos offset = (BlockPos)MarineSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 141 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 143 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\marine\MarineSmallBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */