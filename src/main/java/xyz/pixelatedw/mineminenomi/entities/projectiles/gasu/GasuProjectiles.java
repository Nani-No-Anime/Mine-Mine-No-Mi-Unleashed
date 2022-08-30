/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*    */ import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GasuProjectiles {
/* 18 */   public static final EntityType GAS_ROBE = WyRegistry.createEntityType(GasRobeProjectile::new).size(0.5F, 0.5F).build("gas_robe");
/* 19 */   public static final EntityType GASTILLE = WyRegistry.createEntityType(GastilleProjectile::new).size(0.5F, 0.5F).build("gastille");
/* 20 */   public static final EntityType BIG_GASTILLE = WyRegistry.createEntityType(BigGastilleProjectile::new).size(1.5F, 1.5F).build("big_gastille");
/*    */ 
/*    */   
/*    */   static {
/* 24 */     WyRegistry.registerEntityType(GAS_ROBE, "Gas Robe");
/* 25 */     WyRegistry.registerEntityType(GASTILLE, "Gastille");
/* 26 */     WyRegistry.registerEntityType(BIG_GASTILLE, "big_gastille");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler(GAS_ROBE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(GASTILLE, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#300061").setScale(1.0D, 1.0D, 3.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(BIG_GASTILLE, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(3.0D, 3.0D).setGlowing().setColor("#300061").setScale(3.0D, 3.0D, 9.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GasuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */