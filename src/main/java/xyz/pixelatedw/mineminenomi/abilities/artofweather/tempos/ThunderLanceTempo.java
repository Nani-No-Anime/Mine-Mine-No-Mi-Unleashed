package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ThunderLanceTempo extends TempoAbility {
	public static final ThunderLanceTempo INSTANCE = new ThunderLanceTempo();
	private boolean canShoot;

	public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) {
		if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
			ClimaTactItem climaTact = (ClimaTactItem) player.getHeldItemMainhand().getItem();
			String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
			return tempoCombo.equalsIgnoreCase("TTT");
		}
		return false;
	}

	public ThunderLanceTempo() {
		super("Thunder Lance Tempo", AbilityHelper.getStyleCategory());
		this.canShoot = false;
		setDescription("3 Charged Thunder Balls\nCreates a lighting bolt that goes directly to the area the user is pointing at, exploding on impact and hurting entities in its path");
		setCustomTexture("tempo");
		setMaxCooldown(25.0D);
		this.onUseEvent = this::onUseEvent;
		this.canUseCheck = this::canUseCheck;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}

	private void duringCooldownEvent(PlayerEntity player, int cooldown) {
		if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
			BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity) player, 100.0D);
			if (blockRayTraceResult.getType().equals(RayTraceResult.Type.BLOCK) && player.getHeldItemMainhand().getItem() instanceof ClimaTactItem && this.canShoot) {
				if (cooldown >= 0) {
					if (cooldown >= getMaxCooldown() * 0.67D && cooldown <= getMaxCooldown() * 0.67D + 1.0D) {
						this.canShoot = false;
						((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf((Entity) player, (IPacket) new SAnimateHandPacket((Entity) player, 0));
						int beamLength = 2;
						LightningEntity bolt = new LightningEntity((Entity) player, (beamLength + 100), 6.0F);
						bolt.setAliveTicks(10);
						bolt.setTargetTimeToReset(20);
						bolt.setDamage(12.0F);
						bolt.disableLightningMimic();
						bolt.disableExplosionKnockback();
						bolt.setColor(new Color(253, 208, 35, 205));
						bolt.setExplosion(1, false, 1.0F);
						bolt.setBoxSizeDivision(0.22499999403953552D);
						bolt.setAngle(20);
						bolt.setBranches(2);
						int segments = 5;
						bolt.setSegments((int) (segments + WyHelper.randomWithRange(-segments / 2, segments / 2)));
						player.world.addEntity((Entity) bolt);
					}
					if (this.canShoot) {
						double i = (blockRayTraceResult.getHitVec()).x;
						double j = (blockRayTraceResult.getHitVec()).y;
						double k = (blockRayTraceResult.getHitVec()).z;
						if (player.ticksExisted % 2 == 0) {
							spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D);
						}
					}
				}
			}
		} else {
			player.sendMessage((ITextComponent) new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
			stopCooldown(player);
		}
	}

	private boolean onUseEvent(PlayerEntity player) {
		if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
			ClimaTactItem climaTact = (ClimaTactItem) player.getHeldItemMainhand().getItem();
			setMaxCooldown(((4 - climaTact.getLevel()) * 9));
			if (climaTact.getLevel() < 2) {
				player.sendMessage((ITextComponent) new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
				return false;
			}
			this.canShoot = true;
		}
		return true;
	}

	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		int i = 0;
		double phi = 0.0D;
		double phi2 = 0.0D;
		double radius = 2.0D;
		while (phi < Math.PI) {
			phi += 2.0943951023931953D;
			double theta;
			for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
				float red, green, blue;
				ParticleType<GenericParticleData> particle;
				double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
				double y = radius * Math.tan(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
				double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
				if (i % 2 == 0) {
					red = 0.5F;
					green = 0.5F;
					blue = 0.5F;
					particle = ModParticleTypes.MOKU2;
				} else {
					red = 0.3F;
					green = 0.3F;
					blue = 0.3F;
					particle = ModParticleTypes.MOKU;
				}
				GenericParticleData data = new GenericParticleData(particle);
				data.setLife(25);
				data.setSize(8.0F);
				data.setColor(red, green, blue);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY + y, posZ + z);
				i++;
			}
		}
		while (phi2 < Math.PI) {
			phi2 += 2.0943951023931953D;
			double theta;
			for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
				ParticleType<GenericParticleData> particle;
				double x = radius * Math.cos(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
				double y = radius * Math.tan(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
				double z = radius * Math.sin(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
				if (i % 4 == 0) {
					particle = ModParticleTypes.GORO_YELLOW;
				} else {
					particle = ModParticleTypes.GORO2_YELLOW;
				}
				GenericParticleData data = new GenericParticleData(particle);
				data.setLife(15);
				data.setSize(6.0F);
				WyHelper.spawnParticles(data, (ServerWorld) world, posX + x, posY - 2.0D + y, posZ + z);
				i++;
			}
		}
	}
}