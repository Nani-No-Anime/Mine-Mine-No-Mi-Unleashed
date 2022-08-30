/*   */ package xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles;
/*   */ 
/*   */ import net.minecraft.network.datasync.DataParameter;
/*   */ import net.minecraft.network.datasync.DataSerializers;
/*   */ import net.minecraft.network.datasync.EntityDataManager;
/*   */ 
/*   */ public interface IFlexibleSizeProjectile
/*   */ {
/* 9 */   public static final DataParameter<Float> SIZE = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.FLOAT);
/*   */   
/*   */   void setSize(float paramFloat);
/*   */   
/*   */   float getSize();
/*   */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\projectiles\IFlexibleSizeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */