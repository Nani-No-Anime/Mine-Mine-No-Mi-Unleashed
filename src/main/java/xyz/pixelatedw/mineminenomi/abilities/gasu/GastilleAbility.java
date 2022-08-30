/*    */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.BigGastilleProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gasu.GastilleProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class GastilleAbility extends Ability {
/* 12 */   public static final GastilleAbility INSTANCE = new GastilleAbility();
/*    */ 
/*    */   
/*    */   public GastilleAbility() {
/* 16 */     super("Gastille", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("shoots a beam of lit gas from the users mouth, imbued a effect, that explodes on impact");
/* 18 */     setMaxCooldown(7.0D);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 26 */     if (ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*    */       
/* 28 */       BigGastilleProjectile bigGastilleProjectile = new BigGastilleProjectile(player.world, (LivingEntity)player);
/* 29 */       player.world.addEntity((Entity)bigGastilleProjectile);
/* 30 */       bigGastilleProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/* 31 */       return true;
/*    */     } 
/*    */     
/* 34 */     GastilleProjectile proj = new GastilleProjectile(player.world, (LivingEntity)player);
/* 35 */     player.world.addEntity((Entity)proj);
/* 36 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 1.0F);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\GastilleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */