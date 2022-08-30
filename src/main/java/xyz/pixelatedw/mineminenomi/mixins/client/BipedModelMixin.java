/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.HandcuffedAnimation;
/*    */ 
/*    */ @Mixin({BipedModel.class})
/*    */ public abstract class BipedModelMixin<T extends LivingEntity>
/*    */   extends AgeableModel<T>
/*    */ {
/*    */   @Shadow
/*    */   public ModelRenderer bipedHead;
/*    */   @Shadow
/*    */   public ModelRenderer bipedBody;
/*    */   @Shadow
/*    */   public ModelRenderer bipedRightArm;
/*    */   @Shadow
/*    */   public ModelRenderer bipedLeftArm;
/*    */   @Shadow
/*    */   public ModelRenderer bipedRightLeg;
/*    */   @Shadow
/*    */   public ModelRenderer bipedLeftLeg;
/*    */   @Shadow
/*    */   public ModelRenderer bipedHeadwear;
/*    */   
/*    */   @Inject(method = {"setRotationAngles"}, at = {@At("HEAD")})
/*    */   public void blockPunchAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
/* 43 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*    */     
/* 45 */     if (props.isBlackLeg() && entity.getHeldItemMainhand().isEmpty())
/*    */     {
/* 47 */       this.swingProgress = 0.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"setRotationAngles"}, at = {@At("TAIL")})
/*    */   public void legKickAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
/* 54 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*    */     
/* 56 */     if (props.isBlackLeg()) {
/*    */       
/* 58 */       if (((LivingEntity)entity).prevPosX - entity.getPosX() == 0.0D && ((LivingEntity)entity).prevPosZ - entity.getPosZ() == 0.0D && !entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
/*    */         
/* 60 */         this.bipedRightLeg.rotationPointY -= 4.0F;
/* 61 */         this.bipedRightLeg.rotationPointZ -= 3.0F;
/*    */       } 
/*    */       
/* 64 */       if (((LivingEntity)entity).swingProgress > 0.0F && entity.getHeldItemMainhand().isEmpty()) {
/*    */         
/* 66 */         float swingProgress = ((LivingEntity)entity).swingProgress;
/* 67 */         this.bipedRightLeg.rotateAngleX -= ((LivingEntity)entity).swingProgress * 2.0F;
/* 68 */         this.bipedRightLeg.rotateAngleZ += ((LivingEntity)entity).swingProgress * 2.0F;
/* 69 */         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F) * 0.2F;
/* 70 */         this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 71 */         this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 72 */         this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 73 */         this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 74 */         this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 75 */         this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 76 */         this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/*    */         
/* 78 */         this.bipedHead.copyModelAngles(this.bipedHeadwear);
/*    */       } 
/*    */     } 
/*    */     
/* 82 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 84 */       BipedModel model = (BipedModel)(Object)this;
/*    */ 
/*    */       
/* 87 */       if (entity.isPotionActive(ModEffects.HANDCUFFED) || entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
/* 88 */         HandcuffedAnimation.INSTANCE.setAnimationAngles((PlayerEntity)entity, model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */       }
/* 90 */       IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility((LivingEntity)entity);
/* 91 */       if (ability != null)
/* 92 */         ability.getAnimation().setAnimationAngles((PlayerEntity)entity, model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\BipedModelMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */