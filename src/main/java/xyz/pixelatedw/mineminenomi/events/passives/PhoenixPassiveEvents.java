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
/*    */ import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.FlamesOfRegenerationAbility;
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
/*    */ public class PhoenixPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onRenderOverlay(RenderGameOverlayEvent event) {
/* 29 */     Minecraft mc = Minecraft.getInstance();
/* 30 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/* 31 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 33 */     FlamesOfRegenerationAbility ability = (FlamesOfRegenerationAbility)AbilityDataCapability.get((LivingEntity)clientPlayerEntity).getUnlockedAbility((Ability)FlamesOfRegenerationAbility.INSTANCE);
/*    */     
/* 35 */     boolean hasKarma = AbilityDataCapability.get((LivingEntity)clientPlayerEntity).hasUnlockedAbility((Ability)KarmaAbility.INSTANCE);
/*    */     
/* 37 */     if (!entityStatsProps.isInCombatMode() || ability == null) {
/*    */       return;
/*    */     }
/* 40 */     int posX = mc.getMainWindow().getScaledWidth();
/* 41 */     int posY = mc.getMainWindow().getScaledHeight();
/*    */     
/* 43 */     if (hasKarma) {
/* 44 */       posX -= 70;
/*    */     }
/* 46 */     if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*    */       
/* 48 */       mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*    */       
/* 50 */       RendererHelper.drawAbilityIcon("phoenix_fly_point", (posX - 343) / 2, posY - 38, 0, 32, 32);
/*    */       
/* 52 */       DecimalFormat karmaFormat = new DecimalFormat("#0.0");
/* 53 */       String karma = karmaFormat.format(ability.getEnergy());
/* 54 */       WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, karma, (posX - 312) / 2 - mc.fontRenderer.getStringWidth(karma) / 2, posY - 25, Color.WHITE.getRGB());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\PhoenixPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */