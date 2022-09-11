package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.event.entity.living.LivingEvent;

public class AttributeModifierApplyEvent
  extends LivingEvent
{
  private AbstractAttributeMap map;
  
  public AttributeModifierApplyEvent(LivingEntity entity, AbstractAttributeMap map, int amplifier) {
    super(entity);
    this.map = map;
  }

  
  public IAttributeInstance getAttribute(IAttribute attr) {
    return this.map.getAttributeInstance(attr);
  }
}


