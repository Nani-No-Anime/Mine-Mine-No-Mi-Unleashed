/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.GammaKnifeProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class GammaKnifeAbility extends Ability {
/* 10 */   public static final Ability INSTANCE = new GammaKnifeAbility();
/*    */ 
/*    */   
/*    */   public GammaKnifeAbility() {
/* 14 */     super("Gamma Knife", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(25.0D);
/* 16 */     setDescription("Creates a blade of gamma radiation which massively damages the opponent's organs");
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     if (!OpeHelper.hasRoomActive(player, this)) {
/* 24 */       return false;
/*    */     }
/* 26 */     GammaKnifeProjectile proj = new GammaKnifeProjectile(player.world, (LivingEntity)player);
/* 27 */     player.world.addEntity((Entity)proj);
/* 28 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\GammaKnifeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */