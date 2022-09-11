package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.BindLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class HumanoidRenderer<T extends MobEntity, M extends BipedModel<T>>
  extends BipedRenderer<T, M> {
  protected ResourceLocation texture;
  protected float[] scale;
  
  public HumanoidRenderer(EntityRendererManager manager, M model) {
    this(manager, model, (String)null);
  }

  
  public HumanoidRenderer(EntityRendererManager manager, M model, String tex) {
    this(manager, model, new float[] { 1.0F, 1.0F, 1.0F }, tex);
  }

  
  public HumanoidRenderer(EntityRendererManager manager, M model, float[] scale, String tex) {
    super(manager, model, (float)(0.5D * Math.pow(scale[0], 1.5D)));
    this.scale = scale;
    this.texture = new ResourceLocation("mineminenomi", "textures/models/" + tex + ".png");
    addLayer(new BodyCoatingLayer((IEntityRenderer)this));
    addLayer(new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
    addLayer(new PotionLayer((IEntityRenderer)this));
    addLayer(new AuraLayer((IEntityRenderer)this));
    addLayer(new HandcuffsLayer((IEntityRenderer)this));
    addLayer(new BindLayer((IEntityRenderer)this));
    addLayer(new HanaHandsLayer((IEntityRenderer)this));
  }


  
  public void preRenderCallback(T entity, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(this.scale[0], this.scale[1], this.scale[2]);
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    
    if (entity instanceof OPEntity)
    {
      if (getEntityModel() instanceof HumanoidModel) {
        
        ItemStack mainItemStack = entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        HumanoidModel model = (HumanoidModel)getEntityModel();
        int animation = ((OPEntity)entity).getAnimation();
        model.armsPose = HumanoidModel.HumanoidArmPose.EMPTY;

        
        if (mainItemStack.getItem() == ModWeapons.FLINTLOCK && animation == OPEntity.Animation.FLINTLOCK_POINTING.ordinal()) {
          model.armsPose = HumanoidModel.HumanoidArmPose.FLINTLOCK_POINTING;
        }
        
        if (mainItemStack.getItem() == ModWeapons.SENRIKU) {
          
          model.armsPose = HumanoidModel.HumanoidArmPose.SENRIKU_HOLDING;
          if (animation == OPEntity.Animation.FLINTLOCK_POINTING.ordinal()) {
            model.armsPose = HumanoidModel.HumanoidArmPose.SENRIKU_POINTING;
          }
        } 
        
        if (mainItemStack.getItem() == ModWeapons.BAZOOKA) {
          model.armsPose = HumanoidModel.HumanoidArmPose.CANNON_HOLDING;
        }
      } 
    }
  }

  
  protected void applyRotations(T entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
    super.applyRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    if ((this.texture == null && entity instanceof IDynamicRenderer) || this.texture.equals(new ResourceLocation("mineminenomi:textures/models/null.png"))) {
      
      String textureName = ((IDynamicRenderer)entity).getMobTexture();
      if (WyHelper.isNullOrEmpty(textureName))
        textureName = ((IDynamicRenderer)entity).getDefaultTexture(); 
      return new ResourceLocation("mineminenomi", "textures/models/" + textureName + ".png");
    } 
    
    return this.texture;
  }
  
  public static class Factory
    implements IRenderFactory
  {
    protected BipedModel model;
    private float[] scale;
    private String texture;
    
    public Factory(BipedModel model, float scale) {
      this(model, scale, scale, scale, null);
    }

    
    public Factory(BipedModel model, float scale, String texture) {
      this(model, scale, scale, scale, texture);
    }

    
    public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ) {
      this(model, scaleX, scaleY, scaleZ, null);
    }

    
    public Factory(BipedModel model, float scaleX, float scaleY, float scaleZ, String texture) {
      this.model = model;
      this.texture = texture;
      
      this.scale = new float[] { scaleX, scaleY, scaleZ };
    }


    
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new HumanoidRenderer<>(manager, this.model, this.scale, this.texture);
    }
  }
}


