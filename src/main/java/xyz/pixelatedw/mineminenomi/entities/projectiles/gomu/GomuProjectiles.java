/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.StretchingProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class GomuProjectiles {
/* 17 */   public static final EntityType GOMU_GOMU_NO_PISTOL = WyRegistry.createEntityType(GomuGomuNoPistolProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_pistol");
/* 18 */   public static final EntityType GOMU_GOMU_NO_JET_PISTOL = WyRegistry.createEntityType(GomuGomuNoJetPistolProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_jet_pistol");
/* 19 */   public static final EntityType GOMU_GOMU_NO_ELEPHANT_GUN = WyRegistry.createEntityType(GomuGomuNoElephantGunProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_elephant_gun");
/* 20 */   public static final EntityType GOMU_NO_KONG_GUN = WyRegistry.createEntityType(GomuGomuNoKongProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_kong_gun");
/*    */   
/* 22 */   public static final EntityType GOMU_GOMU_NO_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoBazookaProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_bazooka");
/* 23 */   public static final EntityType GOMU_GOMU_NO_JET_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoJetBazookaProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_jet_bazooka");
/* 24 */   public static final EntityType GOMU_GOMU_NO_GRIZZLY_MAGNUM = WyRegistry.createEntityType(GomuGomuNoGrizzlyMagnumProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_grizzly_magnum");
/* 25 */   public static final EntityType GOMU_GOMU_NO_LEO_BAZOOKA = WyRegistry.createEntityType(GomuGomuNoLeoBazookaProjectile::new).size(5.0F, 5.0F).build("gomu_gomu_no_leo_bazooka");
/*    */   
/* 27 */   public static final EntityType GOMU_GOMU_NO_ROCKET = WyRegistry.createEntityType(GomuGomuNoRocketProjectile::new).size(0.5F, 0.5F).build("gomu_gomu_no_rocket");
/*    */ 
/*    */   
/*    */   static {
/* 31 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_PISTOL, "Gomu Gomu no Pistol");
/* 32 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_JET_PISTOL, "Gomu Gomu no Jet Pistol");
/* 33 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_ELEPHANT_GUN, "Gomu Gomu no Elephant Gun");
/* 34 */     WyRegistry.registerEntityType(GOMU_NO_KONG_GUN, "Gomu Gomu no Kong Gun");
/*    */     
/* 36 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_BAZOOKA, "Gomu Gomu no Bazooka");
/* 37 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_JET_BAZOOKA, "Gomu Gomu no Jet Bazooka");
/* 38 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_GRIZZLY_MAGNUM, "Gomu Gomu no Grizzly Magnum");
/* 39 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_LEO_BAZOOKA, "Gomu Gomu no Leo Bazooka");
/*    */     
/* 41 */     WyRegistry.registerEntityType(GOMU_GOMU_NO_ROCKET, "Gomu Gomu no Rocket");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 48 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_PISTOL, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/* 49 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_JET_PISTOL, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(1.45D, 1.45D).setPlayerTexture().setScale(3.0D, 3.0D, 3.0D));
/* 50 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_ELEPHANT_GUN, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(10.0D, 10.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/* 51 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_NO_KONG_GUN, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(4.5D, 4.5D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/*    */     
/* 53 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_BAZOOKA, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/* 54 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_JET_BAZOOKA, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture().setScale(3.0D, 3.0D, 3.0D));
/* 55 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_GRIZZLY_MAGNUM, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(10.0D, 10.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/* 56 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_LEO_BAZOOKA, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(5.0D, 5.0D, 10.0D).setPlayerTexture().setScale(13.0D, 13.0D, 13.0D));
/*    */     
/* 58 */     RenderingRegistry.registerEntityRenderingHandler(GOMU_GOMU_NO_ROCKET, (IRenderFactory)(new StretchingProjectileRenderer.Factory((EntityModel)new EntityArmModel())).setStretchScale(3.1D, 3.1D, 10.0D).setPlayerTexture());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */