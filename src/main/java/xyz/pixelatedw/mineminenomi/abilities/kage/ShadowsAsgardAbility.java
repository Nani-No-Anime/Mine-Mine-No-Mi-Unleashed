package xyz.pixelatedw.mineminenomi.abilities.kage;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.KageHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShadowsAsgardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class ShadowsAsgardAbility extends ZoanAbility implements IExtraUpdateData {
  public static final ShadowsAsgardAbility INSTANCE = new ShadowsAsgardAbility();
  
  private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("c9f0e77d-6cd6-498e-b032-6641daaa1081");
  private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("ad79a8f6-0e4e-4cfb-9df0-1ce833eee85c");
  private static final UUID REACH_MODIFIER_UUID = UUID.fromString("5d8c2020-c5f7-48c5-8825-b482bc8efff4");
  
  private static final int MAX_SHADOWS = 30;
  
  private int shadows = 0;

  
  public ShadowsAsgardAbility() {
    super("Shadow's Asgard", AbilityHelper.getDevilFruitCategory());
    setDescription("By continuously absorbing Shadows the user's strenght increases as well as their size");
    setMaxCooldown(60.0D);
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.duringCooldownEvent = this::duringCooldownEvent;
    this.onEndCooldownEvent = this::onEndCooldownEvent;
  }


  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (KageHelper.getAvailableShadows(player) <= 0) {
      
      player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
      return false;
    } 
    
    return super.onStartContinuityEvent(player);
  }


  
  protected void duringContinuityEvent(PlayerEntity player, int time) {
    if (KageHelper.getAvailableShadows(player) <= 0) {
      
      player.sendMessage(KageHelper.NOT_ENOUGH_SHADOWS_WARN);
      endContinuity(player);
      
      return;
    } 
    
    if (time % 10 == 0 && this.shadows < 30) {
      
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket(player, (Ability)this), (LivingEntity)player);
      
      for (Map.Entry<IAttribute, AttributeModifier> entry : (Iterable<Map.Entry<IAttribute, AttributeModifier>>)getAttributes().entries())
      {
        addZoanModifier(entry.getKey(), entry.getValue());
      }
      applyZoanModifiers(player);

      
      MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
      WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
      player.recalculateSize();
      
      this.shadows++;
      KageHelper.removeShadows(player, 1);
    } 


    
    super.duringContinuityEvent(player, this.continueTime);
  }


  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    return super.onEndContinuityEvent(player);
  }

  
  private void duringCooldownEvent(PlayerEntity player, int cooldown) {
    if (cooldown % 20 == 0)
    {
      if (this.shadows > 0) {
        this.shadows--;
      }
    }
  }
  
  private void onEndCooldownEvent(PlayerEntity player) {
    this.shadows = 0;
  }

  
  private Multimap<IAttribute, AttributeModifier> getAttributes() {
    HashMultimap hashMultimap = HashMultimap.create();
    
    hashMultimap.put(SharedMonsterAttributes.ARMOR, (new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Armor Modifier", Math.min(this.shadows / 30.0F * 5.0F, 5.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
    hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE, (new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Attack Modifier", Math.min(this.shadows / 30.0F * 5.0F, 5.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
    AbilityAttributeModifier reachAttribute = (new AbilityAttributeModifier(REACH_MODIFIER_UUID, (Ability)INSTANCE, "Shadows Asgard Reach Modifier", Math.min((this.shadows / 30.0F) * 1.2D, 1.2D), AttributeModifier.Operation.ADDITION)).setSaved(false);
    hashMultimap.put(PlayerEntity.REACH_DISTANCE, reachAttribute);
    hashMultimap.put(ModAttributes.ATTACK_RANGE, reachAttribute);
    
    return (Multimap<IAttribute, AttributeModifier>)hashMultimap;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return (isContinuous() || (isOnCooldown() && this.shadows > 0));
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)ShadowsAsgardZoanInfo.INSTANCE;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putInt("shadows", this.shadows);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.shadows = nbt.getInt("shadows");
  }

  
  public int getShadows() {
    return this.shadows;
  }
}


