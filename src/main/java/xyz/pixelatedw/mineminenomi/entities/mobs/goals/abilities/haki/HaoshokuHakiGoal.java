package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;

public class HaoshokuHakiGoal extends CooldownGoal {
  public static final ParticleEffect PARTICLES_1 = (ParticleEffect)new HaoshokuHakiParticleEffect(1);
  public static final ParticleEffect PARTICLES_2 = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
  public static final ParticleEffect PARTICLES_3 = (ParticleEffect)new HaoshokuHakiParticleEffect(3);
  
  private OPEntity entity;
  private float hakiXP;
  
  public HaoshokuHakiGoal(OPEntity entity, float hxp) {
    super(entity, 120, entity.getRNG().nextInt(10));
    this.entity = entity;
    this.entity.addThreat(20);
    this.hakiXP = hxp;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
  }

  
  public boolean shouldExecute() {
    if (!super.shouldExecute()) {
      return false;
    }
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
      return false;
    }
    if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 5.0F) {
      return false;
    }
    execute((LivingEntity)this.entity);
    return true;
  }

  
  private void execute(LivingEntity player) {
    IHakiData hakiProps = HakiDataCapability.get(player);
    hakiProps.alterHakiOveruse(400);
    
    player.world.playSound(null, player.getPosition(), ModSounds.HAKI_RELEASE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    float haoLevel = this.hakiXP / 100.0F;
    
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
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate(player), new Class[] { LivingEntity.class });
    
    targets.remove(player);
    
    this.maxCooldown = cooldown;
    
    for (LivingEntity target : targets) {
      
      if (unconsciousTimer > 0) {
        
        float targetHaoLevel = 0.0F;
        if (target instanceof net.minecraft.entity.player.PlayerEntity) {
          
          targetHaoLevel = HakiHelper.getTotalHakiExp(target) / 100.0F;
        }
        else if (target instanceof OPEntity) {
          
          float busoHaki = ((OPEntity)target).goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal() instanceof BusoshokuHakiGoal) ? 1.0F : 0.0F;
          float dorikiConversion = (((OPEntity)target).getDoriki() / 100);
          
          targetHaoLevel = busoHaki + dorikiConversion;
        } 
        
        if (targetHaoLevel + 0.3D >= haoLevel) {
          continue;
        }
        EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, unconsciousTimer, 1, false, false);
        target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, unconsciousTimer - 20, 1, false, false));
        target.addPotionEffect(instance);
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
        
        continue;
      } 
      target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
    } 
  }
}


