/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DemonHydraProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.HydraProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class HydraAbility extends Ability {
/* 13 */   public static final HydraAbility INSTANCE = new HydraAbility();
/*    */ 
/*    */   
/*    */   public HydraAbility() {
/* 17 */     super("Hydra", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(5.0D);
/* 19 */     setDescription("Launches a dragon made out of liquid poison at the opponent");
/*    */     
/* 21 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 26 */     AbilityProjectileEntity proj = VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player) ? (AbilityProjectileEntity)new DemonHydraProjectile(player.world, (LivingEntity)player, false) : (AbilityProjectileEntity)new HydraProjectile(player.world, (LivingEntity)player, false);
/* 27 */     player.world.addEntity((Entity)proj);
/* 28 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableVenomDemoMode() {
/* 35 */     setDisplayName("Demon Hydra");
/* 36 */     setCustomTexture("hydra_venom");
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableVenomDemoMode() {
/* 41 */     setDisplayName("Hydra");
/* 42 */     setCustomTexture("");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\HydraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */