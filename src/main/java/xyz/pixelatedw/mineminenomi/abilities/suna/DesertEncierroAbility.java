package xyz.pixelatedw.mineminenomi.abilities.suna;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertEncierroParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class DesertEncierroAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new DesertEncierroAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new DesertEncierroParticleEffect();
  private LivingEntity grabbedEntity = null;

  
  public DesertEncierroAbility() {
    super("Desert Encierro", AbilityHelper.getDevilFruitCategory());
    setDescription("Quickly drains the enemy in front of the user of their moisture, leaving them weak for a few seconds");
    
    setMaxCooldown(15.0D);
    setMaxChargeTime(5.0D);
    
    this.onStartChargingEvent = this::onUseEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 3.0D);
    if (mop instanceof EntityRayTraceResult) {
      
      EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
      if (entityRayTraceResult.getEntity() instanceof LivingEntity)
      {
        LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
        if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
          return false;
        }
        this.grabbedEntity = e;
        SunaHelper.drainLiquids(this.grabbedEntity, (int)WyHelper.randomWithRange(0, 1), (int)WyHelper.randomWithRange(1, 3), (int)WyHelper.randomWithRange(0, 1));
      }
    
    } else {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!this.grabbedEntity.isAlive() || !AbilityHelper.canUseMomentumAbility(player) || (
      DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
      AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
      
      endCharging(player);
      
      return;
    } 
    PARTICLES.spawn(this.grabbedEntity.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 0.0D, 0.0D, 0.0D);
    this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
    this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
    
    this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    this.grabbedEntity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor().setDamageIsAbsolute(), 2.0F);
    
    float distance = 2.0F;
    Vec3d lookVec = player.getLookVec();
    Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
    this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.grabbedEntity == null) {
      return false;
    }
    this.grabbedEntity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor().setDamageIsAbsolute(), 20.0F);
    this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 4, false, false));
    this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.HUNGER, 300, 5, false, false));
    this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 300, 1, false, false));
    this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300, 1, false, false));
    this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 300, 1, false, false));
    
    return true;
  }
}


