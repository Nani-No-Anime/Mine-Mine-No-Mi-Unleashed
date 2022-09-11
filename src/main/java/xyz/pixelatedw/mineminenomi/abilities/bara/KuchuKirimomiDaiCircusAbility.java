package xyz.pixelatedw.mineminenomi.abilities.bara;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.DaiCircusProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.BaraCircusZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class KuchuKirimomiDaiCircusAbility extends ZoanAbility {
  public static final KuchuKirimomiDaiCircusAbility INSTANCE = new KuchuKirimomiDaiCircusAbility();
  
  private LivingEntity grabbedEntity = null;
  private DaiCircusProjectile proj = null;

  
  public KuchuKirimomiDaiCircusAbility() {
    super("Kuchu Kirimomi Dai Circus", AbilityHelper.getDevilFruitCategory());
    setDescription("Fires both fists at an enemy and lifts them up, moving them around according to the user's movements");
    setMaxCooldown(15.0D);
    setThreshold(5.0D);
  }


  
  public boolean onStartContinuityEvent(PlayerEntity player) {
    if (!super.onStartContinuityEvent(player)) {
      return false;
    }
    BaraBaraFestivalAbility ability = (BaraBaraFestivalAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BaraBaraFestivalAbility.INSTANCE);
    if (ability != null && ability.isContinuous()) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_BARA_FESTIVAL, new Object[] { getDisplayName() }));
      return false;
    } 
    
    this.proj = new DaiCircusProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)this.proj);
    this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.0F);
    
    return true;
  }


  
  public void duringContinuityEvent(PlayerEntity player, int timer) {
    if ((this.proj == null || !this.proj.isAlive()) && this.grabbedEntity == null) {
      
      endContinuity(player);
      
      return;
    } 
    if (this.grabbedEntity != null && !this.grabbedEntity.isAlive()) {
      
      endContinuity(player);
      return;
    } 
    if (this.grabbedEntity != null) {
      
      this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
      this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
      this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
      
      double distance = 7.0D;
      Vec3d lookVec = player.getLookVec();
      Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
      if (!player.world.getBlockState(new BlockPos(pos)).isSolid()) {
        this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
      }
      this.grabbedEntity.fallDistance = 0.0F;
    } 
    player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 2, 0));
  }


  
  public boolean onEndContinuityEvent(PlayerEntity player) {
    if (!super.onEndContinuityEvent(player)) {
      return false;
    }
    if (this.grabbedEntity == null) {
      setMaxCooldown(0.0D);
    } else {
      setMaxCooldown(15.0D);
    } 
    this.grabbedEntity = null;
    this.proj = null;
    
    return true;
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BaraCircusZoanInfo.INSTANCE;
  }

  
  public void grabEntity(LivingEntity target) {
    this.grabbedEntity = target;
  }
}


