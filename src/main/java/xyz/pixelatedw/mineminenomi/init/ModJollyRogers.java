/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyPatreon;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModJollyRogers
/*     */ {
/*  19 */   public static final DeferredRegister<JollyRogerElement> JOLLY_ROGER_ELEMENTS = DeferredRegister.create(ModRegistries.JOLLY_ROGER_ELEMENTS, "mineminenomi");
/*     */ 
/*     */   
/*     */   public static JollyRogerElement registerElement(JollyRogerElement element) {
/*  23 */     String resourceName = WyHelper.getResourceName(element.getTexture().toString().replace("mineminenomi", ""));
/*     */     
/*  25 */     JOLLY_ROGER_ELEMENTS.register(resourceName, () -> element);
/*     */     
/*  27 */     return element;
/*     */   }
/*     */   
/*  30 */   public static final JollyRogerElement NULL = null;
/*     */ 
/*     */   
/*  33 */   public static final JollyRogerElement BASE_0 = new JollyRogerElement(JollyRogerElement.LayerType.BASE, "base_0");
/*  34 */   public static final JollyRogerElement BASE_1 = (new JollyRogerElement(JollyRogerElement.LayerType.BASE, "base_1")).setCanBeColored();
/*  35 */   public static final JollyRogerElement BASE_2 = (new JollyRogerElement(JollyRogerElement.LayerType.BASE, "base_2")).setCanBeColored();
/*  36 */   public static final JollyRogerElement BASE_3 = new JollyRogerElement(JollyRogerElement.LayerType.BASE, "base_3"); public static final JollyRogerElement DETAIL_0; public static final JollyRogerElement DETAIL_1; public static final JollyRogerElement DETAIL_2; public static final JollyRogerElement DETAIL_3; public static final JollyRogerElement DETAIL_4; public static final JollyRogerElement DETAIL_5; public static final JollyRogerElement DETAIL_6; public static final JollyRogerElement DETAIL_7; public static final JollyRogerElement DETAIL_8; public static final JollyRogerElement DETAIL_9; public static final JollyRogerElement DETAIL_10; public static final JollyRogerElement DETAIL_11; public static final JollyRogerElement DETAIL_12; public static final JollyRogerElement DETAIL_13; public static final JollyRogerElement DETAIL_14; public static final JollyRogerElement DETAIL_15; public static final JollyRogerElement DETAIL_16; public static final JollyRogerElement DETAIL_17;
/*     */   
/*     */   static {
/*  39 */     DETAIL_0 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_0")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  40 */     DETAIL_1 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_1")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  41 */     DETAIL_2 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_2")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  42 */     DETAIL_3 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_3")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  43 */     DETAIL_4 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_4")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  44 */     DETAIL_5 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_5")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/*  45 */     DETAIL_6 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_6")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/*  46 */     DETAIL_7 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_7")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 })).setCanBeColored();
/*  47 */     DETAIL_8 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_8")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  48 */     DETAIL_9 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_9")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  49 */     DETAIL_10 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_10")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  50 */     DETAIL_11 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_11")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  51 */     DETAIL_12 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_12")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 })).setCanBeColored();
/*  52 */     DETAIL_13 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_13")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  53 */     DETAIL_14 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_14")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  54 */     DETAIL_15 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_15")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  55 */     DETAIL_16 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_16")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  56 */     DETAIL_17 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_17")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/*  57 */   } public static final JollyRogerElement DETAIL_18 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_18")).addUseCheck(WyPatreon::isSupernova).setCanBeColored(); public static final JollyRogerElement DETAIL_19; public static final JollyRogerElement DETAIL_20; public static final JollyRogerElement DETAIL_21; public static final JollyRogerElement DETAIL_22; public static final JollyRogerElement DETAIL_23; public static final JollyRogerElement DETAIL_24; public static final JollyRogerElement DETAIL_25; public static final JollyRogerElement DETAIL_26; public static final JollyRogerElement DETAIL_27; static {
/*  58 */     DETAIL_19 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_19")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  59 */     DETAIL_20 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_20")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  60 */     DETAIL_21 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_21")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  61 */     DETAIL_22 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_22")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  62 */     DETAIL_23 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_23")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  63 */     DETAIL_24 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_24")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  64 */     DETAIL_25 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_25")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_1 })).setCanBeColored();
/*  65 */     DETAIL_26 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_26")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_1, BASE_3 })).setCanBeColored();
/*  66 */     DETAIL_27 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_27")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_1 })).setCanBeColored();
/*  67 */     DETAIL_28 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_28")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  68 */     DETAIL_29 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_29")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  69 */     DETAIL_30 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_30")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  70 */     DETAIL_31 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_31")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  71 */     DETAIL_32 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_32")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  72 */     DETAIL_33 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_33")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  73 */     DETAIL_34 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_34")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  74 */     DETAIL_35 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_35")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  75 */     DETAIL_36 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_36")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  76 */     DETAIL_37 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_37")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  77 */   } public static final JollyRogerElement DETAIL_28; public static final JollyRogerElement DETAIL_29; public static final JollyRogerElement DETAIL_30; public static final JollyRogerElement DETAIL_31; public static final JollyRogerElement DETAIL_32; public static final JollyRogerElement DETAIL_33; public static final JollyRogerElement DETAIL_34; public static final JollyRogerElement DETAIL_35; public static final JollyRogerElement DETAIL_36; public static final JollyRogerElement DETAIL_37; public static final JollyRogerElement DETAIL_38 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_38")).setCanBeColored(); public static final JollyRogerElement DETAIL_39; public static final JollyRogerElement DETAIL_40; public static final JollyRogerElement DETAIL_41; public static final JollyRogerElement DETAIL_42; public static final JollyRogerElement DETAIL_43; public static final JollyRogerElement DETAIL_44; public static final JollyRogerElement DETAIL_45; public static final JollyRogerElement DETAIL_46; public static final JollyRogerElement DETAIL_47; public static final JollyRogerElement DETAIL_48; public static final JollyRogerElement DETAIL_49; public static final JollyRogerElement DETAIL_50; public static final JollyRogerElement DETAIL_51; public static final JollyRogerElement DETAIL_52; public static final JollyRogerElement DETAIL_53; public static final JollyRogerElement DETAIL_54; public static final JollyRogerElement DETAIL_55; static {
/*  78 */     DETAIL_39 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_39")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  79 */     DETAIL_40 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_40")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  80 */     DETAIL_41 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_41")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  81 */     DETAIL_42 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_42")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/*  82 */     DETAIL_43 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_43")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  83 */     DETAIL_44 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_44")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  84 */     DETAIL_45 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_45")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 })).setCanBeColored();
/*  85 */     DETAIL_46 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_46")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/*  86 */     DETAIL_47 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_47")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  87 */     DETAIL_48 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_48")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  88 */     DETAIL_49 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_49")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  89 */     DETAIL_50 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_50")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/*  90 */     DETAIL_51 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_51")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  91 */     DETAIL_52 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_52")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/*  92 */     DETAIL_53 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_53")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1 })).setCanBeColored();
/*  93 */     DETAIL_54 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_54")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  94 */     DETAIL_55 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_55")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 })).setCanBeColored();
/*  95 */   } public static final JollyRogerElement DETAIL_56 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_56")).setCanBeColored(); public static final JollyRogerElement DETAIL_57; public static final JollyRogerElement DETAIL_58; public static final JollyRogerElement DETAIL_59; public static final JollyRogerElement DETAIL_60; public static final JollyRogerElement DETAIL_61; public static final JollyRogerElement DETAIL_62; public static final JollyRogerElement DETAIL_63; static {
/*  96 */     DETAIL_57 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_57")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  97 */     DETAIL_58 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_58")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/*  98 */     DETAIL_59 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_59")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/*  99 */     DETAIL_60 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_60")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/* 100 */     DETAIL_61 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_61")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/* 101 */     DETAIL_62 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_62")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/* 102 */     DETAIL_63 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_63")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/* 103 */   } public static final JollyRogerElement DETAIL_64 = new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_64");
/* 104 */   public static final JollyRogerElement DETAIL_65 = new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_65"); public static final JollyRogerElement DETAIL_66; public static final JollyRogerElement DETAIL_67; public static final JollyRogerElement DETAIL_68; public static final JollyRogerElement DETAIL_69; public static final JollyRogerElement DETAIL_70; public static final JollyRogerElement DETAIL_71; public static final JollyRogerElement DETAIL_72; public static final JollyRogerElement DETAIL_73; public static final JollyRogerElement DETAIL_74; public static final JollyRogerElement DETAIL_75; public static final JollyRogerElement DETAIL_76; public static final JollyRogerElement DETAIL_77; static {
/* 105 */     DETAIL_66 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_66")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/* 106 */     DETAIL_67 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_67")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/* 107 */     DETAIL_68 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_68")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 108 */     DETAIL_69 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_69")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 109 */     DETAIL_70 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_70")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 })).setCanBeColored();
/* 110 */     DETAIL_71 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_71")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 111 */     DETAIL_72 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_72")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 112 */     DETAIL_73 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_73")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 }));
/* 113 */     DETAIL_74 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_74")).addUseCheck(WyPatreon::isSupernova).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 }));
/* 114 */     DETAIL_75 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_75")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 115 */     DETAIL_76 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_76")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_3 })).setCanBeColored();
/* 116 */     DETAIL_77 = (new JollyRogerElement(JollyRogerElement.LayerType.DETAIL, "detail_77")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0, BASE_1, BASE_3 })).setCanBeColored();
/*     */   }
/*     */   
/* 119 */   public static final JollyRogerElement BACKGROUND_0 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_0");
/* 120 */   public static final JollyRogerElement BACKGROUND_1 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_1");
/* 121 */   public static final JollyRogerElement BACKGROUND_2 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_2");
/* 122 */   public static final JollyRogerElement BACKGROUND_3 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_3");
/* 123 */   public static final JollyRogerElement BACKGROUND_4 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_4")).setCanBeColored();
/* 124 */   public static final JollyRogerElement BACKGROUND_5 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_5")).setCanBeColored();
/* 125 */   public static final JollyRogerElement BACKGROUND_6 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_6")).setCanBeColored();
/* 126 */   public static final JollyRogerElement BACKGROUND_7 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_7")).setCanBeColored();
/* 127 */   public static final JollyRogerElement BACKGROUND_8 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_8")).setCanBeColored();
/* 128 */   public static final JollyRogerElement BACKGROUND_9 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_9");
/* 129 */   public static final JollyRogerElement BACKGROUND_10 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_10");
/* 130 */   public static final JollyRogerElement BACKGROUND_11 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_11")).setCanBeColored();
/* 131 */   public static final JollyRogerElement BACKGROUND_12 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_12");
/* 132 */   public static final JollyRogerElement BACKGROUND_13 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_13")).setCanBeColored(); public static final JollyRogerElement BACKGROUND_14; static {
/* 133 */     BACKGROUND_14 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_14")).addUseCheck(player -> onlyWith(player, new JollyRogerElement[] { BASE_0 }));
/* 134 */   } public static final JollyRogerElement BACKGROUND_15 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_15")).setCanBeColored();
/* 135 */   public static final JollyRogerElement BACKGROUND_16 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_16")).setCanBeColored();
/* 136 */   public static final JollyRogerElement BACKGROUND_17 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_17");
/* 137 */   public static final JollyRogerElement BACKGROUND_18 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_18")).setCanBeColored();
/* 138 */   public static final JollyRogerElement BACKGROUND_19 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_19")).setCanBeColored();
/* 139 */   public static final JollyRogerElement BACKGROUND_20 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_20")).setCanBeColored();
/* 140 */   public static final JollyRogerElement BACKGROUND_21 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_21")).setCanBeColored();
/* 141 */   public static final JollyRogerElement BACKGROUND_22 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_22")).setCanBeColored();
/* 142 */   public static final JollyRogerElement BACKGROUND_23 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_23")).setCanBeColored();
/* 143 */   public static final JollyRogerElement BACKGROUND_24 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_24");
/* 144 */   public static final JollyRogerElement BACKGROUND_25 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_25");
/* 145 */   public static final JollyRogerElement BACKGROUND_26 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_26")).addUseCheck(WyPatreon::isSupernova).setCanBeColored();
/* 146 */   public static final JollyRogerElement BACKGROUND_27 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_27")).setCanBeColored();
/* 147 */   public static final JollyRogerElement BACKGROUND_28 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_28")).setCanBeColored();
/* 148 */   public static final JollyRogerElement BACKGROUND_29 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_29")).addUseCheck(WyPatreon::isSupernova).setCanBeColored();
/* 149 */   public static final JollyRogerElement BACKGROUND_30 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_30")).setCanBeColored();
/* 150 */   public static final JollyRogerElement BACKGROUND_31 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_31")).setCanBeColored();
/* 151 */   public static final JollyRogerElement BACKGROUND_32 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_32")).setCanBeColored();
/* 152 */   public static final JollyRogerElement BACKGROUND_33 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_33");
/* 153 */   public static final JollyRogerElement BACKGROUND_34 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_34")).setCanBeColored();
/* 154 */   public static final JollyRogerElement BACKGROUND_35 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_35")).setCanBeColored();
/* 155 */   public static final JollyRogerElement BACKGROUND_36 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_36")).setCanBeColored();
/* 156 */   public static final JollyRogerElement BACKGROUND_37 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_37")).setCanBeColored();
/* 157 */   public static final JollyRogerElement BACKGROUND_38 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_38")).setCanBeColored();
/* 158 */   public static final JollyRogerElement BACKGROUND_39 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_39");
/* 159 */   public static final JollyRogerElement BACKGROUND_40 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_40")).setCanBeColored();
/* 160 */   public static final JollyRogerElement BACKGROUND_41 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_41")).setCanBeColored();
/* 161 */   public static final JollyRogerElement BACKGROUND_42 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_42")).setCanBeColored();
/* 162 */   public static final JollyRogerElement BACKGROUND_43 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_43")).addUseCheck(WyPatreon::isSupernova);
/* 163 */   public static final JollyRogerElement BACKGROUND_44 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_44")).setCanBeColored();
/* 164 */   public static final JollyRogerElement BACKGROUND_45 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_45")).setCanBeColored();
/* 165 */   public static final JollyRogerElement BACKGROUND_46 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_46")).setCanBeColored();
/* 166 */   public static final JollyRogerElement BACKGROUND_47 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_47");
/* 167 */   public static final JollyRogerElement BACKGROUND_48 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_48");
/* 168 */   public static final JollyRogerElement BACKGROUND_49 = new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_49");
/* 169 */   public static final JollyRogerElement BACKGROUND_50 = (new JollyRogerElement(JollyRogerElement.LayerType.BACKGROUND, "bg_50")).setCanBeColored();
/*     */ 
/*     */   
/*     */   public static boolean onlyWith(PlayerEntity player, JollyRogerElement... elements) {
/* 173 */     ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/* 174 */     Crew crew = worldProps.getCrewWithMember(player.getUniqueID());
/* 175 */     JollyRoger jollyRoger = crew.getJollyRoger();
/*     */     
/* 177 */     for (JollyRogerElement element : elements) {
/*     */       
/* 179 */       if (jollyRoger.getBase() == null && element == null) {
/* 180 */         return true;
/*     */       }
/* 182 */       if (jollyRoger.getBase() != null && jollyRoger.getBase().equals(element)) {
/* 183 */         return true;
/*     */       }
/*     */     } 
/* 186 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModJollyRogers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */