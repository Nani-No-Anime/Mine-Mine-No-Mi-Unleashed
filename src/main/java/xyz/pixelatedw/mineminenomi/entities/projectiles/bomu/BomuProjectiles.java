/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class BomuProjectiles {
/* 18 */   public static final EntityType NOSE_FANCY_CANNON = WyRegistry.createEntityType(NoseFancyCannonProjectile::new).size(1.0F, 1.0F).build("nose_fancy_cannon");
/* 19 */   public static final EntityType BREEZE_BREATH_BOMB = WyRegistry.createEntityType(BreezeBreathBombProjectile::new).size(1.0F, 1.0F).build("breeze_breath_bomb");
/*    */ 
/*    */   
/*    */   static {
/* 23 */     WyRegistry.registerEntityType(NOSE_FANCY_CANNON, "Nose Fancy Cannon");
/* 24 */     WyRegistry.registerEntityType(BREEZE_BREATH_BOMB, "Breeze Breath Bomb");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 31 */     RenderingRegistry.registerEntityRenderingHandler(NOSE_FANCY_CANNON, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("3D2B1F").setScale(0.4D, 0.4D, 0.4D));
/* 32 */     RenderingRegistry.registerEntityRenderingHandler(BREEZE_BREATH_BOMB, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("3D2B1F").setScale(1.0D, 1.0D, 1.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\BomuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */