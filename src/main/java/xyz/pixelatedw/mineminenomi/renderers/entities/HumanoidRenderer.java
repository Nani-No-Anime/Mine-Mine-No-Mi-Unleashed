/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.BipedRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BindLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class HumanoidRenderer<T extends MobEntity, M extends BipedModel<T>>
/*     */   extends BipedRenderer<T, M> {
/*     */   protected ResourceLocation texture;
/*     */   protected float[] scale;
/*     */   
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model) {
/*  40 */     this(manager, model, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model, String tex) {
/*  45 */     this(manager, model, new float[] { 1.0F, 1.0F, 1.0F }, tex);
/*     */   }
/*     */ 
/*     */   
/*     */   public HumanoidRenderer(EntityRendererManager manager, M model, float[] scale, String tex) {
/*  50 */     super(manager, model, (float)(0.5D * Math.pow(scale[0], 1.5D)));
/*  51 */     this.scale = scale;
/*  52 */     this.texture = new ResourceLocation("mineminenomi", "textures/models/" + tex + ".png");
/*  53 */     addLayer(new BodyCoatingLayer((IEntityRenderer)this));
/*  54 */     addLayer(new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*  55 */     addLayer(new PotionLayer((IEntityRenderer)this));
/*  56 */     addLayer(new AuraLayer((IEntityRenderer)this));
/*  57 */     addLayer(new HandcuffsLayer((IEntityRenderer)this));
/*  58 */     addLayer(new BindLayer((IEntityRenderer)this));
/*  59 */     addLayer(new HanaHandsLayer((IEntityRenderer)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void preRenderCallback(T entity, MatrixStack matrixStack, float partialTickTime) {
/*  65 */     matrixStack.scale(this.scale[0], this.scale[1], this.scale[2]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  71 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */     
/*  73 */     if (entity instanceof OPEntity)
/*     */     {
/*  75 */       if (getEntityModel() instanceof HumanoidModel) {
/*     */         
/*  77 */         ItemStack mainItemStack = entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/*  78 */         HumanoidModel model = (HumanoidModel)getEntityModel();
/*  79 */         int animation = ((OPEntity)entity).getAnimation();
/*  80 */         model.armsPose = HumanoidModel.HumanoidArmPose.EMPTY;
/*     */ 
/*     */         
/*  83 */         if (mainItemStack.getItem() == ModWeapons.FLINTLOCK && animation == OPEntity.Animation.FLINTLOCK_POINTING.ordinal()) {
/*  84 */           model.armsPose = HumanoidModel.HumanoidArmPose.FLINTLOCK_POINTING;
/*     */         }
/*     */         
/*  87 */         if (mainItemStack.getItem() == ModWeapons.SENRIKU) {
/*     */           
/*  89 */           model.armsPose = HumanoidModel.HumanoidArmPose.SENRIKU_HOLDING;
/*  90 */           if (animation == OPEntity.Animation.FLINTLOCK_POINTING.ordinal()) {
/*  91 */             model.armsPose = HumanoidModel.HumanoidArmPose.SENRIKU_POINTING;
/*     */           }
/*     */         } 
/*     */         
/*  95 */         if (mainItemStack.getItem() == ModWeapons.BAZOOKA) {
/*  96 */           model.armsPose = HumanoidModel.HumanoidArmPose.CANNON_HOLDING;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyRotations(T entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 104 */     super.applyRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(T entity) {
/* 110 */     if ((this.texture == null && entity instanceof IDynamicRenderer) || this.texture.equals(new ResourceLocation("mineminenomi:textures/models/null.png"))) {
/*     */       
/* 112 */       String textureName = ((IDynamicRenderer)entity).getMobTexture();
/* 113 */       if (WyHelper.isNullOrEmpty(textureName))
/* 114 */         textureName = ((IDynamicRenderer)entity).getDefaultTexture(); 
/* 115 */       return new ResourceLocation("mineminenomi", "textures/models/" + textureName + ".png");
/*     */     } 
/*     */     
/* 118 */     return this.texture;
/*     */   }
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory
/*     */   {
/*     */     protected BipedModel model;
/*     */     private float[] scale;
/*     */     private String texture;
/*     */     
/*     */     public Factory(BipedModel model, float scale) {
/* 129 */       this(model, scale, scale, scale, null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scale, String texture) {
/* 134 */       this(model, scale, scale, scale, texture);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ) {
/* 139 */       this(model, scaleX, scaleY, scaleZ, null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ, String texture) {
/* 144 */       this.model = model;
/* 145 */       this.texture = texture;
/*     */       
/* 147 */       this.scale = new float[] { scaleX, scaleY, scaleZ };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer createRenderFor(EntityRendererManager manager) {
/* 153 */       return (EntityRenderer)new HumanoidRenderer<>(manager, this.model, this.scale, this.texture);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\HumanoidRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */