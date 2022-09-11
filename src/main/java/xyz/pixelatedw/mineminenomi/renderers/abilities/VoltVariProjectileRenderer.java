package xyz.pixelatedw.mineminenomi.renderers.abilities;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import java.util.Random;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVariProjectile;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;

@OnlyIn(Dist.CLIENT)
public class VoltVariProjectileRenderer<T extends VoltVariProjectile, M extends EntityModel<T>> extends EntityRenderer<T> {
  private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
  private float red; private float blue; private float green; private float alpha; protected M model; private ResourceLocation texture; Random random;
  
  public void setTexture(ResourceLocation res) {
    this.texture = res;
  }
  
  public VoltVariProjectileRenderer(EntityRendererManager renderManager, M model) { super(renderManager);
































    
    this.random = new Random();
    this.model = model; }
  public void setColor(double red, double green, double blue, double alpha) { this.red = (float)(red / 255.0D);
    this.green = (float)(green / 255.0D);
    this.blue = (float)(blue / 255.0D);
    this.alpha = (float)(alpha / 255.0D); } public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) { if (((VoltVariProjectile)entity).ticksExisted < 2) {
      return;
    }
    
    setScale(entity.getSize(), entity.getSize(), entity.getSize());
    matrixStack.push();
    IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.getLightning());
    
    float randMovement = (((VoltVariProjectile)entity).ticksExisted + partialTicks) / 600.0F;
    float rays = 1000.0F;
    matrixStack.push();
    
    for (int i = 0; i < rays; i++) {
      
      matrixStack.rotate(Vector3f.XP.rotationDegrees(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
      
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(this.random.nextFloat() * 360.0F + randMovement * 90.0F));
      float f3 = (float)((getScale()).x / 3.0D * this.random.nextFloat());
      float f4 = (float)((getScale()).x / 3.0D * this.random.nextFloat());
      Matrix4f matrix4f = matrixStack.getLast().getMatrix();
      
      Color a = new Color(125, 249, 255, 80);
      Color b = new Color(125, 100, 255, 0);
      Color c = new Color(125, 255, 255, 0);
      
      RendererHelper.drawA(vertexBuilder, matrix4f, a);
      RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
      RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
      RendererHelper.drawA(vertexBuilder, matrix4f, a);
      RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, c);
      RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
      RendererHelper.drawA(vertexBuilder, matrix4f, a);
      RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, c);
      RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, b);
    } 
    matrixStack.pop();

    
    matrixStack.rotate(new Quaternion(Vector3f.YP, ((VoltVariProjectile)entity).prevRotationYaw + (((VoltVariProjectile)entity).rotationYaw - ((VoltVariProjectile)entity).prevRotationYaw) * partialTicks - 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.XP, ((VoltVariProjectile)entity).prevRotationPitch + (((VoltVariProjectile)entity).rotationPitch - ((VoltVariProjectile)entity).prevRotationPitch) * partialTicks, true));
    matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
    
    matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
    
    ResourceLocation finalTexture = getEntityTexture((VoltVariProjectile)entity);
    RenderType type = (finalTexture == null) ? ModRenderTypes.TRANSPARENT_COLOR : RenderType.getEntityTranslucent(finalTexture);
    
    if (this.model != null) {
      this.model.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
    }
    matrixStack.pop(); }
   public void setScale(double scaleX, double scaleY, double scaleZ) {
    this.scaleX = (float)scaleX;
    this.scaleY = (float)scaleY;
    this.scaleZ = (float)scaleZ;
  } public ResourceLocation getEntityTexture(VoltVariProjectile entity) {
    if (entity.getThrower() != null && entity.isAffectedByHaki()) {
      
      boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
      if (hardeningCheck) {
        return ModResources.BUSOSHOKU_HAKI_ARM;
      }
    } 
    return this.texture;
  } public Vec3d getScale() {
    return new Vec3d(this.scaleX, this.scaleY, this.scaleZ);
  } public Color getRGB() {
    return new Color(this.red, this.green, this.blue, this.alpha);
  } public static class Factory implements IRenderFactory<VoltVariProjectile> { protected EntityModel model = (EntityModel)new CubeModel();
    protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
    protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
    
    protected ResourceLocation texture;
    
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


    
    public EntityRenderer<? super VoltVariProjectile> createRenderFor(EntityRendererManager manager) {
      VoltVariProjectileRenderer renderer = new VoltVariProjectileRenderer(manager, this.model);
      renderer.setTexture(this.texture);
      renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
      renderer.setColor(this.red, this.green, this.blue, this.alpha);
      return renderer;
    } }

}


