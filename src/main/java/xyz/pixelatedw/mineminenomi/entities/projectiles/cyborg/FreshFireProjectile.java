/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class FreshFireProjectile extends AbilityProjectileEntity {
/*    */   public FreshFireProjectile(World world) {
/* 23 */     super(CyborgProjectiles.FRESH_FIRE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public FreshFireProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public FreshFireProjectile(World world, double x, double y, double z) {
/* 33 */     super(CyborgProjectiles.FRESH_FIRE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public FreshFireProjectile(World world, LivingEntity player) {
/* 38 */     super(CyborgProjectiles.FRESH_FIRE, world, player);
/*    */     
/* 40 */     setDamage(1.0F);
/* 41 */     setPassThroughEntities();
/* 42 */     setMaxLife(15);
/* 43 */     setChangeHurtTime(true);
/* 44 */     setHurtTime(15);
/* 45 */     setDamageSource(DamageSource.IN_FIRE);
/*    */ 
/*    */     
/* 48 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 49 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 50 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 55 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
/* 56 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 57 */       hitEntity.setFire(4);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 62 */     AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 67 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 69 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 70 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 71 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 73 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 74 */       data.setLife(5);
/* 75 */       data.setSize(0.7F);
/* 76 */       WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\FreshFireProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */