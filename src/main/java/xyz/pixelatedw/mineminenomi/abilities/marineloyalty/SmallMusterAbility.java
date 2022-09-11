package xyz.pixelatedw.mineminenomi.abilities.marineloyalty;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SmallMusterAbility extends Ability {
  public static final SmallMusterAbility INSTANCE = new SmallMusterAbility();

  
  public SmallMusterAbility() {
    super("Small Muster", AbilityHelper.getFactionCategory());
    setMaxCooldown(20.0D);
    setDescription("The user musters reinforcements");
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
    EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
    for (int i = 0; i < WyHelper.randomWithRange(1, 5); i++) {
      
      EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
      
      BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, gruntEntity, player.getPosition(), 10);
      if (spawnPos != null) {
        gruntEntity.spawn(player.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
      }
    } 
    
    return true;
  }
}


