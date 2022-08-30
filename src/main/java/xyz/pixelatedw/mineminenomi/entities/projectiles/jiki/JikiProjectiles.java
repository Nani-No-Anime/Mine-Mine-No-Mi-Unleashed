/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.jiki;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.PunkGibsonModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.PunkGibsonLayer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class JikiProjectiles {
/* 21 */   public static final EntityType PUNK_GIBSON = WyRegistry.createEntityType(PunkGibsonProjectile::new).size(1.5F, 1.5F).build("punk_gibson");
/* 22 */   public static final EntityType PUNK_PISTOL = WyRegistry.createEntityType(PunkPistolProjectile::new).size(0.5F, 0.5F).build("punk_pistol");
/* 23 */   public static final EntityType DAMNED_PUNK = WyRegistry.createEntityType(DamnedPunkProjectile::new).size(1.0F, 1.0F).build("damned_punk");
/*    */ 
/*    */   
/*    */   static {
/* 27 */     WyRegistry.registerEntityType(PUNK_GIBSON, "Punk Gibson");
/* 28 */     WyRegistry.registerEntityType(PUNK_PISTOL, "Punk Pistol");
/* 29 */     WyRegistry.registerEntityType(DAMNED_PUNK, "Damned Punk");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(PUNK_GIBSON, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PunkGibsonModel())).setScale(2.0D, 2.0D, 2.0D).setTexture(PunkGibsonLayer.TEXTURE));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(PUNK_PISTOL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SpearModel())).setColor("#2b2930"));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler(DAMNED_PUNK, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new CubeModel(), (EntityModel)new CubeModel())).setStretchScale(1.0D, 1.0D).setGlowing().setColor("#300061").setScale(1.0D, 1.0D, 3.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\jiki\JikiProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */