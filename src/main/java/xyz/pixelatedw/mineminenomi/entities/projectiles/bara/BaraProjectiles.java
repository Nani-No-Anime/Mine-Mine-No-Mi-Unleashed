/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.BaraFestivalRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BaraProjectiles {
/* 18 */   public static final EntityType BARA_BARA_HO = WyRegistry.createEntityType(BaraBaraHoProjectile::new).size(1.2F, 1.2F).build("bara_bara_ho");
/* 19 */   public static final EntityType DAI_CIRCUS = WyRegistry.createEntityType(BaraBaraHoProjectile::new).size(0.5F, 0.5F).build("dai_circus");
/*    */   
/* 21 */   public static final EntityType BARA_FESTIVAL = WyRegistry.createEntityType(BaraFestivalEntity::new).size(0.8F, 0.8F).build("bara_festival");
/*    */ 
/*    */   
/*    */   static {
/* 25 */     WyRegistry.registerEntityType(BARA_BARA_HO, "Bara Bara Ho");
/* 26 */     WyRegistry.registerEntityType(DAI_CIRCUS, "Dai Circus");
/*    */     
/* 28 */     WyRegistry.registerEntityType(BARA_FESTIVAL, "Bara Festival");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(BARA_BARA_HO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setPlayerTexture().setScale(1.5D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(DAI_CIRCUS, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setPlayerTexture().setScale(1.5D));
/*    */     
/* 38 */     RenderingRegistry.registerEntityRenderingHandler(BARA_FESTIVAL, (IRenderFactory)new BaraFestivalRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */