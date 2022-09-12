package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.UUID;

public class CoreSwordItem extends SwordItem {
  public double damage = 1.0D;
  
  public double conductivity = 0.1D;
  public double range = 0.0D; protected boolean isPoisonous = false; protected boolean isFireAspect = false; protected boolean isSlownessInducing = false;
  protected boolean isStackable = false;
  protected int poisonTimer = 100; protected int fireAspectTimer = 10; protected int slownessTimer = 100; protected int frostBiteTimer = 0;
  
  private boolean isBlunt = false;
  private boolean rustImmunity = false;
  private boolean independentImbuing = false;
  private Ingredient repairIngredient;
  protected static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("06256896-00c1-45b4-bc71-514ee36310bd");






  
  private IItemPropertyGetter hakiProperty;






  
  private IItemPropertyGetter sheathedProperty;







  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return true;
  }











  
  public CoreSwordItem(Item.Properties props, int damage, float attackSpeed) {
    super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, props); this.hakiProperty = ((itemStack, world, livingEntity) -> { 
    if (livingEntity == null) return 0.0F;  
    float hasHakiActive = 0.0F; if (livingEntity instanceof PlayerEntity) {
         IAbilityData IabilityProps = AbilityDataCapability.get(livingEntity); 
         boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); 
         boolean offHandFlag = (livingEntity.getHeldItemOffhand() == itemStack); 
         BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)IabilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
         boolean hakiActiveFlag = (ability != null && ability.isContinuous()); hasHakiActive = ((mainHandFlag || offHandFlag) && hakiActiveFlag) ? 1.0F : 0.0F; } 
         else if 
         (livingEntity instanceof OPEntity) { hasHakiActive = ((OPEntity)livingEntity).hasBusoHaki() ? 1.0F : 0.0F; }  return hasHakiActive; });
          this.sheathedProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
          return 1.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() != itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() != itemStack); return (mainHandFlag && offHandFlag) ? 1.0F : 0.0F; }); this.damage = (damage - 1);
    addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
    addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
  }
  
  public CoreSwordItem(int damage, int durability) {
    this(damage, -2.4F, durability);
    this.damage = (damage - 1);
    addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
    addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
  }
  
  public CoreSwordItem(int damage, float attackSpeed, int durability) {
    super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, (new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(durability)); this.hakiProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null) return 0.0F;  float hasHakiActive = 0.0F; if (livingEntity instanceof PlayerEntity) { IAbilityData props = AbilityDataCapability.get(livingEntity); boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() == itemStack); BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)props.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE); boolean hakiActiveFlag = (ability != null && ability.isContinuous()); hasHakiActive = ((mainHandFlag || offHandFlag) && hakiActiveFlag) ? 1.0F : 0.0F; } else if (livingEntity instanceof OPEntity) { hasHakiActive = ((OPEntity)livingEntity).hasBusoHaki() ? 1.0F : 0.0F; }  return hasHakiActive; }); this.sheathedProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
          return 1.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() != itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() != itemStack); return (mainHandFlag && offHandFlag) ? 1.0F : 0.0F; }); this.damage = (damage - 1);
    addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
    addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
  }














  
  public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
    if (!world.isRemote) {
      
      itemStack.getOrCreateTag();
      if (itemStack.getTag().getBoolean("isClone") && !itemStack.getTag().getBoolean("hasCloneTag")) {
        
        itemStack.setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + itemStack.getDisplayName().getFormattedText() + " (Replica)"));
        itemStack.getTag().putBoolean("hasCloneTag", true);
      } 
    } 
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    return new ActionResult(ActionResultType.PASS, player.getHeldItem(hand));
  }


  
  public int getItemEnchantability() {
    return 14;
  }


  
  public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
    IAbilityData abilityProps = AbilityDataCapability.get(attacker);
    
    BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
    boolean hasBusoHaki = (ability != null && ability.isContinuous());
    
    if (!hasBusoHaki) {
      
      int damage = itemStack.getOrCreateTag().getBoolean("isClone") ? 3 : 1;
      itemStack.damageItem(damage, attacker, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
    } 



    
    if (hasBusoHaki || this.isBlunt) {
      
      Item mainShield = target.getHeldItemMainhand().getItem();
      Item secondaryShield = target.getHeldItemOffhand().getItem();
      if (target instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.SHIELD) || secondaryShield.equals(Items.SHIELD))) {
        
        ((PlayerEntity)target).getCooldownTracker().setCooldown(Items.SHIELD, 100);
        target.resetActiveHand();
        target.world.setEntityState((Entity)attacker, (byte)30);
      } 
    } 
    
    if (this.isPoisonous) {
      target.addPotionEffect(new EffectInstance(Effects.POISON, this.poisonTimer, 0));
    }
    if (this.isFireAspect) {
      
      SetOnFireEvent event = new SetOnFireEvent(attacker, target, this.fireAspectTimer);
      if (!MinecraftForge.EVENT_BUS.post(event)) {
        target.setFire(this.fireAspectTimer);
      }
    } 
    if (this.isSlownessInducing)
    {
      if (this.isStackable) {
        
        if (target.isPotionActive(Effects.SLOWNESS)) {
          
          int timer = target.getActivePotionEffect(Effects.SLOWNESS).getDuration();
          target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, timer + this.slownessTimer, 0));
        } else {
          
          target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
        } 
      } else {
        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
      } 
    }
    if (this.frostBiteTimer > 0) {
      AbilityHelper.addFrostbite(target, attacker, this.frostBiteTimer);
    }
    return true;
  }

  
  public <T extends CoreSwordItem> T setIsPoisonous() {
    this.isPoisonous = true;
    this.poisonTimer = 100;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setIsPoisonous(int timer) {
    this.isPoisonous = true;
    this.poisonTimer = timer;
    return (T)this;
  }





  
  public <T extends CoreSwordItem> T setConductivity(double conductivity) {
    this.conductivity = conductivity;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setRepairIngredient(Ingredient ingredient) {
    this.repairIngredient = ingredient;
    return (T)this;
  }

  
  public Ingredient getRepairIngredient() {
    return this.repairIngredient;
  }

  
  public <T extends CoreSwordItem> T setBlunt() {
    this.isBlunt = true;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setSharp() {
    this.isBlunt = false;
    return (T)this;
  }

  
  public boolean isBlunt() {
    return this.isBlunt;
  }

  
  public <T extends CoreSwordItem> T setRustImmunity() {
    this.rustImmunity = true;
    return (T)this;
  }

  
  public boolean isRustImmune() {
    return this.rustImmunity;
  }

  
  public <T extends CoreSwordItem> T setIsFireAspect() {
    this.isFireAspect = true;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setIsFireAspect(int timer) {
    this.isFireAspect = true;
    this.fireAspectTimer = timer;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setIsSlownessInducing() {
    this.isSlownessInducing = true;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setIsSlownessInducing(int timer) {
    this.isSlownessInducing = true;
    this.slownessTimer = timer;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setFrosbiteTimer(int timer) {
    this.frostBiteTimer = timer;
    return (T)this;
  }

  
  public <T extends CoreSwordItem> T setIsSlownessInducing(int timer, boolean isStackable) {
    this.isSlownessInducing = true;
    this.slownessTimer = timer;
    this.isStackable = isStackable;
    
    return (T)this;
  }
  
  public <T extends CoreSwordItem> T setExtraAttackRange(double range) {
    this.range = range;
    return (T)this;
  }








  
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    try {
      return this.repairIngredient.test(repair);
    } catch (Exception ex) {
      ex.printStackTrace();
      
      return false;
    } 
  }
  public boolean usesIndependentImbuing() {
    return this.independentImbuing;
  }
  
  public void setIndependentImbuing() {
    this.independentImbuing = true;
  }
}


