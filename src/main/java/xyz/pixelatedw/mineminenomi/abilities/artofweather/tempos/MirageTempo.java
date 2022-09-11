package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;

public class MirageTempo extends TempoAbility {
  public static final MirageTempo INSTANCE = new MirageTempo();

  
  public MirageTempo() {
    super("Mirage Tempo", AbilityHelper.getStyleCategory());
    setDescription("2 Charged Cool Ball + Charged Heat Ball\nCreates several clones of the user");
    setCustomTexture("tempo");
    setMaxCooldown(10.0D);
    
    this.onUseEvent = this::onUseEvent;
    this.canUseCheck = this::canUseCheck;
  }

  
  public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) {
    if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
      
      ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
      String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
      return tempoCombo.equalsIgnoreCase("CCH");
    } 
    
    return false;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
    smokeCloud.setLife(50);
    smokeCloud.setLocationAndAngles(player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0F, 0.0F);
    smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
    smokeCloud.setThrower((LivingEntity)player);
    player.world.addEntity((Entity)smokeCloud);
    
    for (int i = 0; i < 5; i++) {
      
      MirageCloneEntity mirageClone = new MirageCloneEntity(player.world);
      mirageClone.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), 180.0F, 0.0F);
      mirageClone.setOwner(player.getUniqueID());
      for (EquipmentSlotType slot : EquipmentSlotType.values()) {
        
        ItemStack stack = player.getItemStackFromSlot(slot);
        mirageClone.setItemStackToSlot(slot, stack);
      } 
      player.world.addEntity((Entity)mirageClone);
    } 
    
    return true;
  }
}


