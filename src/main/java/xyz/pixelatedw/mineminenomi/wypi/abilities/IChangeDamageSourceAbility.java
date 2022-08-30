package xyz.pixelatedw.mineminenomi.wypi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IChangeDamageSourceAbility {
  float damageToEntityWithSource(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity);
  
  DamageSource getSourceToUse(PlayerEntity paramPlayerEntity);
  
  boolean cancelsOriginalDamage();
  
  boolean isSourceChangeEnabled();
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\IChangeDamageSourceAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */