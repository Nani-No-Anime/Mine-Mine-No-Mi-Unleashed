package xyz.pixelatedw.mineminenomi.abilities.ope;


import java.util.Collections;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class ShamblesAbility
  extends Ability {
  public static final Ability INSTANCE = new ShamblesAbility();
  
  private Mode currentMode = Mode.SINGLE;

  
  public ShamblesAbility() {
    super("Shambles", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setDescription("The user swaps place with the closest entity or block within the ROOM\n\n§2SHIFT-USE§r: Switches between GROUP and SINGLE modes");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (!OpeHelper.hasRoomActive(player, this)) {
      return false;
    }
    RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)RoomAbility.INSTANCE);
    boolean hadTarget = false;
    
    if (player.isSneaking()) {
      
      if (this.currentMode == Mode.SINGLE) {
        this.currentMode = Mode.GROUP;
      } else {
        this.currentMode = Mode.SINGLE;
      } 
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.currentMode, new Object[0]));
      return false;
    } 

    
    if (this.currentMode == Mode.SINGLE) {
      
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
      
      if (mop instanceof EntityRayTraceResult) {
        
        EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
        
        if (entityRayTraceResult.getEntity() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity || (entityRayTraceResult.getEntity() instanceof AbilityProjectileEntity && !((AbilityProjectileEntity)entityRayTraceResult.getEntity()).isPhysical())) {
          return true;
        }
        Entity target = ((EntityRayTraceResult)mop).getEntity();
        
        if (!ability.isEntityInThisRoom(target)) {
          return true;
        }
        float[] beforeCoords = { (float)player.getPosX(), (float)player.getPosY(), (float)player.getPosZ(), player.rotationYaw, player.rotationPitch };
        player.setPositionAndRotation(target.getPosX(), target.getPosY(), target.getPosZ(), target.rotationYaw, target.rotationPitch);
        player.setPositionAndUpdate(target.getPosX(), target.getPosY(), target.getPosZ());
        target.setPositionAndRotation(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
        
        player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
        player.world.playSound(null, target.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
        
        hadTarget = true;
      }
      else if (mop instanceof BlockRayTraceResult) {
        
        BlockRayTraceResult result = (BlockRayTraceResult)mop;
        BlockPos pos = result.getPos();
        BlockState state = player.world.getBlockState(pos);
        BlockPos playerPos = player.getPosition();
        BlockState playerPosState = player.world.getBlockState(playerPos);
        
        boolean isInsideRoom = ability.isInsideROOM(player.world, pos);
        boolean isDestinationBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(state.getBlock());
        boolean isOriginBanned = RestrictedBlockProtectionRule.INSTANCE.isBanned(playerPosState.getBlock());
        
        if (isInsideRoom && !isDestinationBanned && !isOriginBanned)
        {
          BlockPos beforePos = player.getPosition();
          player.setPositionAndRotation(pos.getX(), (pos.getY() + 1), pos.getZ(), player.rotationYaw, player.rotationPitch);
          player.world.setBlockState(beforePos, state);
          player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
          
          player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
          
          hadTarget = true;
        }
      
      } 
    } else {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(ability.getCenterBlock(), player.world, ability.getROOMSize(), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      Collections.shuffle(targets);
      
      for (int i = 0; i < targets.size(); i += 2) {
        
        if (i >= targets.size() || i + 1 >= targets.size()) {
          break;
        }
        LivingEntity target1 = targets.get(i);
        LivingEntity target2 = targets.get(i + 1);
        
        if (ability.isEntityInThisRoom((Entity)target1) && ability.isEntityInThisRoom((Entity)target2)) {

          
          float[] beforeCoords = { (float)target2.getPosX(), (float)target2.getPosY(), (float)target2.getPosZ(), target2.rotationYaw, target2.rotationPitch };
          target2.setPositionAndRotation(target1.getPosX(), target1.getPosY(), target1.getPosZ(), target1.rotationYaw, target1.rotationPitch);
          target2.setPositionAndUpdate(target1.getPosX(), target1.getPosY(), target1.getPosZ());
          target1.setPositionAndRotation(beforeCoords[0], beforeCoords[1], beforeCoords[2], beforeCoords[3], beforeCoords[4]);
          
          player.world.playSound(null, target2.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
          player.world.playSound(null, target1.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
        } 
      } 
      if (targets.size() >= 2) {
        hadTarget = true;
      }
    } 
    
    return hadTarget;
  }
  
  public enum Mode
  {
    SINGLE, GROUP;
  }
}


