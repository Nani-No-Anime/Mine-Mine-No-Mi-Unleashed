/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteBlowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class WhiteBlowRushAbility extends RepeaterAbility {
/* 10 */   public static final Ability INSTANCE = (Ability)new WhiteBlowRushAbility();
/*    */ 
/*    */ 
/*    */   
/*    */   public WhiteBlowRushAbility() {
/* 15 */     super("White Blow Rush", AbilityHelper.getDevilFruitCategory());
/* 16 */     setMaxCooldown(6.0D);
/* 17 */     setMaxRepeaterCount(8, 3);
/* 18 */     setDescription("Shoots clouds of smoke to engulf the opponent and trap them");
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     WhiteBlowProjectile proj = new WhiteBlowProjectile(player.world, (LivingEntity)player);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.75F, 4.0F);
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteBlowRushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */