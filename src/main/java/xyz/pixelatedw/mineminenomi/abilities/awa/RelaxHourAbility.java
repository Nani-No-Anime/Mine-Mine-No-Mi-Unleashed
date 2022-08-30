/*    */ package xyz.pixelatedw.mineminenomi.abilities.awa;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.awa.RelaxHourProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */
/*    */ public class RelaxHourAbility extends RepeaterAbility {
/* 10 */   public static final RelaxHourAbility INSTANCE = new RelaxHourAbility();
/*    */ 
/*    */   
/*    */   public RelaxHourAbility() {
/* 14 */     super("Relax Hour", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Fires a barrage of cleaning bubbles, leaving their targets weakened and immobile");
/* 16 */     setMaxCooldown(4.0D);
/* 17 */     setMaxRepeaterCount(3, 2);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     RelaxHourProjectile proj = new RelaxHourProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.0F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\awa\RelaxHourAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */