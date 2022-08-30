/*     */ package xyz.pixelatedw.mineminenomi.particles;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.IParticleRenderType;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.TexturedParticle;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SimpleParticle
/*     */   extends TexturedParticle {
/*  30 */   private static final ImmutableList<VertexFormatElement> ELEMENTS = ImmutableList.of(DefaultVertexFormats.POSITION_3F, DefaultVertexFormats.TEX_2F, DefaultVertexFormats.COLOR_4UB, DefaultVertexFormats.NORMAL_3B, DefaultVertexFormats.PADDING_1B);
/*  31 */   public static final VertexFormat VERTEX_FORMAT = new VertexFormat(ELEMENTS);
/*     */   
/*     */   private boolean hasMotionDecay = true;
/*     */   private boolean hasScaleDecay = true;
/*     */   private Vector3f rotationVector;
/*     */   private float rotationSpeed;
/*     */   private IParticleRenderType type;
/*     */   
/*     */   public SimpleParticle(IParticleRenderType type, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*  40 */     super(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*  41 */     this.maxAge = 30 + this.rand.nextInt(10);
/*  42 */     this.age = 0;
/*  43 */     this.particleScale = 0.2F;
/*  44 */     this.particleGravity = 0.0F;
/*  45 */     setColor(1.0F, 1.0F, 1.0F);
/*  46 */     this.canCollide = false;
/*     */     
/*  48 */     this.type = type;
/*     */     
/*  50 */     this.motionX = motionX;
/*  51 */     this.motionY = motionY;
/*  52 */     this.motionZ = motionZ;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  58 */     this.prevPosX = this.posX;
/*  59 */     this.prevPosY = this.posY;
/*  60 */     this.prevPosZ = this.posZ;
/*  61 */     if (this.particleGravity != 0.0F) {
/*  62 */       this.motionY = -0.04D * this.particleGravity;
/*     */     }
/*  64 */     move(this.motionX, this.motionY, this.motionZ);
/*  65 */     if (this.hasMotionDecay) {
/*     */       
/*  67 */       this.motionX *= 0.99D;
/*  68 */       this.motionY *= 0.99D;
/*  69 */       this.motionZ *= 0.99D;
/*     */     } 
/*     */     
/*  72 */     if (this.hasScaleDecay) {
/*     */       
/*  74 */       if (this.age >= this.maxAge / 2)
/*     */       {
/*  76 */         if (this.particleScale > 0.0F) {
/*  77 */           this.particleScale /= 1.1F;
/*     */         }
/*     */       }
/*  80 */       if (this.age + 5 >= this.maxAge)
/*     */       {
/*  82 */         if (this.particleAlpha > 0.0F) {
/*  83 */           this.particleAlpha = (float)(this.particleAlpha / 1.15D);
/*     */         }
/*     */       }
/*     */     } 
/*  87 */     if (this.rotationSpeed != 0.0F) {
/*  88 */       this.particleAngle -= this.rotationSpeed;
/*     */     }
/*  90 */     if (this.age++ >= this.maxAge || this.onGround) {
/*  91 */       setExpired();
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
/*     */     Quaternion quaternion;
/*  97 */     Vec3d vec3d = renderInfo.getProjectedView();
/*  98 */     float f = (float)(MathHelper.lerp(partialTicks, this.prevPosX, this.posX) - vec3d.getX());
/*  99 */     float f1 = (float)(MathHelper.lerp(partialTicks, this.prevPosY, this.posY) - vec3d.getY());
/* 100 */     float f2 = (float)(MathHelper.lerp(partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());
/*     */     
/* 102 */     if (this.particleAngle == 0.0F) {
/*     */       
/* 104 */       quaternion = renderInfo.getRotation();
/*     */     }
/*     */     else {
/*     */       
/* 108 */       quaternion = new Quaternion(renderInfo.getRotation());
/* 109 */       quaternion.multiply(this.rotationVector.rotation(this.particleAngle));
/*     */     } 
/*     */     
/* 112 */     Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
/* 113 */     vector3f1.transform(quaternion);
/* 114 */     Vector3f[] avector3f = { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
/* 115 */     float scale = getScale(partialTicks);
/*     */     
/* 117 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 119 */       Vector3f vector3f = avector3f[i];
/* 120 */       vector3f.transform(quaternion);
/* 121 */       vector3f.mul(scale);
/* 122 */       vector3f.add(f, f1, f2);
/*     */     } 
/*     */     
/* 125 */     float minU = getMinU();
/* 126 */     float maxU = getMaxU();
/* 127 */     float minV = getMinV();
/* 128 */     float maxV = getMaxV();
/* 129 */     int brightness = getBrightnessForRender(partialTicks);
/* 130 */     buffer.pos(avector3f[0].getX(), avector3f[0].getY(), avector3f[0].getZ()).tex(maxU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
/* 131 */     buffer.pos(avector3f[1].getX(), avector3f[1].getY(), avector3f[1].getZ()).tex(maxU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
/* 132 */     buffer.pos(avector3f[2].getX(), avector3f[2].getY(), avector3f[2].getZ()).tex(minU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
/* 133 */     buffer.pos(avector3f[3].getX(), avector3f[3].getY(), avector3f[3].getZ()).tex(minU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightness).endVertex();
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleAlpha(float f) {
/* 138 */     setAlphaF(f);
/* 139 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleScale(float f) {
/* 144 */     this.particleScale = f;
/* 145 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleRotation(float f) {
/* 150 */     this.rotationSpeed = f;
/* 151 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleGravity(float f) {
/* 156 */     this.particleGravity = f;
/* 157 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setParticleAge(int i) {
/* 162 */     this.maxAge = i + this.rand.nextInt(10);
/* 163 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setHasMotionDecay(boolean flag) {
/* 168 */     this.hasMotionDecay = flag;
/* 169 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setRotation(Vector3f vec) {
/* 174 */     this.rotationVector = vec;
/* 175 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleParticle setHasScaleDecay(boolean flag) {
/* 180 */     this.hasScaleDecay = flag;
/* 181 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getPos() {
/* 186 */     return new BlockPos(this.posX, this.posY, this.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IParticleRenderType getRenderType() {
/* 192 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMaxU() {
/* 198 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMaxV() {
/* 204 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMinU() {
/* 210 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMinV() {
/* 216 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IParticleFactory<GenericParticleData>
/*     */   {
/*     */     private IParticleRenderType type;
/*     */     
/*     */     public Factory(ResourceLocation res) {
/* 225 */       this.type = new CustomParticleRenderType(res);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Particle makeParticle(GenericParticleData data, World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
/* 231 */       SimpleParticle particle = new SimpleParticle(this.type, world, posX, posY, posZ, data.getMotionX(), data.getMotionY(), data.getMotionZ());
/* 232 */       particle.setColor(data.getRed(), data.getGreen(), data.getBlue());
/* 233 */       particle.setRotation(new Vector3f(data.getRotX(), data.getRotY(), data.getRotZ()));
/* 234 */       particle.setParticleAlpha(data.getAlpha());
/* 235 */       particle.setParticleScale(data.getSize() / 10.0F);
/* 236 */       particle.setParticleRotation(data.getRotationSpeed());
/* 237 */       particle.setParticleAge(data.getLife());
/* 238 */       particle.setHasMotionDecay(data.hasMotionDecay());
/* 239 */       particle.setHasScaleDecay(data.hasScaleDecay());
/*     */       
/* 241 */       if ((Minecraft.getInstance()).player != null)
/*     */       {
/* 243 */         if (data.getEntityID() == (Minecraft.getInstance()).player.getEntityId()) {
/*     */           
/* 245 */           if ((Minecraft.getInstance()).gameSettings.thirdPersonView == 0 && data.getHideTooClose())
/* 246 */             particle.setParticleAlpha(ClientConfig.INSTANCE.getFireVisibility() / 100.0F); 
/* 247 */         } else if (data.getHideFromOthers()) {
/* 248 */           particle.setParticleAlpha(0.0F);
/*     */         } 
/*     */       }
/* 251 */       return (Particle)particle;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\SimpleParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */