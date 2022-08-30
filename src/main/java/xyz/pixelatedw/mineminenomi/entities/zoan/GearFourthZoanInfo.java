/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.GearFourthModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.GearFourthRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ public class GearFourthZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  27 */   public static final GearFourthZoanInfo INSTANCE = new GearFourthZoanInfo();
/*     */   
/*  29 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(2.8F, 4.0F);
/*  30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(2.8F, 3.9F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  36 */     return (IRenderFactory)new GearFourthRenderer.Factory(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  43 */     return (ZoanMorphModel)new GearFourthModel(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float partialTickTime) {
/*  50 */     double x = entity.prevPosX - entity.getPosX();
/*  51 */     double z = entity.prevPosZ - entity.getPosZ();
/*  52 */     boolean isMoving = (x != 0.0D || z != 0.0D);
/*  53 */     if (isMoving) {
/*     */       
/*  55 */       matrixStack.translate(0.0D, -2.0D, 2.0D);
/*  56 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
/*  63 */     return entity.getLocationSkin();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  69 */     return ModAbilities.GOMU_GOMU_NO_MI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  75 */     return "gear_4th";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  81 */     return (Ability)GearFourthAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  87 */     return 4.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/*  93 */     return 1.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/*  99 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 100 */       .put(Pose.STANDING, STANDING_SIZE)
/* 101 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 102 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\GearFourthZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */