package xyz.pixelatedw.mineminenomi.api.helpers.abilities;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.FailedTempoParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ArtOfWeatherHelper {
  private static final ParticleEffect FAILED_TEMPO_PARTICLES = (ParticleEffect)new FailedTempoParticleEffect();

  
  public static void checkForTempo(PlayerEntity player, ParticleEffect chargeParticles) {
    ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
    String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
    
    if (tempoCombo.length() == 3) {
      
      List<TempoAbility> availableTempos = (List)props.getUnlockedAbilities(AbilityHelper.getStyleCategory()).stream().filter(ability -> ability instanceof TempoAbility).collect(Collectors.toList());
      boolean hasTempo = false;
      
      for (TempoAbility tempo : availableTempos) {
        
        if (tempo.canUseTempo(player, null)) {
          
          tempo.use(player);
          climaTact.emptyCharge(player.getHeldItemMainhand());
          hasTempo = true;
          
          break;
        } 
      } 
      if (!hasTempo) {
        
        FAILED_TEMPO_PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
        climaTact.emptyCharge(player.getHeldItemMainhand());
      } 
    } else {
      
      chargeParticles.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    } 
  }
}


