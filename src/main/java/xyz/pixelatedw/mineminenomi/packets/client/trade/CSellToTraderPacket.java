/*     */ package xyz.pixelatedw.mineminenomi.packets.client.trade;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class CSellToTraderPacket
/*     */ {
/*     */   private int traderId;
/*     */   private int amount;
/*     */   
/*     */   public CSellToTraderPacket() {}
/*     */   
/*     */   public CSellToTraderPacket(int traderId, int amount) {
/*  31 */     this.traderId = traderId;
/*  32 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  37 */     buffer.writeInt(this.traderId);
/*  38 */     buffer.writeInt(this.amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CSellToTraderPacket decode(PacketBuffer buffer) {
/*  43 */     CSellToTraderPacket msg = new CSellToTraderPacket();
/*  44 */     msg.traderId = buffer.readInt();
/*  45 */     msg.amount = buffer.readInt();
/*  46 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CSellToTraderPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  51 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             Entity target = player.world.getEntityByID(message.traderId);
/*     */             
/*     */             if (player.getDistanceSq(target) > 25.0D) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (target != null && target instanceof SkypieanTraderEntity) {
/*     */               SkypieanTraderEntity trader = (SkypieanTraderEntity)target;
/*     */               
/*     */               IEntityStats entityData = EntityStatsCapability.get((LivingEntity)player);
/*     */               
/*     */               int dirtBlocksAvailable = 0;
/*     */               
/*     */               List<ItemStack> markedStacks = new ArrayList<>();
/*     */               
/*     */               for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */                 ItemStack stack = player.inventory.getStackInSlot(i);
/*     */                 
/*     */                 if (stack != null && !stack.isEmpty() && stack.getItem() == Blocks.DIRT.asItem()) {
/*     */                   dirtBlocksAvailable += stack.getCount();
/*     */                   
/*     */                   if (!markedStacks.contains(stack)) {
/*     */                     markedStacks.add(stack);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */               
/*     */               dirtBlocksAvailable = MathHelper.clamp(dirtBlocksAvailable, 0, message.amount);
/*     */               
/*     */               if (dirtBlocksAvailable >= message.amount) {
/*     */                 int markedBlocks = dirtBlocksAvailable;
/*     */                 
/*     */                 for (ItemStack stack : markedStacks) {
/*     */                   if (markedBlocks <= 0) {
/*     */                     break;
/*     */                   }
/*     */                   int stackSize = stack.getCount();
/*     */                   if (markedBlocks - stackSize >= 0) {
/*     */                     markedBlocks -= stackSize;
/*     */                     stack.shrink(stackSize);
/*     */                     continue;
/*     */                   } 
/*     */                   int blocksNeeded = markedBlocks;
/*     */                   markedBlocks -= blocksNeeded;
/*     */                   stack.shrink(blocksNeeded);
/*     */                 } 
/*     */                 trader.removeTradesLeft(message.amount);
/*     */                 long value = CurrencyHelper.getExtolFromBelly(dirtBlocksAvailable);
/*     */                 entityData.alterExtol(value);
/*     */                 WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityData), (PlayerEntity)player);
/*     */               } 
/*     */             } 
/*     */           });
/*     */     }
/* 110 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\trade\CSellToTraderPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */