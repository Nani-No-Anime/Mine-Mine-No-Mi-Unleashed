package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.doru.CandleChampionAbility;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doru.CandleLockParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class CandleLockProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new CandleLockParticleEffect();

  
  public CandleLockProjectile(World world) {
    super(DoruProjectiles.CANDLE_LOCK, world);
  }

  
  public CandleLockProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CandleLockProjectile(World world, double x, double y, double z) {
    super(DoruProjectiles.CANDLE_LOCK, world, x, y, z);
  }

  
  public CandleLockProjectile(World world, LivingEntity player) {
    super(DoruProjectiles.CANDLE_LOCK, world, player);
    
    setDamage(8.0F);
    setMaxLife(20);
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    
    this.withEffects = this::withEffects;
    
    Ability ability = AbilityDataCapability.get(this.owner).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      setCollisionSize(3.0D);
    }
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    BlockPos pos = null;
    int j = 1;
    
    while (pos == null) {
      
      BlockState state = this.world.getBlockState(getPosition().down(j));
      
      if (state.isSolid()) {
        
        pos = getPosition().down(j);
        
        break;
      } 
      if (j > 5) {
        break;
      }
      j++;
    } 
    
    if (pos == null) {
      return;
    }
    PARTICLES.spawn(this.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private EffectInstance[] withEffects() {
    if (this.owner == null)
      remove(); 
    if (this.owner instanceof PlayerEntity) {
      
      int time = 200;
      int modifier = 1;
      
      if (((PlayerEntity)this.owner).inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
        modifier = 2;
      }
      Ability ability = AbilityDataCapability.get(this.owner).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
      if (ability != null && ability.isContinuous()) {
        time = 300;
      }
      return new EffectInstance[] { new EffectInstance(ModEffects.CANDLE_LOCK, time, modifier) };
    } 
    return null;
  }
}


