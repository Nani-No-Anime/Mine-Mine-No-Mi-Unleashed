/*     */ package xyz.pixelatedw.mineminenomi.particles.data;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ 
/*     */ public class GenericParticleData
/*     */   implements IParticleData
/*     */ {
/*  13 */   public static final IParticleData.IDeserializer<GenericParticleData> DESERIALIZER = new IParticleData.IDeserializer<GenericParticleData>()
/*     */     {
/*     */       
/*     */       public GenericParticleData deserialize(ParticleType<GenericParticleData> particleType, StringReader stringReader) throws CommandSyntaxException
/*     */       {
/*  18 */         stringReader.expect(' ');
/*  19 */         float red = stringReader.readFloat();
/*  20 */         float green = stringReader.readFloat();
/*  21 */         float blue = stringReader.readFloat();
/*  22 */         float alpha = stringReader.readFloat();
/*  23 */         float size = stringReader.readFloat();
/*  24 */         float rot = stringReader.readFloat();
/*  25 */         int life = stringReader.readInt();
/*  26 */         int ID = stringReader.readInt();
/*     */         
/*  28 */         boolean hasMotionDecay = stringReader.readBoolean();
/*  29 */         boolean hasScaleDecay = stringReader.readBoolean();
/*  30 */         boolean shouldHideTooClose = stringReader.readBoolean();
/*  31 */         boolean shouldHideFromOthers = stringReader.readBoolean();
/*     */         
/*  33 */         double motionX = stringReader.readDouble();
/*  34 */         double motionY = stringReader.readDouble();
/*  35 */         double motionZ = stringReader.readDouble();
/*     */         
/*  37 */         float rotX = stringReader.readFloat();
/*  38 */         float rotY = stringReader.readFloat();
/*  39 */         float rotZ = stringReader.readFloat();
/*     */         
/*  41 */         GenericParticleData data = new GenericParticleData(particleType);
/*  42 */         data.setColor(red, green, blue, alpha);
/*  43 */         data.setMotion(motionX, motionY, motionZ);
/*  44 */         data.setRotation(rotX, rotY, rotZ);
/*  45 */         data.setSize(size);
/*  46 */         data.setRotationSpeed(rot);
/*  47 */         data.setLife(life);
/*  48 */         data.setEntityID(ID);
/*     */         
/*  50 */         data.setHasMotionDecay(hasMotionDecay);
/*  51 */         data.setHasScaleDecay(hasScaleDecay);
/*     */         
/*  53 */         if (shouldHideTooClose) {
/*  54 */           data.hideTooClose();
/*     */         }
/*  56 */         if (shouldHideFromOthers) {
/*  57 */           data.hideFromOthers();
/*     */         }
/*  59 */         return data;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public GenericParticleData read(ParticleType<GenericParticleData> particleType, PacketBuffer packetBuffer) {
/*  65 */         float red = packetBuffer.readFloat();
/*  66 */         float green = packetBuffer.readFloat();
/*  67 */         float blue = packetBuffer.readFloat();
/*  68 */         float alpha = packetBuffer.readFloat();
/*  69 */         float size = packetBuffer.readFloat();
/*  70 */         float rot = packetBuffer.readFloat();
/*  71 */         int life = packetBuffer.readInt();
/*  72 */         int ID = packetBuffer.readInt();
/*  73 */         boolean hasMotionDecay = packetBuffer.readBoolean();
/*  74 */         boolean hasScaleDecay = packetBuffer.readBoolean();
/*  75 */         boolean shouldHideTooClose = packetBuffer.readBoolean();
/*  76 */         boolean shouldHideFromOthers = packetBuffer.readBoolean();
/*     */         
/*  78 */         double motionX = packetBuffer.readDouble();
/*  79 */         double motionY = packetBuffer.readDouble();
/*  80 */         double motionZ = packetBuffer.readDouble();
/*     */         
/*  82 */         float rotX = packetBuffer.readFloat();
/*  83 */         float rotY = packetBuffer.readFloat();
/*  84 */         float rotZ = packetBuffer.readFloat();
/*     */         
/*  86 */         GenericParticleData data = new GenericParticleData(particleType);
/*  87 */         data.setColor(red, green, blue, alpha);
/*  88 */         data.setMotion(motionX, motionY, motionZ);
/*  89 */         data.setRotation(rotX, rotY, rotZ);
/*  90 */         data.setSize(size);
/*  91 */         data.setRotationSpeed(rot);
/*  92 */         data.setLife(life);
/*  93 */         data.setEntityID(ID);
/*     */         
/*  95 */         data.setHasMotionDecay(hasMotionDecay);
/*  96 */         data.setHasScaleDecay(hasScaleDecay);
/*     */         
/*  98 */         if (shouldHideTooClose) {
/*  99 */           data.hideTooClose();
/*     */         }
/* 101 */         if (shouldHideFromOthers) {
/* 102 */           data.hideFromOthers();
/*     */         }
/* 104 */         return data;
/*     */       }
/*     */     };
/*     */   
/* 108 */   private float red = 1.0F; private float green = 1.0F; private float blue = 1.0F; private double motionX;
/*     */   private double motionY;
/*     */   private double motionZ;
/* 111 */   private float alpha = 1.0F; private float rotX; private float rotY; private float rotZ;
/* 112 */   private float size = 1.0F;
/* 113 */   private float rotSpeed = 0.0F;
/* 114 */   private int life = 10;
/* 115 */   private int entityID = 0;
/*     */   
/*     */   private boolean hasMotionDecay = true;
/*     */   
/*     */   private boolean hasScaleDecay = true;
/*     */   private boolean shouldHideTooClose = false;
/*     */   private boolean shouldHideFromOthers = false;
/*     */   private ParticleType type;
/*     */   
/*     */   public GenericParticleData(ParticleType type) {
/* 125 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ParticleType<?> getType() {
/* 131 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(PacketBuffer buffer) {
/* 137 */     buffer.writeFloat(this.red);
/* 138 */     buffer.writeFloat(this.green);
/* 139 */     buffer.writeFloat(this.blue);
/* 140 */     buffer.writeFloat(this.alpha);
/* 141 */     buffer.writeFloat(this.size);
/* 142 */     buffer.writeFloat(this.rotSpeed);
/* 143 */     buffer.writeInt(this.life);
/* 144 */     buffer.writeInt(this.entityID);
/*     */     
/* 146 */     buffer.writeBoolean(this.hasMotionDecay);
/* 147 */     buffer.writeBoolean(this.hasScaleDecay);
/* 148 */     buffer.writeBoolean(this.shouldHideTooClose);
/* 149 */     buffer.writeBoolean(this.shouldHideFromOthers);
/*     */     
/* 151 */     buffer.writeDouble(this.motionX);
/* 152 */     buffer.writeDouble(this.motionY);
/* 153 */     buffer.writeDouble(this.motionZ);
/*     */     
/* 155 */     buffer.writeFloat(this.rotX);
/* 156 */     buffer.writeFloat(this.rotY);
/* 157 */     buffer.writeFloat(this.rotZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setMotion(double motionX, double motionY, double motionZ) {
/* 162 */     this.motionX = motionX;
/* 163 */     this.motionY = motionY;
/* 164 */     this.motionZ = motionZ;
/* 165 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setRotation(float rotX, float rotY, float rotZ) {
/* 170 */     this.rotX = rotX;
/* 171 */     this.rotY = rotY;
/* 172 */     this.rotZ = rotZ;
/* 173 */     this.rotSpeed = 0.3F;
/* 174 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setRotation(Vector3f vec) {
/* 179 */     return setRotation(vec.getX(), vec.getY(), vec.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setColor(float red, float green, float blue) {
/* 184 */     return setColor(red, green, blue, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setColor(float red, float green, float blue, float alpha) {
/* 189 */     this.red = red;
/* 190 */     this.green = green;
/* 191 */     this.blue = blue;
/* 192 */     this.alpha = alpha;
/* 193 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setRotationSpeed(float rot) {
/* 198 */     this.rotSpeed = rot;
/* 199 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setSize(float size) {
/* 204 */     this.size = size;
/* 205 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setLife(int life) {
/* 210 */     this.life = life;
/* 211 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setEntityID(int id) {
/* 216 */     this.entityID = id;
/* 217 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setHasMotionDecay(boolean flag) {
/* 222 */     this.hasMotionDecay = flag;
/* 223 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData setHasScaleDecay(boolean flag) {
/* 228 */     this.hasScaleDecay = flag;
/* 229 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData hideFromOthers() {
/* 234 */     this.shouldHideFromOthers = true;
/* 235 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public GenericParticleData hideTooClose() {
/* 240 */     this.shouldHideTooClose = true;
/* 241 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHideTooClose() {
/* 246 */     return this.shouldHideTooClose;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHideFromOthers() {
/* 252 */     return this.shouldHideFromOthers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParameters() {
/* 258 */     return getType().getRegistryName().toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRed() {
/* 263 */     return this.red;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEntityID() {
/* 268 */     return this.entityID;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getGreen() {
/* 273 */     return this.green;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBlue() {
/* 278 */     return this.blue;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAlpha() {
/* 283 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 288 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRotationSpeed() {
/* 293 */     return this.rotSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLife() {
/* 298 */     return this.life;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMotionX() {
/* 303 */     return this.motionX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMotionY() {
/* 308 */     return this.motionY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMotionZ() {
/* 313 */     return this.motionZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRotX() {
/* 318 */     return this.rotX;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRotY() {
/* 323 */     return this.rotY;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRotZ() {
/* 328 */     return this.rotZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMotionDecay() {
/* 333 */     return this.hasMotionDecay;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasScaleDecay() {
/* 338 */     return this.hasScaleDecay;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\data\GenericParticleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */