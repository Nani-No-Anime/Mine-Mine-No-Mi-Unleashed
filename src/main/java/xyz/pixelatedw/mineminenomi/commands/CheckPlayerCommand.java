/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.mojang.brigadier.CommandDispatcher;
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
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CheckPlayerCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 27 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("check_player").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 29 */     ((LiteralArgumentBuilder)builder
/* 30 */       .executes(context -> checkPlayer(context, ((CommandSource)context.getSource()).asPlayer())))
/* 31 */       .then(Commands.argument("target", EntityArgument.player())
/* 32 */         .executes(context -> checkPlayer(context, EntityArgument.getPlayer(context, "target"))));
/*    */     
/* 34 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int checkPlayer(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/*    */     try {
/* 41 */       IEntityStats statsData = EntityStatsCapability.get((LivingEntity)player);
/* 42 */       IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)player);
/* 43 */       IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
/*    */       
/* 45 */       ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/* 46 */       Crew crew = worldData.getCrewWithMember(player.getUniqueID());
/*    */       
/* 48 */       StringBuilder builder = new StringBuilder();
/*    */       
/* 50 */       builder.append("===============================================\n");
/*    */       
/* 52 */       builder.append("Name: " + player.getDisplayName().getFormattedText() + "\n");
/* 53 */       builder.append("Faction: " + statsData.getFaction() + "\n");
/* 54 */       if (statsData.isMarine() || statsData.isRevolutionary()) {
/*    */         
/* 56 */         builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
/* 57 */         String rank = statsData.isMarine() ? statsData.getMarineRank().getLocalizedName() : statsData.getRevolutionaryRank().getLocalizedName();
/* 58 */         builder.append("Rank: " + rank + "\n");
/*    */       } 
/* 60 */       builder.append("Race: " + statsData.getRace() + "\n");
/* 61 */       if (statsData.isCyborg()) {
/*    */         
/* 63 */         builder.append("Max Cola: " + statsData.getMaxCola() + "\n");
/* 64 */         builder.append("Ultra Cola: " + statsData.getUltraCola() + "\n");
/*    */       } 
/* 66 */       builder.append("Style: " + statsData.getFightingStyle() + "\n");
/* 67 */       builder.append("Doriki: " + statsData.getDoriki() + "\n");
/* 68 */       builder.append("Belly: " + statsData.getBelly() + "\n");
/* 69 */       builder.append("Extol: " + statsData.getExtol() + "\n");
/* 70 */       if (BountyHelper.canGainBounty((PlayerEntity)player)) {
/*    */         
/* 72 */         builder.append("Bounty: " + statsData.getBounty() + "\n");
/* 73 */         builder.append("Issued Bounty: " + worldData.getBounty(player.getUniqueID().toString()) + "\n");
/*    */       } 
/* 75 */       if (statsData.isPirate())
/* 76 */         builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n"); 
/* 77 */       builder.append("Devil Fruit: " + (!WyHelper.isNullOrEmpty(fruitData.getDevilFruit()) ? fruitData.getDevilFruit() : "None") + "\n");
/* 78 */       builder.append("Damage Multiplier: " + statsData.getDamageMultiplier() + "\n");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 89 */       builder.append("===============================================");
/*    */       
/* 91 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), true);
/*    */     }
/* 93 */     catch (Exception e) {
/*    */       
/* 95 */       e.printStackTrace();
/*    */     } 
/* 97 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\CheckPlayerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */