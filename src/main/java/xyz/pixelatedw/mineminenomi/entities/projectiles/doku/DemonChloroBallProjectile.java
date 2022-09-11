package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallCloudParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DemonChloroBallProjectile extends AbilityProjectileEntity {
  private static final ChloroBallParticleEffect PARTICLES1 = new ChloroBallParticleEffect();
  private static final ChloroBallCloudParticleEffect PARTICLES2 = new ChloroBallCloudParticleEffect();

  
  public DemonChloroBallProjectile(World world) {
    super(DokuProjectiles.DEMON_CHLORO_BALL, world);
  }

  
  public DemonChloroBallProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public DemonChloroBallProjectile(World world, double x, double y, double z) {
    super(DokuProjectiles.DEMON_CHLORO_BALL, world, x, y, z);
  }

  
  public DemonChloroBallProjectile(World world, LivingEntity player) {
    super(DokuProjectiles.DEMON_CHLORO_BALL, world, player);
    
    setDamage(12.0F);
    setPhysical(false);
    setAffectedByImbuing();
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 300, 4));
    this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
  }

  
  private void onBlockImpactEvent(BlockPos pos) {
    for (int i = 0; i < 80; i++) {
      
      double offsetX = WyHelper.randomWithRange(-5, 5);
      double offsetZ = WyHelper.randomWithRange(-5, 5);
      
      BlockPos location = new BlockPos(getPosX() + offsetX, getPosY(), getPosZ() + offsetZ);
      
      if (this.world.getBlockState(location.down()).isSolid())
        AbilityHelper.placeBlockIfAllowed(this.world, location.getX(), location.getY(), location.getZ(), ModBlocks.DEMON_POISON, DefaultProtectionRules.AIR_FOLIAGE); 
    } 
    PARTICLES1.venomDemon = true;
    PARTICLES2.venomDemon = true;
    PARTICLES1.spawn(this.world, getPosX(), getPosY() + 1.0D, getPosZ(), 0.0D, 0.0D, 0.0D);
    
    DeathChloroBallCloudEntity smokeCloud = new DeathChloroBallCloudEntity(this.world);
    smokeCloud.setLife(30);
    smokeCloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
    smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
    smokeCloud.setThrower(getThrower());
    this.world.addEntity((Entity)smokeCloud);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
        data.setColor(1.0F, 0.0F, 0.0F);
        data.setLife(5);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
  
  public static class DeathChloroBallCloudEntity
    extends EntityCloud
  {
    public DeathChloroBallCloudEntity(World world) {
      super(world);
    }


    
    public void tick() {
      super.tick();
      if (!this.world.isRemote) {
        
        for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 8.0D)) {
          
          if (getThrower() != target && target.isPotionActive(Effects.POISON)) {
            target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 3));
          }
        } 
        if (this.ticksExisted % 2 == 0)
          DemonChloroBallProjectile.PARTICLES2.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
      } 
    }
  }
}


