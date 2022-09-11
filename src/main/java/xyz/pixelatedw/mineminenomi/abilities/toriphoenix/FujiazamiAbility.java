package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;


import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.FujizamiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class FujiazamiAbility
  extends ContinuousAbility
  implements IFormRequiredAbility {
  public static final FujiazamiAbility INSTANCE = new FujiazamiAbility();
  
  private static final FujizamiParticleEffect PARTICLES = new FujizamiParticleEffect();

  
  public FujiazamiAbility() {
    super("Fujiazami", AbilityHelper.getDevilFruitCategory());
    setThreshold(4.0D);
    setMaxCooldown(16.0D);
    setDescription("While midair, the user forms a protective swirl of fire in front of them capable of blocking most attacks");
    
    this.onStartContinuityEvent = this::onStartChargingEvent;
    this.duringContinuityEvent = this::duringChargingEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = 4 + Math.round(this.continueTime / 10.0F);
    setMaxCooldown(cooldown);
    return true;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
      return false;
    } 
    
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int time) {
    player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 4, 8, false, false));
    player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, 4, 1, false, false));
    boolean flyForm = PhoenixFlyZoanInfo.INSTANCE.isActive((LivingEntity)player);
    
    int range = flyForm ? 3 : 2;
    double boxSize = flyForm ? 1.25D : 0.8D;
    for (int i = 0; i < range * 2; i++) {
      
      double distance = i / 2.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));



      
      for (Entity e : list) {
        
        if (e instanceof LivingEntity) {
          
          Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
          e.setMotion(speed.x, 0.5D, speed.z);
          e.velocityChanged = true; continue;
        } 
        if (e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
          
          if (e instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)e).getDamage() > (flyForm ? 50 : 30))
            return; 
          e.remove();
        } 
      } 
    } 
    
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
    if (time % 2 == 0)
    {
      PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + (player.getEyeHeight() / 2.0F), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    }
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE, (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
  }
}


