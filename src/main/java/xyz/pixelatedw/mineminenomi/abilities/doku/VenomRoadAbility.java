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
/*    */ public class VenomRoadAbility extends Ability {
/* 13 */   public static final VenomRoadAbility INSTANCE = new VenomRoadAbility();
/*    */ 
/*    */   
/*    */   public VenomRoadAbility() {
/* 17 */     super("Venom Road", AbilityHelper.getDevilFruitCategory());
/* 18 */     setMaxCooldown(8.0D);
/* 19 */     setDescription("The user fires a Hydra at the target location and transports there through its path");
/*    */     
/* 21 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 26 */     AbilityProjectileEntity proj = VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player) ? (AbilityProjectileEntity)new DemonHydraProjectile(player.world, (LivingEntity)player, true) : (AbilityProjectileEntity)new HydraProjectile(player.world, (LivingEntity)player, true);
/* 27 */     player.world.addEntity((Entity)proj);
/* 28 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableVenomDemoMode() {
/* 35 */     setCustomTexture("venom_road_venom");
/* 36 */     setDisplayName("Demon Road");
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableVenomDemoMode() {
/* 41 */     setCustomTexture("");
/* 42 */     setDisplayName("Venom Road");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\VenomRoadAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */