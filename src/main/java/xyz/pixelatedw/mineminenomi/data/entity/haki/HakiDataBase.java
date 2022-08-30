/*    */ package xyz.pixelatedw.mineminenomi.data.entity.haki;
/*    */ 
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ public class HakiDataBase
/*    */   implements IHakiData {
/*    */   private float kenbunshokuExp;
/*    */   private float busoshokuHardeningExp;
/*    */   private float busoshokuImbuingExp;
/*    */   private int hakiOveruse;
/*    */   
/*    */   public float getTotalHakiExp() {
/* 14 */     return this.kenbunshokuExp + this.busoshokuHardeningExp + this.busoshokuImbuingExp;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHakiOveruse() {
/* 19 */     return this.hakiOveruse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void alterHakiOveruse(int value) {
/* 24 */     this.hakiOveruse = MathHelper.clamp(this.hakiOveruse + value, 0, 2147483647);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHakiOveruse(int value) {
/* 29 */     this.hakiOveruse = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getKenbunshokuHakiExp() {
/* 35 */     return this.kenbunshokuExp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void alterKenbunshokuHakiExp(float value) {
/* 41 */     this.kenbunshokuExp = MathHelper.clamp(this.kenbunshokuExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setKenbunshokuHakiExp(float value) {
/* 47 */     this.kenbunshokuExp = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBusoshokuHardeningHakiExp() {
/* 53 */     return this.busoshokuHardeningExp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void alterBusoshokuHardeningHakiExp(float value) {
/* 59 */     this.busoshokuHardeningExp = MathHelper.clamp(this.busoshokuHardeningExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBusoshokuHardeningHakiExp(float value) {
/* 65 */     this.busoshokuHardeningExp = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getBusoshokuImbuingHakiExp() {
/* 71 */     return this.busoshokuImbuingExp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void alterBusoshokuImbuingHakiExp(float value) {
/* 77 */     this.busoshokuImbuingExp = MathHelper.clamp(this.busoshokuImbuingExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBusoshokuImbuingHakiExp(float value) {
/* 83 */     this.busoshokuImbuingExp = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\HakiDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */