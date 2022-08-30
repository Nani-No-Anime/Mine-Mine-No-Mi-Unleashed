/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.GlowingAbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class PikaProjectiles {
/* 18 */   public static final EntityType AMATERASU = WyRegistry.createEntityType(AmaterasuProjectile::new).size(1.0F, 1.0F).build("amaterasu");
/* 19 */   public static final EntityType YASAKANI_NO_MAGATAMA = WyRegistry.createEntityType(YasakaniNoMagatamaProjectile::new).size(0.2F, 0.2F).build("yasakani_no_magatama");
/*    */ 
/*    */   
/*    */   static {
/* 23 */     WyRegistry.registerEntityType(AMATERASU, "Amaterasu");
/* 24 */     WyRegistry.registerEntityType(YASAKANI_NO_MAGATAMA, "Yasakani no Magatama");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 31 */     RenderingRegistry.registerEntityRenderingHandler(AMATERASU, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor(1.0D, 0.8999999761581421D, 0.10000000149011612D, 1.0D).setScale(1.5D, 1.5D, 3.0D));
/* 32 */     RenderingRegistry.registerEntityRenderingHandler(YASAKANI_NO_MAGATAMA, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(1.0D, 0.8999999761581421D, 0.10000000149011612D, 1.0D).setScale(0.5D, 0.5D, 0.5D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\PikaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */