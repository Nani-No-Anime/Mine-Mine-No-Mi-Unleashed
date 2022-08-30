/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkGibsonZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class PunkGibsonAbility extends ZoanAbility {
/*  26 */   public static final PunkGibsonAbility INSTANCE = new PunkGibsonAbility();
/*     */   
/*     */   private static final int MAX_ITEMS = 100;
/*     */   
/*  30 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*     */   
/*     */   private boolean dropItems = true;
/*     */   
/*     */   public PunkGibsonAbility() {
/*  35 */     super("Punk Gibson", AbilityHelper.getDevilFruitCategory());
/*  36 */     setDescription("Uses §2100§r magnetic items from the user's inventory to create a large arm increasing their punch power and reach.");
/*  37 */     setThreshold(120.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  43 */     if (!this.magneticItems.isEmpty()) {
/*  44 */       this.magneticItems.clear();
/*     */     }
/*  46 */     List<ItemStack> originals = JikiHelper.getMagneticItems(player, 100);
/*     */     
/*  48 */     int count = JikiHelper.countMagneticItems(originals);
/*  49 */     if (count < 100) {
/*     */       
/*  51 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_MATERIALS, new Object[0]));
/*  52 */       endContinuity(player);
/*  53 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  57 */     this.magneticItems = JikiHelper.getMagneticItemsStack(player, originals, 100);
/*     */     
/*  59 */     double size = MathHelper.clamp(count, 10, 100);
/*  60 */     double powerAmount = 6.0D * size / 100.0D;
/*  61 */     double reachAmount = 2.0D * size / 100.0D;
/*     */     
/*  63 */     AbilityAttributeModifier powerModifier = new AbilityAttributeModifier(UUID.randomUUID(), (Ability)INSTANCE, "Punk Gibson Strength Modifier", powerAmount, AttributeModifier.Operation.ADDITION);
/*  64 */     AbilityAttributeModifier reachModifier = new AbilityAttributeModifier(UUID.randomUUID(), (Ability)INSTANCE, "Punk Gibson Reach Modifier", reachAmount, AttributeModifier.Operation.ADDITION);
/*     */     
/*  66 */     addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)powerModifier);
/*  67 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)reachModifier);
/*  68 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)reachModifier);
/*     */     
/*  70 */     this.dropItems = true;
/*     */     
/*  72 */     AttractAbility.PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/*  74 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/*  80 */     if (!super.onEndContinuityEvent(player) || this.magneticItems.size() <= 0) {
/*  81 */       return false;
/*     */     }
/*  83 */     if (this.dropItems) {
/*     */       
/*  85 */       ItemStack stack = this.magneticItems.get(player.getRNG().nextInt(this.magneticItems.size() - 1));
/*  86 */       ItemsHelper.itemBreakParticles((LivingEntity)player, 100, stack);
/*  87 */       player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, player.getSoundCategory(), 1.0F, 1.0F);
/*  88 */       JikiHelper.dropComponentItems(player, player.getPosition(), this.magneticItems);
/*     */     } 
/*     */     
/*  91 */     int cooldown = 3 + (int)Math.round(this.continueTime / 20.0D);
/*  92 */     setMaxCooldown(cooldown);
/*     */     
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopItemDrops() {
/*  99 */     this.dropItems = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ItemStack> getMagneticItems() {
/* 104 */     return this.magneticItems;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 110 */     return (ZoanInfo)PunkGibsonZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 116 */     if (!super.isTransformationActive(entity)) {
/* 117 */       return false;
/*     */     }
/* 119 */     DamnedPunkAbility abl = (DamnedPunkAbility)AbilityDataCapability.get(entity).getEquippedAbility((Ability)DamnedPunkAbility.INSTANCE);
/*     */     
/* 121 */     return (abl == null || !abl.isContinuous());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkGibsonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */