/*    */ package xyz.pixelatedw.mineminenomi.screens.config;
/*    */ 
/*    */ import net.minecraft.client.GameSettings;
/*    */ import net.minecraft.client.settings.BooleanOption;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ 
/*    */ public class ModBooleanOption
/*    */   extends BooleanOption
/*    */   implements IExtendedOption {
/*    */   private String translationKey;
/*    */   
/*    */   public ModBooleanOption(String name, ForgeConfigSpec.BooleanValue value) {
/* 13 */     super("gui.mineminenomi.config.option." + name, gameSettings -> ((Boolean)value.get()).booleanValue(), (gameSettings, val) -> value.set(val));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 21 */     this.translationKey = "gui.mineminenomi.config.option." + name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTranslateKey() {
/* 27 */     return this.translationKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\config\ModBooleanOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */