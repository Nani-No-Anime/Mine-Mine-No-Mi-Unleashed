/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.OverheatProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class OverheatAbility extends Ability {
/* 10 */   public static final OverheatAbility INSTANCE = new OverheatAbility();
/*    */ 
/*    */   
/*    */   public OverheatAbility() {
/* 14 */     super("Overheat", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("The user shoots a rope made of heated strings at the opponent, exploding upon impact");
/* 16 */     setMaxCooldown(10.0D);
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     OverheatProjectile proj = new OverheatProjectile(player.world, (LivingEntity)player);
/* 24 */     proj.setPosition(proj.getPosX(), player.getPosY() + player.getEyeHeight() - 0.4000000059604645D, proj.getPosZ());
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.25F, 0.0F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\OverheatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */