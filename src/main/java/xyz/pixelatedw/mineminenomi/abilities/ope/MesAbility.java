package xyz.pixelatedw.mineminenomi.abilities.ope;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.items.HeartItem;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class MesAbility extends PunchAbility implements IParallelContinuousAbility {
  public static final Ability INSTANCE = (Ability)new MesAbility();

  
  public MesAbility() {
    super("MES", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setDescription("Removes the heart of the user's target which they can then damage to hurt the opponent");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffect;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
      return false;
    }
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
      endContinuity(player);
    }
  }
  
  private void onHitEffect(PlayerEntity player, LivingEntity target) {
    IDevilFruit props = DevilFruitCapability.get(target);
    IEntityStats targetProps = EntityStatsCapability.get(target);
    
    boolean targetNoHeart = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || target.getCreatureAttribute().equals(CreatureAttribute.UNDEAD));
    
    if (targetProps.hasHeart() && !targetNoHeart) {
      
      ItemStack heart = new ItemStack((IItemProvider)ModItems.HEART);
      ((HeartItem)heart.getItem()).setHeartOwner(heart, target);
      heart.setDisplayName((ITextComponent)new StringTextComponent(target.getDisplayName().getFormattedText() + "'s Heart"));
      player.inventory.addItemStackToInventory(heart);
      targetProps.setHeart(false);
    } 
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    IDevilFruit props = DevilFruitCapability.get(target);
    
    if (props.hasDevilFruit(ModAbilities.WARA_WARA_NO_MI))
    {
      for (int i = 0; i < ((PlayerEntity)target).inventory.mainInventory.size(); i++) {
        if (((PlayerEntity)target).inventory.getStackInSlot(i).getItem() == ModItems.STRAW_DOLL)
          return 0.0F; 
      } 
    }
    if (AbilityHelper.isTargetBlockingAbility((LivingEntity)player, target)) {
      return 0.0F;
    }
    return 1.0F;
  }
}


