package xyz.pixelatedw.mineminenomi.abilities.swordsman;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ShiShishiSonsonAbility extends Ability implements IMultiTargetAbility {
  public static final ShiShishiSonsonAbility INSTANCE = new ShiShishiSonsonAbility();

  
  public ShiShishiSonsonAbility() {
    super("Shi Shishi Sonson", AbilityHelper.getStyleCategory());
    setMaxCooldown(9.0D);
    setDescription("The user dashes forward and rapidly slashes the opponent");
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player)) {
      return false;
    }
    if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
      return false;
    } 
    
    clearTargets();
    
    ItemStack stack = player.getHeldItemMainhand();
    stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(speed.x, 0.2D, speed.z);
    player.velocityChanged = true;
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    player.world.playSound(null, player.getPosition(), ModSounds.DASH_ABILITY_SWOOSH_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage()) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class );
      list.remove(player);
      
      list.forEach(entity -> {
            if (isTarget(entity) && player.canEntityBeSeen((Entity)entity)) {
              entity.attackEntityFrom((DamageSource)(new ModEntityDamageSource("player", (Entity)player)).markDamageAsSlash(), 15.0F);
              WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ());
            } 
          });
    } 
  }



  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.9D);
  }
}


