/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon.BarizodonProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonAssaultZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class BarizodonAbility extends RepeaterAbility implements IFormRequiredAbility {
/* 17 */   public static final BarizodonAbility INSTANCE = new BarizodonAbility();
/*    */ 
/*    */   
/*    */   public BarizodonAbility() {
/* 21 */     super("Barizodon", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Shoots out a barrage of elliptic air projectiles using the user's wings");
/* 23 */     setMaxCooldown(7.0D);
/* 24 */     setMaxRepeaterCount(8, 2);
/*    */     
/* 26 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 31 */     for (int i = 0; i < WyHelper.randomWithRange(1, 4); i++) {
/*    */       
/* 33 */       BarizodonProjectile proj = new BarizodonProjectile(player.world, (LivingEntity)player);
/* 34 */       player.world.addEntity((Entity)proj);
/* 35 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 10.0F);
/*    */     } 
/*    */     
/* 38 */     player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 46 */     return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE, (ZoanInfo)PteranodonAssaultZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\BarizodonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */