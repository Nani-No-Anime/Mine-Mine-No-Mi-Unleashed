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
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChallengeWinMessageThread
  implements Runnable {
  private Thread worker;
  private final AtomicBoolean running = new AtomicBoolean(false);
  
  private ServerPlayerEntity player;
  
  private List<String> rewards;
  
  public ChallengeWinMessageThread(ServerPlayerEntity player, List<String> rewards) {
    this.player = player;
    this.rewards = rewards;
  }

  
  public void start() {
    this.worker = new Thread(this, "Win Message Thread");
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
      StringTextComponent stringTextComponent = new StringTextComponent(TextFormatting.GOLD + "Challenge Completed!");
      ITextComponent titleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent, (Entity)this.player, 0);
      this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.TITLE, titleComponent));
      
      int i = 0;
      while (i < this.rewards.size())
      {
        if (WyHelper.isNullOrEmpty(this.rewards.get(i))) {
          
          i--;
          continue;
        } 
        StringTextComponent stringTextComponent1 = new StringTextComponent(TextFormatting.GOLD + "" + (String)this.rewards.get(i));
        ITextComponent subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
        this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
        Thread.sleep(1500L);
        if (i < this.rewards.size() - 1) {
          
          stringTextComponent1 = new StringTextComponent("");
          subtitleComponent = TextComponentUtils.updateForEntity(this.player.getCommandSource(), (ITextComponent)stringTextComponent1, (Entity)this.player, 0);
          this.player.connection.sendPacket((IPacket)new STitlePacket(STitlePacket.Type.SUBTITLE, subtitleComponent));
          Thread.sleep(300L);
        } 
        i++;
      }
    
    } catch (CommandSyntaxException|InterruptedException e) {
      
      e.printStackTrace();
    } 
    
    stop();
  }
}


