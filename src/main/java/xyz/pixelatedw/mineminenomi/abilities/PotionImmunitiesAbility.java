package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PotionImmunitiesAbility
  extends PotionPassiveAbility
{
  public static final ImmunityInfo HEAT_RESISTANCES = (new ImmunityInfo()).<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROSTBITE, ModEffects.CANDY_STUCK, ModEffects.CANDLE_LOCK }).<ImmunityInfo>addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(10)).addResistanceEffect(ModEffects.STICKY, Integer.valueOf(6));
  
  public static final PotionImmunitiesAbility HEAT_INSTANCE = new PotionImmunitiesAbility("Heat Immunities", HEAT_RESISTANCES);
  
  public static final PotionImmunitiesAbility LOGIA_HEAT_INSTANCE = new PotionImmunitiesAbility("Heat Logia Immunities", HEAT_RESISTANCES
      .addLogiaImmunities());
  public static final PotionImmunitiesAbility PERO_INSTANCE = new PotionImmunitiesAbility("Pero Immunities", (new ImmunityInfo())
      .addImmunityEffects(new Effect[] { ModEffects.CANDY_STUCK }));
  public static final PotionImmunitiesAbility LOGIA_INSTANCE = new PotionImmunitiesAbility("Logia Immunities", (new ImmunityInfo())
      .addLogiaImmunities());
  public static final PotionImmunitiesAbility YUKI_INSTANCE = new PotionImmunitiesAbility("Logia Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addLogiaImmunities().addImmunityEffects(new Effect[] { ModEffects.FROSTBITE }));
  public static final PotionImmunitiesAbility MOKU_INSTANCE = new PotionImmunitiesAbility("Moku Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addLogiaImmunities().addImmunityEffects(new Effect[] { ModEffects.SMOKE }));
  public static final PotionImmunitiesAbility GASU_INSTANCE = new PotionImmunitiesAbility("Gasu Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addImmunityEffects(new Effect[] { Effects.POISON }).addLogiaImmunities());
  public static final PotionImmunitiesAbility HIE_INSTANCE = new PotionImmunitiesAbility("Hie Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROZEN, ModEffects.FROSTBITE }).addLogiaImmunities());
  public static final PotionImmunitiesAbility BETA_INSTANCE = new PotionImmunitiesAbility("Beta Immunities", (new ImmunityInfo())
      .addImmunityEffects(new Effect[] { ModEffects.STICKY, Effects.NAUSEA, Effects.SLOWNESS }));
  public static final PotionImmunitiesAbility GURA_INSTANCE = new PotionImmunitiesAbility("Gura Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addResistanceEffect(ModEffects.CANDLE_LOCK, Integer.valueOf(5)).<ImmunityInfo>addResistanceEffect(ModEffects.CANDY_STUCK, Integer.valueOf(8)).addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(8)));
  public static final PotionImmunitiesAbility NEGATIVE_INSTANCE = new PotionImmunitiesAbility("Negative Immunities", (new ImmunityInfo())
      .addImmunityEffects(new Effect[] { ModEffects.NEGATIVE }));
  public static final PotionImmunitiesAbility HANDCUFF_INSTANCE = new PotionImmunitiesAbility("Handcuff Immunities", (new ImmunityInfo())
      .addImmunityEffects(new Effect[] { ModEffects.HANDCUFFED }));
  public static final PotionImmunitiesAbility YOMI_INSTANCE = new PotionImmunitiesAbility("Yomi Immunities", (new ImmunityInfo())
      .<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROSTBITE, ModEffects.DIZZY, ModEffects.DRUNK, ModEffects.UNCONSCIOUS, Effects.INSTANT_DAMAGE, Effects.REGENERATION, Effects.HUNGER, Effects.POISON }).<ImmunityInfo>addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(2)).addResistanceEffect(ModEffects.PARALYSIS, Integer.valueOf(2)));
  
  private final ImmunityInfo immunityInfo;

  
  public PotionImmunitiesAbility(String name, ImmunityInfo info) {
    super(name, AbilityHelper.getDevilFruitCategory());
    this.checkPotionEvent = this::checkPotionEvent;
    
    this.immunityInfo = info;
  }

  
  private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
    if (AbilityHelper.isAffectedByWater((LivingEntity)player)) {
      return true;
    }
    ArrayList<Effect> immunityEffects = this.immunityInfo.getImmunityEffects();
    ArrayList<Effect> resistanceEffects = this.immunityInfo.getResistanceEffects();
    ArrayList<Integer> resistanceEffectsReduction = this.immunityInfo.getResistanceEffectsReduction();
    
    if (resistanceEffects.size() > 0)
    {
      for (int i = 0; i < resistanceEffects.size(); i++) {
        if (((Effect)resistanceEffects.get(i)).equals(effect.getPotion()) && effect.getAmplifier() < 1) {
          
          int duration = effect.getDuration() / ((Integer)resistanceEffectsReduction.get(i)).intValue();
          if (duration <= 20)
            return false; 
          ((EffectInstanceMixin)effect).setDuration(duration);
        } 
      } 
    }
    
    return !immunityEffects.contains(effect.getPotion());
  }



  
  public Ability create() {
    try {
      return getClass()
        .getConstructor(new Class[] { String.class, ImmunityInfo.class
          }).newInstance(new Object[] { getName(), this.immunityInfo });
    }
    catch (Exception ex) {
      
      ex.printStackTrace();
      
      return null;
    } 
  }





  
  public static class ImmunityInfo
  {
    private ArrayList<Effect> immunityEffects = new ArrayList<>();
    private ArrayList<Effect> resistanceEffects = new ArrayList<>();
    private ArrayList<Integer> resistanceEffectsReduction = new ArrayList<>();

    
    public ArrayList<Effect> getImmunityEffects() {
      return this.immunityEffects;
    }

    
    public ArrayList<Effect> getResistanceEffects() {
      return this.resistanceEffects;
    }
    
    public ArrayList<Integer> getResistanceEffectsReduction() {
      return this.resistanceEffectsReduction;
    }

    
    public <T extends ImmunityInfo> T addImmunityEffects(Effect... immunityEffects) {
      this.immunityEffects.addAll(Arrays.asList(immunityEffects));
      return (T)this;
    }

    
    public <T extends ImmunityInfo> T addResistanceEffect(Effect resistanceEffect, Integer reduction) {
      this.resistanceEffects.add(resistanceEffect);
      this.resistanceEffectsReduction.add(reduction);
      return (T)this;
    }

    
    public <T extends ImmunityInfo> T addLogiaImmunities() {
      addResistanceEffect(ModEffects.MOVEMENT_BLOCKED, Integer.valueOf(2));
      return (T)this;
    }
  }
}


