/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MH5CloudEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MH5Goal extends Goal {
/*     */   private DonKriegEntity entity;
/*  27 */   private int duration = 0;
/*     */   
/*     */   private static final int TIMER = 1500;
/*     */ 
/*     */   
/*     */   public MH5Goal(DonKriegEntity entity) {
/*  33 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  40 */     if (this.entity.getAttackTarget() == null) {
/*  41 */       return false;
/*     */     }
/*  43 */     if (this.entity.getHealth() > WyHelper.percentage(30.0D, this.entity.getMaxHealth())) {
/*  44 */       return false;
/*     */     }
/*  46 */     if (this.entity.isMH5Active()) {
/*  47 */       return false;
/*     */     }
/*  49 */     if (this.entity.challengeSpawnPosition == null) {
/*  50 */       return false;
/*     */     }
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  58 */     this.entity.triggerMH5Phase();
/*  59 */     if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED))
/*  60 */       this.entity.removePotionEffect(ModEffects.MOVEMENT_BLOCKED); 
/*  61 */     this.entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 1500, 0, false, false));
/*  62 */     BlockPos pos = this.entity.challengeSpawnPosition.add(-6, 2, -36);
/*  63 */     this.entity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
/*     */     
/*  65 */     this.entity.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.MH5_GAS_MASK));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     if (this.entity.getAttackTarget() == null) {
/*     */       return;
/*     */     }
/*  74 */     this.entity.lookAt(EntityAnchorArgument.Type.EYES, this.entity.getAttackTarget().getPositionVec());
/*     */     
/*  76 */     if (this.entity.getAttackTarget().getDistance((Entity)this.entity) <= 5.0F) {
/*     */       
/*  78 */       this.entity.setAnimation(OPEntity.Animation.CLEAVE_ATTACK.ordinal());
/*  79 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 4.0D, new Class[] { LivingEntity.class });
/*  80 */       targets.remove(this.entity);
/*  81 */       float damage = (float)this.entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/*  82 */       for (LivingEntity target : targets) {
/*     */         
/*  84 */         target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
/*  85 */         double x = target.getPosX() - this.entity.getPosX();
/*     */         double z;
/*  87 */         for (z = target.getPosZ() - this.entity.getPosZ(); x * x + z * z < 1.0E-4D; z = (Math.random() - Math.random()) * 0.01D)
/*     */         {
/*  89 */           x = (Math.random() - Math.random()) * 0.01D;
/*     */         }
/*  91 */         target.knockBack((Entity)target, 5.0F, -x, -z);
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     if (this.duration % 20 == 0)
/*     */     {
/*  97 */       this.entity.setAnimation(OPEntity.Animation.NONE.getId());
/*     */     }
/*     */     
/* 100 */     if (this.duration % 200 == 0) {
/*     */       
/* 102 */       int iRand = this.entity.world.rand.nextInt(2);
/* 103 */       int iDifficulty = (this.entity.world.getDifficulty().getId() == 3) ? 5 : 0;
/* 104 */       for (int i = 0; i < 5 + iRand + iDifficulty; i++) {
/*     */         
/* 106 */         BlockPos pos = WyHelper.findOnGroundSpawnLocation(this.entity.world, ModEntities.PIRATE_WITH_SWORD, this.entity.getAttackTarget().getPosition(), 15, 10);
/* 107 */         if (pos == null)
/*     */           return; 
/* 109 */         PirateWithSwordEntity pirate = (PirateWithSwordEntity)ModEntities.PIRATE_WITH_SWORD.spawn(this.entity.world, null, null, pos, SpawnReason.EVENT, true, true);
/* 110 */         if (i % 3 == 0) {
/* 111 */           pirate.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.MH5_GAS_MASK));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/* 119 */     this.duration++;
/* 120 */     return (this.duration < 1500);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 126 */     this.duration = 0;
/*     */     
/* 128 */     MH5CloudEntity cloud = new MH5CloudEntity(this.entity.world);
/* 129 */     cloud.setLife(100);
/* 130 */     cloud.setLocationAndAngles(this.entity.getAttackTarget().getPosX(), this.entity.getAttackTarget().getPosY() + 1.0D, this.entity.getAttackTarget().getPosZ(), 0.0F, 0.0F);
/* 131 */     cloud.setMotion(0.0D, 0.0D, 0.0D);
/* 132 */     this.entity.world.addEntity((Entity)cloud);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\donkrieg\MH5Goal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */