/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
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
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BrickBatModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.NightmareSoldierRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.renderers.AbilityProjectileRenderer;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class KageProjectiles {
/* 20 */   public static final EntityType BRICK_BAT = WyRegistry.createEntityType(BrickBatProjectile::new).size(0.5F, 0.5F).build("brick_bat");
/* 21 */   public static final EntityType BLACK_BOX = WyRegistry.createEntityType(BlackBoxProjectile::new).size(0.5F, 0.5F).build("black_box");
/*    */ 
/*    */   
/* 24 */   public static final EntityType TSUNO_TOKAGE = WyRegistry.createEntityType(TsunoTokagePillarEntity::new).size(1.5F, 2.5F).build("tsuno_tokage");
/*    */   
/* 26 */   public static final EntityType NIGHTMARE_SOLDIER = WyRegistry.createEntityType(xyz.pixelatedw.mineminenomi.entities.mobs.ability.NightmareSoldierEntity::new).size(1.5F, 2.5F).build("nightmare_soldier");
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 31 */     WyRegistry.registerEntityType(BRICK_BAT, "Brick Bat");
/* 32 */     WyRegistry.registerEntityType(BLACK_BOX, "Brick Box");
/*    */     
/* 34 */     WyRegistry.registerEntityType(TSUNO_TOKAGE, "Tsuno Tokage");
/*    */     
/* 36 */     WyRegistry.registerEntityType(NIGHTMARE_SOLDIER, "Nightmare Soldier");
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent event) {
/* 43 */     RenderingRegistry.registerEntityRenderingHandler(BRICK_BAT, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new BrickBatModel())).setTexture("brickbat"));
/* 44 */     RenderingRegistry.registerEntityRenderingHandler(BLACK_BOX, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new BrickBatModel())).setTexture("brickbat"));
/* 45 */     RenderingRegistry.registerEntityRenderingHandler(TSUNO_TOKAGE, (IRenderFactory)(new AbilityProjectileRenderer.Factory((EntityModel)new CubeModel())).setColor("#431C4b").setScale(5.0D, 5.0D, 15.0D));
/* 46 */     RenderingRegistry.registerEntityRenderingHandler(NIGHTMARE_SOLDIER, (IRenderFactory)new NightmareSoldierRenderer.Factory());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\KageProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */