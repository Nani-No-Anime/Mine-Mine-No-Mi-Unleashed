/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteStrikeParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class WhiteStrikeAbility
/*    */   extends Ability {
/* 17 */   public static final Ability INSTANCE = new WhiteStrikeAbility();
/*    */   
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new WhiteStrikeParticleEffect();
/*    */ 
/*    */   
/*    */   public WhiteStrikeAbility() {
/* 23 */     super("White Strike", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(20.0D);
/* 25 */     setDescription("Surrounds the nearby area with smoke, slowing down nearby entities");
/*    */     
/* 27 */     this.onUseEvent = this::onUseEvent;
/* 28 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 33 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { LivingEntity.class });
/* 34 */     targets.remove(player);
/*    */     
/* 36 */     targets.forEach(entity -> entity.addPotionEffect(new EffectInstance(ModEffects.SMOKE, 800, 0, false, false)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldownEvent(PlayerEntity player, int cooldown) {
/* 46 */     if (cooldown > WyHelper.percentage(80.0D, this.maxCooldown))
/* 47 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteStrikeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */