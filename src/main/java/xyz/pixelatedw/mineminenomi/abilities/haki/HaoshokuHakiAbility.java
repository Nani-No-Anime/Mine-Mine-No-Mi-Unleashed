package xyz.pixelatedw.mineminenomi.abilities.haki;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.List;

public class HaoshokuHakiAbility extends ChargeableAbility {
  public static final HaoshokuHakiAbility INSTANCE = new HaoshokuHakiAbility();
  public static final ParticleEffect PARTICLES_1 = (ParticleEffect)new HaoshokuHakiParticleEffect(1);
  public static final ParticleEffect PARTICLES_2 = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
  public static final ParticleEffect PARTICLES_3 = (ParticleEffect)new HaoshokuHakiParticleEffect(3);

  
  public HaoshokuHakiAbility() {
    super("Haoshoku Haki", AbilityHelper.getHakiCategory());
    setDescription("A burst of the unique Conqueror's haki, that knocks out enemies that are weaker than the user");
    setMaxChargeTime(3.0D);
    
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    hakiProps.alterHakiOveruse(1200);
    
    player.world.playSound(null, player.getPosition(), ModSounds.HAKI_RELEASE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    float haoLevel = HakiHelper.getTotalHakiExp((LivingEntity)player) / 100.0F;
    
    int cooldown = 0;
    double radius = 0.0D;
    int unconsciousTimer = 0;
    
    if (haoLevel < 1.0F) {
      
      radius = 10.0D;
      unconsciousTimer = 0;
      cooldown = 120;
      PARTICLES_1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    else if (haoLevel >= 1.0F && haoLevel < 2.5D) {
      
      radius = 25.0D;
      unconsciousTimer = 100;
      cooldown = 60;
      PARTICLES_2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    else if (haoLevel >= 2.5D) {
      
      radius = 40.0D;
      unconsciousTimer = 200;
      cooldown = 60;
      PARTICLES_3.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity target : targets) {
      
      if (unconsciousTimer > 0) {
        
        float targetHaoLevel = 0.0F;
        if (target instanceof PlayerEntity) {
          
          float hakiLevel = HakiHelper.getTotalHakiExp(target) / 100.0F;
          float dorikiConversion = EntityStatsCapability.get(target).getDoriki() / 10000.0F;
          targetHaoLevel = hakiLevel + dorikiConversion;
        }
        else if (target instanceof OPEntity) {
          
          float busoHaki = ((OPEntity)target).goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiGoal) ? 1.0F : 0.0F;
          float dorikiConversion = ((OPEntity)target).getDoriki() / 100.0F;
          
          targetHaoLevel = busoHaki + dorikiConversion;
        } 

        
        if (targetHaoLevel + 0.5D >= haoLevel) {
          continue;
        }
        EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, unconsciousTimer, 1, false, false);
        target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, unconsciousTimer - 20, 0, false, false));
        target.addPotionEffect(instance);
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
        
        continue;
      } 
      target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
    } 

    
    setMaxCooldown(cooldown);
    return true;
  }
}


