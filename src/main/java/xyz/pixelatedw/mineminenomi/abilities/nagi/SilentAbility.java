package xyz.pixelatedw.mineminenomi.abilities.nagi;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class SilentAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final SilentAbility INSTANCE = new SilentAbility();

  
  public SilentAbility() {
    super("Silent", AbilityHelper.getDevilFruitCategory());
    setDescription("Cancels all noises caused by or around the user");
    
    this.duringContinuityEvent = this::duringContinuityEvent;
  }




  
  private void duringContinuityEvent(PlayerEntity player, int time) {}



  
  @EventBusSubscriber(modid = "mineminenomi")
  public static class SilentAbilityServerEvents
  {
    @SubscribeEvent
    public static void onServerMessage(ServerChatEvent event) {
      ServerPlayerEntity serverPlayerEntity = event.getPlayer();
      IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
      SilentAbility ability = (SilentAbility)abilityData.getEquippedAbility((Ability)SilentAbility.INSTANCE);
      
      if (ability != null && ability.isContinuous()) {
        
        event.setCanceled(true);
        List<PlayerEntity> players = WyHelper.getEntitiesNear(serverPlayerEntity.getPosition(), ((PlayerEntity)serverPlayerEntity).world, 30.0D, new Class[] { PlayerEntity.class });
        
        if (players.size() <= 0) {
          return;
        }
        for (PlayerEntity target : players) {
          target.sendMessage(event.getComponent());
        }
      } 
    }
  }






  
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class SilentAbilityClientEvents
  {
    @SubscribeEvent
    public static void onSoundPlayed(PlaySoundEvent event) {
      Minecraft mc = Minecraft.getInstance();
      
      if (mc.world == null) {
        return;
      }
      BlockPos soundPos = new BlockPos(event.getSound().getX(), event.getSound().getY(), event.getSound().getZ());
      
      List<PlayerEntity> targets = WyHelper.getEntitiesNear(soundPos, (World)mc.world, 30.0D, new Class[] { PlayerEntity.class });
      
      if (targets.size() <= 0) {
        return;
      }
      PlayerEntity user = null;
      
      for (PlayerEntity target : targets) {
        
        IAbilityData targetData = AbilityDataCapability.get((LivingEntity)target);
        SilentAbility ability = (SilentAbility)targetData.getEquippedAbility((Ability)SilentAbility.INSTANCE);
        boolean isActive = (ability != null && ability.isContinuous());
        if (isActive) {
          
          user = target;
          
          break;
        } 
      } 
      if (user != null)
        event.setResultSound(null); 
    }
  }
}


