package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CoupDeBooAbility extends Ability implements IFallDamageBlockingAbility {
  public static final Ability INSTANCE = new CoupDeBooAbility();
  
  private static final int COLA_REQUIRED = 20;
  
  private boolean hasFallDamage = true;
  
  public CoupDeBooAbility() {
    super("Coup De Boo", AbilityHelper.getRacialCategory());
    setMaxCooldown(10.0D);
    setDescription("Launches the user into the sky\nConsumes §220§r cola");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    if (props.getCola() - 20 < 0) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_COLA, new Object[0]));
      return false;
    } 
    
    this.hasFallDamage = false;
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 1.5D, 2.0D);
    player.setMotion(speed.x, speed.y + 3.5D, speed.z);
    props.alterCola(-20);
    WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    spawnParticles((ServerWorld)player.world, player.getPosX(), player.getPosY(), player.getPosZ());
    return true;
  }

  
  private void spawnParticles(ServerWorld world, double posX, double posY, double posZ) {
    for (int i = 0; i < 200; i++) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
      data.setColor(0.48F, 0.36F, 0.0F);
      data.setLife(30);
      data.setSize(3.0F);
      data.setMotion(WyHelper.randomDouble() / 4.0D, WyHelper.randomDouble(), WyHelper.randomDouble() / 4.0D);
      double offsetX = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-2, 2) * WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-3, 3) * WyHelper.randomDouble();
      WyHelper.spawnParticles(data, world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }
}


