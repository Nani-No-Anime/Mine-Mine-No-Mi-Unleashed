package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;









@Mixin({Template.class})
public class TemplateMixin
{
  @Redirect(method = {"addBlocksToWorld(Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/template/PlacementSettings;I)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
  public boolean setBlockState(IWorld world, BlockPos pos, BlockState state, int flags) {
    if (world.getDimension().getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES)) {
      WyHelper.swapBlockData(world, pos, state);
    } else {
      world.setBlockState(pos, state, flags);
    }  return true;
  }
}


