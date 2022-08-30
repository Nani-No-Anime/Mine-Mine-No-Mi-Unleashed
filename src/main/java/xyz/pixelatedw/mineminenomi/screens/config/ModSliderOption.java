/*    */ package xyz.pixelatedw.mineminenomi.screens.config;
/*    */ 
/*    */ import net.minecraft.client.GameSettings;
/*    */ import net.minecraft.client.settings.SliderPercentageOption;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class ModSliderOption
/*    */   extends SliderPercentageOption
/*    */   implements IExtendedOption
/*    */ {
/*    */   private String translationKey;
/*    */   
/*    */   public ModSliderOption(String name, double min, double max, float step, ForgeConfigSpec.DoubleValue value) {
/* 16 */     super("gui.mineminenomi.config.option." + name, min, max, step, gameSettings -> Double.valueOf(((Double)value.get()).doubleValue()), (gameSettings, val) -> value.set(Double.valueOf(MathHelper.clamp(val.doubleValue(), min, max))), (gameSettings, option) -> {
/*    */           TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);
/*    */ 
/*    */ 
/*    */           
/*    */           String valStr = String.format("%,.1f", new Object[] { Double.valueOf(option.get(gameSettings)) });
/*    */ 
/*    */ 
/*    */           
/*    */           return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, valStr })).getFormattedText();
/*    */         });
/*    */ 
/*    */     
/* 29 */     this.translationKey = "gui.mineminenomi.config.option." + name;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModSliderOption(String name, double min, double max, float step, ForgeConfigSpec.IntValue value) {
/* 34 */     super("gui.mineminenomi.config.option." + name, min, max, step, gameSettings -> Double.valueOf(((Integer)value.get()).intValue()), (gameSettings, val) -> value.set(Integer.valueOf(MathHelper.clamp(val.intValue(), (int)min, (int)max))), (gameSettings, option) -> {
/*    */           TranslationTextComponent nameComp = new TranslationTextComponent("gui.mineminenomi.config.option." + name, new Object[0]);
/*    */ 
/*    */ 
/*    */           
/*    */           String valStr = String.format("%,.0f", new Object[] { Double.valueOf(option.get(gameSettings)) });
/*    */ 
/*    */ 
/*    */           
/*    */           return (new TranslationTextComponent("%s: %s", new Object[] { nameComp, valStr })).getFormattedText();
/*    */         });
/*    */ 
/*    */     
/* 47 */     this.translationKey = "gui.mineminenomi.config.option." + name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTranslateKey() {
/* 53 */     return this.translationKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\ModSliderOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */