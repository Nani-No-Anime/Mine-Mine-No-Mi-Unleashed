/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.HealingTouchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class HealingTouchAbility extends PunchAbility {
/* 14 */   public static final HealingTouchAbility INSTANCE = new HealingTouchAbility();
/*    */   
/* 16 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new HealingTouchParticleEffect();
/*    */ 
/*    */   
/*    */   public HealingTouchAbility() {
/* 20 */     super("Healing Touch", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(10.0D);
/* 22 */     setDescription("Touch the target to heal them");
/*    */     
/* 24 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 29 */     target.heal(20.0F);
/* 30 */     target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
/*    */     
/* 32 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 34 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\HealingTouchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */