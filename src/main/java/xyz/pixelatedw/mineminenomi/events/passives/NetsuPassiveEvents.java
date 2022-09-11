package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi")
public class NetsuPassiveEvents
{
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (event.getEntityLiving() == null) {
      return;
    }
    LivingEntity entity = event.getEntityLiving();
    IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
    DamageSource damageSource = event.getSource();
    
    if (devilFruitProps.hasDevilFruit(ModAbilities.NETSU_NETSU_NO_MI) && damageSource.getDamageType().equals(DamageSource.IN_FIRE.getDamageType())) {
      
      entity.extinguish();
      event.setCanceled(true);
    } 
    
    if (damageSource.getImmediateSource() instanceof LivingEntity) {
      
      LivingEntity netsuAttacker = (LivingEntity)damageSource.getImmediateSource();
      IAbilityData abilityProps = AbilityDataCapability.get(netsuAttacker);
      NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
      
      if (ability == null || !ability.isContinuous() || netsuAttacker.getHeldItemMainhand().isEmpty()) {
        return;
      }
      SetOnFireEvent e = new SetOnFireEvent((LivingEntity)damageSource.getImmediateSource(), entity, 6);
      if (!MinecraftForge.EVENT_BUS.post((Event)e))
        entity.setFire(6); 
    } 
  }
}


