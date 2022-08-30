/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BrickBatProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class BrickBatAbility extends RepeaterAbility {
/* 10 */   public static final BrickBatAbility INSTANCE = new BrickBatAbility();
/*    */ 
/*    */   
/*    */   public BrickBatAbility() {
/* 14 */     super("Brick Bat", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Launches bats created from the user's shadow at the opponent");
/* 16 */     setMaxCooldown(8.0D);
/* 17 */     setMaxRepeaterCount(6, 3);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     BrickBatProjectile proj = new BrickBatProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.5F);
/*    */     
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\BrickBatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */