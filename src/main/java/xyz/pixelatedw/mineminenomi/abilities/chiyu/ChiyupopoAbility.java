/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class ChiyupopoAbility
/*    */   extends Ability {
/* 18 */   public static final Ability INSTANCE = new ChiyupopoAbility();
/*    */   
/* 20 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();
/*    */ 
/*    */   
/*    */   public ChiyupopoAbility() {
/* 24 */     super("Chiyupopo", AbilityHelper.getDevilFruitCategory());
/* 25 */     setMaxCooldown(30.0D);
/* 26 */     setDescription("Releases dandelions made of tears that temporarily increase the healing rate of those around the user");
/*    */     
/* 28 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 33 */     player.heal(player.getMaxHealth() / 2.0F);
/*    */     
/* 35 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 36 */     targets.remove(player);
/*    */     
/* 38 */     for (LivingEntity entity : targets)
/*    */     {
/* 40 */       entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
/*    */     }
/*    */     
/* 43 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\ChiyupopoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */