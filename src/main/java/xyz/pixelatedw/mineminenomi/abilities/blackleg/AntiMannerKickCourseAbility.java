/*    */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class AntiMannerKickCourseAbility extends PunchAbility {
/* 12 */   public static final AntiMannerKickCourseAbility INSTANCE = new AntiMannerKickCourseAbility();
/*    */ 
/*    */   
/*    */   public AntiMannerKickCourseAbility() {
/* 16 */     super("Anti-Manner Kick Course", AbilityHelper.getStyleCategory());
/* 17 */     setMaxCooldown(12.0D);
/* 18 */     setDescription("Kicks an enemy and launches them vertically");
/*    */     
/* 20 */     this.onHitEntityEvent = this::onHitEntity;
/* 21 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 26 */     target.setPosition(target.getPosX(), target.getPosY() + 10.0D, target.getPosZ());
/* 27 */     target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 40, 0));
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 32 */     return 25.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\AntiMannerKickCourseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */