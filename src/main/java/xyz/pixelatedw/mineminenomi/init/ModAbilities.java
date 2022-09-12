package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.*;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.*;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.*;
import xyz.pixelatedw.mineminenomi.abilities.awa.GoldenHourAbility;
import xyz.pixelatedw.mineminenomi.abilities.awa.RelaxHourAbility;
import xyz.pixelatedw.mineminenomi.abilities.awa.SoapDefenseAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuFactoryAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuMunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuTsuihoAbility;
import xyz.pixelatedw.mineminenomi.abilities.baku.BeroCannonAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringDeathKnockAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringHopperAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringMovementAbility;
import xyz.pixelatedw.mineminenomi.abilities.bane.SpringSnipeAbility;
import xyz.pixelatedw.mineminenomi.abilities.bara.*;
import xyz.pixelatedw.mineminenomi.abilities.bari.*;
import xyz.pixelatedw.mineminenomi.abilities.beta.BetaBetaChainAbility;
import xyz.pixelatedw.mineminenomi.abilities.beta.BetaCoatingAbility;
import xyz.pixelatedw.mineminenomi.abilities.beta.BetaLauncherAbility;
import xyz.pixelatedw.mineminenomi.abilities.beta.HanamizuShinkenAbility;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.*;
import xyz.pixelatedw.mineminenomi.abilities.bomu.*;
import xyz.pixelatedw.mineminenomi.abilities.brawler.*;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.ChiyupopoAbility;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.HealingTouchAbility;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.KenpopoAbility;
import xyz.pixelatedw.mineminenomi.abilities.chiyu.TearsAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.*;
import xyz.pixelatedw.mineminenomi.abilities.doa.AirDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doa.DoorDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doa.KaitenDoorAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.abilities.doku.*;
import xyz.pixelatedw.mineminenomi.abilities.doru.*;
import xyz.pixelatedw.mineminenomi.abilities.electro.*;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.*;
import xyz.pixelatedw.mineminenomi.abilities.gasu.*;
import xyz.pixelatedw.mineminenomi.abilities.goe.DragonsRoarAbility;
import xyz.pixelatedw.mineminenomi.abilities.goe.TodorokiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gomu.*;
import xyz.pixelatedw.mineminenomi.abilities.goro.*;
import xyz.pixelatedw.mineminenomi.abilities.gura.GekishinAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.ShimaYurashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.ShingenNoIchigekiAbility;
import xyz.pixelatedw.mineminenomi.abilities.gura.TenchiMeidoAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.*;
import xyz.pixelatedw.mineminenomi.abilities.hana.*;
import xyz.pixelatedw.mineminenomi.abilities.hie.*;
import xyz.pixelatedw.mineminenomi.abilities.hiso.AnimalFriendAbility;
import xyz.pixelatedw.mineminenomi.abilities.hiso.ForewarnAbility;
import xyz.pixelatedw.mineminenomi.abilities.hiso.LookoutAbility;
import xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu.HitoDaibutsuPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.hitodaibutsu.ImpactBlastAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.MiniHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.NegativeHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.TokuHollowAbility;
import xyz.pixelatedw.mineminenomi.abilities.horo.YutaiRidatsuAbility;
import xyz.pixelatedw.mineminenomi.abilities.horu.*;
import xyz.pixelatedw.mineminenomi.abilities.ito.*;
import xyz.pixelatedw.mineminenomi.abilities.jiki.*;
import xyz.pixelatedw.mineminenomi.abilities.kachi.EvaporateAbility;
import xyz.pixelatedw.mineminenomi.abilities.kachi.HotBoilingSpecialAbility;
import xyz.pixelatedw.mineminenomi.abilities.kachi.VulcanizationAbility;
import xyz.pixelatedw.mineminenomi.abilities.kage.*;
import xyz.pixelatedw.mineminenomi.abilities.kame.KameGuardPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.kame.KameWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.karu.IngaZarashiAbility;
import xyz.pixelatedw.mineminenomi.abilities.karu.KarmaAbility;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress10000Ability;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPress1Ability;
import xyz.pixelatedw.mineminenomi.abilities.kilo.KiloPunch5000Ability;
import xyz.pixelatedw.mineminenomi.abilities.kira.BrilliantPunkAbility;
import xyz.pixelatedw.mineminenomi.abilities.kira.CabochonKnuckleAbility;
import xyz.pixelatedw.mineminenomi.abilities.kira.DiamondBodyAbility;
import xyz.pixelatedw.mineminenomi.abilities.kobu.ShoureiAbility;
import xyz.pixelatedw.mineminenomi.abilities.kuku.GastronomorphAbility;
import xyz.pixelatedw.mineminenomi.abilities.kuku.GourmetSnipeAbility;
import xyz.pixelatedw.mineminenomi.abilities.kuku.GourmetamorphosisAbility;
import xyz.pixelatedw.mineminenomi.abilities.magu.*;
import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.MusterAbility;
import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.SmallMusterAbility;
import xyz.pixelatedw.mineminenomi.abilities.mega.MegaMegaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mera.*;
import xyz.pixelatedw.mineminenomi.abilities.mero.MeroMeroMellowAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PerfumeFemurAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.PistolKissAbility;
import xyz.pixelatedw.mineminenomi.abilities.mero.SlaveArrowAbility;
import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
import xyz.pixelatedw.mineminenomi.abilities.mini.PaperFloatAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguraBananaAbility;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguraTonpoAbility;
import xyz.pixelatedw.mineminenomi.abilities.moku.*;
import xyz.pixelatedw.mineminenomi.abilities.nagi.SilentAbility;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.ClawStrikeAbility;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.FerociousLeapAbility;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.LeopardHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.nekoleopard.LeopardWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.netsu.HeatDenasshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.netsu.NekkaiGyoraiAbility;
import xyz.pixelatedw.mineminenomi.abilities.netsu.NekkaiJigokuAbility;
import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
import xyz.pixelatedw.mineminenomi.abilities.nikyu.*;
import xyz.pixelatedw.mineminenomi.abilities.noro.KyubiRushAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.noro.NoroNoroBeamSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.ope.*;
import xyz.pixelatedw.mineminenomi.abilities.ori.AwaseBaoriAbility;
import xyz.pixelatedw.mineminenomi.abilities.ori.BindAbility;
import xyz.pixelatedw.mineminenomi.abilities.ori.GreatCageAbility;
import xyz.pixelatedw.mineminenomi.abilities.oto.BonAbility;
import xyz.pixelatedw.mineminenomi.abilities.oto.DonAbility;
import xyz.pixelatedw.mineminenomi.abilities.oto.ShanAbility;
import xyz.pixelatedw.mineminenomi.abilities.pero.*;
import xyz.pixelatedw.mineminenomi.abilities.pika.*;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.*;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AllosaurusWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AncientBiteAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus.AncientTailSpinAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachioBomberAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachioGrabAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachiosaurusGuardPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus.BrachiosaurusHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ryupteranodon.*;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustBreakAbility;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustSkinAbility;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustTouchAbility;
import xyz.pixelatedw.mineminenomi.abilities.sai.HornDashAbility;
import xyz.pixelatedw.mineminenomi.abilities.sai.RhinoSmashAbility;
import xyz.pixelatedw.mineminenomi.abilities.sai.SaiHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.sai.SaiWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.saraaxolotl.*;
import xyz.pixelatedw.mineminenomi.abilities.sniper.*;
import xyz.pixelatedw.mineminenomi.abilities.sube.SubeSubeDeflectAbility;
import xyz.pixelatedw.mineminenomi.abilities.sube.SubeSubeSpurAbility;
import xyz.pixelatedw.mineminenomi.abilities.sui.FreeSwimmingAbility;
import xyz.pixelatedw.mineminenomi.abilities.sui.NekomimiPunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.sui.NyanNyanSuplexAbility;
import xyz.pixelatedw.mineminenomi.abilities.sui.NyanNyanSuplexBabyBusterAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.ShishaNoTeAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SkattingAbility;
import xyz.pixelatedw.mineminenomi.abilities.suke.SukePunchAbility;
import xyz.pixelatedw.mineminenomi.abilities.suna.*;
import xyz.pixelatedw.mineminenomi.abilities.supa.*;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.*;
import xyz.pixelatedw.mineminenomi.abilities.toriphoenix.*;
import xyz.pixelatedw.mineminenomi.abilities.ushibison.*;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.BiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.ushigiraffe.GiraffeWalkPointAbility;
import xyz.pixelatedw.mineminenomi.abilities.wara.LifeMinusAbility;
import xyz.pixelatedw.mineminenomi.abilities.wara.StrawManAbility;
import xyz.pixelatedw.mineminenomi.abilities.wara.WarabideSwordAbility;
import xyz.pixelatedw.mineminenomi.abilities.yami.*;
import xyz.pixelatedw.mineminenomi.abilities.yomi.KasuriutaFubukiGiriAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility;
import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
import xyz.pixelatedw.mineminenomi.abilities.yuki.*;
import xyz.pixelatedw.mineminenomi.abilities.zou.*;
import xyz.pixelatedw.mineminenomi.abilities.zoumammoth.*;
import xyz.pixelatedw.mineminenomi.abilities.zushi.*;
import xyz.pixelatedw.mineminenomi.api.enums.FruitType;
import xyz.pixelatedw.mineminenomi.events.passives.*;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.Arrays;
import java.util.Objects;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAbilities
{
  public static final AkumaNoMiItem RYU_RYU_NO_MI_ALLOSAURUS = new AkumaNoMiItem("Ryu Ryu no Mi, Model: Allosaurus", 2, FruitType.ANCIENT_ZOAN, new Ability[] { (Ability)AllosaurusWalkPointAbility.INSTANCE, (Ability)AllosaurusHeavyPointAbility.INSTANCE, (Ability)AncientBiteAbility.INSTANCE, (Ability)AncientTailSpinAbility.INSTANCE });
  public static final AkumaNoMiItem SAI_SAI_NO_MI = new AkumaNoMiItem("Sai Sai no Mi", 2, FruitType.ZOAN, new Ability[] { (Ability)SaiWalkPointAbility.INSTANCE, (Ability)SaiHeavyPointAbility.INSTANCE, (Ability)RhinoSmashAbility.INSTANCE, (Ability)HornDashAbility.INSTANCE });
  public static final AkumaNoMiItem SARA_SARA_NO_MI_AXOLOTL = new AkumaNoMiItem("Sara Sara no Mi, Model: Axolotl", 2, FruitType.ZOAN, new Ability[] { (Ability)AxolotlHeavyPointAbility.INSTANCE, (Ability)AxolotlWalkPointAbility.INSTANCE, (Ability)PoisonSpitAbility.INSTANCE, (Ability)AxolotlHealAbility.INSTANCE, (Ability)PlayDeadAbility.INSTANCE, (Ability)HeartRegenAbility.INSTANCE });
  public static final AkumaNoMiItem RYU_RYU_NO_MI_BRACHIOSAURUS = new AkumaNoMiItem("Ryu Ryu no Mi, Model: Brachiosaurus", 2, FruitType.ANCIENT_ZOAN, new Ability[] { (Ability)BrachiosaurusGuardPointAbility.INSTANCE, (Ability)BrachiosaurusHeavyPointAbility.INSTANCE, (Ability)BrachioBomberAbility.INSTANCE, (Ability)BrachioGrabAbility.INSTANCE });
  public static final AkumaNoMiItem RYU_RYU_NO_MI_PTERANODON = new AkumaNoMiItem("Ryu Ryu no Mi, Model: Pteranodon", 2, FruitType.ANCIENT_ZOAN, new Ability[] { (Ability)PteranodonFlyPointAbility.INSTANCE, (Ability)PteranodonAssaultPointAbility.INSTANCE, (Ability)TempuraudonAbility.INSTANCE, (Ability)TankyudonAbility.INSTANCE, (Ability)BarizodonAbility.INSTANCE, (Ability)PteranodonFlightAbility.INSTANCE, (Ability)PteranodonSmashAbility.INSTANCE });
  public static final AkumaNoMiItem KAME_KAME_NO_MI = new AkumaNoMiItem("Kame Kame no Mi", 1, FruitType.ZOAN, new Ability[] { (Ability)KameGuardPointAbility.INSTANCE, (Ability)KameWalkPointAbility.INSTANCE });
  public static final AkumaNoMiItem NEKO_NEKO_NO_MI_LEOPARD = new AkumaNoMiItem("Neko Neko no Mi, Model: Leopard", 2, FruitType.ZOAN, new Ability[] { (Ability)LeopardWalkPointAbility.INSTANCE, (Ability)LeopardHeavyPointAbility.INSTANCE, (Ability)FerociousLeapAbility.INSTANCE, (Ability)ClawStrikeAbility.INSTANCE });
  public static final AkumaNoMiItem ZOU_ZOU_NO_MI_MAMMOTH = new AkumaNoMiItem("Zou Zou no Mi, Model: Mammoth", 2, FruitType.ANCIENT_ZOAN, new Ability[] { (Ability)MammothGuardPointAbility.INSTANCE, (Ability)MammothHeavyPointAbility.INSTANCE, (Ability)TrampleAbility.INSTANCE, (Ability)AncientStompAbility.INSTANCE, (Ability)AncientSweepAbility.INSTANCE, (Ability)AncientTrunkShotAbility.INSTANCE });
  public static final AkumaNoMiItem HITO_HITO_NO_MI = new AkumaNoMiItem("Hito Hito no Mi", 1, FruitType.ZOAN, new Ability[0]);
  public static final AkumaNoMiItem USHI_USHI_NO_MI_GIRAFFE = new AkumaNoMiItem("Ushi Ushi no Mi, Model: Giraffe", 1, FruitType.ZOAN, new Ability[] { (Ability)GiraffeHeavyPointAbility.INSTANCE, (Ability)GiraffeWalkPointAbility.INSTANCE, (Ability)BiganAbility.INSTANCE });
  public static final AkumaNoMiItem MOGU_MOGU_NO_MI = new AkumaNoMiItem("Mogu Mogu no Mi", 1, FruitType.ZOAN, new Ability[] { (Ability)MoguHeavyPointAbility.INSTANCE, (Ability)MoguraBananaAbility.INSTANCE, (Ability)MoguraTonpoAbility.INSTANCE });
  public static final AkumaNoMiItem USHI_USHI_NO_MI_BISON = new AkumaNoMiItem("Ushi Ushi no Mi, Model: Bison", 2, FruitType.ZOAN, new Ability[] { (Ability)BisonHeavyPointAbility.INSTANCE, (Ability)BisonWalkPointAbility.INSTANCE, (Ability)FiddleBanffAbility.INSTANCE, (Ability)KokuteiCrossAbility.INSTANCE, (Ability)BisonSmashAbility.INSTANCE });
  public static final AkumaNoMiItem ZOU_ZOU_NO_MI = new AkumaNoMiItem("Zou Zou no Mi", 2, FruitType.ZOAN, new Ability[] { (Ability)ZouGuardPointAbility.INSTANCE, (Ability)ZouHeavyPointAbility.INSTANCE, (Ability)TrunkShotAbility.INSTANCE, (Ability)GreatStompAbility.INSTANCE, (Ability)IvoryDartAbility.INSTANCE, (Ability)IvoryStompAbility.INSTANCE });
  public static final AkumaNoMiItem HITO_HITO_NO_MI_DAIBUTSU = new AkumaNoMiItem("Hito Hito no Mi, Model: Daibutsu", 3, FruitType.MYTHICAL_ZOAN, new Ability[] { (Ability)HitoDaibutsuPointAbility.INSTANCE, ImpactBlastAbility.INSTANCE });
  public static final AkumaNoMiItem TORI_TORI_NO_MI_PHOENIX = new AkumaNoMiItem("Tori Tori no Mi, Model: Phoenix", 3, FruitType.MYTHICAL_ZOAN, new Ability[] { (Ability)PhoenixAssaultPointAbility.INSTANCE, (Ability)PhoenixFlyPointAbility.INSTANCE, (Ability)PhoenixGoenAbility.INSTANCE, (Ability)SaiseiNoHonoAbility.INSTANCE, (Ability)FujiazamiAbility.INSTANCE, (Ability)TenseiNoSoenAbility.INSTANCE, (Ability)BlueBirdAbility.INSTANCE, (Ability)FlamesOfRegenerationAbility.INSTANCE, (Ability)PhoenixFlightAbility.INSTANCE });
  
  public static final AkumaNoMiItem MANE_MANE_NO_MI = new AkumaNoMiItem("Mane Mane no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)ManeManeMemoryAbility.INSTANCE });
  public static final AkumaNoMiItem MEGA_MEGA_NO_MI = new AkumaNoMiItem("Mega Mega no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)MegaMegaAbility.INSTANCE });
  public static final AkumaNoMiItem KARU_KARU_NO_MI = new AkumaNoMiItem("Karu Karu no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)IngaZarashiAbility.INSTANCE, (Ability)KarmaAbility.INSTANCE });
  public static final AkumaNoMiItem JIKI_JIKI_NO_MI = new AkumaNoMiItem("Jiki Jiki no Mi", 3, FruitType.PARAMECIA, new Ability[] { (Ability)AttractAbility.INSTANCE, (Ability)RepelAbility.INSTANCE, (Ability)PunkGibsonAbility.INSTANCE, (Ability)PunkPistolsAbility.INSTANCE, (Ability)PunkCornaDioAbility.INSTANCE, (Ability)DamnedPunkAbility.INSTANCE });
  
  public static final AkumaNoMiItem OTO_OTO_NO_MI = new AkumaNoMiItem("Oto Oto no Mi", 2, FruitType.PARAMECIA, new Ability[] { BonAbility.INSTANCE, DonAbility.INSTANCE, ShanAbility.INSTANCE });
  public static final AkumaNoMiItem BARA_BARA_NO_MI = new AkumaNoMiItem("Bara Bara No Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)BaraBaraHoAbility.INSTANCE, (Ability)BaraBaraFestivalAbility.INSTANCE, (Ability)KuchuKirimomiDaiCircusAbility.INSTANCE, (Ability)BaraBaraCarAbility.INSTANCE, (Ability)BaraSplitAbility.INSTANCE, (Ability)SlashDamageImmunityAbility.BARA_INSTANCE });
  public static final AkumaNoMiItem SUBE_SUBE_NO_MI = new AkumaNoMiItem("Sube Sube No Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)SubeSubeSpurAbility.INSTANCE, (Ability)SubeSubeDeflectAbility.INSTANCE, (Ability)PotionImmunitiesAbility.HANDCUFF_INSTANCE });
  public static final AkumaNoMiItem KOBU_KOBU_NO_MI = new AkumaNoMiItem("Kobu Kobu No Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)ShoureiAbility.INSTANCE, (Ability)PotionImmunitiesAbility.NEGATIVE_INSTANCE });
  public static final AkumaNoMiItem HANA_HANA_NO_MI = new AkumaNoMiItem("Hana Hana No Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)DosFleurClutchAbility.INSTANCE, (Ability)SeisFleurSlapAbility.INSTANCE, (Ability)SeisFleurTwistAbility.INSTANCE, (Ability)VeinteFleurCalendulaAbility.INSTANCE, (Ability)CienFleurWingAbility.INSTANCE, (Ability)CienFleurStompAbility.INSTANCE, (Ability)MilFleurAbility.INSTANCE });
  public static final AkumaNoMiItem MINI_MINI_NO_MI = new AkumaNoMiItem("Mini Mini No Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)MiniMiniAbility.INSTANCE, (Ability)PaperFloatAbility.INSTANCE });
  public static final AkumaNoMiItem HISO_HISO_NO_MI = new AkumaNoMiItem("Hiso Hiso No Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)ForewarnAbility.INSTANCE, (Ability)LookoutAbility.INSTANCE, (Ability)AnimalFriendAbility.INSTANCE });
  public static final AkumaNoMiItem KUKU_KUKU_NO_MI = new AkumaNoMiItem("Kuku Kuku no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)GourmetamorphosisAbility.INSTANCE, (Ability)GastronomorphAbility.INSTANCE, (Ability)GourmetSnipeAbility.INSTANCE });
  public static final AkumaNoMiItem KIRA_KIRA_NO_MI = new AkumaNoMiItem("Kira Kira no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)DiamondBodyAbility.INSTANCE, (Ability)BrilliantPunkAbility.INSTANCE, (Ability)CabochonKnuckleAbility.INSTANCE });
  
  public static final AkumaNoMiItem BETA_BETA_NO_MI = new AkumaNoMiItem("Beta Beta no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)BetaCoatingAbility.INSTANCE, BetaLauncherAbility.INSTANCE, (Ability)HanamizuShinkenAbility.INSTANCE, (Ability)BetaBetaChainAbility.INSTANCE, (Ability)PotionImmunitiesAbility.BETA_INSTANCE });
  public static final AkumaNoMiItem NAGI_NAGI_NO_MI = new AkumaNoMiItem("Nagi Nagi no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)SilentAbility.INSTANCE });
  public static final AkumaNoMiItem PERO_PERO_NO_MI = new AkumaNoMiItem("Pero Pero no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)CandyManAbility.INSTANCE, (Ability)CandyWallAbility.INSTANCE, (Ability)CandyEscalatorAbility.INSTANCE, (Ability)CandyWaveAbility.INSTANCE, (Ability)CandyArmorAbility.INSTANCE, (Ability)PotionImmunitiesAbility.PERO_INSTANCE });
  public static final AkumaNoMiItem NETSU_NETSU_NO_MI = new AkumaNoMiItem("Netsu Netsu no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)NetsuEnhancementAbility.INSTANCE, (Ability)NekkaiJigokuAbility.INSTANCE, (Ability)NekkaiGyoraiAbility.INSTANCE, (Ability)HeatDenasshiAbility.INSTANCE, (Ability)PotionImmunitiesAbility.HEAT_INSTANCE });
  public static final AkumaNoMiItem SUI_SUI_NO_MI = new AkumaNoMiItem("Sui Sui no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)FreeSwimmingAbility.INSTANCE, (Ability)NekomimiPunchAbility.INSTANCE, (Ability)NyanNyanSuplexAbility.INSTANCE, (Ability)NyanNyanSuplexBabyBusterAbility.INSTANCE });
  
  public static final AkumaNoMiItem AWA_AWA_NO_MI = new AkumaNoMiItem("Awa Awa no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)GoldenHourAbility.INSTANCE, (Ability)RelaxHourAbility.INSTANCE, SoapDefenseAbility.INSTANCE });
  
  public static final AkumaNoMiItem WARA_WARA_NO_MI = new AkumaNoMiItem("Wara Wara no Mi", 3, FruitType.PARAMECIA, new Ability[] { StrawManAbility.INSTANCE, (Ability)WarabideSwordAbility.INSTANCE, (Ability)LifeMinusAbility.INSTANCE });
  public static final AkumaNoMiItem KACHI_KACHI_NO_MI = new AkumaNoMiItem("Kachi Kachi no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)EvaporateAbility.INSTANCE, (Ability)HotBoilingSpecialAbility.INSTANCE, (Ability)VulcanizationAbility.INSTANCE });
  public static final AkumaNoMiItem DOA_DOA_NO_MI = new AkumaNoMiItem("Doa Doa no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)DoorDoorAbility.INSTANCE, (Ability)KaitenDoorAbility.INSTANCE, (Ability)AirDoorAbility.INSTANCE });
  public static final AkumaNoMiItem CHIYU_CHIYU_NO_MI = new AkumaNoMiItem("Chiyu Chiyu no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)HealingTouchAbility.INSTANCE, ChiyupopoAbility.INSTANCE, KenpopoAbility.INSTANCE, (Ability)TearsAbility.INSTANCE });
  public static final AkumaNoMiItem SABI_SABI_NO_MI = new AkumaNoMiItem("Sabi Sabi no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)RustTouchAbility.INSTANCE, (Ability)RustBreakAbility.INSTANCE, (Ability)RustSkinAbility.INSTANCE });
  public static final AkumaNoMiItem YOMI_YOMI_NO_MI = new AkumaNoMiItem("Yomi Yomi no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)SoulParadeAbility.INSTANCE, (Ability)KasuriutaFubukiGiriAbility.INSTANCE, (Ability)YomiNoReikiAbility.INSTANCE, (Ability)PotionImmunitiesAbility.YOMI_INSTANCE });
  public static final AkumaNoMiItem BAKU_BAKU_NO_MI = new AkumaNoMiItem("Baku Baku no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)BakuMunchAbility.INSTANCE, (Ability)BeroCannonAbility.INSTANCE, (Ability)BakuTsuihoAbility.INSTANCE, (Ability)BakuFactoryAbility.INSTANCE });
  public static final AkumaNoMiItem HORU_HORU_NO_MI = new AkumaNoMiItem("Horu Horu no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)OnnaHormoneAbility.INSTANCE, (Ability)ChiyuHormoneAbility.INSTANCE, (Ability)TensionHormoneAbility.INSTANCE, (Ability)GanmenSeichoHormoneAbility.INSTANCE, (Ability)DeathWinkAbility.INSTANCE });
  public static final AkumaNoMiItem KILO_KILO_NO_MI = new AkumaNoMiItem("Kilo Kilo no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)KiloPress1Ability.INSTANCE, (Ability)KiloPress10000Ability.INSTANCE, (Ability)KiloPunch5000Ability.INSTANCE });
  public static final AkumaNoMiItem GOE_GOE_NO_MI = new AkumaNoMiItem("Goe Goe no Mi", 1, FruitType.PARAMECIA, new Ability[] { TodorokiAbility.INSTANCE, (Ability)DragonsRoarAbility.INSTANCE });
  public static final AkumaNoMiItem MERO_MERO_NO_MI = new AkumaNoMiItem("Mero Mero no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)MeroMeroMellowAbility.INSTANCE, (Ability)PistolKissAbility.INSTANCE, (Ability)SlaveArrowAbility.INSTANCE, (Ability)PerfumeFemurAbility.INSTANCE });
  public static final AkumaNoMiItem ORI_ORI_NO_MI = new AkumaNoMiItem("Ori Ori no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)BindAbility.INSTANCE, (Ability)GreatCageAbility.INSTANCE, (Ability)AwaseBaoriAbility.INSTANCE });
  public static final AkumaNoMiItem SUPA_SUPA_NO_MI = new AkumaNoMiItem("Supa Supa no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)SpiderAbility.INSTANCE, (Ability)SparClawAbility.INSTANCE, (Ability)SpiralHollowAbility.INSTANCE, (Ability)SparklingDaisyAbility.INSTANCE, (Ability)AtomicSpurtAbility.INSTANCE });
  public static final AkumaNoMiItem HORO_HORO_NO_MI = new AkumaNoMiItem("Horo Horo no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)NegativeHollowAbility.INSTANCE, (Ability)MiniHollowAbility.INSTANCE, (Ability)TokuHollowAbility.INSTANCE, (Ability)YutaiRidatsuAbility.INSTANCE });
  public static final AkumaNoMiItem ITO_ITO_NO_MI = new AkumaNoMiItem("Ito Ito no Mi", 3, FruitType.PARAMECIA, new Ability[] { (Ability)ParasiteAbility.INSTANCE, (Ability)SoraNoMichiAbility.INSTANCE, (Ability)TamaitoAbility.INSTANCE, (Ability)FullbrightAbility.INSTANCE, (Ability)OverheatAbility.INSTANCE, (Ability)KumoNoSugakiAbility.INSTANCE, (Ability)TorikagoAbility.INSTANCE, (Ability)BlackKnightAbility.INSTANCE });
  public static final AkumaNoMiItem ZUSHI_ZUSHI_NO_MI = new AkumaNoMiItem("Zushi Zushi no Mi", 3, FruitType.PARAMECIA, new Ability[] { (Ability)JigokuTabiAbility.INSTANCE, (Ability)SagariNoRyuseiAbility.INSTANCE, (Ability)MokoAbility.INSTANCE, (Ability)AbareHimatsuriAbility.INSTANCE, (Ability)GraviZoneAbility.INSTANCE, (Ability)GraviPullAbility.INSTANCE });
  public static final AkumaNoMiItem BARI_BARI_NO_MI = new AkumaNoMiItem("Bari Bari no Mi", 1, FruitType.PARAMECIA, new Ability[] { (Ability)BarrierAbility.INSTANCE, (Ability)BarrierBallAbility.INSTANCE, (Ability)BarrierCrashAbility.INSTANCE, (Ability)BariBariNoPistolAbility.INSTANCE, (Ability)BarrierbilityStairsAbility.INSTANCE, (Ability)BarrierbilityBatAbility.INSTANCE });
  public static final AkumaNoMiItem DOKU_DOKU_NO_MI = new AkumaNoMiItem("Doku Doku no Mi", 3, FruitType.PARAMECIA, new Ability[] { (Ability)HydraAbility.INSTANCE, (Ability)ChloroBallAbility.INSTANCE, (Ability)DokuFuguAbility.INSTANCE, (Ability)VenomRoadAbility.INSTANCE, (Ability)DokuGumoAbility.INSTANCE, (Ability)VenomDemonAbility.INSTANCE, (Ability)PoisonToRegenAbility.INSTANCE });
  public static final AkumaNoMiItem DORU_DORU_NO_MI = new AkumaNoMiItem("Doru Doru no Mi", 2, FruitType.PARAMECIA, new Ability[] { CandleWallAbility.INSTANCE, CandleHouseAbility.INSTANCE, (Ability)DoruDoruArtsMoriAbility.INSTANCE, DoruDoruArtsKenAbility.INSTANCE, CandleLockAbility.INSTANCE, DoruDoruArtsPickaxeAbility.INSTANCE, (Ability)DoruDoruBallAbility.INSTANCE, (Ability)DoruDoruNoYakataAbility.INSTANCE, (Ability)CandleChampionAbility.INSTANCE });
  public static final AkumaNoMiItem KAGE_KAGE_NO_MI = new AkumaNoMiItem("Kage Kage no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)DoppelmanAbility.INSTANCE, (Ability)KagemushaAbility.INSTANCE, (Ability)BrickBatAbility.INSTANCE, (Ability)BlackBoxAbility.INSTANCE, (Ability)TsunoTokageAbility.INSTANCE, KageKakumeiAbility.INSTANCE, (Ability)KageGiriAbility.INSTANCE, (Ability)NightmareSoldiersAbility.INSTANCE, (Ability)ShadowsAsgardAbility.INSTANCE });
  public static final AkumaNoMiItem GURA_GURA_NO_MI = new AkumaNoMiItem("Gura Gura no Mi", 3, FruitType.PARAMECIA, new Ability[] { GekishinAbility.INSTANCE, (Ability)ShingenNoIchigekiAbility.INSTANCE, TenchiMeidoAbility.INSTANCE, ShimaYurashiAbility.INSTANCE, (Ability)PotionImmunitiesAbility.GURA_INSTANCE });
  public static final AkumaNoMiItem BOMU_BOMU_NO_MI = new AkumaNoMiItem("Bomu Bomu no Mi", 2, FruitType.PARAMECIA, new Ability[] { NoseFancyCannonAbility.INSTANCE, KickBombAbility.INSTANCE, (Ability)ZenshinKibakuAbility.INSTANCE, ExplosivePunchAbility.INSTANCE, (Ability)BreezeBreathBombAbility.INSTANCE });
  public static final AkumaNoMiItem NIKYU_NIKYU_NO_MI = new AkumaNoMiItem("Nikyu Nikyu no Mi", 2, FruitType.PARAMECIA, new Ability[] { PadHoAbility.INSTANCE, HanpatsuAbility.INSTANCE, TsuppariPadHoAbility.INSTANCE, UrsusShockAbility.INSTANCE, (Ability)PuniAbility.INSTANCE });
  public static final AkumaNoMiItem NORO_NORO_NO_MI = new AkumaNoMiItem("Noro Noro no Mi", 1, FruitType.PARAMECIA, new Ability[] { NoroNoroBeamAbility.INSTANCE, NoroNoroBeamSwordAbility.INSTANCE, KyubiRushAbility.INSTANCE });
  public static final AkumaNoMiItem OPE_OPE_NO_MI = new AkumaNoMiItem("Ope Ope no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)RoomAbility.INSTANCE, ShamblesAbility.INSTANCE, TaktAbility.INSTANCE, InjectionShotAbility.INSTANCE, CounterShockAbility.INSTANCE, MesAbility.INSTANCE, GammaKnifeAbility.INSTANCE });
  public static final AkumaNoMiItem SUKE_SUKE_NO_MI = new AkumaNoMiItem("Suke Suke no Mi", 1, FruitType.PARAMECIA, new Ability[] { SkattingAbility.INSTANCE, ShishaNoTeAbility.INSTANCE, SukePunchAbility.INSTANCE });
  public static final AkumaNoMiItem GOMU_GOMU_NO_MI = new AkumaNoMiItem("Gomu Gomu no Mi", 2, FruitType.PARAMECIA, new Ability[] { (Ability)GomuGomuNoPistolAbility.INSTANCE, (Ability)GomuGomuNoGatlingAbility.INSTANCE, (Ability)GomuGomuNoBazookaAbility.INSTANCE, (Ability)GomuGomuNoRocketAbility.INSTANCE, (Ability)GearSecondAbility.INSTANCE, (Ability)GearThirdAbility.INSTANCE, (Ability)GearFourthAbility.INSTANCE, (Ability)BouncyAbility.INSTANCE });
  public static final AkumaNoMiItem BANE_BANE_NO_MI = new AkumaNoMiItem("Bane Bane no Mi", 1, FruitType.PARAMECIA, new Ability[] { SpringHopperAbility.INSTANCE, SpringSnipeAbility.INSTANCE, SpringDeathKnockAbility.INSTANCE, (Ability)SpringMovementAbility.INSTANCE, (Ability)NoFallDamageAbility.INSTANCE });

  
  public static final AkumaNoMiItem YUKI_YUKI_NO_MI = new AkumaNoMiItem("Yuki Yuki no Mi", 3, FruitType.LOGIA, new Ability[] { (Ability)KamakuraAbility.INSTANCE, (Ability)YukiRabiAbility.INSTANCE, (Ability)KamakuraJussoshiAbility.INSTANCE, (Ability)TabiraYukiAbility.INSTANCE, (Ability)YukiGakiAbility.INSTANCE, (Ability)FubukiAbility.INSTANCE, (Ability)YukiPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.YUKI_INSTANCE });
  public static final AkumaNoMiItem YAMI_YAMI_NO_MI = new AkumaNoMiItem("Yami Yami no Mi", 3, FruitType.LOGIA, new Ability[] { (Ability)BlackHoleAbility.INSTANCE, (Ability)BlackRoadAbility.INSTANCE, (Ability)LiberationAbility.INSTANCE, (Ability)KurouzuAbility.INSTANCE, (Ability)DarkMatterAbility.INSTANCE, (Ability)BlackWorldAbility.INSTANCE });
  public static final AkumaNoMiItem GASU_GASU_NO_MI = new AkumaNoMiItem("Gasu Gasu no Mi", 3, FruitType.LOGIA, new Ability[] { (Ability)GasRobeAbility.INSTANCE, (Ability)GastanetAbility.INSTANCE, (Ability)GastilleAbility.INSTANCE, BlueSwordAbility.INSTANCE, (Ability)KarakuniAbility.INSTANCE, (Ability)ShinokuniAbility.INSTANCE, (Ability)SpecialFlyAbility.INSTANCE, (Ability)PotionImmunitiesAbility.GASU_INSTANCE, (Ability)GasuPassiveEvents.INVULNERABILITY_INSTANCE });
  public static final AkumaNoMiItem MAGU_MAGU_NO_MI = new AkumaNoMiItem("Magu Magu no Mi", 3, FruitType.LOGIA, new Ability[] { DaiFunkaAbility.INSTANCE, RyuseiKazanAbility.INSTANCE, MeigoAbility.INSTANCE, BakuretsuKazanAbility.INSTANCE, LavaFlowAbility.INSTANCE, MagmaCoatingAbility.INSTANCE, (Ability)MaguPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.LOGIA_HEAT_INSTANCE });
  public static final AkumaNoMiItem SUNA_SUNA_NO_MI = new AkumaNoMiItem("Suna Suna no Mi", 3, FruitType.LOGIA, new Ability[] { DesertSpadaAbility.INSTANCE, DesertGrandeSpadaAbility.INSTANCE, SablesAbility.INSTANCE, (Ability)GrandeSablesAbility.INSTANCE, BarjanAbility.INSTANCE, DesertEncierroAbility.INSTANCE, GroundDeathAbility.INSTANCE, DesertGirasoleAbility.INSTANCE, (Ability)SpecialFlyAbility.INSTANCE, SablesPesadoAbility.INSTANCE, (Ability)SunaPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.LOGIA_INSTANCE });
  public static final AkumaNoMiItem GORO_GORO_NO_MI = new AkumaNoMiItem("Goro Goro no Mi", 3, FruitType.LOGIA, new Ability[] { SparkStepAbility.INSTANCE, (Ability)ElThorAbility.INSTANCE, VoltVariAbility.INSTANCE, KariAbility.INSTANCE, SangoAbility.INSTANCE, RaigoAbility.INSTANCE, (Ability)VoltAmaruAbility.INSTANCE, (Ability)GoroPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.LOGIA_HEAT_INSTANCE });
  public static final AkumaNoMiItem MOKU_MOKU_NO_MI = new AkumaNoMiItem("Moku Moku no Mi", 3, FruitType.LOGIA, new Ability[] { WhiteOutAbility.INSTANCE, WhiteSnakeAbility.INSTANCE, WhiteLauncherAbility.INSTANCE, WhiteStrikeAbility.INSTANCE, WhiteBlowRushAbility.INSTANCE, (Ability)WhiteGrabAbility.INSTANCE, (Ability)SpecialFlyAbility.INSTANCE, (Ability)WhitePullAbility.INSTANCE, (Ability)SmokeLaunchAbility.INSTANCE, (Ability)MokuPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.MOKU_INSTANCE });
  public static final AkumaNoMiItem PIKA_PIKA_NO_MI = new AkumaNoMiItem("Pika Pika no Mi", 3, FruitType.LOGIA, new Ability[] { YataNoKagamiAbility.INSTANCE, YasakaniNoMagatamaAbility.INSTANCE, AmaNoMurakumoAbility.INSTANCE, AmaterasuAbility.INSTANCE, FlashAbility.INSTANCE, (Ability)PikaPassiveEvents.INVULNERABILITY_INSTANCE, LightAccelerationAbility.INSTANCE, (Ability)PotionImmunitiesAbility.LOGIA_INSTANCE });
  public static final AkumaNoMiItem HIE_HIE_NO_MI = new AkumaNoMiItem("Hie Hie no Mi", 3, FruitType.LOGIA, new Ability[] { IceBlockPartisanAbility.INSTANCE, IceAgeAbility.INSTANCE, IceBallAbility.INSTANCE, IceSaberAbility.INSTANCE, IceTimeCapsuleAbility.INSTANCE, IceBlockPheasantAbility.INSTANCE, (Ability)IceBlockAvalancheAbility.INSTANCE, (Ability)HiePassiveEvents.INVULNERABILITY_INSTANCE, (Ability)FrostWalkerAbility.INSTANCE, (Ability)PotionImmunitiesAbility.HIE_INSTANCE });
  public static final AkumaNoMiItem MERA_MERA_NO_MI = new AkumaNoMiItem("Mera Mera no Mi", 3, FruitType.LOGIA, new Ability[] { HikenAbility.INSTANCE, HiganAbility.INSTANCE, DaiEnkaiEnteiAbility.INSTANCE, HidarumaAbility.INSTANCE, JujikaAbility.INSTANCE, (Ability)HeatDashAbility.INSTANCE, (Ability)KyokaenAbility.INSTANCE, (Ability)HibashiraAbility.INSTANCE, (Ability)MeraPassiveEvents.INVULNERABILITY_INSTANCE, (Ability)PotionImmunitiesAbility.LOGIA_HEAT_INSTANCE });
  
  public static final Ability[] HAKI_ABILITIES = new Ability[] { (Ability)BusoshokuHakiFullBodyHardeningAbility.INSTANCE, (Ability)BusoshokuHakiHardeningAbility.INSTANCE, (Ability)BusoshokuHakiImbuingAbility.INSTANCE, (Ability)HaoshokuHakiAbility.INSTANCE, (Ability)KenbunshokuHakiAuraAbility.INSTANCE, (Ability)KenbunshokuHakiFutureSightAbility.INSTANCE };
  
  public static final Ability[] MARINE_ABILITIES = new Ability[] { (Ability)SmallMusterAbility.INSTANCE, (Ability)MusterAbility.INSTANCE };
  
  public static final Ability[] HUMAN_ABILITIES = new Ability[] { SoruAbility.INSTANCE, TekkaiAbility.INSTANCE, ShiganAbility.INSTANCE, RankyakuAbility.INSTANCE, KamieAbility.INSTANCE, (Ability)GeppoAbility.INSTANCE, (Ability)RokuoganAbility.INSTANCE };
  public static final Ability[] FISHMAN_ABILITIES = new Ability[] { UchimizuAbility.INSTANCE, SamehadaShoteiAbility.INSTANCE, MurasameAbility.INSTANCE, KarakusagawaraSeikenAbility.INSTANCE, KachiageHaisokuAbility.INSTANCE, (Ability)TwoFishEngineAbility.INSTANCE, (Ability)YarinamiAbility.INSTANCE };
  public static final Ability[] CYBORG_ABILITIES = new Ability[] { StrongRightAbility.INSTANCE, RadicalBeamAbility.INSTANCE, FreshFireAbility.INSTANCE, CoupDeBooAbility.INSTANCE, CoupDeVentAbility.INSTANCE, ColaOverdriveAbility.INSTANCE };
  public static final Ability[] MINK_ABILITIES = new Ability[] { (Ability)EleclawAbility.INSTANCE, (Ability)ElectricalMissileAbility.INSTANCE, (Ability)ElectricalTempestaAbility.INSTANCE, (Ability)ElectricalShowerAbility.INSTANCE, (Ability)ElectricalLunaAbility.INSTANCE, (Ability)SulongAbility.INSTANCE };
  
  public static final Ability[] SWORDSMAN_ABILITIES = new Ability[] { (Ability)ShiShishiSonsonAbility.INSTANCE, YakkodoriAbility.INSTANCE, SanbyakurokujuPoundHoAbility.INSTANCE, OTatsumakiAbility.INSTANCE, (Ability)HiryuKaenAbility.INSTANCE };
  public static final Ability[] SNIPER_ABILITIES = new Ability[] { KaenBoshiAbility.INSTANCE, KemuriBoshiAbility.INSTANCE, (Ability)RenpatsuNamariBoshiAbility.INSTANCE, SakuretsuSabotenBoshiAbility.INSTANCE, TetsuBoshiAbility.INSTANCE, TokuyoAburaBoshiAbility.INSTANCE, NemuriBoshiAbility.INSTANCE };
  public static final Ability[] DOCTOR_ABILITIES = new Ability[] { (Ability)FirstAidAbility.INSTANCE, (Ability)MedicBagExplosionAbility.INSTANCE, FailedExperimentAbility.INSTANCE, (Ability)DopingAbility.INSTANCE };
  public static final Ability[] ART_OF_WEATHER_ABILITIES = new Ability[] { (Ability)HeatBallAbility.INSTANCE, (Ability)CoolBallAbility.INSTANCE, (Ability)WeatherCloudTempo.INSTANCE, (Ability)ThunderBallAbility.INSTANCE, (Ability)ThunderboltTempo.INSTANCE, (Ability)ThunderstormTempo.INSTANCE, (Ability)ThunderLanceTempo.INSTANCE, (Ability)FogTempo.INSTANCE, (Ability)MirageTempo.INSTANCE, (Ability)RainTempo.INSTANCE, (Ability)WeatherEggAbility.INSTANCE, (Ability)GustSwordAbility.INSTANCE };
  public static final Ability[] BLACK_LEG_ABILITIES = new Ability[] { (Ability)DiableJambeAbility.INSTANCE, (Ability)SkywalkAbility.INSTANCE, (Ability)AntiMannerKickCourseAbility.INSTANCE, (Ability)ExtraHachisAbility.INSTANCE, (Ability)PartyTableKickCourseAbility.INSTANCE, (Ability)ConcasseAbility.INSTANCE, (Ability)BienCuitGrillShotAbility.INSTANCE };
  public static final Ability[] BRAWLER_ABILITIES = new Ability[] { (Ability)GenkotsuMeteorAbility.INSTANCE, (Ability)KingPunchAbility.INSTANCE, (Ability)HakaiHoAbility.INSTANCE, (Ability)JishinHoAbility.INSTANCE, (Ability)SpinningBrawlAbility.INSTANCE, (Ability)SuplexAbility.INSTANCE };

  
  static {
    for (AkumaNoMiItem df : ModValues.devilfruits) {
      
      WyRegistry.registerItem((Item)df, df.getDevilFruitName());
      registerAbilities(df.abilities);
    } 
    
    registerAbilities(HUMAN_ABILITIES);
    registerAbilities(FISHMAN_ABILITIES);
    registerAbilities(CYBORG_ABILITIES);
    registerAbilities(MINK_ABILITIES);
    
    registerAbilities(SWORDSMAN_ABILITIES);
    registerAbilities(SNIPER_ABILITIES);
    registerAbilities(DOCTOR_ABILITIES);
    registerAbilities(ART_OF_WEATHER_ABILITIES);
    registerAbilities(BLACK_LEG_ABILITIES);
    registerAbilities(BRAWLER_ABILITIES);
    
    registerAbilities(HAKI_ABILITIES);
    
    registerAbilities(MARINE_ABILITIES);

    
    WyRegistry.registerAbility((Ability)ZoomAbility.INSTANCE);

    
    WyRegistry.registerAbility((Ability)GunArrayAbility.INSTANCE);

    
    WyRegistry.registerAbility((Ability)ColaBackpackBonusAbility.INSTANCE);

    
    WyRegistry.registerAbility((Ability)ShinzoMassageAbility.INSTANCE);
  }

  
  private static void registerAbilities(Ability[] abilities) {
    Arrays.<Ability>stream(abilities).filter(Objects::nonNull).forEach(WyRegistry::registerAbility);
  }
}


