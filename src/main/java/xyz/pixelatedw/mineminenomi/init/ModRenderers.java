package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.mineminenomi.api.IFruitColor;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ExtraProjectiles;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.models.abilities.DamnedPunkModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.SniperTargetModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DenDenMushiModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.KungFuDugongModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.*;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.*;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.PunkCornaDioModel;
import xyz.pixelatedw.mineminenomi.renderers.abilities.BottomHalfBodyRenderer;
import xyz.pixelatedw.mineminenomi.renderers.abilities.PhysicalBodyRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.*;
import xyz.pixelatedw.mineminenomi.renderers.layers.*;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaCalendulaLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaHandsLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.HanaWingsLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.armor.CaptainCapeOverlayLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.armor.LowerHalfArmorLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.armor.UpperHalfArmorLayer;

import java.util.Iterator;
import java.util.Map;

public class ModRenderers {
  public static void registerRenderers() {
    Minecraft.getInstance().getItemColors().register((itemStack, layer) -> (layer > 0) ? -1 : ((IDyeableArmorItem)itemStack.getItem()).getColor(itemStack), new IItemProvider[] { (IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE, (IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE, (IItemProvider)ModItems.STRAW_DOLL, (IItemProvider)ModArmors.BANDANA, (IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN, (IItemProvider)ModWeapons.DORU_PICKAXE, (IItemProvider)ModArmors.VICE_ADMIRAL_CHEST, (IItemProvider)ModArmors.VICE_ADMIRAL_FEET, (IItemProvider)ModArmors.VICE_ADMIRAL_LEGS });


    
Iterator<AkumaNoMiItem> fruits = ModValues.devilfruits.iterator();
    while ( fruits.hasNext() ) { 
    AkumaNoMiItem fruit = fruits.next();
      
      Minecraft.getInstance().getItemColors().register((itemStack, layer) -> (layer > 0) ? ((IFruitColor)itemStack.getItem()).getStemColor(itemStack) : ((IFruitColor)itemStack.getItem()).getBaseColor(itemStack), new IItemProvider[] { (IItemProvider)fruit }); 
}




    
    RenderTypeLookup.setRenderLayer(ModBlocks.OPE, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.BARRIER, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.STRING_WALL, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.KAIROSEKI_BARS, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.ABILITY_PROTECTION, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.CANDY, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.CHALLENGE_ARENA, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.CUSTOM_SPAWNER, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(ModBlocks.ORI_BARS, RenderType.getTranslucent());

    
    ClientRegistry.bindTileEntityRenderer(ModTileEntities.WANTED_POSTER_PACKAGE, xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPostersPackageTileEntityRenderer::new);
    ClientRegistry.bindTileEntityRenderer(ModTileEntities.WANTED_POSTER, xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPosterTileEntityRenderer::new);


    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_WITH_GUN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_SNIPER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_BOMBER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_CAPTAIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MARINE_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MORGAN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new MorganModel(), 1.0F, "morgan"));

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_WITH_GUN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_BRUTE, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_CAPTAIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PIRATE_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.GIN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new GinModel(), 1.0F, "gin"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DON_KRIEG, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DonKriegModel(), 1.4F, "don_krieg"));

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_WITH_SWORD, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_SNIPER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_BRUTE, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDIT_BOMBER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F));

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SWORDSMAN_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DojoSenseiModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SNIPER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.ART_OF_WEATHER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new WeatherWizardModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DOCTOR_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BRAWLER_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BLACK_LEG_TRAINER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYPIEAN_CIVILIAN, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SKYPIEAN_TRADER, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEN_DEN_MUSHI, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DenDenMushiModel(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAPAHN, (IRenderFactory)new LapahnRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.KUNG_FU_DUGONG, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new KungFuDugongModel(), 1.0F, "kung_fu_dugong"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.YAGARA_BULL, (IRenderFactory)new YagaraBullRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SEA_KING, (IRenderFactory)new SeaKingRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.HUMANDRILL, (IRenderFactory)new HumandrillRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIGHTING_FISH, (IRenderFactory)new FightingFishRenderer.Factory());

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DOPPELMAN, (IRenderFactory)new DoppelmanRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.WAX_CLONE, (IRenderFactory)new WaxCloneRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BLACK_KNIGHT, (IRenderFactory)new BlackKnightRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ExtraProjectiles.CLOUD, (IRenderFactory)new CloudRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.WANTED_POSTER_PACKAGE, (IRenderFactory)new WantedPosterPackageRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.VIVRE_CARD, (IRenderFactory)new VivreCardRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SNIPER_TARGET, (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SniperTargetModel(), 1.0F, "sniper_target"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.PHYSICAL_BODY, (IRenderFactory)new PhysicalBodyRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.MIRAGE_CLONE, (IRenderFactory)new MirageCloneRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOMB, (IRenderFactory)new BombRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPIKE, (IRenderFactory)new SpikeRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEVIL_FRUIT_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOTTOM_HALF_BODY, (IRenderFactory)new BottomHalfBodyRenderer.Factory());

    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_MAMMOTH_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new MammothGuardModel(), "zou_zou_mammoth_guard"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_MAMMOTH_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new MammothHeavyModel(), "zou_zou_mammoth_heavy"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_KAME_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new KameGuardModel(), "kame_kame_guard"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_BRACHIO_GUARD, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new BrachiosaurusGuardModel(), "ryu_ryu_brachiosaurus_guard"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_BRACHIO_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new BrachiosaurusHeavyModel(), "ryu_ryu_brachiosaurus_heavy"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SAI_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new SaiHeavyModel(), "sai_sai_heavy"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SAI_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new SaiWalkModel(), "sai_sai_walk"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_PTERANODON_FLY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new PteranodonFlyModel(), "ryu_ryu_pteranodon_fly"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_ALLOSAURUS_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new AllosaurusWalkModel(), "ryu_ryu_allosaurus_walk"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_LEOPARD_WALK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new LeopardWalkModel(), "neko_neko_leopard_walk"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_LEOPARD_HEAVY, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new LeopardHeavyModel(), "neko_neko_leopard_heavy"));
    
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_SHINOKUNI, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new ShinokuniModel(), "shinokuni"));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_CORNA_DIO, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new PunkCornaDioModel(), "jiki_jiki_corna_dio_bull", 2.0F));
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUMMY_DAMNED_PUNK, (IRenderFactory)new DummyRenderer.Factory((EntityModel)new DamnedPunkModel(), "damned_punk", 2.0F));


    
    for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : (Iterable<Map.Entry<EntityType<?>, EntityRenderer<?>>>)(Minecraft.getInstance().getRenderManager()).renderers.entrySet()) {
      
      EntityRenderer entityRenderer = entry.getValue();
      if (entityRenderer instanceof LivingRenderer) {
        
        LivingRenderer renderer = (LivingRenderer)entityRenderer;
        renderer.addLayer((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
        renderer.addLayer((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
      } 
    } 
    
    for (Map.Entry<String, PlayerRenderer> entry : (Iterable<Map.Entry<String, PlayerRenderer>>)Minecraft.getInstance().getRenderManager().getSkinMap().entrySet()) {
      
      PlayerRenderer renderer = entry.getValue();
      renderer.addLayer((LayerRenderer)new PotionLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new UpperHalfArmorLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new LowerHalfArmorLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new CaptainCapeOverlayLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new BodyCoatingLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new HandcuffsLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new BindLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new HanaWingsLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new HanaCalendulaLayer((IEntityRenderer)renderer));
      renderer.addLayer((LayerRenderer)new HanaHandsLayer((IEntityRenderer)renderer));
    } 
  }
}


