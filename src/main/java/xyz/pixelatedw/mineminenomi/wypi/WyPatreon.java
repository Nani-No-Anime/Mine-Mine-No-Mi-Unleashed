/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WyPatreon
/*     */ {
/*  26 */   public static final HashMap<UUID, List<APIConfig.AccountType>> PATREONS = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static boolean isDevBuild() {
/*  30 */     return (APIConfig.BUILD_MODE == APIConfig.BuildMode.DEV);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEarlyAccessBuild() {
/*  35 */     return (APIConfig.BUILD_MODE == APIConfig.BuildMode.EARLY_ACCESS);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isReleaseBuild() {
/*  40 */     return (APIConfig.BUILD_MODE == APIConfig.BuildMode.RELEASE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isQABuild() {
/*  45 */     return (APIConfig.BUILD_MODE == APIConfig.BuildMode.QA);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPromoBuild() {
/*  50 */     return (APIConfig.BUILD_MODE == APIConfig.BuildMode.PROMO);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static List<APIConfig.AccountType> getPatreonLevel(PlayerEntity player) throws IOException {
/*  56 */     String apiURL = "/patreon/" + player.getUniqueID().toString();
/*  57 */     String[] result = WyHelper.<String[]>sendGET(apiURL, String[].class);
/*     */     
/*  59 */     if (result != null && result.length > 0) {
/*     */       
/*  61 */       List<APIConfig.AccountType> types = new ArrayList<>();
/*  62 */       for (String group : result) {
/*     */         
/*  64 */         String formattedGroupName = WyHelper.getResourceName(group);
/*  65 */         if (formattedGroupName.equalsIgnoreCase("patreon_rookie")) {
/*  66 */           types.add(APIConfig.AccountType.ROOKIE);
/*     */         }
/*  68 */         if (formattedGroupName.equalsIgnoreCase("patreon_supernova")) {
/*  69 */           types.add(APIConfig.AccountType.SUPERNOVA);
/*     */         }
/*  71 */         if (formattedGroupName.equalsIgnoreCase("patreon_celestial_dragon")) {
/*  72 */           types.add(APIConfig.AccountType.CELESTIAL_DRAGON);
/*     */         }
/*  74 */         if (formattedGroupName.equalsIgnoreCase("mine_mine_no_mi_qateam")) {
/*  75 */           types.add(APIConfig.AccountType.TESTER);
/*     */         }
/*     */       } 
/*  78 */       return types;
/*     */     } 
/*     */     
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCelestialDragon(PlayerEntity player) {
/*  86 */     if (!PATREONS.containsKey(player.getUniqueID()))
/*  87 */       return false; 
/*  88 */     boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.CELESTIAL_DRAGON);
/*  89 */     return hasType;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSupernova(PlayerEntity player) {
/*  94 */     if (!PATREONS.containsKey(player.getUniqueID()))
/*  95 */       return false; 
/*  96 */     boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.SUPERNOVA);
/*  97 */     return (hasType || isCelestialDragon(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTester(PlayerEntity player) {
/* 102 */     if (!PATREONS.containsKey(player.getUniqueID()))
/* 103 */       return false; 
/* 104 */     boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.TESTER);
/* 105 */     return hasType;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRookie(PlayerEntity player) {
/* 110 */     if (!PATREONS.containsKey(player.getUniqueID()))
/* 111 */       return false; 
/* 112 */     boolean hasType = ((List)PATREONS.get(player.getUniqueID())).contains(APIConfig.AccountType.ROOKIE);
/* 113 */     return (hasType || isSupernova(player) || isCelestialDragon(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasPatreonAccess(PlayerEntity player) {
/* 118 */     if (isDevBuild() && isCelestialDragon(player))
/* 119 */       return true; 
/* 120 */     if (isQABuild() && isTester(player))
/* 121 */       return true; 
/* 122 */     if (isEarlyAccessBuild() && isSupernova(player)) {
/* 123 */       return true;
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PatreonEvents
/*     */   {
/*     */     private static final Function<ServerPlayerEntity, TimerTask> TASK = player -> new TimerTask()
/*     */       {
/*     */         public void run() {
/* 136 */           StringTextComponent message = new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!");
/* 137 */           player.connection.disconnect((ITextComponent)message);
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onEntityJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
/* 145 */       if (WyPatreon.isReleaseBuild() || (WyPatreon.isDevBuild() && WyDebug.isDebug())) {
/*     */         return;
/*     */       }
/* 148 */       ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
/*     */       
/* 150 */       List<APIConfig.AccountType> patreonLevels = null;
/*     */       
/*     */       try {
/* 153 */         patreonLevels = WyPatreon.getPatreonLevel((PlayerEntity)player);
/*     */       }
/* 155 */       catch (IOException e) {
/*     */         
/* 157 */         e.printStackTrace();
/* 158 */         (new Timer()).schedule(TASK.apply(player), 200L);
/*     */       } 
/*     */       
/* 161 */       if (patreonLevels != null) {
/* 162 */         WyPatreon.PATREONS.put(player.getUniqueID(), patreonLevels);
/*     */       }
/* 164 */       if (!WyPatreon.hasPatreonAccess((PlayerEntity)player))
/*     */       {
/* 166 */         (new Timer()).schedule(TASK.apply(player), 200L);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\WyPatreon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */