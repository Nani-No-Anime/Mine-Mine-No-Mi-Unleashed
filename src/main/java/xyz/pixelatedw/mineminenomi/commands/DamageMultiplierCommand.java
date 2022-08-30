/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class DamageMultiplierCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 21 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("damagem").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 23 */     builder
/* 24 */       .then(Commands.argument("multiplier", (ArgumentType)DoubleArgumentType.doubleArg(0.0D, 10.0D))
/* 25 */         .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/* 26 */           .executes(context -> applyMultiplier(context, DoubleArgumentType.getDouble(context, "multiplier"), EntityArgument.getPlayer(context, "target")))));
/*    */     
/* 28 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int applyMultiplier(CommandContext<CommandSource> context, double multiplier, ServerPlayerEntity player) {
/* 33 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 35 */     props.setDamageMultiplier(multiplier);
/*    */     
/* 37 */     ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Damage Multiplier set to " + props.getDamageMultiplier() + " for " + player.getDisplayName().getFormattedText()), true);
/*    */     
/* 39 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\DamageMultiplierCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */