package xyz.pixelatedw.mineminenomi.abilities.ope;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.RoomTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class RoomAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final RoomAbility INSTANCE = new RoomAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { ModBlocks.OPE }).setBypassGriefRule();
  private List<BlockPos> blockList = new ArrayList<>();
  private List<BlockPos> placedBlockList = new ArrayList<>();
  public static final int MAX_ROOM_SIZE = 45;
  public static final int MAX_THRESHOLD = 2;
  private int roomSize = 0;
  private int chargingTicks = 0;
  
  private BlockPos centerBlock;
  
  public RoomAbility() {
    super("ROOM", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Creates a spherical space around the user in which they can manipulate anything or use other skills");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CREATE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
    this.chargingTicks = 10;
    setThreshold(2.0D);
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
    if (getThreshold() == 0) {
      
      if (this.blockList.isEmpty()) {
        
        this.blockList.addAll(AbilityHelper.createSphere(player.world, player.getPosition(), this.roomSize, true, ModBlocks.OPE, 0, GRIEF_RULE));
        this.centerBlock = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());
        player.world.setBlockState(this.centerBlock, ModBlocks.OPE_MID.getDefaultState());
        TileEntity te = player.world.getTileEntity(this.centerBlock);
        if (te != null && te instanceof RoomTileEntity) {
          
          ((RoomTileEntity)te).setOwner((LivingEntity)player);
          ((RoomTileEntity)te).markDirty();
        } 
        this.blockList.add(new BlockPos(MathHelper.floor(player.getPosX()), MathHelper.floor(player.getPosY()), MathHelper.floor(player.getPosZ())));
        this.placedBlockList.addAll(this.blockList);
        setThreshold(0.0D);
      }
      else {
        
        int placedBlocks = 0;
        Iterator<BlockPos> iter = this.placedBlockList.iterator();
        while (iter.hasNext())
        {
          BlockPos pos = iter.next();
          player.world.notifyBlockUpdate(pos, Blocks.AIR.getDefaultState(), ModBlocks.OPE.getDefaultState(), 0);
          iter.remove();
          placedBlocks++;
          if (placedBlocks > 500) {
            return;
          }
        }
      
      } 
    } else if (0 >= this.chargingTicks) {
      
      this.chargingTicks = 19;
      player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CHARGE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
    } else {
      this.chargingTicks--;
    } 
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (getThreshold() > 0) {
      
      setThreshold(0.0D);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      this.roomSize = Math.max(8, (int)(45.0D * this.continueTime / 20.0D / 2.0D));
      player.world.playSound(null, player.getPosition(), ModSounds.ROOM_EXPAND_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
      return false;
    } 
    
    return true;
  }

  
  private boolean onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.blockList) {
      
      Block currentBlock = player.world.getBlockState(pos).getBlock();
      if (currentBlock == ModBlocks.OPE || currentBlock == ModBlocks.OPE_MID) {
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
      }
    } 
    this.blockList.clear();
    this.placedBlockList.clear();
    player.world.playSound(null, player.getPosition(), ModSounds.ROOM_CLOSE_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
    
    setMaxCooldown((10.0F * this.roomSize / 45.0F));
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
    
    return true;
  }

  
  public int getROOMSize() {
    return this.roomSize;
  }

  
  public BlockPos getCenterBlock() {
    return this.centerBlock;
  }

  
  public boolean isEntityInThisRoom(Entity entity) {
    return isInsideROOM(entity.world, entity.getPosition());
  }

  
  public boolean isInsideROOM(World world, BlockPos pos) {
    int roomSize = this.roomSize;
    for (int i = -roomSize; i < roomSize; i++) {
      for (int j = -roomSize; j < roomSize; j++) {
        for (int k = -roomSize; k < roomSize; k++) {
          
          BlockPos posCheck = pos.add(i, j, k);
          if (world.getBlockState(posCheck).getBlock() == ModBlocks.OPE_MID) {
            
            double distance = pos.distanceSq((Vec3i)posCheck);
            if (distance < ((roomSize - 1) * (roomSize - 1)))
              return true; 
          } 
        } 
      } 
    }  return false;
  }
}


