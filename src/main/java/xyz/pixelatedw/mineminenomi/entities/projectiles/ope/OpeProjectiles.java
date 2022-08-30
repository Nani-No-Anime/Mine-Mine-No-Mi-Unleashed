/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.TaktBlockRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class OpeProjectiles {
/* 19 */   public static final EntityType GAMMA_KNIFE = WyRegistry.createEntityType(GammaKnifeProjectile::new).size(0.5F, 0.5F).build("gamma_knife");
/* 20 */   public static final EntityType SPATIAL_SLASH = WyRegistry.createEntityType(SpatialSlashProjectile::new).size(10.0F, 0.5F).build("spatial_slash");
/*    */   
/* 22 */   public static final EntityType TAKT_BLOCK = WyRegistry.createEntityType(TaktBlockEntity::new).size(0.5F, 0.5F).build("takt_block");
/*    */ 
/*    */   
/*    */   static {
/* 26 */     WyRegistry.registerEntityType(GAMMA_KNIFE, "Gamma Knife");
/* 27 */     WyRegistry.registerEntityType(SPATIAL_SLASH, "Spatial Slash");
/*    */     
/* 29 */     WyRegistry.registerEntityType(TAKT_BLOCK, "Takt Block");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(GAMMA_KNIFE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#00AB66").setScale(1.0D, 1.0D, 5.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(SPATIAL_SLASH, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/*    */     
/* 39 */     RenderingRegistry.registerEntityRenderingHandler(TAKT_BLOCK, (IRenderFactory)new TaktBlockRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\OpeProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */