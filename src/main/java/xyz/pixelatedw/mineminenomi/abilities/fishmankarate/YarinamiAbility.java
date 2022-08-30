/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.YarinamiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class YarinamiAbility extends ChargeableAbility {
/* 10 */   public static final YarinamiAbility INSTANCE = new YarinamiAbility();
/*    */ 
/*    */   
/*    */   public YarinamiAbility() {
/* 14 */     super("Yarinami", AbilityHelper.getRacialCategory());
/* 15 */     setDescription("The user fires a densely compressed spear-shaped waterbullet at the opponent");
/* 16 */     setMaxCooldown(12.0D);
/* 17 */     setMaxChargeTime(3.0D);
/*    */     
/* 19 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 24 */     YarinamiProjectile proj = new YarinamiProjectile(player.world, (LivingEntity)player);
/* 25 */     if (player.canSwim())
/* 26 */       proj.setDamage(40.0F); 
/* 27 */     player.world.addEntity((Entity)proj);
/* 28 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\YarinamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */