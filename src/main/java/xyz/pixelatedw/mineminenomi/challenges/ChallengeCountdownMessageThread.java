package xyz.pixelatedw.mineminenomi.challenges;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;

import java.util.concurrent.atomic.AtomicBoolean;

public class ChallengeCountdownMessageThread
  implements Runnable {
  private Thread worker;
  private final AtomicBoolean running = new AtomicBoolean(false);
  
  private ServerPlayerEntity player;
  
  private String challengeName;
  
  public ChallengeCountdownMessageThread(ServerPlayerEntity player, String challengeName) {
    this.player = player;
    this.challengeName = challengeName;
  }

  
  public void start() {
    this.worker = new Thread(this, "Countdown Message Thread");
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
      StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + this.challengeName);
      ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
      this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
      
      int i = 3;
      while (i >= 0)
      {
        String countdown = (i > 0) ? (i + "") : "FIGHT";
        StringTextComponent stringTextComponent1 = new StringTextComponent(TextFormatting.GOLD + "" + countdown);
        ITextComponent subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
        this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
        i--;
        Thread.sleep(1200L);
      }
    
    } catch (CommandSyntaxException|InterruptedException e) {
      
      e.printStackTrace();
    } 
    
    stop();
  }
}


