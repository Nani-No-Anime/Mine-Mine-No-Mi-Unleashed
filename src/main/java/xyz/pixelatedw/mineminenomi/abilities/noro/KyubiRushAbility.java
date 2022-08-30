/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KyubiRushAbility extends PunchAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new KyubiRushAbility();
/*    */ 
/*    */   
/*    */   public KyubiRushAbility() {
/* 17 */     super("Kyubi Rush", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(5.0D);
/* 19 */     setDescription("While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once (a stronger slowness effect causes more damage)");
/*    */     
/* 21 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 26 */     float damageFromSlowness = 0.0F;
/*    */     
/* 28 */     if (target.isPotionActive(ModEffects.NORO_SLOWNESS)) {
/*    */       
/* 30 */       damageFromSlowness = (float)(Math.sqrt(target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration()) / 1.5D);
/* 31 */       int newTime = target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration() / 2;
/* 32 */       int newAmplifier = Math.min(target.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getAmplifier() - 2, 0);
/* 33 */       target.removePotionEffect(ModEffects.NORO_SLOWNESS);
/* 34 */       target.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, newTime, newAmplifier));
/*    */     } else {
/*    */       
/* 37 */       damageFromSlowness = 1.0F;
/*    */     } 
/* 39 */     return damageFromSlowness;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\KyubiRushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */