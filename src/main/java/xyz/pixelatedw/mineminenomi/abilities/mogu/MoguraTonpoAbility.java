package xyz.pixelatedw.mineminenomi.abilities.mogu;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MoguraTonpoAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
  public static final MoguraTonpoAbility INSTANCE = new MoguraTonpoAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
  
  private int initialY;
  private boolean hasFallDamage = true;
  
  public MoguraTonpoAbility() {
    super("Mogura Tonpo", AbilityHelper.getDevilFruitCategory());
    setDescription("Digs a massive tunnel forwards while in mole form\n\n§2SHIFT-USE§r: Digs a tunnel straight below the user");
    setMaxCooldown(12.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.duringCooldownEvent = this::duringCooldown;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    clearTargets();
    
    this.initialY = (int)player.getPosY();
    
    if (player.isSneaking()) {
      
      this.hasFallDamage = false;
      for (int x = -1; x < 1; x++) {
        
        for (int y = 0; y < 10; y++) {
          
          for (int z = -1; z < 1; z++) {
            
            int posX = (int)player.getPosX() + x;
            int posY = (int)player.getPosY() - y;
            int posZ = (int)player.getPosZ() + z;
            BlockPos pos = new BlockPos(posX, posY, posZ);
            
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 50, 4, false, false));
            player.addPotionEffect(new EffectInstance(Effects.HASTE, 400, 2, false, false));
            
            BlockState tempBlock = player.world.getBlockState(pos);
            if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
            {
              player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock.getBlock()));
              PARTICLES.spawn(player.world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
          
          } 
        } 
      } 
    } else {
      
      Vec3d speed = WyHelper.propulsion((LivingEntity)player, 4.0D, 4.0D);
      player.setMotion(speed.x, 0.3D, speed.z);
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    } 
    
    return true;
  }

  
  private void duringCooldown(PlayerEntity player, int cooldownTimer) {
    if (canDealDamage() && player.getPosY() >= this.initialY) {
      
      List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      list.remove(player);
      list.forEach(entity -> {
            if (isTarget(entity)) {
              entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
            }
          });

      
      for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 3)) {
        
        if (location.getY() >= player.getPosY()) {
          
          BlockState tempBlock = player.world.getBlockState(new BlockPos(location.getX(), location.getY(), location.getZ()));
          if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
            
            player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock.getBlock()));
            PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
          } 
        } 
      } 
    } 
  }

  
  public boolean canDealDamage() {
    return (this.cooldown > getMaxCooldown() * 0.8D);
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)MoguHeavyZoanInfo.INSTANCE };
  }
}


