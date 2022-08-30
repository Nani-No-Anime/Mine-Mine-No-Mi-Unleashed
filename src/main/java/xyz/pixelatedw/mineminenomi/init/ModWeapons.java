/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityBatAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsKenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruArtsPickaxeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gasu.BlueSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.hie.IceSaberAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.pika.AmaNoMurakumoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.wara.WarabideSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.yuki.TabiraYukiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityColoredPickaxeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityColoredSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilityPickaxeItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.GunItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.KujaBowItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.PopGreenBowItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ScissorsItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.UmbrellaItem;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.WarabideSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModWeapons {
/*  35 */   public static final CoreSwordItem MARINE_SWORD = (new CoreSwordItem(6, 251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  36 */   public static final CoreSwordItem PIRATE_CUTLASS = (new CoreSwordItem(6, 251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  37 */   public static final CoreSwordItem PIPE = (new CoreSwordItem(5, 180)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setBlunt();
/*  38 */   public static final CoreSwordItem SCISSORS = (CoreSwordItem)new ScissorsItem();
/*  39 */   public static final CoreSwordItem KIKOKU = (new CoreSwordItem(7, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  40 */   public static final CoreSwordItem KIRIBACHI = (new CoreSwordItem(7, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  41 */   public static final CoreSwordItem YORU = (new CoreSwordItem(14, -2.0F, 2000)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.9D);
/*  42 */   public static final CoreSwordItem MURAKUMOGIRI = (new CoreSwordItem(16, -2.9F, 2251)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.9D);
/*  43 */   public static final CoreSwordItem HOOK = (new CoreSwordItem(6, 251)).setIsPoisonous(300).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  44 */   public static final UmbrellaItem UMBRELLA = new UmbrellaItem();
/*  45 */   public static final CoreSwordItem JITTE = (new CoreSwordItem(7, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI })).setBlunt().setRustImmunity();
/*  46 */   public static final CoreSwordItem NONOSAMA_STAFF = (new CoreSwordItem(7, -2.6F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT })).setBlunt().setRustImmunity().setConductivity(1.0D);
/*  47 */   public static final CoreSwordItem NONOSAMA_TRIDENT = (new CoreSwordItem(10, -2.6F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT })).setRustImmunity().setConductivity(1.0D);
/*  48 */   public static final CoreSwordItem HAMMER_5T = (new CoreSwordItem(1, 500)).setBlunt();
/*  49 */   public static final CoreSwordItem TONFA = (new CoreSwordItem(5, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setBlunt();
/*  50 */   public static final CoreSwordItem BANDIT_KNIFE = (new CoreSwordItem(5, -1.3F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  51 */   public static final CoreSwordItem ACES_KNIFE = (new CoreSwordItem(5, -1.1F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  52 */   public static final CoreSwordItem MIHAWKS_KNIFE = (new CoreSwordItem(5, -1.1F, 200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  53 */   public static final CoreSwordItem SANDAI_KITETSU = (new CoreSwordItem(8, -2.2F, 600)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.5D);
/*  54 */   public static final CoreSwordItem WADO_ICHIMONJI = (new CoreSwordItem(9, -2.0F, 1200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.6D);
/*  55 */   public static final CoreSwordItem NIDAI_KITETSU = (new CoreSwordItem(9, -2.0F, 1200)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setConductivity(0.6D);
/*  56 */   public static final CoreSwordItem SHUSUI = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
/*  57 */   public static final CoreSwordItem ENMA = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
/*  58 */   public static final CoreSwordItem AME_NO_HABAKIRI = (new CoreSwordItem(10, -2.0F, 1562)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT })).setRustImmunity().setConductivity(0.75D);
/*  59 */   public static final CoreSwordItem SOUL_SOLID = (new CoreSwordItem(7, -1.4F, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  60 */   public static final CoreSwordItem DURANDAL = (new CoreSwordItem(7, -1.5F, 400)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  61 */   public static final ClimaTactItem CLIMA_TACT = (new ClimaTactItem(1, 1, 300)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI }));
/*  62 */   public static final ClimaTactItem PERFECT_CLIMA_TACT = (new ClimaTactItem(3, 2, 500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI }));
/*  63 */   public static final ClimaTactItem SORCERY_CLIMA_TACT = (new ClimaTactItem(5, 3, 800)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI, (IItemProvider)Items.GOLD_INGOT }));
/*  64 */   public static final CoreSwordItem MACE = (new CoreSwordItem(9, -3.2F, 600)).setBlunt().setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  65 */   public static final CoreSwordItem DAISENSO = (new CoreSwordItem(6, -2.6F, 600)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  66 */   public static final CoreSwordItem ACE = (new CoreSwordItem(14, -1.7F, 2500)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  67 */   public static final CoreSwordItem MOGURA = (new CoreSwordItem(13, -2.6F, 1700)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*  68 */   public static final CoreSwordItem DALTONS_SPADE = (new CoreSwordItem(7, -2.0F, 800)).setRepairIngredient(Ingredient.fromItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT }));
/*     */ 
/*     */   
/*  71 */   public static final GunItem FLINTLOCK = (new GunItem(200)).setShotCooldown(15).setReloadCooldown(40).setBulletAccuracy(2).setBulletSpeed(2).setDamageMultiplier(1.6F).setGunpowderLimit(5);
/*  72 */   public static final GunItem SENRIKU = (new GunItem(800)).setShotCooldown(25).setReloadCooldown(80).setBulletAccuracy(0).setBulletSpeed(4).setDamageMultiplier(2.0F).setGunpowderLimit(2);
/*  73 */   public static final GunItem BAZOOKA = (new GunItem(800, GunItem.BAZOOKA_AMMO)).setShotCooldown(60).setBulletAccuracy(0).setBulletSpeed(3).setDamageMultiplier(2.0F).setGunpowderLimit(1);
/*  74 */   public static final GunItem WALKER = (new GunItem(2500)).setShotCooldown(10).setReloadCooldown(30).setBulletAccuracy(1).setBulletSpeed(2).setDamageMultiplier(1.8F).setGunpowderLimit(7);
/*  75 */   public static final KujaBowItem GREEN_KUJA_BOW = new KujaBowItem(700);
/*  76 */   public static final KujaBowItem RED_KUJA_BOW = new KujaBowItem(700);
/*  77 */   public static final KujaBowItem BLUE_KUJA_BOW = new KujaBowItem(700);
/*  78 */   public static final PopGreenBowItem KABUTO = new PopGreenBowItem(400);
/*  79 */   public static final PopGreenBowItem BLACK_KABUTO = new PopGreenBowItem(800);
/*  80 */   public static final PopGreenBowItem GINGA_PACHINKO = new PopGreenBowItem(200);
/*     */ 
/*     */   
/*  83 */   public static final AbilitySwordItem ICE_SABER = (AbilitySwordItem)(new AbilitySwordItem(IceSaberAbility.INSTANCE, 12, -1.8F)).setFrosbiteTimer(40);
/*  84 */   public static final AbilitySwordItem AMA_NO_MURAKUMO = new AbilitySwordItem(AmaNoMurakumoAbility.INSTANCE, 14, 0.0F);
/*  85 */   public static final AbilitySwordItem NORO_NORO_BEAM_SWORD = (AbilitySwordItem)(new AbilitySwordItem(NoroNoroBeamSwordAbility.INSTANCE, 7)).setIsSlownessInducing(75, true);
/*  86 */   public static final AbilitySwordItem DORU_DORU_ARTS_KEN = (AbilitySwordItem)new AbilityColoredSwordItem(DoruDoruArtsKenAbility.INSTANCE, 7);
/*  87 */   public static final AbilityPickaxeItem DORU_PICKAXE = (AbilityPickaxeItem)new AbilityColoredPickaxeItem(DoruDoruArtsPickaxeAbility.INSTANCE, AbilityItemTier.DORU, 1, -2.8F);
/*  88 */   public static final AbilitySwordItem BLUE_SWORD = (AbilitySwordItem)(new AbilitySwordItem(BlueSwordAbility.INSTANCE, 20)).setIsFireAspect(5);
/*  89 */   public static final AbilitySwordItem TABIRA_YUKI = (AbilitySwordItem)(new AbilitySwordItem((Ability)TabiraYukiAbility.INSTANCE, 9, -1.9F)).setFrosbiteTimer(12);
/*  90 */   public static final AbilitySwordItem WARABIDE_SWORD = (AbilitySwordItem)new WarabideSwordItem((Ability)WarabideSwordAbility.INSTANCE, 7);
/*  91 */   public static final AbilitySwordItem BARRIERBILITY_BAT = new AbilitySwordItem((Ability)BarrierbilityBatAbility.INSTANCE, 7);
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  96 */     WyRegistry.registerItem((Item)MARINE_SWORD, "Marine Sword");
/*  97 */     WyRegistry.registerItem((Item)PIRATE_CUTLASS, "Pirate Cutlass");
/*  98 */     WyRegistry.registerItem((Item)PIPE, "Pipe");
/*  99 */     WyRegistry.registerItem((Item)SCISSORS, "Scissors");
/* 100 */     WyRegistry.registerItem((Item)KIKOKU, "Kikoku");
/* 101 */     WyRegistry.registerItem((Item)KIRIBACHI, "Kiribachi");
/* 102 */     WyRegistry.registerItem((Item)YORU, "Yoru");
/* 103 */     WyRegistry.registerItem((Item)MURAKUMOGIRI, "Murakumogiri");
/* 104 */     WyRegistry.registerItem((Item)HOOK, "Hook");
/* 105 */     WyRegistry.registerItem((Item)UMBRELLA, "Umbrella");
/* 106 */     WyRegistry.registerItem((Item)JITTE, "Jitte");
/* 107 */     WyRegistry.registerItem((Item)NONOSAMA_STAFF, "Nonosama Staff");
/* 108 */     WyRegistry.registerItem((Item)NONOSAMA_TRIDENT, "Nonosama Trident");
/* 109 */     WyRegistry.registerItem((Item)HAMMER_5T, "5t Hammer");
/* 110 */     WyRegistry.registerItem((Item)TONFA, "Tonfa");
/* 111 */     WyRegistry.registerItem((Item)BANDIT_KNIFE, "Bandit Knife");
/* 112 */     WyRegistry.registerItem((Item)ACES_KNIFE, "Ace's Knife");
/* 113 */     WyRegistry.registerItem((Item)MIHAWKS_KNIFE, "Mihawk's Knife");
/* 114 */     WyRegistry.registerItem((Item)WADO_ICHIMONJI, "Wado Ichimonji");
/* 115 */     WyRegistry.registerItem((Item)SANDAI_KITETSU, "Sandai Kitetsu");
/* 116 */     WyRegistry.registerItem((Item)NIDAI_KITETSU, "Nidai Kitetsu");
/* 117 */     WyRegistry.registerItem((Item)SHUSUI, "Shusui");
/* 118 */     WyRegistry.registerItem((Item)ENMA, "Enma");
/* 119 */     WyRegistry.registerItem((Item)AME_NO_HABAKIRI, "Ame no Habakiri");
/* 120 */     WyRegistry.registerItem((Item)SOUL_SOLID, "Soul Solid");
/* 121 */     WyRegistry.registerItem((Item)DURANDAL, "Durandal");
/* 122 */     WyRegistry.registerItem((Item)CLIMA_TACT, "Clima Tact");
/* 123 */     WyRegistry.registerItem((Item)PERFECT_CLIMA_TACT, "Perfect Clima Tact");
/* 124 */     WyRegistry.registerItem((Item)SORCERY_CLIMA_TACT, "Sorcery Clima Tact");
/* 125 */     WyRegistry.registerItem((Item)MACE, "Mace");
/* 126 */     WyRegistry.registerItem((Item)DAISENSO, "Daisenso");
/* 127 */     WyRegistry.registerItem((Item)ACE, "Ace");
/* 128 */     WyRegistry.registerItem((Item)MOGURA, "Mogura");
/* 129 */     WyRegistry.registerItem((Item)DALTONS_SPADE, "Dalton's Spade");
/*     */     
/* 131 */     WyRegistry.registerItem((Item)FLINTLOCK, "Flintlock");
/* 132 */     WyRegistry.registerItem((Item)SENRIKU, "Senriku");
/* 133 */     WyRegistry.registerItem((Item)BAZOOKA, "Bazooka");
/* 134 */     WyRegistry.registerItem((Item)WALKER, "Walker");
/* 135 */     WyRegistry.registerItem((Item)GREEN_KUJA_BOW, "Green Kuja Bow");
/*     */ 
/*     */     
/* 138 */     WyRegistry.registerItem((Item)KABUTO, "Kabuto");
/* 139 */     WyRegistry.registerItem((Item)BLACK_KABUTO, "Kuro Kabuto");
/* 140 */     WyRegistry.registerItem((Item)GINGA_PACHINKO, "Ginga Pachinko");
/*     */     
/* 142 */     WyRegistry.registerItem((Item)ICE_SABER, "Ice Saber");
/* 143 */     WyRegistry.registerItem((Item)AMA_NO_MURAKUMO, "Ama no Murakumo");
/* 144 */     WyRegistry.registerItem((Item)NORO_NORO_BEAM_SWORD, "Noro Noro Beam Sword");
/* 145 */     WyRegistry.registerItem((Item)DORU_DORU_ARTS_KEN, "Doru Doru Arts: Ken");
/* 146 */     WyRegistry.registerItem((Item)DORU_PICKAXE, "Doru Doru Arts: Pickaxe");
/* 147 */     WyRegistry.registerItem((Item)BLUE_SWORD, "Blue Sword");
/* 148 */     WyRegistry.registerItem((Item)TABIRA_YUKI, "Tabira Yuki");
/* 149 */     WyRegistry.registerItem((Item)WARABIDE_SWORD, "Warabide Sword");
/* 150 */     WyRegistry.registerItem((Item)BARRIERBILITY_BAT, "Barrierbility Bat");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModWeapons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */