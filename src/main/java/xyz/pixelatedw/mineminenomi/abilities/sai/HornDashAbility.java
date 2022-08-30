/*    */ package xyz.pixelatedw.mineminenomi.abilities.sai;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.SaiHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.SaiWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class HornDashAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
/* 23 */   public static final HornDashAbility INSTANCE = new HornDashAbility();
/*    */ 
/*    */   
/*    */   public HornDashAbility() {
/* 27 */     super("Horn Dash", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(9.0D);
/* 29 */     setDescription("The user dashes forward hurting the enemy and pushing them forward using their horn.");
/*    */     
/* 31 */     this.onUseEvent = this::onUseEvent;
/* 32 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 37 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 38 */       return false;
/*    */     }
/* 40 */     clearTargets();
/*    */     
/* 42 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 43 */     player.setMotion(speed.x, 0.2D, speed.z);
/* 44 */     player.velocityChanged = true;
/* 45 */     player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 52 */     if (canDealDamage()) {
/*    */       
/* 54 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.7D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 55 */       list.remove(player);
/*    */       
/* 57 */       list.forEach(entity -> {
/*    */             if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
/*    */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
/*    */               Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/*    */               entity.setMotion(speed.x, 0.5D, speed.z);
/*    */               entity.velocityChanged = true;
/*    */             } 
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 72 */     return (this.cooldown > getMaxCooldown() * 0.9D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 78 */     return new ZoanInfo[] { (ZoanInfo)SaiHeavyZoanInfo.INSTANCE, (ZoanInfo)SaiWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sai\HornDashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */