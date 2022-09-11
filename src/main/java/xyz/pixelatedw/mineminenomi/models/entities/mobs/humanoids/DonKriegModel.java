package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

@OnlyIn(Dist.CLIENT)
public class DonKriegModel<T extends DonKriegEntity> extends HumanoidModel<T> {
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    if (Minecraft.getInstance().isGamePaused()) {
      return;
    }
    
    if (entity.getAnimation() == 120 || entity.getAnimation() == 121) {
      
      if (this.animationTimer < 1.5F) {
        this.animationTimer = (float)(this.animationTimer + 0.05D);
      }
      if (this.animationTimer >= 0.5F && entity.getAnimation() == 120) {
        
        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
        entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
      } 
      
      this.bipedRightArm.rotateAngleX -= this.animationTimer;
      this.bipedLeftArm.rotateAngleX -= this.animationTimer;
    }
    else if (entity.getAnimation() <= OPEntity.Animation.NONE.ordinal()) {
      
      if (entity.isDaisensoActive()) {
        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.DAISENSO));
      } else {
        entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
      }  entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
      this.animationTimer = 0.0F;
    } 
  }
}


