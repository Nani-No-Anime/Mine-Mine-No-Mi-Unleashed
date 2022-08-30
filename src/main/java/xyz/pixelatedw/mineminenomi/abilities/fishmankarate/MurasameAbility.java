/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.MurasameProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class MurasameAbility extends RepeaterAbility {
/* 11 */   public static final Ability INSTANCE = (Ability)new MurasameAbility();
/*    */ 
/*    */   
/*    */   public MurasameAbility() {
/* 15 */     super("Murasame", AbilityHelper.getRacialCategory());
/* 16 */     setDescription("The user fires densely compressed shark-shaped waterbullets at the opponent");
/* 17 */     setMaxCooldown(15.0D);
/* 18 */     setMaxRepeaterCount(5, 3);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     MurasameProjectile proj = new MurasameProjectile(player.world, (LivingEntity)player);
/* 26 */     if (player.canSwim())
/* 27 */       proj.setDamage(15.0F); 
/* 28 */     player.world.addEntity((Entity)proj);
/* 29 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\MurasameAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */