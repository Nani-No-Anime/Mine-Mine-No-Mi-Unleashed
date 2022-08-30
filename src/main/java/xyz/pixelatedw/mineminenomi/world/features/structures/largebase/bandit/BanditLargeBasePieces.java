/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.bandit;
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
/*     */ public class BanditLargeBasePieces
/*     */ {
/*  35 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "bandit/large_base");
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
/*  59 */       super(ModFeatures.Pieces.BANDIT_LARGE_BASE_PIECE, nbt);
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
/*  72 */       super(ModFeatures.Pieces.BANDIT_LARGE_BASE_PIECE, 0);
/*  73 */       this.rotation = Rotation.NONE;
/*  74 */       this.resourceLocation = res;
/*  75 */       BlockPos blockpos = (BlockPos)BanditLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
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
/*  96 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/*  97 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 104 */       if (function.equals("tower_supplies")) {
/* 105 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_TOWER_CHEST);
/*     */       }
/* 107 */       if (function.equals("tent_supplies")) {
/* 108 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_TENT_CHEST);
/*     */       }
/* 110 */       if (function.equals("secret_stash")) {
/* 111 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_SECRET_STASH_CHEST);
/*     */       }
/* 113 */       if (function.equals("kitchen_supplies")) {
/* 114 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_FOOD_CHEST);
/*     */       }
/* 116 */       if (function.equals("dorm_supplies")) {
/* 117 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_DORM_CHEST);
/*     */       }
/*     */       
/* 120 */       if (function.equals("tent_spawn")) {
/* 121 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 2);
/*     */       }
/* 123 */       if (function.equals("back_spawn")) {
/* 124 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 6);
/*     */       }
/* 126 */       if (function.equals("tower_spawn")) {
/* 127 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 1);
/*     */       }
/* 129 */       if (function.equals("main_tower_spawn")) {
/* 130 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.SNIPER, 3);
/*     */       }
/* 132 */       if (function.equals("court_spawn")) {
/* 133 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.GRUNT, 5);
/*     */       }
/* 135 */       if (function.equals("storage_spawn")) {
/* 136 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.CAPTAIN, 3);
/*     */       }
/* 138 */       if (function.equals("kitchen_spawn")) {
/* 139 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/* 141 */       if (function.equals("bedroom_spawn")) {
/* 142 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.BANDIT, StructuresHelper.StructureSpawnType.BRUTE, 3);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 148 */       if (this.centerPosition == null) {
/*     */         
/* 150 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 151 */         return false;
/*     */       } 
/*     */       
/* 154 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)BanditLargeBasePieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
/* 155 */       BlockPos offset = (BlockPos)BanditLargeBasePieces.POSITION_OFFSET.get(this.resourceLocation);
/* 156 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 158 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\largebase\bandit\BanditLargeBasePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */