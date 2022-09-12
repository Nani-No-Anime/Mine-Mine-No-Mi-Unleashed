package xyz.pixelatedw.mineminenomi.events.devilfruits;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;





@EventBusSubscriber(modid = "mineminenomi")
public class DFUserDeathEvents
{
  @SubscribeEvent
  public static void onClonePlayer(LivingDeathEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    
    if (!player.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
      player.getAttributes().getAllAttributes().forEach(WyHelper::removeAllModifiers);
    }
    if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
      
      for (String stat : CommonConfig.INSTANCE.getStatsToKeep()) {
        
        if (WyHelper.getResourceName(stat).equals("devil_fruit")) {
          return;
        }
      } 
    } else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
      return;
    } 
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    boolean isYomi = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player));
    if (isYomi) {
      return;
    }
    double chance = WyHelper.randomWithRange(1, 100);
    
    if (props.hasDevilFruit()) {
      
      List<ItemEntity> dropList = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { ItemEntity.class });
      List<PlayerEntity> players = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { PlayerEntity.class });
      List<VillagerEntity> villagers = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { VillagerEntity.class });
      List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((LivingEntity)player, 30);
      
      dropList.removeIf(entry -> (entry.getItem().getItem() != Items.APPLE));
      players.removeIf(entry -> !entry.inventory.hasItemStack(new ItemStack((IItemProvider)Items.APPLE)));
      players.remove(player);
      ImmutableSet immutableSet = ImmutableSet.of(Items.APPLE);
      
      villagers.removeIf(entry -> !entry.getVillagerInventory().hasAny(immutableSet));
      if (!dropList.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForDroppedAppleReincarnation()) {
        
        ((ItemEntity)dropList.get(0)).setItem(DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
      }
      else if (!players.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation()) {
        
        int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, (IInventory)((PlayerEntity)players.get(0)).inventory);
        
        if (stackIndex != -1) {
          ((PlayerEntity)players.get(0)).inventory.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
        }
      } else if (!villagers.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation() && !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
        
        int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, (IInventory)((VillagerEntity)villagers.get(0)).getVillagerInventory());
        if (stackIndex != -1) {
          ((PlayerEntity)players.get(0)).inventory.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
        }
      } else if (!blockPosList.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForChestAppleReincarnation() && !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
        
        for (BlockPos pos : blockPosList) {
          
          BlockState state = player.world.getBlockState(pos);
          if (state.getBlock() instanceof ChestBlock) {

            
            IInventory inven = ChestBlock.func_226916_a_((ChestBlock)state.getBlock(), state, player.world, pos, false);
            if (inven == null)
              continue; 
            int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, inven);
            
            if (stackIndex != -1) {
              
              inven.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
              break;
            } 
          } 
        } 
      } 
    } 
  }
}


