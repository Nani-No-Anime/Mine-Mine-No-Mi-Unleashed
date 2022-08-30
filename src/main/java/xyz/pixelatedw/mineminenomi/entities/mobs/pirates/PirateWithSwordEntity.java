/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PirateWithSwordEntity extends AbstractPirateEntity {
/* 26 */   private static final String[] DEFAULT_TEXTURES = new String[] { "pirate1", "pirate2", "pirate3", "pirate4", "pirate5" };
/* 27 */   private static final String[] FISHMAN_TEXTURES = new String[] { "fishman_pirate1", "fishman_pirate2", "fishman_pirate3" };
/*    */ 
/*    */   
/*    */   public PirateWithSwordEntity(World world) {
/* 31 */     super(ModEntities.PIRATE_WITH_SWORD, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 37 */     super.registerGoals();
/* 38 */     if (getRNG().nextInt(10) > 0)
/* 39 */       this.goalSelector.addGoal(0, (Goal)new RunAwayGoal(this, 1.5D)); 
/* 40 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 46 */     super.registerAttributes();
/* 47 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/* 48 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/* 49 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(-1.0D);
/* 50 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/* 51 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
/*    */     
/* 53 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
/* 54 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 60 */     super.registerData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 67 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 69 */     ItemStack randomSword = new ItemStack((IItemProvider)PIRATE_SWORDS[this.rand.nextInt(PIRATE_SWORDS.length)]);
/* 70 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
/*    */     
/* 72 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 73 */     if (props.isFishman()) {
/*    */       
/* 75 */       this.textures = FISHMAN_TEXTURES;
/* 76 */       chooseTexture();
/*    */     } 
/*    */     
/* 79 */     return spawnData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ITextComponent getProfessionName() {
/* 85 */     ItemStack equippedItem = getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/* 86 */     if (equippedItem != null && equippedItem.getItem() instanceof net.minecraft.item.AxeItem)
/* 87 */       return (ITextComponent)new StringTextComponent("Pirate with Axe"); 
/* 88 */     return super.getProfessionName();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\PirateWithSwordEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */