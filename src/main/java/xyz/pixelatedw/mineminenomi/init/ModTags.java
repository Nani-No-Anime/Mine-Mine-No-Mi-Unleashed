/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.tags.BlockTags;
/*    */ import net.minecraft.tags.EntityTypeTags;
/*    */ import net.minecraft.tags.ItemTags;
/*    */ import net.minecraft.tags.Tag;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class ModTags
/*    */ {
/*    */   public static class Items
/*    */   {
/* 17 */     public static final Tag<Item> KAIROSEKI = tag("kairoseki");
/* 18 */     public static final Tag<Item> MAGNETIC = tag("magnetic");
/* 19 */     public static final Tag<Item> RUSTY = tag("rusty");
/*    */ 
/*    */     
/*    */     private static Tag<Item> tag(String id) {
/* 23 */       return (Tag<Item>)new ItemTags.Wrapper(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Blocks
/*    */   {
/* 29 */     public static final Tag<Block> NO_PHASE = tag("nophase");
/* 30 */     public static final Tag<Block> RUSTY = tag("rusty");
/*    */ 
/*    */     
/*    */     private static Tag<Block> tag(String id) {
/* 34 */       return (Tag<Block>)new BlockTags.Wrapper(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Entities
/*    */   {
/* 40 */     public static final Tag<EntityType<?>> MAGNETIC = tag("magnetic");
/*    */ 
/*    */     
/*    */     private static Tag<EntityType<?>> tag(String id) {
/* 44 */       return (Tag<EntityType<?>>)new EntityTypeTags.Wrapper(new ResourceLocation("mineminenomi", id));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */