/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPartisanProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class IceBlockPartisanAbility extends RepeaterAbility {
/* 11 */   public static final Ability INSTANCE = (Ability)new IceBlockPartisanAbility();
/*    */ 
/*    */   
/*    */   public IceBlockPartisanAbility() {
/* 15 */     super("Ice Block: Partisan", AbilityHelper.getDevilFruitCategory());
/* 16 */     setMaxCooldown(10.0D);
/* 17 */     setMaxRepeaterCount(8, 4);
/* 18 */     setDescription("Creates several spears of ice that the user hurls at the enemy");
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     IceBlockPartisanProjectile proj = new IceBlockPartisanProjectile(player.world, (LivingEntity)player);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBlockPartisanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */