package xyz.pixelatedw.mineminenomi.data.entity.challenges;

import java.util.concurrent.Callable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

public class ChallengesDataCapability
{
  @CapabilityInject(IChallengesData.class)
  public static final Capability<IChallengesData> INSTANCE = null;

  
  public static void register() {
    CapabilityManager.INSTANCE.register(IChallengesData.class, new Capability.IStorage<IChallengesData>()
        {
          
          public INBT writeNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side)
          {
            CompoundNBT props = new CompoundNBT();
            
            ListNBT challenges = new ListNBT();
            for (int i = 0; i < instance.getChallenges().size(); i++) {
              
              Challenge challenge = instance.getChallenges().get(i);
              CompoundNBT nbtData = new CompoundNBT();
              nbtData.putString("id", challenge.getId());
              nbtData.putBoolean("complete", challenge.isComplete());
              challenges.add(nbtData);
            } 
            props.put("challenges", (INBT)challenges);
            
            return (INBT)props;
          }


          
          public void readNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side, INBT nbt) {
            CompoundNBT props = (CompoundNBT)nbt;
            
            instance.clearChallenges();
            
            ListNBT challenges = props.getList("challenges", 10);
            
            try {
              for (int i = 0; i < challenges.size(); i++) {
                
                CompoundNBT nbtData = challenges.getCompound(i);
                Challenge challenge = ((Challenge)GameRegistry.findRegistry(Challenge.class).getValue(new ResourceLocation(APIConfig.projectId, nbtData.getString("id")))).create();
                challenge.setComplete(nbtData.getBoolean("complete"));
                
                try {
                  instance.addChallenge(challenge);
                }
                catch (Exception e) {
                  
                  WyDebug.debug("Unregistered challenge: " + nbtData.getString("id"));
                }
              
              } 
            } catch (Exception e) {
              
              WyDebug.debug("Error in finding a Challenge registry element. This might be due to the use of an old world. \nNothing to worry about but challenges from previous mod versions will revert to being uncompleted!");
            } 
          }
        },ChallengesDataBase::new);
  }


  
  public static IChallengesData get(PlayerEntity entity) {
    return (IChallengesData)entity.getCapability(INSTANCE, null).orElse(new ChallengesDataBase());
  }

  
  public static LazyOptional<IChallengesData> getLazy(PlayerEntity entity) {
    return entity.getCapability(INSTANCE, null);
  }
}


