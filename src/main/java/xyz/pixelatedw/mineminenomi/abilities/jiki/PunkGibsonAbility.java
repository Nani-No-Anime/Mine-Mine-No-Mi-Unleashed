package xyz.pixelatedw.mineminenomi.abilities.jiki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PunkGibsonZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PunkGibsonAbility extends ZoanAbility {
  public static final PunkGibsonAbility INSTANCE = new PunkGibsonAbility();
  
  private static final int MAX_ITEMS = 100;
  
  private List<ItemStack> magneticItems = new ArrayList<>();
  
  private boolean dropItems = true;
  
  public PunkGibsonAbility() {
    super("Punk Gibson", AbilityHelper.getDevilFruitCategory());
    setDescription("Uses §2100§r magnetic items from the user's inventory to create a large arm increasing their punch power and reach.");
    setThreshold(120.0D);
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (!this.magneticItems.isEmpty()) {
      this.magneticItems.clear();
    }
    List<ItemStack> originals = JikiHelper.getMagneticItems(player, 100);
    
    int count = JikiHelper.countMagneticItems(originals);
    if (count < 100) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
      endContinuity(player);
      return false;
    } 

    
    this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 100);
    
    double size = MathHelper.clamp(count, 10, 100);
    double powerAmount = 6.0D * size / 100.0D;
    double reachAmount = 2.0D * size / 100.0D;
    
    AbilityAttributeModifier powerModifier = new AbilityAttributeModifier(UUID.randomUUID(), (Ability)INSTANCE, "Punk Gibson Strength Modifier", powerAmount, AttributeModifier.Operation.ADDITION);
    AbilityAttributeModifier reachModifier = new AbilityAttributeModifier(UUID.randomUUID(), (Ability)INSTANCE, "Punk Gibson Reach Modifier", reachAmount, AttributeModifier.Operation.ADDITION);
    
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)powerModifier);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)reachModifier);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)reachModifier);
    
    this.dropItems = true;
    
    AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return super.onStartContinuityEvent(player);
  }


  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    if (!super.onEndContinuityEvent(player) || this.magneticItems.size() <= 0) {
      return false;
    }
    if (this.dropItems) {
      
      ItemStack stack = this.magneticItems.get(player.getRNG().nextInt(this.magneticItems.size() - 1));
      ItemsHelper.itemBreakParticles((LivingEntity)player, 100, stack);
      player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, player.getSoundCategory(), 1.0F, 1.0F);
      JikiHelper.dropComponentItems(player, player.getPosition(), this.magneticItems);
    } 
    
    int cooldown = 3 + (int)Math.round(this.continueTime / 20.0D);
    setMaxCooldown(cooldown);
    
    return true;
  }

  
  public void stopItemDrops() {
    this.dropItems = false;
  }

  
  public List<ItemStack> getMagneticItems() {
    return this.magneticItems;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)PunkGibsonZoanInfo.INSTANCE;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    if (!super.isTransformationActive(entity)) {
      return false;
    }
    DamnedPunkAbility abl = (DamnedPunkAbility)AbilityDataCapability.get(entity).getEquippedAbility((Ability)DamnedPunkAbility.INSTANCE);
    
    return (abl == null || !abl.isContinuous());
  }
}


