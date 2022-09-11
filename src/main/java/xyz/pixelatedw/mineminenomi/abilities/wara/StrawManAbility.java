package xyz.pixelatedw.mineminenomi.abilities.wara;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class StrawManAbility extends PunchAbility {
  public static final Ability INSTANCE = (Ability)new StrawManAbility();

  
  public StrawManAbility() {
    super("Straw Man", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a doll that representing the entity you attacked, any damage will get redirected to it");
    setMaxCooldown(30.0D);
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    IEntityStats targetProps = EntityStatsCapability.get(target);
    
    int dolls = player.inventory.count(ModItems.STRAW_DOLL);
    
    if (dolls < 3)
    {


      
      if (targetProps.hasStrawDoll()) {
        
        ItemStack doll = new ItemStack((IItemProvider)ModItems.STRAW_DOLL);
        ((StrawDollItem)doll.getItem()).setStrawDollOwner(doll, target);
        doll.setDisplayName((ITextComponent)new StringTextComponent(target.getCommandSource().getName() + "'s Straw Doll"));
        player.inventory.addItemStackToInventory(doll);
        targetProps.setStrawDoll(false);
      } 
    }
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 1.0F;
  }
}


