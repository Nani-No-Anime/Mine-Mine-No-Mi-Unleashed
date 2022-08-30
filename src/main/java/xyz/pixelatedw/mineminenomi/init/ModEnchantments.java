/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModEnchantments {
/* 11 */   public static final Enchantment DIAL_IMPACT = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
/* 12 */   public static final Enchantment DIAL_FLASH = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
/* 13 */   public static final Enchantment KAIROSEKI = (Enchantment)new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND });
/*    */   
/*    */   static {
/* 16 */     WyRegistry.registerEnchantment(DIAL_IMPACT, "Impact Dial");
/* 17 */     WyRegistry.registerEnchantment(DIAL_FLASH, "Flash Dial");
/* 18 */     WyRegistry.registerEnchantment(KAIROSEKI, "Kairoseki");
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */