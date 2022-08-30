/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ArtOfWeatherProjectiles {
/* 17 */   public static final EntityType HEAT_BALL = WyRegistry.createEntityType(HeatBallProjectile::new).size(0.5F, 0.5F).build("heat_ball");
/* 18 */   public static final EntityType COOL_BALL = WyRegistry.createEntityType(CoolBallProjectile::new).size(0.5F, 0.5F).build("cool_ball");
/* 19 */   public static final EntityType THUNDER_BALL = WyRegistry.createEntityType(ThunderBallProjectile::new).size(0.5F, 0.5F).build("thunder_ball");
/* 20 */   public static final EntityType GUST_SWORD = WyRegistry.createEntityType(GustSwordProjectile::new).size(0.5F, 0.5F).build("gust_sword");
/* 21 */   public static final EntityType WEATHER_EGG = WyRegistry.createEntityType(WeatherEggProjectile::new).size(0.5F, 0.5F).build("weather_egg");
/*    */ 
/*    */   
/*    */   static {
/* 25 */     WyRegistry.registerEntityType(HEAT_BALL, "Heat Ball");
/* 26 */     WyRegistry.registerEntityType(COOL_BALL, "Cool Ball");
/* 27 */     WyRegistry.registerEntityType(THUNDER_BALL, "Thunder Ball");
/* 28 */     WyRegistry.registerEntityType(GUST_SWORD, "Gust Sword");
/* 29 */     WyRegistry.registerEntityType(WEATHER_EGG, "Weather Egg");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(HEAT_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FF0000BB").setScale(2.0D, 2.0D, 2.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(COOL_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#0000FFBB").setScale(2.0D, 2.0D, 2.0D));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler(THUNDER_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#FFFF00BB").setScale(2.0D, 2.0D, 2.0D));
/* 39 */     RenderingRegistry.registerEntityRenderingHandler(GUST_SWORD, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/* 40 */     RenderingRegistry.registerEntityRenderingHandler(WEATHER_EGG, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#8B8B8BBB").setScale(2.0D, 2.0D, 2.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\ArtOfWeatherProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */