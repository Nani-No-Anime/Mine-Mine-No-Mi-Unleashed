package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;

public class TimedKillEntityObjective
  extends KillEntityObjective {
  private long firstHit = 0L;
  private int ticks = 0;

  
  public TimedKillEntityObjective(String title, int count, int seconds) {
    super(title, count, (KillEntityObjective.ICheckKill)null);
    this.ticks = seconds * 20;
  }

  
  public TimedKillEntityObjective(String title, int count, int seconds, KillEntityObjective.ICheckKill check) {
    super(title, count, check);
    this.ticks = seconds * 20;
  }


  
  public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source) {
    long hitTime = player.world.getGameTime();
    
    if (hitTime > this.firstHit + this.ticks) {
      
      setProgress(0.0D);
      this.firstHit = 0L;
    } 
    
    if (this.firstHit == 0L) {
      this.firstHit = player.world.getGameTime();
    }
    if (hitTime - this.ticks <= this.firstHit) {
      return super.checkKill(player, target, source);
    }
    return false;
  }


  
  public String getLocalizedTitle() {
    String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
    return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()), Integer.valueOf(this.ticks / 20) })).getFormattedText();
  }
}


