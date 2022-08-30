/*    */ package xyz.pixelatedw.mineminenomi.data.entity.haki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ 
/*    */ public class HakiDataCapability
/*    */ {
/*    */   @CapabilityInject(IHakiData.class)
/* 14 */   public static final Capability<IHakiData> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 18 */     CapabilityManager.INSTANCE.register(IHakiData.class, new Capability.IStorage<IHakiData>()
/*    */         {
/*    */           
/*    */           public INBT writeNBT(Capability<IHakiData> capability, IHakiData instance, Direction side)
/*    */           {
/* 23 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 25 */             props.putFloat("kenHakiExp", instance.getKenbunshokuHakiExp());
/* 26 */             props.putFloat("busoHardeningHakiExp", instance.getBusoshokuHardeningHakiExp());
/* 27 */             props.putFloat("busoImbuingHakiExp", instance.getBusoshokuImbuingHakiExp());
/* 28 */             props.putInt("hakiOveruse", instance.getHakiOveruse());
/*    */             
/* 30 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IHakiData> capability, IHakiData instance, Direction side, INBT nbt) {
/* 36 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 38 */             instance.setKenbunshokuHakiExp(props.getFloat("kenHakiExp"));
/* 39 */             instance.setBusoshokuHardeningHakiExp(props.getFloat("busoHardeningHakiExp"));
/* 40 */             instance.setBusoshokuImbuingHakiExp(props.getFloat("busoImbuingHakiExp"));
/* 41 */             instance.setHakiOveruse(props.getInt("hakiOveruse"));
/*    */           }
/*    */         },HakiDataBase::new);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static IHakiData get(LivingEntity entity) {
/* 50 */     return (IHakiData)entity.getCapability(INSTANCE, null).orElse(new HakiDataBase());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\HakiDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */