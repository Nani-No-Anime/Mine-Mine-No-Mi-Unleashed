/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities.renderers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
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
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class AbilityProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
/*     */   extends EntityRenderer<T>
/*     */ {
/*  38 */   private float scaleX = 1.0F; private float scaleY = 1.0F; private float scaleZ = 1.0F;
/*     */   
/*     */   private float red;
/*     */   
/*     */   private float blue;
/*     */   private float green;
/*     */   private float alpha;
/*     */   
/*     */   public AbilityProjectileRenderer(EntityRendererManager renderManager, M model) {
/*  47 */     super(renderManager);
/*  48 */     this.model = model;
/*     */   }
/*     */   protected M model; private ResourceLocation texture; private boolean usePlayerTexture; private boolean isGlowing;
/*     */   
/*     */   public void setTexture(ResourceLocation res) {
/*  53 */     this.texture = res;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerTexture(boolean flag) {
/*  58 */     this.usePlayerTexture = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(double red, double green, double blue, double alpha) {
/*  63 */     this.red = (float)(red / 255.0D);
/*  64 */     this.green = (float)(green / 255.0D);
/*  65 */     this.blue = (float)(blue / 255.0D);
/*  66 */     this.alpha = (float)(alpha / 255.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGlowing(boolean flag) {
/*  71 */     this.isGlowing = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGlowing() {
/*  76 */     return this.isGlowing;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(double scaleX, double scaleY, double scaleZ) {
/*  81 */     this.scaleX = (float)scaleX;
/*  82 */     this.scaleY = (float)scaleY;
/*  83 */     this.scaleZ = (float)scaleZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3d getScale() {
/*  88 */     return new Vec3d(this.scaleX, this.scaleY, this.scaleZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getRGB() {
/*  93 */     return new Color(this.red, this.green, this.blue, this.alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*     */     RenderType type;
/*  99 */     if (((AbilityProjectileEntity)entity).ticksExisted < 2) {
/*     */       return;
/*     */     }
/* 102 */     if (entity instanceof IFlexibleSizeProjectile) {
/* 103 */       setScale(((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize(), ((IFlexibleSizeProjectile)entity).getSize());
/*     */     }
/* 105 */     matrixStack.push();
/*     */     
/* 107 */     matrixStack.rotate(new Quaternion(Vector3f.YP, ((AbilityProjectileEntity)entity).prevRotationYaw + (((AbilityProjectileEntity)entity).rotationYaw - ((AbilityProjectileEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/* 108 */     matrixStack.rotate(new Quaternion(Vector3f.XP, ((AbilityProjectileEntity)entity).prevRotationPitch + (((AbilityProjectileEntity)entity).rotationPitch - ((AbilityProjectileEntity)entity).prevRotationPitch) * partialTicks, true));
/* 109 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*     */     
/* 111 */     matrixStack.scale(this.scaleX, this.scaleY, this.scaleZ);
/*     */ 
/*     */     
/* 114 */     ResourceLocation finalTexture = getEntityTexture((AbilityProjectileEntity)entity);
/* 115 */     if (finalTexture == null) {
/* 116 */       type = isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
/*     */     } else {
/* 118 */       type = RenderType.getEntityTranslucent(finalTexture);
/*     */     } 
/* 120 */     boolean isSlim = false;
/* 121 */     if (entity.getThrower() instanceof net.minecraft.client.entity.player.ClientPlayerEntity) {
/* 122 */       isSlim = ((AbstractClientPlayerEntity)entity.getThrower()).getSkinType().equals("slim");
/*     */     }
/* 124 */     if (isSlim && this.model instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel) {
/*     */       
/*     */       try {
/*     */         
/* 128 */         this.model = (M)this.model.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
/*     */       }
/* 130 */       catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/*     */         
/* 132 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 136 */     if (this.model != null) {
/* 137 */       this.model.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
/*     */     }
/* 139 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(AbilityProjectileEntity entity) {
/* 145 */     if (entity.getThrower() != null && entity.isAffectedByHaki()) {
/*     */       
/* 147 */       boolean hardeningCheck = (entity.isAffectedByHardening() && HakiHelper.hasHardeningActive(entity.getThrower()));
/* 148 */       boolean imbuingCheck = (entity.isAffectedByImbuing() && HakiHelper.hasImbuingActive(entity.getThrower()));
/* 149 */       if (hardeningCheck || imbuingCheck) {
/* 150 */         return ModResources.BUSOSHOKU_HAKI_ARM;
/*     */       }
/*     */     } 
/* 153 */     if (this.usePlayerTexture && entity.getThrower() != null && entity.getThrower() instanceof PlayerEntity) {
/*     */       
/* 155 */       PlayerEntity player = entity.world.getPlayerByUuid(entity.getThrower().getUniqueID());
/* 156 */       if (player != null) {
/* 157 */         return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*     */       }
/* 159 */       return DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
/*     */     } 
/*     */     
/* 162 */     return this.texture;
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<AbilityProjectileEntity> {
/* 167 */     protected EntityModel model = (EntityModel)new CubeModel();
/* 168 */     protected double scaleX = 1.0D, scaleY = 1.0D, scaleZ = 1.0D;
/* 169 */     protected double red = 255.0D; protected double green = 255.0D; protected double blue = 255.0D; protected double alpha = 255.0D;
/*     */     
/*     */     protected ResourceLocation texture;
/*     */     protected boolean usePlayerTexture;
/*     */     protected boolean isGlowing = false;
/*     */     
/*     */     public Factory(EntityModel model) {
/* 176 */       this.model = model;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(String textureName) {
/* 181 */       this.texture = new ResourceLocation(APIConfig.projectId, "textures/models/projectiles/" + textureName + ".png");
/* 182 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setTexture(ResourceLocation location) {
/* 187 */       this.texture = location;
/* 188 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setPlayerTexture() {
/* 193 */       this.usePlayerTexture = true;
/* 194 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setGlowing() {
/* 199 */       this.isGlowing = true;
/* 200 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(double red, double green, double blue, double alpha) {
/* 205 */       this.red = red;
/* 206 */       this.green = green;
/* 207 */       this.blue = blue;
/* 208 */       this.alpha = alpha;
/* 209 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setColor(String hex) {
/* 214 */       Color color = WyHelper.hexToRGB(hex);
/* 215 */       this.red = color.getRed();
/* 216 */       this.green = color.getGreen();
/* 217 */       this.blue = color.getBlue();
/* 218 */       this.alpha = color.getAlpha();
/* 219 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setAlpha(double alpha) {
/* 224 */       this.alpha = alpha;
/* 225 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scale) {
/* 230 */       this.scaleX = this.scaleY = this.scaleZ = scale;
/* 231 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setScale(double scaleX, double scaleY, double scaleZ) {
/* 236 */       this.scaleX = scaleX;
/* 237 */       this.scaleY = scaleY;
/* 238 */       this.scaleZ = scaleZ;
/* 239 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
/* 245 */       AbilityProjectileRenderer<AbilityProjectileEntity, EntityModel<AbilityProjectileEntity>> renderer = new AbilityProjectileRenderer<>(manager, this.model);
/* 246 */       renderer.setTexture(this.texture);
/* 247 */       renderer.setPlayerTexture(this.usePlayerTexture);
/* 248 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 249 */       renderer.setColor(this.red, this.green, this.blue, this.alpha);
/* 250 */       return renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\renderers\AbilityProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */