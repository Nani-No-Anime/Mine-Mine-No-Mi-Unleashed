package xyz.pixelatedw.mineminenomi.abilities.ope;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.SpatialSlashProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class AmputateAbility extends Ability {
  public static final AmputateAbility INSTANCE = new AmputateAbility();

  
  public AmputateAbility() {
    super("Amputate", AbilityHelper.getDevilFruitCategory());
    setDescription("The user cuts the every block on his sight, horizontal sneaking causes a vertical cut");
    
    setMaxCooldown(5.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)RoomAbility.INSTANCE);
    if (ability == null || !ability.isEntityInThisRoom((Entity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { getName() }));
      return false;
    } 
    
    SpatialSlashProjectile proj = new SpatialSlashProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }
}


