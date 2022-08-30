/*    */ package xyz.pixelatedw.mineminenomi.abilities.karu;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class KarmaAbility extends PassiveAbility implements IOnDamageAbility, IExtraUpdateData {
/* 18 */   public static final KarmaAbility INSTANCE = new KarmaAbility();
/*    */   
/*    */   public static final float MAX_KARMA = 100.0F;
/*    */   
/* 22 */   private float karma = 0.0F;
/* 23 */   private float prevKarma = 0.0F;
/*    */ 
/*    */   
/*    */   public KarmaAbility() {
/* 27 */     super("Karma", AbilityHelper.getDevilFruitCategory());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
/* 33 */     if (source.isMagicDamage()) {
/* 34 */       return true;
/*    */     }
/* 36 */     if (this.karma >= 100.0F) {
/* 37 */       return true;
/*    */     }
/* 39 */     if (source instanceof ModDamageSource) {
/*    */       
/* 41 */       boolean isInternal = ((ModDamageSource)source).isInternalDamage();
/* 42 */       if (!isInternal)
/*    */       {
/* 44 */         addKarma(entity, (float)amount);
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 49 */       addKarma(entity, (float)amount);
/*    */     } 
/*    */     
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addKarma(LivingEntity entity, float amount) {
/* 57 */     this.prevKarma = this.karma;
/* 58 */     this.karma = MathHelper.clamp(this.karma + amount, 0.0F, 100.0F);
/* 59 */     if (entity instanceof PlayerEntity) {
/* 60 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket((PlayerEntity)entity, (Ability)this), entity);
/*    */     }
/*    */   }
/*    */   
/*    */   public float getKarma() {
/* 65 */     return this.karma;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPrevKarma(float prevKarma) {
/* 70 */     this.prevKarma = prevKarma;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPrevKarma() {
/* 75 */     return this.prevKarma;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT getExtraData() {
/* 81 */     CompoundNBT nbt = new CompoundNBT();
/* 82 */     nbt.putFloat("karma", this.karma);
/* 83 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExtraData(CompoundNBT nbt) {
/* 89 */     this.karma = nbt.getFloat("karma");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\karu\KarmaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */