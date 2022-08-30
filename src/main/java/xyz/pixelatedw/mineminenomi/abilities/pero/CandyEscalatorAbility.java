/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyEscalatorProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CandyEscalatorAbility extends Ability {
/* 10 */   public static final CandyEscalatorAbility INSTANCE = new CandyEscalatorAbility();
/*    */ 
/*    */   
/*    */   public CandyEscalatorAbility() {
/* 14 */     super("Candy Escalator", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("The user creates an escalator made out of candy");
/* 16 */     setMaxCooldown(8.0D);
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     CandyEscalatorProjectile proj = new CandyEscalatorProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyEscalatorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */