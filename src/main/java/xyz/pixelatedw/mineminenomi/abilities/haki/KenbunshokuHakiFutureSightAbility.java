package xyz.pixelatedw.mineminenomi.abilities.haki;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class KenbunshokuHakiFutureSightAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
  public static final KenbunshokuHakiFutureSightAbility INSTANCE = new KenbunshokuHakiFutureSightAbility();
  
  public float maxProtection = 0.0F;
  protected float protection = 0.0F;

  
  public KenbunshokuHakiFutureSightAbility() {
    super("Kenbunshoku Haki: Future Sight", AbilityHelper.getHakiCategory());
    setDescription("Using Observation Haki allows the user to see a short period into the future to avoid attacks");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!HakiHelper.canEnableHaki(player)) {
      return false;
    }
    this.protection = this.maxProtection = calculateMaxProtection(player);
    player.world.playSound(null, player.getPosition(), ModSounds.KENBUNSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 2);
    
    if (isOnMaxOveruse || 0.0F > this.protection) {
      
      int time = Math.round(40.0F + 60.0F * (1.0F - this.maxProtection / 200.0F));
      setMaxCooldown(time);
      endContinuity(player);
    } 
    
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    
    if (hakiProps.getHakiOveruse() == 0 && this.maxProtection > this.protection) {
      this.protection = Math.min(this.protection + 5.0F, this.maxProtection);
    }
  }
  
  private float calculateMaxProtection(PlayerEntity player) {
    IEntityStats sprops = EntityStatsCapability.get((LivingEntity)player);
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
    
    float dorikiPower = sprops.getDoriki() / 1000.0F;
    float hakiPower = hakiProps.getKenbunshokuHakiExp() * 0.75F;
    float percentageOveruse = 1.0F - hakiProps.getHakiOveruse() / HakiHelper.getMaxOveruse(player) / 2.0F;
    
    return (15.0F + dorikiPower + hakiPower) * percentageOveruse;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (this.protection > 0.0F)
      setMaxCooldown((5.0F + 30.0F * (1.0F - this.protection / 200.0F))); 
    return true;
  }

  
  public void reduceProtection(float amount) {
    this.protection -= amount;
  }


  
  public HakiType getType() {
    return HakiType.KENBUNSHOKU;
  }
}


