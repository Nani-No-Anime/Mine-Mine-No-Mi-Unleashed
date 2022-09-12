package xyz.pixelatedw.mineminenomi.api.crew;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class JollyRogerElement
  extends ForgeRegistryEntry<JollyRogerElement>
{
  private boolean canBeColored = false;
  private String color = "#FFFFFF";
  private ResourceLocation texture;
  private List<ICanUse> canUseChecks = new ArrayList<>();
  
  private LayerType type;
  
  @Deprecated
  public JollyRogerElement(LayerType type) {
    this.type = type;
  }

  
  public JollyRogerElement(LayerType type, String texture) {
    this.type = type;
    String category = "bases";
    if (type == LayerType.BACKGROUND) {
      category = "backgrounds";
    } else if (type == LayerType.DETAIL) {
      category = "details";
    }  setTexture(new ResourceLocation("mineminenomi", "textures/jolly_rogers/" + category + "/" + texture + ".png"));
    ModJollyRogers.registerElement(this);
  }

  
  public boolean canBeColored() {
    return this.canBeColored;
  }

  
  public JollyRogerElement setCanBeColored() {
    this.canBeColored = true;
    return this;
  }

  
  public String getColor() {
    return this.color;
  }

  
  public JollyRogerElement setColor(String color) {
    this.color = color;
    return this;
  }

  
  public ResourceLocation getTexture() {
    return this.texture;
  }

  
  public JollyRogerElement setTexture(ResourceLocation texture) {
    this.texture = texture;
    return this;
  }

  
  public JollyRogerElement addUseCheck(ICanUse check) {
    this.canUseChecks.add(check);
    return this;
  }

  
  public boolean canUse(PlayerEntity player) {
    boolean flag = true;
    for (ICanUse check : this.canUseChecks) {
      
      if (check != null && !check.canUse(player)) {
        
        flag = false;
        
        break;
      } 
    } 
    return flag;
  }

  
  public LayerType getLayerType() {
    return this.type;
  }


  
  public boolean equals(Object element) {
    if (!(element instanceof JollyRogerElement) || getTexture() == null || ((JollyRogerElement)element).getTexture() == null) {
      return false;
    }
    return getTexture().toString().equalsIgnoreCase(((JollyRogerElement)element).getTexture().toString());
  }


  
  @Nullable
  public JollyRogerElement create() {
    try {
      return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
    }
    catch (Exception ex) {
      
      ex.printStackTrace();
      
      return null;
    } 
  }




  
  public enum LayerType
  {
    BASE, BACKGROUND, DETAIL;
  }
  
  public static interface ICanUse extends Serializable {
    boolean canUse(PlayerEntity param1PlayerEntity);
  }
}


