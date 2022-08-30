/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.GlowingAbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MeraProjectiles {
/* 20 */   public static final EntityType HIKEN = WyRegistry.createEntityType(HikenProjectile::new).size(1.2F, 1.2F).build("hiken");
/* 21 */   public static final EntityType HIGAN = WyRegistry.createEntityType(HiganProjectile::new).size(0.5F, 0.5F).build("higan");
/* 22 */   public static final EntityType DAI_ENKAI_ENTEI = WyRegistry.createEntityType(DaiEnkaiEnteiProjectile::new).size(3.0F, 3.0F).build("dai_enkai_entei");
/* 23 */   public static final EntityType HIDARUMA = WyRegistry.createEntityType(HidarumaProjectile::new).size(0.5F, 0.5F).build("hidaruma");
/* 24 */   public static final EntityType JUJIKA = WyRegistry.createEntityType(JujikaProjectile::new).size(1.0F, 1.0F).build("jujika");
/*    */ 
/*    */   
/*    */   static {
/* 28 */     WyRegistry.registerEntityType(HIKEN, "Hiken");
/* 29 */     WyRegistry.registerEntityType(HIGAN, "Higan");
/* 30 */     WyRegistry.registerEntityType(DAI_ENKAI_ENTEI, "Dai Enkai: Entei");
/* 31 */     WyRegistry.registerEntityType(HIDARUMA, "Hidaruma");
/* 32 */     WyRegistry.registerEntityType(JUJIKA, "Jujika");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 39 */     RenderingRegistry.registerEntityRenderingHandler(HIKEN, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new FistModel())).setTexture("hiken").setScale(1.5D));
/* 40 */     RenderingRegistry.registerEntityRenderingHandler(HIGAN, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor(0.8999999761581421D, 0.4000000059604645D, 0.15000000596046448D, 1.0D).setScale(0.5D));
/* 41 */     RenderingRegistry.registerEntityRenderingHandler(DAI_ENKAI_ENTEI, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.8999999761581421D, 0.4000000059604645D, 0.15000000596046448D, 1.0D).setScale(15.0D));
/* 42 */     RenderingRegistry.registerEntityRenderingHandler(HIDARUMA, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.0D, 1.0D, 0.0D, 1.0D).setScale(1.2D));
/* 43 */     RenderingRegistry.registerEntityRenderingHandler(JUJIKA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.8999999761581421D, 0.4000000059604645D, 0.15000000596046448D, 1.0D).setScale(1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\MeraProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */