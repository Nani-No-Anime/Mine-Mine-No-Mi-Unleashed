package xyz.pixelatedw.mineminenomi.entities.projectiles.netsu;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class NekkaiGyoraiProjectile extends AbilityProjectileEntity {
  private int damage = 10;

  
  public NekkaiGyoraiProjectile(World world) {
    super(NetsuProjectiles.NEKKAI_GYORAI, world);
  }

  
  public NekkaiGyoraiProjectile(World world, LivingEntity player) {
    super(NetsuProjectiles.NEKKAI_GYORAI, world, player);
    
    IAbilityData abilityProps = AbilityDataCapability.get(player);
    NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      this.damage += 5;
    }
    setDamage(this.damage);
    setDamageSource(DamageSource.IN_FIRE);
    setMaxLife(30);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    hitEntity.setFire(4);
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        ParticleType<GenericParticleData> particle = ModParticleTypes.NETSU;
        if (i % 3 == 0)
          particle = ModParticleTypes.NETSU2; 
        if (i % 7 == 0) {
          particle = ModParticleTypes.MERA;
        }
        GenericParticleData data = new GenericParticleData(particle);
        data.setLife(10);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


