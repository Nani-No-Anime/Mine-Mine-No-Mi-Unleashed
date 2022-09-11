package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class HeartRegenAbility extends PassiveAbility {
  public static final HeartRegenAbility INSTANCE = new HeartRegenAbility();
  
  private int ticksWithoutHeart = 0;

  
  public HeartRegenAbility() {
    super("Heart Regen", AbilityHelper.getDevilFruitCategory());
    
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  private void duringPassiveEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    if (!props.hasHeart()) {
      
      this.ticksWithoutHeart++;
      if (this.ticksWithoutHeart == 100) {
        
        props.setHeart(true);
        this.ticksWithoutHeart = 0;
      } 
    } 
  }
}


