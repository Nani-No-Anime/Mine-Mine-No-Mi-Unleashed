/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SharkModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class FishmanKarateProjectiles {
/* 19 */   public static final EntityType UCHIMIZU = WyRegistry.createEntityType(UchimizuProjectile::new).size(0.5F, 0.5F).build("uchimizu");
/* 20 */   public static final EntityType MURASAME = WyRegistry.createEntityType(MurasameProjectile::new).size(0.5F, 0.5F).build("murasame");
/* 21 */   public static final EntityType YARINAMI = WyRegistry.createEntityType(YarinamiProjectile::new).size(0.5F, 0.5F).build("yarinami");
/*    */ 
/*    */   
/*    */   static {
/* 25 */     WyRegistry.registerEntityType(UCHIMIZU, "Uchimizu");
/* 26 */     WyRegistry.registerEntityType(MURASAME, "Murasame");
/* 27 */     WyRegistry.registerEntityType(YARINAMI, "Yarinami");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler(UCHIMIZU, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#00CED1").setScale(0.5D, 0.5D, 1.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(MURASAME, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SharkModel())).setTexture("murasame").setScale(0.8D, 0.8D, 1.2D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(YARINAMI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setColor("#00CED1").setScale(5.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\FishmanKarateProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */