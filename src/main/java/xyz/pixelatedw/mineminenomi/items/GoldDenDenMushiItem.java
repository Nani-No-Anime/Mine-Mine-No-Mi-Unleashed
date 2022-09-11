package xyz.pixelatedw.mineminenomi.items;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class GoldDenDenMushiItem
  extends Item
{
  public GoldDenDenMushiItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
  }


  
  public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
    super.inventoryTick(stack, world, entity, itemSlot, isSelected);
    
    if (!stack.hasTag() || !stack.getTag().getBoolean("inUse")) {
      return;
    }
    CompoundNBT nbt = stack.getTag();
    nbt.putInt("countdown", nbt.getInt("countdown") - 1);
    int[] pos = nbt.getIntArray("coords");
    
    if (nbt.getInt("countdown") > 40 && nbt.getInt("countdown") < 180 && nbt.getInt("countdown") % 5 == 0)
    {
      for (int i = 0; i < 20; i++) {
        
        CannonBallProjectile projectile = new CannonBallProjectile(world, (LivingEntity)entity);
        projectile.setPosition(pos[0] + WyHelper.randomWithRange(-50, 50), (pos[1] + 100), pos[2] + WyHelper.randomWithRange(-50, 50));
        projectile.setDamage(50.0F);
        projectile.setMaxLife(60);
        world.addEntity((Entity)projectile);
        projectile.shoot(entity, 90.0F, 0.0F, 0.0F, 3.0F, 0.0F);
      } 
    }
    
    if (nbt.getInt("countdown") == 0) {
      
      stack.shrink(1);
      
      EntityType captainEntity = ModEntities.MARINE_CAPTAIN;
      EntityType grunt1Entity = ModEntities.MARINE_WITH_SWORD;
      EntityType grunt2Entity = ModEntities.MARINE_WITH_GUN;
      
      int nrCaptains = (int)WyHelper.randomWithRange(10, 20);
      int nrGrunts = (int)WyHelper.randomWithRange(100, 200);
      
      int i;
      for (i = 0; i < nrCaptains; i++) {
        
        BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, captainEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
        if (spawnPos != null) {
          captainEntity.spawn(world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);
        }
      } 
      
      for (i = 0; i < nrGrunts; i++) {
        
        EntityType gruntEntity = (i % 2 == 0) ? grunt1Entity : grunt2Entity;
        
        BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(world, gruntEntity, new BlockPos(pos[0], pos[1], pos[2]), 50);
        if (spawnPos != null) {
          gruntEntity.spawn(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
        }
      } 
    } 
  }

  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!player.getHeldItem(hand).getOrCreateTag().getBoolean("inUse") && !world.isRemote) {
      
      CompoundNBT compoundNBT = player.getHeldItem(hand).getOrCreateTag();
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      
      if (!props.hasMarineRank(FactionHelper.MarineRank.ADMIRAL)) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_REQUIREMENT, new Object[0]));
        return ActionResult.resultFail(player.getHeldItem(hand));
      } 
      
      compoundNBT.putInt("countdown", 1000);
      compoundNBT.putBoolean("inUse", true);
      compoundNBT.putIntArray("coords", new int[] { player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ() });
      world.getPlayers().stream().filter(target -> EntityStatsCapability.get((LivingEntity)target).isMarine()).forEach(target -> target.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_LAUNCHED, new Object[] { Long.valueOf(Math.round(player.getPosX())), Long.valueOf(Math.round(player.getPosZ())), player.getDisplayName().getFormattedText() })));
    } 



    
    return ActionResult.resultSuccess(player.getHeldItem(hand));
  }


  
  public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> lore, ITooltipFlag tooltip) {
    super.addInformation(stack, world, lore, tooltip);
    lore.add(new TranslationTextComponent(ModI18n.ITEM_BUSTER_CALL_INFO, new Object[0]));
    if (stack.getOrCreateTag().contains("countdown")) {
      
      int t = stack.getOrCreateTag().getInt("countdown") / 20;
      lore.add(new StringTextComponent("§4Countdown: " + t + " seconds§r"));
    } 
  }
}


