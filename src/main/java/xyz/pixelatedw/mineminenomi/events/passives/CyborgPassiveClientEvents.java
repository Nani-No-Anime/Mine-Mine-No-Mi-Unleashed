/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class CyborgPassiveClientEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 25 */     Minecraft mc = Minecraft.getInstance();
/* 26 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/* 27 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 29 */     if (!entityStatsProps.isCyborg() || !entityStatsProps.isInCombatMode()) {
/*    */       return;
/*    */     }
/* 32 */     int posX = mc.getMainWindow().getScaledWidth();
/* 33 */     int posY = mc.getMainWindow().getScaledHeight();
/*    */     
/* 35 */     if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*    */       
/* 37 */       mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*    */ 
/*    */ 
/*    */       
/* 41 */       GuiUtils.drawTexturedModalRect((posX - 275) / 2, posY - 42, 0, 52, 23, 40, 0.0F);
/* 42 */       int barHeight = (int)(entityStatsProps.getCola() / entityStatsProps.getMaxCola() * 30.0F) + 23;
/*    */       
/* 44 */       if (barHeight > 0 && barHeight < 24) {
/* 45 */         barHeight = 24;
/* 46 */       } else if (barHeight > 52) {
/* 47 */         barHeight = 52;
/*    */       } 
/* 49 */       GuiUtils.drawTexturedModalRect((posX - 267) / 2, posY - 42, 32, barHeight, 16, 32, 0.0F);
/* 50 */       String cola = entityStatsProps.getCola() + "";
/* 51 */       WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, cola, (posX - 250) / 2 - mc.fontRenderer.getStringWidth(cola) / 2, posY - 12, Color.WHITE.getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\CyborgPassiveClientEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */