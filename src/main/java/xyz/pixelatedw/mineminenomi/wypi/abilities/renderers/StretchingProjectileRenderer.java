/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities.renderers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class StretchingProjectileRenderer<T extends AbilityProjectileEntity, M extends EntityModel<T>>
/*     */   extends AbilityProjectileRenderer<T, M>
/*     */ {
/*     */   private M stretchModel;
/*  31 */   private float stretchScaleX = 1.0F, stretchScaleY = 1.0F, stretchScaleZ = 1.0F;
/*     */ 
/*     */   
/*     */   public StretchingProjectileRenderer(EntityRendererManager renderManager, M model, M stretchModel) {
/*  35 */     super(renderManager, model);
/*  36 */     this.stretchModel = stretchModel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStretchScale(double scaleX, double scaleY, double scaleZ) {
/*  41 */     this.stretchScaleX = (float)scaleX;
/*  42 */     this.stretchScaleY = (float)scaleY;
/*  43 */     this.stretchScaleZ = (float)scaleZ;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  49 */     if (((AbilityProjectileEntity)entity).ticksExisted < 2) {
/*     */       return;
/*     */     }
/*  52 */     if (entity.getThrower() == null || !entity.getThrower().isAlive()) {
/*     */       
/*  54 */       entity.remove();
/*     */       
/*     */       return;
/*     */     } 
/*  58 */     Direction dir = Direction.fromAngle((entity.getThrower()).rotationYaw);
/*  59 */     Vec3d originPos = entity.getThrower().getPositionVec().add(dir.getXOffset() * 1.5D, 0.0D, dir.getZOffset() * 1.5D);
/*     */ 
/*     */ 
/*     */     
/*  63 */     Vec3d entityPos = new Vec3d(MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosX, entity.getPosX()), MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosY, entity.getPosY()), MathHelper.lerp(partialTicks, ((AbilityProjectileEntity)entity).prevPosZ, entity.getPosZ()));
/*     */     
/*  65 */     Vec3d stretchVec = entityPos.subtract(originPos);
/*     */     
/*  67 */     if (this.stretchModel != null) {
/*     */       RenderType type;
/*  69 */       matrixStack.push();
/*     */       
/*  71 */       matrixStack.rotate(new Quaternion(Vector3f.YP, ((AbilityProjectileEntity)entity).prevRotationYaw + (((AbilityProjectileEntity)entity).rotationYaw - ((AbilityProjectileEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
/*  72 */       matrixStack.rotate(new Quaternion(Vector3f.XP, ((AbilityProjectileEntity)entity).prevRotationPitch + (((AbilityProjectileEntity)entity).rotationPitch - ((AbilityProjectileEntity)entity).prevRotationPitch) * partialTicks, true));
/*  73 */       matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*     */       
/*  75 */       float modelLength = this.stretchScaleZ / 16.0F;
/*  76 */       float modelOffset = 0.25F;
/*  77 */       float stretchLength = (float)stretchVec.length();
/*  78 */       matrixStack.translate(0.0D, 0.0D, -modelOffset);
/*  79 */       matrixStack.scale(this.stretchScaleX, this.stretchScaleY, (stretchLength + 2.0F * modelOffset) / modelLength);
/*  80 */       matrixStack.translate(0.0D, 0.0D, modelOffset);
/*     */ 
/*     */       
/*  83 */       ResourceLocation finalTexture = getEntityTexture((AbilityProjectileEntity)entity);
/*  84 */       if (finalTexture == null) {
/*  85 */         type = isGlowing() ? ModRenderTypes.getEnergyRenderType() : ModRenderTypes.TRANSPARENT_COLOR;
/*     */       } else {
/*  87 */         type = RenderType.getEntityTranslucent(finalTexture);
/*     */       } 
/*  89 */       boolean isSlim = false;
/*  90 */       if (entity.getThrower() instanceof net.minecraft.client.entity.player.ClientPlayerEntity) {
/*  91 */         isSlim = ((AbstractClientPlayerEntity)entity.getThrower()).getSkinType().equals("slim");
/*     */       }
/*  93 */       if (isSlim && this.stretchModel instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel) {
/*     */         
/*     */         try {
/*     */           
/*  97 */           this.stretchModel = (M)this.stretchModel.getClass().getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(isSlim) });
/*     */         }
/*  99 */         catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/*     */           
/* 101 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */       
/* 105 */       this.stretchModel.render(matrixStack, buffer.getBuffer(type), packedLight, OverlayTexture.NO_OVERLAY, getRGB().getRed() / 255.0F, getRGB().getGreen() / 255.0F, getRGB().getBlue() / 255.0F, getRGB().getAlpha() / 255.0F);
/*     */       
/* 107 */       matrixStack.pop();
/*     */     } 
/*     */     
/* 110 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     extends AbilityProjectileRenderer.Factory {
/*     */     private EntityModel stretchModel;
/* 116 */     protected double stretchScaleX = 1.0D, stretchScaleY = 1.0D, stretchScaleZ = 8.0D;
/*     */ 
/*     */     
/*     */     public Factory(EntityModel stretchModel) {
/* 120 */       super(null);
/* 121 */       this.stretchModel = stretchModel;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(EntityModel tipModel, EntityModel stretchModel) {
/* 126 */       super(tipModel);
/* 127 */       this.stretchModel = stretchModel;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setStretchScale(double scaleX, double scaleY) {
/* 132 */       return setStretchScale(scaleX, scaleY, 8.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory setStretchScale(double scaleX, double scaleY, double scaleZ) {
/* 137 */       this.stretchScaleX = scaleX;
/* 138 */       this.stretchScaleY = scaleY;
/* 139 */       this.stretchScaleZ = scaleZ;
/* 140 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
/* 146 */       StretchingProjectileRenderer<AbilityProjectileEntity, EntityModel<AbilityProjectileEntity>> renderer = new StretchingProjectileRenderer<>(manager, this.model, this.stretchModel);
/* 147 */       renderer.setTexture(this.texture);
/* 148 */       renderer.setStretchScale(this.stretchScaleX, this.stretchScaleY, this.stretchScaleZ);
/* 149 */       renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
/* 150 */       renderer.setColor(this.red, this.green, this.blue, this.alpha);
/* 151 */       renderer.setPlayerTexture(this.usePlayerTexture);
/* 152 */       renderer.setGlowing(this.isGlowing);
/* 153 */       return renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\renderers\StretchingProjectileRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */