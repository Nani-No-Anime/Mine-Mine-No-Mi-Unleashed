package xyz.pixelatedw.mineminenomi.setup;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;

public class ClientProxy {
  public ClientProxy() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "mineminenomi-client.toml");
             //[todo figure out why lambda was left blank ] ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> {return null;});
    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> {return null;});
  }
}


