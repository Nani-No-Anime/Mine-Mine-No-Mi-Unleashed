package xyz.pixelatedw.mineminenomi.api.abilities;


import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public abstract class ZoanAbility
  extends ContinuousAbility
  implements IParallelContinuousAbility, IMorphAbility
{
  private HashMap<IAttribute, AttributeModifier> zoanModifiers = new HashMap<>();
  private HashMap<IAttribute, AttributeModifier> emptyHandZoanModifier = new HashMap<>();

  
  public ZoanAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }









  
  protected void duringContinuityEvent(PlayerEntity player, int i) {
    this.emptyHandZoanModifier.forEach(player.getHeldItemMainhand().isEmpty() ? ((key, value) -> {
          if (player.getAttribute(key).getModifier(value.getID()) == null) {
            player.getAttribute(key).applyModifier(value.setSaved(false));
          }
        }) : ((key, value) -> player.getAttribute(key).removeModifier(value.setSaved(false))));
  }

  
  public boolean checkZoanTransformation(PlayerEntity player) {
    if (!player.world.isRemote) {
      
      double transformationHeight = 0.0D;
      double transformationWidth = 0.0D;
      boolean hasEmptySpace = true;
      
      if (getTransformation() instanceof xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo && getTransformation().getSizes() != null) {
        
        EntitySize size = (EntitySize)getTransformation().getSizes().get(Pose.STANDING);
        transformationHeight = size.height;
        transformationWidth = size.width;
      } 
      
      if (transformationHeight > 0.0D)
      {
        for (int i = 0; i < transformationHeight; i++) {
          
          BlockPos pos = player.getPosition().add(0, i, 0);
          if (player.world.isTopSolid(pos, (Entity)player)) {
            
            hasEmptySpace = false;
            
            break;
          } 
        } 
      }
      if (hasEmptySpace && transformationWidth > 0.0D)
      {
        for (int i = 0; i < transformationWidth; i++) {
          
          BlockPos pos1 = player.getPosition().add(i, 0, i);
          BlockPos pos2 = player.getPosition().add(-i, 0, -i);
          if (player.world.getBlockState(pos1).getMaterial().isSolid() && player.world.getBlockState(pos2).getMaterial().isSolid()) {
            
            hasEmptySpace = false;
            
            break;
          } 
        } 
      }
      if (!hasEmptySpace) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SPACE, new Object[] { getName() }));
        return false;
      } 
    } 
    
    return true;
  }

  
  protected boolean onStartContinuityEvent(PlayerEntity player) {
    if (!checkZoanTransformation(player)) {
      return false;
    }
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (props.getZoanPoint().isEmpty()) {
      props.setZoanPoint("");
    }
    applyZoanModifiers(player);

    
    setState(Ability.State.CONTINUOUS);
    
    props.setZoanPoint(getTransformation().getForm());
    WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
    WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));

    
    MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
    WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
    player.recalculateSize();
    
    return true;
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    if (!checkZoanTransformation(player)) {
      return false;
    }
    return true;
  }

  
  protected void onStopContinuityEvent(PlayerEntity player) {
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    props.setZoanPoint("");
    
    removeZoanModifiers(player);
    
    WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
    WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));

    
    MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
    WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
    player.recalculateSize();
  }

  
  protected void applyZoanModifiers(PlayerEntity player) {
    for (Map.Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet()) {
      
      player.getAttribute(entry.getKey()).removeModifier(((AttributeModifier)entry.getValue()).setSaved(false));
      player.getAttribute(entry.getKey()).applyModifier(((AttributeModifier)entry.getValue()).setSaved(false));
    } 
  }

  
  protected void removeZoanModifiers(PlayerEntity player) {
    for (Map.Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet()) {
      
      player.getAttribute(entry.getKey()).removeModifier(((AttributeModifier)entry.getValue()).setSaved(false));

      
      if (((IAttribute)entry.getKey()).equals(SharedMonsterAttributes.MAX_HEALTH)) {
        
        float leftHp = player.getHealth() - player.getMaxHealth();
        if (leftHp > 0.0F) {
          player.setHealth(player.getHealth() - leftHp);
        }
      } 
    } 
  }

  
  public boolean isTransformationActive(LivingEntity entity) {
    return isContinuous();
  }

  
  public HashMap<IAttribute, AttributeModifier> getZoanModifiers() {
    return this.zoanModifiers;
  }

  
  public void addZoanModifier(IAttribute attr, AttributeModifier modifier) {
    this.zoanModifiers.put(attr, modifier);
  }

  
  public void addEmptyHandZoanModifier(IAttribute attr, AttributeModifier modifier) {
    this.zoanModifiers.put(attr, modifier);
    this.emptyHandZoanModifier.put(attr, modifier);
  }
}


