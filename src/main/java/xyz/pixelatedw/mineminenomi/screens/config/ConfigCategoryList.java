/*     */ package xyz.pixelatedw.mineminenomi.screens.config;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.GameSettings;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.list.AbstractList;
/*     */ import net.minecraft.client.gui.widget.list.AbstractOptionList;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.AbstractOption;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.screens.ConfigScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ConfigCategoryList
/*     */   extends AbstractOptionList<ConfigCategoryList.Entry>
/*     */ {
/*     */   private ConfigScreen parent;
/*     */   
/*     */   public ConfigCategoryList(ConfigScreen parent) {
/*  32 */     super(parent.getMinecraft(), parent.width + 45, parent.height, 50, parent.height - 40, 35);
/*  33 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  39 */     super.render(mouseX, mouseY, partialTicks);
/*     */     
/*  41 */     for (Entry entry : children()) {
/*     */       
/*  43 */       if (!(entry instanceof Row)) {
/*     */         continue;
/*     */       }
/*  46 */       Row row = (Row)entry;
/*     */       
/*  48 */       int i = 0;
/*  49 */       if (ClientConfig.INSTANCE.isTooltipMessageEnabled())
/*     */       {
/*  51 */         for (Widget widget : row.widgets) {
/*     */           
/*  53 */           boolean isHovered = (mouseY > 50 && mouseY < this.height - 40 && mouseX >= widget.x && mouseY >= widget.y && mouseX < widget.x + widget.getWidth() && mouseY < widget.y + widget.getHeight());
/*  54 */           if (isHovered) {
/*     */             
/*  56 */             String tooltip = (new TranslationTextComponent((String)row.getTranslationKeys().get(i) + ".tooltip", new Object[0])).getFormattedText();
/*  57 */             double posX = (this.width - 325);
/*  58 */             double posY = (this.height - 190);
/*  59 */             double width = posX + 280.0D;
/*  60 */             double height = posY + 150.0D;
/*  61 */             int fgColor = 16777215;
/*  62 */             int bgColor1 = WyHelper.hexToRGB("#222222FF").getRGB();
/*  63 */             int bgColor2 = WyHelper.hexToRGB("#686868EE").getRGB();
/*  64 */             fillGradient((int)posX, (int)posY, (int)width, (int)height, bgColor1, bgColor2);
/*  65 */             List<String> strings = WyHelper.splitString(this.minecraft.fontRenderer, tooltip, (int)posX, 270);
/*  66 */             for (int b = 0; b < strings.size(); b++)
/*     */             {
/*  68 */               WyHelper.drawStringWithBorder((this.parent.getMinecraft()).fontRenderer, strings.get(b), (int)posX + 5, 5 + (int)posY + 10 * b, fgColor);
/*     */             }
/*     */           } 
/*  71 */           i++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int addCategory(String name) {
/*  79 */     return addEntry(new Category(name));
/*     */   }
/*     */ 
/*     */   
/*     */   public int addOption(AbstractOption option) {
/*  84 */     return addEntry(new Row(this.minecraft.gameSettings, this.width, option));
/*     */   }
/*     */ 
/*     */   
/*     */   public int addOption(AbstractOption option1, @Nullable AbstractOption option2) {
/*  89 */     return addEntry(new Row(this.minecraft.gameSettings, this.width, option1, option2));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addOptions(AbstractOption[] options) {
/*  94 */     for (int i = 0; i < options.length; i += 2)
/*     */     {
/*  96 */       addOption(options[i], (i < options.length - 1) ? options[i + 1] : null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollbarPosition() {
/* 103 */     return super.getScrollbarPosition() + 35;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowWidth() {
/* 109 */     return super.getRowWidth() + 140;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static abstract class Entry
/*     */     extends AbstractOptionList.Entry<Entry> {}
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public class Category
/*     */     extends Entry {
/*     */     private final String labelText;
/*     */     
/*     */     public Category(String name) {
/* 122 */       this.labelText = I18n.format(name, new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void render(int p_render_1_, int p_render_2_, int p_render_3_, int p_render_4_, int p_render_5_, int mouseX, int mouseY, boolean p_render_8_, float partialTicks) {
/* 128 */       RenderSystem.pushMatrix();
/* 129 */       RenderSystem.translated((ConfigCategoryList.this.width / 2 + 30), (p_render_2_ + p_render_5_ + 35), 0.0D);
/* 130 */       RenderSystem.translated(128.0D, 128.0D, 0.0D);
/* 131 */       RenderSystem.scaled(1.4D, 1.4D, 1.4D);
/* 132 */       RenderSystem.translated(-128.0D, -128.0D, -5.0D);
/* 133 */       WyHelper.drawStringWithBorder(ConfigCategoryList.this.minecraft.fontRenderer, this.labelText, -ConfigCategoryList.this.minecraft.fontRenderer.getStringWidth(this.labelText) / 2, 0, 16777215);
/* 134 */       RenderSystem.popMatrix();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean changeFocus(boolean p_changeFocus_1_) {
/* 140 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public List<? extends IGuiEventListener> children() {
/* 146 */       return Collections.emptyList();
/*     */     }
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static class Row
/*     */     extends Entry {
/*     */     private final List<Widget> widgets;
/* 154 */     private final List<String> translationKeys = new ArrayList<>();
/*     */ 
/*     */     
/*     */     private Row(GameSettings settings, int guiWidth, AbstractOption option) {
/* 158 */       this.widgets = (List<Widget>)ImmutableList.of(option.createWidget(settings, guiWidth / 2 - 175, 0, 310));
/* 159 */       if (option instanceof IExtendedOption) {
/* 160 */         this.translationKeys.add(((IExtendedOption)option).getTranslateKey());
/*     */       }
/*     */     }
/*     */     
/*     */     private Row(GameSettings settings, int guiWidth, AbstractOption leftOption, @Nullable AbstractOption rightOption) {
/* 165 */       Widget widget = leftOption.createWidget(settings, guiWidth / 2 - 175, 0, 150);
/* 166 */       if (leftOption instanceof IExtendedOption)
/* 167 */         this.translationKeys.add(((IExtendedOption)leftOption).getTranslateKey()); 
/* 168 */       if (rightOption == null) {
/* 169 */         this.widgets = (List<Widget>)ImmutableList.of(widget);
/*     */       } else {
/*     */         
/* 172 */         this.widgets = (List<Widget>)ImmutableList.of(widget, rightOption.createWidget(settings, guiWidth / 2 - 20, 0, 150));
/* 173 */         if (rightOption instanceof IExtendedOption) {
/* 174 */           this.translationKeys.add(((IExtendedOption)rightOption).getTranslateKey());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void render(int p_render_1_, int p_render_2_, int p_render_3_, int p_render_4_, int p_render_5_, int mouseX, int mouseY, boolean p_render_8_, float partialTicks) {
/* 181 */       this.widgets.forEach(widget -> {
/*     */             widget.y = p_render_2_;
/*     */             widget.render(mouseX, mouseY, partialTicks);
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public List<? extends IGuiEventListener> children() {
/* 191 */       return (List)this.widgets;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<String> getTranslationKeys() {
/* 196 */       return this.translationKeys;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\ConfigCategoryList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */