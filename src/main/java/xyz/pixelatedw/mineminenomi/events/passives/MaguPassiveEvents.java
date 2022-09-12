package xyz.pixelatedw.mineminenomi.events.passives;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;

import java.util.ArrayList;
import java.util.Arrays;

@EventBusSubscriber(modid = "mineminenomi")
public class MaguPassiveEvents
{
  public static final LogiaInvulnerabilityAbility INVULNERABILITY_INSTANCE = new LogiaInvulnerabilityAbility(ModParticleTypes.MAGU, "Magu", MaguPassiveEvents::maguDamage, new DamageSource[] { DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.LAVA, DamageSource.HOT_FLOOR });
  
  public static boolean maguDamage(LivingEntity target, LivingEntity attacker) {
    if (CommonConfig.INSTANCE.isLogiaDamageEffectEnabled()) {
      
      SetOnFireEvent event = new SetOnFireEvent(attacker, target, 8);
      if (!MinecraftForge.EVENT_BUS.post(event)) {
        attacker.setFire(5);
      }
      ItemStack stack = attacker.getHeldItemMainhand();
      
      if (stack.getItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem)
      {
        stack.damageItem(stack.getMaxDamage() / 10 + 1, target, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
      }



      
      attacker.attackEntityFrom(DamageSource.LAVA, 10.0F);
    } 
    return false;
  }

  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
      return;
    }
    if (player.isInLava() && !player.abilities.isFlying) {
      float a = (player.moveForward > 0.0F) ? 2.0F : 0.5F;
      float y = (player.isSneaking() && player.getPosY() - player.prevPosY <= 0.0D) ? 2.0F : 0.0F;
      Vec3d vec3d = player.getMotion().mul(a, y, a);
      
      if (AbilityHelper.isJumping((LivingEntity)player)) {
        player.setMotion(vec3d.add(0.0D, 0.4000000059604645D, 0.0D));
      } else {
        player.setMotion(vec3d);
      } 
    } 
  }
  
  private static final ArrayList<String> fireFruits = new ArrayList<>(Arrays.asList(new String[] { "magu_magu", "mera_mera", "goro_goro" }));





  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    
    if (!fireFruits.contains(devilFruitProps.getDevilFruit())) {
      return;
    }
    if (event.getOverlayType().equals(RenderBlockOverlayEvent.OverlayType.FIRE)) {
      event.setCanceled(true);
    }
  }
  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void onEntityInLava(EntityViewRenderEvent.FogDensity event) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.MAGU_MAGU_NO_MI)) {
      return;
    }
    if (clientPlayerEntity.areEyesInFluid(FluidTags.LAVA)) {
      
      event.setCanceled(true);
      event.setDensity(0.025F);
    } 
  }
}


