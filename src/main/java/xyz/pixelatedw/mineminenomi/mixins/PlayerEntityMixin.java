/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntitySize;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Constant;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyConstant;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AttributeHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ @Mixin({PlayerEntity.class})
/*    */ public abstract class PlayerEntityMixin
/*    */   extends LivingEntity
/*    */ {
/*    */   public PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
/* 33 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   @ModifyConstant(method = {"attackTargetEntityWithCurrentItem(Lnet/minecraft/entity/Entity;)V"}, constant = {@Constant(doubleValue = 9.0D)})
/*    */   private double getActualAttackRange(double attackRange) {
/* 39 */     return AttributeHelper.getSquaredAttackRangeDistance(this, attackRange);
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"getSize"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getSize(Pose pose, CallbackInfoReturnable<EntitySize> callback) {
/* 45 */     IDevilFruit props = DevilFruitCapability.get(this);
/* 46 */     if (!WyHelper.isNullOrEmpty(props.getZoanPoint())) {
/*    */       
/* 48 */       ZoanInfo info = MorphHelper.getZoanInfo(this);
/* 49 */       if (info == null)
/*    */         return; 
/* 51 */       Map<Pose, EntitySize> poses = info.getSizes();
/* 52 */       if (poses != null && poses.containsKey(getPose()) && poses.get(getPose()) != null) {
/* 53 */         callback.setReturnValue(poses.get(getPose()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"attackTargetEntityWithCurrentItem"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;spawnSweepParticles()V", shift = At.Shift.AFTER)})
/*    */   public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo callback) {
/* 67 */     targetEntity.hurtResistantTime = 0;
/* 68 */     targetEntity.attackEntityFrom((DamageSource)new EntityDamageSource("sweep_damage", (Entity)this), 0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\PlayerEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */