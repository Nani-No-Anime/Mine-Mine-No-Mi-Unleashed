/*    */ package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ 
/*    */ public class DevilFruitCapability
/*    */ {
/*    */   @CapabilityInject(IDevilFruit.class)
/* 14 */   public static final Capability<IDevilFruit> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 18 */     CapabilityManager.INSTANCE.register(IDevilFruit.class, new Capability.IStorage<IDevilFruit>()
/*    */         {
/*    */           
/*    */           public INBT writeNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side)
/*    */           {
/* 23 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 25 */             props.putString("devilFruit", instance.getDevilFruit());
/* 26 */             props.putBoolean("isLogia", instance.isLogia());
/* 27 */             props.putBoolean("hasYamiPower", instance.hasYamiPower());
/* 28 */             props.putString("zoanPoint", instance.getZoanPoint());
/*    */             
/* 30 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side, INBT nbt) {
/* 36 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 38 */             instance.setDevilFruit(props.getString("devilFruit"));
/* 39 */             instance.setLogia(props.getBoolean("isLogia"));
/* 40 */             instance.setYamiPower(props.getBoolean("hasYamiPower"));
/* 41 */             instance.setZoanPoint(props.getString("zoanPoint"));
/*    */           }
/*    */         },DevilFruitBase::new);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static IDevilFruit get(LivingEntity entity) {
/* 49 */     return (IDevilFruit)entity.getCapability(INSTANCE, null).orElse(new DevilFruitBase());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\DevilFruitCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */