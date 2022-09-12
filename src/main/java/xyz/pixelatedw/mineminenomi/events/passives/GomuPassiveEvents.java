package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import java.util.ArrayList;
import java.util.Arrays;







@EventBusSubscriber(modid = "mineminenomi")
public class GomuPassiveEvents
{
  @SubscribeEvent
  public static void onEntityHurt(LivingHurtEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    DamageSource source = event.getSource();
    Entity instantSource = source.getImmediateSource();
    Entity trueSource = source.getTrueSource();
    PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)attacked);
    
    if (!props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) || source.isMagicDamage()) {
      return;
    }
    float reduction = 0.0F;
    ArrayList<String> instantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player" }));
    
    boolean a = false;
    if (instantSource instanceof LivingEntity) {
      
      ItemStack mainhandGear = ((LivingEntity)instantSource).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
      a = (trueSource instanceof LivingEntity && !HakiHelper.hasHardeningActive((LivingEntity)instantSource) && instantSources.contains(source.getDamageType()) && !source.isProjectile() && getGomuDamagingItems(mainhandGear.getItem()) && !ItemsHelper.isKairosekiWeapon(mainhandGear));
    } 
    
    boolean b = (source.isProjectile() && instantSource instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)instantSource).isPhysical() && !((AbilityProjectileEntity)instantSource).isAffectedByHaki());
    
    if ((a || b) && !source.isExplosion()) {
      reduction = 0.75F;
    }
    if (source.getDamageType().equals(DamageSource.LIGHTNING_BOLT.getDamageType())) {
      reduction = 1.0F;
    }
    event.setAmount(event.getAmount() * (1.0F - reduction));
  }

  
  @SubscribeEvent
  public static void onEntityAttackEvent(LivingAttackEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacked);
    
    if (!devilFruitProps.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI)) {
      return;
    }
    DamageSource source = event.getSource();
    Entity instantSource = source.getImmediateSource();
    
    if (instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile || instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile || instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile) {


      
      event.setCanceled(true);
      ((AbilityProjectileEntity)instantSource).setThrower((LivingEntity)attacked);
      ((AbilityProjectileEntity)instantSource).shoot(-(instantSource.getMotion()).x, -(instantSource.getMotion()).y, -(instantSource.getMotion()).z, 0.8F, 20.0F);
    } 
  }

  
  private static boolean getGomuDamagingItems(Item item) {
    if ((item instanceof net.minecraft.item.SwordItem && !(item instanceof CoreSwordItem)) || item instanceof net.minecraft.item.PickaxeItem || item instanceof net.minecraft.item.AxeItem || item instanceof net.minecraft.item.TridentItem || item instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
      return false;
    }
    if (item instanceof CoreSwordItem) {
      return ((CoreSwordItem)item).isBlunt();
    }
    return true;
  }
}


