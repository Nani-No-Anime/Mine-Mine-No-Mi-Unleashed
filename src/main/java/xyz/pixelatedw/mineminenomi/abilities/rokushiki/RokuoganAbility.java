/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki.RokuoganProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*    */ 
/*    */ public class RokuoganAbility extends PunchTriggerAbility {
/* 12 */   public static final RokuoganAbility INSTANCE = new RokuoganAbility();
/*    */ 
/*    */   
/*    */   public RokuoganAbility() {
/* 16 */     super("Rokuogan", AbilityHelper.getRacialCategory());
/* 17 */     setMaxCooldown(50.0D);
/* 18 */     setDescription("The user places both their fists right in front of the target to focus their physical strength to launch a devastating shockwave forward");
/*    */     
/* 20 */     stopAfterUsage(true);
/* 21 */     this.onSwingEvent = this::onSwingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onSwingEvent(PlayerEntity player) {
/* 26 */     RokuoganProjectile proj = new RokuoganProjectile(player.world, (LivingEntity)player);
/* 27 */     player.world.addEntity((Entity)proj);
/* 28 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 30 */     player.world.playSound(null, player.getPosition(), ModSounds.ROKUOGAN, SoundCategory.PLAYERS, 1.0F, 0.2F + player.getRNG().nextFloat());
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\RokuoganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */