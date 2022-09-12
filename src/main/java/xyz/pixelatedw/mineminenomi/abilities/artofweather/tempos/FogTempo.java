package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;

public class FogTempo extends TempoAbility {
  public static final FogTempo INSTANCE = new FogTempo();

  
  public FogTempo() {
    super("Fog Tempo", AbilityHelper.getStyleCategory());
    setDescription("2 Charged Heat Balls + Charged Cool Ball\nCreates a thick fog at the user's position");
    setMaxCooldown(10.0D);
    setCustomTexture("tempo");
    
    this.onUseEvent = this::onUseEvent;
    this.canUseCheck = this::canUseCheck;
  }

  
  public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) {
    if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
      
      ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
      String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
      return tempoCombo.equalsIgnoreCase("HHC");
    } 
    
    return false;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
    smokeCloud.setLife(100);
    smokeCloud.setLocationAndAngles(player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0F, 0.0F);
    smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
    smokeCloud.setThrower((LivingEntity)player);
    player.world.addEntity((Entity)smokeCloud);
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D);
    targets.remove(player);
    
    for (LivingEntity entity : targets)
    {
      entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
    }
    
    return true;
  }
}


