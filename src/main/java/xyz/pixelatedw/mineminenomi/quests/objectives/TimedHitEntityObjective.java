package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;

public class TimedHitEntityObjective
  extends HitEntityObjective {
  private long lastHit = 0L;
  private int seconds = 0;

  
  public TimedHitEntityObjective(String title, int count, int seconds) {
    super(title, count, (HitEntityObjective.ICheckHit)null);
    this.seconds = seconds * 20;
  }

  
  public TimedHitEntityObjective(String title, int count, int seconds, HitEntityObjective.ICheckHit check) {
    super(title, count, check);
    this.seconds = seconds * 20;
  }


  
  public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount) {
    long hitTime = player.world.getGameTime();
    
    if (this.lastHit == 0L) {
      this.lastHit = player.world.getGameTime();
    }
    if (hitTime - this.seconds <= this.lastHit) {
      
      this.lastHit = hitTime;
      return super.checkHit(player, target, source, amount);
    } 

    
    setProgress(0.0D);
    this.lastHit = 0L;
    return false;
  }



  
  public String getLocalizedTitle() {
    String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
    return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()), Integer.valueOf(this.seconds / 20) })).getFormattedText();
  }
}


