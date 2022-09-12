package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;

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
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISniper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

import javax.annotation.Nullable;

public class BanditSniperEntity extends AbstractBanditEntity implements IRangedAttackMob, ISniper {
	private static final String[] DEFAULT_TEXTURES = new String[] { "bandit1", "bandit2", "bandit3" };

	public BanditSniperEntity(World world) { super(ModEntities.BANDIT_SNIPER, world, DEFAULT_TEXTURES); }

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, (Goal) new RangedAttackGoal(this, 1.0D, 60, 60.0F));
		addSniperAbilities(this, 1);
		setDoriki(15.0D + WyHelper.randomWithRange(0, 5) + getThreat());
		setBelly(5.0D + WyHelper.randomWithRange(0, 10));
	}

	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	protected void registerData() { super.registerData(); }

	public void tick() {
		super.tick();
		if (!this.world.isRemote) {
			if (this.ticksExisted % 5 == 0) {
				if (getAttackTarget() != null) {
					setAnimation(OPEntity.Animation.FLINTLOCK_POINTING.ordinal());
				} else {
					setAnimation(OPEntity.Animation.NONE.ordinal());
				}
			}
		}
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider) ModWeapons.SENRIKU));
		return spawnData;
	}

	public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
		if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.SENRIKU)) {
			return;
		}
		AbilityProjectileEntity projectileEntity = new NormalBulletProjectile(this.world, (LivingEntity) this);
		if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, getHeldItemMainhand()) != 0) {
			projectileEntity = new KairosekiBulletProjectile(this.world, (LivingEntity) this);
		}
		projectileEntity.setDamage(5.0F);
		double velX = target.getPosX() - getPosX();
		double velY = (target.getBoundingBox()).minY - projectileEntity.getPosY();
		double velZ = target.getPosZ() - getPosZ();
		double x = MathHelper.sqrt(velX * velX + velZ * velZ);
		projectileEntity.shoot(velX, velY + x * 0.10000000149011612D, velZ, 3.0F, MathHelper.clamp(7 - this.world.getDifficulty().getId() * 4, 0, 100));
		this.world.addEntity(projectileEntity);
	}
}