/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class IceTimeCapsuleAbility extends PunchAbility {
/* 15 */   public static final Ability INSTANCE = (Ability)new IceTimeCapsuleAbility();
/*    */ 
/*    */   
/*    */   public IceTimeCapsuleAbility() {
/* 19 */     super("Ice Time Capsule", AbilityHelper.getDevilFruitCategory());
/* 20 */     setMaxCooldown(45.0D);
/* 21 */     setDescription("Hit the target to encase them in ice");
/*    */     
/* 23 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 24 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 29 */     EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 300, 0);
/* 30 */     target.addPotionEffect(instance);
/* 31 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 36 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceTimeCapsuleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */