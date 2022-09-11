package xyz.pixelatedw.mineminenomi.particles;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.config.ClientConfig;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;

@OnlyIn(Dist.CLIENT)
public class SimpleParticle
  extends TexturedParticle {
  private static final ImmutableList<VertexFormatElement> ELEMENTS = ImmutableList.of(DefaultVertexFormats.POSITION_3F, DefaultVertexFormats.TEX_2F, DefaultVertexFormats.COLOR_4UB, DefaultVertexFormats.NORMAL_3B, DefaultVertexFormats.PADDING_1B);
  public static final VertexFormat VERTEX_FORMAT = new VertexFormat(ELEMENTS);
  
  private boolean hasMotionDecay = true;
  private boolean hasScaleDecay = true;
  private Vector3f rotationVector;
  private float rotationSpeed;
  private IParticleRenderType type;
  
  public SimpleParticle(IParticleRenderType type, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    super(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
    this.maxAge = 30 + this.rand.nextInt(10);
    this.age = 0;
    this.particleScale = 0.2F;
    this.particleGravity = 0.0F;
    setColor(1.0F, 1.0F, 1.0F);
    this.canCollide = false;
    
    this.type = type;
    
    this.motionX = motionX;
    this.motionY = motionY;
    this.motionZ = motionZ;
  }


  
  public void tick() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;
    if (this.particleGravity != 0.0F) {
      this.motionY = -0.04D * this.particleGravity;
    }
    move(this.motionX, this.motionY, this.motionZ);
    if (this.hasMotionDecay) {
      
      this.motionX *= 0.99D;
      this.motionY *= 0.99D;
      this.motionZ *= 0.99D;
    } 
    
    if (this.hasScaleDecay) {
      
      if (this.age >= this.maxAge / 2)
      {
        if (this.particleScale > 0.0F) {
          this.particleScale /= 1.1F;
        }
      }
      if (this.age + 5 >= this.maxAge)
      {
        if (this.particleAlpha > 0.0F) {
          this.particleAlpha = (float)(this.particleAlpha / 1.15D);
        }
      }
    } 
    if (this.rotationSpeed != 0.0F) {
      this.particleAngle -= this.rotationSpeed;
    }
    if (this.age++ >= this.maxAge || this.onGround) {
      setExpired();
    }
  }
  
  public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
    Quaternion quaternion;
    Vec3d vec3d = renderInfo.getProjectedView();
    float f = (float)(MathHelper.lerp(partialTicks, this.prevPosX, this.posX) - vec3d.getX());
    float f1 = (float)(MathHelper.lerp(partialTicks, this.prevPosY, this.posY) - vec3d.getY());
    float f2 = (float)(MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());
    
    if (this.particleAngle == 0.0F) {
      
      quaternion = renderInfo.getRotation();
    }
    else {
      
      quaternion = new Quaternion(renderInfo.getRotation());
      quaternion.multiply(this.rotationVector.rotation(this.particleAngle));
    } 
    
    Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
    vector3f1.transform(quaternion);
    Vector3f[] avector3f = { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
    float scale = getScale(partialTicks);
    
    for (int i = 0; i < 4; i++) {
      
      Vector3f vector3f = avector3f[i];
      vector3f.transform(quaternion);
      vector3f.mul(scale);
      vector3f.add(f, f1, f2);
    } 
    
    float minU = getMinU();
    float maxU = getMaxU();
    float minV = getMinV();
    float maxV = getMaxV();
    int brightness = getBrightnessForRender(partialTicks);
    buffer.pos(avector3f[0].getX(), avector3f[0].getY(), avector3f[0].getZ()).tex(maxU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
    buffer.pos(avector3f[1].getX(), avector3f[1].getY(), avector3f[1].getZ()).tex(maxU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
    buffer.pos(avector3f[2].getX(), avector3f[2].getY(), avector3f[2].getZ()).tex(minU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
    buffer.pos(avector3f[3].getX(), avector3f[3].getY(), avector3f[3].getZ()).tex(minU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
  }

  
  public SimpleParticle setParticleAlpha(float f) {
    setAlphaF(f);
    return this;
  }

  
  public SimpleParticle setParticleScale(float f) {
    this.particleScale = f;
    return this;
  }

  
  public SimpleParticle setParticleRotation(float f) {
    this.rotationSpeed = f;
    return this;
  }

  
  public SimpleParticle setParticleGravity(float f) {
    this.particleGravity = f;
    return this;
  }

  
  public SimpleParticle setParticleAge(int i) {
    this.maxAge = i + this.rand.nextInt(10);
    return this;
  }

  
  public SimpleParticle setHasMotionDecay(boolean flag) {
    this.hasMotionDecay = flag;
    return this;
  }

  
  public SimpleParticle setRotation(Vector3f vec) {
    this.rotationVector = vec;
    return this;
  }

  
  public SimpleParticle setHasScaleDecay(boolean flag) {
    this.hasScaleDecay = flag;
    return this;
  }

  
  public BlockPos getPos() {
    return new BlockPos(this.posX, this.posY, this.posZ);
  }


  
  public IParticleRenderType getRenderType() {
    return this.type;
  }


  
  protected float getMaxU() {
    return 1.0F;
  }


  
  protected float getMaxV() {
    return 1.0F;
  }


  
  protected float getMinU() {
    return 0.0F;
  }


  
  protected float getMinV() {
    return 0.0F;
  }
  
  public static class Factory
    implements IParticleFactory<GenericParticleData>
  {
    private IParticleRenderType type;
    
    public Factory(ResourceLocation res) {
      this.type = new CustomParticleRenderType(res);
    }


    
    public Particle makeParticle(GenericParticleData data, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
      SimpleParticle particle = new SimpleParticle(this.type, world, posX, posY, posZ, data.getMotionX(), data.getMotionY(), data.getMotionZ());
      particle.setColor(data.getRed(), data.getGreen(), data.getBlue());
      particle.setRotation(new Vector3f(data.getRotX(), data.getRotY(), data.getRotZ()));
      particle.setParticleAlpha(data.getAlpha());
      particle.setParticleScale(data.getSize() / 10.0F);
      particle.setParticleRotation(data.getRotationSpeed());
      particle.setParticleAge(data.getLife());
      particle.setHasMotionDecay(data.hasMotionDecay());
      particle.setHasScaleDecay(data.hasScaleDecay());
      
      if ((Minecraft.getInstance()).player != null)
      {
        if (data.getEntityID() == (Minecraft.getInstance()).player.getEntityId()) {
          
          if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0 && data.getHideTooClose())
            particle.setParticleAlpha(ClientConfig.INSTANCE.getFireVisibility() / 100.0F); 
        } else if (data.getHideFromOthers()) {
          particle.setParticleAlpha(0.0F);
        } 
      }
      return (Particle)particle;
    }
  }
}


