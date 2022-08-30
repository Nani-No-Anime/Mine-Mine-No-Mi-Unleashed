/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JollyRoger
/*     */ {
/*  15 */   private JollyRogerElement base = ModJollyRogers.BASE_0;
/*  16 */   private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
/*  17 */   private JollyRogerElement[] details = new JollyRogerElement[5];
/*     */ 
/*     */   
/*     */   public CompoundNBT write() {
/*  21 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/*  23 */     JollyRogerElement baseElement = getBase();
/*  24 */     if (baseElement != null && baseElement.getTexture() != null) {
/*     */       
/*  26 */       CompoundNBT baseNBT = new CompoundNBT();
/*  27 */       baseNBT.putString("id", baseElement.getTexture().toString());
/*  28 */       baseNBT.putBoolean("canBeColored", baseElement.canBeColored());
/*  29 */       baseNBT.putString("color", baseElement.getColor());
/*  30 */       nbt.put("base", (INBT)baseNBT);
/*     */     }
/*     */     else {
/*     */       
/*  34 */       CompoundNBT baseNBT = new CompoundNBT();
/*  35 */       baseNBT.putString("id", "empty");
/*  36 */       nbt.put("base", (INBT)baseNBT);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  41 */       ListNBT backgrounds = new ListNBT();
/*  42 */       for (int i = 0; i < (getBackgrounds()).length; i++) {
/*     */         
/*  44 */         JollyRogerElement bgElement = getBackgrounds()[i];
/*  45 */         if (bgElement != null && bgElement.getTexture() != null) {
/*     */           
/*  47 */           CompoundNBT backgroundNBT = new CompoundNBT();
/*  48 */           backgroundNBT.putInt("slot", i);
/*  49 */           backgroundNBT.putString("id", bgElement.getTexture().toString());
/*  50 */           backgroundNBT.putBoolean("canBeColored", bgElement.canBeColored());
/*  51 */           backgroundNBT.putString("color", bgElement.getColor());
/*  52 */           backgrounds.add(backgroundNBT);
/*     */         }
/*     */         else {
/*     */           
/*  56 */           CompoundNBT backgroundNBT = new CompoundNBT();
/*  57 */           backgroundNBT.putInt("slot", i);
/*  58 */           backgroundNBT.putString("id", "empty");
/*  59 */           backgrounds.add(backgroundNBT);
/*     */         } 
/*     */       } 
/*  62 */       nbt.put("backgrounds", (INBT)backgrounds);
/*     */       
/*  64 */       ListNBT details = new ListNBT();
/*  65 */       for (int j = 0; j < (getDetails()).length; j++) {
/*     */         
/*  67 */         JollyRogerElement detElement = getDetails()[j];
/*  68 */         if (detElement != null && detElement.getTexture() != null) {
/*     */           
/*  70 */           CompoundNBT detailNBT = new CompoundNBT();
/*  71 */           detailNBT.putInt("slot", j);
/*  72 */           detailNBT.putString("id", detElement.getTexture().toString());
/*  73 */           detailNBT.putBoolean("canBeColored", detElement.canBeColored());
/*  74 */           detailNBT.putString("color", detElement.getColor());
/*  75 */           details.add(detailNBT);
/*     */         }
/*     */         else {
/*     */           
/*  79 */           CompoundNBT detailNBT = new CompoundNBT();
/*  80 */           detailNBT.putInt("slot", j);
/*  81 */           detailNBT.putString("id", "empty");
/*  82 */           details.add(detailNBT);
/*     */         } 
/*     */       } 
/*  85 */       nbt.put("details", (INBT)details);
/*     */     }
/*  87 */     catch (Exception ex) {
/*     */       
/*  89 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  92 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/*     */     try {
/*  99 */       CompoundNBT baseNBT = nbt.getCompound("base");
/* 100 */       JollyRogerElement baseElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation("mineminenomi", baseNBT.getString("id").replace("mineminenomi:", "")));
/*     */       
/* 102 */       if (baseElement != null) {
/*     */         
/* 104 */         if (baseNBT.getBoolean("canBeColored")) {
/* 105 */           baseElement.setCanBeColored();
/*     */         }
/* 107 */         String color = baseNBT.getString("color");
/* 108 */         baseElement.setColor(color);
/*     */         
/* 110 */         setBase(baseElement);
/*     */       }
/*     */       else {
/*     */         
/* 114 */         setBase(null);
/*     */       } 
/*     */       
/* 117 */       ListNBT backgroundsNBT = nbt.getList("backgrounds", 10);
/* 118 */       for (int i = 0; i < backgroundsNBT.size(); i++) {
/*     */         
/* 120 */         CompoundNBT backgroundNBT = backgroundsNBT.getCompound(i);
/* 121 */         ResourceLocation res = new ResourceLocation("mineminenomi", backgroundNBT.getString("id").replace("mineminenomi:", ""));
/* 122 */         JollyRogerElement bgElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(res);
/*     */         
/* 124 */         int slot = backgroundNBT.getInt("slot");
/* 125 */         if (bgElement != null) {
/*     */           
/* 127 */           if (backgroundNBT.getBoolean("canBeColored")) {
/* 128 */             bgElement.setCanBeColored();
/*     */           }
/* 130 */           String color = backgroundNBT.getString("color");
/* 131 */           bgElement.setColor(color);
/*     */           
/* 133 */           setBackground(slot, bgElement);
/*     */         }
/*     */         else {
/*     */           
/* 137 */           setBackground(slot, null);
/*     */         } 
/*     */       } 
/*     */       
/* 141 */       ListNBT detailsNBT = nbt.getList("details", 10);
/* 142 */       for (int j = 0; j < detailsNBT.size(); j++) {
/*     */         
/* 144 */         CompoundNBT detailNBT = detailsNBT.getCompound(j);
/* 145 */         JollyRogerElement detElement = (JollyRogerElement)GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation("mineminenomi", detailNBT.getString("id").replace("mineminenomi:", "")));
/*     */         
/* 147 */         int slot = detailNBT.getInt("slot");
/* 148 */         if (detElement != null)
/*     */         {
/* 150 */           if (detailNBT.getBoolean("canBeColored")) {
/* 151 */             detElement.setCanBeColored();
/*     */           }
/* 153 */           String color = detailNBT.getString("color");
/* 154 */           detElement.setColor(color);
/*     */           
/* 156 */           setDetail(slot, detElement);
/*     */         }
/*     */         else
/*     */         {
/* 160 */           setDetail(slot, null);
/*     */         }
/*     */       
/*     */       } 
/* 164 */     } catch (Exception ex) {
/*     */       
/* 166 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement getBase() {
/* 172 */     return this.base;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBase(JollyRogerElement base) {
/* 177 */     this.base = base;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement[] getBackgrounds() {
/* 182 */     return this.backgrounds;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackgrounds(JollyRogerElement[] elements) {
/* 187 */     this.backgrounds = elements;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addBackground(JollyRogerElement bg) {
/* 192 */     for (int i = 0; i < this.backgrounds.length; i++) {
/*     */       
/* 194 */       JollyRogerElement background = this.backgrounds[i];
/* 195 */       if (background == null) {
/*     */         
/* 197 */         this.backgrounds[i] = bg;
/* 198 */         return true;
/*     */       } 
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setBackground(int slot, JollyRogerElement bg) {
/* 206 */     if (!hasBackground(bg) && slot <= 2) {
/*     */       
/* 208 */       this.backgrounds[slot] = bg;
/* 209 */       return true;
/*     */     } 
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBackground(JollyRogerElement bg) {
/* 216 */     return Arrays.<JollyRogerElement>stream(this.backgrounds).parallel().anyMatch(background -> (background != null && background.equals(bg)));
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement[] getDetails() {
/* 221 */     return this.details;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDetails(JollyRogerElement[] elements) {
/* 226 */     this.details = elements;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addDetail(JollyRogerElement det) {
/* 231 */     for (int i = 0; i < this.details.length; i++) {
/*     */       
/* 233 */       JollyRogerElement detail = this.details[i];
/* 234 */       if (detail == null) {
/*     */         
/* 236 */         this.details[i] = det;
/* 237 */         return true;
/*     */       } 
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setDetail(int slot, JollyRogerElement det) {
/* 245 */     if (!hasDetail(det) && slot <= 5) {
/*     */       
/* 247 */       this.details[slot] = det;
/* 248 */       return true;
/*     */     } 
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasDetail(JollyRogerElement det) {
/* 255 */     return Arrays.<JollyRogerElement>stream(this.details).parallel().anyMatch(detail -> (detail != null && detail.equals(det)));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\crew\JollyRoger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */