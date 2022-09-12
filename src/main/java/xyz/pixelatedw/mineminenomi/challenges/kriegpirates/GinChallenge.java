package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.challenges.ArenaData;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.GinEntity;
import xyz.pixelatedw.mineminenomi.init.ModArenas;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class GinChallenge extends Challenge {
  public GinChallenge() {
    super("Gin");
    setCategory("Krieg Pirates");
    setArena(ModArenas.BARATIE);
    setObjective("Defeat Gin");
    setTimeLimit(10);
    
    this.reward.setDoriki(100);
    this.reward.setBounty(1000);
    this.reward.addItem(new ItemStack((IItemProvider)ModWeapons.TONFA));
    
    this.onStartEvent = this::onStartEvent;
  }

  
  private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
    data.build(world);
    
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
    player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
    
    GinEntity boss = new GinEntity(player.world);
    boss.setLocationAndAngles(data.getBossSpawn().getX(), data.getBossSpawn().getY(), data.getBossSpawn().getZ(), 0.0F, 0.0F);
    boss.onInitialSpawn((IWorld)player.world, player.world.getDifficultyForLocation(player.getPosition()), SpawnReason.EVENT, (ILivingEntityData)null, (CompoundNBT)null);
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
    return ModEntities.GIN;
  }
}


