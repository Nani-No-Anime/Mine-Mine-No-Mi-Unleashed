package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class CandyEscalatorProjectile extends AbilityProjectileEntity {
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });

  
  public CandyEscalatorProjectile(World world) {
    super(PeroProjectiles.CANDY_ESCALATOR, world);
  }

  
  public CandyEscalatorProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public CandyEscalatorProjectile(World world, double x, double y, double z) {
    super(PeroProjectiles.CANDY_ESCALATOR, world, x, y, z);
  }

  
  public CandyEscalatorProjectile(World world, LivingEntity player) {
    super(PeroProjectiles.CANDY_ESCALATOR, world, player);
    
    setMaxLife(30);
    setPhysical(false);
    setPassThroughEntities();
    setPassThroughBlocks();
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    AbilityHelper.createFilledCube(this.world, getPosX(), getPosY() - 2.0D, getPosZ(), 1, 1, 1, ModBlocks.CANDY, GRIEF_RULE);
  }
}


