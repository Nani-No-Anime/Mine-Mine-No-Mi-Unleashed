/*    */ package xyz.pixelatedw.mineminenomi.api.morph;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public abstract class ZoanMorphModel<T extends LivingEntity>
/*    */   extends PlayerModel<T>
/*    */ {
/*    */   public ZoanMorphModel(float modelSize, boolean hasSmallArms) {
/* 18 */     super(modelSize, hasSmallArms);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZoanMorphModel(float modelSize) {
/* 23 */     this(modelSize, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void renderFirstPersonArm(MatrixStack paramMatrixStack, IVertexBuilder paramIVertexBuilder, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, HandSide paramHandSide);
/*    */   
/*    */   public abstract void renderFirstPersonLeg(MatrixStack paramMatrixStack, IVertexBuilder paramIVertexBuilder, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, HandSide paramHandSide);
/*    */   
/*    */   public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
/* 32 */     ModelRenderer modelrenderer = getArmForSide(side);
/* 33 */     modelrenderer.translateRotate(matrixStack);
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\morph\ZoanMorphModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */