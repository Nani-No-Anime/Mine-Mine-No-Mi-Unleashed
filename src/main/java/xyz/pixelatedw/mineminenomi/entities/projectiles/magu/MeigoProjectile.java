package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MeigoProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });

  
  public MeigoProjectile(World world) {
    super(MaguProjectiles.MEIGO, world);
  }

  
  public MeigoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MeigoProjectile(World world, double x, double y, double z) {
    super(MaguProjectiles.MEIGO, world, x, y, z);
  }

  
  public MeigoProjectile(World world, LivingEntity player) {
    super(MaguProjectiles.MEIGO, world, player);
    
    setDamage(100.0F);
    setMaxLife(4);
    setPassThroughEntities();
    setCanGetStuckInGround();
    setAffectedByHardening();
    setDamageSource((DamageSource)ModDamageSource.MAGMA.causeIndirectDamageFromSource((ThrowableEntity)this));
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 20);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(20);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.LAVA, GRIEF_RULE);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 3; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(5);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 10; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.LAVA, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.5D);
      } 
    } 
  }
}


