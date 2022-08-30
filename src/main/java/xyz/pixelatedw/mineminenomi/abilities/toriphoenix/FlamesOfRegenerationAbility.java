/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IOnDamageAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class FlamesOfRegenerationAbility extends PassiveAbility implements IExtraUpdateData, IOnDamageAbility {
/*  19 */   public static final FlamesOfRegenerationAbility INSTANCE = new FlamesOfRegenerationAbility();
/*     */   
/*     */   private static final int MAX_ENERGY = 100;
/*  22 */   private static final LogiaParticleEffect PARTICLES = new LogiaParticleEffect(ModParticleTypes.BLUE_FLAME);
/*     */   
/*  24 */   private double energy = 100.0D;
/*     */ 
/*     */   
/*     */   public FlamesOfRegenerationAbility() {
/*  28 */     super("Flames of Regeneration", AbilityHelper.getDevilFruitCategory());
/*  29 */     setDescription("Protects the user and heals them back up when damage is taken, has an initial reserve of 100 §2Energy§r which increases with time and decreses with each heal");
/*  30 */     hideInGUI(false);
/*     */     
/*  32 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringPassiveEvent(PlayerEntity player) {
/*  37 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/*  40 */     if (player.isSleeping() && player.ticksExisted % 10 == 0) {
/*  41 */       addEnergy((LivingEntity)player, 10.0D);
/*     */     }
/*  43 */     if (player.ticksExisted % 40 == 0)
/*     */     {
/*  45 */       if (this.energy - 4.0D >= 0.0D && player.getHealth() < player.getMaxHealth()) {
/*     */         
/*  47 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), Math.random(), 0.0D, Math.random());
/*  48 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() - Math.random(), player.getPosZ(), Math.random(), 0.0D, Math.random());
/*  49 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY() + Math.random(), player.getPosZ(), Math.random(), 0.0D, Math.random());
/*     */         
/*  51 */         player.heal(4.0F);
/*  52 */         addEnergy((LivingEntity)player, -5.0D);
/*     */       } 
/*     */     }
/*     */     
/*  56 */     addEnergy((LivingEntity)player, 0.05D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onDamage(LivingEntity entity, DamageSource source, double amount) {
/*  62 */     if (this.energy - amount >= 0.0D) {
/*     */       
/*  64 */       PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
/*  65 */       PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() - Math.random(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
/*  66 */       PARTICLES.spawn(entity.world, entity.getPosX(), entity.getPosY() + Math.random(), entity.getPosZ(), Math.random(), 0.0D, Math.random());
/*     */       
/*  68 */       entity.heal((float)amount);
/*  69 */       addEnergy(entity, -amount);
/*     */       
/*  71 */       return false;
/*     */     } 
/*     */     
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEnergy() {
/*  79 */     return this.energy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEnergy(LivingEntity entity, double energy) {
/*  84 */     this.energy = MathHelper.clamp(this.energy + energy, 0.0D, 100.0D);
/*  85 */     if (entity instanceof PlayerEntity) {
/*  86 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateExtraDataPacket((PlayerEntity)entity, (Ability)this), entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT getExtraData() {
/*  92 */     CompoundNBT nbt = new CompoundNBT();
/*  93 */     nbt.putDouble("energy", this.energy);
/*  94 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtraData(CompoundNBT nbt) {
/* 100 */     this.energy = nbt.getDouble("energy");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\FlamesOfRegenerationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */