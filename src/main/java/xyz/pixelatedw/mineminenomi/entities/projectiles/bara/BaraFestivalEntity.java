/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.datasync.DataParameter;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ 
/*    */ 
/*    */ public class BaraFestivalEntity
/*    */   extends Entity
/*    */ {
/* 21 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BaraFestivalEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*    */   
/*    */   private LivingEntity target;
/*    */ 
/*    */   
/*    */   public BaraFestivalEntity(World world) {
/* 27 */     super(BaraProjectiles.BARA_FESTIVAL, world);
/* 28 */     setNoGravity(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 34 */     this.dataManager.register(OWNER, Optional.empty());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 40 */     if (!this.world.isRemote) {
/*    */       
/* 42 */       if (getOwner() == null || !getOwner().isAlive()) {
/*    */         
/* 44 */         remove();
/*    */         
/*    */         return;
/*    */       } 
/* 48 */       if (this.target == null || !this.target.isAlive()) {
/*    */         
/* 50 */         remove();
/*    */         
/*    */         return;
/*    */       } 
/* 54 */       setPositionAndUpdate(this.target.getPosX(), this.target.getPosY(), this.target.getPosZ());
/*    */     } 
/* 56 */     super.tick();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeAdditional(CompoundNBT compound) {
/* 62 */     if (((Optional)this.dataManager.get(OWNER)).isPresent()) {
/* 63 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void readAdditional(CompoundNBT compound) {
/* 69 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTarget(LivingEntity target) {
/* 74 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(UUID uuid) {
/* 79 */     this.dataManager.set(OWNER, Optional.of(uuid));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public UUID getOwnerUUID() {
/* 85 */     return ((Optional<UUID>)this.dataManager.get(OWNER)).orElseGet(() -> null);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerEntity getOwner() {
/* 90 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> createSpawnPacket() {
/* 96 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraFestivalEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */