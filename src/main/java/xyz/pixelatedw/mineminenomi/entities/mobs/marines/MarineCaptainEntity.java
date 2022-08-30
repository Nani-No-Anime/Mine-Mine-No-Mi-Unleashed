/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IBrawler;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IRokushikiUser;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MarineCaptainEntity extends AbstractMarineEntity implements IRokushikiUser, ISwordsman, IBrawler, IHakiUser {
/* 27 */   private static final String[] DEFAULT_TEXTURES = new String[] { "marine_captain1", "marine_captain2", "marine_captain3", "marine_captain4", "marine_captain5" };
/*    */ 
/*    */   
/*    */   public MarineCaptainEntity(World world) {
/* 31 */     super(ModEntities.MARINE_CAPTAIN, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 37 */     super.registerGoals();
/* 38 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/* 39 */     this.goalSelector.addGoal(2, (Goal)new GapCloserGoal(this));
/* 40 */     addRokushikiAbilities(this, 3);
/* 41 */     addSwordsmanAbilities(this, 2);
/* 42 */     addBrawlerAbilities(this, 2);
/* 43 */     addBusoshokuHaki(this, 30);
/*    */ 
/*    */     
/* 46 */     setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
/* 47 */     setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 53 */     super.registerAttributes();
/* 54 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/* 55 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/* 56 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/* 57 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
/* 58 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(12, 15));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 64 */     super.registerData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 71 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 73 */     ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE);
/* 74 */     capeStack.getOrCreateChildTag("display").putInt("color", WyHelper.hexToRGB("#0BB6FF").getRGB());
/* 75 */     setItemStackToSlot(EquipmentSlotType.CHEST, capeStack);
/*    */     
/* 77 */     if (this.rand.nextDouble() < 0.8D) {
/*    */       
/* 79 */       ItemStack randomSword = new ItemStack((IItemProvider)MARINE_SWORDS[this.rand.nextInt(MARINE_SWORDS.length)]);
/* 80 */       setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
/*    */       
/* 82 */       if (this.rand.nextDouble() < 0.1D) {
/* 83 */         setItemStackToSlot(EquipmentSlotType.OFFHAND, randomSword);
/*    */       }
/*    */     } 
/* 86 */     if (this.rand.nextDouble() < 0.7D)
/*    */     {
/* 88 */       if (this.rand.nextDouble() < 0.3D) {
/* 89 */         setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR));
/*    */       } else {
/* 91 */         setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR_LESS));
/*    */       } 
/*    */     }
/* 94 */     return spawnData;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MarineCaptainEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */