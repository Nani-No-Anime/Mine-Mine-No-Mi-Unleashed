package xyz.pixelatedw.mineminenomi.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;





public class ClientConfig
{
  private static final Path CONFIG_PATH = Paths.get("config", new String[] { "mineminenomi-client.toml" });
  
  public static final ClientConfig INSTANCE;
  
  public static final ForgeConfigSpec SPEC;
  
  private Map<String, ForgeConfigSpec.BooleanValue> cooldownVisual;
  
  public ForgeConfigSpec.IntValue onFireVisibility;
  public ForgeConfigSpec.BooleanValue fovRemover;
  public ForgeConfigSpec.BooleanValue updateMessage;
  public ForgeConfigSpec.BooleanValue tooltipMessage;
  
  static {
    Pair<ClientConfig, ForgeConfigSpec> pair = (new ForgeConfigSpec.Builder()).configure(ClientConfig::new);
    SPEC = (ForgeConfigSpec)pair.getRight();
    INSTANCE = (ClientConfig)pair.getLeft();
    CommentedFileConfig config = (CommentedFileConfig)CommentedFileConfig.builder(CONFIG_PATH).sync().autoreload().writingMode(WritingMode.REPLACE).build();
    config.load();
    config.save();
    SPEC.setConfig((CommentedConfig)config);
  }

  
  public static void save() {
    SPEC.save();
  }

  
  public ClientConfig(ForgeConfigSpec.Builder builder) {
    builder.push("General");
    
    this.onFireVisibility = builder.comment("Visibility when on fire while using a fire resistant fruit \nDefault: 20").defineInRange("Fire Visibility", 25, 0, 100);
    builder.push("Cooldown Visuals");
    
    String[] cooldownVisuals = { "Text", "Color" };
    this.cooldownVisual = new HashMap<>();
    
    for (String mode : cooldownVisuals) {
      this.cooldownVisual.put(mode, builder.define(mode, true));
    }
    builder.pop();
    
    builder.pop();
    
    builder.push("System");
    
    this.updateMessage = builder.comment("Allows the game to show a text message when the installed mod is outdated\nDefault: true").define("Update Message", true);
    this.fovRemover = builder.comment("Keeps the FOV fixed when the player has speed effects active\nDefault: true").define("FOV Remover", true);
    this.tooltipMessage = builder.comment("Displays tooltips when hovering over certain elements like config options, abilities etc\nDefault: true").define("Tooltip Messages", true);
    
    builder.pop();
  }

  
  public boolean isTooltipMessageEnabled() {
    return ((Boolean)this.tooltipMessage.get()).booleanValue();
  }

  
  public boolean isUpdateMessageEnabled() {
    return ((Boolean)this.updateMessage.get()).booleanValue();
  }

  
  public boolean isFOVRemoved() {
    return ((Boolean)this.fovRemover.get()).booleanValue();
  }

  
  public boolean[] getCooldownVisuals() {
    boolean hasText = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Text")).get()).booleanValue();
    boolean hasColor = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Color")).get()).booleanValue();
    
    return new boolean[] { hasText, hasColor };
  }

  
  public int getFireVisibility() {
    return ((Integer)this.onFireVisibility.get()).intValue();
  }
}


