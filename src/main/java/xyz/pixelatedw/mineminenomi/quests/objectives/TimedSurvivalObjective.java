package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.ISurviveObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

public class TimedSurvivalObjective
  extends Objective
  implements ISurviveObjective {
  private int timeToSurvive;
  private float initialHP;
  
  public TimedSurvivalObjective(String title, int seconds) {
    super(title);
    this.timeToSurvive = seconds;
    setMaxProgress(this.timeToSurvive);
    
    this.onStartEvent = this::onStartEvent;
  }

  
  private boolean onStartEvent(PlayerEntity player) {
    this.initialHP = player.getHealth();
    return true;
  }


  
  public boolean checkTime(PlayerEntity player) {
    if (player.getHealth() > this.initialHP) {
      this.initialHP = player.getHealth();
    }
    if (player.getHealth() < this.initialHP) {
      
      setProgress(0.0D);
      this.initialHP = player.getHealth();
      return false;
    } 
    
    return true;
  }
}


