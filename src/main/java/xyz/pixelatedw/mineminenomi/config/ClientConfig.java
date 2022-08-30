/*    */ package xyz.pixelatedw.mineminenomi.config;
/*    */ 
/*    */ import com.electronwill.nightconfig.core.CommentedConfig;
/*    */ import com.electronwill.nightconfig.core.file.CommentedFileConfig;
/*    */ import com.electronwill.nightconfig.core.io.WritingMode;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import net.minecraftforge.common.ForgeConfigSpec;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientConfig
/*    */ {
/* 20 */   private static final Path CONFIG_PATH = Paths.get("config", new String[] { "mineminenomi-client.toml" });
/*    */   
/*    */   public static final ClientConfig INSTANCE;
/*    */   
/*    */   public static final ForgeConfigSpec SPEC;
/*    */   
/*    */   private Map<String, ForgeConfigSpec.BooleanValue> cooldownVisual;
/*    */   
/*    */   public ForgeConfigSpec.IntValue onFireVisibility;
/*    */   public ForgeConfigSpec.BooleanValue fovRemover;
/*    */   public ForgeConfigSpec.BooleanValue updateMessage;
/*    */   public ForgeConfigSpec.BooleanValue tooltipMessage;
/*    */   
/*    */   static {
/* 34 */     Pair<ClientConfig, ForgeConfigSpec> pair = (new ForgeConfigSpec.Builder()).configure(ClientConfig::new);
/* 35 */     SPEC = (ForgeConfigSpec)pair.getRight();
/* 36 */     INSTANCE = (ClientConfig)pair.getLeft();
/* 37 */     CommentedFileConfig config = (CommentedFileConfig)CommentedFileConfig.builder(CONFIG_PATH).sync().autoreload().writingMode(WritingMode.REPLACE).build();
/* 38 */     config.load();
/* 39 */     config.save();
/* 40 */     SPEC.setConfig((CommentedConfig)config);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void save() {
/* 45 */     SPEC.save();
/*    */   }
/*    */ 
/*    */   
/*    */   public ClientConfig(ForgeConfigSpec.Builder builder) {
/* 50 */     builder.push("General");
/*    */     
/* 52 */     this.onFireVisibility = builder.comment("Visibility when on fire while using a fire resistant fruit \nDefault: 20").defineInRange("Fire Visibility", 25, 0, 100);
/* 53 */     builder.push("Cooldown Visuals");
/*    */     
/* 55 */     String[] cooldownVisuals = { "Text", "Color" };
/* 56 */     this.cooldownVisual = new HashMap<>();
/*    */     
/* 58 */     for (String mode : cooldownVisuals) {
/* 59 */       this.cooldownVisual.put(mode, builder.define(mode, true));
/*    */     }
/* 61 */     builder.pop();
/*    */     
/* 63 */     builder.pop();
/*    */     
/* 65 */     builder.push("System");
/*    */     
/* 67 */     this.updateMessage = builder.comment("Allows the game to show a text message when the installed mod is outdated\nDefault: true").define("Update Message", true);
/* 68 */     this.fovRemover = builder.comment("Keeps the FOV fixed when the player has speed effects active\nDefault: true").define("FOV Remover", true);
/* 69 */     this.tooltipMessage = builder.comment("Displays tooltips when hovering over certain elements like config options, abilities etc\nDefault: true").define("Tooltip Messages", true);
/*    */     
/* 71 */     builder.pop();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTooltipMessageEnabled() {
/* 76 */     return ((Boolean)this.tooltipMessage.get()).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUpdateMessageEnabled() {
/* 81 */     return ((Boolean)this.updateMessage.get()).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFOVRemoved() {
/* 86 */     return ((Boolean)this.fovRemover.get()).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean[] getCooldownVisuals() {
/* 91 */     boolean hasText = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Text")).get()).booleanValue();
/* 92 */     boolean hasColor = ((Boolean)((ForgeConfigSpec.BooleanValue)this.cooldownVisual.get("Color")).get()).booleanValue();
/*    */     
/* 94 */     return new boolean[] { hasText, hasColor };
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireVisibility() {
/* 99 */     return ((Integer)this.onFireVisibility.get()).intValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\config\ClientConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */