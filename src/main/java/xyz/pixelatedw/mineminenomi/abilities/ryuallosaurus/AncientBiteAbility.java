package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class AncientBiteAbility extends PunchAbility implements IFormRequiredAbility {
  public static final AncientBiteAbility INSTANCE = new AncientBiteAbility();

  
  public AncientBiteAbility() {
    super("Ancient Bite", AbilityHelper.getDevilFruitCategory());
    setDescription("");
    setMaxCooldown(7.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    double x = WyHelper.randomDouble() * 2.0D;
    double y = WyHelper.randomDouble() * 0.3D;
    double z = WyHelper.randomDouble() * 2.0D;
    player.world.addParticle(ParticleTypes.CRIT, target.getPosX(), target.getPosY() + 1.0D, target.getPosZ(), x, y, z);
    target.addPotionEffect(new EffectInstance(ModEffects.BLEEDING, 20, 0));
    
    for (int i = 0; i < 50; i++) {
      
      Vec3d vec3d = new Vec3d((player.getRNG().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
      vec3d = vec3d.rotatePitch(-player.rotationPitch * 0.017453292F);
      vec3d = vec3d.rotateYaw(-player.rotationYaw * 0.017453292F);
      ((ServerWorld)player.world).spawnParticle(ParticleTypes.CRIT, target.getPosX(), target.getPosY() + 1.5D, target.getPosZ(), 1, vec3d.x, vec3d.y, vec3d.z, 0.8D);
    } 
  }

  
  public float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 20.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)AllosaurusHeavyZoanInfo.INSTANCE, (ZoanInfo)AllosaurusWalkZoanInfo.INSTANCE };
  }
}


