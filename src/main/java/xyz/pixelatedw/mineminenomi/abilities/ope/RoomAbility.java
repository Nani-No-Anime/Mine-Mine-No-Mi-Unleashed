/*     */ package xyz.pixelatedw.mineminenomi.abilities.ope;
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
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.RoomTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class RoomAbility extends ContinuousAbility implements IParallelContinuousAbility {
/*  31 */   public static final RoomAbility INSTANCE = new RoomAbility();
/*     */   
/*  33 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { ModBlocks.OPE }).setBypassGriefRule();
/*  34 */   private List<BlockPos> blockList = new ArrayList<>();
/*  35 */   private List<BlockPos> placedBlockList = new ArrayList<>();
/*     */   public static final int MAX_ROOM_SIZE = 45;
/*     */   public static final int MAX_THRESHOLD = 2;
/*  38 */   private int roomSize = 0;
/*  39 */   private int chargingTicks = 0;
/*     */   
/*     */   private BlockPos centerBlock;
/*     */   
/*     */   public RoomAbility() {
/*  44 */     super("ROOM", AbilityHelper.getDevilFruitCategory());
/*  45 */     setMaxCooldown(10.0D);
/*  46 */     setDescription("Creates a spherical space around the user in which they can manipulate anything or use other skills");
/*     */     
/*  48 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  49 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  50 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  51 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  56 */     player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CREATE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*  57 */     this.chargingTicks = 10;
/*  58 */     setThreshold(2.0D);
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
/*  64 */     if (getThreshold() == 0) {
/*     */       
/*  66 */       if (this.blockList.isEmpty()) {
/*     */         
/*  68 */         this.blockList.addAll(AbilityHelper.createSphere(player.world, player.getPosition(), this.roomSize, true, ModBlocks.OPE, 0, GRIEF_RULE));
/*  69 */         this.centerBlock = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
/*  70 */         player.world.setBlockState(this.centerBlock, ModBlocks.OPE_MID.getDefaultState());
/*  71 */         TileEntity te = player.world.getTileEntity(this.centerBlock);
/*  72 */         if (te != null && te instanceof RoomTileEntity) {
/*     */           
/*  74 */           ((RoomTileEntity)te).setOwner((LivingEntity)player);
/*  75 */           ((RoomTileEntity)te).markDirty();
/*     */         } 
/*  77 */         this.blockList.add(new BlockPos(MathHelper.floor(player.getPosX()), MathHelper.floor(player.getPosY()), MathHelper.floor(player.getPosZ())));
/*  78 */         this.placedBlockList.addAll(this.blockList);
/*  79 */         setThreshold(0.0D);
/*     */       }
/*     */       else {
/*     */         
/*  83 */         int placedBlocks = 0;
/*  84 */         Iterator<BlockPos> iter = this.placedBlockList.iterator();
/*  85 */         while (iter.hasNext())
/*     */         {
/*  87 */           BlockPos pos = iter.next();
/*  88 */           player.world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), ModBlocks.OPE.getDefaultState(), 0);
/*  89 */           iter.remove();
/*  90 */           placedBlocks++;
/*  91 */           if (placedBlocks > 500) {
/*     */             return;
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*  97 */     } else if (0 >= this.chargingTicks) {
/*     */       
/*  99 */       this.chargingTicks = 19;
/* 100 */       player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CHARGE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     } else {
/* 102 */       this.chargingTicks--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 108 */     if (getThreshold() > 0) {
/*     */       
/* 110 */       setThreshold(0.0D);
/* 111 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/* 112 */       this.roomSize = Math.max(8, (int)(45.0D * this.continueTime / 20.0D / 2.0D));
/* 113 */       player.world.playSound(null, player.getPosition(), ModSounds.ROOM_EXPAND_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/* 114 */       return false;
/*     */     } 
/*     */     
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStopContinuityEvent(PlayerEntity player) {
/* 122 */     for (BlockPos pos : this.blockList) {
/*     */       
/* 124 */       Block currentBlock = player.world.getBlockState(pos).getBlock();
/* 125 */       if (currentBlock == ModBlocks.OPE || currentBlock == ModBlocks.OPE_MID) {
/* 126 */         player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
/*     */       }
/*     */     } 
/* 129 */     this.blockList.clear();
/* 130 */     this.placedBlockList.clear();
/* 131 */     player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CLOSE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     
/* 133 */     setMaxCooldown((10.0F * this.roomSize / 45.0F));
/* 134 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */     
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getROOMSize() {
/* 141 */     return this.roomSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getCenterBlock() {
/* 146 */     return this.centerBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInThisRoom(Entity entity) {
/* 151 */     return isInsideROOM(entity.world, entity.getPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsideROOM(World world, BlockPos pos) {
/* 156 */     int roomSize = this.roomSize;
/* 157 */     for (int i = -roomSize; i < roomSize; i++) {
/* 158 */       for (int j = -roomSize; j < roomSize; j++) {
/* 159 */         for (int k = -roomSize; k < roomSize; k++) {
/*     */           
/* 161 */           BlockPos posCheck = pos.add(i, j, k);
/* 162 */           if (world.getBlockState(posCheck).getBlock() == ModBlocks.OPE_MID) {
/*     */             
/* 164 */             double distance = pos.distanceSq((Vec3i)posCheck);
/* 165 */             if (distance < ((roomSize - 1) * (roomSize - 1)))
/* 166 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 170 */     }  return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\RoomAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */