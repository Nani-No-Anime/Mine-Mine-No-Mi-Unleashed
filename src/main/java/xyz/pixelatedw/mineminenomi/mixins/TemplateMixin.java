/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.dimension.DimensionType;
/*    */ import net.minecraft.world.gen.feature.template.Template;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Redirect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Template.class})
/*    */ public class TemplateMixin
/*    */ {
/*    */   @Redirect(method = {"addBlocksToWorld(Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/template/PlacementSettings;I)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
/*    */   public boolean setBlockState(IWorld world, BlockPos pos, BlockState state, int flags) {
/* 27 */     if (world.getDimension().getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES)) {
/* 28 */       WyHelper.swapBlockData(world, pos, state);
/*    */     } else {
/* 30 */       world.setBlockState(pos, state, flags);
/* 31 */     }  return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\TemplateMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */