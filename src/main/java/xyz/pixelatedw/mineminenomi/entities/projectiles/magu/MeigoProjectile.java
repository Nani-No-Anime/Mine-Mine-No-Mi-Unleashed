/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.projectile.ThrowableEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class MeigoProjectile extends AbilityProjectileEntity {
/* 25 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public MeigoProjectile(World world) {
/* 29 */     super(MaguProjectiles.MEIGO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeigoProjectile(EntityType type, World world) {
/* 34 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeigoProjectile(World world, double x, double y, double z) {
/* 39 */     super(MaguProjectiles.MEIGO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeigoProjectile(World world, LivingEntity player) {
/* 44 */     super(MaguProjectiles.MEIGO, world, player);
/*    */     
/* 46 */     setDamage(100.0F);
/* 47 */     setMaxLife(4);
/* 48 */     setPassThroughEntities();
/* 49 */     setCanGetStuckInGround();
/* 50 */     setAffectedByHardening();
/* 51 */     setDamageSource((DamageSource)ModDamageSource.MAGMA.causeIndirectDamageFromSource((ThrowableEntity)this));
/*    */     
/* 53 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 54 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 55 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 60 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 20);
/* 61 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 62 */       hitEntity.setFire(20);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 67 */     AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.LAVA, GRIEF_RULE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 72 */     if (!this.world.isRemote) {
/*    */       int i;
/* 74 */       for (i = 0; i < 3; i++) {
/*    */         
/* 76 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 77 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 78 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 80 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 81 */         data.setLife(5);
/* 82 */         data.setSize(1.3F);
/* 83 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */       
/* 86 */       for (i = 0; i < 10; i++) {
/*    */         
/* 88 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 89 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 90 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 92 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.LAVA, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.5D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\magu\MeigoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */