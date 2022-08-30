/*    */ package xyz.pixelatedw.mineminenomi.abilities.kobu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.kobu.ShoureiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class ShoureiAbility
/*    */   extends Ability {
/* 17 */   public static final ShoureiAbility INSTANCE = new ShoureiAbility();
/* 18 */   private static final ShoureiParticleEffect PARTICLES = new ShoureiParticleEffect();
/*    */ 
/*    */   
/*    */   public ShoureiAbility() {
/* 22 */     super("Shourei", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("Increases other people's fighting spirit and physical strength by simply encouraging them with words.");
/* 24 */     setMaxCooldown(60.0D);
/*    */     
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 31 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 32 */     targets.remove(player);
/*    */     
/* 34 */     for (LivingEntity target : targets) {
/*    */       
/* 36 */       target.addPotionEffect(new EffectInstance(Effects.STRENGTH, 400, 1, true, false, true));
/* 37 */       target.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 1, true, false, true));
/* 38 */       target.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 400, 1, true, false, true));
/* 39 */       target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 0, true, false, true));
/*    */       
/* 41 */       PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kobu\ShoureiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */