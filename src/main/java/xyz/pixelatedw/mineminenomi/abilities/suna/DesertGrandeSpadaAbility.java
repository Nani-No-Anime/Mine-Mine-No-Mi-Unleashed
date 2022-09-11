package xyz.pixelatedw.mineminenomi.abilities.suna;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.DesertGrandeSpadaProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class DesertGrandeSpadaAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new DesertGrandeSpadaAbility();

  
  public DesertGrandeSpadaAbility() {
    super("Desert Grande Spada", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setMaxChargeTime(0.5D);
    setDescription("Shoots a large sand blade cutting through multiple enemies and blocks");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }
  
  private void duringChargingEvent(PlayerEntity player, int i) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    DesertGrandeSpadaProjectile proj = new DesertGrandeSpadaProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.0F);
    return true;
  }
}


