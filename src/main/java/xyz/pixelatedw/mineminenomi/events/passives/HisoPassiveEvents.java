/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.nbt.StringNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HisoPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 27 */     if (!(event.getEntityLiving() instanceof net.minecraft.entity.passive.AnimalEntity)) {
/*    */       return;
/*    */     }
/* 30 */     if (event.getEntityLiving().isPotionActive(ModEffects.ANIMAL_LOOKOUT)) {
/*    */       
/* 32 */       LivingEntity animal = event.getEntityLiving();
/* 33 */       if (animal.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() != Items.COBBLESTONE) {
/*    */         
/* 35 */         ItemStack itemStack = new ItemStack((IItemProvider)Items.COBBLESTONE);
/* 36 */         CompoundNBT nbt = new CompoundNBT();
/* 37 */         nbt.put("seen", (INBT)new ListNBT());
/* 38 */         itemStack.setTag(nbt);
/* 39 */         animal.setItemStackToSlot(EquipmentSlotType.CHEST, itemStack);
/*    */       } 
/* 41 */       List<LivingEntity> around = WyHelper.getEntitiesNear(animal.getPosition(), animal.world, 10.0D);
/* 42 */       around.remove(animal);
/* 43 */       CompoundNBT tag = animal.getItemStackFromSlot(EquipmentSlotType.CHEST).getTag();
/* 44 */       ListNBT seen = (ListNBT)tag.get("seen");
/* 45 */       around.forEach(entity -> {
/*    */             INBT hasn = null;
/*    */             
/*    */             for (INBT previous : seen) {
/*    */               if (previous.getString().split(" ")[0].equals(entity.getName().getString())) {
/*    */                 hasn = previous;
/*    */               }
/*    */             } 
/*    */             
/*    */             if (hasn != null) {
/*    */               seen.remove(hasn);
/*    */             }
/*    */             
/*    */             seen.add(StringNBT.valueOf(entity.getName().getString() + " " + entity.world.getGameTime()));
/*    */           });
/* 60 */       tag.put("seen", (INBT)seen);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\HisoPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */