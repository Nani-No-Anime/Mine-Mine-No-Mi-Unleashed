/*    */ package xyz.pixelatedw.mineminenomi.screens.config.categories;
/*    */ 
/*    */ import net.minecraft.client.settings.AbstractOption;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigCategoryList;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ConfigPage;
/*    */ import xyz.pixelatedw.mineminenomi.screens.config.ModBooleanOption;
/*    */ 
/*    */ public class QuestsConfigPage
/*    */   extends ConfigPage {
/* 13 */   private ModBooleanOption enableQuests = new ModBooleanOption("enable_quests", CommonConfig.INSTANCE.enableQuests);
/* 14 */   private ModBooleanOption questProgression = new ModBooleanOption("quest_progression", CommonConfig.INSTANCE.questProgression);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 19 */     list.addOption((AbstractOption)this.enableQuests);
/* 20 */     list.addOption((AbstractOption)this.questProgression);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 26 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.quests", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\QuestsConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */