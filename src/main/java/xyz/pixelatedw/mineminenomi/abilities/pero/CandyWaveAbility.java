/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyWaveProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CandyWaveAbility extends Ability {
/* 10 */   public static final CandyWaveAbility INSTANCE = new CandyWaveAbility();
/*    */ 
/*    */   
/*    */   public CandyWaveAbility() {
/* 14 */     super("Candy Wave", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(12.0D);
/* 16 */     setDescription("Launches a wave of candy and traps enemies in hardened candy");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     CandyWaveProjectile proj = new CandyWaveProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyWaveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */