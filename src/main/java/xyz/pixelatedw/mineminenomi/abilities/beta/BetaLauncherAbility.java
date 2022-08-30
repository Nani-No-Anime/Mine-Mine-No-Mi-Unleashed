/*    */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.StickyProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class BetaLauncherAbility extends RepeaterAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new BetaLauncherAbility();
/*    */ 
/*    */   
/*    */   public BetaLauncherAbility() {
/* 17 */     super("Beta Launcher", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("Shoots sticky Mucus which cause explosions on contact when combined with fire (also holding Flint & Steel)");
/* 19 */     setMaxCooldown(9.0D);
/* 20 */     setMaxRepeaterCount(6, 3);
/*    */     
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     HanamizuShinkenAbility ability = (HanamizuShinkenAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)HanamizuShinkenAbility.INSTANCE);
/* 29 */     if (ability != null && ability.isContinuous()) {
/* 30 */       ability.endContinuity(player);
/*    */     }
/* 32 */     StickyProjectile proj = new StickyProjectile(player.world, (LivingEntity)player);
/* 33 */     if (player.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL) {
/*    */       
/* 35 */       proj.setDamage(8.0F);
/* 36 */       proj.setCauseExplosion();
/* 37 */       proj.setChangeHurtTime(true);
/*    */     } 
/* 39 */     player.world.addEntity((Entity)proj);
/* 40 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaLauncherAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */