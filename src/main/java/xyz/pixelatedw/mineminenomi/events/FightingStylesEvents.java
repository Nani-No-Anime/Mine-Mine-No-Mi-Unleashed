/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
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
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.LogicalSide;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FightingStyleHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class FightingStylesEvents
/*     */ {
/*  38 */   private static final UUID SWORDSMAN_BONUS_ID = UUID.fromString("4b706e44-2567-4019-9b9c-747fa53bb05d");
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemChange(LivingEquipmentChangeEvent event) {
/*  43 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/*  45 */     ItemStack stack = event.getTo();
/*  46 */     if (!stack.isEmpty() && ItemsHelper.isSword(stack))
/*     */     {
/*  48 */       if (EntityStatsCapability.get(entity).isSwordsman()) {
/*     */         
/*  50 */         Multimap<String, AttributeModifier> map = stack.getItem().getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
/*  51 */         String key = SharedMonsterAttributes.ATTACK_DAMAGE.getName();
/*     */         
/*  53 */         AttributeModifier mod = map.get(key).stream().filter(m -> m.getID().equals(SWORDSMAN_BONUS_ID)).findFirst().orElse(null);
/*  54 */         if (mod == null) {
/*  55 */           map.put(key, new AttributeModifier(SWORDSMAN_BONUS_ID, "Swordsman Bonus", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE));
/*     */         }
/*  57 */         for (Map.Entry<String, AttributeModifier> entry : (Iterable<Map.Entry<String, AttributeModifier>>)map.entries()) {
/*     */           
/*  59 */           if (!stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).containsEntry(entry.getKey(), entry.getValue())) {
/*  60 */             stack.addAttributeModifier(entry.getKey(), entry.getValue(), EquipmentSlotType.MAINHAND);
/*     */           }
/*     */         } 
/*  63 */       } else if (stack.hasTag()) {
/*     */         
/*  65 */         ListNBT listnbt = stack.getTag().getList("AttributeModifiers", 10);
/*     */         
/*  67 */         for (int i = 0; i < listnbt.size(); i++) {
/*     */           
/*  69 */           CompoundNBT compoundnbt = listnbt.getCompound(i);
/*  70 */           AttributeModifier mod = SharedMonsterAttributes.readAttributeModifier(compoundnbt);
/*  71 */           if (mod.getID().equals(SWORDSMAN_BONUS_ID)) {
/*  72 */             listnbt.remove(i);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void onTooltipRender(ItemTooltipEvent event) {
/*  82 */     PlayerEntity player = event.getPlayer();
/*     */     
/*  84 */     if (player == null) {
/*     */       return;
/*     */     }
/*  87 */     if (ItemsHelper.isSword(event.getItemStack()) && EntityStatsCapability.get((LivingEntity)event.getPlayer()).isSwordsman()) {
/*     */       
/*  89 */       StringTextComponent damageBonus = new StringTextComponent(TextFormatting.GREEN + "" + (new TranslationTextComponent(ModI18n.ITEM_SWORDSMAN_BONUS, new Object[0])).getFormattedText());
/*  90 */       if (!event.getToolTip().contains(damageBonus)) {
/*     */         
/*  92 */         event.getToolTip().add(new StringTextComponent(""));
/*  93 */         event.getToolTip().add(damageBonus);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/* 101 */     if (event.getEntityStats().isBlackLeg())
/* 102 */       FightingStyleHelper.applyBlackLegModifiers(event.getPlayer()); 
/* 103 */     if (event.getEntityStats().isBrawler()) {
/* 104 */       FightingStyleHelper.applyBrawlerModifiers(event.getPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void edgeCasesChecks(TickEvent.PlayerTickEvent event) {
/* 110 */     if (event.phase != TickEvent.Phase.START || event.side != LogicalSide.SERVER || event.player.ticksExisted % 5 != 0) {
/*     */       return;
/*     */     }
/* 113 */     PlayerEntity player = event.player;
/* 114 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 115 */     boolean isHandEmpty = player.getHeldItemMainhand().isEmpty();
/*     */     
/* 117 */     if (props.isBlackLeg()) {
/*     */       
/* 119 */       if (!isHandEmpty && FightingStyleHelper.hasBlackLegModifiers(player)) {
/* 120 */         FightingStyleHelper.removeBlackLegModifiers(player);
/* 121 */       } else if (isHandEmpty && !FightingStyleHelper.hasBlackLegModifiers(player)) {
/* 122 */         FightingStyleHelper.applyBlackLegModifiers(player);
/*     */       } 
/* 124 */     } else if (props.isBrawler()) {
/*     */       
/* 126 */       if (!isHandEmpty && FightingStyleHelper.hasBrawlerModifiers(player)) {
/* 127 */         FightingStyleHelper.removeBrawlerModifiers(player);
/* 128 */       } else if (isHandEmpty && !FightingStyleHelper.hasBrawlerModifiers(player)) {
/* 129 */         FightingStyleHelper.applyBrawlerModifiers(player);
/*     */       } 
/*     */     } else {
/* 132 */       if (FightingStyleHelper.hasBrawlerModifiers(player)) {
/* 133 */         FightingStyleHelper.removeBrawlerModifiers(player);
/*     */       }
/* 135 */       if (FightingStyleHelper.hasBlackLegModifiers(player))
/* 136 */         FightingStyleHelper.removeBlackLegModifiers(player); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\FightingStylesEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */