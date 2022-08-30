/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushibison;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class FiddleBanffAbility extends Ability implements IMultiTargetAbility, IFormRequiredAbility {
/* 23 */   public static final FiddleBanffAbility INSTANCE = new FiddleBanffAbility();
/*    */ 
/*    */   
/*    */   public FiddleBanffAbility() {
/* 27 */     super("Fiddle Banff", AbilityHelper.getDevilFruitCategory());
/* 28 */     setMaxCooldown(7.0D);
/* 29 */     setDescription("The transformed user dashes towards the opponent to crash against them with great power");
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
/* 42 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.0D, 5.0D);
/* 43 */     player.setMotion(speed.x, player.getMotion().getY(), speed.z);
/* 44 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 51 */     if (canDealDamage()) {
/*    */       
/* 53 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 54 */       list.remove(player);
/* 55 */       for (LivingEntity target : list) {
/*    */         
/* 57 */         if (isTarget(target)) {
/* 58 */           target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8.0F);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 68 */     return (this.cooldown > 100.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 74 */     return new ZoanInfo[] { (ZoanInfo)BisonHeavyZoanInfo.INSTANCE, (ZoanInfo)BisonWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushibison\FiddleBanffAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */