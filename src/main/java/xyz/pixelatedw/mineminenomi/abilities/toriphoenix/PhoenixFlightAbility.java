/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class PhoenixFlightAbility extends PassiveAbility implements IFormRequiredAbility {
/* 18 */   public static final PhoenixFlightAbility INSTANCE = new PhoenixFlightAbility();
/*    */ 
/*    */   
/*    */   public PhoenixFlightAbility() {
/* 22 */     super("Phoenix Flight", AbilityHelper.getDevilFruitCategory());
/* 23 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringPassiveEvent(PlayerEntity player) {
/* 28 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 30 */     if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
/*    */       return;
/*    */     }
/* 33 */     PhoenixFlyPointAbility flyPointAbility = (PhoenixFlyPointAbility)abilityProps.getEquippedAbility((Ability)PhoenixFlyPointAbility.INSTANCE);
/* 34 */     TenseiNoSoenAbility tenseiNoSoenAbility = (TenseiNoSoenAbility)abilityProps.getEquippedAbility((Ability)TenseiNoSoenAbility.INSTANCE);
/*    */     
/* 36 */     boolean isTenseiActive = (tenseiNoSoenAbility != null && tenseiNoSoenAbility.isCharging() && tenseiNoSoenAbility.getChargeTime() < 20);
/*    */     
/* 38 */     if (flyPointAbility == null || !flyPointAbility.isContinuous() || isTenseiActive) {
/*    */       return;
/*    */     }
/* 41 */     boolean isSprinting = player.isSprinting();
/* 42 */     float maxSpeed = isSprinting ? 2.0F : 1.25F;
/* 43 */     float acceleration = isSprinting ? 0.014F : 0.007F;
/* 44 */     if (player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT) != null) {
/* 45 */       maxSpeed /= (1 + Math.min(player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT).getAmplifier(), 3));
/*    */     }
/* 47 */     FujiazamiAbility fujizami = (FujiazamiAbility)abilityProps.getEquippedAbility((Ability)FujiazamiAbility.INSTANCE);
/*    */     
/* 49 */     acceleration *= (flyPointAbility.speed > 0.0F) ? (1.0F - flyPointAbility.speed / maxSpeed) : 1.0F;
/* 50 */     acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
/* 51 */     flyPointAbility.speed = MathHelper.clamp(flyPointAbility.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 5.0F) : 0.0F, maxSpeed);
/* 52 */     float speed = flyPointAbility.speed;
/*    */     
/* 54 */     if (fujizami != null && fujizami.isContinuous()) {
/* 55 */       flyPointAbility.speed = speed = 0.0F;
/*    */     }
/* 57 */     int d1 = player.onGround ? 1 : 0;
/* 58 */     int d2 = (player.moveForward > 0.0F) ? 1 : 0;
/* 59 */     int d3 = 0;
/* 60 */     int d4 = (maxSpeed / 3.0F >= speed || d2 == 0) ? 1 : 0;
/*    */     
/* 62 */     Vec3d vec = player.getLookVec();
/* 63 */     double x = vec.x * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
/* 64 */     double y = (d1 * 0.65F) + vec.y * speed * (1 - d1) * d2 + (d3 * -0.5F) + d4 * Math.cos((player.ticksExisted / 4.0F)) / 5.0D;
/* 65 */     double z = vec.z * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
/* 66 */     player.setMotion(x, y, z);
/*    */     
/* 68 */     if (player.getPosY() > (player.world.getMaxHeight() - 1)) {
/* 69 */       player.setMotion(0.0D, -3.0D, 0.0D);
/*    */     }
/* 71 */     player.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 77 */     return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */