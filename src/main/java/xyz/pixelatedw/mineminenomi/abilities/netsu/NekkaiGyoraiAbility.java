/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.netsu.NekkaiGyoraiProjectile;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class NekkaiGyoraiAbility extends RepeaterAbility {
/* 10 */   public static final NekkaiGyoraiAbility INSTANCE = new NekkaiGyoraiAbility();
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiAbility() {
/* 14 */     super("Nekkai Gyorai", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Shoots heat-torpedoes, exploding and setting the enemy on fire");
/* 16 */     setMaxCooldown(5.0D);
/* 17 */     setMaxRepeaterCount(3, 5);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     NekkaiGyoraiProjectile proj = new NekkaiGyoraiProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NekkaiGyoraiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */