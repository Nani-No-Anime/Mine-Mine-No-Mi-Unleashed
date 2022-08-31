/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.gui.ForgeIngameGui;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombatModeEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*  49 */     private static final ArrayList<Effect> FOV_EFFECTS = new ArrayList<>(Arrays.asList(new Effect[] { ModEffects.PARALYSIS, ModEffects.GUARDING, Effects.SPEED, Effects.SLOWNESS, ModEffects.MOVEMENT_BLOCKED, ModEffects.CANDLE_LOCK }));
/*     */ 
/*     */     
/*     */     @SubscribeEvent(priority = EventPriority.LOWEST)
/*     */     public static void onRenderOverlay(RenderGameOverlayEvent event) {
/*  54 */       Minecraft mc = Minecraft.getInstance();
/*  55 */       ClientPlayerEntity clientPlayerEntity = mc.player;
/*  56 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*  57 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/*  59 */       int posX = mc.getMainWindow().getScaledWidth();
/*  60 */       int posY = mc.getMainWindow().getScaledHeight();
/*     */       
/*  62 */       if (abilityDataProps == null) {
/*     */         return;
/*     */       }
/*  65 */       if (CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
/*  66 */         ForgeIngameGui.left_height++;
/*     */       }
/*  68 */       if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH && CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
/*     */         
/*  70 */         event.setCanceled(true);
/*  71 */         double maxHealth = clientPlayerEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
/*  72 */         double health = clientPlayerEntity.getHealth();
/*  73 */         int absorptionBonus = MathHelper.ceil(clientPlayerEntity.getAbsorptionAmount());
/*  74 */         int rgb = Color.RED.getRGB();
/*     */         
/*  76 */         if (absorptionBonus > 0) {
/*  77 */           rgb = Color.YELLOW.getRGB();
/*     */         }
/*  79 */         WyHelper.drawStringWithBorder((Minecraft.getInstance()).fontRenderer, (int)(health + absorptionBonus) + "", posX / 2 - 27, posY - 39, rgb);
/*     */         
/*  81 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/*  83 */         mc.getTextureManager().bindTexture(Widget.GUI_ICONS_LOCATION);
/*     */         int i;
/*  85 */         for (i = MathHelper.ceil(maxHealth / 2.0D) - 1; i >= 0; i--) {
/*     */           
/*  87 */           int k = posX / 2 - 91 + i % 10 * 6;
/*     */           
/*  89 */           GuiUtils.drawTexturedModalRect(k, posY - 39, 16, 0, 9, 9, 0.0F);
/*     */         } 
/*     */         
/*  92 */         for (i = 0; i < (100.0D - (maxHealth - health) / maxHealth * 100.0D) / 10.0D; i++) {
/*     */           
/*  94 */           int k = posX / 2 - 91 + i % 10 * 6;
/*     */           
/*  96 */           int u = 36;
/*  97 */           if (absorptionBonus > 0) {
/*  98 */             u = 144;
/*     */           }
/* 100 */           GuiUtils.drawTexturedModalRect(k, posY - 39, 16 + u, 0, 9, 9, 0.0F);
/*     */         } 
/*     */       } 
/*     */       
/* 104 */       if (!entityStatsProps.isInCombatMode()) {
/*     */         return;
/*     */       }
/* 107 */       if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
/*     */         
/* 109 */         boolean[] visuals = ClientConfig.INSTANCE.getCooldownVisuals();
/*     */         
/* 111 */         boolean hasNumberVisual = visuals[0];
/* 112 */         boolean hasColorVisual = visuals[1];
/* 113 */         event.setCanceled(true);
/* 114 */         RenderSystem.pushMatrix();
/*     */         
/* 116 */         RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 117 */         RenderSystem.disableLighting();
/* 118 */         RenderSystem.enableBlend();
/* 119 */         WyHelper.drawStringWithBorder(mc.fontRenderer, abilityDataProps.getCombatBarSet() + "", posX / 2 - 110, posY - 14, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */         
/* 121 */         mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*     */         
/* 123 */         for (int i = 0; i < 8; i++) {
/*     */           
/* 125 */           int j = i + abilityDataProps.getCombatBarSet() * 8;
/* 126 */           Ability abl = abilityDataProps.getEquippedAbility(j);
/*     */           
/* 128 */           if (abl == null) {
/*     */             
/* 130 */             GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 0, 0, 23, 23, 0.0F);
/*     */           }
/*     */           else {
/*     */             
/* 134 */             String number = "";
/*     */             
/* 136 */             float cooldown = 23.0F - (float)((abl.getCooldown() - 10.0D) / abl.getMaxCooldown() * 23.0D);
/* 137 */             float threshold = 23.0F;
/* 138 */             float charge = 23.0F;
/*     */             
/* 140 */             if (abl.isOnCooldown() && abl.getCooldown() - 10.0D > 0.0D) {
/* 141 */               number = ((int)abl.getCooldown() - 10) + " ";
/*     */             }
/* 143 */             if (abl instanceof ContinuousAbility) {
/*     */               
/* 145 */               ContinuousAbility cAbility = (ContinuousAbility)abl;
                        
/* 146 */               threshold = cAbility.getContinueTime() / Math.max(cAbility.getThreshold(),1) * 23.0F;
/* 147 */               if (cAbility.getThreshold() > 0 && abl.isContinuous() && cAbility.getContinueTime() > 0) {
/* 148 */                 number = (cAbility.getThreshold() - cAbility.getContinueTime()) + " ";
/*     */               }
/*     */             } 
/* 151 */             if (abl instanceof ChargeableAbility) {
/*     */               
/* 153 */               ChargeableAbility cAbility = (ChargeableAbility)abl;
/* 154 */               charge = cAbility.getChargeTime() / cAbility.getMaxChargeTime() * 23.0F;
/* 155 */               if (abl.isCharging() && cAbility.getChargeTime() > 0) {
/* 156 */                 number = cAbility.getChargeTime() + " ";
/*     */               }
/*     */             } 
/* 159 */             boolean isContinuous = (abl.isContinuous() || (abl.getState() == Ability.State.CONTINUOUS && abl.isStateForced()));
/* 160 */             boolean isCharging = (abl.isCharging() || (abl.getState() == Ability.State.CHARGING && abl.isStateForced()));
/*     */ 
/*     */             
/* 163 */             if (abl.isOnCooldown() && !abl.isDisabled() && abl.getCooldown() > 10.0D) {
/* 164 */               RenderSystem.color4f(1.0F, 0.0F, 0.0F, 1.0F);
/* 165 */             } else if (isCharging) {
/* 166 */               RenderSystem.color4f(1.0F, 1.0F, 0.0F, 1.0F);
/* 167 */             } else if (isContinuous) {
/* 168 */               RenderSystem.color4f(0.0F, 0.0F, 1.0F, 1.0F);
/* 169 */             } else if (abl.isDisabled()) {
/* 170 */               RenderSystem.color4f(0.0F, 0.0F, 0.0F, 1.0F);
/*     */             } 
/*     */             
/* 173 */             GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 0, 0, 23, 23, 0.0F);
/*     */             
/* 175 */             RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */             
/* 178 */             if (hasColorVisual)
/*     */             {
/* 180 */               if (abl.isDisabled()) {
/*     */                 
/* 182 */                 RendererHelper.drawAbilityIcon("disabled_status", (posX - 192 + i * 50) / 2, posY - 19, 3, 16, 16);
/* 183 */                 mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*     */               }
/* 185 */               else if (isContinuous) {
/*     */                 
/* 187 */                 if (threshold < 2.14748365E9F) {
/* 188 */                   GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)threshold, 0.0F);
/*     */                 }
/* 190 */               } else if (isCharging) {
/*     */                 
/* 192 */                 GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)charge, 0.0F);
/*     */               }
/* 194 */               else if (abl.isOnCooldown() && !abl.isDisabled()) {
/*     */                 
/* 196 */                 if (cooldown < 2.14748365E9F && cooldown > 0.0F) {
/*     */                   
/* 198 */                   GuiUtils.drawTexturedModalRect((posX - 200 + i * 50) / 2, posY - 23, 24, 0, 23, (int)cooldown, 0.0F);
/*     */                   
/* 200 */                   if (abl.getCooldown() < 10.0D) {
/*     */ 
/*     */                     
/* 203 */                     RenderSystem.pushMatrix();
/*     */                     
/* 205 */                     double scale = 2.5D - abl.getCooldown() / 10.0D;
/* 206 */                     RenderSystem.color4f(0.2F, 0.8F, 0.4F, (float)(abl.getCooldown() / 10.0D));
/* 207 */                     RenderSystem.translated(((posX - 200 + i * 50) / 2), (posY - 23), 1.0D);
/* 208 */                     RenderSystem.translated(12.0D, 12.0D, 0.0D);
/* 209 */                     RenderSystem.scaled(scale, scale, 1.0D);
/* 210 */                     RenderSystem.translated(-12.0D, -12.0D, 0.0D);
/* 211 */                     GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 23, 23, -1.0F);
/*     */                     
/* 213 */                     RenderSystem.popMatrix();
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/* 220 */             RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */             
/* 223 */             if (!abl.isDisabled()) {
/*     */               
/* 225 */               String texture = WyHelper.getResourceName(abl.hasCustomTexture() ? abl.getCustomTexture() : abl.getName());
/* 226 */               RendererHelper.drawAbilityIcon(texture, (posX - 192 + i * 50) / 2, posY - 19, 16, 16);
/*     */             } 
/* 228 */             RenderSystem.translated(0.0D, 0.0D, 2.0D);
/* 229 */             if (hasNumberVisual)
/* 230 */               WyHelper.drawStringWithBorder(mc.fontRenderer, number, (posX - 172 + i * 50) / 2 - mc.fontRenderer.getStringWidth(number) / 2, posY - 14, WyHelper.hexToRGB("#FFFFFF").getRGB()); 
/* 231 */             mc.getTextureManager().bindTexture(ModResources.WIDGETS);
/*     */           } 
/* 233 */         }  RenderSystem.disableBlend();
/*     */         
/* 235 */         RenderSystem.popMatrix();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void updateFOV(FOVUpdateEvent event) {
/* 242 */       if (ClientConfig.INSTANCE.isFOVRemoved()) {
/*     */         
/* 244 */         if (FOV_EFFECTS.stream().anyMatch(f -> event.getEntity().isPotionActive(f))) {
/* 245 */           event.setNewfov(1.0F);
/*     */         }
/* 247 */         if (event.getEntity().isPotionActive(Effects.SPEED) && event.getEntity().isSprinting()) {
/* 248 */           event.setNewfov(1.1F);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onItemPickedUp(EntityItemPickupEvent event) {
/* 259 */       if (!CommonConfig.INSTANCE.isCombatPickupOn()) {
/*     */         
/* 261 */         IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/* 262 */         event.setCanceled(entityStatsProps.isInCombatMode());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\CombatModeEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */