/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.doku.DokuGumoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class DokuGumoAbility
/*    */   extends ContinuousAbility {
/* 18 */   public static final DokuGumoAbility INSTANCE = new DokuGumoAbility();
/*    */   
/* 20 */   private static final DokuGumoParticleEffect PARTICLES = new DokuGumoParticleEffect();
/*    */ 
/*    */   
/*    */   public DokuGumoAbility() {
/* 24 */     super("Doku Gumo", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(30.0D);
/* 26 */     setThreshold(20.0D);
/* 27 */     setDescription("Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside");
/*    */     
/* 29 */     this.duringContinuityEvent = this::duringContinuity;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int timer) {
/* 34 */     int power = 0;
/* 35 */     int duration = 100;
/* 36 */     int range = 8;
/* 37 */     boolean color = false;
/*    */     
/* 39 */     if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*    */       
/* 41 */       color = true;
/* 42 */       power += 2;
/* 43 */       duration *= 2;
/* 44 */       range = (int)(range * 1.5D);
/*    */     } 
/*    */     
/* 47 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 48 */     targets.remove(player);
/* 49 */     player.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1));
/*    */     
/* 51 */     for (LivingEntity enemy : targets) {
/*    */       
/* 53 */       if (!enemy.isPotionActive(Effects.BLINDNESS))
/* 54 */         enemy.addPotionEffect(new EffectInstance(Effects.BLINDNESS, duration, power)); 
/* 55 */       if (!enemy.isPotionActive(Effects.POISON))
/* 56 */         enemy.addPotionEffect(new EffectInstance(Effects.POISON, duration, power + 1)); 
/* 57 */       if (!enemy.isPotionActive(Effects.WEAKNESS)) {
/* 58 */         enemy.addPotionEffect(new EffectInstance(Effects.WEAKNESS, duration, power));
/*    */       }
/*    */     } 
/* 61 */     PARTICLES.venomDemon = color;
/* 62 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableVenomDemoMode() {
/* 67 */     setCustomTexture("doku_gumo_venom");
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableVenomDemoMode() {
/* 72 */     setCustomTexture("");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\DokuGumoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */