/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.ChestBlock;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DFUserDeathEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onClonePlayer(LivingDeathEvent event) {
/*  38 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  41 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */     
/*  43 */     if (!player.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
/*  44 */       player.getAttributes().getAllAttributes().forEach(WyHelper::removeAllModifiers);
/*     */     }
/*  46 */     if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
/*     */       
/*  48 */       for (String stat : CommonConfig.INSTANCE.getStatsToKeep()) {
/*     */         
/*  50 */         if (WyHelper.getResourceName(stat).equals("devil_fruit")) {
/*     */           return;
/*     */         }
/*     */       } 
/*  54 */     } else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
/*     */       return;
/*     */     } 
/*  57 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/*  59 */     boolean isYomi = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !YomiZoanInfo.INSTANCE.isActive((LivingEntity)player));
/*  60 */     if (isYomi) {
/*     */       return;
/*     */     }
/*  63 */     double chance = WyHelper.randomWithRange(1, 100);
/*     */     
/*  65 */     if (props.hasDevilFruit()) {
/*     */       
/*  67 */       List<ItemEntity> dropList = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { ItemEntity.class });
/*  68 */       List<PlayerEntity> players = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { PlayerEntity.class });
/*  69 */       List<VillagerEntity> villagers = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, new Class[] { VillagerEntity.class });
/*  70 */       List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((LivingEntity)player, 30);
/*     */       
/*  72 */       dropList.removeIf(entry -> (entry.getItem().getItem() != Items.APPLE));
/*  73 */       players.removeIf(entry -> !entry.inventory.hasItemStack(new ItemStack((IItemProvider)Items.APPLE)));
/*  74 */       players.remove(player);
/*  75 */       ImmutableSet immutableSet = ImmutableSet.of(Items.APPLE);
/*     */       
/*  77 */       villagers.removeIf(entry -> !entry.getVillagerInventory().hasAny(immutableSet));
/*  78 */       if (!dropList.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForDroppedAppleReincarnation()) {
/*     */         
/*  80 */         ((ItemEntity)dropList.get(0)).setItem(DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
/*     */       }
/*  82 */       else if (!players.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation()) {
/*     */         
/*  84 */         int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, (IInventory)((PlayerEntity)players.get(0)).inventory);
/*     */         
/*  86 */         if (stackIndex != -1) {
/*  87 */           ((PlayerEntity)players.get(0)).inventory.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
/*     */         }
/*  89 */       } else if (!villagers.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForInventoryAppleReincarnation() && !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */         
/*  91 */         int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, (IInventory)((VillagerEntity)villagers.get(0)).getVillagerInventory());
/*  92 */         if (stackIndex != -1) {
/*  93 */           ((PlayerEntity)players.get(0)).inventory.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
/*     */         }
/*  95 */       } else if (!blockPosList.isEmpty() && chance <= CommonConfig.INSTANCE.getChanceForChestAppleReincarnation() && !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */         
/*  97 */         for (BlockPos pos : blockPosList) {
/*     */           
/*  99 */           BlockState state = player.world.getBlockState(pos);
/* 100 */           if (state.getBlock() instanceof ChestBlock) {
/*     */ 
/*     */             
/* 103 */             IInventory inven = ChestBlock.func_226916_a_((ChestBlock)state.getBlock(), state, player.world, pos, false);
/* 104 */             if (inven == null)
/*     */               continue; 
/* 106 */             int stackIndex = WyHelper.getIndexOfItemStack(Items.APPLE, inven);
/*     */             
/* 108 */             if (stackIndex != -1) {
/*     */               
/* 110 */               inven.setInventorySlotContents(stackIndex, DevilFruitHelper.getDevilFruitItem(props.getDevilFruit()));
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\DFUserDeathEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */