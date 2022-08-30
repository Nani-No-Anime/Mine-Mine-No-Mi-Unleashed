/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.MeigoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class MeigoAbility extends Ability {
/* 10 */   public static final Ability INSTANCE = new MeigoAbility();
/*    */ 
/*    */   
/*    */   public MeigoAbility() {
/* 14 */     super("Meigo", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(30.0D);
/* 16 */     setDescription("The user transforms their arm into magma and thrusts it at the opponent at incredible speeds");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     MeigoProjectile proj = new MeigoProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\MeigoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */