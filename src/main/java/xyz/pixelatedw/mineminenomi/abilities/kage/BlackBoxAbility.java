/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BlackBoxProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BlackBoxAbility extends Ability {
/* 10 */   public static final BlackBoxAbility INSTANCE = new BlackBoxAbility();
/*    */ 
/*    */   
/*    */   public BlackBoxAbility() {
/* 14 */     super("Black Box", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Encases and suffocates the opponent in a box made of shadows");
/* 16 */     setMaxCooldown(16.0D);
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     BlackBoxProjectile proj = new BlackBoxProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.5F);
/*    */     
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\BlackBoxAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */