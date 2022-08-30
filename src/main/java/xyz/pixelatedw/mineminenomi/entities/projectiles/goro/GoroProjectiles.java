/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.VoltVariProjectileRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.LightningBallEntityRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.LightningEntityRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.GlowingAbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GoroProjectiles {
/* 20 */   public static final EntityType<LightningEntity> LIGHTNING = WyRegistry.createEntityType(LightningEntity::new).size(0.0F, 0.0F).immuneToFire().setTrackingRange(1024).setUpdateInterval(1).build("lightning");
/* 21 */   public static final EntityType RAIGO = WyRegistry.createEntityType(RaigoProjectile::new).size(15.0F, 15.0F).setUpdateInterval(1).setTrackingRange(1024).build("raigo");
/* 22 */   public static final EntityType VOLT_VARI = WyRegistry.createEntityType(VoltVariProjectile::new).size(1.75F, 1.75F).build("volt_vari");
/* 23 */   public static final EntityType<LightningBallEntity> LIGHTNING_BALL = WyRegistry.createEntityType(LightningBallEntity::new).size(0.0F, 0.0F).build("lightning_ball");
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 28 */     WyRegistry.registerEntityType(LIGHTNING, "Lightning");
/* 29 */     WyRegistry.registerEntityType(LIGHTNING_BALL, "Lightning Ball");
/* 30 */     WyRegistry.registerEntityType(RAIGO, "Raigo");
/* 31 */     WyRegistry.registerEntityType(VOLT_VARI, "Volt Vari");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 39 */     RenderingRegistry.registerEntityRenderingHandler(LIGHTNING, (IRenderFactory)new LightningEntityRenderer.Factory());
/* 40 */     RenderingRegistry.registerEntityRenderingHandler(LIGHTNING_BALL, (IRenderFactory)new LightningBallEntityRenderer.Factory());
/* 41 */     RenderingRegistry.registerEntityRenderingHandler(RAIGO, (IRenderFactory)(new GlowingAbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.0D, 0.9800000190734863D, 1.0D, 1.0D).setScale(60.0D));
/* 42 */     RenderingRegistry.registerEntityRenderingHandler(VOLT_VARI, (IRenderFactory)(new VoltVariProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor(0.0D, 0.9800000190734863D, 1.0D, 0.44999998807907104D).setScale(10.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goro\GoroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */