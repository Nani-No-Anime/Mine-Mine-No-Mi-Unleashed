/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.hie.IceAgeAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.IFlexibleSizeProjectile;
/*    */ 
/*    */ public class IceBlockAvalancheProjectile
/*    */   extends AbilityProjectileEntity implements IFlexibleSizeProjectile {
/*    */   public boolean finalized = false;
/*    */   
/*    */   public IceBlockAvalancheProjectile(World world) {
/* 17 */     super(HieProjectiles.ICE_BLOCK_AVALANCHE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockAvalancheProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockAvalancheProjectile(World world, double x, double y, double z) {
/* 27 */     super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockAvalancheProjectile(World world, LivingEntity player) {
/* 32 */     super(HieProjectiles.ICE_BLOCK_AVALANCHE, world, player);
/* 33 */     setDamage(50.0F);
/* 34 */     setMaxLife(150);
/* 35 */     setPhysical(false);
/* 36 */     setPassThroughEntities();
/* 37 */     setCanGetStuckInGround();
/*    */     
/* 39 */     this.onTickEvent = this::onTickEvent;
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 45 */     float mult = getSize() / 6.0F;
/* 46 */     setBoundingBox(getBoundingBox().grow(mult));
/* 47 */     setCollisionSize(mult);
/*    */     
/* 49 */     if (!this.finalized) {
/* 50 */       setSize(getSize() + 0.4F);
/*    */     }
/* 52 */     if (this.world.getBlockState(getPosition().down()).isSolid()) {
/* 53 */       setMotion(0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onBlockImpactEvent(BlockPos pos) {
/* 58 */     if (!this.world.isRemote) {
/* 59 */       IceAgeAbility.PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerData() {
/* 65 */     super.registerData();
/* 66 */     this.dataManager.register(SIZE, Float.valueOf(1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 72 */     this.dataManager.set(SIZE, Float.valueOf(Math.min(size, 50.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 78 */     return ((Float)this.dataManager.get(SIZE)).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockAvalancheProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */