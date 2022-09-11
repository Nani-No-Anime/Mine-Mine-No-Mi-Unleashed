package xyz.pixelatedw.mineminenomi.events;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;






public class CombatModeEvents
{
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    private static final ArrayList<Effect> FOV_EFFECTS = new ArrayList<>(Arrays.asList(new Effect[] { ModEffects.PARALYSIS, ModEffects.GUARDING, Effects.SPEED, Effects.SLOWNESS, ModEffects.MOVEMENT_BLOCKED, ModEffects.CANDLE_LOCK }));

    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderOverlay(RenderGameOverlayEvent event) {
      Minecraft mc = Minecraft.getInstance();
      ClientPlayerEntity clientPlayerEntity = mc.player;
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
      
      int posX = mc.getMainWindow().getScaledWidth();
      int posY = mc.getMainWindow().getScaledHeight();
      
      if (abilityDataProps == null) {
        return;
      }
      if (CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
        ForgeIngameGui.left_height++;
      }
      if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH && CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
        
        event.setCanceled(true);
        double maxHealth = clientPlayerEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
        double health = clientPlayerEntity.getHealth();
        int absorptionBonus = MathHelper.ceil(clientPlayerEntity.getAbsorptionAmount());
        int rgb = Color.RED.getRGB();
        
        if (absorptionBonus > 0) {
          rgb = Color.YELLOW.getRGB();
        }
        WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, (int)(health + absorptionBonus) + "", posX / 2 - 27, posY - 39, rgb);
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        mc.getTextureManager().bindTexture(Widget.GUI_ICONS_LOCATION);
        int i;
        for (i = MathHelper.ceil(maxHealth / 2.0D) - 1; i >= 0; i--) {
          
          int k = posX / 2 - 91 + i % 10 * 6;
          
          GuiUtils.drawTexturedModalRect(k, posY - 39, 16, 0, 9, 9, 0.0F);
        } 
        
        for (i = 0; i < (100.0D - (maxHealth - health) / maxHealth * 100.0D) / 10.0D; i++) {
          
          int k = posX / 2 - 91 + i % 10 * 6;
          
          int u = 36;
          if (absorptionBonus > 0) {
            u = 144;
          }
          GuiUtils.drawTexturedModalRect(k, posY - 39, 16 + u, 0, 9, 9, 0.0F);
        } 
      } 
      
      if (!entityStatsProps.isInCombatMode()) {
        return;
      }
      if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
        
        boolean[] visuals = ClientConfig.INSTANCE.getCooldownVisuals();
        
        boolean hasNumberVisual = visuals[0];
        boolean hasColorVisual = visuals[1];
        event.setCanceled(true);
        RenderSystem.pushMatrix();
        
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableLighting();
        RenderSystem.enableBlend();
        WyHelper.drawStringWithBorder(mc.fontRenderer, abilityDataProps.getCombatBarSet() + "", posX / 2 - 110, posY - 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
        
        mc.getTextureManager().bindTexture(ModResources.WIDGETS);
        
        for (int i = 0; i < 8; i++) {
          
          int j = i + abilityDataProps.getCombatBarSet() * 8;
          Ability abl = abilityDataProps.getEquippedAbility(j);
          
          if (abl == null) {
            
            GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 0, 0, 23, 23, 0.0F);
          }
          else {
            
            String number = "";
            
            float cooldown = 23.0F - (float)((abl.getCooldown() - 10.0D) / abl.getMaxCooldown() * 23.0D);
            float threshold = 23.0F;
            float charge = 23.0F;
            
            if (abl.isOnCooldown() && abl.getCooldown() - 10.0D > 0.0D) {
              number = ((int)abl.getCooldown() - 10) + " ";
            }
            if (abl instanceof ContinuousAbility) {
              
              ContinuousAbility cAbility = (ContinuousAbility)abl;
                        
              threshold = cAbility.getContinueTime() / Math.max(cAbility.getThreshold(),1) * 23.0F;
              if (cAbility.getThreshold() > 0 && abl.isContinuous() && cAbility.getContinueTime() > 0) {
                number = (cAbility.getThreshold() - cAbility.getContinueTime()) + " ";
              }
            } 
            if (abl instanceof ChargeableAbility) {
              
              ChargeableAbility cAbility = (ChargeableAbility)abl;
              charge = cAbility.getChargeTime() / cAbility.getMaxChargeTime() * 23.0F;
              if (abl.isCharging() && cAbility.getChargeTime() > 0) {
                number = cAbility.getChargeTime() + " ";
              }
            } 
            boolean isContinuous = (abl.isContinuous() || (abl.getState() == Ability.State.CONTINUOUS && abl.isStateForced()));
            boolean isCharging = (abl.isCharging() || (abl.getState() == Ability.State.CHARGING && abl.isStateForced()));

            
            if (abl.isOnCooldown() && !abl.isDisabled() && abl.getCooldown() > 10.0D) {
              RenderSystem.color4f(1.0F, 0.0F, 0.0F, 1.0F);
            } else if (isCharging) {
              RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
            } else if (isContinuous) {
              RenderSystem.color4f(0.0F, 0.0F, 1.0F, 1.0F);
            } else if (abl.isDisabled()) {
              RenderSystem.color4f(0.0F, 0.0F, 0.0F, 1.0F);
            } 
            
            GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 0, 0, 23, 23, 0.0F);
            
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            
            if (hasColorVisual)
            {
              if (abl.isDisabled()) {
                
                RendererHelper.drawAbilityIcon("disabled_status", (posX - 192 + i * 50) / 2, posY - 19, 3, 16, 16);
                mc.getTextureManager().bindTexture(ModResources.WIDGETS);
              }
              else if (isContinuous) {
                
                if (threshold < 2.14748365E9F) {
                  GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)threshold, 0.0F);
                }
              } else if (isCharging) {
                
                GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)charge, 0.0F);
              }
              else if (abl.isOnCooldown() && !abl.isDisabled()) {
                
                if (cooldown < 2.14748365E9F && cooldown > 0.0F) {
                  
                  GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)cooldown, 0.0F);
                  
                  if (abl.getCooldown() < 10.0D) {

                    
                    RenderSystem.pushMatrix();
                    
                    double scale = 2.5D - abl.getCooldown() / 10.0D;
                    RenderSystem.color4f(0.2F, 0.8F, 0.4F, (float)(abl.getCooldown() / 10.0D));
                    RenderSystem.translated(((posX - 200 + i * 50) / 2), (posY - 23), 1.0D);
                    RenderSystem.translated(12.0D, 12.0D, 0.0D);
                    RenderSystem.scaled(scale, scale, 1.0D);
                    RenderSystem.translated(-12.0D, -12.0D, 0.0D);
                    GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 23, 23, -1.0F);
                    
                    RenderSystem.popMatrix();
                  } 
                } 
              } 
            }

            
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            
            if (!abl.isDisabled()) {
              
              String texture = WyHelper.getResourceName(abl.hasCustomTexture() ? abl.getCustomTexture() : abl.getName());
              RendererHelper.drawAbilityIcon(texture, (posX - 192 + i * 50) / 2, posY - 19, 16, 16);
            } 
            RenderSystem.translated(0.0D, 0.0D, 2.0D);
            if (hasNumberVisual)
              WyHelper.drawStringWithBorder(mc.fontRenderer, number, (posX - 172 + i * 50) / 2 - mc.fontRenderer.getStringWidth(number) / 2, posY - 14, WyHelper.hexToRGB("#FFFFFF").getRGB()); 
            mc.getTextureManager().bindTexture(ModResources.WIDGETS);
          } 
        }  RenderSystem.disableBlend();
        
        RenderSystem.popMatrix();
      } 
    }

    
    @SubscribeEvent
    public static void updateFOV(FOVUpdateEvent event) {
      if (ClientConfig.INSTANCE.isFOVRemoved()) {
        
        if (FOV_EFFECTS.stream().anyMatch(f -> event.getEntity().isPotionActive(f))) {
          event.setNewfov(1.0F);
        }
        if (event.getEntity().isPotionActive(Effects.SPEED) && event.getEntity().isSprinting()) {
          event.setNewfov(1.1F);
        }
      } 
    }
  }
  
  @EventBusSubscriber(modid = "mineminenomi")
  public static class Common
  {
    @SubscribeEvent
    public static void onItemPickedUp(EntityItemPickupEvent event) {
      if (!CommonConfig.INSTANCE.isCombatPickupOn()) {
        
        IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)event.getPlayer());
        event.setCanceled(entityStatsProps.isInCombatMode());
      } 
    }
  }
}


