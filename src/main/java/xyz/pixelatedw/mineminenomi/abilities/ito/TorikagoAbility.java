/*     */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class TorikagoAbility extends ContinuousAbility implements IParallelContinuousAbility {
/*  28 */   public static final TorikagoAbility INSTANCE = new TorikagoAbility();
/*     */   
/*  30 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { ModBlocks.STRING_WALL }).setBypassGriefRule();
/*  31 */   private List<BlockPos> blockList = new ArrayList<>();
/*  32 */   private List<BlockPos> placedBlockList = new ArrayList<>();
/*     */   public static final int MAX_CAGE_SIZE = 60;
/*     */   public static final int MAX_THRESHOLD = 3;
/*  35 */   public int roomSize = 0;
/*  36 */   private int chargingTicks = 0;
/*     */ 
/*     */   
/*     */   public TorikagoAbility() {
/*  40 */     super("Torikago", AbilityHelper.getDevilFruitCategory());
/*  41 */     setMaxCooldown(20.0D);
/*  42 */     setDescription("Creates an indestructible dome made of strings, that damage anyone who touches them");
/*     */     
/*  44 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  45 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  46 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*  47 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  52 */     this.chargingTicks = 10;
/*  53 */     setThreshold(3.0D);
/*  54 */     return (player.getFoodStats().getFoodLevel() > 6);
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
/*  59 */     if (getThreshold() == 0) {
/*     */       
/*  61 */       if (this.blockList.isEmpty()) {
/*     */         
/*  63 */         this.blockList.addAll(AbilityHelper.createSphere(player.world, player.getPosition(), this.roomSize, true, ModBlocks.STRING_WALL, 0, GRIEF_RULE));
/*  64 */         BlockPos center = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
/*  65 */         player.world.setBlockState(center, ModBlocks.STRING_MID.getDefaultState());
/*  66 */         TileEntity te = player.world.getTileEntity(center);
/*  67 */         if (te != null && te instanceof TorikagoTileEntity) {
/*     */           
/*  69 */           ((TorikagoTileEntity)te).setOwner((LivingEntity)player);
/*  70 */           ((TorikagoTileEntity)te).markDirty();
/*     */         } 
/*  72 */         this.blockList.add(new BlockPos(MathHelper.floor(player.getPosX()), MathHelper.floor(player.getPosY()), MathHelper.floor(player.getPosZ())));
/*  73 */         this.placedBlockList.addAll(this.blockList);
/*  74 */         setThreshold(0.0D);
/*     */       }
/*     */       else {
/*     */         
/*  78 */         int placedBlocks = 0;
/*  79 */         Iterator<BlockPos> iter = this.placedBlockList.iterator();
/*  80 */         while (iter.hasNext()) {
/*     */           
/*  82 */           BlockPos pos = iter.next();
/*  83 */           player.world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), ModBlocks.STRING_WALL.getDefaultState(), 0);
/*  84 */           iter.remove();
/*  85 */           placedBlocks++;
/*  86 */           if (placedBlocks > 500)
/*     */             return; 
/*     */         } 
/*     */       } 
/*  90 */       player.addExhaustion(0.005F * this.roomSize / 30.0F);
/*  91 */       if (player.getFoodStats().getFoodLevel() < 6) {
/*  92 */         endContinuity(player);
/*     */       
/*     */       }
/*     */     }
/*  96 */     else if (0 >= this.chargingTicks) {
/*     */       
/*  98 */       this.chargingTicks = 19;
/*     */     } else {
/*     */       
/* 101 */       this.chargingTicks--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 107 */     if (getThreshold() > 0) {
/*     */       
/* 109 */       setThreshold(0.0D);
/* 110 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/* 111 */       this.roomSize = (int)(20.0F + 40.0F * this.continueTime / 60.0F);
/* 112 */       return false;
/*     */     } 
/*     */     
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStopContinuityEvent(PlayerEntity player) {
/* 120 */     for (BlockPos pos : this.blockList) {
/*     */       
/* 122 */       Block currentBlock = player.world.getBlockState(pos).getBlock();
/* 123 */       if (currentBlock == ModBlocks.STRING_WALL || currentBlock == ModBlocks.STRING_MID)
/* 124 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
/*     */     } 
/* 126 */     this.blockList.clear();
/* 127 */     this.placedBlockList.clear();
/*     */     
/* 129 */     setMaxCooldown((10.0F * this.roomSize / 30.0F));
/* 130 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInThisTorikago(Entity entity) {
/* 137 */     int roomSize = this.roomSize;
/* 138 */     for (int i = -roomSize; i < roomSize; i++) {
/* 139 */       for (int j = -roomSize; j < roomSize; j++) {
/* 140 */         for (int k = -roomSize; k < roomSize; k++) {
/*     */           
/* 142 */           if (entity.world.getBlockState(new BlockPos(entity.getPosX() + i, entity.getPosY() + j, entity.getPosZ() + k)).getBlock() == ModBlocks.STRING_MID) {
/*     */             
/* 144 */             double distance = entity.getDistanceSq(entity.getPosX() + i, entity.getPosY() + j, entity.getPosZ() + k);
/* 145 */             if (distance < ((roomSize - 1) * (roomSize - 1)))
/* 146 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 150 */     }  return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\TorikagoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */