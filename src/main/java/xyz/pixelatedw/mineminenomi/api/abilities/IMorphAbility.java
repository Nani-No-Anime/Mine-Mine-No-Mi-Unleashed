package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;

public interface IMorphAbility {
  ZoanInfo getTransformation();
  
  boolean isTransformationActive(LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IMorphAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */