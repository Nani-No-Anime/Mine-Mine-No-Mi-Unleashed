/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class ModCreativeTabs {
/* 10 */   public static final ItemGroup DEVIL_FRUITS = new ItemGroup("devil_fruits")
/*    */     {
/*    */       
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack createIcon()
/*    */       {
/* 16 */         return new ItemStack((IItemProvider)ModAbilities.MERA_MERA_NO_MI);
/*    */       }
/*    */     };
/*    */   
/* 20 */   public static final ItemGroup WEAPONS = new ItemGroup("weapons")
/*    */     {
/*    */       
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack createIcon()
/*    */       {
/* 26 */         return new ItemStack((IItemProvider)ModWeapons.YORU);
/*    */       }
/*    */     };
/*    */   
/* 30 */   public static final ItemGroup EQUIPMENT = new ItemGroup("equipment")
/*    */     {
/*    */       
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack createIcon()
/*    */       {
/* 36 */         return new ItemStack((IItemProvider)ModArmors.STRAW_HAT);
/*    */       }
/*    */     };
/*    */   
/* 40 */   public static final ItemGroup MISC = new ItemGroup("misc")
/*    */     {
/*    */       
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack createIcon()
/*    */       {
/* 46 */         return new ItemStack((IItemProvider)ModItems.KAIROSEKI);
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModCreativeTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */