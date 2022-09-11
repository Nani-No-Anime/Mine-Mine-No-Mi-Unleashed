package xyz.pixelatedw.mineminenomi.challenges.kriegpirates;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
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
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.init.ModArenas;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModChallenges;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class DonKriegChallenge extends Challenge {
  public DonKriegChallenge() {
    super("Don Krieg");
    setCategory("Krieg Pirates");
    setArena(ModArenas.BARATIE);
    addRequirements(new Challenge[] { ModChallenges.GIN });
    setObjective("Defeat Don Krieg");
    setTimeLimit(20);
    
    this.reward.setDoriki(200);
    this.reward.setBounty(5000);
    this.reward.addItem(new ItemStack((IItemProvider)ModArmors.WOOTZ_STEEL_ARMOR));
    
    this.onStartEvent = this::onStartEvent;
    this.onCompleteEvent = this::onCompleteEvent;
  }

  
  private void onCompleteEvent(ServerPlayerEntity player) {
    for (ItemStack stack : player.inventory.mainInventory) {
      
      if (stack.getItem() == ModArmors.MH5_GAS_MASK) {
        player.inventory.deleteStack(stack);
      }
    } 
    for (ItemStack stack : player.inventory.armorInventory) {
      
      if (stack.getItem() == ModArmors.MH5_GAS_MASK) {
        player.inventory.deleteStack(stack);
      }
    } 
  }
  
  private boolean onStartEvent(ServerPlayerEntity player, World world, ArenaData data) {
    data.build(world);
    
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0));
    player.teleport((ServerWorld)world, data.getPlayerSpawn().getX(), data.getPlayerSpawn().getY(), data.getPlayerSpawn().getZ(), 270.0F, 0.0F);
    
    DonKriegEntity boss = new DonKriegEntity(player.world);
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
    return ModEntities.DON_KRIEG;
  }
}


