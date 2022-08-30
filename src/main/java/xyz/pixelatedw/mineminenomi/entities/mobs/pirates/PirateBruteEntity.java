/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*    */ import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PirateBruteEntity extends AbstractPirateEntity implements IBrawler, IHakiUser {
/* 27 */   private static final String[] DEFAULT_TEXTURES = new String[] { "pirate1", "pirate2", "pirate3", "pirate4", "pirate5" };
/* 28 */   private static final String[] FISHMAN_TEXTURES = new String[] { "fishman_pirate1", "fishman_pirate2", "fishman_pirate3" };
/*    */ 
/*    */   
/*    */   public PirateBruteEntity(World world) {
/* 32 */     super(ModEntities.PIRATE_BRUTE, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 38 */     super.registerGoals();
/* 39 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/* 40 */     this.goalSelector.addGoal(2, (Goal)(new CleaveAttackGoal(this, 100, 5, 4)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal()));
/* 41 */     addBrawlerAbilities(this, 1);
/* 42 */     addBusoshokuHaki(this, 15);
/*    */ 
/*    */     
/* 45 */     setDoriki(15.0D + WyHelper.randomWithRange(0, 12) + getThreat());
/* 46 */     setBelly(15.0D + WyHelper.randomWithRange(0, 10));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 52 */     super.registerAttributes();
/* 53 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/* 54 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/* 55 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/* 56 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/* 57 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(8, 12));
/* 58 */     getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 65 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 67 */     if (this.rand.nextDouble() < 0.4D) {
/* 68 */       setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.MACE));
/*    */     }
/* 70 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 71 */     if (props.isFishman()) {
/*    */       
/* 73 */       this.textures = FISHMAN_TEXTURES;
/* 74 */       chooseTexture();
/*    */     } 
/*    */     
/* 77 */     return spawnData;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\PirateBruteEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */