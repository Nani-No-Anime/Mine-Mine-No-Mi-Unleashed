/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Quaternion;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class PaperFloatLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 25 */   private CubeModel model = new CubeModel();
/*    */ 
/*    */   
/*    */   public PaperFloatLayer(IEntityRenderer renderer) {
/* 29 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 35 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/* 36 */     Ability ability = abilityData.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
/* 37 */     boolean hasAbility = (ability != null && ability.isContinuous());
/* 38 */     boolean hasPaper = (entity.getHeldItemMainhand().getItem() == Items.PAPER || entity.getHeldItemOffhand().getItem() == Items.PAPER);
/* 39 */     boolean inAir = (!((LivingEntity)entity).onGround && (entity.getMotion()).y < 0.0D);
/*    */     
/* 41 */     if (hasAbility && hasPaper && inAir) {
/*    */       
/* 43 */       matrixStack.translate(0.0D, -0.7D, 0.0D);
/* 44 */       matrixStack.rotate(new Quaternion(Vector3f.YP, -((LivingEntity)entity).rotationYaw, true));
/*    */       
/* 46 */       matrixStack.scale(2.4F, 0.5F, 2.5F);
/*    */       
/* 48 */       RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
/* 49 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
/* 50 */       this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\PaperFloatLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */