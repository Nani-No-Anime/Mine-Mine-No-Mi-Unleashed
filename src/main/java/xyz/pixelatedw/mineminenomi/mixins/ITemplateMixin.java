package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin({Template.class})
public interface ITemplateMixin {
  @Accessor("size")
  void setSize(BlockPos paramBlockPos);
  
  @Accessor("blocks")
  List<List<Template.BlockInfo>> getBlocks();
  
  @Accessor("entities")
  List<Template.EntityInfo> getEntities();
}


