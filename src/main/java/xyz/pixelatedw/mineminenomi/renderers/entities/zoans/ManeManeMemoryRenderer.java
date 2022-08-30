/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.NoMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class ManeManeMemoryRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public ManeManeMemoryRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands) {
/* 27 */     super(rendererManager, info, hasSmallHands);
/* 28 */     this.entityModel = new NoMorphModel(hasSmallHands);
/* 29 */     addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 35 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 41 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
/* 42 */     if (ability != null && ability.isTransformationActive((LivingEntity)entity)) {
/*    */       
/* 44 */       UUID uuid = ability.getMemory().getUUID();
/* 45 */       PlayerEntity player = entity.world.getPlayerByUuid(uuid);
/* 46 */       if (player != null) {
/* 47 */         return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*    */       }
/* 49 */       return DefaultPlayerSkin.getDefaultSkin(uuid);
/*    */     } 
/*    */     
/* 52 */     return entity.getLocationSkin();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasSmallHands) {
/* 62 */       this.info = info;
/* 63 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 69 */       ManeManeMemoryRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new ManeManeMemoryRenderer<>(manager, this.info, this.hasSmallHands);
/* 70 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\ManeManeMemoryRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */