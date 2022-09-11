package xyz.pixelatedw.mineminenomi.abilities.sabi;

import com.google.common.collect.Iterables;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class RustTouchAbility
  extends PunchAbility implements IPunchOverlayAbility {
  public static final RustTouchAbility INSTANCE = new RustTouchAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new RustTouchParticleEffect();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.RUST_TOUCH_ARM);

  
  public RustTouchAbility() {
    super("Rust Touch", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setDescription("Rusts the enemy and the items equipped on them");
    
    this.onHitEntityEvent = this::onHitEntity;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    target.addPotionEffect(new EffectInstance(Effects.WITHER, 160, 2));
    target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 160, 1));
    target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 160, 1));
    
    Iterable<ItemStack> iter = target.getEquipmentAndArmor();
    if (target instanceof PlayerEntity) {
      
      PlayerInventory playerInv = ((PlayerEntity)target).inventory;
      iter = Iterables.concat((Iterable)playerInv.mainInventory, (Iterable)playerInv.armorInventory, (Iterable)playerInv.offHandInventory);
    } 
    
    for (ItemStack stack : iter) {
      
      if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.RUSTY)) {
        
        if (stack.isDamageable()) {
          stack.damageItem(stack.getMaxDamage() / 3 + 1 + 1, target, e -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND)); continue;
        } 
        stack.shrink(1 + this.random.nextInt(4));
      } 
    } 
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }

  
  private float onHitEntity(PlayerEntity player, LivingEntity target) {
    return 8.0F;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return OVERLAY;
  }
}


