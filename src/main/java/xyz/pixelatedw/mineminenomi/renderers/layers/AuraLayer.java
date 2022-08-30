/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.OutlineLayerBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;

/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/*    */   public AuraLayer(IEntityRenderer renderer) {
/* 36 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 42 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/*    */     
/* 44 */     if (entity == clientPlayerEntity) {
/*    */       return;
/*    */     }
/* 47 */     IEntityStats entityData = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/* 48 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/* 49 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 51 */     Ability ability = abilityData.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
/* 52 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 54 */     if (isActive) {
/*    */       
/* 56 */       float dorikiPower = entityData.getDoriki() / 1000.0F;
/* 57 */       float hakiPower = hakiProps.getKenbunshokuHakiExp() / 2.0F;
/* 58 */       float finalPower = (dorikiPower + hakiPower) * 1.06F * (DevilFruitCapability.get((LivingEntity)clientPlayerEntity).hasDevilFruit(ModAbilities.GORO_GORO_NO_MI) ? 4 : 1);
/*    */       
/* 60 */       if (entity.getDistance(clientPlayerEntity) > finalPower) {
/*    */         return;
/*    */       }
/* 63 */       matrixStack.push();
/*    */       
/* 65 */       String color = "#5555FF";
/*    */       
/* 67 */       if (entity instanceof net.minecraft.entity.passive.AnimalEntity) {
/* 68 */         color = "#55FF55";
/* 69 */       } else if (entity instanceof net.minecraft.entity.monster.MonsterEntity) {
/* 70 */         color = "#FF0000";
/* 71 */       } else if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/* 72 */         color = "#00FFFF";
/*    */       } 
/* 74 */       OutlineLayerBuffer outline = Minecraft.getInstance().getRenderTypeBuffers().getOutlineBufferSource();
/* 75 */       Color rgbColor = WyHelper.hexToRGB(color);
/* 76 */       float red = rgbColor.getRed() / 255.0F;
/* 77 */       float green = rgbColor.getGreen() / 255.0F;
/* 78 */       float blue = rgbColor.getBlue() / 255.0F;
/* 79 */       outline.setColor((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), 200);
/* 80 */       IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(getEntityTexture(entity)));
/*    */       
/* 82 */       getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6F);
/*    */       
/* 84 */       matrixStack.pop();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\AuraLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */