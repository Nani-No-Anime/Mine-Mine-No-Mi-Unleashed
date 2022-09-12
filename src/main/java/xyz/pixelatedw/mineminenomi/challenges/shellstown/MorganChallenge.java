package xyz.pixelatedw.mineminenomi.challenges.shellstown;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MorganEntity;
import xyz.pixelatedw.mineminenomi.init.ModArenas;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class MorganChallenge extends Challenge {
  public MorganChallenge() {
    super("Morgan");
    setCategory("Marine 153rd Branch");
    setArena(ModArenas.MARINE_153rd_BRANCH);
    setObjective("Defeat \"Axe-Hand\" Morgan");
    setTimeLimit(10);
    
    this.reward.setBounty(2000);
    
    this.onStartEvent = this::onStartEvent;
  }

  
  private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
    data.build(world);
    
    player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
    
    MorganEntity boss = new MorganEntity(world);
    boss.setLocationAndAngles(data.getBossSpawn().getX(), data.getBossSpawn().getY(), data.getBossSpawn().getZ(), 0.0F, 0.0F);
    boss.onInitialSpawn((IWorld)world, world.getDifficultyForLocation(player.getPosition()), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
    if (!player.isCreative() && !player.isSpectator())
      boss.setAttackTarget((LivingEntity)player); 
    world.addEntity((Entity)boss);
    boss.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
    
    data.getArenaTileEntity().setChallenge(this);
    data.getArenaTileEntity().addPlayer((PlayerEntity)player);
    data.getArenaTileEntity().addTarget((LivingEntity)boss);
    
    return true;
  }


  
  public EntityType getTarget() {
    return ModEntities.MORGAN;
  }
}


