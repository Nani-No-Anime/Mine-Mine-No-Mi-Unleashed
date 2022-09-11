package xyz.pixelatedw.mineminenomi.abilities.baku;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BeroCannonAbility extends Ability {
  public static final BeroCannonAbility INSTANCE = new BeroCannonAbility();

  
  public BeroCannonAbility() {
    super("Bero Cannon", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("Transforms the user's tongue into a cannon and fires a random block from their inventory");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    List<ItemStack> projectiles = new ArrayList<>();
    for (ItemStack item : player.inventory.mainInventory) {
      
      if (item != null && item.getItem() instanceof BlockItem && BakuMunchAbility.GRIEF_RULE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.getItem()).getBlock()))) {
        
        projectiles.add(item);
        
        break;
      } 
    } 
    if (!projectiles.isEmpty()) {
      
      BeroCannonProjectile proj = new BeroCannonProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.0F);
      ItemStack s = projectiles.stream().findFirst().orElse(null);
      if (s.getCount() > 1) {
        s.setCount(s.getCount() - 1);
      } else {
        player.inventory.deleteStack(s);
      } 
      return true;
    } 
    
    player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS, new Object[0]));
    
    return false;
  }
}


