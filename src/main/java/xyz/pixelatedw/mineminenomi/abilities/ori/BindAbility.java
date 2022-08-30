/*    */ package xyz.pixelatedw.mineminenomi.abilities.ori;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class BindAbility extends PunchAbility {
/* 14 */   public static final BindAbility INSTANCE = new BindAbility();
/*    */ 
/*    */   
/*    */   public BindAbility() {
/* 18 */     super("Bind", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Punching ability that binds the target.");
/* 20 */     setMaxCooldown(5.0D);
/*    */     
/* 22 */     this.onHitEntityEvent = this::onHitEntity;
/* 23 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 28 */     EffectInstance instance = new EffectInstance(ModEffects.BIND, 200, 0, false, true);
/* 29 */     target.addPotionEffect(instance);
/* 30 */     if (player instanceof ServerPlayerEntity) {
/* 31 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */     }
/*    */   }
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 36 */     return 4.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ori\BindAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */