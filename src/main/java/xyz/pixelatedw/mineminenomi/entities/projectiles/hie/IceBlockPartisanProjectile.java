/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class IceBlockPartisanProjectile extends AbilityProjectileEntity {
/*    */   public IceBlockPartisanProjectile(World world) {
/* 20 */     super(HieProjectiles.ICE_BLOCK_PARTISAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPartisanProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPartisanProjectile(World world, double x, double y, double z) {
/* 30 */     super(HieProjectiles.ICE_BLOCK_PARTISAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public IceBlockPartisanProjectile(World world, LivingEntity player) {
/* 35 */     super(HieProjectiles.ICE_BLOCK_PARTISAN, world, player);
/*    */     
/* 37 */     setDamage(9.0F);
/* 38 */     setMaxLife(40);
/* 39 */     setPhysical(false);
/* 40 */     setAffectedByImbuing();
/* 41 */     setChangeHurtTime(true);
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 45 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 51 */     AbilityHelper.addFrostbite(entity, getThrower(), 40);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 56 */     AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY(), hit.getZ(), Blocks.BLUE_ICE, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 61 */     if (!this.world.isRemote)
/*    */     {
/* 63 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 65 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 66 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 67 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 69 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 70 */         data.setLife(2);
/* 71 */         data.setSize(1.5F);
/* 72 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\IceBlockPartisanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */