package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoenParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class TenseiNoSoenAbility extends ChargeableAbility implements IFormRequiredAbility {
  public static final TenseiNoSoenAbility INSTANCE = new TenseiNoSoenAbility();
  
  private static final ParticleEffect PARTICLES1 = (ParticleEffect)new TenseiNoSoenParticleEffect();
  private static final ParticleEffect PARTICLES2 = (ParticleEffect)new TenseiNoSoen2ParticleEffect();

  
  public TenseiNoSoenAbility() {
    super("Tensei no Soen", AbilityHelper.getDevilFruitCategory());
    setMaxChargeTime(3.0D);
    setMaxCooldown(30.0D);
    setDescription("While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int time) {
    PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    if (!player.onGround && time == 1) {
      
      setChargeTime(1);
      player.abilities.isFlying = false;
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
      player.setMotion((player.getMotion()).x, -700.0D, (player.getMotion()).z);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      player.fallDistance = 0.0F;
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    player.fallDistance = 0.0F;
    
    PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    list.remove(player);
    
    for (LivingEntity target : list) {
      target.attackEntityFrom(ModDamageSource.causePlayerDamage(player), 50.0F);
    }
    return true;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
  }
}


