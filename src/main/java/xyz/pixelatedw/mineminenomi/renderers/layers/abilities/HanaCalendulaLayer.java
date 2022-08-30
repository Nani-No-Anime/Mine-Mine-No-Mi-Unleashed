/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.hana.VeinteFleurCalendulaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.HanaCalendulaModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class HanaCalendulaLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 23 */   private HanaCalendulaModel model = new HanaCalendulaModel();
/*    */ 
/*    */   
/*    */   public HanaCalendulaLayer(IEntityRenderer renderer) {
/* 27 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 33 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/* 34 */     Ability ability = abilityData.getEquippedAbility((Ability)VeinteFleurCalendulaAbility.INSTANCE);
/* 35 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*    */     
/* 37 */     if (hasAbility) {
/*    */       
/* 39 */       ResourceLocation skin = ((AbstractClientPlayerEntity)entity).getLocationSkin();
/* 40 */       RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 41 */       this.model.isSneak = entity.isCrouching();
/* 42 */       this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 43 */       this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\HanaCalendulaLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */