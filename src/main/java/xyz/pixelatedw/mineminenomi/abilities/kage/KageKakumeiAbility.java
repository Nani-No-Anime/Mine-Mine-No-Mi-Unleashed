/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KageKakumeiAbility extends PunchAbility {
/* 14 */   public static final Ability INSTANCE = (Ability)new KageKakumeiAbility();
/*    */   
/*    */   private static final int SHADOWS_REQUIRED = 20;
/*    */ 
/*    */   
/*    */   public KageKakumeiAbility() {
/* 20 */     super("Kage Kakumei", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("By hitting another entity the user impales §220§r shadows at once into them boosting their physical abilities");
/* 22 */     setMaxCooldown(20.0D);
/*    */     
/* 24 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 25 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 30 */     target.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 0));
/* 31 */     target.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 0));
/* 32 */     target.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 0));
/* 33 */     target.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 400, 0));
/* 34 */     target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0));
/* 35 */     KageHelper.removeShadows(player, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 40 */     if (KageHelper.getAvailableShadows(player) < 20) {
/*    */       
/* 42 */       player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/* 43 */       return -1.0F;
/*    */     } 
/*    */     
/* 46 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KageKakumeiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */