/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public class ShinzoMassageAbility extends PassiveAbility implements IExtraUpdateData {
/* 15 */   public static final ShinzoMassageAbility INSTANCE = new ShinzoMassageAbility();
/* 16 */   private double strain = 0.0D;
/* 17 */   private int healTicks = 0;
/* 18 */   private int outTicks = 0;
/*    */ 
/*    */   
/*    */   public ShinzoMassageAbility() {
/* 22 */     super("Shinzo Massage", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(60.0D);
/* 24 */     setDescription("The user restarts their own heart after dying using lightning powers");
/* 25 */     hideInGUI(false);
/* 26 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringPassiveEvent(PlayerEntity user) {
/* 31 */     if (user.world.isRemote) {
/*    */       return;
/*    */     }
/* 34 */     if (this.strain > 0.0D) {
/*    */       
/* 36 */       this.healTicks++;
/* 37 */       if (this.healTicks % (4800.0D + 2400.0D * this.strain) == 0.0D) {
/* 38 */         resetReviveTime(user, true);
/*    */       }
/*    */     } 
/* 41 */     EffectInstance effect = user.getActivePotionEffect(ModEffects.UNCONSCIOUS);
/* 42 */     if (!AbilityHelper.isAffectedByWater((LivingEntity)user) && !DevilFruitHelper.kairosekiChecks((LivingEntity)user) && effect != null && this.strain < 4.0D) {
/*    */       
/* 44 */       int time = (int)(60.0D + 180.0D * this.strain / 4.0D);
/* 45 */       if (this.outTicks >= time) {
/*    */         
/* 47 */         user.removePotionEffect(effect.getPotion());
/* 48 */         this.strain = Math.min(this.strain + Math.min(effect.getDuration() / 1200.0F, 1.0F), 4.0D);
/* 49 */         user.addPotionEffect(new EffectInstance(ModEffects.SHINZO_MASSAGE, 40, 5, false, false));
/* 50 */         this.outTicks = 0;
/*    */         return;
/*    */       } 
/* 53 */       this.outTicks++;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void increaseReviveTime() {
/* 59 */     this.strain = Math.min(this.strain + Math.max(WyHelper.randomDouble(), 0.25D), 4.0D);
/* 60 */     this.healTicks = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetReviveTime(PlayerEntity user, boolean resetStrain) {
/* 65 */     if (resetStrain) {
/* 66 */       this.strain = 0.0D;
/*    */     }
/* 68 */     this.healTicks = 0;
/* 69 */     stopCooldown(user);
/*    */   }
/*    */   
/*    */   public double getStrain() {
/* 73 */     return this.strain;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT getExtraData() {
/* 79 */     CompoundNBT nbt = new CompoundNBT();
/* 80 */     nbt.putDouble("strain", this.strain);
/* 81 */     nbt.putInt("healTicks", this.healTicks);
/* 82 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExtraData(CompoundNBT nbt) {
/* 88 */     this.strain = nbt.getDouble("strain");
/* 89 */     this.healTicks = nbt.getInt("healTicks");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\ShinzoMassageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */