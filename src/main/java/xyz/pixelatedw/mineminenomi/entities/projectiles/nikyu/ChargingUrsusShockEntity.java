/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.datasync.DataParameter;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ 
/*    */ public class ChargingUrsusShockEntity
/*    */   extends Entity {
/* 16 */   protected static final DataParameter<Float> CHARGE = EntityDataManager.createKey(ChargingUrsusShockEntity.class, DataSerializers.FLOAT);
/*    */   
/*    */   private LivingEntity owner;
/*    */ 
/*    */   
/*    */   public ChargingUrsusShockEntity(World worldIn) {
/* 22 */     super(NikyuProjectiles.CHARGING_URSUS_SHOCK, worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChargingUrsusShockEntity(EntityType<?> entityTypeIn, World worldIn) {
/* 27 */     super(entityTypeIn, worldIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 33 */     super.tick();
/*    */     
/* 35 */     if (!this.world.isRemote) {
/*    */       
/* 37 */       if (this.owner == null) {
/*    */         
/* 39 */         remove();
/*    */         
/*    */         return;
/*    */       } 
/* 43 */       if (this.ticksExisted > 600) {
/*    */         
/* 45 */         remove();
/*    */         
/*    */         return;
/*    */       } 
/* 49 */       setPosition(this.owner.getPosX(), this.owner.getPosY() + this.owner.getEyeHeight() + (0.9F * 
/* 50 */           getCharge()), this.owner.getPosZ());
/* 51 */       setRotation(this.owner.rotationYawHead, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 58 */     this.dataManager.register(CHARGE, Float.valueOf(0.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharge(float value) {
/* 63 */     this.dataManager.set(CHARGE, Float.valueOf(value));
/*    */   }
/*    */ 
/*    */   
/*    */   public float getCharge() {
/* 68 */     return ((Float)this.dataManager.get(CHARGE)).floatValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(LivingEntity owner) {
/* 73 */     this.owner = owner;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readAdditional(CompoundNBT compound) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeAdditional(CompoundNBT compound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> createSpawnPacket() {
/* 89 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\ChargingUrsusShockEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */