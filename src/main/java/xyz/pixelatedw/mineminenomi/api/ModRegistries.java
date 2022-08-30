/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIRegistries;
/*    */ 
/*    */ 
/*    */ 
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ModRegistries
/*    */ {
/*    */   static {
/* 17 */     APIRegistries.make(new ResourceLocation("mineminenomi", "jolly_roger_elements"), JollyRogerElement.class);
/* 18 */     APIRegistries.make(new ResourceLocation("mineminenomi", "challenges"), Challenge.class);
/*    */   }
/*    */   
/* 21 */   public static final IForgeRegistry<JollyRogerElement> JOLLY_ROGER_ELEMENTS = RegistryManager.ACTIVE.getRegistry(JollyRogerElement.class);
/* 22 */   public static final IForgeRegistry<Challenge> CHALLENGES = RegistryManager.ACTIVE.getRegistry(Challenge.class);
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\ModRegistries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */