package xyz.pixelatedw.mineminenomi.abilities.ope;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class OpeHelper {
  public static boolean hasRoomActive(PlayerEntity entity, Ability inst) {
    RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)RoomAbility.INSTANCE);
    if (ability == null || !ability.isEntityInThisRoom((Entity)entity)) {
      
      entity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { inst.getName() }));
      return false;
    } 
    return true;
  }
}


