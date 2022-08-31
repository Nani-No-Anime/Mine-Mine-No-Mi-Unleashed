/*    */ package xyz.pixelatedw.mineminenomi.abilities.mero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mero.PistolKissProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PistolKissAbility extends Ability {
/* 10 */   public static final PistolKissAbility INSTANCE = new PistolKissAbility();
/*    */ 
/*    */   
/*    */   public PistolKissAbility() {
/* 14 */     super("Pistol Kiss", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(5.0D);
/* 16 */     setDescription("A weaker but faster variant of 'Mero Mero Mellow'");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     PistolKissProjectile proj = new PistolKissProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 1.0F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mero\PistolKissAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */