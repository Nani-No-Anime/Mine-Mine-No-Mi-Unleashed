package xyz.pixelatedw.mineminenomi.wypi;

import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IExtensibleEnum;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;






public class APIConfig
{
  public static String projectId;
  public static String projectName;
  public static String projectVersion;
  private static String projectResourceFolder;
  public static final String API_VERSION = "1.3.0";
  
  public static void setup(String modName, String modId, String version) {
    projectId = modId;
    projectName = modName;
    projectVersion = version;
  }







  
  public static void setupResourceFolderPath() {
    if (!WyDebug.isDebug()) {
      return;
    }
    String basicPath = System.getProperty("user.dir");
    projectResourceFolder = basicPath.replace("/run", "") + "/src/main/resources";
  }

  
  public static String getResourceFolderPath() {
    return projectResourceFolder;
  }





  
  public static final BuildMode BUILD_MODE = BuildMode.RELEASE;
  public static final int MAX_SELECTED_ABILITIES = 8;
  public static final int MAX_IN_PROGRESS_QUESTS = 4;
  
  public enum BuildMode {
    RELEASE, DEV, EARLY_ACCESS, QA, PROMO;
  }
  
  public enum AccountType
  {
    NORMAL, ROOKIE, SUPERNOVA, CELESTIAL_DRAGON, TESTER;
  }






  
  public enum AbilityCategory
    implements IExtensibleEnum
  {
    ALL;
    
    private Function<PlayerEntity, ResourceLocation> iconFunction;

    
    AbilityCategory() {
      this.iconFunction = null;
    }

    
    AbilityCategory(Function<PlayerEntity, ResourceLocation> function) {
      this.iconFunction = function;
    }

    
    @Nullable
    public ResourceLocation getIcon(PlayerEntity player) {
      if (this.iconFunction == null)
        return null; 
      return this.iconFunction.apply(player);
    }

    
    public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function) {
      throw new IllegalStateException("Enum not extended");
    }
  }
}


