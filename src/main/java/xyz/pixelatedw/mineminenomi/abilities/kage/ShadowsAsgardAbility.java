/*     */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShadowsAsgardZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class ShadowsAsgardAbility extends ZoanAbility implements IExtraUpdateData {
/*  31 */   public static final ShadowsAsgardAbility INSTANCE = new ShadowsAsgardAbility();
/*     */   
/*  33 */   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("c9f0e77d-6cd6-498e-b032-6641daaa1081");
/*  34 */   private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("ad79a8f6-0e4e-4cfb-9df0-1ce833eee85c");
/*  35 */   private static final UUID REACH_MODIFIER_UUID = UUID.fromString("5d8c2020-c5f7-48c5-8825-b482bc8efff4");
/*     */   
/*     */   private static final int MAX_SHADOWS = 30;
/*     */   
/*  39 */   private int shadows = 0;
/*     */ 
/*     */   
/*     */   public ShadowsAsgardAbility() {
/*  43 */     super("Shadow's Asgard", AbilityHelper.getDevilFruitCategory());
/*  44 */     setDescription("By continuously absorbing Shadows the user's strenght increases as well as their size");
/*  45 */     setMaxCooldown(60.0D);
/*  46 */     setThreshold(60.0D);
/*     */     
/*  48 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  49 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  50 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  51 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*  52 */     this.onEndCooldownEvent = this::onEndCooldownEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/*  58 */     if (KageHelper.getAvailableShadows(player) <= 0) {
/*     */       
/*  60 */       player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/*  61 */       return false;
/*     */     } 
/*     */     
/*  64 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void duringContinuityEvent(PlayerEntity player, int time) {
/*  70 */     if (KageHelper.getAvailableShadows(player) <= 0) {
/*     */       
/*  72 */       player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
/*  73 */       endContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  78 */     if (time % 10 == 0 && this.shadows < 30) {
/*     */       
/*  80 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket(player, (Ability)this), (LivingEntity)player);
/*     */       
/*  82 */       for (Map.Entry<IAttribute, AttributeModifier> entry : (Iterable<Map.Entry<IAttribute, AttributeModifier>>)getAttributes().entries())
/*     */       {
/*  84 */         addZoanModifier(entry.getKey(), entry.getValue());
/*     */       }
/*  86 */       applyZoanModifiers(player);
/*     */ 
/*     */       
/*  89 */       MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/*  90 */       WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/*  91 */       player.recalculateSize();
/*     */       
/*  93 */       this.shadows++;
/*  94 */       KageHelper.removeShadows(player, 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     super.duringContinuityEvent(player, this.continueTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 105 */     return super.onEndContinuityEvent(player);
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int cooldown) {
/* 110 */     if (cooldown % 20 == 0)
/*     */     {
/* 112 */       if (this.shadows > 0) {
/* 113 */         this.shadows--;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void onEndCooldownEvent(PlayerEntity player) {
/* 119 */     this.shadows = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private Multimap<IAttribute, AttributeModifier> getAttributes() {
/* 124 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 126 */     hashMultimap.put(SharedMonsterAttributes.ARMOR, (new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Armor Modifier", Math.min(this.shadows / 30.0F * 5.0F, 5.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
/* 127 */     hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE, (new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Attack Modifier", Math.min(this.shadows / 30.0F * 5.0F, 5.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
/* 128 */     AbilityAttributeModifier reachAttribute = (new AbilityAttributeModifier(REACH_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Reach Modifier", Math.min((this.shadows / 30.0F) * 1.2D, 1.2D), AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 129 */     hashMultimap.put(PlayerEntity.REACH_DISTANCE, reachAttribute);
/* 130 */     hashMultimap.put(ModAttributes.ATTACK_RANGE, reachAttribute);
/*     */     
/* 132 */     return (Multimap<IAttribute, AttributeModifier>)hashMultimap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 138 */     return (isContinuous() || (isOnCooldown() && this.shadows > 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 144 */     return (ZoanInfo)ShadowsAsgardZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/* 150 */     CompoundNBT nbt = new CompoundNBT();
/* 151 */     nbt.putInt("shadows", this.shadows);
/* 152 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 158 */     this.shadows = nbt.getInt("shadows");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShadows() {
/* 163 */     return this.shadows;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\ShadowsAsgardAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */