/*    */ package xyz.pixelatedw.mineminenomi.renderers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockRenderType;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.RenderTypeLookup;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.texture.AtlasTexture;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.ILightReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.ForgeHooksClient;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.TaktBlockEntity;
/*    */ 
/*    */ public class TaktBlockRenderer extends EntityRenderer<TaktBlockEntity> {
/*    */   public TaktBlockRenderer(EntityRendererManager renderManager) {
/* 27 */     super(renderManager);
/* 28 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(TaktBlockEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
/* 34 */     BlockState blockstate = entityIn.getBlockState();
/* 35 */     if (blockstate.getRenderType() == BlockRenderType.MODEL) {
/*    */       
/* 37 */       World world = entityIn.getWorldObj();
/* 38 */       if (blockstate != world.getBlockState(new BlockPos((Entity)entityIn)) && blockstate.getRenderType() != BlockRenderType.INVISIBLE) {
/*    */         
/* 40 */         matrixStackIn.push();
/* 41 */         BlockPos blockpos = new BlockPos(entityIn.getPosX(), (entityIn.getBoundingBox()).maxY, entityIn.getPosZ());
/* 42 */         matrixStackIn.translate(-0.5D, 0.0D, -0.5D);
/* 43 */         BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
/* 44 */         for (RenderType type : RenderType.getBlockRenderTypes()) {
/*    */           
/* 46 */           if (RenderTypeLookup.canRenderInLayer(blockstate, type)) {
/*    */             
/* 48 */             ForgeHooksClient.setRenderLayer(type);
/* 49 */             blockrendererdispatcher.getBlockModelRenderer().renderModel((ILightReader)world, blockrendererdispatcher.getModelForState(blockstate), blockstate, blockpos, matrixStackIn, bufferIn.getBuffer(type), false, new Random(), blockstate.getPositionRandom(entityIn.getOrigin()), OverlayTexture.NO_OVERLAY);
/*    */           } 
/*    */         } 
/* 52 */         ForgeHooksClient.setRenderLayer(null);
/* 53 */         matrixStackIn.pop();
/* 54 */         super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(TaktBlockEntity entity) {
/* 62 */     return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory
/*    */     implements IRenderFactory<TaktBlockEntity>
/*    */   {
/*    */     public EntityRenderer<? super TaktBlockEntity> createRenderFor(EntityRendererManager manager) {
/* 70 */       return new TaktBlockRenderer(manager);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\TaktBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */