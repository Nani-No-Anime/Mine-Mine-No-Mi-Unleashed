/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.awa.GoldenHourParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class GoldenHourAbility
/*    */   extends ContinuousAbility {
/* 19 */   public static final GoldenHourAbility INSTANCE = new GoldenHourAbility();
/* 20 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GoldenHourParticleEffect();
/*    */ 
/*    */   
/*    */   public GoldenHourAbility() {
/* 24 */     super("Golden Hour", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(60.0D);
/* 26 */     setThreshold(10.0D);
/* 27 */     setDescription("Spreads bubbles on enemies around, leaving them weakened and immobile");
/*    */     
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int timer) {
/* 34 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
/* 35 */     targets.removeIf(Entity::isInWater);
/* 36 */     targets.remove(player);
/*    */     
/* 38 */     for (LivingEntity target : targets) {
/*    */       
/* 40 */       if (!target.isPotionActive(ModEffects.WASHED)) {
/* 41 */         target.addPotionEffect(new EffectInstance(ModEffects.WASHED, 600, 1, true, false, true));
/*    */       }
/* 43 */       PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\GoldenHourAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */