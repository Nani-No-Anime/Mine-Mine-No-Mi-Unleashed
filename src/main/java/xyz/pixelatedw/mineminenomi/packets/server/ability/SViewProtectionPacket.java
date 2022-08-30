/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.ClientBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SViewProtectionPacket
/*    */ {
/*    */   private boolean state;
/*    */   private int[] midPoint;
/*    */   private int size;
/*    */   
/*    */   public SViewProtectionPacket() {}
/*    */   
/*    */   public SViewProtectionPacket(boolean state, int[] midPoint, int size) {
/* 31 */     this.state = state;
/* 32 */     this.midPoint = midPoint;
/* 33 */     this.size = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 38 */     buffer.writeBoolean(this.state);
/* 39 */     buffer.writeInt(this.midPoint[0]);
/* 40 */     buffer.writeInt(this.midPoint[1]);
/* 41 */     buffer.writeInt(this.midPoint[2]);
/* 42 */     buffer.writeInt(this.size);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SViewProtectionPacket decode(PacketBuffer buffer) {
/* 47 */     SViewProtectionPacket msg = new SViewProtectionPacket();
/* 48 */     msg.state = buffer.readBoolean();
/* 49 */     msg.midPoint = new int[] { buffer.readInt(), buffer.readInt(), buffer.readInt() };
/* 50 */     msg.size = buffer.readInt();
/* 51 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SViewProtectionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 56 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 58 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/* 69 */     private static final BlockProtectionRule GRIEF_RULE1 = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE })).setBypassGriefRule();
/* 70 */     private static final BlockProtectionRule GRIEF_RULE2 = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)ClientBlockProtectionRule.INSTANCE })).setBypassGriefRule();
/*    */ 
/*    */     
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SViewProtectionPacket message) {
/* 75 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*    */       
/* 77 */       if (message.state) {
/*    */         
/* 79 */         AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.BLUE_STAINED_GLASS, GRIEF_RULE1);
/* 80 */         AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, 1, 1, Blocks.RED_STAINED_GLASS, GRIEF_RULE1);
/*    */       }
/*    */       else {
/*    */         
/* 84 */         AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.AIR, GRIEF_RULE2);
/* 85 */         AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, 1, 1, Blocks.AIR, GRIEF_RULE2);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SViewProtectionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */