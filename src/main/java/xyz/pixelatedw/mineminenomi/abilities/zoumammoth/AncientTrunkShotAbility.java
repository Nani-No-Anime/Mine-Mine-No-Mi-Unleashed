/*    */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MammothHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class AncientTrunkShotAbility extends PunchAbility implements IFormRequiredAbility {
/* 15 */   public static final AncientTrunkShotAbility INSTANCE = new AncientTrunkShotAbility();
/*    */ 
/*    */   
/*    */   public AncientTrunkShotAbility() {
/* 19 */     super("Ancient Trunk Shot", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Hit using the massive trunk");
/* 21 */     setMaxCooldown(5.0D);
/*    */     
/* 23 */     this.onHitEntityEvent = this::onHitEntityEvent;
/* 24 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 29 */     Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize().mul(5.0D, 1.0D, 5.0D);
/* 30 */     target.setMotion(-dirVec.x, 0.25D, -dirVec.z);
/* 31 */     target.velocityChanged = true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 36 */     return 15.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 42 */     return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE, (ZoanInfo)MammothHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientTrunkShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */