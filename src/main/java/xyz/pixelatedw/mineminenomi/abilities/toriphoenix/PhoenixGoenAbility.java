package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix.PhoenixGoenProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

public class PhoenixGoenAbility extends RepeaterAbility implements IFormRequiredAbility {
  public static final PhoenixGoenAbility INSTANCE = new PhoenixGoenAbility();

  
  public PhoenixGoenAbility() {
    super("Phoenix Goen", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setMaxRepeaterCount(5, 4);
    setDescription("Launches high speed blue flames while midair.");
    
    this.onStartContinuityEvent = this::onStartContinuity;
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onStartContinuity(PlayerEntity player) {
    if (player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    PhoenixGoenProjectile proj = new PhoenixGoenProjectile(player.world, (LivingEntity)player, player.getLookVec());
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 5.0F);
    
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE, (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
  }
}


