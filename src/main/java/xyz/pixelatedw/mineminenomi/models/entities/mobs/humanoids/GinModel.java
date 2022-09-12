package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

@OnlyIn(Dist.CLIENT)
public class GinModel<T extends GinEntity> extends HumanoidModel<T> {
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    this.bipedRightArm.rotateAngleZ = (float)(this.bipedRightArm.rotateAngleZ + 0.1D);
    this.bipedLeftArm.rotateAngleZ = (float)(this.bipedLeftArm.rotateAngleZ - 0.1D);

    
    if (entity.getAnimation() == OPEntity.Animation.BLOCK.getId()) {
      
      this.bipedRightArm.rotateAngleX = -2.4F;
      this.bipedRightArm.rotateAngleZ = 0.7F;
      this.bipedLeftArm.rotateAngleX = -2.4F;
      this.bipedLeftArm.rotateAngleZ = -0.7F;
    }
    else if (entity.getAnimation() == OPEntity.Animation.FLINTLOCK_POINTING.getId()) {
      
      if (this.animationTimer < 1.5F) {
        this.animationTimer = (float)(this.animationTimer + 0.05D);
      }
      if (this.animationTimer >= 0.5F) {
        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
      }
    } else if (entity.getAnimation() == OPEntity.Animation.NONE.getId()) {
      
      entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
      this.animationTimer = 0.0F;
    } 
  }
}


