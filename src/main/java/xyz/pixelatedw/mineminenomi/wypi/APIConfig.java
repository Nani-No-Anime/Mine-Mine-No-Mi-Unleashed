/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.IExtensibleEnum;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APIConfig
/*     */ {
/*     */   public static String projectId;
/*     */   public static String projectName;
/*     */   public static String projectVersion;
/*     */   private static String projectResourceFolder;
/*     */   public static final String API_VERSION = "1.3.0";
/*     */   
/*     */   public static void setup(String modName, String modId, String version) {
/*  24 */     projectId = modId;
/*  25 */     projectName = modName;
/*  26 */     projectVersion = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupResourceFolderPath() {
/*  37 */     if (!WyDebug.isDebug()) {
/*     */       return;
/*     */     }
/*  40 */     String basicPath = System.getProperty("user.dir");
/*  41 */     projectResourceFolder = basicPath.replace("/run", "") + "/src/main/resources";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getResourceFolderPath() {
/*  46 */     return projectResourceFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final BuildMode BUILD_MODE = BuildMode.RELEASE;
/*     */   public static final int MAX_SELECTED_ABILITIES = 8;
/*     */   public static final int MAX_IN_PROGRESS_QUESTS = 4;
/*     */   
/*     */   public enum BuildMode {
/*  59 */     RELEASE, DEV, EARLY_ACCESS, QA, PROMO;
/*     */   }
/*     */   
/*     */   public enum AccountType
/*     */   {
/*  64 */     NORMAL, ROOKIE, SUPERNOVA, CELESTIAL_DRAGON, TESTER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum AbilityCategory
/*     */     implements IExtensibleEnum
/*     */   {
/*  76 */     ALL;
/*     */     
/*     */     private Function<PlayerEntity, ResourceLocation> iconFunction;
/*     */ 
/*     */     
/*     */     AbilityCategory() {
/*  82 */       this.iconFunction = null;
/*     */     }
/*     */ 
/*     */     
/*     */     AbilityCategory(Function<PlayerEntity, ResourceLocation> function) {
/*  87 */       this.iconFunction = function;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public ResourceLocation getIcon(PlayerEntity player) {
/*  93 */       if (this.iconFunction == null)
/*  94 */         return null; 
/*  95 */       return this.iconFunction.apply(player);
/*     */     }
/*     */ 
/*     */     
/*     */     public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function) {
/* 100 */       throw new IllegalStateException("Enum not extended");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\APIConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */