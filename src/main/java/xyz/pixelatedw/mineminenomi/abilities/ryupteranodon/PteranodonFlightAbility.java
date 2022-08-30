/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class PteranodonFlightAbility extends PassiveAbility implements IFormRequiredAbility {
/* 17 */   public static final PteranodonFlightAbility INSTANCE = new PteranodonFlightAbility();
/*    */ 
/*    */   
/*    */   public PteranodonFlightAbility() {
/* 21 */     super("Pteranodon Flight", AbilityHelper.getDevilFruitCategory());
/* 22 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringPassiveEvent(PlayerEntity player) {
/* 27 */     IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 29 */     if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
/*    */       return;
/*    */     }
/* 32 */     PteranodonFlyPointAbility ability = (PteranodonFlyPointAbility)AbilityProps.getEquippedAbility((Ability)PteranodonFlyPointAbility.INSTANCE);
/*    */     
/* 34 */     if (ability == null || !ability.isContinuous()) {
/*    */       return;
/*    */     }
/* 37 */     boolean isSprinting = player.isSprinting();
/*    */     
/* 39 */     float maxSpeed = isSprinting ? 2.1F : 1.5F;
/* 40 */     float acceleration = isSprinting ? 0.012F : 0.006F;
/*    */     
/* 42 */     TempuraudonAbility tempura = (TempuraudonAbility)AbilityProps.getEquippedAbility((Ability)TempuraudonAbility.INSTANCE);
/* 43 */     TankyudonAbility tankyudonAbility = (TankyudonAbility)AbilityProps.getEquippedAbility((Ability)TankyudonAbility.INSTANCE);
/*    */     
/* 45 */     acceleration *= (ability.speed > 0.0F) ? (1.0F - ability.speed / maxSpeed) : 1.0F;
/* 46 */     acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
/* 47 */     ability.speed = MathHelper.clamp(ability.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 5.0F) : 0.0F, maxSpeed);
/* 48 */     float speed = ability.speed;
/*    */     
/* 50 */     if (tempura != null && tempura.isCharging()) {
/* 51 */       ability.speed = speed = 0.0F;
/* 52 */     } else if (tankyudonAbility != null && tankyudonAbility.canGrab()) {
/* 53 */       speed++;
/*    */     } 
/* 55 */     int d1 = player.onGround ? 1 : 0;
/* 56 */     int d2 = (player.moveForward > 0.0F || (tankyudonAbility != null && tankyudonAbility.canGrab())) ? 1 : 0;
/* 57 */     int d3 = 0;
/* 58 */     int d4 = (maxSpeed / 3.0F >= speed || d2 == 0) ? 1 : 0;
/*    */     
/* 60 */     Vec3d vec = player.getLookVec();
/* 61 */     double x = vec.x * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
/* 62 */     double y = (d1 * 0.65F) + vec.y * speed * (1 - d1) * d2 + (d3 * -0.5F) + d4 * Math.cos((player.ticksExisted / 4.0F)) / 5.0D;
/* 63 */     double z = vec.z * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
/* 64 */     player.setMotion(x, y, z);
/*    */     
/* 66 */     if (player.getPosY() > (player.world.getMaxHeight() - 1)) {
/* 67 */       player.setMotion(0.0D, -3.0D, 0.0D);
/*    */     }
/* 69 */     player.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 75 */     return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */