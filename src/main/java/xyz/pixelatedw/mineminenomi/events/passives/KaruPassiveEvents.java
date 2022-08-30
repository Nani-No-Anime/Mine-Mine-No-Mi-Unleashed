/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.text.DecimalFormat;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*    */ public class KaruPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 28 */     Minecraft mc = Minecraft.getInstance();
/* 29 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/* 30 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 32 */     KarmaAbility karmaAbility = (KarmaAbility)AbilityDataCapability.get((LivingEntity)clientPlayerEntity).getUnlockedAbility((Ability)KarmaAbility.INSTANCE);
/*    */     
/* 34 */     if (!entityStatsProps.isInCombatMode() || karmaAbility == null) {
/*    */       return;
/*    */     }
/* 37 */     int posX = mc.getMainWindow().getScaledWidth();
/* 38 */     int posY = mc.getMainWindow().getScaledHeight();
/*    */     
/* 40 */     if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*    */       
/* 42 */       mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*    */       
/* 44 */       RendererHelper.drawAbilityIcon("inga_zarashi", (posX - 343) / 2, posY - 38, 0, 32, 32);
/*    */       
/* 46 */       DecimalFormat karmaFormat = new DecimalFormat("#0.0");
/* 47 */       String karma = karmaFormat.format(karmaAbility.getKarma());
/* 48 */       WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, karma, (posX - 310) / 2 - mc.fontRenderer.getStringWidth(karma) / 2, posY - 25, Color.WHITE.getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\KaruPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */