/*    */ package xyz.pixelatedw.mineminenomi.abilities.horo;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.MiniHollowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class MiniHollowAbility extends RepeaterAbility {
/* 10 */   public static final MiniHollowAbility INSTANCE = new MiniHollowAbility();
/*    */ 
/*    */   
/*    */   public MiniHollowAbility() {
/* 14 */     super("Mini Hollow", AbilityHelper.getDevilFruitCategory());
/* 15 */     setMaxCooldown(9.0D);
/* 16 */     setMaxRepeaterCount(4, 5);
/* 17 */     setDescription("Launches small ghosts at the opponent, exploding upon impact");
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     MiniHollowProjectile proj = new MiniHollowProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\horo\MiniHollowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */