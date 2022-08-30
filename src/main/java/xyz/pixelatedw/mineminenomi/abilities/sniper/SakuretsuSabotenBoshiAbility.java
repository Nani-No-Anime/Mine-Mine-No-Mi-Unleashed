/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.SakuretsuSabotenBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class SakuretsuSabotenBoshiAbility extends ContinuousAbility implements ISniperAbility {
/* 12 */   public static final Ability INSTANCE = (Ability)new SakuretsuSabotenBoshiAbility();
/*    */ 
/*    */   
/*    */   public SakuretsuSabotenBoshiAbility() {
/* 16 */     super("Sakuretsu Saboten Boshi", AbilityHelper.getStyleCategory());
/* 17 */     setMaxCooldown(10.0D);
/* 18 */     setDescription("The fired projectile explodes on impact and creates cacti arond the target to trap them");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void shoot(PlayerEntity player) {
/* 24 */     SakuretsuSabotenBoshiProjectile proj = new SakuretsuSabotenBoshiProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 3.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\SakuretsuSabotenBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */