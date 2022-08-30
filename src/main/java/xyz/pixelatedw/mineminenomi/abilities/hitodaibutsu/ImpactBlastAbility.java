/*    */ package xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu.ImpactBlastProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.HitoDaibutsuZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*    */ 
/*    */ public class ImpactBlastAbility extends PunchTriggerAbility implements IFormRequiredAbility {
/* 14 */   public static final Ability INSTANCE = (Ability)new ImpactBlastAbility();
/*    */ 
/*    */   
/*    */   public ImpactBlastAbility() {
/* 18 */     super("Impact Blast", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Launches a golden shock wave forward when punching an enemy or the air, hurting every entity in its path");
/* 20 */     setMaxCooldown(6.0D);
/*    */     
/* 22 */     stopAfterUsage(true);
/* 23 */     this.onSwingEvent = this::onSwingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onSwingEvent(PlayerEntity player) {
/* 28 */     ImpactBlastProjectile proj = new ImpactBlastProjectile(player.world, (LivingEntity)player);
/* 29 */     player.world.addEntity((Entity)proj);
/* 30 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.0F);
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 37 */     return new ZoanInfo[] { (ZoanInfo)HitoDaibutsuZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hitodaibutsu\ImpactBlastAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */