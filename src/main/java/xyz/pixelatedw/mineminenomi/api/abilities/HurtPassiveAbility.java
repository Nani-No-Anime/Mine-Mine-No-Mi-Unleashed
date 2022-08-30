/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HurtPassiveAbility
/*    */   extends PassiveAbility
/*    */ {
/*    */   protected IOnHurt onHurtEvent = (player, attacker) -> true;
/*    */   private float amount;
/*    */   
/*    */   public HurtPassiveAbility(String name, APIConfig.AbilityCategory category) {
/* 19 */     super(name, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hurt(LivingEntity entity, Entity source, float amount) {
/* 24 */     this.amount = amount;
/*    */     
/* 26 */     if (isPaused()) {
/* 27 */       return true;
/*    */     }
/* 29 */     return this.onHurtEvent.onHurt(entity, source);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAmount() {
/* 34 */     return this.amount;
/*    */   }
/*    */   
/*    */   public static interface IOnHurt extends Serializable {
/*    */     boolean onHurt(LivingEntity param1LivingEntity, Entity param1Entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\HurtPassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */