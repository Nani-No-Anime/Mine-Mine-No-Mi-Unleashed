/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierCrashProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BarrierCrashAbility extends Ability {
/* 10 */   public static final BarrierCrashAbility INSTANCE = new BarrierCrashAbility();
/*    */ 
/*    */   
/*    */   public BarrierCrashAbility() {
/* 14 */     super("Barrier Crash", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Launches a barrier towards the opponent, smashing it against them");
/*    */     
/* 17 */     setMaxCooldown(8.0D);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     BarrierCrashProjectile proj = new BarrierCrashProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierCrashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */