package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.HandcuffedAnimation;

@Mixin({BipedModel.class})
public abstract class BipedModelMixin<T extends LivingEntity>
  extends AgeableModel<T>
{
  @Shadow
  public ModelRenderer bipedHead;
  @Shadow
  public ModelRenderer bipedBody;
  @Shadow
  public ModelRenderer bipedRightArm;
  @Shadow
  public ModelRenderer bipedLeftArm;
  @Shadow
  public ModelRenderer bipedRightLeg;
  @Shadow
  public ModelRenderer bipedLeftLeg;
  @Shadow
  public ModelRenderer bipedHeadwear;
  
  @Inject(method = {"setRotationAngles"}, at = {@At("HEAD")})
  public void blockPunchAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
    
    if (props.isBlackLeg() && entity.getHeldItemMainhand().isEmpty())
    {
      this.swingProgress = 0.0F;
    }
  }

  
  @Inject(method = {"setRotationAngles"}, at = {@At("TAIL")})
  public void legKickAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
    
    if (props.isBlackLeg()) {
      
      if (((LivingEntity)entity).prevPosX - entity.getPosX() == 0.0D && ((LivingEntity)entity).prevPosZ - entity.getPosZ() == 0.0D && !entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED)) {
        
        this.bipedRightLeg.rotationPointY -= 4.0F;
        this.bipedRightLeg.rotationPointZ -= 3.0F;
      } 
      
      if (((LivingEntity)entity).swingProgress > 0.0F && entity.getHeldItemMainhand().isEmpty()) {
        
        float swingProgress = ((LivingEntity)entity).swingProgress;
        this.bipedRightLeg.rotateAngleX -= ((LivingEntity)entity).swingProgress * 2.0F;
        this.bipedRightLeg.rotateAngleZ += ((LivingEntity)entity).swingProgress * 2.0F;
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F) * 0.2F;
        this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
        
        this.bipedHead.copyModelAngles(this.bipedHeadwear);
      } 
    } 
    
    if (entity instanceof PlayerEntity) {
      
      BipedModel model = (BipedModel)(Object)this;

      
      if (entity.isPotionActive(ModEffects.HANDCUFFED) || entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
        HandcuffedAnimation.INSTANCE.setAnimationAngles((PlayerEntity)entity, model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      }
      IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility((LivingEntity)entity);
      if (ability != null)
        ability.getAnimation().setAnimationAngles((PlayerEntity)entity, model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch); 
    } 
  }
}


