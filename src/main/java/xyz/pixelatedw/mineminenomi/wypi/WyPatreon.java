package xyz.pixelatedw.mineminenomi.wypi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;




public class WyPatreon
{
  public static final HashMap<UUID, List<APIConfig.AccountType>> PATREONS = new HashMap<>();

  
  public static boolean isDevBuild() {
    return (APIConfig.BUILD_MODE == APIConfig.BuildMode.DEV);
  }

  
  public static boolean isEarlyAccessBuild() {
    return (APIConfig.BUILD_MODE == APIConfig.BuildMode.EARLY_ACCESS);
  }

  
  public static boolean isReleaseBuild() {
    return (APIConfig.BUILD_MODE == APIConfig.BuildMode.RELEASE);
  }

  
  public static boolean isQABuild() {
    return (APIConfig.BUILD_MODE == APIConfig.BuildMode.QA);
  }

  
  public static boolean isPromoBuild() {
    return (APIConfig.BUILD_MODE == APIConfig.BuildMode.PROMO);
  }

  
  @Nullable
  private static List<APIConfig.AccountType> getPatreonLevel(PlayerEntity player) throws IOException {
    String apiURL = "/patreon/" + player.getUniqueID().toString();
    String[] result = WyHelper.<String[]>sendGET(apiURL, String[].class);
    
    if (result != null && result.length > 0) {
      
      List<APIConfig.AccountType> types = new ArrayList<>();
      for (String group : result) {
        
        String formattedGroupName = WyHelper.getResourceName(group);
        if (formattedGroupName.equalsIgnoreCase("patreon_rookie")) {
          types.add(APIConfig.AccountType.ROOKIE);
        }
        if (formattedGroupName.equalsIgnoreCase("patreon_supernova")) {
          types.add(APIConfig.AccountType.SUPERNOVA);
        }
        if (formattedGroupName.equalsIgnoreCase("patreon_celestial_dragon")) {
          types.add(APIConfig.AccountType.CELESTIAL_DRAGON);
        }
        if (formattedGroupName.equalsIgnoreCase("mine_mine_no_mi_qateam")) {
          types.add(APIConfig.AccountType.TESTER);
        }
      } 
      return types;
    } 
    
    return null;
  }

  
  public static boolean isCelestialDragon(PlayerEntity player) {
    if (!PATREONS.containsKey(player.getUniqueID()))
      return false; 
    boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.CELESTIAL_DRAGON);
    return hasType;
  }

  
  public static boolean isSupernova(PlayerEntity player) {
    if (!PATREONS.containsKey(player.getUniqueID()))
      return false; 
    boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.SUPERNOVA);
    return (hasType || isCelestialDragon(player));
  }

  
  public static boolean isTester(PlayerEntity player) {
    if (!PATREONS.containsKey(player.getUniqueID()))
      return false; 
    boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.TESTER);
    return hasType;
  }

  
  public static boolean isRookie(PlayerEntity player) {
    if (!PATREONS.containsKey(player.getUniqueID()))
      return false; 
    boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.ROOKIE);
    return (hasType || isSupernova(player) || isCelestialDragon(player));
  }

  
  public static boolean hasPatreonAccess(PlayerEntity player) {
    if (isDevBuild() && isCelestialDragon(player))
      return true; 
    if (isQABuild() && isTester(player))
      return true; 
    if (isEarlyAccessBuild() && isSupernova(player)) {
      return true;
    }
    return false;
  }



  
  public static class PatreonEvents
  {
    private static final Function<ServerPlayerEntity, TimerTask> TASK = player -> new TimerTask()
      {
        public void run() {
          StringTextComponent message = new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!");
          player.connection.disconnect((ITextComponent)message);
        }
      };


    
    @SubscribeEvent
    public void onEntityJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
      if (WyPatreon.isReleaseBuild() || (WyPatreon.isDevBuild() && WyDebug.isDebug())) {
        return;
      }
      ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
      
      List<APIConfig.AccountType> patreonLevels = null;
      
      try {
        patreonLevels = WyPatreon.getPatreonLevel((PlayerEntity)player);
      }
      catch (IOException e) {
        
        e.printStackTrace();
        (new Timer()).schedule(TASK.apply(player), 200L);
      } 
      
      if (patreonLevels != null) {
        WyPatreon.PATREONS.put(player.getUniqueID(), patreonLevels);
      }
      if (!WyPatreon.hasPatreonAccess((PlayerEntity)player))
      {
        (new Timer()).schedule(TASK.apply(player), 200L);
      }
    }
  }
}


