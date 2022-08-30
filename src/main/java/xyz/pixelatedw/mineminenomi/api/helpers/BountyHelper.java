/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class BountyHelper
/*    */ {
/*    */   public static boolean canGainBounty(PlayerEntity player) {
/* 16 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 18 */     if (props.isPirate() || props.isRevolutionary() || props.isBandit()) {
/* 19 */       return true;
/*    */     }
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean issueBountyForPlayer(PlayerEntity player) {
/* 26 */     ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/* 27 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 29 */     if (canGainBounty(player) && props.getBounty() > 1000L) {
/*    */       
/* 31 */       worldData.issueBounty(player.getUniqueID().toString(), props.getBounty());
/* 32 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncWorldDataPacket(worldData), (LivingEntity)player);
/* 33 */       return true;
/*    */     } 
/*    */     
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void issueBountyForAllPlayers(World world) {
/* 41 */     if (!(world instanceof net.minecraft.world.server.ServerWorld)) {
/*    */       return;
/*    */     }
/* 44 */     for (PlayerEntity player : world.getPlayers())
/*    */     {
/* 46 */       issueBountyForPlayer(player);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\BountyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */