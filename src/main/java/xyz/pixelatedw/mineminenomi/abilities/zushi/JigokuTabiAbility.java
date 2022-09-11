package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class JigokuTabiAbility extends ContinuousAbility {
  public static final JigokuTabiAbility INSTANCE = new JigokuTabiAbility();

  
  public JigokuTabiAbility() {
    super("Jigoku Tabi", AbilityHelper.getDevilFruitCategory());
    setDescription("Causes a powerful downward force of gravity, sending the enemies down in a crater");
    setMaxCooldown(20.0D);
    setThreshold(12.0D);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int activeTimer) {
    int range = 24;
    GraviZoneAbility.gravityRing((LivingEntity)player, range, 0, true);
    
    if (++activeTimer % 20 == 0) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, range, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      
      for (LivingEntity entity : targets) {
        
        entity.setMotion(0.0D, (entity.getMotion()).y - 4.0D, 0.0D);
        entity.velocityChanged = true;
        EffectInstance instance = new EffectInstance(Effects.SLOWNESS, 25, 5, false, false);
        entity.addPotionEffect(instance);
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), instance));
        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
        GraviZoneAbility.gravityRing(entity, 3, 2, false);
        for (int x = -2; x < 2; x++) {
          for (int z = -2; z < 2; z++) {
            
            int posX = (int)entity.getPosX() + x;
            int posY = (int)entity.getPosY() - 1;
            int posZ = (int)entity.getPosZ() + z;
            AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE);
          } 
        } 
      } 
    } 
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 2;
    setMaxCooldown(cooldown);
    return true;
  }
}


