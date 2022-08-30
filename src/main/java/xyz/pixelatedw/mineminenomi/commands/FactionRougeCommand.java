/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ 
/*    */ public class FactionRougeCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 17 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("gorogue").requires(source -> source.hasPermissionLevel(0));
/*    */     
/* 19 */     builder.executes(context -> goRogue(context, ((CommandSource)context.getSource()).asPlayer()));
/*    */     
/* 21 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int goRogue(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 26 */     boolean rogue = EntityStatsCapability.get((LivingEntity)player).isRogue();
/* 27 */     EntityStatsCapability.get((LivingEntity)player).setRogue(!rogue);
/* 28 */     player.sendMessage((ITextComponent)new StringTextComponent((rogue ? "Disabled" : "Enabled") + " rogue mode!"));
/* 29 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\FactionRougeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */