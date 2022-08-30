/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class PotionImmunitiesAbility
/*     */   extends PotionPassiveAbility
/*     */ {
/*  19 */   public static final ImmunityInfo HEAT_RESISTANCES = (new ImmunityInfo()).<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROSTBITE, ModEffects.CANDY_STUCK, ModEffects.CANDLE_LOCK }).<ImmunityInfo>addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(10)).addResistanceEffect(ModEffects.STICKY, Integer.valueOf(6));
/*     */   
/*  21 */   public static final PotionImmunitiesAbility HEAT_INSTANCE = new PotionImmunitiesAbility("Heat Immunities", HEAT_RESISTANCES);
/*     */   
/*  23 */   public static final PotionImmunitiesAbility LOGIA_HEAT_INSTANCE = new PotionImmunitiesAbility("Heat Logia Immunities", HEAT_RESISTANCES
/*  24 */       .addLogiaImmunities());
/*  25 */   public static final PotionImmunitiesAbility PERO_INSTANCE = new PotionImmunitiesAbility("Pero Immunities", (new ImmunityInfo())
/*  26 */       .addImmunityEffects(new Effect[] { ModEffects.CANDY_STUCK }));
/*  27 */   public static final PotionImmunitiesAbility LOGIA_INSTANCE = new PotionImmunitiesAbility("Logia Immunities", (new ImmunityInfo())
/*  28 */       .addLogiaImmunities());
/*  29 */   public static final PotionImmunitiesAbility YUKI_INSTANCE = new PotionImmunitiesAbility("Logia Immunities", (new ImmunityInfo())
/*  30 */       .<ImmunityInfo>addLogiaImmunities().addImmunityEffects(new Effect[] { ModEffects.FROSTBITE }));
/*  31 */   public static final PotionImmunitiesAbility MOKU_INSTANCE = new PotionImmunitiesAbility("Moku Immunities", (new ImmunityInfo())
/*  32 */       .<ImmunityInfo>addLogiaImmunities().addImmunityEffects(new Effect[] { ModEffects.SMOKE }));
/*  33 */   public static final PotionImmunitiesAbility GASU_INSTANCE = new PotionImmunitiesAbility("Gasu Immunities", (new ImmunityInfo())
/*  34 */       .<ImmunityInfo>addImmunityEffects(new Effect[] { Effects.POISON }).addLogiaImmunities());
/*  35 */   public static final PotionImmunitiesAbility HIE_INSTANCE = new PotionImmunitiesAbility("Hie Immunities", (new ImmunityInfo())
/*  36 */       .<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROZEN, ModEffects.FROSTBITE }).addLogiaImmunities());
/*  37 */   public static final PotionImmunitiesAbility BETA_INSTANCE = new PotionImmunitiesAbility("Beta Immunities", (new ImmunityInfo())
/*  38 */       .addImmunityEffects(new Effect[] { ModEffects.STICKY, Effects.NAUSEA, Effects.SLOWNESS }));
/*  39 */   public static final PotionImmunitiesAbility GURA_INSTANCE = new PotionImmunitiesAbility("Gura Immunities", (new ImmunityInfo())
/*  40 */       .<ImmunityInfo>addResistanceEffect(ModEffects.CANDLE_LOCK, Integer.valueOf(5)).<ImmunityInfo>addResistanceEffect(ModEffects.CANDY_STUCK, Integer.valueOf(8)).addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(8)));
/*  41 */   public static final PotionImmunitiesAbility NEGATIVE_INSTANCE = new PotionImmunitiesAbility("Negative Immunities", (new ImmunityInfo())
/*  42 */       .addImmunityEffects(new Effect[] { ModEffects.NEGATIVE }));
/*  43 */   public static final PotionImmunitiesAbility HANDCUFF_INSTANCE = new PotionImmunitiesAbility("Handcuff Immunities", (new ImmunityInfo())
/*  44 */       .addImmunityEffects(new Effect[] { ModEffects.HANDCUFFED }));
/*  45 */   public static final PotionImmunitiesAbility YOMI_INSTANCE = new PotionImmunitiesAbility("Yomi Immunities", (new ImmunityInfo())
/*  46 */       .<ImmunityInfo>addImmunityEffects(new Effect[] { ModEffects.FROSTBITE, ModEffects.DIZZY, ModEffects.DRUNK, ModEffects.UNCONSCIOUS, Effects.INSTANT_DAMAGE, Effects.REGENERATION, Effects.HUNGER, Effects.POISON }).<ImmunityInfo>addResistanceEffect(ModEffects.FROZEN, Integer.valueOf(2)).addResistanceEffect(ModEffects.PARALYSIS, Integer.valueOf(2)));
/*     */   
/*     */   private final ImmunityInfo immunityInfo;
/*     */ 
/*     */   
/*     */   public PotionImmunitiesAbility(String name, ImmunityInfo info) {
/*  52 */     super(name, AbilityHelper.getDevilFruitCategory());
/*  53 */     this.checkPotionEvent = this::checkPotionEvent;
/*     */     
/*  55 */     this.immunityInfo = info;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
/*  60 */     if (AbilityHelper.isAffectedByWater((LivingEntity)player)) {
/*  61 */       return true;
/*     */     }
/*  63 */     ArrayList<Effect> immunityEffects = this.immunityInfo.getImmunityEffects();
/*  64 */     ArrayList<Effect> resistanceEffects = this.immunityInfo.getResistanceEffects();
/*  65 */     ArrayList<Integer> resistanceEffectsReduction = this.immunityInfo.getResistanceEffectsReduction();
/*     */     
/*  67 */     if (resistanceEffects.size() > 0)
/*     */     {
/*  69 */       for (int i = 0; i < resistanceEffects.size(); i++) {
/*  70 */         if (((Effect)resistanceEffects.get(i)).equals(effect.getPotion()) && effect.getAmplifier() < 1) {
/*     */           
/*  72 */           int duration = effect.getDuration() / ((Integer)resistanceEffectsReduction.get(i)).intValue();
/*  73 */           if (duration <= 20)
/*  74 */             return false; 
/*  75 */           ((EffectInstanceMixin)effect).setDuration(duration);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  80 */     return !immunityEffects.contains(effect.getPotion());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Ability create() {
/*     */     try {
/*  88 */       return getClass()
/*  89 */         .getConstructor(new Class[] { String.class, ImmunityInfo.class
/*  90 */           }).newInstance(new Object[] { getName(), this.immunityInfo });
/*     */     }
/*  92 */     catch (Exception ex) {
/*     */       
/*  94 */       ex.printStackTrace();
/*     */       
/*  96 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ImmunityInfo
/*     */   {
/* 107 */     private ArrayList<Effect> immunityEffects = new ArrayList<>();
/* 108 */     private ArrayList<Effect> resistanceEffects = new ArrayList<>();
/* 109 */     private ArrayList<Integer> resistanceEffectsReduction = new ArrayList<>();
/*     */ 
/*     */     
/*     */     public ArrayList<Effect> getImmunityEffects() {
/* 113 */       return this.immunityEffects;
/*     */     }
/*     */ 
/*     */     
/*     */     public ArrayList<Effect> getResistanceEffects() {
/* 118 */       return this.resistanceEffects;
/*     */     }
/*     */     
/*     */     public ArrayList<Integer> getResistanceEffectsReduction() {
/* 122 */       return this.resistanceEffectsReduction;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T extends ImmunityInfo> T addImmunityEffects(Effect... immunityEffects) {
/* 127 */       this.immunityEffects.addAll(Arrays.asList(immunityEffects));
/* 128 */       return (T)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T extends ImmunityInfo> T addResistanceEffect(Effect resistanceEffect, Integer reduction) {
/* 133 */       this.resistanceEffects.add(resistanceEffect);
/* 134 */       this.resistanceEffectsReduction.add(reduction);
/* 135 */       return (T)this;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T extends ImmunityInfo> T addLogiaImmunities() {
/* 140 */       addResistanceEffect(ModEffects.MOVEMENT_BLOCKED, Integer.valueOf(2));
/* 141 */       return (T)this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\PotionImmunitiesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */