/*    */ package xyz.pixelatedw.mineminenomi.screens.config;
/*    */ 
/*    */ import net.minecraft.client.GameSettings;
/*    */ import net.minecraft.client.settings.IteratableOption;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ public class ModIteratableOption
/*    */   extends IteratableOption
/*    */   implements IExtendedOption {
/*    */   private String translationKey;
/*    */   
/*    */   public ModIteratableOption(String name, ForgeConfigSpec.EnumValue<? extends CommonConfig.IConfigEnum> value) {
/* 15 */     super("gui.mineminenomi.config.option." + name, (gameSettings, i) -> value.set(((CommonConfig.IConfigEnum)value.get()).next()), (gameSettings, option) -> {
/*    */           TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);
/*    */ 
/*    */ 
/*    */           
/*    */           return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, ((Enum)value.get()).name() })).getFormattedText();
/*    */         });
/*    */ 
/*    */     
/* 24 */     this.translationKey = "gui.mineminenomi.config.option." + name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTranslateKey() {
/* 30 */     return this.translationKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\ModIteratableOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */