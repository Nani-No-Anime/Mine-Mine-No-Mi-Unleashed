/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.ChallengeFailMessageThread;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArenas;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SCloseScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ChallengesEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinsWorld(PlayerEvent.PlayerLoggedInEvent event) {
/* 27 */     PlayerEntity player = event.getPlayer();
/* 28 */     if (!player.world.isRemote && player.world.getDimension().getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES)) {
/*    */       
/* 30 */       ServerWorld overworld = player.getServer().getWorld(DimensionType.OVERWORLD);
/* 31 */       ((ServerPlayerEntity)player).teleport(overworld, overworld.getSpawnPoint().getX(), overworld.getSpawnPoint().getY(), overworld.getSpawnPoint().getZ(), 270.0F, 0.0F);
/* 32 */       (new ChallengeFailMessageThread((ServerPlayerEntity)player)).start();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onWorldLoaded(EntityJoinWorldEvent event) {
/* 39 */     if (!(event.getEntity() instanceof PlayerEntity) || event.getWorld().getDimension().getType() != DimensionType.OVERWORLD) {
/*    */       return;
/*    */     }
/* 42 */     if (!CommonConfig.INSTANCE.isChallengesEnabled()) {
/*    */       return;
/*    */     }
/* 45 */     World world = event.getWorld();
/*    */     
/* 47 */     if (world.isRemote) {
/*    */       return;
/*    */     }
/* 50 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/* 51 */     ServerWorld nextWorld = world.getServer().getWorld(DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES));
/*    */     
/* 53 */     for (ArenaData arena : ModArenas.ARENAS) {
/* 54 */       arena.buildIfNeeded(player, (World)nextWorld);
/*    */     }
/* 56 */     WyNetwork.sendTo(new SCloseScreenPacket(), player);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\ChallengesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */