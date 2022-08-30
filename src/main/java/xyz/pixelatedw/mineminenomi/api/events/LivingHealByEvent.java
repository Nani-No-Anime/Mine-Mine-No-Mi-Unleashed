/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class LivingHealByEvent
/*    */   extends LivingHealEvent
/*    */ {
/*    */   private LivingEntity healer;
/*    */   
/*    */   public LivingHealByEvent(LivingEntity healer, LivingEntity entity, float amount) {
/* 14 */     super(entity, amount);
/* 15 */     this.healer = healer;
/*    */   }
/*    */ 
/*    */   
/*    */   public LivingEntity getHealer() {
/* 20 */     return this.healer;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\LivingHealByEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */