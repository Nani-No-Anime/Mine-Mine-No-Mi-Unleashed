/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;
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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class SwordsmanProjectiles {
/* 17 */   public static final EntityType YAKKODORI = WyRegistry.createEntityType(YakkodoriProjectile::new).size(0.5F, 1.5F).build("yakkodori");
/* 18 */   public static final EntityType SANBYAKUROKUJU_POUND_HO = WyRegistry.createEntityType(SanbyakurokujuPoundHoProjectile::new).size(2.5F, 0.5F).build("sanbyakurokuju_pound_ho");
/*    */ 
/*    */   
/*    */   static {
/* 22 */     WyRegistry.registerEntityType(YAKKODORI, "Yakkodori");
/* 23 */     WyRegistry.registerEntityType(SANBYAKUROKUJU_POUND_HO, "Sanbyakurokuju Pound Ho");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 30 */     RenderingRegistry.registerEntityRenderingHandler(YAKKODORI, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#bbf7b4").setScale(0.4D, 6.0D, 0.4D));
/* 31 */     RenderingRegistry.registerEntityRenderingHandler(SANBYAKUROKUJU_POUND_HO, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#bbf7b4").setScale(6.0D, 0.4D, 1.5D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\swordsman\SwordsmanProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */