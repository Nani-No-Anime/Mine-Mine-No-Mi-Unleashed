/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class DummyEntity
/*    */   extends OPEntity
/*    */ {
/*    */   private boolean hasBazooka;
/*    */   
/*    */   public DummyEntity(EntityType<? extends DummyEntity> type, World world) {
/* 25 */     super(type, world);
/* 26 */     EntityStatsCapability.get((LivingEntity)this).setFaction("civilian");
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBazooka() {
/* 31 */     this.hasBazooka = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 37 */     this.noClip = true;
/* 38 */     super.tick();
/* 39 */     this.noClip = false;
/* 40 */     setNoGravity(true);
/* 41 */     setMotion(0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 48 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 50 */     if (this.hasBazooka) {
/* 51 */       setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.BAZOOKA));
/*    */     }
/* 53 */     return spawnData;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\DummyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */