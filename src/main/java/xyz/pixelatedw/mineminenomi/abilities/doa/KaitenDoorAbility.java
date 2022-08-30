/*    */ package xyz.pixelatedw.mineminenomi.abilities.doa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class KaitenDoorAbility extends PunchAbility {
/* 15 */   public static final KaitenDoorAbility INSTANCE = new KaitenDoorAbility();
/*    */ 
/*    */   
/*    */   public KaitenDoorAbility() {
/* 19 */     super("Kaiten Door", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Turn the head of your opponent into a rotating door to confuse and disorient them");
/* 21 */     setMaxCooldown(8.0D);
/*    */     
/* 23 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 24 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 29 */     target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1, false, false));
/* 30 */     EffectInstance instance = new EffectInstance(ModEffects.DOOR_HEAD, 200, 0, false, false);
/* 31 */     target.addPotionEffect(instance);
/* 32 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 37 */     return 4.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doa\KaitenDoorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */