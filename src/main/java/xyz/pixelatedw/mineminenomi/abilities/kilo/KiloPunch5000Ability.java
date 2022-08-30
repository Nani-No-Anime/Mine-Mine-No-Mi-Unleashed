/*    */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KiloPunch5000Ability extends PunchAbility {
/* 12 */   public static final KiloPunch5000Ability INSTANCE = new KiloPunch5000Ability();
/*    */ 
/*    */   
/*    */   public KiloPunch5000Ability() {
/* 16 */     super("5000 Kilo Punch", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("Delivers a 5000 kilo punch, the user is slowed down due to the extra kilos");
/* 18 */     setMaxCooldown(7.0D);
/*    */     
/* 20 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 21 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 26 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 5, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 31 */     return 20.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPunch5000Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */