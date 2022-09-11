package xyz.pixelatedw.mineminenomi.abilities;


import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SlashDamageImmunityAbility
  extends DamagedPassiveAbility {
  public static final SlashDamageImmunityAbility BARA_INSTANCE = new SlashDamageImmunityAbility(AbilityHelper.getDevilFruitCategory());
  
  private APIConfig.AbilityCategory category;

  
  public SlashDamageImmunityAbility(APIConfig.AbilityCategory category) {
    super("Slash Immunity", category);
    this.category = category;
    setDescription("Makes the user immune to slash based attacks");
    
    hideInGUI(false);
    this.onDamagedEvent = this::onDamagedEvent;
  }

  
  public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
    boolean isSlashDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isSlashDamage() && !((ModDamageSource)damageSource).isInternalDamage());
    boolean isSwordDamage = false;
    
    if (damageSource instanceof EntityDamageSource) {
      
      Entity source = ((EntityDamageSource)damageSource).getTrueSource();
      if (source != null && source.isAlive() && source instanceof LivingEntity) {
        
        LivingEntity livingSource = (LivingEntity)source;
        
        isSwordDamage |= checkItemStack(livingSource.getHeldItemMainhand());
        isSwordDamage |= checkItemStack(livingSource.getHeldItemOffhand());
      } 
    } 
    
    if (isSlashDamage || isSwordDamage) {
      return false;
    }
    return true;
  }

  
  private boolean checkItemStack(ItemStack stack) {
    if (stack != null && !stack.isEmpty()) {
      
      boolean isSword = ItemsHelper.isSword(stack);
      boolean hasDamageAttribute = (stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).size() > 0);
      
      if (isSword || hasDamageAttribute) {
        return true;
      }
    } 
    return false;
  }



  
  @Nullable
  public Ability create() {
    try {
      return getClass().getConstructor(new Class[] { APIConfig.AbilityCategory.class }).newInstance(new Object[] { this.category });
    }
    catch (Exception ex) {
      
      System.out.println("Exception raised for " + getDisplayName());
      ex.printStackTrace();
      
      return null;
    } 
  }
}


