/*    */ package xyz.pixelatedw.mineminenomi.data.entity.challenges;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.common.util.LazyOptional;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*    */ 
/*    */ public class ChallengesDataCapability
/*    */ {
/*    */   @CapabilityInject(IChallengesData.class)
/* 22 */   public static final Capability<IChallengesData> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 26 */     CapabilityManager.INSTANCE.register(IChallengesData.class, new Capability.IStorage<IChallengesData>()
/*    */         {
/*    */           
/*    */           public INBT writeNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side)
/*    */           {
/* 31 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 33 */             ListNBT challenges = new ListNBT();
/* 34 */             for (int i = 0; i < instance.getChallenges().size(); i++) {
/*    */               
/* 36 */               Challenge challenge = instance.getChallenges().get(i);
/* 37 */               CompoundNBT nbtData = new CompoundNBT();
/* 38 */               nbtData.putString("id", challenge.getId());
/* 39 */               nbtData.putBoolean("complete", challenge.isComplete());
/* 40 */               challenges.add(nbtData);
/*    */             } 
/* 42 */             props.put("challenges", (INBT)challenges);
/*    */             
/* 44 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side, INBT nbt) {
/* 50 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 52 */             instance.clearChallenges();
/*    */             
/* 54 */             ListNBT challenges = props.getList("challenges", 10);
/*    */             
/*    */             try {
/* 57 */               for (int i = 0; i < challenges.size(); i++) {
/*    */                 
/* 59 */                 CompoundNBT nbtData = challenges.getCompound(i);
/* 60 */                 Challenge challenge = ((Challenge)GameRegistry.findRegistry(Challenge.class).getValue(new ResourceLocation(APIConfig.projectId, nbtData.getString("id")))).create();
/* 61 */                 challenge.setComplete(nbtData.getBoolean("complete"));
/*    */                 
/*    */                 try {
/* 64 */                   instance.addChallenge(challenge);
/*    */                 }
/* 66 */                 catch (Exception e) {
/*    */                   
/* 68 */                   WyDebug.debug("Unregistered challenge: " + nbtData.getString("id"));
/*    */                 }
/*    */               
/*    */               } 
/* 72 */             } catch (Exception e) {
/*    */               
/* 74 */               WyDebug.debug("Error in finding a Challenge registry element. This might be due to the use of an old world. \nNothing to worry about but challenges from previous mod versions will revert to being uncompleted!");
/*    */             } 
/*    */           }
/*    */         },ChallengesDataBase::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static IChallengesData get(PlayerEntity entity) {
/* 83 */     return (IChallengesData)entity.getCapability(INSTANCE, null).orElse(new ChallengesDataBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static LazyOptional<IChallengesData> getLazy(PlayerEntity entity) {
/* 88 */     return entity.getCapability(INSTANCE, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\ChallengesDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */