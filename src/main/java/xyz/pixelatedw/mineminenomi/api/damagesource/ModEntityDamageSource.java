package xyz.pixelatedw.mineminenomi.api.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;

import javax.annotation.Nullable;


public class ModEntityDamageSource
  extends ModDamageSource
{
  @Nullable
  protected final Entity damageSourceEntity;
  
  public ModEntityDamageSource(String damageTypeIn, @Nullable Entity damageSourceEntityIn) {
    super(damageTypeIn, false, false, false);
    this.damageSourceEntity = damageSourceEntityIn;
  }
  
  @Nullable
  public Entity getTrueSource() {
    return this.damageSourceEntity;
  }
  
  public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
    ItemStack itemstack = (this.damageSourceEntity instanceof LivingEntity) ? ((LivingEntity)this.damageSourceEntity).getHeldItemMainhand() : ItemStack.EMPTY;
    String s = "death.attack." + this.damageType;
    return (!itemstack.isEmpty() && itemstack.hasDisplayName()) ? (ITextComponent)new TranslationTextComponent(s + ".item", new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), itemstack.getTextComponent() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName() });
  }
  
  public boolean isDifficultyScaled() {
    return (this.damageSourceEntity != null && this.damageSourceEntity instanceof LivingEntity && !(this.damageSourceEntity instanceof net.minecraft.entity.player.PlayerEntity));
  }
  
  @Nullable
  public Vec3d getDamageLocation() {
    return (this.damageSourceEntity != null) ? this.damageSourceEntity.getPositionVec() : null;
  }
}


