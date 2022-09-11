package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.Comparator;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


public class MobsHelper
{
  public static final Comparator<LivingEntity> ENTITY_THREAT = new Comparator<LivingEntity>()
    {
      
      public int compare(LivingEntity e1, LivingEntity e2)
      {
        int e1Threat = MobsHelper.getEntityThreatLevel(e1);
        int e2Threat = MobsHelper.getEntityThreatLevel(e2);
        
        return e1Threat - e2Threat;
      }
    };

  
  public static int getEntityThreatLevel(LivingEntity entity) {
    if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
      
      IHakiData hakiProps = HakiDataCapability.get(entity);
      IEntityStats statsProps = EntityStatsCapability.get(entity);
      IDevilFruit dfProps = DevilFruitCapability.get(entity);
      IAbilityData abilityProps = AbilityDataCapability.get(entity);
      
      int threat = (int)((statsProps.getDoriki() / 2000) + hakiProps.getTotalHakiExp() / 60.0F);
      if (dfProps.hasDevilFruit()) {
        
        threat += 2;
        if (dfProps.isLogia()) {
          threat += 2;
        }
      } 
      if (abilityProps.hasUnlockedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
        
        threat += 2;
        if (abilityProps.hasEquippedAbility((Ability)HaoshokuHakiAbility.INSTANCE)) {
          threat += 3;
        }
      } 
      return threat;
    } 
    if (entity instanceof OPEntity)
    {
      return ((OPEntity)entity).getThreat();
    }
    
    return (int)entity.getMaxHealth();
  }
}


