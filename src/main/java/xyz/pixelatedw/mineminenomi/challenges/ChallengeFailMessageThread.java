package xyz.pixelatedw.mineminenomi.challenges;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;

public class ChallengeFailMessageThread
  implements Runnable {
  private Thread worker;
  private final AtomicBoolean running = new AtomicBoolean(false);
  
  private ServerPlayerEntity player;

  
  public ChallengeFailMessageThread(ServerPlayerEntity player) {
    this.player = player;
  }

  
  public void start() {
    this.worker = new Thread(this, "Fail Message Thread");
    this.worker.setDaemon(true);
    this.worker.start();
  }

  
  public void stop() {
    this.running.set(false);
    Thread.currentThread().interrupt();
  }


  
  public void run() {
    this.running.set(true);

    
    try {
      StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + "Challenge Failed!");
      ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
      this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
    }
    catch (CommandSyntaxException e) {
      
      e.printStackTrace();
    } 
    
    stop();
  }
}


