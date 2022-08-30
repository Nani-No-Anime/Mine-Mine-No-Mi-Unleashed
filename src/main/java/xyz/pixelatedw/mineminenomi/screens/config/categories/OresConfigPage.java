/*    */ package xyz.pixelatedw.mineminenomi.screens.config.categories;
/*    */ 
/*    */ import net.minecraft.client.settings.AbstractOption;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModSliderOption;
/*    */ 
/*    */ public class OresConfigPage
/*    */   extends ConfigPage {
/* 13 */   private ModSliderOption kairosekiSpawnCount = new ModSliderOption("spawn_count", 1.0D, 20.0D, 1.0F, CommonConfig.INSTANCE.kairosekiSpawnCount);
/* 14 */   private ModSliderOption kairosekiSpawnMaxHeight = new ModSliderOption("spawn_height", 1.0D, 128.0D, 1.0F, CommonConfig.INSTANCE.kairosekiSpawnMaxHeight);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 19 */     list.addCategory("Kairoseki");
/* 20 */     list.addOption((AbstractOption)this.kairosekiSpawnCount);
/* 21 */     list.addOption((AbstractOption)this.kairosekiSpawnMaxHeight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 27 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.ores", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\OresConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */