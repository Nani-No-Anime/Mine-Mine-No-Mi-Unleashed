package xyz.pixelatedw.mineminenomi.entities.projectiles.magu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RyuseiKazanProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });

  
  public RyuseiKazanProjectile(World world) {
    super(MaguProjectiles.BAKURETSU_KAZAN, world);
  }

  
  public RyuseiKazanProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public RyuseiKazanProjectile(World world, double x, double y, double z) {
    super(MaguProjectiles.BAKURETSU_KAZAN, world, x, y, z);
  }

  
  public RyuseiKazanProjectile(World world, LivingEntity player) {
    super(MaguProjectiles.BAKURETSU_KAZAN, world, player);
    
    setDamage(20.0F);
    setGravity(0.01F);
    setChangeHurtTime(true);
    setAffectedByHardening();
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }
  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 8);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(8);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.createFilledSphere(getEntityWorld(), (int)getPosX(), (int)getPosY(), (int)getPosZ(), 2, Blocks.LAVA, GRIEF_RULE);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(3);
        data.setSize(2.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MAGU);
        data.setLife(3);
        data.setSize(2.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }
}


