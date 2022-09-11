package xyz.pixelatedw.mineminenomi.api.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.event.entity.living.LivingEvent;

public class AttributeModifierRemoveEvent
  extends LivingEvent
{
  private AbstractAttributeMap map;
  
  public AttributeModifierRemoveEvent(LivingEntity entity, AbstractAttributeMap map, int amplifier) {
    super(entity);
    this.map = map;
  }

  
  public IAttributeInstance getAttribute(IAttribute attr) {
    return this.map.getAttributeInstance(attr);
  }
}


