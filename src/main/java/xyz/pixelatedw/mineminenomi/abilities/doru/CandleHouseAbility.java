package xyz.pixelatedw.mineminenomi.abilities.doru;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class CandleHouseAbility extends Ability {
  public static final Ability INSTANCE = new CandleHouseAbility();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });

  
  public CandleHouseAbility() {
    super("Candle House", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(50.0D);
    setDescription("Creates a big house-like structure made of wax, to protect the user");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    int thiccness = 1;
    Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      thiccness = 3;
    }
    for (int y = -3; y <= 3; y++) {
      int i;
      for (i = 0; i < thiccness; i++) {
        for (int z = -5; z < 5; z++)
          AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() + 6.0D - i, player.getPosY() + y, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
      }  for (i = 0; i < thiccness; i++) {
        for (int z = -5; z < 5; z++)
          AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - 5.0D - i, player.getPosY() + y, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
      }  for (i = -5; i < 5; i++) {
        for (int z = 0; z < thiccness; z++)
          AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - i, player.getPosY() + y, player.getPosZ() + 6.0D - z, ModBlocks.WAX, GRIEF_RULE); 
      }  for (i = -5; i < 5; i++) {
        for (int z = 0; z < thiccness; z++)
          AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - i, player.getPosY() + y, player.getPosZ() - 5.0D - z, ModBlocks.WAX, GRIEF_RULE); 
      } 
    }  for (int x = -5; x < 5; x++) {
      for (int z = -5; z < 5; z++)
        AbilityHelper.placeBlockIfAllowed(player.world, player.getPosX() - x, player.getPosY() + 4.0D, player.getPosZ() - z, ModBlocks.WAX, GRIEF_RULE); 
    } 
    return true;
  }
}


