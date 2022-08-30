/*     */ package xyz.pixelatedw.mineminenomi.abilities.karu;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.IngaZarashiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class IngaZarashiAbility extends ZoanAbility {
/*  35 */   public static final IngaZarashiAbility INSTANCE = new IngaZarashiAbility();
/*     */   
/*  37 */   private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("06141405-6e5c-4b98-a8f7-230e0ffb96bc");
/*  38 */   private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("7ddb710f-a497-4f64-b272-8fcc9955b401");
/*  39 */   private static final UUID REACH_MODIFIER_UUID = UUID.fromString("dc0d06d6-ffd6-49d8-b484-da232b78fd41");
/*     */   
/*  41 */   private Optional<KarmaAbility> karmaAbility = Optional.empty();
/*     */ 
/*     */   
/*     */   public IngaZarashiAbility() {
/*  45 */     super("Inga Zarashi", AbilityHelper.getDevilFruitCategory());
/*  46 */     setDescription("Increases your physical prowess depending on how much damage you have in your §2Karma counter§r\nDamage taken is converted into karma");
/*     */     
/*  48 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  49 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  50 */     this.onEndCooldownEvent = this::onEndCooldownEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  56 */     if (!super.onStartContinuityEvent(player)) {
/*  57 */       return false;
/*     */     }
/*  59 */     this.karmaAbility = Optional.of((KarmaAbility)AbilityDataCapability.get((LivingEntity)player).getUnlockedAbility((Ability)KarmaAbility.INSTANCE));
/*     */     
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/*  67 */     int cooldown = Math.min(240, (int)Math.round(this.continueTime / 20.0D) + 3);
/*  68 */     setMaxCooldown(cooldown);
/*     */     
/*  70 */     WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket(player, (Ability)this), (LivingEntity)player);
/*     */     
/*  72 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  73 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  75 */     props.setZoanPoint("");
/*     */     
/*  77 */     removeZoanModifiers(player);
/*     */     
/*  79 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/*  80 */     WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));
/*     */ 
/*     */     
/*  83 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/*  84 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/*  85 */     player.recalculateSize();
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEndCooldownEvent(PlayerEntity player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int time) {
/*  97 */     updateKarma(player, time);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateKarma(PlayerEntity player, int time) {
/* 102 */     if (time % 20 == 0 && this.karmaAbility.isPresent()) {
/*     */ 
/*     */       
/* 105 */       if (((KarmaAbility)this.karmaAbility.get()).getPrevKarma() != ((KarmaAbility)this.karmaAbility.get()).getKarma()) {
/*     */         
/* 107 */         for (Map.Entry<IAttribute, AttributeModifier> entry : (Iterable<Map.Entry<IAttribute, AttributeModifier>>)getAttributes().entries())
/*     */         {
/* 109 */           addZoanModifier(entry.getKey(), entry.getValue());
/*     */         }
/* 111 */         applyZoanModifiers(player);
/*     */ 
/*     */         
/* 114 */         MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/* 115 */         WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/* 116 */         player.recalculateSize();
/*     */         
/* 118 */         ((KarmaAbility)this.karmaAbility.get()).setPrevKarma(((KarmaAbility)this.karmaAbility.get()).getKarma());
/*     */       } 
/*     */       
/* 121 */       ((KarmaAbility)this.karmaAbility.get()).addKarma((LivingEntity)player, -(((KarmaAbility)this.karmaAbility.get()).getKarma() / 100.0F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Multimap<IAttribute, AttributeModifier> getAttributes() {
/* 127 */     HashMultimap hashMultimap = HashMultimap.create();
/*     */     
/* 129 */     float karma = ((KarmaAbility)this.karmaAbility.get()).getKarma();
/*     */     
/* 131 */     hashMultimap.put(SharedMonsterAttributes.ARMOR, (new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, (Ability)INSTANCE, "Karma Armor Modifier", Math.min(karma / 100.0F * 8.0F, 8.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
/* 132 */     hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE, (new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, (Ability)INSTANCE, "Karma Attack Modifier", Math.min(karma / 100.0F * 10.0F, 10.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
/* 133 */     AbilityAttributeModifier reachAttribute = (new AbilityAttributeModifier(REACH_MODIFIER_UUID, (Ability)INSTANCE, "Karma Reach Modifier", Math.min((karma / 100.0F) * 2.5D, 2.5D), AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 134 */     hashMultimap.put(PlayerEntity.REACH_DISTANCE, reachAttribute);
/* 135 */     hashMultimap.put(ModAttributes.ATTACK_RANGE, reachAttribute);
/*     */     
/* 137 */     return (Multimap<IAttribute, AttributeModifier>)hashMultimap;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 143 */     return isContinuous();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 149 */     return (ZoanInfo)IngaZarashiZoanInfo.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\karu\IngaZarashiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */