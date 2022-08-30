/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.EntityLegModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HanaProjectiles {
/* 19 */   public static final EntityType HANDS = WyRegistry.createEntityType(HanaHandsEntity::new).size(2.0F, 2.0F).build("hands");
/* 20 */   public static final EntityType FEET = WyRegistry.createEntityType(HanaFeetEntity::new).size(2.0F, 2.0F).build("feet");
/*    */   
/* 22 */   public static final EntityType GENERIC_HANA = WyRegistry.createEntityType(HanaGenericEntity::new).size(0.5F, 0.5F).build("generic_hana");
/*    */ 
/*    */   
/*    */   static {
/* 26 */     WyRegistry.registerEntityType(HANDS, "Hands");
/* 27 */     WyRegistry.registerEntityType(FEET, "Feet");
/* 28 */     WyRegistry.registerEntityType(GENERIC_HANA, "Hana Projectile");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(HANDS, (IRenderFactory)new EmptyRenderer.Factory());
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(FEET, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new EntityLegModel())).setPlayerTexture().setScale(8.0D));
/*    */     
/* 38 */     RenderingRegistry.registerEntityRenderingHandler(GENERIC_HANA, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new SphereModel())).setScale(0.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */