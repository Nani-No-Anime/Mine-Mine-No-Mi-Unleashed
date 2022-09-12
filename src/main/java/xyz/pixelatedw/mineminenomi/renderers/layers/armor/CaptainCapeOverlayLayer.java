package xyz.pixelatedw.mineminenomi.renderers.layers.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;

public class CaptainCapeOverlayLayer<T extends LivingEntity, M extends CaptainCapeModel<T>, A extends CaptainCapeModel<T>>
  extends ArmorLayer<T, M, A> {
  public CaptainCapeOverlayLayer(IEntityRenderer<T, M> entityRenderer) {
    super(entityRenderer, (A)new CaptainCapeModel<T>(), (A)new CaptainCapeModel<T>());
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.CHEST);
    if (itemstack.getItem() != ModArmors.PIRATE_CAPTAIN_CAPE || !(this.modelArmor instanceof CaptainCapeModel)) {
      return;
    }
    this.modelArmor.cape.showModel = false;
    
    ExtendedWorldData worldProps = ExtendedWorldData.get(((LivingEntity)entity).world);
    Crew crew = worldProps.getCrewWithMember(entity.getUniqueID());
    if (crew != null) {
      
      matrixStack.push();
      
      double dist = entity.getDistanceSq(((LivingEntity)entity).prevPosX, ((LivingEntity)entity).prevPosY, ((LivingEntity)entity).prevPosZ);
      if (dist > 0.0D && dist <= 0.02D)
        dist += 0.02D; 
      double angle = MathHelper.clamp(dist * 1000.0D - 1.0D, 0.0D, 70.0D);
      boolean isMoving = (dist > 0.02D);
      if (isMoving) {
        angle += (MathHelper.sin(MathHelper.lerp(limbSwing, ((LivingEntity)entity).prevDistanceWalkedModified, ((LivingEntity)entity).distanceWalkedModified)) * 4.0F);
      }
      if (angle <= 0.0D) {
        matrixStack.translate(0.5D, 0.1D, 0.18D);
      } else if (angle > 0.0D && angle < 66.0D) {
        matrixStack.translate(0.5D, 0.01D, 0.25D);
      } else if (angle >= 66.0D) {
        matrixStack.translate(0.5D, -0.01D, 0.25D);
      } 
      if (entity.isSneaking() && ((LivingEntity)entity).onGround) {
        
        angle += 29.0D;
        matrixStack.translate(0.0D, 0.1D, 0.01D);
        if (angle > 35.0D) {
          
          angle--;
          matrixStack.translate(0.0D, 0.1D, 0.09D);
        } 
      } 
      
      matrixStack.rotate(Vector3f.XP.rotationDegrees((float)angle));
      matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
      
      RendererHelper.drawPlayerJollyRoger(crew.getJollyRoger(), matrixStack, buffer);
      matrixStack.pop();
    } 
  }


  
  protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
    setModelVisible(model);
  }






  
  protected void setModelVisible(A model) {
    model.setVisible(false);
  }
}


