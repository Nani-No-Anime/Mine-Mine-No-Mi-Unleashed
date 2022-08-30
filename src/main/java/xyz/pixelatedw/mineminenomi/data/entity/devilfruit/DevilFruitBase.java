/*    */ package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DevilFruitBase
/*    */   implements IDevilFruit {
/*  9 */   private String devilFruit = ""; private String zoanPoint = "";
/*    */   
/*    */   private boolean isLogia = false, hasYamiPower = false;
/*    */ 
/*    */   
/*    */   public String getDevilFruit() {
/* 15 */     return this.devilFruit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDevilFruit(String value) {
/* 21 */     this.devilFruit = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDevilFruit(AkumaNoMiItem fruit) {
/* 27 */     this.devilFruit = DevilFruitHelper.getDevilFruitKey(fruit);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasDevilFruit() {
/* 33 */     return !WyHelper.isNullOrEmpty(this.devilFruit);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasDevilFruit(AkumaNoMiItem fruit) {
/* 39 */     String key = DevilFruitHelper.getDevilFruitKey(fruit);
/* 40 */     boolean check = getDevilFruit().equalsIgnoreCase(key);
/*    */     
/* 42 */     if (!check && key.equalsIgnoreCase("yami_yami")) {
/* 43 */       check = hasYamiPower();
/*    */     }
/* 45 */     return check;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isLogia() {
/* 51 */     return this.isLogia;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLogia(boolean value) {
/* 57 */     this.isLogia = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasYamiPower() {
/* 63 */     return this.hasYamiPower;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setYamiPower(boolean value) {
/* 69 */     this.hasYamiPower = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getZoanPoint() {
/* 75 */     return this.zoanPoint;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setZoanPoint(String value) {
/* 81 */     this.zoanPoint = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\DevilFruitBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */