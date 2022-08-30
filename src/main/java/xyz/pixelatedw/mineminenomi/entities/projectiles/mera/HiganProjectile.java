/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.projectile.ThrowableEntity;
/*    */ import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class HiganProjectile extends AbilityProjectileEntity {
/*    */   public HiganProjectile(World world) {
/* 27 */     super(MeraProjectiles.HIGAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HiganProjectile(EntityType type, World world) {
/* 32 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HiganProjectile(World world, double x, double y, double z) {
/* 37 */     super(MeraProjectiles.HIGAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public HiganProjectile(World world, LivingEntity player) {
/* 42 */     super(MeraProjectiles.HIGAN, world, player);
/*    */     
/* 44 */     setDamage(10.0F);
/* 45 */     setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/* 46 */     setChangeHurtTime(true);
/*    */     
/* 48 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 49 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 50 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 55 */     SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 4);
/* 56 */     if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 57 */       hitEntity.setFire(4);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 62 */     BlockState state = this.world.getBlockState(hit);
/* 63 */     if (state.getBlock() == Blocks.CAMPFIRE) {
/* 64 */       this.world.setBlockState(hit, (BlockState)state.with((IProperty)BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
/*    */     } else {
/* 66 */       AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 71 */     if (areEyesInFluid(FluidTags.WATER)) {
/*    */       
/* 73 */       remove();
/* 74 */       getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 77 */     if (!this.world.isRemote)
/*    */     {
/* 79 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 81 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 82 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 83 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */         
/* 85 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 86 */         data.setLife(10);
/* 87 */         data.setSize(1.3F);
/* 88 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\HiganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */