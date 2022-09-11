package xyz.pixelatedw.mineminenomi.abilities.goro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class ShinzoMassageAbility extends PassiveAbility implements IExtraUpdateData {
  public static final ShinzoMassageAbility INSTANCE = new ShinzoMassageAbility();
  private double strain = 0.0D;
  private int healTicks = 0;
  private int outTicks = 0;

  
  public ShinzoMassageAbility() {
    super("Shinzo Massage", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(60.0D);
    setDescription("The user restarts their own heart after dying using lightning powers");
    hideInGUI(false);
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public void duringPassiveEvent(PlayerEntity user) {
    if (user.world.isRemote) {
      return;
    }
    if (this.strain > 0.0D) {
      
      this.healTicks++;
      if (this.healTicks % (4800.0D + 2400.0D * this.strain) == 0.0D) {
        resetReviveTime(user, true);
      }
    } 
    EffectInstance effect = user.getActivePotionEffect(ModEffects.UNCONSCIOUS);
    if (!AbilityHelper.isAffectedByWater((LivingEntity)user) && !DevilFruitHelper.kairosekiChecks((LivingEntity)user) && effect != null && this.strain < 4.0D) {
      
      int time = (int)(60.0D + 180.0D * this.strain / 4.0D);
      if (this.outTicks >= time) {
        
        user.removePotionEffect(effect.getPotion());
        this.strain = Math.min(this.strain + Math.min(effect.getDuration() / 1200.0F, 1.0F), 4.0D);
        user.addPotionEffect(new EffectInstance(ModEffects.SHINZO_MASSAGE, 40, 5, false, false));
        this.outTicks = 0;
        return;
      } 
      this.outTicks++;
    } 
  }

  
  public void increaseReviveTime() {
    this.strain = Math.min(this.strain + Math.max(WyHelper.randomDouble(), 0.25D), 4.0D);
    this.healTicks = 0;
  }

  
  public void resetReviveTime(PlayerEntity user, boolean resetStrain) {
    if (resetStrain) {
      this.strain = 0.0D;
    }
    this.healTicks = 0;
    stopCooldown(user);
  }
  
  public double getStrain() {
    return this.strain;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    nbt.putDouble("strain", this.strain);
    nbt.putInt("healTicks", this.healTicks);
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    this.strain = nbt.getDouble("strain");
    this.healTicks = nbt.getInt("healTicks");
  }
}


