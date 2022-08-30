/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities.renderers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class GlowingAbilityProjectileRenderer<T extends AbilityProjectileEntity>
/*     */   extends EntityRenderer<T>
/*     */ {
/*  33 */   private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
/*     */   
/*     */   private float red;
/*     */   private float blue;
/*     */   private float green;
/*     */   
/*     */   public GlowingAbilityProjectileRenderer(EntityRendererManager renderManager, EntityModel model) {
/*  40 */     super(renderManager);
/*  41 */     this.model = model;
/*     */   }
/*     */   private float alpha; private EntityModel model; private ResourceLocation texture;
/*     */   
/*     */   public void setTexture(ResourceLocation res) {
/*  46 */     this.texture = res;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(double red, double green, double blue, double alpha) {
/*  51 */     this.red = (float)red;
/*  52 */     this.green = (float)green;
/*  53 */     this.blue = (float)blue;
/*  54 */     this.alpha = (float)alpha;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(double scaleX, double scaleY, double scaleZ) {
/*  59 */     this.scaleX = (float)scaleX;
/*  60 */     this.scaleY = (float)scaleY;
/*  61 */     this.scaleZ = (float)scaleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3d getScale() {
/*  66 */     return new Vec3d(this.scaleX, this.scaleY, this.scaleZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*     */     RenderType type;
/*  72 */     if (((AbilityProjectileEntity)entity).ticksExisted < 2) {
/*     */       return;
/*     */     }
/*  75 */     if (entity instanceof IFlexibleSizeProjectile)
/*     */     {
/*  77 */       setScale(((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize());
/*     */     }
/*     */     
/*  80 */     matrixStack.push();
/*     */     
/*  82 */     matrixStack.rotate(new Quaternion(Vector3f.YP, ((AbilityProjectileEntity)entity).prevRotationYaw + (((AbilityProjectileEntity)entity).rotationYaw - ((AbilityProjectileEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/*  83 */     matrixStack.rotate(new Quaternion(Vector3f.XP, ((AbilityProjectileEntity)entity).prevRotationPitch + (((AbilityProjectileEntity)entity).rotationPitch - ((AbilityProjectileEntity)entity).prevRotationPitch) * partialTicks, true));
/*  84 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*     */     
/*  86 */     matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
/*     */ 
/*     */ 
/*     */     
/*  90 */     ResourceLocation finalTexture = getEntityTexture((AbilityProjectileEntity)entity);
/*     */     
/*  92 */     if (finalTexture == null) {
/*  93 */       type = ModRenderTypes.getEnergyRenderType();
/*     */     } else {
/*  95 */       type = RenderType.getEntityTranslucent(finalTexture);
/*     */     } 
/*  97 */     if (this.model != null) {
/*  98 */       this.model.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, this.red, this.green, this.blue, this.alpha);
/*     */     }
/* 100 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(AbilityProjectileEntity entity) {
/* 106 */     if (entity.getThrower() != null && entity.isAffectedByHaki()) {
/*     */       
/* 108 */       boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
/* 109 */       if (hardeningCheck) {
/* 110 */         return ModResources.BUSOSHOKU_HAKI_ARM;
/*     */       }
/*     */     } 
/* 113 */     return this.texture;
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<AbilityProjectileEntity> {
/* 118 */     protected EntityModel model = (EntityModel)new CubeModel();
/* 119 */     protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
/* 120 */     protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
/*     */     
/*     */     protected ResourceLocation texture;
/*     */     
/*     */     public Factory(EntityModel model) {
/* 125 */       this.model = model;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(String textureName) {
/* 130 */       this.texture = new ResourceLocation(APIConfig.projectId, "textures/models/projectiles/" + textureName + ".png");
/* 131 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(ResourceLocation location) {
/* 136 */       this.texture = location;
/* 137 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(double red, double green, double blue, double alpha) {
/* 142 */       this.red = red;
/* 143 */       this.green = green;
/* 144 */       this.blue = blue;
/* 145 */       this.alpha = alpha;
/* 146 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(String hex) {
/* 151 */       Color color = WyHelper.hexToRGB(hex);
/* 152 */       this.red = color.getRed();
/* 153 */       this.green = color.getGreen();
/* 154 */       this.blue = color.getBlue();
/* 155 */       this.alpha = color.getAlpha();
/* 156 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setAlpha(double alpha) {
/* 161 */       this.alpha = alpha;
/* 162 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scale) {
/* 167 */       this.scaleX = this.scaleY = this.scaleZ = scale;
/* 168 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scaleX, double scaleY, double scaleZ) {
/* 173 */       this.scaleX = scaleX;
/* 174 */       this.scaleY = scaleY;
/* 175 */       this.scaleZ = scaleZ;
/* 176 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
/* 182 */       GlowingAbilityProjectileRenderer<AbilityProjectileEntity> renderer = new GlowingAbilityProjectileRenderer<>(manager, this.model);
/* 183 */       renderer.setTexture(this.texture);
/* 184 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 185 */       renderer.setColor(this.red, this.green, this.blue, this.alpha);
/* 186 */       return renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\renderers\GlowingAbilityProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */