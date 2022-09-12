package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntityTextureWeight;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import javax.annotation.Nullable;

public class PirateWithGunEntity extends AbstractPirateEntity implements IRangedAttackMob {
	private static final String[] DEFAULT_TEXTURES = new String[] { "pirate1", "pirate2", "pirate3", "pirate4", "pirate5" };
	private static final String[] FISHMAN_TEXTURES = new String[] { "fishman_pirate1", "fishman_pirate2", "fishman_pirate3" };

	public PirateWithGunEntity(World world) { super(ModEntities.PIRATE_WITH_GUN, world, DEFAULT_TEXTURES); }

	protected void registerGoals() {
		super.registerGoals();
		if (getRNG().nextInt(10) > 0)
			this.goalSelector.addGoal(0, (Goal) new RunAwayGoal(this, 1.5D));
		this.goalSelector.addGoal(1, (Goal) new RangedAttackGoal(this, 1.0D, 40, 15.0F));
	}

	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
		setBelly(5.0D + WyHelper.randomWithRange(0, 5));
	}

	protected void registerData() { super.registerData(); }

	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider) ModWeapons.FLINTLOCK));
		IEntityStats props = EntityStatsCapability.get((LivingEntity) this);
		if (props.isFishman()) {
			this.textures = OPEntityTextureWeight.fromList(FISHMAN_TEXTURES);
			chooseTexture();
		}
		return spawnData;
	}

	public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
		if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.FLINTLOCK)) {
			return;
		}
		AbilityProjectileEntity projectileEntity = new NormalBulletProjectile(this.world, (LivingEntity) this);
		if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, getHeldItemMainhand()) != 0) {
			projectileEntity = new KairosekiBulletProjectile(this.world, (LivingEntity) this);
		}
		projectileEntity.setDamage(2.0F);
		if (getAttackTarget() == null) {
			return;
		}
		double velX = target.getPosX() - getPosX();
		double velY = (target.getBoundingBox()).minY - projectileEntity.getPosY();
		double velZ = target.getPosZ() - getPosZ();
		double x = MathHelper.sqrt(velX * velX + velZ * velZ);
		projectileEntity.shoot(velX, velY + x * 0.20000000298023224D, velZ, 1.6F, (16 - this.world.getDifficulty().getId() * 4));
		this.world.addEntity(projectileEntity);
	}
}