package xyz.pixelatedw.mineminenomi.particles.data;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class GenericParticleData
  implements IParticleData
{
  public static final IParticleData.IDeserializer<GenericParticleData> DESERIALIZER = new IParticleData.IDeserializer<GenericParticleData>()
    {
      
      public GenericParticleData deserialize(ParticleType<GenericParticleData> particleType, StringReader stringReader) throws CommandSyntaxException
      {
        stringReader.expect(' ');
        float red = stringReader.readFloat();
        float green = stringReader.readFloat();
        float blue = stringReader.readFloat();
        float alpha = stringReader.readFloat();
        float size = stringReader.readFloat();
        float rot = stringReader.readFloat();
        int life = stringReader.readInt();
        int ID = stringReader.readInt();
        
        boolean hasMotionDecay = stringReader.readBoolean();
        boolean hasScaleDecay = stringReader.readBoolean();
        boolean shouldHideTooClose = stringReader.readBoolean();
        boolean shouldHideFromOthers = stringReader.readBoolean();
        
        double motionX = stringReader.readDouble();
        double motionY = stringReader.readDouble();
        double motionZ = stringReader.readDouble();
        
        float rotX = stringReader.readFloat();
        float rotY = stringReader.readFloat();
        float rotZ = stringReader.readFloat();
        
        GenericParticleData data = new GenericParticleData(particleType);
        data.setColor(red, green, blue, alpha);
        data.setMotion(motionX, motionY, motionZ);
        data.setRotation(rotX, rotY, rotZ);
        data.setSize(size);
        data.setRotationSpeed(rot);
        data.setLife(life);
        data.setEntityID(ID);
        
        data.setHasMotionDecay(hasMotionDecay);
        data.setHasScaleDecay(hasScaleDecay);
        
        if (shouldHideTooClose) {
          data.hideTooClose();
        }
        if (shouldHideFromOthers) {
          data.hideFromOthers();
        }
        return data;
      }


      
      public GenericParticleData read(ParticleType<GenericParticleData> particleType, PacketBuffer packetBuffer) {
        float red = packetBuffer.readFloat();
        float green = packetBuffer.readFloat();
        float blue = packetBuffer.readFloat();
        float alpha = packetBuffer.readFloat();
        float size = packetBuffer.readFloat();
        float rot = packetBuffer.readFloat();
        int life = packetBuffer.readInt();
        int ID = packetBuffer.readInt();
        boolean hasMotionDecay = packetBuffer.readBoolean();
        boolean hasScaleDecay = packetBuffer.readBoolean();
        boolean shouldHideTooClose = packetBuffer.readBoolean();
        boolean shouldHideFromOthers = packetBuffer.readBoolean();
        
        double motionX = packetBuffer.readDouble();
        double motionY = packetBuffer.readDouble();
        double motionZ = packetBuffer.readDouble();
        
        float rotX = packetBuffer.readFloat();
        float rotY = packetBuffer.readFloat();
        float rotZ = packetBuffer.readFloat();
        
        GenericParticleData data = new GenericParticleData(particleType);
        data.setColor(red, green, blue, alpha);
        data.setMotion(motionX, motionY, motionZ);
        data.setRotation(rotX, rotY, rotZ);
        data.setSize(size);
        data.setRotationSpeed(rot);
        data.setLife(life);
        data.setEntityID(ID);
        
        data.setHasMotionDecay(hasMotionDecay);
        data.setHasScaleDecay(hasScaleDecay);
        
        if (shouldHideTooClose) {
          data.hideTooClose();
        }
        if (shouldHideFromOthers) {
          data.hideFromOthers();
        }
        return data;
      }
    };
  
  private float red = 1.0F; private float green = 1.0F; private float blue = 1.0F; private double motionX;
  private double motionY;
  private double motionZ;
  private float alpha = 1.0F; private float rotX; private float rotY; private float rotZ;
  private float size = 1.0F;
  private float rotSpeed = 0.0F;
  private int life = 10;
  private int entityID = 0;
  
  private boolean hasMotionDecay = true;
  
  private boolean hasScaleDecay = true;
  private boolean shouldHideTooClose = false;
  private boolean shouldHideFromOthers = false;
  private ParticleType type;
  
  public GenericParticleData(ParticleType type) {
    this.type = type;
  }


  
  public ParticleType<?> getType() {
    return this.type;
  }


  
  public void write(PacketBuffer buffer) {
    buffer.writeFloat(this.red);
    buffer.writeFloat(this.green);
    buffer.writeFloat(this.blue);
    buffer.writeFloat(this.alpha);
    buffer.writeFloat(this.size);
    buffer.writeFloat(this.rotSpeed);
    buffer.writeInt(this.life);
    buffer.writeInt(this.entityID);
    
    buffer.writeBoolean(this.hasMotionDecay);
    buffer.writeBoolean(this.hasScaleDecay);
    buffer.writeBoolean(this.shouldHideTooClose);
    buffer.writeBoolean(this.shouldHideFromOthers);
    
    buffer.writeDouble(this.motionX);
    buffer.writeDouble(this.motionY);
    buffer.writeDouble(this.motionZ);
    
    buffer.writeFloat(this.rotX);
    buffer.writeFloat(this.rotY);
    buffer.writeFloat(this.rotZ);
  }

  
  public GenericParticleData setMotion(double motionX, double motionY, double motionZ) {
    this.motionX = motionX;
    this.motionY = motionY;
    this.motionZ = motionZ;
    return this;
  }

  
  public GenericParticleData setRotation(float rotX, float rotY, float rotZ) {
    this.rotX = rotX;
    this.rotY = rotY;
    this.rotZ = rotZ;
    this.rotSpeed = 0.3F;
    return this;
  }

  
  public GenericParticleData setRotation(Vector3f vec) {
    return setRotation(vec.getX(), vec.getY(), vec.getZ());
  }

  
  public GenericParticleData setColor(float red, float green, float blue) {
    return setColor(red, green, blue, 1.0F);
  }

  
  public GenericParticleData setColor(float red, float green, float blue, float alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    return this;
  }

  
  public GenericParticleData setRotationSpeed(float rot) {
    this.rotSpeed = rot;
    return this;
  }

  
  public GenericParticleData setSize(float size) {
    this.size = size;
    return this;
  }

  
  public GenericParticleData setLife(int life) {
    this.life = life;
    return this;
  }

  
  public GenericParticleData setEntityID(int id) {
    this.entityID = id;
    return this;
  }

  
  public GenericParticleData setHasMotionDecay(boolean flag) {
    this.hasMotionDecay = flag;
    return this;
  }

  
  public GenericParticleData setHasScaleDecay(boolean flag) {
    this.hasScaleDecay = flag;
    return this;
  }

  
  public GenericParticleData hideFromOthers() {
    this.shouldHideFromOthers = true;
    return this;
  }

  
  public GenericParticleData hideTooClose() {
    this.shouldHideTooClose = true;
    return this;
  }

  
  public boolean getHideTooClose() {
    return this.shouldHideTooClose;
  }


  
  public boolean getHideFromOthers() {
    return this.shouldHideFromOthers;
  }


  
  public String getParameters() {
    return getType().getRegistryName().toString();
  }

  
  public float getRed() {
    return this.red;
  }

  
  public float getEntityID() {
    return this.entityID;
  }

  
  public float getGreen() {
    return this.green;
  }

  
  public float getBlue() {
    return this.blue;
  }

  
  public float getAlpha() {
    return this.alpha;
  }

  
  public float getSize() {
    return this.size;
  }

  
  public float getRotationSpeed() {
    return this.rotSpeed;
  }

  
  public int getLife() {
    return this.life;
  }

  
  public double getMotionX() {
    return this.motionX;
  }

  
  public double getMotionY() {
    return this.motionY;
  }

  
  public double getMotionZ() {
    return this.motionZ;
  }

  
  public float getRotX() {
    return this.rotX;
  }

  
  public float getRotY() {
    return this.rotY;
  }

  
  public float getRotZ() {
    return this.rotZ;
  }

  
  public boolean hasMotionDecay() {
    return this.hasMotionDecay;
  }

  
  public boolean hasScaleDecay() {
    return this.hasScaleDecay;
  }
}


