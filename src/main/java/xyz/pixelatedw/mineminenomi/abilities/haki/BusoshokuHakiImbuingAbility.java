/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ 
/*     */ public class BusoshokuHakiImbuingAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
/*  35 */   public static final BusoshokuHakiImbuingAbility INSTANCE = new BusoshokuHakiImbuingAbility();
/*     */   
/*  37 */   public static final UUID IMBUING_BONUS_ID = UUID.fromString("741b8582-fce1-4485-9460-0f3320632729");
/*     */ 
/*     */   
/*     */   public BusoshokuHakiImbuingAbility() {
/*  41 */     super("Busoshoku Haki: Imbuing", AbilityHelper.getHakiCategory());
/*  42 */     setDescription("Covers the weapon of the user in Armament haki, making their weapon attacks stronger and being able to damage Logia users");
/*  43 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  44 */     this.duringContinuityEvent = this::duringContinuity;
/*  45 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  51 */     if (player.getHeldItemMainhand().getItem() == ModWeapons.ENMA && isContinuous()) {
/*     */       return;
/*     */     }
/*  54 */     super.use(player);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  59 */     if (!HakiHelper.canUseHaki(player, (Ability)this)) {
/*     */       
/*  61 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONE_HAKI_TYPE, new Object[] { getName() }));
/*  62 */       return false;
/*     */     } 
/*     */     
/*  65 */     if (!HakiHelper.canEnableHaki(player)) {
/*  66 */       return false;
/*     */     }
/*  68 */     applyImbuingBonus((LivingEntity)player, player.getHeldItemMainhand());
/*     */     
/*  70 */     player.world.playSound(null, player.getPosition(), ModSounds.BUSOSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/*  77 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 0);
/*  78 */     if (isOnMaxOveruse) {
/*  79 */       endContinuity(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  84 */     int cooldown = (int)WyHelper.clamp(Math.round(this.continueTime / 30.0D), 10L, 60L);
/*  85 */     setMaxCooldown(cooldown);
/*     */     
/*  87 */     Iterable<ItemStack> inventories = Iterables.concat((Iterable)player.inventory.mainInventory, (Iterable)player.inventory.armorInventory, (Iterable)player.inventory.offHandInventory);
/*  88 */     for (ItemStack stack : inventories) {
/*     */       
/*  90 */       if (!stack.isEmpty() && stack.isDamageable() && !(stack.getItem()).properties.containsKey(new ResourceLocation("haki")) && stack.getOrCreateTag().getBoolean("imbuingHakiActive")) {
/*  91 */         stack.getOrCreateTag().remove("imbuingHakiActive");
/*     */       }
/*     */     } 
/*  94 */     removeImbuingBonus((LivingEntity)player);
/*     */     
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getType() {
/* 102 */     return HakiType.IMBUING;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void tryApplyingImbuingBonus(LivingEntity entity, ItemStack itemStack) {
/* 107 */     boolean hasImbuing = HakiHelper.hasImbuingActive(entity);
/* 108 */     if (hasImbuing) {
/* 109 */       applyImbuingBonus(entity, itemStack);
/*     */     } else {
/* 111 */       removeImbuingBonus(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void applyImbuingBonus(LivingEntity entity, ItemStack itemStack) {
/* 116 */     if (itemStack == null || itemStack.isEmpty() || !HakiHelper.canItemGainImbuing(itemStack)) {
/*     */       return;
/*     */     }
/* 119 */     double bonus = 1.0D;
/* 120 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 122 */       IHakiData hakiProps = HakiDataCapability.get(entity);
/* 123 */       bonus = (hakiProps.getBusoshokuImbuingHakiExp() / 100.0F);
/*     */     } 
/* 125 */     Multimap<String, AttributeModifier> map = itemStack.getItem().getAttributeModifiers(EquipmentSlotType.MAINHAND, itemStack);
/* 126 */     String key = SharedMonsterAttributes.ATTACK_DAMAGE.getName();
/*     */     
/* 128 */     AttributeModifier mod = map.get(key).stream().filter(m -> m.getID().equals(IMBUING_BONUS_ID)).findFirst().orElse(null);
/* 129 */     if (mod == null) {
/* 130 */       map.put(key, new AttributeModifier(IMBUING_BONUS_ID, "Imbuing Haki Bonus", bonus, AttributeModifier.Operation.MULTIPLY_BASE));
/*     */     }
/* 132 */     for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)map.entries()) {
/*     */       
/* 134 */       if (!itemStack.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsEntry(entry.getKey(), entry.getValue())) {
/* 135 */         itemStack.addAttributeModifier(entry.getKey(), entry.getValue(), EquipmentSlotType.MAINHAND);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void removeImbuingBonus(LivingEntity entity) {
/* 141 */     Iterable<ItemStack> list = entity.getEquipmentAndArmor();
/*     */     
/* 143 */     for (ItemStack itemStack : list) {
/*     */       
/* 145 */       if (itemStack == null || itemStack.isEmpty() || !itemStack.hasTag()) {
/*     */         continue;
/*     */       }
/* 148 */       ListNBT listnbt = itemStack.getTag().getList("AttributeModifiers", 10);
/*     */       
/* 150 */       for (int i = 0; i < listnbt.size(); i++) {
/*     */         
/* 152 */         CompoundNBT compoundnbt = listnbt.getCompound(i);
/* 153 */         AttributeModifier mod = SharedMonsterAttributes.readAttributeModifier(compoundnbt);
/* 154 */         if (mod.getID().equals(IMBUING_BONUS_ID))
/* 155 */           listnbt.remove(i); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiImbuingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */