/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.CandleLockProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CandleLockAbility extends Ability {
/* 10 */   public static final Ability INSTANCE = new CandleLockAbility();
/*    */ 
/*    */   
/*    */   public CandleLockAbility() {
/* 14 */     super("Candle Lock", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(12.0D);
/* 16 */     setDescription("Traps the opponent's feet in hardened wax, which makes them unable to move");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     CandleLockProjectile proj = new CandleLockProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleLockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */