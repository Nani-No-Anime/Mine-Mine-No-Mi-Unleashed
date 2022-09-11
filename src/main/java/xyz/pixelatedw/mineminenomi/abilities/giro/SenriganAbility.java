package xyz.pixelatedw.mineminenomi.abilities.giro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.api.abilities.IOutOfBodyAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class SenriganAbility extends DamagedContinuousAbility implements IOutOfBodyAbility, IExtraUpdateData {
  public static final SenriganAbility INSTANCE = new SenriganAbility();
  
  private PhysicalBodyEntity body;
  
  private BlockPos pivotPoint;
  
  public SenriganAbility() {
    super("Senrigan", AbilityHelper.getDevilFruitCategory());
    setDescription("The spirit leaves the body, allowing them to freely explore the nearby areas from huge heights");
    setThreshold(60.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.onDamagedEvent = this::onDamagedEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.isCreative() || player.isSpectator()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY, new Object[0]));
      return false;
    } 
    this.pivotPoint = new BlockPos(player.getPositionVec().getX(), player.getPositionVec().getY(), player.getPositionVec().getZ());
    
    this.body = new PhysicalBodyEntity(player.world);
    this.body.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
    this.body.setOwner((LivingEntity)player);
    player.world.addEntity((Entity)this.body);
    this.body.setHealth(player.getHealth());
    this.body.setParentAbility((Ability)this);
    
    startOutOfBody(player);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int continueTime) {
    if (Math.sqrt(player.getDistanceSq(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ())) > getMaxRange()) {
      
      endContinuity(player);
      
      return;
    } 
    player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 10, 0, false, false));
    
    if (player.getPosY() < 128.0D) {
      player.setPositionAndUpdate(player.getPosX(), 128.0D, player.getPosZ());
    }
    if (this.body == null) {
      
      endContinuity(player);
      
      return;
    } 
    if (!this.body.isAlive()) {
      player.attackEntityFrom(DamageSource.MAGIC, player.getMaxHealth());
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    double cooldown = (this.continueTime / 20.0F);
    setMaxCooldown(cooldown);
    
    if (this.body != null) {
      
      this.body.remove();
      this.body = null;
      player.setPositionAndUpdate(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ());
    } 
    
    stopOutOfBody(player);
    
    return true;
  }

  
  private boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource, double v) {
    if (entity instanceof PlayerEntity) {
      
      if (((PlayerEntity)entity).isCreative() || entity.isSpectator()) {
        return false;
      }
      return (damageSource == DamageSource.MAGIC || damageSource.isDamageAbsolute());
    } 
    return false;
  }


  
  public float getMaxRange() {
    return 256.0F;
  }


  
  public BlockPos getPivotPoint() {
    return this.pivotPoint;
  }


  
  public CompoundNBT getExtraData() {
    CompoundNBT nbt = new CompoundNBT();
    if (this.pivotPoint != null) {
      
      nbt.putDouble("x", this.pivotPoint.getX());
      nbt.putDouble("y", this.pivotPoint.getY());
      nbt.putDouble("z", this.pivotPoint.getZ());
    } 
    return nbt;
  }


  
  public void setExtraData(CompoundNBT nbt) {
    double x = nbt.getDouble("x");
    double y = nbt.getDouble("y");
    double z = nbt.getDouble("z");
    this.pivotPoint = new BlockPos(x, y, z);
  }


  
  public boolean isPhysical() {
    return true;
  }
}


