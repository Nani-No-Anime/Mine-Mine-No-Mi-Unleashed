package xyz.pixelatedw.mineminenomi.abilities.karu;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.IngaZarashiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class IngaZarashiAbility extends ZoanAbility {
  public static final IngaZarashiAbility INSTANCE = new IngaZarashiAbility();
  
  private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("06141405-6e5c-4b98-a8f7-230e0ffb96bc");
  private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("7ddb710f-a497-4f64-b272-8fcc9955b401");
  private static final UUID REACH_MODIFIER_UUID = UUID.fromString("dc0d06d6-ffd6-49d8-b484-da232b78fd41");
  
  private Optional<KarmaAbility> karmaAbility = Optional.empty();

  
  public IngaZarashiAbility() {
    super("Inga Zarashi", AbilityHelper.getDevilFruitCategory());
    setDescription("Increases your physical prowess depending on how much damage you have in your §2Karma counter§r\nDamage taken is converted into karma");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndCooldownEvent = this::onEndCooldownEvent;
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    this.karmaAbility = Optional.of((KarmaAbility)AbilityDataCapability.get((LivingEntity)player).getUnlockedAbility((Ability)KarmaAbility.INSTANCE));
    
    return true;
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = Math.min(240, (int)Math.round(this.continueTime / 20.0D) + 3);
    setMaxCooldown(cooldown);
    
    WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket(player, (Ability)this), (LivingEntity)player);
    
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    props.setZoanPoint("");
    
    removeZoanModifiers(player);
    
    WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
    WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));

    
    MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
    WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
    player.recalculateSize();
    
    return true;
  }


  
  public void onEndCooldownEvent(PlayerEntity player) {}


  
  public void duringContinuityEvent(PlayerEntity player, int time) {
    updateKarma(player, time);
  }

  
  private void updateKarma(PlayerEntity player, int time) {
    if (time % 20 == 0 && this.karmaAbility.isPresent()) {

      
      if (((KarmaAbility)this.karmaAbility.get()).getPrevKarma() != ((KarmaAbility)this.karmaAbility.get()).getKarma()) {
        
        for (Map.Entry<IAttribute, AttributeModifier> entry : (Iterable<Map.Entry<IAttribute, AttributeModifier>>)getAttributes().entries())
        {
          addZoanModifier(entry.getKey(), entry.getValue());
        }
        applyZoanModifiers(player);

        
        MinecraftForge.EVENT_BUS.post((Event)new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
        WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
        player.recalculateSize();
        
        ((KarmaAbility)this.karmaAbility.get()).setPrevKarma(((KarmaAbility)this.karmaAbility.get()).getKarma());
      } 
      
      ((KarmaAbility)this.karmaAbility.get()).addKarma((LivingEntity)player, -(((KarmaAbility)this.karmaAbility.get()).getKarma() / 100.0F));
    } 
  }

  
  private Multimap<IAttribute, AttributeModifier> getAttributes() {
    HashMultimap hashMultimap = HashMultimap.create();
    
    float karma = ((KarmaAbility)this.karmaAbility.get()).getKarma();
    
    hashMultimap.put(SharedMonsterAttributes.ARMOR, (new AbilityAttributeModifier(ARMOR_MODIFIER_UUID, (Ability)INSTANCE, "Karma Armor Modifier", Math.min(karma / 100.0F * 8.0F, 8.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
    hashMultimap.put(SharedMonsterAttributes.ATTACK_DAMAGE, (new AbilityAttributeModifier(ATTACK_MODIFIER_UUID, (Ability)INSTANCE, "Karma Attack Modifier", Math.min(karma / 100.0F * 10.0F, 10.0F), AttributeModifier.Operation.ADDITION)).setSaved(false));
    AbilityAttributeModifier reachAttribute = (new AbilityAttributeModifier(REACH_MODIFIER_UUID, (Ability)INSTANCE, "Karma Reach Modifier", Math.min((karma / 100.0F) * 2.5D, 2.5D), AttributeModifier.Operation.ADDITION)).setSaved(false);
    hashMultimap.put(PlayerEntity.REACH_DISTANCE, reachAttribute);
    hashMultimap.put(ModAttributes.ATTACK_RANGE, reachAttribute);
    
    return (Multimap<IAttribute, AttributeModifier>)hashMultimap;
  }


  
  public boolean isTransformationActive(LivingEntity entity) {
    return isContinuous();
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)IngaZarashiZoanInfo.INSTANCE;
  }
}


