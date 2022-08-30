/*    */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class DopingAbility extends PunchAbility {
/* 12 */   public static final DopingAbility INSTANCE = new DopingAbility();
/*    */ 
/*    */   
/*    */   public DopingAbility() {
/* 16 */     super("Doping", AbilityHelper.getStyleCategory());
/* 17 */     setDescription("The user injects a target with special medicine providing boosting their physical powers\n\n§2SHIFT-USE§r: The user injects themselves");
/* 18 */     setMaxCooldown(25.0D);
/*    */     
/* 20 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 21 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 26 */     if (player.isSneaking()) {
/*    */       
/* 28 */       player.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
/* 29 */       endContinuity(player);
/* 30 */       return false;
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 38 */     target.addPotionEffect(new EffectInstance(ModEffects.TENSION_HORMONE, 200, 1));
/* 39 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\DopingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */