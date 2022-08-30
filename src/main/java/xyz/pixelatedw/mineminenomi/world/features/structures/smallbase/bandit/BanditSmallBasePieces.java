/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit;
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
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BanditSmallBasePieces
/*     */ {
/*  36 */   private static final ResourceLocation HOUSE = new ResourceLocation("mineminenomi", "bandit/small_base_house");
/*  37 */   private static final ResourceLocation UNDERGROUND = new ResourceLocation("mineminenomi", "bandit/small_base_underground");
/*     */   
/*  39 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  40 */     .put(HOUSE, new BlockPos(18, 27, 23))
/*  41 */     .put(UNDERGROUND, new BlockPos(0, 0, 0))
/*  42 */     .build();
/*     */   
/*  44 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
/*  45 */     .put(HOUSE, new BlockPos(0, 0, 0))
/*  46 */     .put(UNDERGROUND, new BlockPos(0, 0, 0))
/*  47 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  51 */     components.add(new Piece(templateManager, HOUSE, pos));
/*  52 */     components.add(new Piece(templateManager, UNDERGROUND, pos));
/*     */   }
/*     */   
/*     */   public static class Piece
/*     */     extends TemplateStructurePiece
/*     */   {
/*     */     private ResourceLocation resourceLocation;
/*     */     private Rotation rotation;
/*     */     private BlockPos centerPosition;
/*     */     
/*     */     public Piece(TemplateManager template, CompoundNBT nbt) {
/*  63 */       super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, nbt);
/*  64 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  65 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  66 */       int centerX = nbt.getShort("CenterX");
/*  67 */       int centerY = nbt.getShort("CenterY");
/*  68 */       int centerZ = nbt.getShort("CenterZ");
/*  69 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  70 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos) {
/*  75 */       super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, 0);
/*  76 */       this.rotation = Rotation.NONE;
/*  77 */       this.resourceLocation = res;
/*  78 */       BlockPos blockpos = (BlockPos)BanditSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/*  79 */       this.centerPosition = pos;
/*  80 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
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
/*  98 */       BlockIgnoreStructureProcessor blockIgnoreStructureProcessor = BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK;
/*     */ 
/*     */       
/* 101 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)blockIgnoreStructureProcessor);
/* 102 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 109 */       if (function.equals("equipment_chest")) {
/* 110 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_MINE_CHEST);
/*     */       }
/* 112 */       if (function.equals("gem_chest")) {
/* 113 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_ORES_CHEST);
/*     */       }
/* 115 */       if (function.equals("lab_chest")) {
/* 116 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_LAB_CHEST);
/*     */       }
/* 118 */       if (function.equals("gold_chest")) {
/* 119 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.SMALL_BANDIT_BASE_GOLD_CHEST);
/*     */       }
/*     */ 
/*     */       
/* 123 */       if (function.equals("lounge_spawn")) {
/* 124 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 126 */       if (function.equals("hall_spawn")) {
/* 127 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/* 129 */       if (function.equals("bedroom_spawn_1")) {
/* 130 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 132 */       if (function.equals("bedroom_spawn_2")) {
/* 133 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 1);
/*     */       }
/* 135 */       if (function.equals("lab_spawn")) {
/* 136 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 142 */       if (this.centerPosition == null) {
/*     */         
/* 144 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 145 */         return false;
/*     */       } 
/*     */       
/* 148 */       BlockIgnoreStructureProcessor blockIgnoreStructureProcessor = BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK;
/*     */ 
/*     */       
/* 151 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditSmallBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)blockIgnoreStructureProcessor);
/* 152 */       BlockPos offset = (BlockPos)BanditSmallBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 153 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/* 154 */       boolean flag = false;
/*     */ 
/*     */       
/*     */       try {
/* 158 */         BlockPos blockpos2 = this.templatePosition;
/* 159 */         int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.centerPosition.getX(), this.centerPosition.getZ());
/* 160 */         this.templatePosition = this.templatePosition.add(0, i - 118, 0);
/*     */         
/* 162 */         flag = super.create(world, generator, random, bb, chunkPos);
/*     */         
/* 164 */         this.templatePosition = blockpos2;
/*     */       }
/* 166 */       catch (Exception e) {
/*     */         
/* 168 */         e.printStackTrace();
/*     */       } 
/*     */       
/* 171 */       return flag;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\smallbase\bandit\BanditSmallBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */