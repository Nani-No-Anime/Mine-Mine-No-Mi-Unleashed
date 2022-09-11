package xyz.pixelatedw.mineminenomi.api.abilities;

import java.awt.Color;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class AbilityOverlay
{
  private ResourceLocation texture = null;
  private RenderType renderType = RenderType.NORMAL;
  private Color color = WyHelper.hexToRGB("#FFFFFFFF");

  
  public AbilityOverlay setTexture(ResourceLocation texture) {
    this.texture = texture;
    return this;
  }

  
  public AbilityOverlay setColor(Color color) {
    this.color = color;
    return this;
  }

  
  public AbilityOverlay setColor(String hex) {
    this.color = WyHelper.hexToRGB(hex);
    return this;
  }

  
  public AbilityOverlay setRenderType(RenderType type) {
    this.renderType = type;
    return this;
  }

  
  public ResourceLocation getTexture() {
    return this.texture;
  }

  
  public Color getColor() {
    return this.color;
  }

  
  public RenderType getRenderType() {
    return this.renderType;
  }
  
  public enum RenderType
  {
    NORMAL, ENERGY;
  }
}


