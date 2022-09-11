package xyz.pixelatedw.mineminenomi.abilities.haki;

import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class BusoshokuHakiImbuingAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
  public static final BusoshokuHakiImbuingAbility INSTANCE = new BusoshokuHakiImbuingAbility();
  
  public static final UUID IMBUING_BONUS_ID = UUID.fromString("741b8582-fce1-4485-9460-0f3320632729");

  
  public BusoshokuHakiImbuingAbility() {
    super("Busoshoku Haki: Imbuing", AbilityHelper.getHakiCategory());
    setDescription("Covers the weapon of the user in Armament haki, making their weapon attacks stronger and being able to damage Logia users");
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public void use(PlayerEntity player) {
    if (player.getHeldItemMainhand().getItem() == ModWeapons.ENMA && isContinuous()) {
      return;
    }
    super.use(player);
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!HakiHelper.canUseHaki(player, (Ability)this)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, new Object[] { getName() }));
      return false;
    } 
    
    if (!HakiHelper.canEnableHaki(player)) {
      return false;
    }
    applyImbuingBonus((LivingEntity)player, player.getHeldItemMainhand());
    
    player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
    if (isOnMaxOveruse) {
      endContinuity(player);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 10L, 60L);
    setMaxCooldown(cooldown);
    
    Iterable<ItemStack> inventories = Iterables.concat((Iterable)player.inventory.mainInventory, (Iterable)player.inventory.armorInventory, (Iterable)player.inventory.offHandInventory);
    for (ItemStack stack : inventories) {
      
      if (!stack.isEmpty() && stack.isDamageable() && !(stack.getItem()).properties.containsKey(new ResourceLocation("haki")) && stack.getOrCreateTag().getBoolean("imbuingHakiActive")) {
        stack.getOrCreateTag().remove("imbuingHakiActive");
      }
    } 
    removeImbuingBonus((LivingEntity)player);
    
    return true;
  }


  
  public HakiType getType() {
    return HakiType.IMBUING;
  }

  
  public static void tryApplyingImbuingBonus(LivingEntity entity, ItemStack itemStack) {
    boolean hasImbuing = HakiHelper.hasImbuingActive(entity);
    if (hasImbuing) {
      applyImbuingBonus(entity, itemStack);
    } else {
      removeImbuingBonus(entity);
    } 
  }
  
  private static void applyImbuingBonus(LivingEntity entity, ItemStack itemStack) {
    if (itemStack == null || itemStack.isEmpty() || !HakiHelper.canItemGainImbuing(itemStack)) {
      return;
    }
    double bonus = 1.0D;
    if (entity instanceof PlayerEntity) {
      
      IHakiData hakiProps = HakiDataCapability.get(entity);
      bonus = (hakiProps.getBusoshokuImbuingHakiExp() / 100.0F);
    } 
    Multimap<String, AttributeModifier> map = itemStack.getItem().getAttributeModifiers(EquipmentSlotType.MAINHAND, itemStack);
    String key = SharedMonsterAttributes.ATTACK_DAMAGE.getName();
    
    AttributeModifier mod = map.get(key).stream().filter(m -> m.getID().equals(IMBUING_BONUS_ID)).findFirst().orElse(null);
    if (mod == null) {
      map.put(key, new AttributeModifier(IMBUING_BONUS_ID, "Imbuing Haki Bonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE));
    }
    for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)map.entries()) {
      
      if (!itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsEntry(entry.getKey(), entry.getValue())) {
        itemStack.addAttributeModifier(entry.getKey(), entry.getValue(), EquipmentSlotType.MAINHAND);
      }
    } 
  }
  
  private static void removeImbuingBonus(LivingEntity entity) {
    Iterable<ItemStack> list = entity.getEquipmentAndArmor();
    
    for (ItemStack itemStack : list) {
      
      if (itemStack == null || itemStack.isEmpty() || !itemStack.hasTag()) {
        continue;
      }
      ListNBT listnbt = itemStack.getTag().getList("AttributeModifiers", 10);
      
      for (int i = 0; i < listnbt.size(); i++) {
        
        CompoundNBT compoundnbt = listnbt.getCompound(i);
        AttributeModifier mod = SharedMonsterAttributes.readAttributeModifier(compoundnbt);
        if (mod.getID().equals(IMBUING_BONUS_ID))
          listnbt.remove(i); 
      } 
    } 
  }
}


