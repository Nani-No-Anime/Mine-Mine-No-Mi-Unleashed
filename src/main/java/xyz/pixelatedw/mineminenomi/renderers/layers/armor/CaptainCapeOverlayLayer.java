/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.ArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;
/*    */ 
/*    */ public class CaptainCapeOverlayLayer<T extends LivingEntity, M extends CaptainCapeModel<T>, A extends CaptainCapeModel<T>>
/*    */   extends ArmorLayer<T, M, A> {
/*    */   public CaptainCapeOverlayLayer(IEntityRenderer<T, M> entityRenderer) {
/* 23 */     super(entityRenderer, (A)new CaptainCapeModel<T>(), (A)new CaptainCapeModel<T>());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.CHEST);
/* 30 */     if (itemstack.getItem() != ModArmors.PIRATE_CAPTAIN_CAPE || !(this.modelArmor instanceof CaptainCapeModel)) {
/*    */       return;
/*    */     }
/* 33 */     this.modelArmor.cape.showModel = false;
/*    */     
/* 35 */     ExtendedWorldData worldProps = ExtendedWorldData.get(((LivingEntity)entity).world);
/* 36 */     Crew crew = worldProps.getCrewWithMember(entity.getUniqueID());
/* 37 */     if (crew != null) {
/*    */       
/* 39 */       matrixStack.push();
/*    */       
/* 41 */       double dist = entity.getDistanceSq(((LivingEntity)entity).prevPosX, ((LivingEntity)entity).prevPosY, ((LivingEntity)entity).prevPosZ);
/* 42 */       if (dist > 0.0D && dist <= 0.02D)
/* 43 */         dist += 0.02D; 
/* 44 */       double angle = MathHelper.clamp(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
/* 45 */       boolean isMoving = (dist > 0.02D);
/* 46 */       if (isMoving) {
/* 47 */         angle += (MathHelper.sin(MathHelper.lerp(limbSwing, ((LivingEntity)entity).prevDistanceWalkedModified, ((LivingEntity)entity).distanceWalkedModified)) * 4.0F);
/*    */       }
/* 49 */       if (angle <= 0.0D) {
/* 50 */         matrixStack.translate(0.5D, 0.1D, 0.18D);
/* 51 */       } else if (angle > 0.0D && angle < 66.0D) {
/* 52 */         matrixStack.translate(0.5D, 0.01D, 0.25D);
/* 53 */       } else if (angle >= 66.0D) {
/* 54 */         matrixStack.translate(0.5D, -0.01D, 0.25D);
/*    */       } 
/* 56 */       if (entity.isSneaking() && ((LivingEntity)entity).onGround) {
/*    */         
/* 58 */         angle += 29.0D;
/* 59 */         matrixStack.translate(0.0D, 0.1D, 0.01D);
/* 60 */         if (angle > 35.0D) {
/*    */           
/* 62 */           angle--;
/* 63 */           matrixStack.translate(0.0D, 0.1D, 0.09D);
/*    */         } 
/*    */       } 
/*    */       
/* 67 */       matrixStack.rotate(Vector3f.XP.rotationDegrees((float)angle));
/* 68 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
/*    */       
/* 70 */       RendererHelper.drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer);
/* 71 */       matrixStack.pop();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
/* 78 */     setModelVisible(model);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelVisible(A model) {
/* 88 */     model.setVisible(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\CaptainCapeOverlayLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */