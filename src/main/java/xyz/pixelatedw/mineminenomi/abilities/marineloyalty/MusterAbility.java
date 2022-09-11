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

public class MusterAbility extends Ability {
  public static final MusterAbility INSTANCE = new MusterAbility();

  
  public MusterAbility() {
    super("Muster", AbilityHelper.getFactionCategory());
    setMaxCooldown(60.0D);
    setDescription("The user musters some higher level reinforcements");
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
    EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
    EntityType captain = ModEntities.MARINE_CAPTAIN;
    int i;
    for (i = 0; i < WyHelper.randomWithRange(3, 10); i++) {
      
      EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
      
      BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, gruntEntity, player.getPosition(), 10);
      if (spawnPos != null) {
        gruntEntity.spawn(player.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
      }
    } 
    for (i = 0; i < WyHelper.randomWithRange(1, 3); i++) {

      
      BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(player.world, captain, player.getPosition(), 10);
      captain.spawn(player.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
    } 
    
    return true;
  }
}


