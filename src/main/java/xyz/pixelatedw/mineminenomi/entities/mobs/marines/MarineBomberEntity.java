package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import javax.annotation.Nullable;

public class MarineBomberEntity extends AbstractMarineEntity implements IRangedAttackMob {
  private static final String[] DEFAULT_TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5" };

  
  public MarineBomberEntity(World world) {
    super(ModEntities.MARINE_BOMBER, world, DEFAULT_TEXTURES);
  }


  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 200, 30.0F));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20999999344348907D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    
    setDoriki(20.0D + WyHelper.randomWithRange(0, 10));
    setBelly(5.0D + WyHelper.randomWithRange(0, 10));
  }


  
  protected void registerData() {
    super.registerData();
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.BAZOOKA));
    
    return spawnData;
  }


  
  public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
    if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.BAZOOKA)) {
      return;
    }
    CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.world, (LivingEntity)this);
    cannonBallProjectile.setDamage(cannonBallProjectile.getDamage() * 0.8F);
    ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {
        if (this.ticksExisted < 0) {
          return;
        }
        AbilityProjectileEntity proj = cannonBallProjectile;
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj, this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
        explosion.setStaticDamage(5.0F);
        explosion.setDestroyBlocks(false);
        explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
        explosion.doExplosion();
      });
    double velX = target.getPosX() - getPosX();
    double velY = (target.getBoundingBox()).minY - cannonBallProjectile.getPosY();
    double velZ = target.getPosZ() - getPosZ();
    double x = MathHelper.sqrt(velX * velX + velZ * velZ);
    
    cannonBallProjectile.shoot(velX, velY + x * 0.10000000149011612D, velZ, 2.0F, MathHelper.clamp(12 - this.world.getDifficulty().getId() * 4, 0, 100));
    this.world.addEntity((Entity)cannonBallProjectile);
  }
}


