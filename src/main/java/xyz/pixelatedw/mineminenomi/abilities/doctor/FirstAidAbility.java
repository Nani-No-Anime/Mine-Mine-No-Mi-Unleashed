package xyz.pixelatedw.mineminenomi.abilities.doctor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doctor.FirstAidParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class FirstAidAbility extends PunchAbility {
  public static final FirstAidAbility INSTANCE = new FirstAidAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new FirstAidParticleEffect();

  
  public FirstAidAbility() {
    super("First Aid", AbilityHelper.getStyleCategory());
    setMaxCooldown(10.0D);
    setDescription("Allows the user to heal their target");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onHitEntityEvent = this::onHitEntity;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
    boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
    
    if (!hasMedicBag) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
    boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
    
    if (!hasMedicBag) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
      return -1.0F;
    } 
    
    int heal = (int)(8.0D + WyHelper.percentage(20.0D, target.getMaxHealth()));
    LivingHealByEvent event = new LivingHealByEvent((LivingEntity)player, target, heal);
    MinecraftForge.EVENT_BUS.post(event);
    
    target.heal(heal);
    PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    int damage = (medicBag.getDamage() + 10 <= medicBag.getMaxDamage()) ? 10 : (medicBag.getMaxDamage() - medicBag.getDamage());
    medicBag.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    if (medicBag.getDamage() >= medicBag.getMaxDamage())
    {
      
      medicBag.shrink(1);
    }
    
    return 0.0F;
  }
}


