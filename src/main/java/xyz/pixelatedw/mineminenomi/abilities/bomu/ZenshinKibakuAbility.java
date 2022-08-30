/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class ZenshinKibakuAbility extends ChargeableAbility {
/* 11 */   public static final ZenshinKibakuAbility INSTANCE = new ZenshinKibakuAbility();
/* 12 */   private int power = 0;
/*    */ 
/*    */   
/*    */   public ZenshinKibakuAbility() {
/* 16 */     super("Zenshin Kibaku", AbilityHelper.getDevilFruitCategory());
/* 17 */     setMaxCooldown(30.0D);
/* 18 */     setDescription("The user creates a massive explosion from their body");
/* 19 */     setMaxChargeTime(5.0D);
/* 20 */     setCancelable();
/* 21 */     this.duringChargingEvent = this::duringChargingEvent;
/* 22 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int timer) {
/* 27 */     this.power = timer;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 32 */     float power = (getMaxChargeTime() - this.power) / 20.0F * 2.0F;
/* 33 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, player.getPosX(), player.getPosY(), player.getPosZ(), power);
/* 34 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)power));
/* 35 */     explosion.setStaticDamage(power * 12.0F);
/* 36 */     explosion.doExplosion();
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\ZenshinKibakuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */