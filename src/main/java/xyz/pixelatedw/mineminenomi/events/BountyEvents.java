package xyz.pixelatedw.mineminenomi.events;

import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@EventBusSubscriber(modid = "mineminenomi")
public class BountyEvents {
  private static HashMap<PlayerEntity, double[]> cachedPositions = (HashMap)new HashMap<>();

  
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.WorldTickEvent event) {
    if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER || !CommonConfig.INSTANCE.isWantedPosterPackagesEnabled()) {
      return;
    }
    
    if (event.world.getDayTime() % CommonConfig.INSTANCE.getTimeBetweenPackages() != 0L) {
      return;
    }
    ServerPlayerEntity serverPlayerEntity = ((ServerWorld)event.world).getRandomPlayer();

    
    if (serverPlayerEntity != null) {
      
      double currentPosX = serverPlayerEntity.getPosX();
      double currentPosZ = serverPlayerEntity.getPosZ();
      
      if (!cachedPositions.containsKey(serverPlayerEntity)) {
        cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
      } else {
        
        double[] positions = cachedPositions.get(serverPlayerEntity);
        double cachedPosX = positions[0];
        double cachedPosZ = positions[1];
        
        boolean flagPosX = (Math.abs(currentPosX - cachedPosX) > 100.0D);
        boolean flagPosZ = (Math.abs(currentPosZ - cachedPosZ) > 100.0D);
        
        if (flagPosX || flagPosZ) {
          
          if (BountyHelper.issueBountyForPlayer((PlayerEntity)serverPlayerEntity)) {
            
            WantedPosterPackageEntity pkg = new WantedPosterPackageEntity(((PlayerEntity)serverPlayerEntity).world);
            pkg.setLocationAndAngles(serverPlayerEntity.getPosX() + WyHelper.randomWithRange(-10, 10), serverPlayerEntity.getPosY() + 30.0D, serverPlayerEntity.getPosZ() + WyHelper.randomWithRange(-10, 10), 0.0F, 0.0F);
            ((PlayerEntity)serverPlayerEntity).world.addEntity((Entity)pkg);
          } 
          
          cachedPositions.remove(serverPlayerEntity);
          cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onBlockBreak(BlockEvent.BreakEvent event) {
    if (event.getState().getBlock() == ModBlocks.WANTED_POSTER) {
      
      ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER.asItem());
      
      WantedPosterTileEntity tileEntity = (WantedPosterTileEntity)event.getWorld().getTileEntity(event.getPos());
      
      CompoundNBT nbt = stack.getOrCreateTag();
      nbt.putString("UUID", tileEntity.getUUID());
      nbt.putString("Name", tileEntity.getEntityName());
      long bounty = 0L;
      
      try {
        bounty = Long.parseLong(tileEntity.getPosterBounty());
      }
      catch (NumberFormatException ex) {
        
        ex.printStackTrace();
      } 
      nbt.putLong("Bounty", bounty);
      nbt.putString("Background", tileEntity.getBackground());
      nbt.putString("Faction", tileEntity.getFaction());
      nbt.putString("Date", tileEntity.getIssuedDate());
      CompoundNBT compoundnbt = new CompoundNBT();
      NBTUtil.writeGameProfile(compoundnbt, event.getPlayer().getGameProfile());
      nbt.put("Owner", (INBT)compoundnbt);
      
      event.getWorld().addEntity((Entity)new ItemEntity((World)event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));
    } 
  }

  
  @SubscribeEvent
  public static void onBountyKilled(LivingDeathEvent event) {
    if (event.getSource().getTrueSource() instanceof PlayerEntity && !(event.getEntityLiving()).world.isRemote) {
      
      if (!EntityStatsCapability.get((LivingEntity)event.getSource().getTrueSource()).isBountyHunter()) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
      LivingEntity target = event.getEntityLiving();
      ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      IEntityStats targetProps = EntityStatsCapability.get(target);
      
      WyNetwork.sendTo(new SSyncEntityStatsPacket(target.getEntityId(), targetProps), player);
      for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
        
        ItemStack itemStack = player.inventory.getStackInSlot(i);
        if (itemStack.hasTag()) {
          
          String uuid = itemStack.getTag().getString("UUID");
          Long bounty = Long.valueOf(itemStack.getTag().getLong("Bounty"));
          
          if (!WyHelper.isNullOrEmpty(uuid)) {

            
            boolean isTarget = UUID.fromString(uuid).equals(target.getUniqueID());
            boolean hasBounty = (worldData.getBounty(target.getUniqueID().toString()) == bounty.longValue());
            
            if (isTarget && hasBounty) {
              
              worldData.issueBounty(uuid, 0L);
              
              long bellyGain = 0L;
              
              if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
                bellyGain = targetProps.getBounty() / 3L;
              } else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.NONE) {
                bellyGain = targetProps.getBounty();
              } 
              props.alterBelly(bellyGain);
              WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
              WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
            } 
          } 
        } 
      } 
    } 
  }
}


