/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.KaenBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class KaenBoshiAbility extends ContinuousAbility implements ISniperAbility {
/* 12 */   public static final Ability INSTANCE = (Ability)new KaenBoshiAbility();
/*    */ 
/*    */   
/*    */   public KaenBoshiAbility() {
/* 16 */     super("Kaen Boshi", AbilityHelper.getStyleCategory());
/* 17 */     setDescription("Fires a flaming pellet, that sets the target on fire");
/* 18 */     setMaxCooldown(5.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void shoot(PlayerEntity player) {
/* 24 */     KaenBoshiProjectile proj = new KaenBoshiProjectile(player.world, (LivingEntity)player);
/* 25 */     player.world.addEntity((Entity)proj);
/* 26 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\KaenBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */