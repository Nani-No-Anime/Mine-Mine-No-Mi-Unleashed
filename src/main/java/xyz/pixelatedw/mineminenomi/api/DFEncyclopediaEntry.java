/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DFEncyclopediaEntry
/*    */ {
/*    */   private final Optional<Color> baseColor;
/*    */   private final Optional<Color> stemColor;
/*    */   private final Optional<Integer> shape;
/*    */   private AkumaNoMiItem devilFruit;
/*    */   
/*    */   public static DFEncyclopediaEntry empty() {
/* 18 */     return new DFEncyclopediaEntry(Optional.empty(), Optional.empty(), Optional.empty());
/*    */   }
/*    */ 
/*    */   
/*    */   public static DFEncyclopediaEntry of(Optional<Integer> type, Optional<Color> baseColor, Optional<Color> stemColor) {
/* 23 */     return new DFEncyclopediaEntry(type, baseColor, stemColor);
/*    */   }
/*    */ 
/*    */   
/*    */   public static DFEncyclopediaEntry of(CompoundNBT nbt) {
/* 28 */     Optional<Integer> shape = Optional.empty();
/*    */     
/* 30 */     int shapeVal = nbt.getInt("shape");
/* 31 */     if (shapeVal > 0) {
/* 32 */       shape = Optional.of(Integer.valueOf(shapeVal));
/*    */     }
/* 34 */     Color baseVal = new Color(nbt.getInt("baseColor"));
/* 35 */     Color stemVal = new Color(nbt.getInt("stemColor"));
/*    */     
/* 37 */     return new DFEncyclopediaEntry(shape, Optional.of(baseVal), Optional.of(stemVal));
/*    */   }
/*    */ 
/*    */   
/*    */   public DFEncyclopediaEntry(Optional<Integer> shape, Optional<Color> baseColor, Optional<Color> stemColor) {
/* 42 */     this.shape = shape;
/* 43 */     this.baseColor = baseColor;
/* 44 */     this.stemColor = stemColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Color> getBaseColor() {
/* 49 */     return this.baseColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Color> getStemColor() {
/* 54 */     return this.stemColor;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Integer> getShape() {
/* 59 */     return this.shape;
/*    */   }
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 64 */     return this.devilFruit;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDevilFruit(AkumaNoMiItem devilFruit) {
/* 69 */     this.devilFruit = devilFruit;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCompletion() {
/* 74 */     int sum = 0;
/* 75 */     if (getShape().isPresent())
/* 76 */       sum++; 
/* 77 */     if (getBaseColor().isPresent() && ((Color)getBaseColor().get()).getRGB() != Color.BLACK.getRGB())
/* 78 */       sum++; 
/* 79 */     if (getStemColor().isPresent() && ((Color)getStemColor().get()).getRGB() != Color.BLACK.getRGB())
/* 80 */       sum++; 
/* 81 */     return sum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     StringBuilder sb = new StringBuilder();
/* 88 */     sb.append("[ ");
/* 89 */     if (getShape().isPresent())
/* 90 */       sb.append("Shape: " + getShape().get() + " "); 
/* 91 */     if (getBaseColor().isPresent())
/* 92 */       sb.append("Base Color: " + ((Color)getBaseColor().get()).toString() + " "); 
/* 93 */     if (getStemColor().isPresent())
/* 94 */       sb.append("Stem Color: " + ((Color)getStemColor().get()).toString() + " "); 
/* 95 */     sb.append("Completion: " + getCompletion());
/* 96 */     sb.append(" ]");
/* 97 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\DFEncyclopediaEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */