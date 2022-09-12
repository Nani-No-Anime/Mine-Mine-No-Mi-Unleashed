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
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;

import java.awt.*;




@OnlyIn(Dist.CLIENT)
public class AbilityProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
  extends EntityRenderer<T>
{
  private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
  
  private float red;
  
  private float blue;
  private float green;
  private float alpha;
  
  public AbilityProjectileRenderer(EntityRendererManager renderManager, M model) {
    super(renderManager);
    this.model = model;
  }
  protected M model; private ResourceLocation texture; private boolean usePlayerTexture; private boolean isGlowing;
  
  public void setTexture(ResourceLocation res) {
    this.texture = res;
  }

  
  public void setPlayerTexture(boolean flag) {
    this.usePlayerTexture = flag;
  }

  
  public void setColor(double red, double green, double blue, double alpha) {
    this.red = (float)(red / 255.0D);
    this.green = (float)(green / 255.0D);
    this.blue = (float)(blue / 255.0D);
    this.alpha = (float)(alpha / 255.0D);
  }

  
  public void setGlowing(boolean flag) {
    this.isGlowing = flag;
  }

  
  public boolean isGlowing() {
    return this.isGlowing;
  }

  
  public void setScale(double scaleX, double scaleY, double scaleZ) {
    this.scaleX = (float)scaleX;
    this.scaleY = (float)scaleY;
    this.scaleZ = (float)scaleZ;
  }

  
  public Vec3d getScale() {
    return new Vec3d(this.scaleX, this.scaleY, this.scaleZ);
  }

  
  public Color getRGB() {
    return new Color(this.red, this.green, this.blue, this.alpha);
  }

  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    RenderType type;
    if (((AbilityProjectileEntity)entity).ticksExisted < 2) {
      return;
    }
    if (entity instanceof IFlexibleSizeProjectile) {
      setScale(((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize());
    }
    matrixStack.push();
    
    matrixStack.rotate(new Quaternion(Vector3f.YP, ((AbilityProjectileEntity)entity).prevRotationYaw + (((AbilityProjectileEntity)entity).rotationYaw - ((AbilityProjectileEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.XP, ((AbilityProjectileEntity)entity).prevRotationPitch + (((AbilityProjectileEntity)entity).rotationPitch - ((AbilityProjectileEntity)entity).prevRotationPitch) * partialTicks, true));
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
    
    matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);

    
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
    if (isSlim && this.model instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel) {
      
      try {
        
        this.model = (M)this.model.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
      }
      catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
        
        e.printStackTrace();
      } 
    }
    
    if (this.model != null) {
      this.model.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
    }
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(AbilityProjectileEntity entity) {
    if (entity.getThrower() != null && entity.isAffectedByHaki()) {
      
      boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
      boolean imbuingCheck = (entity.isAffectedByImbuing() && HakiHelper.hasImbuingActive(entity.getThrower()));
      if (hardeningCheck || imbuingCheck) {
        return ModResources.BUSOSHOKU_HAKI_ARM;
      }
    } 
    if (this.usePlayerTexture && entity.getThrower() != null && entity.getThrower() instanceof PlayerEntity) {
      
      PlayerEntity player = entity.world.getPlayerByUuid(entity.getThrower().getUniqueID());
      if (player != null) {
        return ((AbstractClientPlayerEntity)player).getLocationSkin();
      }
      return DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
    } 
    
    return this.texture;
  }
  
  public static class Factory
    implements IRenderFactory<AbilityProjectileEntity> {
    protected EntityModel model = (EntityModel)new CubeModel();
    protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
    protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
    
    protected ResourceLocation texture;
    protected boolean usePlayerTexture;
    protected boolean isGlowing = false;
    
    public Factory(EntityModel model) {
      this.model = model;
    }

    
    public Factory setTexture(String textureName) {
      this.texture = new ResourceLocation(APIConfig.projectId, "textures/models/projectiles/" + textureName + ".png");
      return this;
    }

    
    public Factory setTexture(ResourceLocation location) {
      this.texture = location;
      return this;
    }

    
    public Factory setPlayerTexture() {
      this.usePlayerTexture = true;
      return this;
    }

    
    public Factory setGlowing() {
      this.isGlowing = true;
      return this;
    }

    
    public Factory setColor(double red, double green, double blue, double alpha) {
      this.red = red;
      this.green = green;
      this.blue = blue;
      this.alpha = alpha;
      return this;
    }

    
    public Factory setColor(String hex) {
      Color color = WyHelper.hexToRGB(hex);
      this.red = color.getRed();
      this.green = color.getGreen();
      this.blue = color.getBlue();
      this.alpha = color.getAlpha();
      return this;
    }

    
    public Factory setAlpha(double alpha) {
      this.alpha = alpha;
      return this;
    }

    
    public Factory setScale(double scale) {
      this.scaleX = this.scaleY = this.scaleZ = scale;
      return this;
    }

    
    public Factory setScale(double scaleX, double scaleY, double scaleZ) {
      this.scaleX = scaleX;
      this.scaleY = scaleY;
      this.scaleZ = scaleZ;
      return this;
    }


    
    public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
      AbilityProjectileRenderer<AbilityProjectileEntity, EntityModel<AbilityProjectileEntity>> renderer = new AbilityProjectileRenderer<>(manager, this.model);
      renderer.setTexture(this.texture);
      renderer.setPlayerTexture(this.usePlayerTexture);
      renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
      renderer.setColor(this.red, this.green, this.blue, this.alpha);
      return renderer;
    }
  }
}


