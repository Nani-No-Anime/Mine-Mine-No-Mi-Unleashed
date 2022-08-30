/*    */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrilliantPunkAbility extends Ability implements IMultiTargetAbility {
/* 21 */   public static final BrilliantPunkAbility INSTANCE = new BrilliantPunkAbility();
/*    */ 
/*    */   
/*    */   public BrilliantPunkAbility() {
/* 25 */     super("Brilliant Punk", AbilityHelper.getDevilFruitCategory());
/* 26 */     setMaxCooldown(10.0D);
/* 27 */     setDescription("The user rams into the target with their diamond body");
/*    */     
/* 29 */     this.onUseEvent = this::onUseEvent;
/* 30 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 36 */       return false;
/*    */     }
/* 38 */     clearTargets();
/*    */     
/* 40 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 41 */     DiamondBodyAbility ability = (DiamondBodyAbility)abilityProps.getEquippedAbility((Ability)DiamondBodyAbility.INSTANCE);
/* 42 */     boolean diamondBodyActive = (ability != null && ability.isContinuous());
/* 43 */     if (!diamondBodyActive) {
/*    */       
/* 45 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, new Object[] { getName(), DiamondBodyAbility.INSTANCE.getName() }));
/* 46 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 50 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 51 */     player.setMotion(speed.x, 0.2D, speed.z);
/* 52 */     player.velocityChanged = true;
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 60 */     if (canDealDamage()) {
/*    */       
/* 62 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 63 */       list.remove(player);
/*    */       
/* 65 */       list.forEach(entity -> {
/*    */             if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
/*    */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
/*    */             }
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 75 */     return (this.cooldown > getMaxCooldown() * 0.9D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\BrilliantPunkAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */