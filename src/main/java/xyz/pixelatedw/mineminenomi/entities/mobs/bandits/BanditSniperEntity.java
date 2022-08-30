/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.RangedAttackGoal;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.ISniper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BanditSniperEntity extends AbstractBanditEntity implements IRangedAttackMob, ISniper {
/*  27 */   private static final String[] DEFAULT_TEXTURES = new String[] { "bandit1", "bandit2", "bandit3" };
/*     */ 
/*     */   
/*     */   public BanditSniperEntity(World world) {
/*  31 */     super(ModEntities.BANDIT_SNIPER, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  37 */     super.registerGoals();
/*  38 */     this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 60, 60.0F));
/*  39 */     addSniperAbilities(this, 1);
/*     */     
/*  41 */     setDoriki(15.0D + WyHelper.randomWithRange(0, 5) + getThreat());
/*  42 */     setBelly(5.0D + WyHelper.randomWithRange(0, 10));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  48 */     super.registerAttributes();
/*  49 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  50 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  51 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  52 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  53 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  59 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  65 */     super.tick();
/*  66 */     if (!this.world.isRemote)
/*     */     {
/*  68 */       if (this.ticksExisted % 5 == 0)
/*     */       {
/*  70 */         if (getAttackTarget() != null) {
/*  71 */           setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
/*     */         } else {
/*  73 */           setAnimation(OPEntity.Animation.NONE.ordinal());
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  82 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  84 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.SENRIKU));
/*     */     
/*  86 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
/*  92 */     if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.SENRIKU)) {
/*     */       return;
/*     */     }
/*  95 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.world, (LivingEntity)this);
/*  96 */     normalBulletProjectile.setDamage(5.0F);
/*     */     
/*  98 */     double velX = target.getPosX() - getPosX();
/*  99 */     double velY = (target.getBoundingBox()).minY - normalBulletProjectile.getPosY();
/* 100 */     double velZ = target.getPosZ() - getPosZ();
/* 101 */     double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*     */     
/* 103 */     normalBulletProjectile.shoot(velX, velY + x * 0.10000000149011612D, velZ, 3.0F, MathHelper.clamp(7 - this.world.getDifficulty().getId() * 4, 0, 100));
/* 104 */     this.world.addEntity((Entity)normalBulletProjectile);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\BanditSniperEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */