/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class DonKriegModel<T extends DonKriegEntity> extends HumanoidModel<T> {
/*    */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 19 */     if (Minecraft.getInstance().isGamePaused()) {
/*    */       return;
/*    */     }
/*    */     
/* 23 */     if (entity.getAnimation() == 120 || entity.getAnimation() == 121) {
/*    */       
/* 25 */       if (this.animationTimer < 1.5F) {
/* 26 */         this.animationTimer = (float)(this.animationTimer + 0.05D);
/*    */       }
/* 28 */       if (this.animationTimer >= 0.5F && entity.getAnimation() == 120) {
/*    */         
/* 30 */         entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
/* 31 */         entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
/*    */       } 
/*    */       
/* 34 */       this.bipedRightArm.rotateAngleX -= this.animationTimer;
/* 35 */       this.bipedLeftArm.rotateAngleX -= this.animationTimer;
/*    */     }
/* 37 */     else if (entity.getAnimation() <= OPEntity.Animation.NONE.ordinal()) {
/*    */       
/* 39 */       if (entity.isDaisensoActive()) {
/* 40 */         entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.DAISENSO));
/*    */       } else {
/* 42 */         entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
/* 43 */       }  entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
/* 44 */       this.animationTimer = 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\DonKriegModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */