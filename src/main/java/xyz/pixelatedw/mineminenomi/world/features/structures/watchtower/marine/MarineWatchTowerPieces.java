/*     */ package xyz.pixelatedw.mineminenomi.world.features.structures.watchtower.marine;

/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
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
/*     */ public class MarineWatchTowerPieces
/*     */ {
/*  34 */   private static final ResourceLocation PIECE = new ResourceLocation("mineminenomi", "marine/watch_tower");
/*     */   
/*  36 */   private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ((Builder)ImmutableMap.builder())
/*  37 */     .put(PIECE, BlockPos.ZERO)
/*  38 */     .build();
/*     */   
/*  40 */   private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ((Builder)ImmutableMap.builder())
/*  41 */     .put(PIECE, BlockPos.ZERO)
/*  42 */     .build();
/*     */ 
/*     */   
/*     */   public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components) {
/*  46 */     components.add(new Piece(templateManager, PIECE, pos));
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
/*  57 */       super(ModFeatures.Pieces.MARINE_WATCH_TOWER_PIECE, nbt);
/*  58 */       this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
/*  59 */       this.rotation = Rotation.valueOf(nbt.getString("Rot"));
/*  60 */       int centerX = nbt.getShort("CenterX");
/*  61 */       int centerY = nbt.getShort("CenterY");
/*  62 */       int centerZ = nbt.getShort("CenterZ");
/*  63 */       this.centerPosition = new BlockPos(centerX, centerY, centerZ);
/*  64 */       build(template);
/*     */     }
/*     */ 
/*     */     
/*     */     public Piece(TemplateManager template, ResourceLocation res, BlockPos pos) {
/*  69 */       super(ModFeatures.Pieces.MARINE_WATCH_TOWER_PIECE, 0);
/*  70 */       this.rotation = Rotation.randomRotation(new Random());
/*  71 */       this.resourceLocation = res;
/*  72 */       BlockPos blockpos = (BlockPos)MarineWatchTowerPieces.POSITION_OFFSET.get(this.resourceLocation);
/*  73 */       this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/*  74 */       this.centerPosition = pos;
/*  75 */       build(template);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void readAdditional(CompoundNBT nbt) {
/*  81 */       super.readAdditional(nbt);
/*  82 */       nbt.putString("Template", this.resourceLocation.toString());
/*  83 */       nbt.putString("Rot", this.rotation.name());
/*  84 */       nbt.putInt("CenterX", this.centerPosition.getX());
/*  85 */       nbt.putInt("CenterY", this.centerPosition.getY());
/*  86 */       nbt.putInt("CenterZ", this.centerPosition.getZ());
/*     */     }
/*     */ 
/*     */     
/*     */     private void build(TemplateManager templateManager) {
/*  91 */       Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
/*  92 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineWatchTowerPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
/*  93 */       setup(template, this.templatePosition, placementsettings);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb) {
/* 100 */       if (function.equals("tower_chest")) {
/* 101 */         StructuresHelper.spawnLoot(world, pos, ModLootTables.LARGE_BANDIT_BASE_TOWER_CHEST);
/*     */       }
/*     */       
/* 104 */       if (function.equals("tower_spawn")) {
/* 105 */         StructuresHelper.spawnMobs(world, pos, StructuresHelper.StructureFaction.MARINE, StructuresHelper.StructureSpawnType.SNIPER, 3);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean create(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox bb, ChunkPos chunkPos) {
/* 111 */       if (this.centerPosition == null) {
/*     */         
/* 113 */         WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
/* 114 */         return false;
/*     */       } 
/*     */       
/* 117 */       PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset((BlockPos)MarineWatchTowerPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor((StructureProcessor)BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
/* 118 */       BlockPos offset = (BlockPos)MarineWatchTowerPieces.POSITION_OFFSET.get(this.resourceLocation);
/* 119 */       this.templatePosition.add((Vec3i)Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
/*     */       
/* 121 */       return super.create(world, generator, random, bb, chunkPos);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\features\structures\watchtower\marine\MarineWatchTowerPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */