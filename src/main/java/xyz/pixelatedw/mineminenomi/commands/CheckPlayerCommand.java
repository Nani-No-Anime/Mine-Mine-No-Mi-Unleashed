package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class CheckPlayerCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("check_player").requires(source -> source.hasPermissionLevel(2));
    
    ((LiteralArgumentBuilder)builder
      .executes(context -> checkPlayer(context, ((CommandSource)context.getSource()).asPlayer())))
      .then(Commands.argument("target", EntityArgument.player())
        .executes(context -> checkPlayer(context, EntityArgument.getPlayer(context, "target"))));
    
    dispatcher.register(builder);
  }


  
  private static int checkPlayer(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    try {
      IEntityStats statsData = EntityStatsCapability.get((LivingEntity)player);
      IDevilFruit fruitData = DevilFruitCapability.get((LivingEntity)player);
      IHakiData hakiData = HakiDataCapability.get((LivingEntity)player);
      
      ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
      Crew crew = worldData.getCrewWithMember(player.getUniqueID());
      
      StringBuilder builder = new StringBuilder();
      
      builder.append("===============================================\n");
      
      builder.append("Name: " + player.getDisplayName().getFormattedText() + "\n");
      builder.append("Faction: " + statsData.getFaction() + "\n");
      if (statsData.isMarine() || statsData.isRevolutionary()) {
        
        builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
        String rank = statsData.isMarine() ? statsData.getMarineRank().getLocalizedName() : statsData.getRevolutionaryRank().getLocalizedName();
        builder.append("Rank: " + rank + "\n");
      } 
      builder.append("Race: " + statsData.getRace() + "\n");
      if (statsData.isCyborg()) {
        
        builder.append("Max Cola: " + statsData.getMaxCola() + "\n");
        builder.append("Ultra Cola: " + statsData.getUltraCola() + "\n");
      } 
      builder.append("Style: " + statsData.getFightingStyle() + "\n");
      builder.append("Doriki: " + statsData.getDoriki() + "\n");
      builder.append("Belly: " + statsData.getBelly() + "\n");
      builder.append("Extol: " + statsData.getExtol() + "\n");
      if (BountyHelper.canGainBounty((PlayerEntity)player)) {
        
        builder.append("Bounty: " + statsData.getBounty() + "\n");
        builder.append("Issued Bounty: " + worldData.getBounty(player.getUniqueID().toString()) + "\n");
      } 
      if (statsData.isPirate())
        builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n"); 
      builder.append("Devil Fruit: " + (!WyHelper.isNullOrEmpty(fruitData.getDevilFruit()) ? fruitData.getDevilFruit() : "None") + "\n");
      builder.append("Damage Multiplier: " + statsData.getDamageMultiplier() + "\n");









      
      builder.append("===============================================");
      
      ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), true);
    }
    catch (Exception e) {
      
      e.printStackTrace();
    } 
    return 1;
  }
}


