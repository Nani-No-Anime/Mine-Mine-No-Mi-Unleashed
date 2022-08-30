/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl.PoisonSpitProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PoisonSpitAbility extends Ability implements IFormRequiredAbility {
/* 14 */   public static final PoisonSpitAbility INSTANCE = new PoisonSpitAbility();
/*    */ 
/*    */   
/*    */   public PoisonSpitAbility() {
/* 18 */     super("Poison Spit", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Spits a small dose of poison.");
/* 20 */     setMaxCooldown(7.0D);
/*    */     
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 27 */     PoisonSpitProjectile proj = new PoisonSpitProjectile(player.world, (LivingEntity)player);
/* 28 */     player.world.addEntity((Entity)proj);
/* 29 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 37 */     return new ZoanInfo[] { (ZoanInfo)AxolotlHeavyZoanInfo.INSTANCE, (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\PoisonSpitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */