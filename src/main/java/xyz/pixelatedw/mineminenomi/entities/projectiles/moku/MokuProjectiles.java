/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MokuProjectiles {
/* 17 */   public static final EntityType WHITE_OUT = WyRegistry.createEntityType(WhiteOutProjectile::new).size(0.5F, 0.5F).build("white_out");
/* 18 */   public static final EntityType WHITE_BLOW = WyRegistry.createEntityType(WhiteBlowProjectile::new).size(0.5F, 0.5F).build("white_blow");
/* 19 */   public static final EntityType WHITE_SNAKE = WyRegistry.createEntityType(WhiteSnakeProjectile::new).size(0.5F, 0.5F).build("white_snake");
/* 20 */   public static final EntityType WHITE_GRAB = WyRegistry.createEntityType(WhiteGrabProjectile::new).size(0.5F, 0.5F).build("white_grab");
/*    */ 
/*    */   
/*    */   static {
/* 24 */     WyRegistry.registerEntityType(WHITE_OUT, "White Out");
/* 25 */     WyRegistry.registerEntityType(WHITE_BLOW, "White Blow");
/* 26 */     WyRegistry.registerEntityType(WHITE_SNAKE, "White Snake");
/* 27 */     WyRegistry.registerEntityType(WHITE_GRAB, "White Grab");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler(WHITE_OUT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(WHITE_BLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(WHITE_SNAKE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(WHITE_GRAB, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\MokuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */