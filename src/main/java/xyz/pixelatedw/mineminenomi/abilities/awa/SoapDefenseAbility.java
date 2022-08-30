/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ public class SoapDefenseAbility extends ContinuousAbility implements IOnDamageAbility {
/* 20 */   public static final Ability INSTANCE = (Ability)new SoapDefenseAbility();
/*    */ 
/*    */   
/*    */   public SoapDefenseAbility() {
/* 24 */     super("Soap Defense", AbilityHelper.getDevilFruitCategory());
/* 25 */     setThreshold(7.0D);
/* 26 */     setDescription("Protect yourself using a giant concentrated soap shield");
/* 27 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/*    */     
/* 29 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 30 */     this.duringContinuityEvent = this::duringContinuity;
/* 31 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player);
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 42 */     if (!player.isInWater()) {
/* 43 */       player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 4, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 48 */     int cooldown = (int)Math.round(this.continueTime / 8.0D) + 3;
/* 49 */     setMaxCooldown(cooldown);
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
/* 56 */     if (source == ModDamageSource.LIGHTNING_BOLT) {
/* 57 */       return false;
/*    */     }
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\SoapDefenseAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */