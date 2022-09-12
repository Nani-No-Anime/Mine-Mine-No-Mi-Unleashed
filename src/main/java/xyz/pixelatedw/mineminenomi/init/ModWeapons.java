package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityBatAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsKenAbility;
import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsPickaxeAbility;
import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.hie.IceSaberAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.pika.AmaNoMurakumoAbility;
import xyz.pixelatedw.mineminenomi.abilities.wara.WarabideSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.TabiraYukiAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
import xyz.pixelatedw.mineminenomi.items.weapons.*;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWeapons {
  public static final CoreSwordItem MARINE_SWORD = (new CoreSwordItem(6, 251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem PIRATE_CUTLASS = (new CoreSwordItem(6, 251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem PIPE = (new CoreSwordItem(5, 180)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setBlunt();
  public static final CoreSwordItem SCISSORS = (CoreSwordItem)new ScissorsItem();
  public static final CoreSwordItem KIKOKU = (new CoreSwordItem(7, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem KIRIBACHI = (new CoreSwordItem(7, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem YORU = (new CoreSwordItem(14, -2.0F, 2000)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.9D);
  public static final CoreSwordItem MURAKUMOGIRI = (new CoreSwordItem(16, -2.9F, 2251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.9D);
  public static final CoreSwordItem HOOK = (new CoreSwordItem(6, 251)).setIsPoisonous(300).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final UmbrellaItem UMBRELLA = new UmbrellaItem();
  public static final CoreSwordItem JITTE = (new CoreSwordItem(7, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI })).setBlunt().setRustImmunity();
  public static final CoreSwordItem NONOSAMA_STAFF = (new CoreSwordItem(7, -2.6F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT })).setBlunt().setRustImmunity().setConductivity(1.0D);
  public static final CoreSwordItem NONOSAMA_TRIDENT = (new CoreSwordItem(10, -2.6F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT })).setRustImmunity().setConductivity(1.0D);
  public static final CoreSwordItem HAMMER_5T = (new CoreSwordItem(1, 500)).setBlunt();
  public static final CoreSwordItem TONFA = (new CoreSwordItem(5, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setBlunt();
  public static final CoreSwordItem BANDIT_KNIFE = (new CoreSwordItem(5, -1.3F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem ACES_KNIFE = (new CoreSwordItem(5, -1.1F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem MIHAWKS_KNIFE = (new CoreSwordItem(5, -1.1F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem SANDAI_KITETSU = (new CoreSwordItem(8, -2.2F, 600)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.5D);
  public static final CoreSwordItem WADO_ICHIMONJI = (new CoreSwordItem(9, -2.0F, 1200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.6D);
  public static final CoreSwordItem NIDAI_KITETSU = (new CoreSwordItem(9, -2.0F, 1200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.6D);
  public static final CoreSwordItem SHUSUI = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
  public static final CoreSwordItem ENMA = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
  public static final CoreSwordItem AME_NO_HABAKIRI = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
  public static final CoreSwordItem SOUL_SOLID = (new CoreSwordItem(7, -1.4F, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem DURANDAL = (new CoreSwordItem(7, -1.5F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final ClimaTactItem CLIMA_TACT = (new ClimaTactItem(1, 1, 300)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI }));
  public static final ClimaTactItem PERFECT_CLIMA_TACT = (new ClimaTactItem(3, 2, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI }));
  public static final ClimaTactItem SORCERY_CLIMA_TACT = (new ClimaTactItem(5, 3, 800)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI, (IItemProvider)Items.GOLD_INGOT }));
  public static final CoreSwordItem MACE = (new CoreSwordItem(9, -3.2F, 600)).setBlunt().setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem DAISENSO = (new CoreSwordItem(6, -2.6F, 600)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem ACE = (new CoreSwordItem(14, -1.7F, 2500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem MOGURA = (new CoreSwordItem(13, -2.6F, 1700)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
  public static final CoreSwordItem DALTONS_SPADE = (new CoreSwordItem(7, -2.0F, 800)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));

  
  public static final GunItem FLINTLOCK = (new GunItem(200)).setShotCooldown(15).setReloadCooldown(40).setBulletAccuracy(2).setBulletSpeed(2).setDamageMultiplier(1.6F).setGunpowderLimit(5);
  public static final GunItem SENRIKU = (new GunItem(800)).setShotCooldown(25).setReloadCooldown(80).setBulletAccuracy(0).setBulletSpeed(4).setDamageMultiplier(2.0F).setGunpowderLimit(2);
  public static final GunItem BAZOOKA = (new GunItem(800, GunItem.BAZOOKA_AMMO)).setShotCooldown(60).setBulletAccuracy(0).setBulletSpeed(3).setDamageMultiplier(2.0F).setGunpowderLimit(1);
  public static final GunItem WALKER = (new GunItem(2500)).setShotCooldown(10).setReloadCooldown(30).setBulletAccuracy(1).setBulletSpeed(2).setDamageMultiplier(1.8F).setGunpowderLimit(7);
  public static final KujaBowItem GREEN_KUJA_BOW = new KujaBowItem(700);
  public static final KujaBowItem RED_KUJA_BOW = new KujaBowItem(700);
  public static final KujaBowItem BLUE_KUJA_BOW = new KujaBowItem(700);
  public static final PopGreenBowItem KABUTO = new PopGreenBowItem(400);
  public static final PopGreenBowItem BLACK_KABUTO = new PopGreenBowItem(800);
  public static final PopGreenBowItem GINGA_PACHINKO = new PopGreenBowItem(200);

  
  public static final AbilitySwordItem ICE_SABER = (AbilitySwordItem)(new AbilitySwordItem(IceSaberAbility.INSTANCE, 12, -1.8F)).setFrosbiteTimer(40);
  public static final AbilitySwordItem AMA_NO_MURAKUMO = new AbilitySwordItem(AmaNoMurakumoAbility.INSTANCE, 14, 0.0F);
  public static final AbilitySwordItem NORO_NORO_BEAM_SWORD = (AbilitySwordItem)(new AbilitySwordItem(NoroNoroBeamSwordAbility.INSTANCE, 7)).setIsSlownessInducing(75, true);
  public static final AbilitySwordItem DORU_DORU_ARTS_KEN = (AbilitySwordItem)new AbilityColoredSwordItem(DoruDoruArtsKenAbility.INSTANCE, 7);
  public static final AbilityPickaxeItem DORU_PICKAXE = (AbilityPickaxeItem)new AbilityColoredPickaxeItem(DoruDoruArtsPickaxeAbility.INSTANCE, AbilityItemTier.DORU, 1, -2.8F);
  public static final AbilitySwordItem BLUE_SWORD = (AbilitySwordItem)(new AbilitySwordItem(BlueSwordAbility.INSTANCE, 20)).setIsFireAspect(5);
  public static final AbilitySwordItem TABIRA_YUKI = (AbilitySwordItem)(new AbilitySwordItem((Ability)TabiraYukiAbility.INSTANCE, 9, -1.9F)).setFrosbiteTimer(12);
  public static final AbilitySwordItem WARABIDE_SWORD = (AbilitySwordItem)new WarabideSwordItem((Ability)WarabideSwordAbility.INSTANCE, 7);
  public static final AbilitySwordItem BARRIERBILITY_BAT = new AbilitySwordItem((Ability)BarrierbilityBatAbility.INSTANCE, 7);


  
  static {
    WyRegistry.registerItem((Item)MARINE_SWORD, "Marine Sword");
    WyRegistry.registerItem((Item)PIRATE_CUTLASS, "Pirate Cutlass");
    WyRegistry.registerItem((Item)PIPE, "Pipe");
    WyRegistry.registerItem((Item)SCISSORS, "Scissors");
    WyRegistry.registerItem((Item)KIKOKU, "Kikoku");
    WyRegistry.registerItem((Item)KIRIBACHI, "Kiribachi");
    WyRegistry.registerItem((Item)YORU, "Yoru");
    WyRegistry.registerItem((Item)MURAKUMOGIRI, "Murakumogiri");
    WyRegistry.registerItem((Item)HOOK, "Hook");
    WyRegistry.registerItem((Item)UMBRELLA, "Umbrella");
    WyRegistry.registerItem((Item)JITTE, "Jitte");
    WyRegistry.registerItem((Item)NONOSAMA_STAFF, "Nonosama Staff");
    WyRegistry.registerItem((Item)NONOSAMA_TRIDENT, "Nonosama Trident");
    WyRegistry.registerItem((Item)HAMMER_5T, "5t Hammer");
    WyRegistry.registerItem((Item)TONFA, "Tonfa");
    WyRegistry.registerItem((Item)BANDIT_KNIFE, "Bandit Knife");
    WyRegistry.registerItem((Item)ACES_KNIFE, "Ace's Knife");
    WyRegistry.registerItem((Item)MIHAWKS_KNIFE, "Mihawk's Knife");
    WyRegistry.registerItem((Item)WADO_ICHIMONJI, "Wado Ichimonji");
    WyRegistry.registerItem((Item)SANDAI_KITETSU, "Sandai Kitetsu");
    WyRegistry.registerItem((Item)NIDAI_KITETSU, "Nidai Kitetsu");
    WyRegistry.registerItem((Item)SHUSUI, "Shusui");
    WyRegistry.registerItem((Item)ENMA, "Enma");
    WyRegistry.registerItem((Item)AME_NO_HABAKIRI, "Ame no Habakiri");
    WyRegistry.registerItem((Item)SOUL_SOLID, "Soul Solid");
    WyRegistry.registerItem((Item)DURANDAL, "Durandal");
    WyRegistry.registerItem((Item)CLIMA_TACT, "Clima Tact");
    WyRegistry.registerItem((Item)PERFECT_CLIMA_TACT, "Perfect Clima Tact");
    WyRegistry.registerItem((Item)SORCERY_CLIMA_TACT, "Sorcery Clima Tact");
    WyRegistry.registerItem((Item)MACE, "Mace");
    WyRegistry.registerItem((Item)DAISENSO, "Daisenso");
    WyRegistry.registerItem((Item)ACE, "Ace");
    WyRegistry.registerItem((Item)MOGURA, "Mogura");
    WyRegistry.registerItem((Item)DALTONS_SPADE, "Dalton's Spade");
    
    WyRegistry.registerItem((Item)FLINTLOCK, "Flintlock");
    WyRegistry.registerItem((Item)SENRIKU, "Senriku");
    WyRegistry.registerItem((Item)BAZOOKA, "Bazooka");
    WyRegistry.registerItem((Item)WALKER, "Walker");
    WyRegistry.registerItem((Item)GREEN_KUJA_BOW, "Green Kuja Bow");

    
    WyRegistry.registerItem((Item)KABUTO, "Kabuto");
    WyRegistry.registerItem((Item)BLACK_KABUTO, "Kuro Kabuto");
    WyRegistry.registerItem((Item)GINGA_PACHINKO, "Ginga Pachinko");
    
    WyRegistry.registerItem((Item)ICE_SABER, "Ice Saber");
    WyRegistry.registerItem((Item)AMA_NO_MURAKUMO, "Ama no Murakumo");
    WyRegistry.registerItem((Item)NORO_NORO_BEAM_SWORD, "Noro Noro Beam Sword");
    WyRegistry.registerItem((Item)DORU_DORU_ARTS_KEN, "Doru Doru Arts: Ken");
    WyRegistry.registerItem((Item)DORU_PICKAXE, "Doru Doru Arts: Pickaxe");
    WyRegistry.registerItem((Item)BLUE_SWORD, "Blue Sword");
    WyRegistry.registerItem((Item)TABIRA_YUKI, "Tabira Yuki");
    WyRegistry.registerItem((Item)WARABIDE_SWORD, "Warabide Sword");
    WyRegistry.registerItem((Item)BARRIERBILITY_BAT, "Barrierbility Bat");
  }
}


