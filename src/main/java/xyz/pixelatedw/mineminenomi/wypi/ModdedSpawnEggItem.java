/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import java.util.ArrayList;
import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.DispenserBlock;
/*    */ import net.minecraft.dispenser.DefaultDispenseItemBehavior;
/*    */ import net.minecraft.dispenser.IBlockSource;
/*    */ import net.minecraft.dispenser.IDispenseItemBehavior;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.SpawnEggItem;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.common.util.NonNullSupplier;
/*    */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModdedSpawnEggItem
/*    */   extends SpawnEggItem
/*    */ {
/* 36 */   protected static final List<ModdedSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
/*    */   
/*    */   private final Supplier<? extends EntityType<?>> entityTypeSupplier;
/*    */   
/*    */   public ModdedSpawnEggItem(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, int p_i48465_2_, int p_i48465_3_, Item.Properties p_i48465_4_) {
/* 41 */     super(null, p_i48465_2_, p_i48465_3_, p_i48465_4_);
/* 42 */     Objects.requireNonNull(entityTypeSupplier); this.entityTypeSupplier = entityTypeSupplier::get;
/* 43 */     UNADDED_EGGS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initUnaddedEggs() {
/* 54 */     Map<EntityType<?>, SpawnEggItem> EGGS =new HashMap<EntityType<?>, SpawnEggItem>();
/* 55 */     DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior()
/*    */       {
/*    */         
/*    */         public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
/*    */         {
/* 60 */           Direction direction = (Direction)source.getBlockState().get((IProperty)DispenserBlock.FACING);
/* 61 */           EntityType<?> entitytype = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
/* 62 */           entitytype.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, (direction != Direction.UP), false);
/* 63 */           stack.shrink(1);
/* 64 */           return stack;
/*    */         }
/*    */       };
/* 67 */     for (SpawnEggItem egg : UNADDED_EGGS) {
/*    */       
/* 69 */       EGGS.put(egg.getType(null), egg);
/* 70 */       DispenserBlock.registerDispenseBehavior((IItemProvider)egg, (IDispenseItemBehavior)defaultDispenseItemBehavior);
/*    */     } 
/*    */     
/* 73 */     UNADDED_EGGS.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
/* 79 */     return this.entityTypeSupplier.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\ModdedSpawnEggItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */