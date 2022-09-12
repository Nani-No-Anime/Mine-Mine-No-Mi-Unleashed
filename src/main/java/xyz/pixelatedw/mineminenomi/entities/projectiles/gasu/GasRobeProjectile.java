package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class GasRobeProjectile extends AbilityProjectileEntity {
  boolean shinokuni;
  ShinokuniAbility ability;
  
  public GasRobeProjectile(World world) { super(GasuProjectiles.GAS_ROBE, world);












    
    this.shinokuni = false; } public GasRobeProjectile(EntityType type, World world) { super(type, world); this.shinokuni = false; } public GasRobeProjectile(World world, double x, double y, double z) { super(GasuProjectiles.GAS_ROBE, world, x, y, z); this.shinokuni = false; }

  
  public GasRobeProjectile(World world, LivingEntity player) {
    super(GasuProjectiles.GAS_ROBE, world, player);
    this.shinokuni = false;
    setDamage(0.1F);
    setMaxLife(30);
    setPassThroughEntities();
    setChangeHurtTime(true);
    
    this.shinokuni = ShinokuniZoanInfo.INSTANCE.isActive(player);
    if (this.shinokuni) {
      
      this.ability = (ShinokuniAbility)AbilityDataCapability.get(player).getEquippedAbility((Ability)ShinokuniAbility.INSTANCE);
    } else {
      
      this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.WEAKNESS, 200, 2), new EffectInstance(Effects.BLINDNESS, 40, 0), new EffectInstance(Effects.POISON, 200, 6) });
    } 




    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity entity) {
    if (this.shinokuni) {
      this.ability.applyEffects((PlayerEntity)getThrower(), entity);
    }
  }
  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 5; i++) {
        
        double offsetX = WyHelper.randomDouble() / 3.0D;
        double offsetY = WyHelper.randomDouble() / 3.0D;
        double offsetZ = WyHelper.randomDouble() / 3.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
        data.setLife(5);
        data.setSize(2.0F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


