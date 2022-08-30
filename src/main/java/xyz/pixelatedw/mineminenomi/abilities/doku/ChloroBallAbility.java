/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.ChloroBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DemonChloroBallProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class ChloroBallAbility extends RepeaterAbility {
/* 16 */   public static final ChloroBallAbility INSTANCE = new ChloroBallAbility();
/*    */ 
/*    */   
/*    */   public ChloroBallAbility() {
/* 20 */     super("Chloro Ball", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(9.0D);
/* 22 */     setMaxRepeaterCount(1, 5);
/* 23 */     setDescription("The user spits a bubble made of poison towards the enemy, which also leaves poison on the ground");
/*    */     
/* 25 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/*    */     ChloroBallProjectile chloroBallProjectile;
/* 32 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 33 */     Ability dokuFuguAbility = props.getEquippedAbility((Ability)DokuFuguAbility.INSTANCE);
             AbilityProjectileEntity projectile;
/*    */     
/* 35 */     if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/* 36 */       DemonChloroBallProjectile demonChloroBallProjectile = new DemonChloroBallProjectile(player.world, (LivingEntity)player);
               projectile=demonChloroBallProjectile;
/* 37 */     } else if (dokuFuguAbility != null && dokuFuguAbility.isContinuous()) {
/* 38 */       chloroBallProjectile = new ChloroBallProjectile(player.world, (LivingEntity)player);
                projectile=chloroBallProjectile;
/*    */     } else {
/* 40 */       chloroBallProjectile = new ChloroBallProjectile(player.world, (LivingEntity)player);
                projectile=chloroBallProjectile;
/*    */     } 
/* 42 */     player.world.addEntity((Entity)projectile);
/* 43 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableVenomDemoMode() {
/* 50 */     setCustomTexture("chloro_ball_venom");
/* 51 */     setDisplayName("Demon Chloro Ball");
/* 52 */     setMaxCooldown(10.0D);
/* 53 */     setMaxRepeaterCount(5, 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableVenomDemoMode() {
/* 58 */     setCustomTexture("");
/* 59 */     setDisplayName("Chloro Ball");
/* 60 */     setMaxCooldown(9.0D);
/* 61 */     setMaxRepeaterCount(1, 5);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\ChloroBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */