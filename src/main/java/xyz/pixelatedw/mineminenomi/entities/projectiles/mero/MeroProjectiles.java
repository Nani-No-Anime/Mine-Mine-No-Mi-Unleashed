/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HeartModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class MeroProjectiles {
/* 18 */   public static final EntityType MERO_MERO_MELLOW = WyRegistry.createEntityType(MeroMeroMellowProjectile::new).size(3.5F, 3.5F).build("mero_mero_mellow");
/* 19 */   public static final EntityType PISTOL_KISS = WyRegistry.createEntityType(PistolKissProjectile::new).size(0.5F, 0.5F).build("pistol_kiss");
/* 20 */   public static final EntityType SLAVE_ARROW = WyRegistry.createEntityType(SlaveArrowProjectile::new).size(0.5F, 0.5F).build("slave_arrow");
/*    */ 
/*    */   
/*    */   static {
/* 24 */     WyRegistry.registerEntityType(MERO_MERO_MELLOW, "Mero Mero Mellow");
/* 25 */     WyRegistry.registerEntityType(PISTOL_KISS, "Pistol Kiss");
/* 26 */     WyRegistry.registerEntityType(SLAVE_ARROW, "Slave Arrow");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 33 */     RenderingRegistry.registerEntityRenderingHandler(MERO_MERO_MELLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HeartModel())).setTexture("meromeromellow").setScale(3.0D));
/* 34 */     RenderingRegistry.registerEntityRenderingHandler(PISTOL_KISS, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new HeartModel())).setTexture("meromeromellow").setScale(0.5D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(SLAVE_ARROW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new ArrowModel())).setColor("#FF69B4"));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\MeroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */