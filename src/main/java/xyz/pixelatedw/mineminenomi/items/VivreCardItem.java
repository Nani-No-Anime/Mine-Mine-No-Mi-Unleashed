package xyz.pixelatedw.mineminenomi.items;

import com.google.common.base.Strings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

import java.util.UUID;



public class VivreCardItem
  extends Item
{
  public VivreCardItem() {
    super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
  }


  
  public boolean onDroppedByPlayer(ItemStack itemStack, PlayerEntity player) {
    VivreCardEntity vivreCard = new VivreCardEntity(player.world);
    
    if (itemStack.getTag() == null) {
      return true;
    }
    LivingEntity owner = (LivingEntity)((ServerWorld)player.world).getEntityByUuid(UUID.fromString(itemStack.getTag().getString("ownerUUID")));
    
    if (owner == null)
      return true; 
    vivreCard.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    
    float f8 = MathHelper.sin(player.rotationPitch * 0.017453292F);
    float f2 = MathHelper.cos(player.rotationPitch * 0.017453292F);
    float f3 = MathHelper.sin(player.rotationYaw * 0.017453292F);
    float f4 = MathHelper.cos(player.rotationYaw * 0.017453292F);
    float f5 = Item.random.nextFloat() * 6.2831855F;
    float f6 = 0.02F * Item.random.nextFloat();
    vivreCard.setMotion((-f3 * f2 * 0.3F) + Math.cos(f5) * f6, (-f8 * 0.3F + 0.1F + (Item.random.nextFloat() - Item.random.nextFloat()) * 0.1F), (f4 * f2 * 0.3F) + Math.sin(f5) * f6);
    
    vivreCard.setOwner(owner.getUniqueID());
    
    player.world.addEntity((Entity)vivreCard);
    
    itemStack.setCount(0);
    
    return false;
  }


  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
    if (world.isRemote) {
      return;
    }
    if (itemStack.getTag() != null) {
      
      String uuidString = itemStack.getTag().getString("ownerUUID");
      if (Strings.isNullOrEmpty(uuidString)) {
        return;
      }
      LivingEntity owner = (LivingEntity)((ServerWorld)world).getEntityByUuid(UUID.fromString(uuidString));
      
      if (owner != null && owner.getHealth() <= 0.0F) {
        itemStack.setCount(0);
      }
    } else if (itemStack.getTag() == null || itemStack.getTag().isEmpty()) {
      
      setOwner(itemStack, (LivingEntity)entity);
    } 
  }

  
  public void setOwner(ItemStack itemStack, LivingEntity entity) {
    itemStack.getOrCreateTag().putString("ownerUUID", entity.getUniqueID().toString());
    itemStack.getOrCreateTag().putString("ownerUsername", entity.getDisplayName().getFormattedText());
    String itemName = itemStack.getDisplayName().getFormattedText();
    itemStack.setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + entity.getDisplayName().getFormattedText() + "'s " + itemName));
  }
}


