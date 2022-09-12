package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.awt.*;




@EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
public class CyborgPassiveClientEvents
{
  @SubscribeEvent
  public static void onRenderOverlay(RenderGameOverlayEvent event) {
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = mc.player;
    IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
    
    if (!entityStatsProps.isCyborg() || !entityStatsProps.isInCombatMode()) {
      return;
    }
    int posX = mc.getMainWindow().getScaledWidth();
    int posY = mc.getMainWindow().getScaledHeight();
    
    if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
      
      mc.getTextureManager().bindTexture(ModResources.WIDGETS);


      
      GuiUtils.drawTexturedModalRect((posX - 275) / 2, posY - 42, 0, 52, 23, 40, 0.0F);
      int barHeight = (int)(entityStatsProps.getCola() / entityStatsProps.getMaxCola() * 30.0F) + 23;
      
      if (barHeight > 0 && barHeight < 24) {
        barHeight = 24;
      } else if (barHeight > 52) {
        barHeight = 52;
      } 
      GuiUtils.drawTexturedModalRect((posX - 267) / 2, posY - 42, 32, barHeight, 16, 32, 0.0F);
      String cola = entityStatsProps.getCola() + "";
      WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, cola, (posX - 250) / 2 - mc.fontRenderer.getStringWidth(cola) / 2, posY - 12, Color.WHITE.getRGB());
    } 
  }
}


