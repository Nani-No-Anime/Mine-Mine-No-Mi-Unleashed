package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class BiganAbility extends PunchAbility implements IFormRequiredAbility {
  public static final BiganAbility INSTANCE = new BiganAbility();

  
  public BiganAbility() {
    super("Bigan", AbilityHelper.getDevilFruitCategory());
    setDescription("Hits using the hardened giraffe nose");
    setMaxCooldown(5.0D);
    
    this.onHitEntityEvent = this::onHitEntityEvent;
  }

  
  private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
    return 25.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)GiraffeHeavyZoanInfo.INSTANCE, (ZoanInfo)GiraffeWalkZoanInfo.INSTANCE };
  }
}


