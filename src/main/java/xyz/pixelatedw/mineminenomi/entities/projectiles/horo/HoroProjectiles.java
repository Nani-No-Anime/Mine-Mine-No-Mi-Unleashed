/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MiniHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NegativeHollowModel;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TokuHollowModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class HoroProjectiles {
/* 19 */   public static final EntityType NEGATIVE_HOLLOW = WyRegistry.createEntityType(NegativeHollowProjectile::new).size(0.5F, 0.5F).build("negative_hollow");
/* 20 */   public static final EntityType MINI_HOLLOW = WyRegistry.createEntityType(MiniHollowProjectile::new).size(0.5F, 0.5F).build("mini_hollow");
/* 21 */   public static final EntityType TOKU_HOLLOW = WyRegistry.createEntityType(TokuHollowProjectile::new).size(1.0F, 1.0F).build("toku_hollow");
/*    */ 
/*    */   
/*    */   static {
/* 25 */     WyRegistry.registerEntityType(NEGATIVE_HOLLOW, "Negative Hollow");
/* 26 */     WyRegistry.registerEntityType(MINI_HOLLOW, "Mini Hollow");
/* 27 */     WyRegistry.registerEntityType(TOKU_HOLLOW, "Toku Hollow");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 34 */     RenderingRegistry.registerEntityRenderingHandler(NEGATIVE_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new NegativeHollowModel())).setTexture("negativehollow").setAlpha(120.0D).setScale(2.0D));
/* 35 */     RenderingRegistry.registerEntityRenderingHandler(MINI_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new MiniHollowModel())).setColor("#F8F8FF").setAlpha(120.0D));
/* 36 */     RenderingRegistry.registerEntityRenderingHandler(TOKU_HOLLOW, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new TokuHollowModel())).setTexture("tokuhollow").setAlpha(120.0D).setScale(4.0D));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\HoroProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */