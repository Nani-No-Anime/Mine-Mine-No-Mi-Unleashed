package xyz.pixelatedw.mineminenomi.abilities.yuki;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.FubukiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class FubukiAbility extends Ability {
  public static final FubukiAbility INSTANCE = new FubukiAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new FubukiParticleEffect();

  
  public FubukiAbility() {
    super("Fubuki", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("The user releases an extremely cold stream around themselves inflicting §2Frostbite§r and causing serious damage to those affected by it.");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BlockProtectionRule.IReplaceBlockRule test = (world, pos, state) -> 
      (world.getBlockState(pos.down()).isSolid() && world.getBlockState(pos.down()).getBlock() != Blocks.SNOW);

    
    AbilityHelper.createSphere(player.world, player.getPosition(), 25, 25, false, Blocks.SNOW, test, 3, DefaultProtectionRules.AIR_FOLIAGE);
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 25.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity entity : targets) {
      
      AbilityHelper.addFrostbite(entity, (LivingEntity)player, 110);
      
      if (entity.isPotionActive(ModEffects.FROSTBITE) || entity.isPotionActive(ModEffects.FROZEN)) {
        entity.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, this), 20.0F);
      }
    } 
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }
}


