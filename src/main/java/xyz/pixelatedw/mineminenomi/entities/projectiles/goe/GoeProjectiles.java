/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GoeProjectiles {
/* 19 */   public static final EntityType TODOROKI = WyRegistry.createEntityType(TodorokiProjectile::new).size(0.5F, 0.5F).build("todoroki");
/* 20 */   public static final EntityType DRAGONS_ROAR = WyRegistry.createEntityType(DragonsRoarProjectile::new).size(0.5F, 0.5F).build("dragons_roar");
/*    */ 
/*    */   
/*    */   static {
/* 24 */     WyRegistry.registerEntityType(TODOROKI, "Todoroki");
/* 25 */     WyRegistry.registerEntityType(DRAGONS_ROAR, "Dragon's Roar");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 32 */     RenderingRegistry.registerEntityRenderingHandler(TODOROKI, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(4.0D, 4.0D).setGlowing().setColor("#87CEFA").setScale(0.0D, 0.0D, 0.0D));
/* 33 */     RenderingRegistry.registerEntityRenderingHandler(DRAGONS_ROAR, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NoroNoroBeamModel())).setTexture("dragonsroar").setScale(5.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\GoeProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */