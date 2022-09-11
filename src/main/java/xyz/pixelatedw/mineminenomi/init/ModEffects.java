package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.effects.AbilityOffEffect;
import xyz.pixelatedw.mineminenomi.effects.BindEffect;
import xyz.pixelatedw.mineminenomi.effects.BlackBoxEffect;
import xyz.pixelatedw.mineminenomi.effects.BleedingEffect;
import xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect;
import xyz.pixelatedw.mineminenomi.effects.CandleLockEffect;
import xyz.pixelatedw.mineminenomi.effects.CandyStuckEffect;
import xyz.pixelatedw.mineminenomi.effects.ChiyuHormoneEffect;
import xyz.pixelatedw.mineminenomi.effects.DizzyEffect;
import xyz.pixelatedw.mineminenomi.effects.DoorHeadEffect;
import xyz.pixelatedw.mineminenomi.effects.DrunkEffect;
import xyz.pixelatedw.mineminenomi.effects.FatigueEffect;
import xyz.pixelatedw.mineminenomi.effects.FrostBiteEffect;
import xyz.pixelatedw.mineminenomi.effects.FrozenEffect;
import xyz.pixelatedw.mineminenomi.effects.GanmenSeichoHormoneEffect;
import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
import xyz.pixelatedw.mineminenomi.effects.HakiOveruseEffect;
import xyz.pixelatedw.mineminenomi.effects.HanaEffect;
import xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect;
import xyz.pixelatedw.mineminenomi.effects.LookoutEffect;
import xyz.pixelatedw.mineminenomi.effects.LovestruckEffect;
import xyz.pixelatedw.mineminenomi.effects.MovementBlockedEffect;
import xyz.pixelatedw.mineminenomi.effects.NegativeEffect;
import xyz.pixelatedw.mineminenomi.effects.NoHandsEffect;
import xyz.pixelatedw.mineminenomi.effects.NoroSlownessEffect;
import xyz.pixelatedw.mineminenomi.effects.ParalysisEffect;
import xyz.pixelatedw.mineminenomi.effects.ReducedFallEffect;
import xyz.pixelatedw.mineminenomi.effects.ShinzoMassageEffect;
import xyz.pixelatedw.mineminenomi.effects.SilentEffect;
import xyz.pixelatedw.mineminenomi.effects.SmokeEffect;
import xyz.pixelatedw.mineminenomi.effects.StickyEffect;
import xyz.pixelatedw.mineminenomi.effects.TensionHormoneEffect;
import xyz.pixelatedw.mineminenomi.effects.UnconsciousEffect;
import xyz.pixelatedw.mineminenomi.effects.WashedEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects
{
  public static final Effect BUBBLY_CORAL = (Effect)new BubblyCoralEffect();
  
  public static final Effect ANIMAL_LOOKOUT = (Effect)new LookoutEffect();
  
  public static final Effect CANDLE_LOCK = (new CandleLockEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "30ee2305-2aaa-4ed8-9c7b-de0ae33a2024", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "0d80c985-ee10-49c9-bc0b-d9eb0cdb9666", 100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "b94c7ca4-ead0-4927-b4c1-51033159a9ff", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect LOVESTRUCK = (new LovestruckEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "eb1809e8-a81d-4076-94aa-6f06e3a64920", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "50b418ff-a637-47be-a13e-1a848dea1638", 100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "51958b7e-3eb7-439e-a49a-de7387037c2d", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect NEGATIVE = (new NegativeEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "11147784-f615-47da-a28c-20c721cf5e9f", -0.25D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -1.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -5.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
  
  public static final Effect CHIYU_HORMONE = (new ChiyuHormoneEffect())
    .addAttributesModifier(ModAttributes.REGEN_RATE, "5548045f-4680-4bc3-9278-5521ef1c2d00", 2.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect FROSTBITE = (new FrostBiteEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "11147784-f615-47da-a28c-20c721cf5e9f", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -0.800000011920929D, AttributeModifier.Operation.MULTIPLY_TOTAL);
  
  public static final Effect FROZEN = (new FrozenEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "4c6e221d-2191-4960-a642-d38fcbea9c4f", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "0aca6c19-061a-4b3f-9ba8-262f5e6f3815", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "777f14bf-fe3f-4f19-9d2c-eb69c04d5209", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "c24353cb-2efd-491f-a3f3-55a853d707ef", 100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "77fb7784-2d75-4e0a-8f39-f5c2dcd4fca8", -0.4D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.REGEN_RATE, "cbbcac4a-a068-47e9-befa-b4324a0faeaf", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fc7efdce-785b-4a61-bde2-10b95f99d7a4", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect TENSION_HORMONE = (new TensionHormoneEffect())
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "c24353cb-2efd-491f-a3f3-55a853d707ef", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "1abe0056-96d4-4cf8-962c-cbf7a54f28f3", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "dd83d35f-5190-4840-a582-f538300ce0af", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 10.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect GANMEN_SEICHO_HORMONE = (new GanmenSeichoHormoneEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "37360b52-ad8e-4c8a-a5a1-fd35a5e98d92", -0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL);
  
  public static final Effect DRUNK = (new DrunkEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "51b6c31a-cc9a-42d9-aa4c-d4fd98dcef2a", -0.005D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect ABILITY_OFF = (Effect)new AbilityOffEffect();
  
  public static final Effect DOOR_HEAD = (new DoorHeadEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "36f5c72e-9cb5-475a-a65d-60a4f8bc11e9", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "da340356-7e1f-4bc1-bdd6-1b757536705d", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect DIZZY = (new DizzyEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.025D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "e6a25b36-894a-4051-961d-6de8869b2900", -2.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect MOVEMENT_BLOCKED = (new MovementBlockedEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "323ffb58-0b57-434e-bdfc-354670e22d5f", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect UNCONSCIOUS = (new UnconsciousEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "042fdefa-052c-4d69-bb21-90b4bfbc094e", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "dac953cd-3c25-463a-a748-8c49b059fc67", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect WASHED = (new WashedEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "63f9c447-3f2d-4ac9-bf17-ea84f5352d46", -5.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "c6344fb0-aee5-46f3-89ef-1791256de5ad", -255.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "887875d0-c7c2-4d6d-afc7-0381d60bda6e", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "957a1347-334f-4988-825b-73dd9de5b9b0", -6.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect STICKY = (new StickyEffect())
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "9276cd71-345c-4420-ae9a-3c8f725908a3", 1.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "b13ba7ec-7103-4160-a5ff-139534a44691", -0.5D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fb997caf-43b6-4dc2-b5da-b504ab41545a", -255.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "c2731993-56d2-4c67-a6fe-cdd144bd9ff8", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "5208c5bf-44d0-4111-9787-f63db22f180c", -6.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect BLACK_BOX = (new BlackBoxEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "b1ddc653-67cc-4eb4-b6ee-8be108e70e2e", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "bacea9ea-a77a-4296-93db-90548eb2d30d", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect GUARDING = (new GuardingEffect(true))
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "94822875-5c1c-4b25-ba22-44ee9d50717c", -1000.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "b2144abf-f6cb-4994-9acd-721f949140cb", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 25.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "7b3a9108-6a36-11eb-9439-0242ac130002", 0.025D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect GUARDING_WITH_MOVEMENT = (new GuardingEffect(true))
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 0.5D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -1.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "7b3a9108-6a36-11eb-9439-0242ac130002", 0.02D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect PHYSICAL_MOVING_GUARD = (new GuardingEffect(true, true, false, new String[] { "mob", "player", "ability_projectile", "ability", "fall"
      })).addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 0.05D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 3.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect SHINZO_MASSAGE = (new ShinzoMassageEffect())
    .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "edfc5eb0-b647-4620-ac92-e81f71dbb9e1", 0.1D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "dca469c0-75d1-4c23-a21c-4fb1abd76c08", 4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.REGEN_RATE, "1eb54f3f-ba0a-4247-aa12-0ba54ed9de5d", 0.2D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect CANDY_STUCK = (new CandyStuckEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "f695cbb3-1223-4c64-97e2-c7979775fd4d", -100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "346e1665-7959-4a14-86ed-c8e2d1e5cd9a", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "23b16162-7090-4f27-87cc-613445852721", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "644893f5-3ae2-4b7c-bc61-32a7a7711286", 100.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.REGEN_RATE, "7d355019-7ef9-4beb-bcba-8b2608a73380", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fa4d711c-faa4-41cd-83c9-8f97edc5800e", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect PARALYSIS = (new ParalysisEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "f132c77e-9f47-42bb-8233-81c89b8ddcab", -4.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "18240623-27cc-4648-b148-ea3a6782bda6", -256.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect HAKI_OVERUSE = (Effect)new HakiOveruseEffect();
  
  public static final Effect FATIGUE_EFFECT = (new FatigueEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.01D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.1D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -1.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "e6a25b36-894a-4051-961d-6de8869b2900", -0.05D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.REGEN_RATE, "e6a25b36-894a-4051-961d-6de8869b2900", -0.1D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect SMOKE = (new SmokeEffect())
    .addAttributesModifier(ModAttributes.REGEN_RATE, "7d355019-7ef9-4beb-bcba-8b2608a73380", -0.25D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect REDUCED_FALL = (Effect)new ReducedFallEffect();
  
  public static final Effect HANDCUFFED = (new HandcuffedEffect(false))
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect HANDCUFFED_KAIROSEKI = (new HandcuffedEffect(true))
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect BIND = (new BindEffect())
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "b94c7ca4-ead0-4927-b4c1-51033159a9ff", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect NO_HANDS = (Effect)new NoHandsEffect();
  
  public static final Effect NORO_SLOWNESS = (new NoroSlownessEffect())
    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "8ab1e3cd-9688-402b-8876-73f314e174d2", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_BASE)
    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "bb904ec8-b548-4e1b-82ba-df237ad06f65", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_BASE)
    .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "61346567-335f-4a4d-a4a6-327faec00aa3", -1.0D, AttributeModifier.Operation.ADDITION)
    .addAttributesModifier(ModAttributes.TIME_PROGRESSION, "15494972-058f-4202-8190-a8a6f64a248d", -0.10000000149011612D, AttributeModifier.Operation.ADDITION);
  
  public static final Effect BLEEDING = (Effect)new BleedingEffect();
  
  public static final Effect HANA_HANDS = (Effect)new HanaEffect();
  
  public static final Effect SILENT = (Effect)new SilentEffect();

  
  static {
    WyRegistry.registerEffect(FROZEN, "Frozen");
    WyRegistry.registerEffect(CANDY_STUCK, "Candy Stuck");
    WyRegistry.registerEffect(BUBBLY_CORAL, "Bubbly Coral");
    WyRegistry.registerEffect(CANDLE_LOCK, "Candle Lock");
    WyRegistry.registerEffect(LOVESTRUCK, "Lovestruck");
    WyRegistry.registerEffect(FROSTBITE, "Frostbite");
    WyRegistry.registerEffect(NEGATIVE, "Negative");
    WyRegistry.registerEffect(CHIYU_HORMONE, "Chiyu Hormone");
    WyRegistry.registerEffect(TENSION_HORMONE, "Tension Hormone");
    WyRegistry.registerEffect(GANMEN_SEICHO_HORMONE, "Genmen Seicho Hormone");
    WyRegistry.registerEffect(DRUNK, "Drunk");
    WyRegistry.registerEffect(ABILITY_OFF, "Ability Off");
    WyRegistry.registerEffect(DOOR_HEAD, "Door Head");
    WyRegistry.registerEffect(MOVEMENT_BLOCKED, "Movement Blocked");
    WyRegistry.registerEffect(UNCONSCIOUS, "Unconscious");
    WyRegistry.registerEffect(BLACK_BOX, "Black Box");
    WyRegistry.registerEffect(GUARDING, "Guarding");
    WyRegistry.registerEffect(GUARDING_WITH_MOVEMENT, "Guarding With Movement");
    WyRegistry.registerEffect(PHYSICAL_MOVING_GUARD, "Physical Guarding");
    WyRegistry.registerEffect(SHINZO_MASSAGE, "Shinzo Massage");
    WyRegistry.registerEffect(WASHED, "Washed");
    WyRegistry.registerEffect(FATIGUE_EFFECT, "Fatigue");
    WyRegistry.registerEffect(STICKY, "Sticky");
    WyRegistry.registerEffect(ANIMAL_LOOKOUT, "Animal Lookout");
    WyRegistry.registerEffect(PARALYSIS, "Paralysis");
    WyRegistry.registerEffect(DIZZY, "Dizzy");
    WyRegistry.registerEffect(HAKI_OVERUSE, "Haki Overuse");
    WyRegistry.registerEffect(SMOKE, "Smoke");
    
    WyRegistry.registerEffect(REDUCED_FALL, "Reduced Fall");
    WyRegistry.registerEffect(HANDCUFFED, "Handcuffed");
    WyRegistry.registerEffect(HANDCUFFED_KAIROSEKI, "Handcuffed", "handcuffed_kairoseki");
    WyRegistry.registerEffect(BIND, "Bind");
    WyRegistry.registerEffect(NO_HANDS, "No Hands");
    WyRegistry.registerEffect(NORO_SLOWNESS, "Noro Slowness");
    WyRegistry.registerEffect(BLEEDING, "Bleeding");
    WyRegistry.registerEffect(HANA_HANDS, "Hana");
    WyRegistry.registerEffect(SILENT, "Silent");
  }
}


