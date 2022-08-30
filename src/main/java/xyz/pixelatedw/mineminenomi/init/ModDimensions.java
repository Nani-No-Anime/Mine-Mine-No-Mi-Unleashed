/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraftforge.common.ModDimension;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.world.ChallengesModDimension;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModDimensions
/*    */ {
/* 12 */   public static final ModDimension CHALLENGES = (ModDimension)new ChallengesModDimension();
/*    */ 
/*    */   
/*    */   static {
/* 16 */     WyRegistry.registerDimension(CHALLENGES, "Challenges");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModDimensions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */