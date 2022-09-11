package xyz.pixelatedw.mineminenomi.abilities.suna;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertSpadaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class DesertSpadaAbility extends Ability {
  public static final Ability INSTANCE = new DesertSpadaAbility();

  
  public DesertSpadaAbility() {
    super("Desert Spada", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setDescription("The user forms their hand into a blade and stabs it into the ground, creating a sand blade that destroys anything on its path");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 
    
    player.world.playMovingSound(null, (Entity)player, ModSounds.DESERT_SPADA_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    boolean fruitBoosted = SunaHelper.isFruitBoosted(player);
    DesertSpadaProjectile proj = new DesertSpadaProjectile(player.world, (LivingEntity)player);
    proj.setMaxLife(fruitBoosted ? 30 : 20);
    proj.setDamage(proj.getDamage() * (fruitBoosted ? 1.5F : 1.0F));
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    
    return true;
  }
}


