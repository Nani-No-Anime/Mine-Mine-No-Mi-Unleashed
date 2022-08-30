/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IBrawler;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BanditBruteEntity extends AbstractBanditEntity implements IBrawler, IHakiUser {
/* 25 */   private static final String[] DEFAULT_TEXTURES = new String[] { "bandit1", "bandit2", "bandit3" };
/*    */ 
/*    */   
/*    */   public BanditBruteEntity(World world) {
/* 29 */     super(ModEntities.BANDIT_BRUTE, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 35 */     super.registerGoals();
/* 36 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/* 37 */     this.goalSelector.addGoal(2, (Goal)(new CleaveAttackGoal(this, 100, 5, 4)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal()));
/* 38 */     addBrawlerAbilities(this, 1);
/* 39 */     addBusoshokuHaki(this, 15);
/*    */ 
/*    */     
/* 42 */     setDoriki(15.0D + WyHelper.randomWithRange(0, 12) + getThreat());
/* 43 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 49 */     super.registerAttributes();
/* 50 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/* 51 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/* 52 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/* 53 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/* 54 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(8, 12));
/* 55 */     getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 62 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 64 */     if (this.rand.nextDouble() < 0.4D) {
/* 65 */       setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.MACE));
/*    */     }
/* 67 */     return spawnData;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\BanditBruteEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */