/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ public class DFItemEntity
/*    */   extends ItemEntity {
/*    */   public DFItemEntity(World world) {
/* 17 */     super(ModEntities.DEVIL_FRUIT_ITEM, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DFItemEntity(World world, double x, double y, double z, ItemStack stack) {
/* 22 */     this(world);
/* 23 */     setItem(stack);
/* 24 */     this.lifespan = (stack.getItem() == null) ? 6000 : stack.getEntityLifespan(world);
/* 25 */     setPosition(x, y, z);
/* 26 */     this.rotationYaw = this.rand.nextFloat() * 360.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove() {
/* 32 */     if (getItem().getItem() instanceof AkumaNoMiItem && !getItem().isEmpty() && !this.world.isRemote)
/* 33 */       ExtendedWorldData.get(this.world).removeDevilFruitInWorld((AkumaNoMiItem)getItem().getItem()); 
/* 34 */     super.remove();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCollideWithPlayer(PlayerEntity player) {
/* 40 */     super.onCollideWithPlayer(player);
/* 41 */     if (!player.world.isRemote && !cannotPickup()) {
/* 42 */       ExtendedWorldData.get(this.world).addDevilFruitInWorld((AkumaNoMiItem)getItem().getItem());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void updatePortal() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> createSpawnPacket() {
/* 54 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\DFItemEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */