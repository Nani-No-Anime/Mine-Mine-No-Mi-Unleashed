/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JollyRogerElement
/*     */   extends ForgeRegistryEntry<JollyRogerElement>
/*     */ {
/*     */   private boolean canBeColored = false;
/*  18 */   private String color = "#FFFFFF";
/*     */   private ResourceLocation texture;
/*  20 */   private List<ICanUse> canUseChecks = new ArrayList<>();
/*     */   
/*     */   private LayerType type;
/*     */   
/*     */   @Deprecated
/*     */   public JollyRogerElement(LayerType type) {
/*  26 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement(LayerType type, String texture) {
/*  31 */     this.type = type;
/*  32 */     String category = "bases";
/*  33 */     if (type == LayerType.BACKGROUND) {
/*  34 */       category = "backgrounds";
/*  35 */     } else if (type == LayerType.DETAIL) {
/*  36 */       category = "details";
/*  37 */     }  setTexture(new ResourceLocation("mineminenomi", "textures/jolly_rogers/" + category + "/" + texture + ".png"));
/*  38 */     ModJollyRogers.registerElement(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeColored() {
/*  43 */     return this.canBeColored;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement setCanBeColored() {
/*  48 */     this.canBeColored = true;
/*  49 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getColor() {
/*  54 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement setColor(String color) {
/*  59 */     this.color = color;
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  65 */     return this.texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement setTexture(ResourceLocation texture) {
/*  70 */     this.texture = texture;
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRogerElement addUseCheck(ICanUse check) {
/*  76 */     this.canUseChecks.add(check);
/*  77 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(PlayerEntity player) {
/*  82 */     boolean flag = true;
/*  83 */     for (ICanUse check : this.canUseChecks) {
/*     */       
/*  85 */       if (check != null && !check.canUse(player)) {
/*     */         
/*  87 */         flag = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  92 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public LayerType getLayerType() {
/*  97 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object element) {
/* 103 */     if (!(element instanceof JollyRogerElement) || getTexture() == null || ((JollyRogerElement)element).getTexture() == null) {
/* 104 */       return false;
/*     */     }
/* 106 */     return getTexture().toString().equalsIgnoreCase(((JollyRogerElement)element).getTexture().toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public JollyRogerElement create() {
/*     */     try {
/* 114 */       return getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
/*     */     }
/* 116 */     catch (Exception ex) {
/*     */       
/* 118 */       ex.printStackTrace();
/*     */       
/* 120 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum LayerType
/*     */   {
/* 130 */     BASE, BACKGROUND, DETAIL;
/*     */   }
/*     */   
/*     */   public static interface ICanUse extends Serializable {
/*     */     boolean canUse(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\crew\JollyRogerElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */