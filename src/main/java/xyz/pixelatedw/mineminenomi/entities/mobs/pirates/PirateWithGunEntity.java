/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*     */ 
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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class PirateWithGunEntity extends AbstractPirateEntity implements IRangedAttackMob {
/*  29 */   private static final String[] DEFAULT_TEXTURES = new String[] { "pirate1", "pirate2", "pirate3", "pirate4", "pirate5" };
/*  30 */   private static final String[] FISHMAN_TEXTURES = new String[] { "fishman_pirate1", "fishman_pirate2", "fishman_pirate3" };
/*     */ 
/*     */   
/*     */   public PirateWithGunEntity(World world) {
/*  34 */     super(ModEntities.PIRATE_WITH_GUN, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  40 */     super.registerGoals();
/*  41 */     if (getRNG().nextInt(10) > 0)
/*  42 */       this.goalSelector.addGoal(0, (Goal)new RunAwayGoal(this, 1.5D)); 
/*  43 */     this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 40, 15.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  49 */     super.registerAttributes();
/*  50 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  51 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/*  52 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  53 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  54 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  56 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
/*  57 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  63 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  70 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  72 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.FLINTLOCK));
/*     */     
/*  74 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  75 */     if (props.isFishman()) {
/*     */       
/*  77 */       this.textures = FISHMAN_TEXTURES;
/*  78 */       chooseTexture();
/*     */     } 
/*     */     
/*  81 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
/*  87 */     if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.FLINTLOCK)) {
/*     */       return;
/*     */     }
AbilityProjectileEntity projectileEntity = new NormalBulletProjectile(this.world, (LivingEntity)this);

if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI,getHeldItemMainhand()) != 0){
projectileEntity = new KairosekiBulletProjectile(this.world, (LivingEntity)this);
}

/*  91 */     projectileEntity.setDamage(2.0F);
/*  92 */     if (getAttackTarget() == null) {
/*     */       return;
/*     */     }
/*  95 */     double velX = target.getPosX() - getPosX();
/*  96 */     double velY = (target.getBoundingBox()).minY - projectileEntity.getPosY();
/*  97 */     double velZ = target.getPosZ() - getPosZ();
/*  98 */     double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*     */     
/* 100 */     projectileEntity.shoot(velX, velY + x * 0.20000000298023224D, velZ, 1.6F, (16 - this.world.getDifficulty().getId() * 4));
/* 101 */     this.world.addEntity(projectileEntity);
/*     */   }
/*     */ }