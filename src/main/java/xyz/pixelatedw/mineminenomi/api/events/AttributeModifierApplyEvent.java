/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
/*    */ import net.minecraft.entity.ai.attributes.IAttribute;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ 
/*    */ public class AttributeModifierApplyEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private AbstractAttributeMap map;
/*    */   
/*    */   public AttributeModifierApplyEvent(LivingEntity entity, AbstractAttributeMap map, int amplifier) {
/* 15 */     super(entity);
/* 16 */     this.map = map;
/*    */   }
/*    */ 
/*    */   
/*    */   public IAttributeInstance getAttribute(IAttribute attr) {
/* 21 */     return this.map.getAttributeInstance(attr);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\AttributeModifierApplyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */