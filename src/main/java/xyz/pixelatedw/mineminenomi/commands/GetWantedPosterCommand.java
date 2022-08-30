/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class GetWantedPosterCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 30 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("getwantedposter").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 32 */     builder
/* 33 */       .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/* 34 */         .executes(context -> giveWantedPoster(context, EntityArgument.getPlayer(context, "target"))));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int giveWantedPoster(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 46 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 47 */     ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*    */     
/* 49 */     worldData.issueBounty(player.getUniqueID().toString(), entityStatsProps.getBounty());
/*    */     
/* 51 */     if (WyDebug.isDebug()) {
/* 52 */       player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!"));
/*    */     }
/* 54 */     ItemStack posterStack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER);
/*    */     
/* 56 */     CompoundNBT data = ItemsHelper.setWantedData(player.world, player.getUniqueID().toString(), worldData.getBounty(player.getUniqueID().toString()));
/*    */     
/* 58 */     if (data.isEmpty()) {
/*    */       
/* 60 */       player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.RED + "New Wanted Posters can only be generated for online players!"));
/* 61 */       return 1;
/*    */     } 
/*    */     
/* 64 */     posterStack.setTag(data);
/* 65 */     player.inventory.addItemStackToInventory(posterStack);
/*    */     
/* 67 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (LivingEntity)player);
/* 68 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncWorldDataPacket(worldData), (LivingEntity)player);
/*    */     
/* 70 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\GetWantedPosterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */