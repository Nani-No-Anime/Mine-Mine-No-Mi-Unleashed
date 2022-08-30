/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GasRobeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class GasRobeAbility extends RepeaterAbility {
/* 10 */   public static final GasRobeAbility INSTANCE = new GasRobeAbility();
/*    */ 
/*    */   
/*    */   public GasRobeAbility() {
/* 14 */     super("Gas Robe", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Launches a cloud of poisonous gas at the opponent");
/* 16 */     setMaxCooldown(10.0D);
/* 17 */     setMaxRepeaterCount(6, 3);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     GasRobeProjectile proj = new GasRobeProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 2.0F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GasRobeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */