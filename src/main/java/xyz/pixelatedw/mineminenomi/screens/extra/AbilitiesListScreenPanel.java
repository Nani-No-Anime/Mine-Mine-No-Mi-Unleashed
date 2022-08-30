/*     */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CTogglePassiveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbilitiesListScreenPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private SelectHotbarAbilitiesScreen parent;
/*     */   private IAbilityData props;
/*  31 */   private List<Entry> allAbilities = new ArrayList<>();
/*  32 */   private List<Entry> activeAbilities = new ArrayList<>();
/*  33 */   private List<Entry> passiveAbilities = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   
/*     */   public AbilitiesListScreenPanel(SelectHotbarAbilitiesScreen parent, IAbilityData abilityProps, Ability[] abilities) {
/*  38 */     super(parent.getMinecraft(), 215, 130, parent.height / 2 - 98, parent.width / 2 - 109);
/*     */     
/*  40 */     this.parent = parent;
/*  41 */     this.props = abilityProps;
/*     */     
/*  43 */     for (int i = 0; i <= abilities.length - 1; i++) {
/*     */       
/*  45 */       boolean hideInGUI = abilities[i].isHideInGUI();
/*  46 */       boolean isPassive = abilities[i] instanceof PassiveAbility;
/*  47 */       boolean isTempo = abilities[i] instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*     */       
/*  49 */       if (abilities[i] != null)
/*     */       {
/*  51 */         if (!isPassive && !isTempo) {
/*  52 */           this.activeAbilities.add(new Entry(abilities[i]));
/*  53 */         } else if ((isPassive || isTempo) && !hideInGUI) {
/*  54 */           this.passiveAbilities.add(new Entry(abilities[i]));
/*     */         } 
/*     */       }
/*     */     } 
/*  58 */     this.allAbilities.addAll(this.activeAbilities);
/*     */     
/*  60 */     if (this.passiveAbilities.size() > 0) {
/*  61 */       this.passiveAbilities.add(0, (Entry)null);
/*     */     }
/*  63 */     this.allAbilities.addAll(this.passiveAbilities);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  75 */     int size = ((List)this.activeAbilities.stream().collect(Collectors.toList())).size();
/*  76 */     if (this.passiveAbilities.size() > 0)
/*  77 */       size += this.passiveAbilities.size(); 
/*  78 */     return (int)(size * 25.0D + 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  84 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  90 */     float y = relativeY;
/*  91 */     float x = (this.parent.width / 2 - 109 + 40);
/*     */ 
/*     */     
/*  94 */     for (Entry entry : this.activeAbilities) {
/*     */       
/*  96 */       if (entry == null) {
/*     */         continue;
/*     */       }
/*  99 */       boolean flag = false;
/*     */       
/* 101 */       if (this.props.hasEquippedAbility(entry.ability)) {
/* 102 */         flag = true;
/*     */       }
/* 104 */       String abilityName = WyHelper.isNullOrEmpty(entry.ability.getDisplayName()) ? entry.ability.getName() : entry.ability.getDisplayName();
/* 105 */       (Minecraft.getInstance()).fontRenderer.drawStringWithShadow(I18n.format("ability.mineminenomi." + WyHelper.getResourceName(abilityName), new Object[0]), x, y + 4.0F, flag ? 16711680 : 16777215);
/*     */       
/* 107 */       String texture = WyHelper.getResourceName(entry.ability.hasCustomTexture() ? entry.ability.getCustomTexture() : entry.ability.getName());
/* 108 */       RendererHelper.drawAbilityIcon(texture, MathHelper.floor(x) - 30, MathHelper.floor(y), 16, 16);
/*     */       
/* 110 */       y = (float)(y + 25.0D);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     if (this.passiveAbilities.size() > 0) {
/*     */       
/* 116 */       (Minecraft.getInstance()).fontRenderer.drawStringWithShadow("Passives", x - 20.0F, y + 4.0F, 9145227);
/* 117 */       y = (float)(y + 25.0D);
/*     */       
/* 119 */       for (Entry entry : this.passiveAbilities) {
/*     */         
/* 121 */         if (entry == null) {
/*     */           continue;
/*     */         }
/* 124 */         Color textColor = WyHelper.hexToRGB("#aaff77");
/* 125 */         Color iconColor = WyHelper.hexToRGB("#FFFFFF");
/*     */         
/* 127 */         if (entry.ability instanceof PassiveAbility && ((PassiveAbility)entry.ability).isPaused()) {
/* 128 */           textColor = iconColor = WyHelper.hexToRGB("#FF0000");
/*     */         }
/* 130 */         if (entry.ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
/* 131 */           textColor = WyHelper.hexToRGB("#8B8B8B");
/*     */         }
/* 133 */         String abilityName = WyHelper.isNullOrEmpty(entry.ability.getDisplayName()) ? entry.ability.getName() : entry.ability.getDisplayName();
/* 134 */         (Minecraft.getInstance()).fontRenderer.drawStringWithShadow(I18n.format("ability.mineminenomi." + WyHelper.getResourceName(abilityName), new Object[0]), x, y + 4.0F, textColor.getRGB());
/*     */         
/* 136 */         String texture = WyHelper.getResourceName(entry.ability.hasCustomTexture() ? entry.ability.getCustomTexture() : entry.ability.getName());
/* 137 */         RendererHelper.drawAbilityIcon(texture, MathHelper.floor(x) - 30, MathHelper.floor(y), 1, 16, 16, iconColor.getRed() / 255.0F, iconColor.getGreen() / 255.0F, iconColor.getBlue() / 255.0F);
/*     */         
/* 139 */         y = (float)(y + 25.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Entry findAbilityEntry(int mouseX, int mouseY) {
/* 146 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*     */     
/* 148 */     if (offset <= 0.0D) {
/* 149 */       return null;
/*     */     }
/* 151 */     int lineIdx = (int)(offset / 25.0D);
/* 152 */     if (lineIdx >= this.allAbilities.size()) {
/* 153 */       return null;
/*     */     }
/* 155 */     Entry entry = this.allAbilities.get(lineIdx);
/* 156 */     if (entry != null) {
/* 157 */       return entry;
/*     */     }
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/* 165 */     Entry entry = findAbilityEntry((int)mouseX, (int)mouseY);
/* 166 */     boolean flag = true;
/*     */     
/* 168 */     if (entry != null) {
/*     */       
/* 170 */       if (entry.ability instanceof xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility) {
/* 171 */         return false;
/*     */       }
/* 173 */       if (entry.ability instanceof PassiveAbility) {
/*     */         
/* 175 */         PassiveAbility passive = (PassiveAbility)entry.ability;
/* 176 */         passive.setPause(!passive.isPaused());
/* 177 */         WyNetwork.sendToServer(new CTogglePassiveAbilityPacket(entry.ability, passive.isPaused()));
/*     */       }
/*     */       else {
/*     */         
/* 181 */         Ability ability = this.props.getEquippedAbility(entry.ability);
/* 182 */         if (ability != null && !ability.isOnStandby()) {
/* 183 */           flag = false;
/*     */         }
/* 185 */         if (isMouseOver(mouseX, mouseY) && this.parent.slotSelected >= 0 && flag && entry.ability != null) {
/*     */           
/* 187 */           this.props.setEquippedAbility(this.parent.slotSelected, entry.ability);
/* 188 */           WyNetwork.sendToServer(new CEquipAbilityPacket(this.parent.slotSelected, entry.ability));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 194 */     return super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMouseOver(double mouseX, double mouseY) {
/* 200 */     return (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/* 206 */     super.render(mouseX, mouseY, partialTicks);
/*     */     
/* 208 */     if (ClientConfig.INSTANCE.isTooltipMessageEnabled() && isMouseOver(mouseX, mouseY)) {
/*     */       
/* 210 */       Entry hoveredEntity = findAbilityEntry(mouseX, mouseY);
/*     */       
/* 212 */       if (hoveredEntity == null || hoveredEntity.ability.getDescription() == null) {
/*     */         return;
/*     */       }
/* 215 */       StringBuilder sb = new StringBuilder();
/* 216 */       sb.append(hoveredEntity.ability.getDisplayName() + "\n\n");
/* 217 */       sb.append(hoveredEntity.ability.getDescription().getFormattedText());
/*     */       
/* 219 */       String tooltip = sb.toString();
/* 220 */       double posX = (this.parent.width - 220);
/* 221 */       double posY = (this.parent.height - 120);
/* 222 */       double width = posX + 220.0D;
/* 223 */       double height = posY + 120.0D;
/* 224 */       int fgColor = 16777215;
/* 225 */       int bgColor1 = WyHelper.hexToRGB("#222222FF").getRGB();
/* 226 */       int bgColor2 = WyHelper.hexToRGB("#686868EE").getRGB();
/* 227 */       fillGradient((int)posX, (int)posY, (int)width, (int)height, bgColor1, bgColor2);
/* 228 */       List<String> strings = WyHelper.splitString((this.parent.getMinecraft()).fontRenderer, tooltip, (int)posX, 210);
/* 229 */       for (int b = 0; b < strings.size(); b++)
/*     */       {
/* 231 */         WyHelper.drawStringWithBorder((this.parent.getMinecraft()).fontRenderer, strings.get(b), (int)posX + 5, 5 + (int)posY + 10 * b, fgColor);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   class Entry
/*     */   {
/*     */     private Ability ability;
/*     */     
/*     */     public Entry(Ability ability) {
/* 242 */       this.ability = ability;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\AbilitiesListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */