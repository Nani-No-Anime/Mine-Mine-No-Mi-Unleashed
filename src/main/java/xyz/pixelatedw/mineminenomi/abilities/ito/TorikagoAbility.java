package xyz.pixelatedw.mineminenomi.abilities.ito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class TorikagoAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final TorikagoAbility INSTANCE = new TorikagoAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { ModBlocks.STRING_WALL }).setBypassGriefRule();
  private List<BlockPos> blockList = new ArrayList<>();
  private List<BlockPos> placedBlockList = new ArrayList<>();
  public static final int MAX_CAGE_SIZE = 60;
  public static final int MAX_THRESHOLD = 3;
  public int roomSize = 0;
  private int chargingTicks = 0;

  
  public TorikagoAbility() {
    super("Torikago", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("Creates an indestructible dome made of strings, that damage anyone who touches them");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.chargingTicks = 10;
    setThreshold(3.0D);
    return (player.getFoodStats().getFoodLevel() > 6);
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
    if (getThreshold() == 0) {
      
      if (this.blockList.isEmpty()) {
        
        this.blockList.addAll(AbilityHelper.createSphere(player.world, player.getPosition(), this.roomSize, true, ModBlocks.STRING_WALL, 0, GRIEF_RULE));
        BlockPos center = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
        player.world.setBlockState(center, ModBlocks.STRING_MID.getDefaultState());
        TileEntity te = player.world.getTileEntity(center);
        if (te != null && te instanceof TorikagoTileEntity) {
          
          ((TorikagoTileEntity)te).setOwner((LivingEntity)player);
          ((TorikagoTileEntity)te).markDirty();
        } 
        this.blockList.add(new BlockPos(MathHelper.floor(player.getPosX()), MathHelper.floor(player.getPosY()), MathHelper.floor(player.getPosZ())));
        this.placedBlockList.addAll(this.blockList);
        setThreshold(0.0D);
      }
      else {
        
        int placedBlocks = 0;
        Iterator<BlockPos> iter = this.placedBlockList.iterator();
        while (iter.hasNext()) {
          
          BlockPos pos = iter.next();
          player.world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), ModBlocks.STRING_WALL.getDefaultState(), 0);
          iter.remove();
          placedBlocks++;
          if (placedBlocks > 500)
            return; 
        } 
      } 
      player.addExhaustion(0.005F * this.roomSize / 30.0F);
      if (player.getFoodStats().getFoodLevel() < 6) {
        endContinuity(player);
      
      }
    }
    else if (0 >= this.chargingTicks) {
      
      this.chargingTicks = 19;
    } else {
      
      this.chargingTicks--;
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (getThreshold() > 0) {
      
      setThreshold(0.0D);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      this.roomSize = (int)(20.0F + 40.0F * this.continueTime / 60.0F);
      return false;
    } 
    
    return true;
  }

  
  private boolean onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.blockList) {
      
      Block currentBlock = player.world.getBlockState(pos).getBlock();
      if (currentBlock == ModBlocks.STRING_WALL || currentBlock == ModBlocks.STRING_MID)
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
    } 
    this.blockList.clear();
    this.placedBlockList.clear();
    
    setMaxCooldown((10.0F * this.roomSize / 30.0F));
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    
    return true;
  }

  
  public boolean isEntityInThisTorikago(Entity entity) {
    int roomSize = this.roomSize;
    for (int i = -roomSize; i < roomSize; i++) {
      for (int j = -roomSize; j < roomSize; j++) {
        for (int k = -roomSize; k < roomSize; k++) {
          
          if (entity.world.getBlockState(new BlockPos(entity.getPosX() + i, entity.getPosY() + j, entity.getPosZ() + k)).getBlock() == ModBlocks.STRING_MID) {
            
            double distance = entity.getDistanceSq(entity.getPosX() + i, entity.getPosY() + j, entity.getPosZ() + k);
            if (distance < ((roomSize - 1) * (roomSize - 1)))
              return true; 
          } 
        } 
      } 
    }  return false;
  }
}


