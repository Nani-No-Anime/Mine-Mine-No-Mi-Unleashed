package xyz.pixelatedw.mineminenomi.abilities.magu;


import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class BakuretsuKazanAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new BakuretsuKazanAbility();
  public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });

  
  public BakuretsuKazanAbility() {
    super("Bakuretsu Kazan", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(25.0D);
    setMaxChargeTime(5.0D);
    setCancelable();
    setDescription("By spreading magma to the surroundings, the user turns everything into lava");
    
    this.onEndChargingEvent = this::endChargingEvent;
  }

  
  private boolean endChargingEvent(PlayerEntity player) {
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    int range = (int)(16.0F * multiplier);
    if (range < 5) {
      return false;
    }
    AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), range, Blocks.LAVA, GRIEF_RULE);
    player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
    return true;
  }
}


