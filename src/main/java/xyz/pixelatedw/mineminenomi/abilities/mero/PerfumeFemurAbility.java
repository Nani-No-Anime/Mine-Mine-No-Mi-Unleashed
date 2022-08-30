/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mero.PerfumeFemurParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class PerfumeFemurAbility extends PunchAbility {
/* 16 */   public static final PerfumeFemurAbility INSTANCE = new PerfumeFemurAbility();
/*    */   
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new PerfumeFemurParticleEffect();
/*    */ 
/*    */   
/*    */   public PerfumeFemurAbility() {
/* 22 */     super("Perfume Femur", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(14.0D);
/* 24 */     setDescription("Turns enemies hit by the user into stone");
/*    */     
/* 26 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 27 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 32 */     EffectInstance instance = new EffectInstance(ModEffects.LOVESTRUCK, 200, 1);
/* 33 */     target.addPotionEffect(instance);
/* 34 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */     
/* 36 */     PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 41 */     return 10.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\PerfumeFemurAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */