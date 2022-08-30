/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class PouchCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 27 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("pouch").requires(source -> source.hasPermissionLevel(0));
/*    */     
/* 29 */     builder
/* 30 */       .then(Commands.literal("ALL")
/* 31 */         .executes(context -> createBellyPouch(context, 999999999L)));
/*    */     
/* 33 */     builder
/* 34 */       .then(Commands.argument("amount", (ArgumentType)LongArgumentType.longArg(1L, 999999999L))
/* 35 */         .executes(context -> createBellyPouch(context, LongArgumentType.getLong(context, "amount"))));
/*    */     
/* 37 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int createBellyPouch(CommandContext<CommandSource> context, long amount) {
/* 42 */     if (amount <= 0L) {
/* 43 */       return 1;
/*    */     }
/*    */     
/*    */     try {
/* 47 */       ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
/*    */       
/* 49 */       if (WyHelper.hasInventoryFull((PlayerEntity)player)) {
/*    */         
/* 51 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_POUCH_MESSAGE_INVENTORY_FULL, new Object[0]));
/* 52 */         return 1;
/*    */       } 
/*    */       
/* 55 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 57 */       if (props.getBelly() <= 0L) {
/* 58 */         return 1;
/*    */       }
/* 60 */       if (props.getBelly() - amount >= 0L) {
/* 61 */         props.alterBelly(-amount);
/*    */       } else {
/*    */         
/* 64 */         amount = props.getBelly();
/* 65 */         props.alterBelly(-amount);
/*    */       } 
/*    */       
/* 68 */       ItemStack pouch = new ItemStack((IItemProvider)ModItems.BELLY_POUCH);
/* 69 */       pouch.getOrCreateTag().putLong("belly", amount);
/*    */       
/* 71 */       player.inventory.addItemStackToInventory(pouch);
/* 72 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (PlayerEntity)player);
/*    */     }
/* 74 */     catch (CommandSyntaxException e) {
/*    */       
/* 76 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 79 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\PouchCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */