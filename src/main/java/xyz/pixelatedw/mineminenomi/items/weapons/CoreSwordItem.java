/*     */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.IItemTier;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.SwordItem;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class CoreSwordItem extends SwordItem {
/*  36 */   public double damage = 1.0D;
/*     */   
/*  38 */   public double conductivity = 0.1D;
/*  39 */   public double range = 0.0D; protected boolean isPoisonous = false; protected boolean isFireAspect = false; protected boolean isSlownessInducing = false;
/*     */   protected boolean isStackable = false;
/*  41 */   protected int poisonTimer = 100; protected int fireAspectTimer = 10; protected int slownessTimer = 100; protected int frostBiteTimer = 0;
/*     */   
/*     */   private boolean isBlunt = false;
/*     */   private boolean rustImmunity = false;
/*     */   private boolean independentImbuing = false;
/*     */   private Ingredient repairIngredient;
/*  47 */   protected static final UUID ATTACK_RANGE_MODIFIER = UUID.fromString("06256896-00c1-45b4-bc71-514ee36310bd");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IItemPropertyGetter hakiProperty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IItemPropertyGetter sheathedProperty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoreSwordItem(Item.Properties props, int damage, float attackSpeed) {
/*  88 */     super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, props); this.hakiProperty = ((itemStack, world, livingEntity) -> { 
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
/*  89 */           return 1.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() != itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() != itemStack); return (mainHandFlag && offHandFlag) ? 1.0F : 0.0F; }); this.damage = (damage - 1);
/*  90 */     addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
/*  91 */     addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
/*     */   }
/*     */   
/*     */   public CoreSwordItem(int damage, int durability) {
/*  95 */     this(damage, -2.4F, durability);
/*  96 */     this.damage = (damage - 1);
/*  97 */     addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
/*  98 */     addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
/*     */   }
/*     */   
/*     */   public CoreSwordItem(int damage, float attackSpeed, int durability) {
/* 102 */     super((IItemTier)AbilityItemTier.WEAPON, damage, attackSpeed, (new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(durability)); this.hakiProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null) return 0.0F;  float hasHakiActive = 0.0F; if (livingEntity instanceof PlayerEntity) { IAbilityData props = AbilityDataCapability.get(livingEntity); boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() == itemStack); BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)props.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE); boolean hakiActiveFlag = (ability != null && ability.isContinuous()); hasHakiActive = ((mainHandFlag || offHandFlag) && hakiActiveFlag) ? 1.0F : 0.0F; } else if (livingEntity instanceof OPEntity) { hasHakiActive = ((OPEntity)livingEntity).hasBusoHaki() ? 1.0F : 0.0F; }  return hasHakiActive; }); this.sheathedProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
/* 103 */           return 1.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() != itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() != itemStack); return (mainHandFlag && offHandFlag) ? 1.0F : 0.0F; }); this.damage = (damage - 1);
/* 104 */     addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
/* 105 */     addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
/* 123 */     if (!world.isRemote) {
/*     */       
/* 125 */       itemStack.getOrCreateTag();
/* 126 */       if (itemStack.getTag().getBoolean("isClone") && !itemStack.getTag().getBoolean("hasCloneTag")) {
/*     */         
/* 128 */         itemStack.setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + itemStack.getDisplayName().getFormattedText() + " (Replica)"));
/* 129 */         itemStack.getTag().putBoolean("hasCloneTag", true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 137 */     return new ActionResult(ActionResultType.PASS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 143 */     return 14;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/* 149 */     IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/*     */     
/* 151 */     BusoshokuHakiImbuingAbility ability = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility((Ability)BusoshokuHakiImbuingAbility.INSTANCE);
/* 152 */     boolean hasBusoHaki = (ability != null && ability.isContinuous());
/*     */     
/* 154 */     if (!hasBusoHaki) {
/*     */       
/* 156 */       int damage = itemStack.getOrCreateTag().getBoolean("isClone") ? 3 : 1;
/* 157 */       itemStack.damageItem(damage, attacker, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (hasBusoHaki || this.isBlunt) {
/*     */       
/* 165 */       Item mainShield = target.getHeldItemMainhand().getItem();
/* 166 */       Item secondaryShield = target.getHeldItemOffhand().getItem();
/* 167 */       if (target instanceof PlayerEntity && Math.random() > 0.5D && (mainShield.equals(Items.SHIELD) || secondaryShield.equals(Items.SHIELD))) {
/*     */         
/* 169 */         ((PlayerEntity)target).getCooldownTracker().setCooldown(Items.SHIELD, 100);
/* 170 */         target.resetActiveHand();
/* 171 */         target.world.setEntityState((Entity)attacker, (byte)30);
/*     */       } 
/*     */     } 
/*     */     
/* 175 */     if (this.isPoisonous) {
/* 176 */       target.addPotionEffect(new EffectInstance(Effects.POISON, this.poisonTimer, 0));
/*     */     }
/* 178 */     if (this.isFireAspect) {
/*     */       
/* 180 */       SetOnFireEvent event = new SetOnFireEvent(attacker, target, this.fireAspectTimer);
/* 181 */       if (!MinecraftForge.EVENT_BUS.post(event)) {
/* 182 */         target.setFire(this.fireAspectTimer);
/*     */       }
/*     */     } 
/* 185 */     if (this.isSlownessInducing)
/*     */     {
/* 187 */       if (this.isStackable) {
/*     */         
/* 189 */         if (target.isPotionActive(Effects.SLOWNESS)) {
/*     */           
/* 191 */           int timer = target.getActivePotionEffect(Effects.SLOWNESS).getDuration();
/* 192 */           target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, timer + this.slownessTimer, 0));
/*     */         } else {
/*     */           
/* 195 */           target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
/*     */         } 
/*     */       } else {
/* 198 */         target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
/*     */       } 
/*     */     }
/* 201 */     if (this.frostBiteTimer > 0) {
/* 202 */       AbilityHelper.addFrostbite(target, attacker, this.frostBiteTimer);
/*     */     }
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsPoisonous() {
/* 209 */     this.isPoisonous = true;
/* 210 */     this.poisonTimer = 100;
/* 211 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsPoisonous(int timer) {
/* 216 */     this.isPoisonous = true;
/* 217 */     this.poisonTimer = timer;
/* 218 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setConductivity(double conductivity) {
/* 227 */     this.conductivity = conductivity;
/* 228 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setRepairIngredient(Ingredient ingredient) {
/* 233 */     this.repairIngredient = ingredient;
/* 234 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Ingredient getRepairIngredient() {
/* 239 */     return this.repairIngredient;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setBlunt() {
/* 244 */     this.isBlunt = true;
/* 245 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setSharp() {
/* 250 */     this.isBlunt = false;
/* 251 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlunt() {
/* 256 */     return this.isBlunt;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setRustImmunity() {
/* 261 */     this.rustImmunity = true;
/* 262 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRustImmune() {
/* 267 */     return this.rustImmunity;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsFireAspect() {
/* 272 */     this.isFireAspect = true;
/* 273 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsFireAspect(int timer) {
/* 278 */     this.isFireAspect = true;
/* 279 */     this.fireAspectTimer = timer;
/* 280 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsSlownessInducing() {
/* 285 */     this.isSlownessInducing = true;
/* 286 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsSlownessInducing(int timer) {
/* 291 */     this.isSlownessInducing = true;
/* 292 */     this.slownessTimer = timer;
/* 293 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setFrosbiteTimer(int timer) {
/* 298 */     this.frostBiteTimer = timer;
/* 299 */     return (T)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends CoreSwordItem> T setIsSlownessInducing(int timer, boolean isStackable) {
/* 304 */     this.isSlownessInducing = true;
/* 305 */     this.slownessTimer = timer;
/* 306 */     this.isStackable = isStackable;
/*     */     
/* 308 */     return (T)this;
/*     */   }
/*     */   
/*     */   public <T extends CoreSwordItem> T setExtraAttackRange(double range) {
/* 312 */     this.range = range;
/* 313 */     return (T)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/*     */     try {
/* 326 */       return this.repairIngredient.test(repair);
/* 327 */     } catch (Exception ex) {
/* 328 */       ex.printStackTrace();
/*     */       
/* 330 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean usesIndependentImbuing() {
/* 334 */     return this.independentImbuing;
/*     */   }
/*     */   
/*     */   public void setIndependentImbuing() {
/* 338 */     this.independentImbuing = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\CoreSwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */