/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryBuilder;
/*    */ import net.minecraftforge.registries.RegistryManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class APIRegistries
/*    */ {
/*    */   static {
/* 15 */     make(new ResourceLocation(APIConfig.projectId, "abilities"), Ability.class);
/* 16 */     make(new ResourceLocation(APIConfig.projectId, "quests"), Quest.class);
/*    */   }
/*    */   
/* 19 */   public static final IForgeRegistry<Ability> ABILITIES = RegistryManager.ACTIVE.getRegistry(Ability.class);
/* 20 */   public static final IForgeRegistry<Quest> QUESTS = RegistryManager.ACTIVE.getRegistry(Quest.class);
/*    */ 
/*    */   
/*    */   public static <T extends net.minecraftforge.registries.IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type) {
/* 24 */     (new RegistryBuilder()).setName(name).setType(type).setMaxID(2147483646).create();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\APIRegistries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */