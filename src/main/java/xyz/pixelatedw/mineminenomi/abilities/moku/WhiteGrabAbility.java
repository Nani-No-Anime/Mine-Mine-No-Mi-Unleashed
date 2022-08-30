/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.moku.WhiteGrabProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class WhiteGrabAbility extends ContinuousAbility {
/* 15 */   public static final WhiteGrabAbility INSTANCE = new WhiteGrabAbility();
/*    */   
/* 17 */   private LivingEntity grabbedEntity = null;
/* 18 */   private WhiteGrabProjectile proj = null;
/*    */ 
/*    */   
/*    */   public WhiteGrabAbility() {
/* 22 */     super("White Grab", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements");
/* 24 */     setMaxCooldown(15.0D);
/* 25 */     setThreshold(5.0D);
/*    */     
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 29 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onStartContinuityEvent(PlayerEntity player) {
/* 34 */     this.proj = new WhiteGrabProjectile(player.world, (LivingEntity)player);
/* 35 */     player.world.addEntity((Entity)this.proj);
/* 36 */     this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringContinuityEvent(PlayerEntity player, int timer) {
/* 43 */     if ((this.proj == null || !this.proj.isAlive()) && this.grabbedEntity == null) {
/*    */       
/* 45 */       endContinuity(player);
/*    */       
/*    */       return;
/*    */     } 
/* 49 */     if (this.grabbedEntity != null && !this.grabbedEntity.isAlive()) {
/*    */       
/* 51 */       endContinuity(player);
/*    */       return;
/*    */     } 
/* 54 */     if (this.grabbedEntity != null) {
/*    */       
/* 56 */       this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
/* 57 */       this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
/* 58 */       this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/* 59 */       this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.SMOKE, 40, 0));
/*    */       
/* 61 */       double distance = 7.0D;
/* 62 */       Vec3d lookVec = player.getLookVec();
/* 63 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/* 64 */       if (!player.world.getBlockState(new BlockPos(pos)).isSolid()) {
/* 65 */         this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
/*    */       }
/* 67 */       this.grabbedEntity.fallDistance = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onEndContinuityEvent(PlayerEntity player) {
/* 73 */     this.grabbedEntity = null;
/* 74 */     this.proj = null;
/*    */     
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void grabEntity(LivingEntity target) {
/* 81 */     this.grabbedEntity = target;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhiteGrabAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */