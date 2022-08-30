/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.effects.AbilityOffEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.BindEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.BlackBoxEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.BleedingEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.CandleLockEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.CandyStuckEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.ChiyuHormoneEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.DizzyEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.DoorHeadEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.DrunkEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.FatigueEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.FrostBiteEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.FrozenEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.GanmenSeichoHormoneEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.GuardingEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.HakiOveruseEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.HanaEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.LookoutEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.LovestruckEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.MovementBlockedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.NegativeEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.NoHandsEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.NoroSlownessEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.ParalysisEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.ReducedFallEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.ShinzoMassageEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.SilentEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.SmokeEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.StickyEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.TensionHormoneEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.UnconsciousEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.WashedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModEffects
/*     */ {
/*  48 */   public static final Effect BUBBLY_CORAL = (Effect)new BubblyCoralEffect();
/*     */   
/*  50 */   public static final Effect ANIMAL_LOOKOUT = (Effect)new LookoutEffect();
/*     */   
/*  52 */   public static final Effect CANDLE_LOCK = (new CandleLockEffect())
/*  53 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "30ee2305-2aaa-4ed8-9c7b-de0ae33a2024", -1000.0D, AttributeModifier.Operation.ADDITION)
/*  54 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "0d80c985-ee10-49c9-bc0b-d9eb0cdb9666", 100.0D, AttributeModifier.Operation.ADDITION)
/*  55 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "b94c7ca4-ead0-4927-b4c1-51033159a9ff", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  57 */   public static final Effect LOVESTRUCK = (new LovestruckEffect())
/*  58 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "eb1809e8-a81d-4076-94aa-6f06e3a64920", -1000.0D, AttributeModifier.Operation.ADDITION)
/*  59 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "50b418ff-a637-47be-a13e-1a848dea1638", 100.0D, AttributeModifier.Operation.ADDITION)
/*  60 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "51958b7e-3eb7-439e-a49a-de7387037c2d", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  62 */   public static final Effect NEGATIVE = (new NegativeEffect())
/*  63 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "11147784-f615-47da-a28c-20c721cf5e9f", -0.25D, AttributeModifier.Operation.ADDITION)
/*  64 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -1.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/*  65 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -5.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   
/*  67 */   public static final Effect CHIYU_HORMONE = (new ChiyuHormoneEffect())
/*  68 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "5548045f-4680-4bc3-9278-5521ef1c2d00", 2.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  70 */   public static final Effect FROSTBITE = (new FrostBiteEffect())
/*  71 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "11147784-f615-47da-a28c-20c721cf5e9f", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/*  72 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "d23c3332-a0bf-4dde-80e1-1a6b936caf41", -0.800000011920929D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   
/*  74 */   public static final Effect FROZEN = (new FrozenEffect())
/*  75 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "4c6e221d-2191-4960-a642-d38fcbea9c4f", -1000.0D, AttributeModifier.Operation.ADDITION)
/*  76 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "0aca6c19-061a-4b3f-9ba8-262f5e6f3815", -256.0D, AttributeModifier.Operation.ADDITION)
/*  77 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "777f14bf-fe3f-4f19-9d2c-eb69c04d5209", -4.0D, AttributeModifier.Operation.ADDITION)
/*  78 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "c24353cb-2efd-491f-a3f3-55a853d707ef", 100.0D, AttributeModifier.Operation.ADDITION)
/*  79 */     .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "77fb7784-2d75-4e0a-8f39-f5c2dcd4fca8", -0.4D, AttributeModifier.Operation.ADDITION)
/*  80 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "cbbcac4a-a068-47e9-befa-b4324a0faeaf", -4.0D, AttributeModifier.Operation.ADDITION)
/*  81 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fc7efdce-785b-4a61-bde2-10b95f99d7a4", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  83 */   public static final Effect TENSION_HORMONE = (new TensionHormoneEffect())
/*  84 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "c24353cb-2efd-491f-a3f3-55a853d707ef", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/*  85 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "1abe0056-96d4-4cf8-962c-cbf7a54f28f3", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/*  86 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "dd83d35f-5190-4840-a582-f538300ce0af", 0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL)
/*  87 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 10.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  89 */   public static final Effect GANMEN_SEICHO_HORMONE = (new GanmenSeichoHormoneEffect())
/*  90 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "37360b52-ad8e-4c8a-a5a1-fd35a5e98d92", -0.4D, AttributeModifier.Operation.MULTIPLY_TOTAL);
/*     */   
/*  92 */   public static final Effect DRUNK = (new DrunkEffect())
/*  93 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "51b6c31a-cc9a-42d9-aa4c-d4fd98dcef2a", -0.005D, AttributeModifier.Operation.ADDITION);
/*     */   
/*  95 */   public static final Effect ABILITY_OFF = (Effect)new AbilityOffEffect();
/*     */   
/*  97 */   public static final Effect DOOR_HEAD = (new DoorHeadEffect())
/*  98 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "36f5c72e-9cb5-475a-a65d-60a4f8bc11e9", -1000.0D, AttributeModifier.Operation.ADDITION)
/*  99 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "da340356-7e1f-4bc1-bdd6-1b757536705d", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 101 */   public static final Effect DIZZY = (new DizzyEffect())
/* 102 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.025D, AttributeModifier.Operation.ADDITION)
/* 103 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "e6a25b36-894a-4051-961d-6de8869b2900", -2.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 105 */   public static final Effect MOVEMENT_BLOCKED = (new MovementBlockedEffect())
/* 106 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256.0D, AttributeModifier.Operation.ADDITION)
/* 107 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "323ffb58-0b57-434e-bdfc-354670e22d5f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 108 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256.0D, AttributeModifier.Operation.ADDITION)
/* 109 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 111 */   public static final Effect UNCONSCIOUS = (new UnconsciousEffect())
/* 112 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "042fdefa-052c-4d69-bb21-90b4bfbc094e", -256.0D, AttributeModifier.Operation.ADDITION)
/* 113 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "dac953cd-3c25-463a-a748-8c49b059fc67", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 115 */   public static final Effect WASHED = (new WashedEffect())
/* 116 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "63f9c447-3f2d-4ac9-bf17-ea84f5352d46", -5.0D, AttributeModifier.Operation.ADDITION)
/* 117 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "c6344fb0-aee5-46f3-89ef-1791256de5ad", -255.0D, AttributeModifier.Operation.ADDITION)
/* 118 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "887875d0-c7c2-4d6d-afc7-0381d60bda6e", -4.0D, AttributeModifier.Operation.ADDITION)
/* 119 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "957a1347-334f-4988-825b-73dd9de5b9b0", -6.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 121 */   public static final Effect STICKY = (new StickyEffect())
/* 122 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "9276cd71-345c-4420-ae9a-3c8f725908a3", 1.0D, AttributeModifier.Operation.ADDITION)
/* 123 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "b13ba7ec-7103-4160-a5ff-139534a44691", -0.5D, AttributeModifier.Operation.ADDITION)
/* 124 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fb997caf-43b6-4dc2-b5da-b504ab41545a", -255.0D, AttributeModifier.Operation.ADDITION)
/* 125 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "c2731993-56d2-4c67-a6fe-cdd144bd9ff8", -4.0D, AttributeModifier.Operation.ADDITION)
/* 126 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "5208c5bf-44d0-4111-9787-f63db22f180c", -6.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 128 */   public static final Effect BLACK_BOX = (new BlackBoxEffect())
/* 129 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "b1ddc653-67cc-4eb4-b6ee-8be108e70e2e", -1000.0D, AttributeModifier.Operation.ADDITION)
/* 130 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 100.0D, AttributeModifier.Operation.ADDITION)
/* 131 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "bacea9ea-a77a-4296-93db-90548eb2d30d", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 133 */   public static final Effect GUARDING = (new GuardingEffect(true))
/* 134 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "94822875-5c1c-4b25-ba22-44ee9d50717c", -1000.0D, AttributeModifier.Operation.ADDITION)
/* 135 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "b2144abf-f6cb-4994-9acd-721f949140cb", -256.0D, AttributeModifier.Operation.ADDITION)
/* 136 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 25.0D, AttributeModifier.Operation.ADDITION)
/* 137 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -256.0D, AttributeModifier.Operation.ADDITION)
/* 138 */     .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "7b3a9108-6a36-11eb-9439-0242ac130002", 0.025D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 140 */   public static final Effect GUARDING_WITH_MOVEMENT = (new GuardingEffect(true))
/* 141 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 0.5D, AttributeModifier.Operation.ADDITION)
/* 142 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "80abd0fe-3fec-42ac-8563-e39f82ab9c59", -1.0D, AttributeModifier.Operation.ADDITION)
/* 143 */     .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "7b3a9108-6a36-11eb-9439-0242ac130002", 0.02D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 145 */   public static final Effect PHYSICAL_MOVING_GUARD = (new GuardingEffect(true, true, false, new String[] { "mob", "player", "ability_projectile", "ability", "fall"
/* 146 */       })).addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 0.05D, AttributeModifier.Operation.ADDITION)
/* 147 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "3abb639e-a984-42d1-a4e1-674fcafcbfbc", 3.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 149 */   public static final Effect SHINZO_MASSAGE = (new ShinzoMassageEffect())
/* 150 */     .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "edfc5eb0-b647-4620-ac92-e81f71dbb9e1", 0.1D, AttributeModifier.Operation.ADDITION)
/* 151 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "dca469c0-75d1-4c23-a21c-4fb1abd76c08", 4.0D, AttributeModifier.Operation.ADDITION)
/* 152 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "1eb54f3f-ba0a-4247-aa12-0ba54ed9de5d", 0.2D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 154 */   public static final Effect CANDY_STUCK = (new CandyStuckEffect())
/* 155 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "f695cbb3-1223-4c64-97e2-c7979775fd4d", -100.0D, AttributeModifier.Operation.ADDITION)
/* 156 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "346e1665-7959-4a14-86ed-c8e2d1e5cd9a", -256.0D, AttributeModifier.Operation.ADDITION)
/* 157 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "23b16162-7090-4f27-87cc-613445852721", -4.0D, AttributeModifier.Operation.ADDITION)
/* 158 */     .addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "644893f5-3ae2-4b7c-bc61-32a7a7711286", 100.0D, AttributeModifier.Operation.ADDITION)
/* 159 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "7d355019-7ef9-4beb-bcba-8b2608a73380", -4.0D, AttributeModifier.Operation.ADDITION)
/* 160 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "fa4d711c-faa4-41cd-83c9-8f97edc5800e", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 162 */   public static final Effect PARALYSIS = (new ParalysisEffect())
/* 163 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -256.0D, AttributeModifier.Operation.ADDITION)
/* 164 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 165 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "f132c77e-9f47-42bb-8233-81c89b8ddcab", -4.0D, AttributeModifier.Operation.ADDITION)
/* 166 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "18240623-27cc-4648-b148-ea3a6782bda6", -256.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 168 */   public static final Effect HAKI_OVERUSE = (Effect)new HakiOveruseEffect();
/*     */   
/* 170 */   public static final Effect FATIGUE_EFFECT = (new FatigueEffect())
/* 171 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.01D, AttributeModifier.Operation.ADDITION)
/* 172 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -0.1D, AttributeModifier.Operation.ADDITION)
/* 173 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -1.0D, AttributeModifier.Operation.ADDITION)
/* 174 */     .addAttributesModifier(ModAttributes.DAMAGE_REDUCTION, "e6a25b36-894a-4051-961d-6de8869b2900", -0.05D, AttributeModifier.Operation.ADDITION)
/* 175 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "e6a25b36-894a-4051-961d-6de8869b2900", -0.1D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 177 */   public static final Effect SMOKE = (new SmokeEffect())
/* 178 */     .addAttributesModifier(ModAttributes.REGEN_RATE, "7d355019-7ef9-4beb-bcba-8b2608a73380", -0.25D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 180 */   public static final Effect REDUCED_FALL = (Effect)new ReducedFallEffect();
/*     */   
/* 182 */   public static final Effect HANDCUFFED = (new HandcuffedEffect(false))
/* 183 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 184 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
/* 185 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 187 */   public static final Effect HANDCUFFED_KAIROSEKI = (new HandcuffedEffect(true))
/* 188 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 189 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
/* 190 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 192 */   public static final Effect BIND = (new BindEffect())
/* 193 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -256.0D, AttributeModifier.Operation.ADDITION)
/* 194 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "b94c7ca4-ead0-4927-b4c1-51033159a9ff", -256.0D, AttributeModifier.Operation.ADDITION)
/* 195 */     .addAttributesModifier(LivingEntity.SWIM_SPEED, "36de9be6-6645-4124-b99d-325163e8c15f", -256.0D, AttributeModifier.Operation.ADDITION)
/* 196 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION)
/* 197 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "75bc5540-523d-43e6-b72a-f5fe9fb2f407", -10.0D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 199 */   public static final Effect NO_HANDS = (Effect)new NoHandsEffect();
/*     */   
/* 201 */   public static final Effect NORO_SLOWNESS = (new NoroSlownessEffect())
/* 202 */     .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "8ab1e3cd-9688-402b-8876-73f314e174d2", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_BASE)
/* 203 */     .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "bb904ec8-b548-4e1b-82ba-df237ad06f65", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_BASE)
/* 204 */     .addAttributesModifier(ModAttributes.JUMP_HEIGHT, "61346567-335f-4a4d-a4a6-327faec00aa3", -1.0D, AttributeModifier.Operation.ADDITION)
/* 205 */     .addAttributesModifier(ModAttributes.TIME_PROGRESSION, "15494972-058f-4202-8190-a8a6f64a248d", -0.10000000149011612D, AttributeModifier.Operation.ADDITION);
/*     */   
/* 207 */   public static final Effect BLEEDING = (Effect)new BleedingEffect();
/*     */   
/* 209 */   public static final Effect HANA_HANDS = (Effect)new HanaEffect();
/*     */   
/* 211 */   public static final Effect SILENT = (Effect)new SilentEffect();
/*     */ 
/*     */   
/*     */   static {
/* 215 */     WyRegistry.registerEffect(FROZEN, "Frozen");
/* 216 */     WyRegistry.registerEffect(CANDY_STUCK, "Candy Stuck");
/* 217 */     WyRegistry.registerEffect(BUBBLY_CORAL, "Bubbly Coral");
/* 218 */     WyRegistry.registerEffect(CANDLE_LOCK, "Candle Lock");
/* 219 */     WyRegistry.registerEffect(LOVESTRUCK, "Lovestruck");
/* 220 */     WyRegistry.registerEffect(FROSTBITE, "Frostbite");
/* 221 */     WyRegistry.registerEffect(NEGATIVE, "Negative");
/* 222 */     WyRegistry.registerEffect(CHIYU_HORMONE, "Chiyu Hormone");
/* 223 */     WyRegistry.registerEffect(TENSION_HORMONE, "Tension Hormone");
/* 224 */     WyRegistry.registerEffect(GANMEN_SEICHO_HORMONE, "Genmen Seicho Hormone");
/* 225 */     WyRegistry.registerEffect(DRUNK, "Drunk");
/* 226 */     WyRegistry.registerEffect(ABILITY_OFF, "Ability Off");
/* 227 */     WyRegistry.registerEffect(DOOR_HEAD, "Door Head");
/* 228 */     WyRegistry.registerEffect(MOVEMENT_BLOCKED, "Movement Blocked");
/* 229 */     WyRegistry.registerEffect(UNCONSCIOUS, "Unconscious");
/* 230 */     WyRegistry.registerEffect(BLACK_BOX, "Black Box");
/* 231 */     WyRegistry.registerEffect(GUARDING, "Guarding");
/* 232 */     WyRegistry.registerEffect(GUARDING_WITH_MOVEMENT, "Guarding With Movement");
/* 233 */     WyRegistry.registerEffect(PHYSICAL_MOVING_GUARD, "Physical Guarding");
/* 234 */     WyRegistry.registerEffect(SHINZO_MASSAGE, "Shinzo Massage");
/* 235 */     WyRegistry.registerEffect(WASHED, "Washed");
/* 236 */     WyRegistry.registerEffect(FATIGUE_EFFECT, "Fatigue");
/* 237 */     WyRegistry.registerEffect(STICKY, "Sticky");
/* 238 */     WyRegistry.registerEffect(ANIMAL_LOOKOUT, "Animal Lookout");
/* 239 */     WyRegistry.registerEffect(PARALYSIS, "Paralysis");
/* 240 */     WyRegistry.registerEffect(DIZZY, "Dizzy");
/* 241 */     WyRegistry.registerEffect(HAKI_OVERUSE, "Haki Overuse");
/* 242 */     WyRegistry.registerEffect(SMOKE, "Smoke");
/*     */     
/* 244 */     WyRegistry.registerEffect(REDUCED_FALL, "Reduced Fall");
/* 245 */     WyRegistry.registerEffect(HANDCUFFED, "Handcuffed");
/* 246 */     WyRegistry.registerEffect(HANDCUFFED_KAIROSEKI, "Handcuffed", "handcuffed_kairoseki");
/* 247 */     WyRegistry.registerEffect(BIND, "Bind");
/* 248 */     WyRegistry.registerEffect(NO_HANDS, "No Hands");
/* 249 */     WyRegistry.registerEffect(NORO_SLOWNESS, "Noro Slowness");
/* 250 */     WyRegistry.registerEffect(BLEEDING, "Bleeding");
/* 251 */     WyRegistry.registerEffect(HANA_HANDS, "Hana");
/* 252 */     WyRegistry.registerEffect(SILENT, "Silent");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */