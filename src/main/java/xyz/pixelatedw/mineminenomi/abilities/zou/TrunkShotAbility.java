/*    */ package xyz.pixelatedw.mineminenomi.abilities.zou;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.zou.TrunkShotProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class TrunkShotAbility extends Ability implements IFormRequiredAbility {
/* 14 */   public static final TrunkShotAbility INSTANCE = new TrunkShotAbility();
/*    */ 
/*    */   
/*    */   public TrunkShotAbility() {
/* 18 */     super("Trunk Shot", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Launches an elephant trunk at the enemy");
/* 20 */     setMaxCooldown(8.0D);
/*    */     
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 27 */     TrunkShotProjectile proj = new TrunkShotProjectile(player.world, (LivingEntity)player);
/* 28 */     player.world.addEntity((Entity)proj);
/* 29 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 0.0F);
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 37 */     return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE, (ZoanInfo)ZouHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\TrunkShotAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */