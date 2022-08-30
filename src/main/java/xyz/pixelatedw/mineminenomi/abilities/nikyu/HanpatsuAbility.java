/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class HanpatsuAbility extends PunchAbility {
/* 12 */   public static final Ability INSTANCE = (Ability)new HanpatsuAbility();
/*    */ 
/*    */   
/*    */   public HanpatsuAbility() {
/* 16 */     super("Hanpatsu", AbilityHelper.getDevilFruitCategory());
/* 17 */     setMaxCooldown(5.0D);
/* 18 */     setDescription("Anyone the user punches gets sent flying far into the air");
/*    */     
/* 20 */     this.onHitEntityEvent = this::onHitEntity;
/* 21 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 26 */     double xPower = WyHelper.randomWithRange(-20, 20);
/* 27 */     if (xPower >= 0.0D) { xPower += 30.0D; }
/* 28 */     else { xPower -= 30.0D; }
/*    */     
/* 30 */     double zPower = WyHelper.randomWithRange(-20, 20);
/* 31 */     if (zPower >= 0.0D) { zPower += 30.0D; }
/* 32 */     else { zPower -= 30.0D; }
/*    */     
/* 34 */     target.setPosition(player.getPosX(), player.getPosY() + 20.0D, player.getPosZ());
/* 35 */     target.setMotion(xPower, 2.5D, zPower);
/* 36 */     target.velocityChanged = true;
/* 37 */     target.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 42 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\HanpatsuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */