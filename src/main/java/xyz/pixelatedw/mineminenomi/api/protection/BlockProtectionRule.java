/*     */ package xyz.pixelatedw.mineminenomi.api.protection;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockProtectionRule
/*     */ {
/*     */   private boolean bypassGriefRule = false;
/*  19 */   private List<IReplaceBlockRule> approvedRules = new ArrayList<>();
/*  20 */   private HashSet<Block> blocks = new HashSet<>();
/*  21 */   private HashSet<Block> bannedBlocks = new HashSet<>();
/*     */ 
/*     */   
/*     */   public BlockProtectionRule(BlockProtectionRule... rules) {
/*  25 */     for (BlockProtectionRule rule : rules) {
/*     */       
/*  27 */       this.blocks.addAll(rule.getApprovedBlocks());
/*  28 */       this.approvedRules.addAll(rule.getApprovedRules());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HashSet<Block> getBannedBlocks() {
/*  35 */     return this.bannedBlocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule addBannedBlocks(HashSet<Block> set) {
/*  40 */     Block[] arr = new Block[set.size()];
/*  41 */     set.toArray(arr);
/*  42 */     return addBannedBlocks(arr);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule addBannedBlocks(Block... blocks) {
/*  47 */     for (Block block : blocks) {
/*     */       
/*  49 */       if (this.blocks.contains(block))
/*  50 */         this.blocks.remove(block); 
/*     */     } 
/*  52 */     this.bannedBlocks.addAll(Lists.newArrayList((Block[])blocks));
/*  53 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public HashSet<Block> getApprovedBlocks() {
/*  58 */     return this.blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IReplaceBlockRule> getApprovedRules() {
/*  63 */     return this.approvedRules;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule addReplaceRules(IReplaceBlockRule fn) {
/*  68 */     this.approvedRules.add(fn);
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule addApprovedBlocks(Block... blocks) {
/*  74 */     this.blocks.addAll(Lists.newArrayList((Block[])blocks));
/*  75 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule addApprovedMaterials(Material... materials) {
/*  80 */     for (Block block : ForgeRegistries.BLOCKS.getValues()) {
/*     */       
/*  82 */       for (Material mat : materials) {
/*     */         
/*  84 */         if (block.getDefaultState().getMaterial() == mat && !this.blocks.contains(block))
/*  85 */           this.blocks.add(block); 
/*     */       } 
/*     */     } 
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBypassGriefRule() {
/*  93 */     return this.bypassGriefRule;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockProtectionRule setBypassGriefRule() {
/*  98 */     this.bypassGriefRule = true;
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean check(World world, BlockPos pos, BlockState state) {
/* 104 */     for (IReplaceBlockRule fn : this.approvedRules) {
/* 105 */       fn.replace(world, pos, state);
/*     */     }
/* 107 */     return isPresent(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPresent(BlockState state) {
/* 112 */     if (this.blocks.contains(state.getBlock())) {
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBanned(Block block) {
/* 120 */     return this.bannedBlocks.contains(block);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IReplaceBlockRule {
/*     */     boolean replace(World param1World, BlockPos param1BlockPos, BlockState param1BlockState);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\BlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */