package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.blocks.traps.TrapAbilityBlock;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.TeleportBlockedEffect;



@EventBusSubscriber(modid = "mineminenomi")
public class BlocksEvents
{
  private static final ParticleEffect PARTICLES = (ParticleEffect)new TeleportBlockedEffect();

  
  @SubscribeEvent
  public static void onEnderTeleport(EnderTeleportEvent event) {
    LivingEntity entity = event.getEntityLiving();
    World world = (event.getEntityLiving()).world;
    
    IDevilFruit props = DevilFruitCapability.get(entity);
    
    if (props.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI)) {
      return;
    }
    for (int i = -2; i < 2; i++) {
      
      for (int j = -2; j < 2; j++) {
        
        for (int k = -2; k < 2; k++) {
          
          BlockPos pos = entity.getPosition().add(i, j, k);
          Block b = world.getBlockState(pos).getBlock();
          if (b instanceof xyz.pixelatedw.mineminenomi.blocks.traps.DarknessBlock) {
            
            event.setCanceled(true);
            PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() + entity.getEyeHeight(), entity.getPosZ(), 0.0D, 0.0D, 0.0D);
          } 
        } 
      } 
    } 
  }



  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    LivingEntity entity = event.getEntityLiving();

























    
    BlockState belowBlockState = entity.world.getBlockState(new BlockPos(entity.getPosX(), entity.getPosY() + 0.15D, entity.getPosZ()));
    
    if (belowBlockState.getBlock() instanceof TrapAbilityBlock && !entity.world.isRemote) {
      
      TrapAbilityBlock block = (TrapAbilityBlock)belowBlockState.getBlock();
      IDevilFruit props = DevilFruitCapability.get(entity);
      
      if (props.hasDevilFruit(block.getDevilFruit())) {
        
        entity.setPosition(entity.getPosX(), entity.getPosY() + 1.0D, entity.getPosZ());
        entity.fallDistance = 0.0F;
        entity.onGround = true;
      } else {
        
        if (entity instanceof PlayerEntity) {
          
          PlayerEntity playerEntity = (PlayerEntity)entity;
          if (!playerEntity.isCreative()) {
            playerEntity.abilities.isFlying = false;
          }
        } 
        if (block.getPotionEffect() != null && !entity.isPotionActive(block.getPotionEffect().getPotion())) {
          entity.addPotionEffect(block.getPotionEffect());
        }
        if (TrapAbilityBlock.isEntityInsideOpaqueBlock(entity, 0))
          entity.attackEntityFrom(DamageSource.IN_WALL, block.getDamageDealt()); 
      } 
    } 
  }
}


