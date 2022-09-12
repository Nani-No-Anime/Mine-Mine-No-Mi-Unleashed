package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

import java.util.UUID;



@EventBusSubscriber(modid = "mineminenomi")
public class CyborgPassiveCommonEvents
{
  private static final AttributeModifier CYBORG_ARMOR = (new AttributeModifier(UUID.fromString("01344b52-e35e-44a3-9895-6fba1c10fc20"), "Cyborg Armor Addition", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AttributeModifier CYBORG_ARMOR_TOUGHNESS = (new AttributeModifier(UUID.fromString("f2443845-6f63-4916-b57e-a6805cfa47ae"), "Cyborg Armor Toughness Addition", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() != null) {
      
      LivingEntity entity = event.getEntityLiving();
      IEntityStats props = EntityStatsCapability.get(entity);
      
      if (props.isCyborg()) {
        
        if (!entity.getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(CYBORG_ARMOR)) {
          entity.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(CYBORG_ARMOR);
        }
        if (!entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).hasModifier(CYBORG_ARMOR_TOUGHNESS)) {
          entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier(CYBORG_ARMOR_TOUGHNESS);
        }
        if (entity instanceof PlayerEntity && AbilityHelper.isAffectedByWater(entity) && !((PlayerEntity)entity).abilities.isCreativeMode)
        {
          if (entity.isActualySwimming()) {
            entity.setMotion((entity.getMotion()).x / 1.07D, (entity.getMotion()).y / 1.07D, (entity.getMotion()).z / 1.07D);
          } else {
            entity.setMotion((entity.getMotion()).x / 1.05D, (entity.getMotion()).y / 1.05D, (entity.getMotion()).z / 1.05D);
          } 
        }
      } else {
        if (entity.getAttribute(SharedMonsterAttributes.ARMOR).hasModifier(CYBORG_ARMOR))
          entity.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(CYBORG_ARMOR); 
        if (entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).hasModifier(CYBORG_ARMOR_TOUGHNESS))
          entity.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier(CYBORG_ARMOR_TOUGHNESS); 
      } 
    } 
  }
}


