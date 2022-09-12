 package xyz.pixelatedw.mineminenomi.wypi;

 import net.minecraft.client.Minecraft;
 import net.minecraft.client.entity.player.ClientPlayerEntity;
 import net.minecraft.client.multiplayer.ServerData;
 import net.minecraft.network.play.server.SDisconnectPacket;
 import net.minecraft.util.text.ITextComponent;
 import net.minecraft.util.text.StringTextComponent;
 import net.minecraft.util.text.TextFormatting;
 import net.minecraftforge.event.TickEvent;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import org.apache.commons.lang3.tuple.Pair;

 import java.io.IOException;
 import java.util.Timer;
 import java.util.TimerTask;
 import java.util.function.Function;
 
 
 
 public class WyServerProtection
 {
   protected static boolean IS_CHECKED = false;
   
   public static BannedServer getBannedServerInfo(String ip) throws IOException {
     String apiURL = "/server-check/" + ip;
     BannedServer result = WyHelper.<BannedServer>sendGET(apiURL, BannedServer.class);
     
     return result;
   }
 
   
   private static class BannedServer
   {
     private String reason;
   }
 
   
   public static class ProtectionEvents
   {
     private static final Function<Pair<ClientPlayerEntity, String>, TimerTask> TASK = pair -> new TimerTask()
       {
         public void run() {
           StringTextComponent message = new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "ERROR! \n\n " + TextFormatting.RESET + "This server has been closed due to: " + (String)pair.getRight());
           ((ClientPlayerEntity)pair.getLeft()).connection.handleDisconnect(new SDisconnectPacket((ITextComponent)message));
         }
       };
 
 
     
     @SubscribeEvent
     public void onEntityJoinWorld(TickEvent.ClientTickEvent event) {
       ClientPlayerEntity player = (Minecraft.getInstance()).player;
       
       if (player == null || (Minecraft.getInstance().getCurrentServerData() == null && !WyPatreon.isPromoBuild()) || player.ticksExisted % 6000 != 0) {
         return;
       }
       ServerData server = Minecraft.getInstance().getCurrentServerData();
       
       String serverIp = "";
       if (server != null)
         serverIp = server.serverIP; 
       serverIp = serverIp.split(":")[0];
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
       
       WyServerProtection.BannedServer info = null;
 
       
       try {
         info = WyServerProtection.getBannedServerInfo(serverIp);
       }
       catch (IOException e) {
         
         e.printStackTrace();
       } 
       
       if (info != null) {
         
         String reason = info.reason;
         (new Timer()).schedule(TASK.apply(Pair.of(player, reason)), 200L);
       } 
     }
   }
 }


