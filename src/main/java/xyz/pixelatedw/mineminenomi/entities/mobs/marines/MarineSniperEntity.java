/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*     */ import javax.annotation.Nullable;
import net.minecraft.enchantment.EnchantmentHelper;
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
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class MarineSniperEntity extends AbstractMarineEntity implements IRangedAttackMob, ISniper {
/*  28 */   private static final String[] DEFAULT_TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5" };
/*     */   
/*     */   private ImprovedMeleeAttackGoal meleeGoal;
/*     */   private RangedAttackGoal rangeGoal;
/*     */   
/*     */   public MarineSniperEntity(World world) {
/*  34 */     super(ModEntities.MARINE_SNIPER, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  40 */     super.registerGoals();
/*     */     
/*  42 */     this.meleeGoal = new ImprovedMeleeAttackGoal(this, 1.0D, true);
/*  43 */     this.rangeGoal = new RangedAttackGoal(this, 1.0D, 60, 60.0F);
/*     */     
/*  45 */     this.goalSelector.addGoal(1, (Goal)this.rangeGoal);
/*  46 */     addSniperAbilities(this, 1);
/*     */     
/*  48 */     setDoriki(15.0D + WyHelper.randomWithRange(0, 5) + getThreat());
/*  49 */     setBelly(5.0D + WyHelper.randomWithRange(0, 10));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  55 */     super.registerAttributes();
/*  56 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  57 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  58 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  59 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  60 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  66 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  72 */     super.tick();
/*  73 */     if (!this.world.isRemote)
/*     */     {
/*  75 */       if (this.ticksExisted % 5 == 0)
/*     */       {
/*  77 */         if (getAttackTarget() != null) {
/*  78 */           setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
/*     */         } else {
/*  80 */           setAnimation(OPEntity.Animation.NONE.ordinal());
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  89 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  91 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.SENRIKU));
/*     */     
/*  93 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
/*  99 */     if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.SENRIKU)) {
/*     */       return;
/*     */     }
			AbilityProjectileEntity projectileEntity = new NormalBulletProjectile(this.world, (LivingEntity)this);

			if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI,getHeldItemMainhand()) != 0){
			projectileEntity = new KairosekiBulletProjectile(this.world, (LivingEntity)this);
			}

/* 103 */     projectileEntity.setDamage(5.0F);
/*     */     
/* 105 */     double velX = target.getPosX() - getPosX();
/* 106 */     double velY = (target.getBoundingBox()).minY - projectileEntity.getPosY();
/* 107 */     double velZ = target.getPosZ() - getPosZ();
/* 108 */     double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*     */     
/* 110 */     projectileEntity.shoot(velX, velY + x * 0.10000000149011612D, velZ, 3.0F, MathHelper.clamp(7 - this.world.getDifficulty().getId() * 4, 0, 100));
/* 111 */     this.world.addEntity(projectileEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MarineSniperEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */