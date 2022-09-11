package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
import java.util.function.Function;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExtraProjectiles {
  public static final EntityType NORMAL_BULLET = WyRegistry.createEntityType(NormalBulletProjectile::new).size(0.3F, 0.3F).build("normal_bullet");
  public static final EntityType KAIROSEKI_BULLET = WyRegistry.createEntityType(KairosekiBulletProjectile::new).size(0.3F, 0.3F).build("kairoseki_bullet");
  public static final EntityType AXE_DIAL_PROJECTILE = WyRegistry.createEntityType(AxeDialProjectile::new).size(3.5F, 0.5F).build("axe_dial_projectile");
  public static final EntityType MILKY_DIAL_PROJECTILE = WyRegistry.createEntityType(MilkyDialProjectile::new).size(0.3F, 0.3F).build("milky_dial_projectile");
  public static final EntityType POP_GREEN = WyRegistry.createEntityType(PopGreenProjectile::new).size(0.3F, 0.3F).build("pop_green");
  public static final EntityType KUJA_ARROW = WyRegistry.createEntityType(KujaArrowProjectile::new).size(0.5F, 0.5F).build("kuja_arrow");
  public static final EntityType CLOUD = WyRegistry.createEntityType(EntityCloud::new).build("cloud");
  public static final EntityType CANNON_BALL = WyRegistry.createEntityType(CannonBallProjectile::new).build("cannon_ball");
  public static final EntityType SHOCKWAVE = WyRegistry.createEntityType(ShockwaveProjectile::new).build("shockwave");
  public static final EntityType BAMBOO_PILLAR = WyRegistry.createEntityType(BambooPillarEntity::new).size(0.3F, 0.3F).build("bamboo_pillar");

  
  static {
    WyRegistry.registerEntityType(NORMAL_BULLET, "Normal Bullet");
    WyRegistry.registerEntityType(KAIROSEKI_BULLET, "Kairoseki Bullet");
    WyRegistry.registerEntityType(AXE_DIAL_PROJECTILE, "Axe Dial Projectile");
    WyRegistry.registerEntityType(MILKY_DIAL_PROJECTILE, "Milky Dial Projectile");
    WyRegistry.registerEntityType(POP_GREEN, "Pop Green");
    WyRegistry.registerEntityType(KUJA_ARROW, "Kuja Arrow");
    WyRegistry.registerEntityType(CLOUD, "Cloud");
    WyRegistry.registerEntityType(CANNON_BALL, "Cannon Ball");
    WyRegistry.registerEntityType(SHOCKWAVE, "Shockwave");
    WyRegistry.registerEntityType(BAMBOO_PILLAR, "Bamboo Pillar");
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void registerEntityRenderers(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(NORMAL_BULLET, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#878787"));
    RenderingRegistry.registerEntityRenderingHandler(KAIROSEKI_BULLET, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#F3F3F3"));
    RenderingRegistry.registerEntityRenderingHandler(AXE_DIAL_PROJECTILE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(6.0D, 0.4D, 1.5D).setColor("#69E3FF"));
    RenderingRegistry.registerEntityRenderingHandler(MILKY_DIAL_PROJECTILE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D).setColor("#69E3FF"));
    RenderingRegistry.registerEntityRenderingHandler(POP_GREEN, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.5D).setColor("#7ccc6a"));
    RenderingRegistry.registerEntityRenderingHandler(KUJA_ARROW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new ArrowModel())).setTexture("kujaarrow").setScale(1.25D));
    RenderingRegistry.registerEntityRenderingHandler(CANNON_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(2.5D).setColor("#878787"));
    RenderingRegistry.registerEntityRenderingHandler(SHOCKWAVE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setScale(0.0D, 0.0D, 0.0D));
    RenderingRegistry.registerEntityRenderingHandler(BAMBOO_PILLAR, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#51bd2d").setScale(0.4D, 0.4D, 20.0D));
  }
}


