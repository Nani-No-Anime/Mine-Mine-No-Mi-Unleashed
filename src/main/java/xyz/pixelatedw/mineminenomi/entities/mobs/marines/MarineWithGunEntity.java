/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
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
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MarineWithGunEntity extends AbstractMarineEntity implements IRangedAttackMob {
/*  27 */   private static final String[] DEFAULT_TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5" };
/*     */ 
/*     */   
/*     */   public MarineWithGunEntity(World world) {
/*  31 */     super(ModEntities.MARINE_WITH_GUN, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  37 */     super.registerGoals();
/*  38 */     if (getRNG().nextInt(10) > 0)
/*  39 */       this.goalSelector.addGoal(0, (Goal)new RunAwayGoal(this, 1.5D)); 
/*  40 */     this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 40, 15.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  46 */     super.registerAttributes();
/*  47 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
/*  48 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/*  49 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  50 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  51 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  53 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
/*  54 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  60 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  66 */     super.tick();
/*  67 */     if (!this.world.isRemote)
/*     */     {
/*  69 */       if (this.ticksExisted % 5 == 0)
/*     */       {
/*  71 */         if (getAttackTarget() != null) {
/*  72 */           setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
/*     */         } else {
/*  74 */           setAnimation(OPEntity.Animation.NONE.ordinal());
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  83 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  85 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
/*     */     
/*  87 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
/*  93 */     if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.FLINTLOCK)) {
/*     */       return;
/*     */     }
/*  96 */     NormalBulletProjectile normalBulletProjectile = new NormalBulletProjectile(this.world, (LivingEntity)this);
/*  97 */     normalBulletProjectile.setDamage(2.0F);
/*     */     
/*  99 */     double velX = target.getPosX() - getPosX();
/* 100 */     double velY = (target.getBoundingBox()).minY - normalBulletProjectile.getPosY();
/* 101 */     double velZ = target.getPosZ() - getPosZ();
/* 102 */     double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*     */     
/* 104 */     normalBulletProjectile.shoot(velX, velY + x * 0.10000000149011612D, velZ, 1.6F, (16 - this.world.getDifficulty().getId() * 4));
/* 105 */     this.world.addEntity((Entity)normalBulletProjectile);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MarineWithGunEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */