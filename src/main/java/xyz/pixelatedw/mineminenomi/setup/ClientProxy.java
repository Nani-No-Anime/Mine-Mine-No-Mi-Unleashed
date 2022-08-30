/*    */ package xyz.pixelatedw.mineminenomi.setup;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraftforge.fml.ExtensionPoint;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.screens.ConfigScreen;
/*    */ 
/*    */ public class ClientProxy {
/*    */   public ClientProxy() {
/* 14 */     ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "mineminenomi-client.toml");
             //[todo figure out why lambda was left blank ] ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> {return null;});
/* 15 */     ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> {return null;});
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\setup\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */