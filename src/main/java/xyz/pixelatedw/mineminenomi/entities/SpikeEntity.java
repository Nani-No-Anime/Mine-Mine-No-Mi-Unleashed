/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MoverType;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class SpikeEntity
/*    */   extends Entity
/*    */ {
/*    */   protected boolean inGround;
/* 24 */   protected List<LivingEntity> targets = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public SpikeEntity(World world) {
/* 28 */     super(ModEntities.SPIKE, world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 34 */     super.tick();
/*    */     
/* 36 */     if (!this.onGround) {
/* 37 */       move(MoverType.SELF, new Vec3d(0.0D, -0.3D, 0.0D));
/*    */     }
/* 39 */     if (this.world.isRemote) {
/*    */       return;
/*    */     }
/* 42 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 0.0D, new Class[] { LivingEntity.class });
/*    */     
/* 44 */     for (LivingEntity entity : targets)
/*    */     {
/* 46 */       entity.attackEntityFrom(DamageSource.GENERIC, 1.0F);
/*    */     }
/*    */     
/* 49 */     if (this.ticksExisted > 1200.0D + WyHelper.randomWithRange(0, 100)) {
/* 50 */       remove();
/*    */     }
/*    */   }
/*    */   
/*    */   protected float getGravityVelocity() {
/* 55 */     return 0.03F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {}
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB getCollisionBox(Entity entity) {
/* 67 */     return entity.getBoundingBox();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AxisAlignedBB getCollisionBoundingBox() {
/* 73 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeCollidedWith() {
/* 79 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void readAdditional(CompoundNBT compound) {
/* 85 */     compound.putBoolean("inGround", this.inGround);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writeAdditional(CompoundNBT compound) {
/* 91 */     this.inGround = compound.getBoolean("inGround");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> createSpawnPacket() {
/* 97 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\SpikeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */