/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class DamagedPassiveAbility
/*    */   extends PassiveAbility
/*    */ {
/*    */   protected IOnDamaged onDamagedEvent = (player, attacker) -> false;
/*    */   
/*    */   public DamagedPassiveAbility(String name, APIConfig.AbilityCategory category) {
/* 20 */     super(name, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damage(LivingEntity entity, DamageSource source) {
/* 25 */     if (isPaused()) {
/* 26 */       return true;
/*    */     }
/* 28 */     return this.onDamagedEvent.onDamage(entity, source);
/*    */   }
/*    */   
/*    */   public static interface IOnDamaged extends Serializable {
/*    */     boolean onDamage(LivingEntity param1LivingEntity, DamageSource param1DamageSource);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\DamagedPassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */