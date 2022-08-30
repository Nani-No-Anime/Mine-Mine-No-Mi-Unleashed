/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class GinModel<T extends GinEntity> extends HumanoidModel<T> {
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 19 */     this.bipedRightArm.rotateAngleZ = (float)(this.bipedRightArm.rotateAngleZ + 0.1D);
/* 20 */     this.bipedLeftArm.rotateAngleZ = (float)(this.bipedLeftArm.rotateAngleZ - 0.1D);
/*    */ 
/*    */     
/* 23 */     if (entity.getAnimation() == OPEntity.Animation.BLOCK.getId()) {
/*    */       
/* 25 */       this.bipedRightArm.rotateAngleX = -2.4F;
/* 26 */       this.bipedRightArm.rotateAngleZ = 0.7F;
/* 27 */       this.bipedLeftArm.rotateAngleX = -2.4F;
/* 28 */       this.bipedLeftArm.rotateAngleZ = -0.7F;
/*    */     }
/* 30 */     else if (entity.getAnimation() == OPEntity.Animation.FLINTLOCK_POINTING.getId()) {
/*    */       
/* 32 */       if (this.animationTimer < 1.5F) {
/* 33 */         this.animationTimer = (float)(this.animationTimer + 0.05D);
/*    */       }
/* 35 */       if (this.animationTimer >= 0.5F) {
/* 36 */         entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
/*    */       }
/* 38 */     } else if (entity.getAnimation() == OPEntity.Animation.NONE.getId()) {
/*    */       
/* 40 */       entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
/* 41 */       this.animationTimer = 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\GinModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */