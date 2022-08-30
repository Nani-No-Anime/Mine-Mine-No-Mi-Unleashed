/*    */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class MoguraBananaAbility extends PunchAbility implements IFormRequiredAbility {
/* 15 */   public static final MoguraBananaAbility INSTANCE = new MoguraBananaAbility();
/*    */ 
/*    */   
/*    */   public MoguraBananaAbility() {
/* 19 */     super("Mogura Banana", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Hit the enemy with big mole claws to launch them away");
/* 21 */     setMaxCooldown(6.0D);
/*    */     
/* 23 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 24 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 29 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 30 */     target.setMotion(speed.x, player.getMotion().getY() + 0.1D, speed.z);
/* 31 */     target.velocityChanged = true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 36 */     return 12.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 42 */     return new ZoanInfo[] { (ZoanInfo)MoguHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguraBananaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */