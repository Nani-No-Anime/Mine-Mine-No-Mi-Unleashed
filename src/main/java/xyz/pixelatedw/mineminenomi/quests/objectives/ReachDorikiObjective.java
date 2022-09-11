package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IReachDorikiObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;

public class ReachDorikiObjective
  extends Objective implements IReachDorikiObjective {
  private int doriki;
  
  public ReachDorikiObjective(String title, int doriki) {
    super(title);
    setMaxProgress(1.0D);
    this.doriki = doriki;
  }


  
  public boolean checkDoriki(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    return (props.getDoriki() >= this.doriki);
  }


  
  public String getLocalizedTitle() {
    String objectiveKey = (new TranslationTextComponent(String.format("quest.objective." + APIConfig.projectId + ".%s", new Object[] { getId() }), new Object[0])).getKey();
    return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf(this.doriki) })).getFormattedText();
  }
}


