/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyMaidenProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CandyMaidenAbility extends Ability {
/* 10 */   public static final CandyMaidenAbility INSTANCE = new CandyMaidenAbility();
/*    */ 
/*    */   
/*    */   public CandyMaidenAbility() {
/* 14 */     super("Candy Maiden", AbilityHelper.getDevilFruitCategory());
/* 15 */     setDescription("Creates a gigant maiden shaped projectile that slows down any entity in it's way");
/*    */     
/* 17 */     setMaxCooldown(10.0D);
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     CandyMaidenProjectile proj = new CandyMaidenProjectile(player.world, (LivingEntity)player);
/* 24 */     player.world.addEntity((Entity)proj);
/* 25 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyMaidenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */