/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class IceBallAbility extends Ability {
/* 10 */   public static final Ability INSTANCE = new IceBallAbility();
/*    */ 
/*    */   
/*    */   public IceBallAbility() {
/* 14 */     super("Ice Ball", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(10.0D);
/* 16 */     setDescription("Creates a sphere of ice where the projectile hits");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     IceBallProjectile proj = new IceBallProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */