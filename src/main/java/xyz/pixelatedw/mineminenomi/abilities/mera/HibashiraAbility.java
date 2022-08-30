/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
import java.util.Objects;

/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.HibashiraParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class HibashiraAbility extends ContinuousAbility implements IMultiTargetAbility {
/* 19 */   public static final HibashiraAbility INSTANCE = new HibashiraAbility();
/*    */   
/*    */   private static final double PILLAR_SIZE = 3.5D;
/* 22 */   private static final HibashiraParticleEffect PARTICLES = new HibashiraParticleEffect();
/*    */ 
/*    */   
/*    */   public HibashiraAbility() {
/* 26 */     super("Hibashira", AbilityHelper.getDevilFruitCategory());
/* 27 */     setThreshold(5.0D);
/* 28 */     setDescription("Creates a fire pillar extending both upwards and downwards, burning every enemy within it");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 32 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 37 */     clearTargets();
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int continuousTime) {
/* 43 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 44 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class);
/* 45 */     for (int i = -10; i <= 10; i++) {
/*    */       
/* 47 */       Objects.requireNonNull(list); WyHelper.getEntitiesNear(player.getPosition().up(i), player.world, 3.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class ).stream().filter(entity -> !list.contains(entity)).forEach(list::add);
/* 48 */     }  list.remove(player);
/*    */     
/* 50 */     list.forEach(entity -> {
/*    */           boolean causedDamage = entity.attackEntityFrom((DamageSource)ModDamageSource.FIRE.causeEntityDamageFromSource((Entity)player), 5.0F);
/*    */ 
/*    */           
/*    */           if (causedDamage && isTarget(entity)) {
/*    */             entity.setFire(4);
/*    */           }
/*    */         });
/*    */     
/* 59 */     if (continuousTime % 20 == 0) {
/* 60 */       clearTargets();
/*    */     }
/* 62 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/* 63 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 68 */     setMaxCooldown((Math.round(this.continueTime * 1.2F) / 20.0F));
/* 69 */     clearTargets();
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HibashiraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */