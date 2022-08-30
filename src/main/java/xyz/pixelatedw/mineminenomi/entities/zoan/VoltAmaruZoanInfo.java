/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.goro.VoltAmaruAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.HitoDaibutsuModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GlowingModelRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class VoltAmaruZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  25 */   public static final VoltAmaruZoanInfo INSTANCE = new VoltAmaruZoanInfo();
/*     */   
/*  27 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.4F, 6.5F);
/*  28 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.4F, 6.2F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  34 */     return (IRenderFactory)new GlowingModelRenderer.Factory(this, GlowingModelRenderer.Type.AMARU);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  41 */     return (ZoanMorphModel)new HitoDaibutsuModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/*  50 */     matrixStack.scale(0.5F, 0.55F, 0.5F);
/*  51 */     matrixStack.translate(0.0D, -1.5D, 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
/*  57 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  63 */     return "volt_amaru";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  69 */     return (Ability)VoltAmaruAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  75 */     return 6.300000190734863D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  81 */     return 1.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(PlayerEntity player) {
/*  88 */     return 12.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 100 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 101 */       .put(Pose.STANDING, STANDING_SIZE)
/* 102 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 103 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\VoltAmaruZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */