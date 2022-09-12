package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.OTatsumakiParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.swordsman.BodyRotateWideArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.List;

public class OTatsumakiAbility extends Ability implements IAnimatedAbility {
  public static final Ability INSTANCE = new OTatsumakiAbility();
  public static final ParticleEffect PARTICLES = (ParticleEffect)new OTatsumakiParticleEffect();

  
  public OTatsumakiAbility() {
    super("O Tatsumaki", AbilityHelper.getStyleCategory());
    setMaxCooldown(12.0D);
    setDescription("By spinning, the user creates a small tornado, which slashes and weakens nearby opponents");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    ItemStack stack = player.getHeldItemMainhand();
    stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
    list.remove(player);
    
    list.forEach(entity -> {
          entity.attackEntityFrom((DamageSource)(new ModEntityDamageSource("player", (Entity)player)).markDamageAsSlash(), 15.0F);
          
          WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ());
        });
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    getAnimation().start();
    
    return true;
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)BodyRotateWideArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > WyHelper.percentage(90.0D, getMaxCooldown()));
  }
}


