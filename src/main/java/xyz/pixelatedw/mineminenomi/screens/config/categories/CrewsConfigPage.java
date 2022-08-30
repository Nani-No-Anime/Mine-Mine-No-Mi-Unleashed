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
/*    */ public class CrewsConfigPage
/*    */   extends ConfigPage {
/* 14 */   private ModSliderOption bountyRequirement = new ModSliderOption("bounty_requirement", 0.0D, 100000.0D, 100.0F, CommonConfig.INSTANCE.bountyRequirement);
/* 15 */   private ModBooleanOption worldMessageOnCrewCreate = new ModBooleanOption("new_crew_world_message", CommonConfig.INSTANCE.worldMessageOnCrewCreate);
/* 16 */   private ModBooleanOption disableFriendlyFire = new ModBooleanOption("disable_friendly_fire", CommonConfig.INSTANCE.disableFriendlyFire);
/*    */ 
/*    */ 
/*    */   
/*    */   public void init(ConfigCategoryList list) {
/* 21 */     list.addOption((AbstractOption)this.bountyRequirement);
/* 22 */     list.addOption((AbstractOption)this.worldMessageOnCrewCreate);
/* 23 */     list.addOption((AbstractOption)this.disableFriendlyFire);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getTitle() {
/* 29 */     return (ITextComponent)new TranslationTextComponent("gui.mineminenomi.config.category.crews", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\categories\CrewsConfigPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */