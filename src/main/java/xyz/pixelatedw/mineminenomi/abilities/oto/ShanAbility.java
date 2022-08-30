/*    */ package xyz.pixelatedw.mineminenomi.abilities.oto;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;

/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.oto.ShanAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class ShanAbility extends Ability implements IAnimatedAbility {
/* 26 */   public static final Ability INSTANCE = new ShanAbility();
/*    */   
/*    */   private static final float VOLUME = 1.5F;
/*    */   
/*    */   private static final double DISTANCE = 22.5D;
/*    */   
/*    */   public ShanAbility() {
/* 33 */     super("Shan", AbilityHelper.getDevilFruitCategory());
/* 34 */     setMaxCooldown(3.0D);
/* 35 */     addInPool(new AbilityPool[] { AbilityPool.OTO_ABILITY });
/* 36 */     setDescription("The user turns their head into a cymbal, by pushing their own head they create a sound shockwave powerful enough to internally cut any entity hearing it");
/*    */     
/* 38 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 43 */     player.world.playSound(null, player.getPosition(), ModSounds.SHAN, SoundCategory.PLAYERS, 1.5F, 0.2F + player.getRNG().nextFloat());
/*    */     
/* 45 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 22.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 46 */     targets.remove(player);
/*    */     
/* 48 */     getAnimation().start();
/*    */     
/* 50 */     for (LivingEntity target : targets) {
/*    */       
/* 52 */       AbilityDamageSource ds = ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
/* 53 */       ds.setInternalDamage();
/* 54 */       ds.markDamageAsSlash();
/* 55 */       target.attackEntityFrom((DamageSource)ds, 15.0F);
/*    */       
/* 57 */       target.addPotionEffect(new EffectInstance(ModEffects.BLEEDING, 100, 0));
/*    */       
/* 59 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 61 */         double offsetX = target.getRNG().nextDouble() / 2.0D;
/* 62 */         double offsetZ = target.getRNG().nextDouble() / 2.0D;
/* 63 */         WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, target.getPosX() + offsetX, target.getPosY() + target.getEyeHeight() + offsetX, target.getPosZ() + offsetZ);
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 73 */     return (TimeAnimation)ShanAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 79 */     return (isOnCooldown() && !isStateForced() && getCooldown() > WyHelper.percentage(70.0D, getMaxCooldown()));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\oto\ShanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */