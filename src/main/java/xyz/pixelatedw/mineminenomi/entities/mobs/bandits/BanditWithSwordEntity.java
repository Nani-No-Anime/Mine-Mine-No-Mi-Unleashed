/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
/*    */ 
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
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BanditWithSwordEntity extends AbstractBanditEntity {
/* 24 */   private static final String[] DEFAULT_TEXTURES = new String[] { "bandit1", "bandit2", "bandit3" };
/*    */ 
/*    */   
/*    */   public BanditWithSwordEntity(World world) {
/* 28 */     super(ModEntities.BANDIT_WITH_SWORD, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 34 */     super.registerGoals();
/* 35 */     if (getRNG().nextInt(10) > 0)
/* 36 */       this.goalSelector.addGoal(0, (Goal)new RunAwayGoal(this, 1.5D)); 
/* 37 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 43 */     super.registerAttributes();
/* 44 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/* 45 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/* 46 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(-1.0D);
/* 47 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/* 48 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*    */     
/* 50 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
/* 51 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 57 */     super.registerData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 64 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 66 */     ItemStack randomSword = new ItemStack((IItemProvider)BANDIT_SWORDS[this.rand.nextInt(BANDIT_SWORDS.length)]);
/* 67 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
/*    */     
/* 69 */     return spawnData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ITextComponent getProfessionName() {
/* 75 */     ItemStack equippedItem = getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/* 76 */     if (equippedItem != null && equippedItem.getItem() instanceof net.minecraft.item.AxeItem)
/* 77 */       return (ITextComponent)new StringTextComponent("Bandit with Axe"); 
/* 78 */     return super.getProfessionName();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\bandits\BanditWithSwordEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */