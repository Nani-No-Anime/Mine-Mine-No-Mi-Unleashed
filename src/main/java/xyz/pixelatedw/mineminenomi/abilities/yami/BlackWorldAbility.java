package xyz.pixelatedw.mineminenomi.abilities.yami;


import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackWorldParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BlackWorldAbility
  extends Ability {
  public static final BlackWorldAbility INSTANCE = new BlackWorldAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BlackWorldParticleEffect();
  private static final BlockProtectionRule GRIEF_RULE = (BlockProtectionRule)AirBlockProtectionRule.INSTANCE;

  
  public BlackWorldAbility() {
    super("Black World", AbilityHelper.getDevilFruitCategory());
    setDescription("The user spreads their power to the surroundings, blinding enemies and creating pillars of Darkness");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    for (int i = 0; i < 8; i++) {
      
      int a1 = (int)WyHelper.randomWithRange(-10, 10);
      int a2 = (int)WyHelper.randomWithRange(-10, 10);
      
      for (int j = -5; j < 5; j++) {
        
        AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2), ModBlocks.DARKNESS, GRIEF_RULE);
        AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1 + 1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2), ModBlocks.DARKNESS, GRIEF_RULE);
        AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2 + 1), ModBlocks.DARKNESS, GRIEF_RULE);
        AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1 + 1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2 + 1), ModBlocks.DARKNESS, GRIEF_RULE);
      } 
    } 
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    for (LivingEntity target : targets) {
      
      target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1, false, false));
      target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
    } 
    
    return true;
  }
}


