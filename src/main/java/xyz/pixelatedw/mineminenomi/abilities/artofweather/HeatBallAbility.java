package xyz.pixelatedw.mineminenomi.abilities.artofweather;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.ArtOfWeatherHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.HeatBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class HeatBallAbility extends Ability {
  public static final HeatBallAbility INSTANCE = new HeatBallAbility();
  
  private static final ParticleEffect CHARGE_PARTICLES = (ParticleEffect)new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#FF0000"), ModParticleTypes.GASU);

  
  public HeatBallAbility() {
    super("Heat Ball", AbilityHelper.getStyleCategory());
    setDescription("Launch a Heat Ball from your Clima Tact to use for different Tempos\n\n§2SHIFT-USE§r: Loads the ball into the clima tact");
    setMaxCooldown(1.0D);
    addInPool(new AbilityPool[] { AbilityPool.WEATHER_BALLS });
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
      return false;
    } 
    
    ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
    
    player.getHeldItemMainhand().damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));


    
    if (player.isSneaking()) {
      
      climaTact.chargeWeatherBall(player.getHeldItemMainhand(), "H");
      ArtOfWeatherHelper.checkForTempo(player, CHARGE_PARTICLES);
    }
    else {
      
      HeatBallProjectile proj = new HeatBallProjectile(player.world, (LivingEntity)player);
      proj.setLocationAndAngles(trace.getHitVec().getX(), player.getPosY() + player.getEyeHeight() - 0.5D, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
      proj.setMotion(0.0D, 0.3D, 0.0D);
      player.world.addEntity((Entity)proj);
    } 
    
    return true;
  }
}


