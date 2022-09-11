package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class SpatialSlashProjectile extends AbilityProjectileEntity {
  public SpatialSlashProjectile(World world) {
    super(OpeProjectiles.SPATIAL_SLASH, world);
  }

  
  public SpatialSlashProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public SpatialSlashProjectile(World world, double x, double y, double z) {
    super(OpeProjectiles.SPATIAL_SLASH, world, x, y, z);
  }

  
  public SpatialSlashProjectile(World world, LivingEntity player) {
    super(OpeProjectiles.SPATIAL_SLASH, world, player);
    setDamageSource((new IndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
    setDamage(5.0F);
    setPhysical(false);
    setMaxLife(20);
    
    setCanGetStuckInGround();
    this.onBlockImpactEvent = this::onBlockImpact;
    this.onEntityImpactEvent = this::onEntityImpact;
  }
  
  private void onEntityImpact(LivingEntity entity) {
    boolean hakiCondition = (HakiHelper.getBusoHardeningHakiExp(entity) + WyHelper.randomWithRange(0, 5) >= HakiHelper.getBusoHardeningHakiExp(getThrower()));
    boolean specialCondition = (DevilFruitCapability.get(entity).isLogia() && !HakiHelper.hasHardeningActive(entity));
    boolean hpCondition = (getThrower().getHealth() >= entity.getHealth() + 10.0F);
    boolean blocking = AbilityHelper.isTargetBlockingAbility(getThrower(), entity);
    
    if (!hakiCondition && !specialCondition && !hpCondition && !blocking)
    {
      
      entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 200, 0, false, false));
    }
  }
  
  private void onBlockImpact(BlockPos blockPos) {
    this.world.removeBlock(blockPos, true);
  }


  
  public void tick() {
    super.tick();
    if (this.world.isRemote)
      return; 
    if (getThrower() != null && this.ticksExisted > 0 && (getThrower()).ticksExisted > 0) {
      
      RoomAbility a = (RoomAbility)AbilityDataCapability.get(getThrower()).getEquippedAbility((Ability)RoomAbility.INSTANCE);
      if (a == null) {
        remove();
      }
      if (!a.isEntityInThisRoom((Entity)this))
        remove(); 
    } 
  }
}


