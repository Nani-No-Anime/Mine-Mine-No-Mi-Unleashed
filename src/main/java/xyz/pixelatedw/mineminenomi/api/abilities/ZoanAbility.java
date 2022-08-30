/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public abstract class ZoanAbility
/*     */   extends ContinuousAbility
/*     */   implements IParallelContinuousAbility, IMorphAbility
/*     */ {
/*  38 */   private HashMap<IAttribute, AttributeModifier> zoanModifiers = new HashMap<>();
/*  39 */   private HashMap<IAttribute, AttributeModifier> emptyHandZoanModifier = new HashMap<>();
/*     */ 
/*     */   
/*     */   public ZoanAbility(String name, APIConfig.AbilityCategory category) {
/*  43 */     super(name, category);
/*     */     
/*  45 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  46 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  47 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*  48 */     this.onStopContinuityEvent = this::onStopContinuityEvent;
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
/*     */   protected void duringContinuityEvent(PlayerEntity player, int i) {
/*  61 */     this.emptyHandZoanModifier.forEach(player.getHeldItemMainhand().isEmpty() ? ((key, value) -> {
/*     */           if (player.getAttribute(key).getModifier(value.getID()) == null) {
/*     */             player.getAttribute(key).applyModifier(value.setSaved(false));
/*     */           }
/*     */         }) : ((key, value) -> player.getAttribute(key).removeModifier(value.setSaved(false))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkZoanTransformation(PlayerEntity player) {
/*  70 */     if (!player.world.isRemote) {
/*     */       
/*  72 */       double transformationHeight = 0.0D;
/*  73 */       double transformationWidth = 0.0D;
/*  74 */       boolean hasEmptySpace = true;
/*     */       
/*  76 */       if (getTransformation() instanceof xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo && getTransformation().getSizes() != null) {
/*     */         
/*  78 */         EntitySize size = (EntitySize)getTransformation().getSizes().get(Pose.STANDING);
/*  79 */         transformationHeight = size.height;
/*  80 */         transformationWidth = size.width;
/*     */       } 
/*     */       
/*  83 */       if (transformationHeight > 0.0D)
/*     */       {
/*  85 */         for (int i = 0; i < transformationHeight; i++) {
/*     */           
/*  87 */           BlockPos pos = player.getPosition().add(0, i, 0);
/*  88 */           if (player.world.isTopSolid(pos, (Entity)player)) {
/*     */             
/*  90 */             hasEmptySpace = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*  96 */       if (hasEmptySpace && transformationWidth > 0.0D)
/*     */       {
/*  98 */         for (int i = 0; i < transformationWidth; i++) {
/*     */           
/* 100 */           BlockPos pos1 = player.getPosition().add(i, 0, i);
/* 101 */           BlockPos pos2 = player.getPosition().add(-i, 0, -i);
/* 102 */           if (player.world.getBlockState(pos1).getMaterial().isSolid() && player.world.getBlockState(pos2).getMaterial().isSolid()) {
/*     */             
/* 104 */             hasEmptySpace = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 110 */       if (!hasEmptySpace) {
/*     */         
/* 112 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SPACE, new Object[] { getName() }));
/* 113 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onStartContinuityEvent(PlayerEntity player) {
/* 122 */     if (!checkZoanTransformation(player)) {
/* 123 */       return false;
/*     */     }
/* 125 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 126 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 128 */     if (props.getZoanPoint().isEmpty()) {
/* 129 */       props.setZoanPoint("");
/*     */     }
/* 131 */     applyZoanModifiers(player);
/*     */ 
/*     */     
/* 134 */     setState(Ability.State.CONTINUOUS);
/*     */     
/* 136 */     props.setZoanPoint(getTransformation().getForm());
/* 137 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/* 138 */     WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));
/*     */ 
/*     */     
/* 141 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/* 142 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/* 143 */     player.recalculateSize();
/*     */     
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 150 */     if (!checkZoanTransformation(player)) {
/* 151 */       return false;
/*     */     }
/* 153 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onStopContinuityEvent(PlayerEntity player) {
/* 158 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 159 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 161 */     props.setZoanPoint("");
/*     */     
/* 163 */     removeZoanModifiers(player);
/*     */     
/* 165 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/* 166 */     WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));
/*     */ 
/*     */     
/* 169 */     MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/* 170 */     WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/* 171 */     player.recalculateSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyZoanModifiers(PlayerEntity player) {
/* 176 */     for (Map.Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet()) {
/*     */       
/* 178 */       player.getAttribute(entry.getKey()).removeModifier(((AttributeModifier)entry.getValue()).setSaved(false));
/* 179 */       player.getAttribute(entry.getKey()).applyModifier(((AttributeModifier)entry.getValue()).setSaved(false));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeZoanModifiers(PlayerEntity player) {
/* 185 */     for (Map.Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet()) {
/*     */       
/* 187 */       player.getAttribute(entry.getKey()).removeModifier(((AttributeModifier)entry.getValue()).setSaved(false));
/*     */ 
/*     */       
/* 190 */       if (((IAttribute)entry.getKey()).equals(SharedMonsterAttributes.MAX_HEALTH)) {
/*     */         
/* 192 */         float leftHp = player.getHealth() - player.getMaxHealth();
/* 193 */         if (leftHp > 0.0F) {
/* 194 */           player.setHealth(player.getHealth() - leftHp);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 202 */     return isContinuous();
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<IAttribute, AttributeModifier> getZoanModifiers() {
/* 207 */     return this.zoanModifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addZoanModifier(IAttribute attr, AttributeModifier modifier) {
/* 212 */     this.zoanModifiers.put(attr, modifier);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEmptyHandZoanModifier(IAttribute attr, AttributeModifier modifier) {
/* 217 */     this.zoanModifiers.put(attr, modifier);
/* 218 */     this.emptyHandZoanModifier.put(attr, modifier);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ZoanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */