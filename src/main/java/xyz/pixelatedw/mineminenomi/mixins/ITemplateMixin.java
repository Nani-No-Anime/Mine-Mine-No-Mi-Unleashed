package xyz.pixelatedw.mineminenomi.mixins;

import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Template.class})
public interface ITemplateMixin {
  @Accessor("size")
  void setSize(BlockPos paramBlockPos);
  
  @Accessor("blocks")
  List<List<Template.BlockInfo>> getBlocks();
  
  @Accessor("entities")
  List<Template.EntityInfo> getEntities();
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\ITemplateMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */