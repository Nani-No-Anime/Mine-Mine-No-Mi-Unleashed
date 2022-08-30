/*     */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusHeavyPointAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.AllosaurusHeavyPartialModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial.AllosaurusHeavyPartialMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AllosaurusHeavyZoanInfo
/*     */   extends ZoanInfo
/*     */ {
/*  27 */   public static final AllosaurusHeavyZoanInfo INSTANCE = new AllosaurusHeavyZoanInfo();
/*     */   
/*  29 */   private static final EntitySize STANDING_SIZE = EntitySize.flexible(0.9F, 2.8F);
/*  30 */   private static final EntitySize CROUCHING_SIZE = EntitySize.flexible(0.9F, 2.6F);
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  36 */     boolean isSlim = entity.getSkinType().equals("slim");
/*  37 */     return (IRenderFactory)new AllosaurusHeavyPartialMorphRenderer.Factory(this, isSlim);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public ZoanMorphModel getModel() {
/*  44 */     return (ZoanMorphModel)new AllosaurusHeavyPartialModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  51 */     return ModAbilities.RYU_RYU_NO_MI_ALLOSAURUS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPartial() {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonLeg() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {
/*  78 */     float scale = 1.4F;
/*  79 */     matrixStack.scale(scale, scale, scale);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getForm() {
/*  85 */     return "heavy";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability getAbility() {
/*  91 */     return (Ability)AllosaurusHeavyPointAbility.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/*  97 */     return 2.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 103 */     return 0.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 109 */     return ImmutableMap.<Pose, EntitySize>builder()
/* 110 */       .put(Pose.STANDING, STANDING_SIZE)
/* 111 */       .put(Pose.CROUCHING, CROUCHING_SIZE)
/* 112 */       .build();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\AllosaurusHeavyZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */