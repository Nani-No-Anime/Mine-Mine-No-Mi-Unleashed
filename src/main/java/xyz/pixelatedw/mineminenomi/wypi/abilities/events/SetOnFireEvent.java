/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ 
/*    */ @Cancelable
/*    */ public class SetOnFireEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private LivingEntity attacker;
/*    */   private int fireTime;
/*    */   
/*    */   public SetOnFireEvent(LivingEntity attacker, LivingEntity target, int fireTime) {
/* 15 */     super(target);
/* 16 */     this.attacker = attacker;
/* 17 */     this.fireTime = fireTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public LivingEntity getAttacker() {
/* 22 */     return this.attacker;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireTime() {
/* 27 */     return this.fireTime;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\events\SetOnFireEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */