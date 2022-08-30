/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HikenProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class HikenAbility extends Ability {
/* 12 */   public static final Ability INSTANCE = new HikenAbility();
/*    */ 
/*    */   
/*    */   public HikenAbility() {
/* 16 */     super("Hiken", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("Turns the user's fist into flames and launches it towards the target");
/* 18 */     setMaxCooldown(12.0D);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     HikenProjectile proj = new HikenProjectile(player.world, (LivingEntity)player);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 28 */     player.world.playSound(null, player.getPosition(), ModSounds.MERA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HikenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */