/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*    */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsProvider;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataProvider;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ModCapabilities
/*    */ {
/*    */   public static void init() {
/* 28 */     APIDefaults.initCapabilities();
/*    */ 
/*    */     
/* 31 */     DevilFruitCapability.register();
/* 32 */     HakiDataCapability.register();
/* 33 */     EntityStatsCapability.register();
/* 34 */     ChallengesDataCapability.register();
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
/* 40 */     if (event.getObject() == null) {
/*    */       return;
/*    */     }
/* 43 */     if (event.getObject() instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */       
/* 45 */       event.addCapability(new ResourceLocation("mineminenomi", "haki_data"), (ICapabilityProvider)new HakiDataProvider());
/* 46 */       event.addCapability(new ResourceLocation("mineminenomi", "challenges"), (ICapabilityProvider)new ChallengesDataProvider());
/*    */     } 
/*    */     
/* 49 */     if (event.getObject() instanceof net.minecraft.entity.LivingEntity) {
/*    */       
/* 51 */       event.addCapability(new ResourceLocation("mineminenomi", "devil_fruit"), (ICapabilityProvider)new DevilFruitProvider());
/* 52 */       event.addCapability(new ResourceLocation("mineminenomi", "entity_stats"), (ICapabilityProvider)new EntityStatsProvider());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModCapabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */