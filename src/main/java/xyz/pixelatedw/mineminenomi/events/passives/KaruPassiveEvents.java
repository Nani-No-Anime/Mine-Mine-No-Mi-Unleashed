package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.awt.*;
import java.text.DecimalFormat;



@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class KaruPassiveEvents
{
  @SubscribeEvent
  public static void onRenderOverlay(RenderGameOverlayEvent event) {
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = mc.player;
    IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
    
    KarmaAbility karmaAbility = (KarmaAbility)AbilityDataCapability.get((LivingEntity)clientPlayerEntity).getUnlockedAbility((Ability)KarmaAbility.INSTANCE);
    
    if (!entityStatsProps.isInCombatMode() || karmaAbility == null) {
      return;
    }
    int posX = mc.getMainWindow().getScaledWidth();
    int posY = mc.getMainWindow().getScaledHeight();
    
    if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
      
      mc.getTextureManager().bindTexture(ModResources.WIDGETS);
      
      RendererHelper.drawAbilityIcon("inga_zarashi", (posX - 343) / 2, posY - 38, 0, 32, 32);
      
      DecimalFormat karmaFormat = new DecimalFormat("#0.0");
      String karma = karmaFormat.format(karmaAbility.getKarma());
      WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, karma, (posX - 310) / 2 - mc.fontRenderer.getStringWidth(karma) / 2, posY - 25, Color.WHITE.getRGB());
    } 
  }
}


