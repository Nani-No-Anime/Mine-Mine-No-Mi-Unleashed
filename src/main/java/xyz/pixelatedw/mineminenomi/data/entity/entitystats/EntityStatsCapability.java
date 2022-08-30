/*    */ package xyz.pixelatedw.mineminenomi.data.entity.entitystats;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ 
/*    */ public class EntityStatsCapability
/*    */ {
/*    */   @CapabilityInject(IEntityStats.class)
/* 14 */   public static final Capability<IEntityStats> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 18 */     CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
/*    */         {
/*    */ 
/*    */           
/*    */           public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
/*    */           {
/* 24 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 26 */             props.putInt("doriki", instance.getDoriki());
/* 27 */             props.putInt("cola", instance.getCola());
/* 28 */             props.putInt("maxCola", instance.getMaxCola());
/* 29 */             props.putInt("ultraCola", instance.getUltraCola());
/* 30 */             props.putInt("loyalty", instance.getLoyalty());
/* 31 */             props.putDouble("damageMultiplier", instance.getDamageMultiplier());
/* 32 */             props.putLong("bounty", instance.getBounty());
/* 33 */             props.putLong("belly", instance.getBelly());
/* 34 */             props.putLong("extol", instance.getExtol());
/* 35 */             props.putString("faction", instance.getFaction());
/* 36 */             props.putString("race", instance.getRace());
/* 37 */             props.putString("subRace", instance.getSubRace());
/* 38 */             props.putString("fightingStyle", instance.getFightingStyle());
/* 39 */             props.putBoolean("hasShadow", instance.hasShadow());
/* 40 */             props.putBoolean("hasHeart", instance.hasHeart());
/* 41 */             props.putBoolean("hasStrawDoll", instance.hasStrawDoll());
/* 42 */             props.putBoolean("isInCombatMode", instance.isInCombatMode());
/* 43 */             props.putBoolean("isRogue", instance.isRogue());
/*    */             
/* 45 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt) {
/* 51 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 53 */             instance.setDoriki(props.getInt("doriki"));
/* 54 */             instance.setCola(props.getInt("cola"));
/* 55 */             instance.setMaxCola(props.getInt("maxCola"));
/* 56 */             instance.setUltraCola(props.getInt("ultraCola"));
/* 57 */             instance.setLoyalty(props.getInt("loyalty"));
/* 58 */             instance.setDamageMultiplier(props.getDouble("damageMultiplier"));
/* 59 */             instance.setBelly(props.getLong("belly"));
/* 60 */             instance.setBounty(props.getLong("bounty"));
/* 61 */             instance.setExtol(props.getLong("extol"));
/* 62 */             instance.setFaction(props.getString("faction"));
/* 63 */             instance.setRace(props.getString("race"));
/* 64 */             instance.setSubRace(props.getString("subRace"));
/* 65 */             instance.setFightingStyle(props.getString("fightingStyle"));
/* 66 */             instance.setShadow(props.getBoolean("hasShadow"));
/* 67 */             instance.setHeart(props.getBoolean("hasHeart"));
/* 68 */             instance.setStrawDoll(props.getBoolean("hasStrawDoll"));
/* 69 */             instance.setCombatMode(props.getBoolean("isInCombatMode"));
/* 70 */             instance.setRogue(props.getBoolean("isRogue"));
/*    */           }
/*    */         },EntityStatsBase::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static IEntityStats get(LivingEntity entity) {
/* 78 */     return (IEntityStats)entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\EntityStatsCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */