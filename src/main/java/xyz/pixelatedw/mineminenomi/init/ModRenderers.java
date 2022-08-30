/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.RenderTypeLookup;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.IDyeableArmorItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.api.IFruitColor;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ExtraProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.DamnedPunkModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.SniperTargetModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DenDenMushiModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.KungFuDugongModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DojoSenseiModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DonKriegModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.GinModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.MorganModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SkypieanModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.WeatherWizardModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.AllosaurusWalkModel;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.BrachiosaurusGuardModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.BrachiosaurusHeavyModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.KameGuardModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.LeopardHeavyModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.LeopardWalkModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.MammothGuardModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.MammothHeavyModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.PteranodonFlyModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.SaiHeavyModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.SaiWalkModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.ShinokuniModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.PunkCornaDioModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.BottomHalfBodyRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.PhysicalBodyRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BlackKnightRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BombRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.CloudRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.DoppelmanRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.DummyRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.FightingFishRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.HumandrillRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.HumanoidRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.LapahnRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.MirageCloneRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.SeaKingRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.SpikeRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.VivreCardRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.WantedPosterPackageRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.WaxCloneRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.YagaraBullRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BindLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.HandcuffsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaCalendulaLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaWingsLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.CaptainCapeOverlayLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.LowerHalfArmorLayer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.armor.UpperHalfArmorLayer;
/*     */ 
/*     */ public class ModRenderers {
/*     */   public static void registerRenderers() {
/*  81 */     Minecraft.getInstance().getItemColors().register((itemStack, layer) -> (layer > 0) ? -1 : ((IDyeableArmorItem)itemStack.getItem()).getColor(itemStack), new IItemProvider[] { (IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE, (IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE, (IItemProvider)ModItems.STRAW_DOLL, (IItemProvider)ModArmors.BANDANA, (IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN, (IItemProvider)ModWeapons.DORU_PICKAXE, (IItemProvider)ModArmors.VICE_ADMIRAL_CHEST, (IItemProvider)ModArmors.VICE_ADMIRAL_FEET, (IItemProvider)ModArmors.VICE_ADMIRAL_LEGS });
/*     */ 
/*     */ 
/*     */     
Iterator<AkumaNoMiItem> fruits = ModValues.devilfruits.iterator();
/*  85 */     while ( fruits.hasNext() ) { 
    AkumaNoMiItem fruit = fruits.next();
/*     */       
/*  87 */       Minecraft.getInstance().getItemColors().register((itemStack, layer) -> (layer > 0) ? ((IFruitColor)itemStack.getItem()).getStemColor(itemStack) : ((IFruitColor)itemStack.getItem()).getBaseColor(itemStack), new IItemProvider[] { (IItemProvider)fruit }); 
}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     RenderTypeLookup.setRenderLayer(ModBlocks.OPE, RenderType.getTranslucent());
/*  94 */     RenderTypeLookup.setRenderLayer(ModBlocks.BARRIER, RenderType.getTranslucent());
/*  95 */     RenderTypeLookup.setRenderLayer(ModBlocks.STRING_WALL, RenderType.getTranslucent());
/*  96 */     RenderTypeLookup.setRenderLayer(ModBlocks.KAIROSEKI_BARS, RenderType.getTranslucent());
/*  97 */     RenderTypeLookup.setRenderLayer(ModBlocks.ABILITY_PROTECTION, RenderType.getTranslucent());
/*  98 */     RenderTypeLookup.setRenderLayer(ModBlocks.CANDY, RenderType.getTranslucent());
/*  99 */     RenderTypeLookup.setRenderLayer(ModBlocks.CHALLENGE_ARENA, RenderType.getTranslucent());
/* 100 */     RenderTypeLookup.setRenderLayer(ModBlocks.CUSTOM_SPAWNER, RenderType.getTranslucent());
/* 101 */     RenderTypeLookup.setRenderLayer(ModBlocks.ORI_BARS, RenderType.getTranslucent());
/*     */ 
/*     */     
/* 104 */     ClientRegistry.bindTileEntityRenderer(ModTileEntities.WANTED_POSTER_PACKAGE, xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPostersPackageTileEntityRenderer::new);
/* 105 */     ClientRegistry.bindTileEntityRenderer(ModTileEntities.WANTED_POSTER, xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPosterTileEntityRenderer::new);
/*     */ 
/*     */ 
/*     */     
/* 109 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 110 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_WITH_GUN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 111 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_SNIPER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 112 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_BOMBER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
/* 113 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_CAPTAIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 114 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 115 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MORGAN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new MorganModel(), 1.0F, "morgan"));
/*     */ 
/*     */     
/* 118 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 119 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_WITH_GUN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 120 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_BRUTE, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
/* 121 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_CAPTAIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 122 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/*     */ 
/*     */     
/* 125 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.GIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new GinModel(), 1.0F, "gin"));
/* 126 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DON_KRIEG, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DonKriegModel(), 1.4F, "don_krieg"));
/*     */ 
/*     */     
/* 129 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 130 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_SNIPER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 131 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_BRUTE, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
/* 132 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_BOMBER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
/*     */ 
/*     */     
/* 135 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SWORDSMAN_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DojoSenseiModel(), 1.0F));
/* 136 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SNIPER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 137 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.ART_OF_WEATHER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new WeatherWizardModel(), 1.0F));
/* 138 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DOCTOR_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 139 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BRAWLER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 140 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BLACK_LEG_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 141 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYPIEAN_CIVILIAN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));
/* 142 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYPIEAN_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));
/*     */ 
/*     */     
/* 145 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEN_DEN_MUSHI, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DenDenMushiModel(), 1.0F));
/* 146 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPAHN, (IRenderFactory)new LapahnRenderer.Factory());
/* 147 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.KUNG_FU_DUGONG, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new KungFuDugongModel(), 1.0F, "kung_fu_dugong"));
/* 148 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.YAGARA_BULL, (IRenderFactory)new YagaraBullRenderer.Factory());
/* 149 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SEA_KING, (IRenderFactory)new SeaKingRenderer.Factory());
/* 150 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANDRILL, (IRenderFactory)new HumandrillRenderer.Factory());
/* 151 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIGHTING_FISH, (IRenderFactory)new FightingFishRenderer.Factory());
/*     */ 
/*     */     
/* 154 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DOPPELMAN, (IRenderFactory)new DoppelmanRenderer.Factory());
/* 155 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.WAX_CLONE, (IRenderFactory)new WaxCloneRenderer.Factory());
/* 156 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BLACK_KNIGHT, (IRenderFactory)new BlackKnightRenderer.Factory());
/* 157 */     RenderingRegistry.registerEntityRenderingHandler(ExtraProjectiles.CLOUD, (IRenderFactory)new CloudRenderer.Factory());
/* 158 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.WANTED_POSTER_PACKAGE, (IRenderFactory)new WantedPosterPackageRenderer.Factory());
/* 159 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.VIVRE_CARD, (IRenderFactory)new VivreCardRenderer.Factory());
/* 160 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SNIPER_TARGET, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SniperTargetModel(), 1.0F, "sniper_target"));
/* 161 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.PHYSICAL_BODY, (IRenderFactory)new PhysicalBodyRenderer.Factory());
/* 162 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.MIRAGE_CLONE, (IRenderFactory)new MirageCloneRenderer.Factory());
/* 163 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOMB, (IRenderFactory)new BombRenderer.Factory());
/* 164 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPIKE, (IRenderFactory)new SpikeRenderer.Factory());
/* 165 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEVIL_FRUIT_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
/* 166 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOTTOM_HALF_BODY, (IRenderFactory)new BottomHalfBodyRenderer.Factory());
/*     */ 
/*     */     
/* 169 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_MAMMOTH_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new MammothGuardModel(), "zou_zou_mammoth_guard"));
/* 170 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_MAMMOTH_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new MammothHeavyModel(), "zou_zou_mammoth_heavy"));
/*     */     
/* 172 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_KAME_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new KameGuardModel(), "kame_kame_guard"));
/*     */     
/* 174 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_BRACHIO_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new BrachiosaurusGuardModel(), "ryu_ryu_brachiosaurus_guard"));
/* 175 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_BRACHIO_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new BrachiosaurusHeavyModel(), "ryu_ryu_brachiosaurus_heavy"));
/*     */     
/* 177 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SAI_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new SaiHeavyModel(), "sai_sai_heavy"));
/* 178 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SAI_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new SaiWalkModel(), "sai_sai_walk"));
/*     */     
/* 180 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_PTERANODON_FLY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new PteranodonFlyModel(), "ryu_ryu_pteranodon_fly"));
/*     */     
/* 182 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_ALLOSAURUS_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new AllosaurusWalkModel(), "ryu_ryu_allosaurus_walk"));
/*     */     
/* 184 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_LEOPARD_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new LeopardWalkModel(), "neko_neko_leopard_walk"));
/* 185 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_LEOPARD_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new LeopardHeavyModel(), "neko_neko_leopard_heavy"));
/*     */     
/* 187 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SHINOKUNI, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new ShinokuniModel(), "shinokuni"));
/* 188 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_CORNA_DIO, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new PunkCornaDioModel(), "jiki_jiki_corna_dio_bull", 2.0F));
/* 189 */     RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_DAMNED_PUNK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new DamnedPunkModel(), "damned_punk", 2.0F));
/*     */ 
/*     */ 
/*     */     
/* 193 */     for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : (Iterable<Map.Entry<EntityType<?>, EntityRenderer<?>>>)(Minecraft.getInstance().getRenderManager()).renderers.entrySet()) {
/*     */       
/* 195 */       EntityRenderer entityRenderer = entry.getValue();
/* 196 */       if (entityRenderer instanceof LivingRenderer) {
/*     */         
/* 198 */         LivingRenderer renderer = (LivingRenderer)entityRenderer;
/* 199 */         renderer.addLayer((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
/* 200 */         renderer.addLayer((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
/* 201 */         renderer.addLayer((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
/* 202 */         renderer.addLayer((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
/* 203 */         renderer.addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
/* 204 */         renderer.addLayer((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
/* 205 */         renderer.addLayer((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
/* 206 */         renderer.addLayer((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
/* 207 */         renderer.addLayer((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
/* 208 */         renderer.addLayer((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     for (Map.Entry<String, PlayerRenderer> entry : (Iterable<Map.Entry<String, PlayerRenderer>>)Minecraft.getInstance().getRenderManager().getSkinMap().entrySet()) {
/*     */       
/* 214 */       PlayerRenderer renderer = entry.getValue();
/* 215 */       renderer.addLayer((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
/* 216 */       renderer.addLayer((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
/* 217 */       renderer.addLayer((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
/* 218 */       renderer.addLayer((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
/* 219 */       renderer.addLayer((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)renderer));
/* 220 */       renderer.addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
/* 221 */       renderer.addLayer((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
/* 222 */       renderer.addLayer((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
/* 223 */       renderer.addLayer((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
/* 224 */       renderer.addLayer((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
/* 225 */       renderer.addLayer((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModRenderers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */