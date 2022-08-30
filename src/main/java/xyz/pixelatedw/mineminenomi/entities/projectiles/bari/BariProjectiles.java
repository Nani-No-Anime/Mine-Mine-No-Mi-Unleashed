/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
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
/*    */ public class BariProjectiles {
/* 17 */   public static final EntityType BARRIER_CRASH = WyRegistry.createEntityType(BarrierCrashProjectile::new).size(2.5F, 7.5F).build("barrier_crash");
/* 18 */   public static final EntityType BARRIERBILITY_STAIRS = WyRegistry.createEntityType(BarrierbilityStairsProjectile::new).size(0.5F, 0.5F).build("barrierbility_stairs");
/*    */ 
/*    */   
/*    */   static {
/* 22 */     WyRegistry.registerEntityType(BARRIER_CRASH, "Barrier Crash");
/* 23 */     WyRegistry.registerEntityType(BARRIERBILITY_STAIRS, "Barrierbility Stairs");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler(BARRIER_CRASH, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#87CEFAAA").setScale(8.0D, 10.0D, 0.5D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler(BARRIERBILITY_STAIRS, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BariProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */