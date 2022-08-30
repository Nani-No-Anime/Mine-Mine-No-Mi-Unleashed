/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.ShinokuniModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ShinokuniRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShinokuniZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  28 */   public static final ShinokuniZoanInfo INSTANCE = new ShinokuniZoanInfo();
/*     */   
/*  30 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.8F, 6.0F);
/*  31 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.8F, 5.9F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  37 */     return (IRenderFactory)new ShinokuniRenderer.Factory(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  44 */     return (ZoanMorphModel)new ShinokuniModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  51 */     matrixStack.scale(3.0F, 3.0F, 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture() {
/*  58 */     return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/shinokuni.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  64 */     return "shinokuni";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  70 */     return (Ability)ShinokuniAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  76 */     return 5.699999809265137D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  82 */     return 1.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(PlayerEntity player) {
/*  89 */     return 7.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 101 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 102 */       .put(Pose.STANDING, STANDING_SIZE)
/* 103 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 104 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ShinokuniZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */