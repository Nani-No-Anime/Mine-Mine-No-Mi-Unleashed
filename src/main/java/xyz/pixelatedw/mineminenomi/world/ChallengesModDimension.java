/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.dimension.Dimension;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraftforge.common.ModDimension;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChallengesModDimension
/*    */   extends ModDimension
/*    */ {
/*    */   public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
/* 15 */     return ChallengesDimension::new;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\world\ChallengesModDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */