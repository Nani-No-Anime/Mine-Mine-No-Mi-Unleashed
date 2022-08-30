/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.IssueBountyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class IssueBountyCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 28 */     LiteralArgumentBuilder<CommandSource> builder = Commands.literal("issuebounty");
/*    */     
/* 30 */     builder
/* 31 */       .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/* 32 */         .then(Commands.argument("bounty", (ArgumentType)LongArgumentType.longArg())
/* 33 */           .executes(context -> issueBounty(context, EntityArgument.getPlayer(context, "target"), LongArgumentType.getLong(context, "bounty")))));
/*    */     
/* 35 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int issueBounty(CommandContext<CommandSource> context, ServerPlayerEntity player, long bounty) {
/*    */     try {
/* 42 */       ServerPlayerEntity serverPlayerEntity = ((CommandSource)context.getSource()).asPlayer();
/*    */       
/* 44 */       IEntityStats propz = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/* 45 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 47 */       IssueBountyEvent event = new IssueBountyEvent((PlayerEntity)player, (PlayerEntity)serverPlayerEntity, bounty);
/* 48 */       MinecraftForge.EVENT_BUS.post(event);
/*    */       
/* 50 */       if (propz.isMarine() && ((event.getResult() == Event.Result.DEFAULT && propz.hasMarineRank(FactionHelper.MarineRank.CAPTAIN)) || event.getResult() == Event.Result.ALLOW)) {
/*    */         
/* 52 */         if (props.isPirate() || props.isBandit() || props.isRevolutionary())
/*    */         {
/* 54 */           if (bounty <= props.getBounty()) {
/* 55 */             serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_ONLY_UP, new Object[0]));
/* 56 */             return 1;
/*    */           } 
/* 58 */           long costOfAdding = bounty - props.getBounty();
/* 59 */           if (propz.getBelly() > costOfAdding) {
/* 60 */             propz.setBelly(propz.getBelly() - costOfAdding);
/* 61 */             props.setBounty(bounty);
/*    */           } else {
/* 63 */             serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_NOT_ENOUGH_BELLY, new Object[0]));
/* 64 */             return 1;
/*    */           
/*    */           }
/*    */         
/*    */         }
/*    */         else
/*    */         {
/* 71 */           serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_TARGET_REQUIREMENTS, new Object[0]));
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 76 */         serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_REQUIREMENTS, new Object[0]));
/*    */       }
/*    */     
/* 79 */     } catch (CommandSyntaxException e) {
/*    */       
/* 81 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 84 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\IssueBountyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */