/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
import xyz.pixelatedw.mineminenomi.renderers.abilities.ChargingUrsusShockRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class NikyuProjectiles {
/* 18 */   public static final EntityType PAD_HO = WyRegistry.createEntityType(PadHoProjectile::new).size(0.5F, 0.5F).build("pad_ho");
/* 19 */   public static final EntityType URSUS_SHOCK = WyRegistry.createEntityType(UrsusShockProjectile::new).size(1.0F, 1.0F).build("ursus_shock");
/*    */ 
/*    */   
/* 22 */   public static final EntityType CHARGING_URSUS_SHOCK = WyRegistry.createEntityType(ChargingUrsusShockEntity::new).size(1.0F, 1.0F).build("charging_ursus_shock");
/*    */ 
/*    */   
/*    */   static {
/* 26 */     WyRegistry.registerEntityType(PAD_HO, "Pad Ho");
/* 27 */     WyRegistry.registerEntityType(URSUS_SHOCK, "Ursus Shock");
/*    */     
/* 29 */     WyRegistry.registerEntityType(CHARGING_URSUS_SHOCK, "Charging Ursus Shock");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(PAD_HO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PawModel())).setColor("#F8F8FF33").setScale(1.0D));
/* 37 */     RenderingRegistry.registerEntityRenderingHandler(URSUS_SHOCK, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new PawModel())).setColor("#F8F8FF33").setScale(0.6D));
/*    */     
/* 39 */     RenderingRegistry.registerEntityRenderingHandler(CHARGING_URSUS_SHOCK, (IRenderFactory)new ChargingUrsusShockRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\NikyuProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */