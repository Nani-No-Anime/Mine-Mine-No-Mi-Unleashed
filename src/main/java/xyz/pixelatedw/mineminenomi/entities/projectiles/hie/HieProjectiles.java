/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HieProjectiles {
/* 19 */   public static final EntityType ICE_BLOCK_PARTISAN = WyRegistry.createEntityType(IceBlockPartisanProjectile::new).size(0.5F, 0.5F).build("ice_block_partisan");
/* 20 */   public static final EntityType ICE_BALL = WyRegistry.createEntityType(IceBallProjectile::new).size(0.7F, 0.7F).build("ice_ball");
/* 21 */   public static final EntityType ICE_BLOCK_PHEASANT = WyRegistry.createEntityType(IceBlockPheasantProjectile::new).size(2.0F, 2.0F).build("ice_block_pheasant");
/* 22 */   public static final EntityType ICE_BLOCK_AVALANCHE = WyRegistry.createEntityType(IceBlockAvalancheProjectile::new).size(9.0F, 9.0F).build("ice_block_avalanche");
/*    */   
/*    */   static {
/* 25 */     WyRegistry.registerEntityType(ICE_BLOCK_PARTISAN, "Ice Block: Partisan");
/* 26 */     WyRegistry.registerEntityType(ICE_BALL, "Ice Ball");
/* 27 */     WyRegistry.registerEntityType(ICE_BLOCK_PHEASANT, "Ice Block: Pheasant");
/* 28 */     WyRegistry.registerEntityType(ICE_BLOCK_AVALANCHE, "Ice Block: Avalanche");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(ICE_BLOCK_PARTISAN, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TridentModel())).setTexture("iceblockpartisan").setScale(1.5D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(ICE_BALL, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#36648B").setScale(5.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(ICE_BLOCK_PHEASANT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PheasantModel())).setTexture("iceblockpheasant").setScale(5.0D));
/* 38 */     RenderingRegistry.registerEntityRenderingHandler(ICE_BLOCK_AVALANCHE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setColor("#54f7ff").setScale(8.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hie\HieProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */