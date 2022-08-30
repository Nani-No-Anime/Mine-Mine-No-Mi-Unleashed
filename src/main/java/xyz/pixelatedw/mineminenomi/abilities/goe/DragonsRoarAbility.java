/*    */ package xyz.pixelatedw.mineminenomi.abilities.goe;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goe.DragonsRoarProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class DragonsRoarAbility extends RepeaterAbility {
/* 10 */   public static final DragonsRoarAbility INSTANCE = new DragonsRoarAbility();
/*    */ 
/*    */   
/*    */   public DragonsRoarAbility() {
/* 14 */     super("Dragon's Roar", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("The user shouts and creates a series of powerful sound shockwaves");
/* 16 */     setMaxCooldown(8.0D);
/* 17 */     setMaxRepeaterCount(10, 6);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     float size = (Math.abs(getMaxRepeaterCount() - getRepeaterCount() - 10) * 2);
/* 25 */     DragonsRoarProjectile proj = new DragonsRoarProjectile(player.world, (LivingEntity)player, size);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goe\DragonsRoarAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */