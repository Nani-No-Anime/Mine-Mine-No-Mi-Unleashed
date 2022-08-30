/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class BountyEvents {
/*  38 */   private static HashMap<PlayerEntity, double[]> cachedPositions = (HashMap)new HashMap<>();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerTick(TickEvent.WorldTickEvent event) {
/*  43 */     if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER || !CommonConfig.INSTANCE.isWantedPosterPackagesEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/*  47 */     if (event.world.getDayTime() % CommonConfig.INSTANCE.getTimeBetweenPackages() != 0L) {
/*     */       return;
/*     */     }
/*  50 */     ServerPlayerEntity serverPlayerEntity = ((ServerWorld)event.world).getRandomPlayer();
/*     */ 
/*     */     
/*  53 */     if (serverPlayerEntity != null) {
/*     */       
/*  55 */       double currentPosX = serverPlayerEntity.getPosX();
/*  56 */       double currentPosZ = serverPlayerEntity.getPosZ();
/*     */       
/*  58 */       if (!cachedPositions.containsKey(serverPlayerEntity)) {
/*  59 */         cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
/*     */       } else {
/*     */         
/*  62 */         double[] positions = cachedPositions.get(serverPlayerEntity);
/*  63 */         double cachedPosX = positions[0];
/*  64 */         double cachedPosZ = positions[1];
/*     */         
/*  66 */         boolean flagPosX = (Math.abs(currentPosX - cachedPosX) > 100.0D);
/*  67 */         boolean flagPosZ = (Math.abs(currentPosZ - cachedPosZ) > 100.0D);
/*     */         
/*  69 */         if (flagPosX || flagPosZ) {
/*     */           
/*  71 */           if (BountyHelper.issueBountyForPlayer((PlayerEntity)serverPlayerEntity)) {
/*     */             
/*  73 */             WantedPosterPackageEntity pkg = new WantedPosterPackageEntity(((PlayerEntity)serverPlayerEntity).world);
/*  74 */             pkg.setLocationAndAngles(serverPlayerEntity.getPosX() + WyHelper.randomWithRange(-10, 10), serverPlayerEntity.getPosY() + 30.0D, serverPlayerEntity.getPosZ() + WyHelper.randomWithRange(-10, 10), 0.0F, 0.0F);
/*  75 */             ((PlayerEntity)serverPlayerEntity).world.addEntity((Entity)pkg);
/*     */           } 
/*     */           
/*  78 */           cachedPositions.remove(serverPlayerEntity);
/*  79 */           cachedPositions.put(serverPlayerEntity, new double[] { currentPosX, currentPosZ });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onBlockBreak(BlockEvent.BreakEvent event) {
/*  88 */     if (event.getState().getBlock() == ModBlocks.WANTED_POSTER) {
/*     */       
/*  90 */       ItemStack stack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER.asItem());
/*     */       
/*  92 */       WantedPosterTileEntity tileEntity = (WantedPosterTileEntity)event.getWorld().getTileEntity(event.getPos());
/*     */       
/*  94 */       CompoundNBT nbt = stack.getOrCreateTag();
/*  95 */       nbt.putString("UUID", tileEntity.getUUID());
/*  96 */       nbt.putString("Name", tileEntity.getEntityName());
/*  97 */       long bounty = 0L;
/*     */       
/*     */       try {
/* 100 */         bounty = Long.parseLong(tileEntity.getPosterBounty());
/*     */       }
/* 102 */       catch (NumberFormatException ex) {
/*     */         
/* 104 */         ex.printStackTrace();
/*     */       } 
/* 106 */       nbt.putLong("Bounty", bounty);
/* 107 */       nbt.putString("Background", tileEntity.getBackground());
/* 108 */       nbt.putString("Faction", tileEntity.getFaction());
/* 109 */       nbt.putString("Date", tileEntity.getIssuedDate());
/* 110 */       CompoundNBT compoundnbt = new CompoundNBT();
/* 111 */       NBTUtil.writeGameProfile(compoundnbt, event.getPlayer().getGameProfile());
/* 112 */       nbt.put("Owner", (INBT)compoundnbt);
/*     */       
/* 114 */       event.getWorld().addEntity((Entity)new ItemEntity((World)event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), stack));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onBountyKilled(LivingDeathEvent event) {
/* 121 */     if (event.getSource().getTrueSource() instanceof PlayerEntity && !(event.getEntityLiving()).world.isRemote) {
/*     */       
/* 123 */       if (!EntityStatsCapability.get((LivingEntity)event.getSource().getTrueSource()).isBountyHunter()) {
/*     */         return;
/*     */       }
/* 126 */       PlayerEntity player = (PlayerEntity)event.getSource().getTrueSource();
/* 127 */       LivingEntity target = event.getEntityLiving();
/* 128 */       ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/* 129 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 130 */       IEntityStats targetProps = EntityStatsCapability.get(target);
/*     */       
/* 132 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(target.getEntityId(), targetProps), player);
/* 133 */       for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */         
/* 135 */         ItemStack itemStack = player.inventory.getStackInSlot(i);
/* 136 */         if (itemStack.hasTag()) {
/*     */           
/* 138 */           String uuid = itemStack.getTag().getString("UUID");
/* 139 */           Long bounty = Long.valueOf(itemStack.getTag().getLong("Bounty"));
/*     */           
/* 141 */           if (!WyHelper.isNullOrEmpty(uuid)) {
/*     */ 
/*     */             
/* 144 */             boolean isTarget = UUID.fromString(uuid).equals(target.getUniqueID());
/* 145 */             boolean hasBounty = (worldData.getBounty(target.getUniqueID().toString()) == bounty.longValue());
/*     */             
/* 147 */             if (isTarget && hasBounty) {
/*     */               
/* 149 */               worldData.issueBounty(uuid, 0L);
/*     */               
/* 151 */               long bellyGain = 0L;
/*     */               
/* 153 */               if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
/* 154 */                 bellyGain = targetProps.getBounty() / 3L;
/* 155 */               } else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.NONE) {
/* 156 */                 bellyGain = targetProps.getBounty();
/*     */               } 
/* 158 */               props.alterBelly(bellyGain);
/* 159 */               WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/* 160 */               WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\BountyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */