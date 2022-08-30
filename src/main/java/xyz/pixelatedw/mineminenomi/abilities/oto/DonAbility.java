/*    */ package xyz.pixelatedw.mineminenomi.abilities.oto;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.oto.DonAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class DonAbility extends Ability implements IAnimatedAbility {
/* 24 */   public static final Ability INSTANCE = new DonAbility();
/*    */   
/*    */   private static final float VOLUME = 1.5F;
/*    */   
/*    */   private static final double DISTANCE = 22.5D;
/*    */   
/*    */   public DonAbility() {
/* 31 */     super("Don", AbilityHelper.getDevilFruitCategory());
/* 32 */     setMaxCooldown(3.0D);
/* 33 */     addInPool(new AbilityPool[] { AbilityPool.OTO_ABILITY });
/* 34 */     setDescription("The user plays the drum, creating a explosion inside all who hear it");
/*    */     
/* 36 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 41 */     player.world.playSound(null, player.getPosition(), ModSounds.DON, SoundCategory.PLAYERS, 1.5F, 0.2F + player.getRNG().nextFloat());
/*    */     
/* 43 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 22.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 44 */     targets.remove(player);
/*    */     
/* 46 */     getAnimation().start();
/*    */     
/* 48 */     for (LivingEntity target : targets) {
/*    */       
/* 50 */       AbilityDamageSource ds = ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
/* 51 */       ds.setInternalDamage();
/* 52 */       target.attackEntityFrom((DamageSource)ds, 20.0F);
/*    */       
/* 54 */       WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)player.world, target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
/*    */     } 
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 63 */     return (TimeAnimation)DonAnimation.INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 69 */     return (isOnCooldown() && !isStateForced() && getCooldown() > WyHelper.percentage(50.0D, getMaxCooldown()));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\oto\DonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */