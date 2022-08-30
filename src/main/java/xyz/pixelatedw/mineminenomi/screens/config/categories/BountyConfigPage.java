/*    */ package xyz.pixelatedw.mineminenomi.screens.config.categories;
/*    */ 
/*    */ import net.minecraft.client.settings.AbstractOption;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;
/*    */ 
/*    */ public class BountyConfigPage
/*    */   extends ConfigPage {
/* 14 */   private ModBooleanOption wantedPosterPackages = new ModBooleanOption("drop_wanted_poster_packages", CommonConfig.INSTANCE.wantedPosterPackages);
/* 15 */   private ModSliderOption timeBetweenPackageDrops = new ModSliderOption("time_package_drops", 0.0D, 72000.0D, 200.0F, CommonConfig.INSTANCE.timeBetweenPackageDrops);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 20 */     list.addOption((AbstractOption)this.wantedPosterPackages);
/* 21 */     list.addOption((AbstractOption)this.timeBetweenPackageDrops);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 27 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.bounty", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\BountyConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */