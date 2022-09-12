package xyz.pixelatedw.mineminenomi.items.dials;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class RejectDialItem extends BlockItem {
  private static final DamageSource REJECT_DIAL = (new DamageSource("reject_dial")).setDamageBypassesArmor().setMagicDamage().setDamageIsAbsolute();

  
  public RejectDialItem() {
    super(ModBlocks.REJECT_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (player.getCooldownTracker().getCooldown(getItem(), 0.0F) > 0.0F) {
      return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
    }
    if (!world.isRemote) {
      
      if (!CommonConfig.INSTANCE.isAbilityGriefingEnabled())
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand)); 
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 5.0D);
      player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 2, false, false));
      player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
      player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1, false, false));
      
      player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2, 5));
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, 
          (mop.getHitVec()).x, 
          (mop.getHitVec()).y, 
          (mop.getHitVec()).z, 12.0F);
      explosion.setStaticDamage(80.0F);
      explosion.setDamageOwner(false);
      explosion.setDamageSource((new IndirectEntityDamageSource("explosion", (Entity)player, null)).setDamageBypassesArmor().setDamageIsAbsolute().setExplosion());
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(13));
      explosion.doExplosion();
      player.attackEntityFrom((new IndirectEntityDamageSource("explosion", (Entity)player, null)).setDamageBypassesArmor().setDamageIsAbsolute().setExplosion(), 50.0F);

      
      player.getCooldownTracker().setCooldown(getItem(), 200);
      player.getHeldItem(hand).shrink(1);
    } 
    
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    if (!attacker.world.isRemote && attacker instanceof PlayerEntity) {
      
      PlayerEntity playerAttacker = (PlayerEntity)attacker;
      if (playerAttacker.getCooldownTracker().getCooldown(getItem(), 0.0F) > 0.0F) {
        return false;
      }
      playerAttacker.getCooldownTracker().setCooldown(getItem(), 400);
      playerAttacker.getHeldItem(playerAttacker.getActiveHand()).shrink(1);
      
      playerAttacker.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 2, false, false));
      playerAttacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 1, false, false));
      playerAttacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 600, 1, false, false));
      
      if (!playerAttacker.isCreative())
        attacker.attackEntityFrom(REJECT_DIAL, attacker.getMaxHealth() - 1.0F); 
      target.attackEntityFrom(REJECT_DIAL, target.getMaxHealth() + 1.0F);
      return true;
    } 
    
    return false;
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    return super.onBlockPlaced(pos, world, player, itemStack, state);
  }
}


