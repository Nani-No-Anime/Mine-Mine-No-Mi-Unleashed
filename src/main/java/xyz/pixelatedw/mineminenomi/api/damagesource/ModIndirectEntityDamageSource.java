package xyz.pixelatedw.mineminenomi.api.damagesource;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;


public class ModIndirectEntityDamageSource
  extends ModEntityDamageSource
{
  private final Entity indirectEntity;
  
  public ModIndirectEntityDamageSource(String damageTypeIn, Entity source, @Nullable Entity indirectEntityIn) {
    super(damageTypeIn, source);
    this.indirectEntity = indirectEntityIn;
  }
  
  @Nullable
  public Entity getImmediateSource() {
    return this.damageSourceEntity;
  }




  
  @Nullable
  public Entity getTrueSource() {
    return this.indirectEntity;
  }



  
  public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
    ITextComponent itextcomponent = (this.indirectEntity == null) ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
    ItemStack itemstack = (this.indirectEntity instanceof LivingEntity) ? ((LivingEntity)this.indirectEntity).getHeldItemMainhand() : ItemStack.EMPTY;
    String s = "death.attack." + this.damageType;
    String s1 = s + ".item";
    return (!itemstack.isEmpty() && itemstack.hasDisplayName()) ? (ITextComponent)new TranslationTextComponent(s1, new Object[] { entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), itextcomponent });
  }
}


