package xyz.pixelatedw.mineminenomi.api.crew;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;

import java.util.Arrays;



public class JollyRoger
{
  private JollyRogerElement base = ModJollyRogers.BASE_0;
  private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
  private JollyRogerElement[] details = new JollyRogerElement[5];

  
  public CompoundNBT write() {
    CompoundNBT nbt = new CompoundNBT();
    
    JollyRogerElement baseElement = getBase();
    if (baseElement != null && baseElement.getTexture() != null) {
      
      CompoundNBT baseNBT = new CompoundNBT();
      baseNBT.putString("id", baseElement.getTexture().toString());
      baseNBT.putBoolean("canBeColored", baseElement.canBeColored());
      baseNBT.putString("color", baseElement.getColor());
      nbt.put("base", (INBT)baseNBT);
    }
    else {
      
      CompoundNBT baseNBT = new CompoundNBT();
      baseNBT.putString("id", "empty");
      nbt.put("base", (INBT)baseNBT);
    } 

    
    try {
      ListNBT backgrounds = new ListNBT();
      for (int i = 0; i < (getBackgrounds()).length; i++) {
        
        JollyRogerElement bgElement = getBackgrounds()[i];
        if (bgElement != null && bgElement.getTexture() != null) {
          
          CompoundNBT backgroundNBT = new CompoundNBT();
          backgroundNBT.putInt("slot", i);
          backgroundNBT.putString("id", bgElement.getTexture().toString());
          backgroundNBT.putBoolean("canBeColored", bgElement.canBeColored());
          backgroundNBT.putString("color", bgElement.getColor());
          backgrounds.add(backgroundNBT);
        }
        else {
          
          CompoundNBT backgroundNBT = new CompoundNBT();
          backgroundNBT.putInt("slot", i);
          backgroundNBT.putString("id", "empty");
          backgrounds.add(backgroundNBT);
        } 
      } 
      nbt.put("backgrounds", (INBT)backgrounds);
      
      ListNBT details = new ListNBT();
      for (int j = 0; j < (getDetails()).length; j++) {
        
        JollyRogerElement detElement = getDetails()[j];
        if (detElement != null && detElement.getTexture() != null) {
          
          CompoundNBT detailNBT = new CompoundNBT();
          detailNBT.putInt("slot", j);
          detailNBT.putString("id", detElement.getTexture().toString());
          detailNBT.putBoolean("canBeColored", detElement.canBeColored());
          detailNBT.putString("color", detElement.getColor());
          details.add(detailNBT);
        }
        else {
          
          CompoundNBT detailNBT = new CompoundNBT();
          detailNBT.putInt("slot", j);
          detailNBT.putString("id", "empty");
          details.add(detailNBT);
        } 
      } 
      nbt.put("details", (INBT)details);
    }
    catch (Exception ex) {
      
      ex.printStackTrace();
    } 
    
    return nbt;
  }


  
  public void read(CompoundNBT nbt) {
    try {
      CompoundNBT baseNBT = nbt.getCompound("base");
      JollyRogerElement baseElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation("mineminenomi", baseNBT.getString("id").replace("mineminenomi:", "")));
      
      if (baseElement != null) {
        
        if (baseNBT.getBoolean("canBeColored")) {
          baseElement.setCanBeColored();
        }
        String color = baseNBT.getString("color");
        baseElement.setColor(color);
        
        setBase(baseElement);
      }
      else {
        
        setBase(null);
      } 
      
      ListNBT backgroundsNBT = nbt.getList("backgrounds", 10);
      for (int i = 0; i < backgroundsNBT.size(); i++) {
        
        CompoundNBT backgroundNBT = backgroundsNBT.getCompound(i);
        ResourceLocation res = new ResourceLocation("mineminenomi", backgroundNBT.getString("id").replace("mineminenomi:", ""));
        JollyRogerElement bgElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(res);
        
        int slot = backgroundNBT.getInt("slot");
        if (bgElement != null) {
          
          if (backgroundNBT.getBoolean("canBeColored")) {
            bgElement.setCanBeColored();
          }
          String color = backgroundNBT.getString("color");
          bgElement.setColor(color);
          
          setBackground(slot, bgElement);
        }
        else {
          
          setBackground(slot, null);
        } 
      } 
      
      ListNBT detailsNBT = nbt.getList("details", 10);
      for (int j = 0; j < detailsNBT.size(); j++) {
        
        CompoundNBT detailNBT = detailsNBT.getCompound(j);
        JollyRogerElement detElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation("mineminenomi", detailNBT.getString("id").replace("mineminenomi:", "")));
        
        int slot = detailNBT.getInt("slot");
        if (detElement != null)
        {
          if (detailNBT.getBoolean("canBeColored")) {
            detElement.setCanBeColored();
          }
          String color = detailNBT.getString("color");
          detElement.setColor(color);
          
          setDetail(slot, detElement);
        }
        else
        {
          setDetail(slot, null);
        }
      
      } 
    } catch (Exception ex) {
      
      ex.printStackTrace();
    } 
  }

  
  public JollyRogerElement getBase() {
    return this.base;
  }

  
  public void setBase(JollyRogerElement base) {
    this.base = base;
  }

  
  public JollyRogerElement[] getBackgrounds() {
    return this.backgrounds;
  }

  
  public void setBackgrounds(JollyRogerElement[] elements) {
    this.backgrounds = elements;
  }

  
  public boolean addBackground(JollyRogerElement bg) {
    for (int i = 0; i < this.backgrounds.length; i++) {
      
      JollyRogerElement background = this.backgrounds[i];
      if (background == null) {
        
        this.backgrounds[i] = bg;
        return true;
      } 
    } 
    return false;
  }

  
  public boolean setBackground(int slot, JollyRogerElement bg) {
    if (!hasBackground(bg) && slot <= 2) {
      
      this.backgrounds[slot] = bg;
      return true;
    } 
    return false;
  }

  
  public boolean hasBackground(JollyRogerElement bg) {
    return Arrays.<JollyRogerElement>stream(this.backgrounds).parallel().anyMatch(background -> (background != null && background.equals(bg)));
  }

  
  public JollyRogerElement[] getDetails() {
    return this.details;
  }

  
  public void setDetails(JollyRogerElement[] elements) {
    this.details = elements;
  }

  
  public boolean addDetail(JollyRogerElement det) {
    for (int i = 0; i < this.details.length; i++) {
      
      JollyRogerElement detail = this.details[i];
      if (detail == null) {
        
        this.details[i] = det;
        return true;
      } 
    } 
    return false;
  }

  
  public boolean setDetail(int slot, JollyRogerElement det) {
    if (!hasDetail(det) && slot <= 5) {
      
      this.details[slot] = det;
      return true;
    } 
    return false;
  }

  
  public boolean hasDetail(JollyRogerElement det) {
    return Arrays.<JollyRogerElement>stream(this.details).parallel().anyMatch(detail -> (detail != null && detail.equals(det)));
  }
}


