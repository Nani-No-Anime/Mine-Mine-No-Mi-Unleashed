package xyz.pixelatedw.mineminenomi.abilities.ito;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class BlackKnightAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final BlackKnightAbility INSTANCE = new BlackKnightAbility();
  
  private BlackKnightEntity knight = null;

  
  public BlackKnightAbility() {
    super("Black Knight", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setThreshold(100.0D);
    setDescription("Creates a clone of the user made entirely out of compressed strings\n\n§2SHIFT-USE§r: Switches between AGGRESSIVE and DEFENSIVE modes");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.knight = new BlackKnightEntity(player.world);
    this.knight.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    this.knight.setOwner((LivingEntity)player);
    player.world.addEntity((Entity)this.knight);
    
    return true;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (this.knight != null) {
      
      if (player.onGround && player.isSneaking()) {
        
        this.knight.isAggressive = !this.knight.isAggressive;
        String abilityName = (new TranslationTextComponent(getI18nKey(), new Object[0])).getFormattedText();
        String puppetState = (new TranslationTextComponent(this.knight.isAggressive ? ModI18n.ABILITY_PUPPET_STATE_AGGRESSIVE : ModI18n.ABILITY_PUPPET_STATE_DEFENSIVE, new Object[0])).getFormattedText();
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_PUPPET_STATE, new Object[] { abilityName, puppetState }));
        return false;
      } 
      
      this.knight.remove();
    } 
    
    return true;
  }
}


