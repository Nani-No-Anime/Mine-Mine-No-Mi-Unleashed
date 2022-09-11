package xyz.pixelatedw.mineminenomi.api.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestChooseScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public abstract class TrainerEntity
  extends OPEntity
{
  public TrainerEntity(EntityType type, World world) {
    this(type, world, null);
  }

  
  public TrainerEntity(EntityType type, World world, String[] textures) {
    super(type, world, textures);
  }


  
  public void registerGoals() {
    super.registerGoals();
    
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    props.setFaction("civilian");
  }


  
  public boolean canSpawn(IWorld world, SpawnReason reason) {
    return true;
  }


  
  protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}

  
  public boolean canDespawn(double distance) {
    if (super.canDespawn(distance) && distance > 1024.0D) {
      return true;
    }
    return false;
  }

  
  public abstract Quest[] getAvailableQuests(PlayerEntity paramPlayerEntity);

  
  protected boolean processInteract(PlayerEntity player, Hand hand) {
    if (hand != Hand.MAIN_HAND) {
      return false;
    }
    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == Items.NAME_TAG) {
      return false;
    }
    if (!player.world.isRemote) {
      
      WyNetwork.sendTo(new SOpenQuestChooseScreenPacket(getEntityId()), player);
      if (this instanceof xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.IHakiTrainer)
      {
        WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), HakiDataCapability.get((LivingEntity)player)), player);
      }
      return true;
    } 
    return false;
  }
}


