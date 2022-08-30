/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class RenpatsuNamariBoshiAbility extends ContinuousAbility implements ISniperAbility {
/* 12 */   public static final RenpatsuNamariBoshiAbility INSTANCE = new RenpatsuNamariBoshiAbility();
/*    */ 
/*    */   
/*    */   public RenpatsuNamariBoshiAbility() {
/* 16 */     super("Renpatsu Namari Boshi", AbilityHelper.getStyleCategory());
/* 17 */     setMaxCooldown(6.0D);
/* 18 */     setDescription("Lets the user fire a barrage of exploding shots");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void shoot(PlayerEntity player) {
/* 24 */     for (int i = 0; i < 4; i++) {
/*    */       
/* 26 */       RenpatsuNamariBoshiProjectile proj = new RenpatsuNamariBoshiProjectile(player.world, (LivingEntity)player);
/* 27 */       proj.setLocationAndAngles(player
/* 28 */           .getPosX() + WyHelper.randomWithRange(-1, 1) / 2.0D + WyHelper.randomDouble(), player
/* 29 */           .getPosY() + 0.75D + WyHelper.randomDouble(), player
/* 30 */           .getPosZ() + WyHelper.randomWithRange(-1, 1) / 2.0D + WyHelper.randomDouble(), 0.0F, 0.0F);
/*    */       
/* 32 */       player.world.addEntity((Entity)proj);
/* 33 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 5.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\RenpatsuNamariBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */