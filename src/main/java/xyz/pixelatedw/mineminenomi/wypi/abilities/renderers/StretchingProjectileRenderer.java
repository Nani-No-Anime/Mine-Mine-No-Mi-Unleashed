package xyz.pixelatedw.mineminenomi.wypi.abilities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;




@OnlyIn(Dist.CLIENT)
public class StretchingProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
  extends AbilityProjectileRenderer<T, M>
{
  private M stretchModel;
  private float stretchScaleX = 1.0F, stretchScaleY = 1.0F, stretchScaleZ = 1.0F;

  
  public StretchingProjectileRenderer(EntityRendererManager renderManager, M model, M stretchModel) {
    super(renderManager, model);
    this.stretchModel = stretchModel;
  }

  
  public void setStretchScale(double scaleX, double scaleY, double scaleZ) {
    this.stretchScaleX = (float)scaleX;
    this.stretchScaleY = (float)scaleY;
    this.stretchScaleZ = (float)scaleZ;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    if (((AbilityProjectileEntity)entity).ticksExisted < 2) {
      return;
    }
    if (entity.getThrower() == null || !entity.getThrower().isAlive()) {
      
      entity.remove();
      
      return;
    } 
    Direction dir = Direction.fromAngle((entity.getThrower()).rotationYaw);
    Vec3d originPos = entity.getThrower().getPositionVec().add(dir.getXOffset() * 1.5D, 0.0D, dir.getZOffset() * 1.5D);


    
    Vec3d entityPos = new Vec3d(MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosX, entity.getPosX()), MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosY, entity.getPosY()), MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosZ, entity.getPosZ()));
    
    Vec3d stretchVec = entityPos.subtract(originPos);
    
    if (this.stretchModel != null) {
      RenderType type;
      matrixStack.push();
      
      matrixStack.rotate(new Quaternion(Vector3f.YP, ((AbilityProjectileEntity)entity).prevRotationYaw + (((AbilityProjectileEntity)entity).rotationYaw - ((AbilityProjectileEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.XP, ((AbilityProjectileEntity)entity).prevRotationPitch + (((AbilityProjectileEntity)entity).rotationPitch - ((AbilityProjectileEntity)entity).prevRotationPitch) * partialTicks, true));
      matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
      
      float modelLength = this.stretchScaleZ / 16.0F;
      float modelOffset = 0.25F;
      float stretchLength = (float)stretchVec.length();
      matrixStack.translate(0.0D, 0.0D, -modelOffset);
      matrixStack.scale(this.stretchScaleX, this.stretchScaleY, (stretchLength + 2.0F * modelOffset) / modelLength);
      matrixStack.translate(0.0D, 0.0D, modelOffset);

      
      ResourceLocation finalTexture = getEntityTexture((AbilityProjectileEntity)entity);
      if (finalTexture == null) {
        type = isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
      } else {
        type = RenderType.getEntityTranslucent(finalTexture);
      } 
      boolean isSlim = false;
      if (entity.getThrower() instanceof net.minecraft.client.entity.player.ClientPlayerEntity) {
        isSlim = ((AbstractClientPlayerEntity)entity.getThrower()).getSkinType().equals("slim");
      }
      if (isSlim && this.stretchModel instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel) {
        
        try {
          
          this.stretchModel = (M)this.stretchModel.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
        }
        catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
          
          e.printStackTrace();
        } 
      }
      
      this.stretchModel.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
      
      matrixStack.pop();
    } 
    
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }
  
  public static class Factory
    extends AbilityProjectileRenderer.Factory {
    private EntityModel stretchModel;
    protected double stretchScaleX = 1.0D, stretchScaleY = 1.0D, stretchScaleZ = 8.0D;

    
    public Factory(EntityModel stretchModel) {
      super(null);
      this.stretchModel = stretchModel;
    }

    
    public Factory(EntityModel tipModel, EntityModel stretchModel) {
      super(tipModel);
      this.stretchModel = stretchModel;
    }

    
    public Factory setStretchScale(double scaleX, double scaleY) {
      return setStretchScale(scaleX, scaleY, 8.0D);
    }

    
    public Factory setStretchScale(double scaleX, double scaleY, double scaleZ) {
      this.stretchScaleX = scaleX;
      this.stretchScaleY = scaleY;
      this.stretchScaleZ = scaleZ;
      return this;
    }


    
    public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
      StretchingProjectileRenderer<AbilityProjectileEntity, EntityModel<AbilityProjectileEntity>> renderer = new StretchingProjectileRenderer<>(manager, this.model, this.stretchModel);
      renderer.setTexture(this.texture);
      renderer.setStretchScale(this.stretchScaleX, this.stretchScaleY, this.stretchScaleZ);
      renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
      renderer.setColor(this.red, this.green, this.blue, this.alpha);
      renderer.setPlayerTexture(this.usePlayerTexture);
      renderer.setGlowing(this.isGlowing);
      return renderer;
    }
  }
}


