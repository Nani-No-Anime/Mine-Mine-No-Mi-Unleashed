/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ 
/*    */ public class EntityCloud
/*    */   extends Entity
/*    */ {
/* 15 */   private int life = 100;
/*    */   
/*    */   private LivingEntity thrower;
/*    */   
/*    */   public EntityCloud(World world) {
/* 20 */     super(ExtraProjectiles.CLOUD, world);
/*    */   }
/*    */   
/*    */   public EntityCloud(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 29 */     super.tick();
/* 30 */     if (!this.world.isRemote) {
/*    */       
/* 32 */       if (this.life <= 0) {
/* 33 */         remove();
/*    */       }
/* 35 */       this.life--;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public LivingEntity getThrower() {
/* 42 */     return this.thrower;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setThrower(LivingEntity player) {
/* 47 */     this.thrower = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLife() {
/* 52 */     return this.life;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLife(int life) {
/* 57 */     this.life = life;
/*    */   }
/*    */   
/*    */   protected void registerData() {}
/*    */   
/*    */   protected void readAdditional(CompoundNBT compound) {}
/*    */   
/*    */   protected void writeAdditional(CompoundNBT compound) {}
/*    */   
/*    */   public IPacket<?> createSpawnPacket() {
/* 67 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\EntityCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */