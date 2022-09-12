package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.ChampFightProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doru.DoruDoruArtsMoriProjectile;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class DoruDoruArtsMoriAbility extends RepeaterAbility {
  public static final DoruDoruArtsMoriAbility INSTANCE = new DoruDoruArtsMoriAbility();

  
  public DoruDoruArtsMoriAbility() {
    super("Doru Doru Arts: Mori", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(4.0D);
    setDescription("The user fires a harpoon made of wax at the opponent");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      
      setMaxRepeaterCount(25, 2);
      setMaxCooldown(12.0D);
    
    }
    else {

      
      setMaxRepeaterCount(0, 0);
      setMaxCooldown(4.0D);
      DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    } 
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      
      int projectileSpace = 2;
      
      ChampFightProjectile proj = new ChampFightProjectile(player.world, (LivingEntity)player);
      proj.setLocationAndAngles(player
          .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
          .getPosY() + 2.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
          .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
      
      proj.setChangeHurtTime(true);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    }
    else {
      
      DoruDoruArtsMoriProjectile proj = new DoruDoruArtsMoriProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    } 
    return true;
  }

  
  public void enableChampionMode() {
    setDisplayName("Champ Fight");
    setCustomTexture("champ_fight");
  }

  
  public void disableChampionMode() {
    setDisplayName("");
    setCustomTexture("");
  }
}


