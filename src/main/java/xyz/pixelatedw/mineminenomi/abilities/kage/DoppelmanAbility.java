package xyz.pixelatedw.mineminenomi.abilities.kage;
import java.lang.invoke.SerializedLambda;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class DoppelmanAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final DoppelmanAbility INSTANCE = new DoppelmanAbility();
  
  private DoppelmanEntity doppelman = null;

  
  public DoppelmanAbility() {
    super("Doppelman", AbilityHelper.getDevilFruitCategory());
    setDescription("Creates a living version of the user's shadow to help them fight\n\n§2SHIFT-USE§r: Switches between AGGRESSIVE and DEFENSIVE modes");
    setMaxCooldown(20.0D);
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.doppelman = new DoppelmanEntity(player.world);
    this.doppelman.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    this.doppelman.setOwner((LivingEntity)player);
    player.world.addEntity((Entity)this.doppelman);
    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (this.doppelman != null) {
      
      if (player.onGround && player.isSneaking()) {
        
        this.doppelman.isAggressive = !this.doppelman.isAggressive;
        String abilityName = (new TranslationTextComponent(getI18nKey(), new Object[0])).getFormattedText();
        String puppetState = (new TranslationTextComponent(this.doppelman.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE, new Object[0])).getFormattedText();
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, new Object[] { abilityName, puppetState }));
        return false;
      } 
      
      this.doppelman.remove();
    } 
    
    int cooldown = (int)Math.round(this.continueTime / 50.0D);
    setMaxCooldown(cooldown);
    
    return true;
  }

  
  public DoppelmanEntity getDoppelman() {
    return this.doppelman;
  }
}


