/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.io.File;
/*     */ import net.minecraft.client.GameSettings;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.SettingsScreen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.ForgeI18n;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.BountyConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.ChallengesConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.CrewsConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.DevilFruitsConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.GeneralConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.OresConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.QuestsConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.StructuresConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.SystemConfigPage;
/*     */ import xyz.pixelatedw.mineminenomi.screens.config.categories.WorldEventsConfigPage;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ConfigScreen
/*     */   extends SettingsScreen
/*     */ {
/*  35 */   private ConfigPage generalCategory = (ConfigPage)new GeneralConfigPage();
/*  36 */   private ConfigPage devilFruitsCategory = (ConfigPage)new DevilFruitsConfigPage();
/*  37 */   private ConfigPage structuresCategory = (ConfigPage)new StructuresConfigPage();
/*  38 */   private ConfigPage worldEventsCategory = (ConfigPage)new WorldEventsConfigPage();
/*  39 */   private ConfigPage crewsCategory = (ConfigPage)new CrewsConfigPage();
/*  40 */   private ConfigPage oresCategory = (ConfigPage)new OresConfigPage();
/*  41 */   private ConfigPage bountyCategory = (ConfigPage)new BountyConfigPage();
/*  42 */   private ConfigPage questsCategory = (ConfigPage)new QuestsConfigPage();
/*  43 */   private ConfigPage challengesCategory = (ConfigPage)new ChallengesConfigPage();
/*  44 */   private ConfigPage systemCategory = (ConfigPage)new SystemConfigPage();
/*     */   
/*  46 */   private ConfigPage[] categories = new ConfigPage[] { this.generalCategory, this.devilFruitsCategory, this.structuresCategory, this.worldEventsCategory, this.crewsCategory, this.oresCategory, this.bountyCategory, this.questsCategory, this.challengesCategory, this.systemCategory };
/*     */   
/*  48 */   private int categoryOffset = 0;
/*  49 */   private ConfigPage selectedCategory = this.generalCategory;
/*     */   
/*     */   private ConfigCategoryList list;
/*     */ 
/*     */   
/*     */   public ConfigScreen(Screen parent, GameSettings settings) {
/*  55 */     super(parent, settings, (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.title", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  61 */     this.list = new ConfigCategoryList(this);
/*  62 */     this.buttons.clear();
/*  63 */     this.children.clear();
/*     */     
/*  65 */     int posX = this.width / 2 - 265;
/*     */     
/*  67 */     Button prevButton = new Button(posX + 60, 25, 20, 20, "<", x -> {
/*     */           if (this.categoryOffset > 0) {
/*     */             this.categoryOffset--;
/*     */ 
/*     */             
/*     */             init();
/*     */           } 
/*     */         });
/*     */     
/*  76 */     int j = 0;
/*  77 */     for (int i = this.categoryOffset; i < this.categories.length; i++) {
/*     */       
/*  79 */       if (j >= 4) {
/*     */         break;
/*     */       }
/*  82 */       ConfigPage category = this.categories[i];
/*     */       
/*  84 */       if (category != null) {
/*     */ 
/*     */         
/*  87 */         posX += 90;
/*  88 */         Button categoryButton = new Button(posX, 25, 80, 20, category.getTitle().getFormattedText() + "...", x -> {
/*     */               this.selectedCategory = category;
/*     */               
/*     */               init();
/*     */             });
/*  93 */         if (this.selectedCategory == category) {
/*  94 */           categoryButton.active = false;
/*     */         }
/*     */         
/*  97 */         j++;
/*     */       } 
/*     */     } 
/* 100 */     Button nextButton = new Button(posX + 90, 25, 20, 20, ">", x -> {
/*     */           if (this.categoryOffset < this.categories.length - 4) {
/*     */             this.categoryOffset++;
/*     */ 
/*     */             
/*     */             init();
/*     */           } 
/*     */         });
/*     */     
/* 109 */     if (this.categoryOffset <= 0)
/* 110 */       prevButton.active = false; 
/* 111 */     if (this.categoryOffset >= this.categories.length - 4) {
/* 112 */       nextButton.active = false;
/*     */     }
/* 114 */     this.selectedCategory.init(this.list);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     addButton((Widget)new Button(this.width / 2 - 154, this.height - 27, 150, 20, I18n.format("gui.mineminenomi.config.open_config", new Object[0]), x -> Util.getOSType().openFile(new File(CommonConfig.CONFIG_PATH.toString()))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     addButton((Widget)new Button(this.width / 2 + 4, this.height - 27, 150, 20, ForgeI18n.parseMessage("gui.done", new Object[0]), x -> {
/*     */             onClose();
/*     */             this.minecraft.displayGuiScreen(this.parentScreen);
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/* 133 */     renderBackground();
/*     */ 
/*     */ 
/*     */     
/* 137 */     drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 8, 16777215);
/*     */     
/* 139 */     RenderSystem.pushMatrix();
/* 140 */     RenderSystem.translated((this.width / 2), (this.height / 2 - 20), 0.0D);
/* 141 */     RenderSystem.scaled(2.0D, 2.0D, 2.0D);
/* 142 */     drawCenteredString(this.font, "Under Maintenance", 0, 0, 16777215);
/* 143 */     RenderSystem.popMatrix();
/*     */     
/* 145 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 151 */     CommonConfig.save();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\ConfigScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */