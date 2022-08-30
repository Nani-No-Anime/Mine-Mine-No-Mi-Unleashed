/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AncientTailSpinAbility extends Ability implements IFormRequiredAbility, IAnimatedAbility {
/* 23 */   public static final AncientTailSpinAbility INSTANCE = new AncientTailSpinAbility();
/* 24 */   private static final BodyRotateAnimation ANIMATION = new BodyRotateAnimation(12.0F);
/*    */ 
/*    */   
/*    */   public AncientTailSpinAbility() {
/* 28 */     super("Ancient Tail Spin", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("");
/* 30 */     setMaxCooldown(10.0D);
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 39 */     list.remove(player);
/*    */     
/* 41 */     getAnimation().start();
/*    */     
/* 43 */     list.forEach(entity -> {
/*    */           entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this), 15.0F);
/*    */           
/*    */           Vec3d dist = entity.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
/*    */           
/*    */           double speedReduction = 2.0D;
/*    */           double xSpeed = -dist.x / speedReduction;
/*    */           double zSpeed = -dist.z / speedReduction;
/*    */           entity.setMotion(-xSpeed, 0.10000000149011612D, -zSpeed);
/*    */           entity.velocityChanged = true;
/*    */         });
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 60 */     return new ZoanInfo[] { (ZoanInfo)AllosaurusHeavyZoanInfo.INSTANCE, (ZoanInfo)AllosaurusWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TimeAnimation getAnimation() {
/* 66 */     return (TimeAnimation)ANIMATION;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimationActive() {
/* 72 */     return (isOnCooldown() && getCooldown() > getMaxCooldown() - 10.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryuallosaurus\AncientTailSpinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */