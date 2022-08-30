/*     */ package xyz.pixelatedw.mineminenomi.data.entity.quests;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ public class QuestDataCapability
/*     */ {
/*     */   @CapabilityInject(IQuestData.class)
/*  23 */   public static final Capability<IQuestData> INSTANCE = null;
/*     */ 
/*     */   
/*     */   public static void register() {
/*  27 */     CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>()
/*     */         {
/*     */           
/*     */           public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side)
/*     */           {
/*  32 */             CompoundNBT props = new CompoundNBT();
/*     */             
/*  34 */             ListNBT questsInTracker = new ListNBT();
/*  35 */             for (int i = 0; i < (instance.getInProgressQuests()).length; i++) {
/*     */               
/*  37 */               Quest quest = instance.getInProgressQuests()[i];
/*  38 */               if (quest != null)
/*  39 */                 questsInTracker.add(quest.save()); 
/*     */             } 
/*  41 */             props.put("quests_in_tracker", (INBT)questsInTracker);
/*     */             
/*  43 */             ListNBT finishedQuests = new ListNBT();
/*  44 */             for (int j = 0; j < instance.getFinishedQuests().size(); j++) {
/*     */               
/*  46 */               Quest quest = instance.getFinishedQuests().get(j);
/*  47 */               CompoundNBT questNbt = new CompoundNBT();
/*  48 */               questNbt.putString("id", quest.getId());
/*  49 */               finishedQuests.add(questNbt);
/*     */             } 
/*  51 */             props.put("finished_quests", (INBT)finishedQuests);
/*     */             
/*  53 */             return (INBT)props;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt) {
/*  59 */             CompoundNBT props = (CompoundNBT)nbt;
/*     */             
/*  61 */             instance.clearInProgressQuests();
/*  62 */             instance.clearFinishedQuests();
/*     */             
/*  64 */             ListNBT trackerQuests = props.getList("quests_in_tracker", 10);
/*  65 */             for (int i = 0; i < trackerQuests.size(); i++) {
/*     */               
/*  67 */               CompoundNBT nbtQuests = trackerQuests.getCompound(i);
/*  68 */               Quest quest = (Quest)GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(APIConfig.projectId, nbtQuests.getString("id")));
/*     */               
/*     */               try {
/*  71 */                 Quest newQuest = quest.create();
/*  72 */                 newQuest.load(nbtQuests);
/*  73 */                 instance.setInProgressQuest(i, newQuest);
/*     */               }
/*  75 */               catch (Exception e) {
/*     */                 
/*  77 */                 WyDebug.debug("Unregistered quest: " + nbtQuests.getString("id"));
/*     */               } 
/*     */             } 
/*     */             
/*  81 */             ListNBT finishedQuests = props.getList("finished_quests", 10);
/*  82 */             for (int j = 0; j < finishedQuests.size(); j++) {
/*     */               
/*  84 */               CompoundNBT nbtQuests = finishedQuests.getCompound(j);
/*  85 */               Quest quest = (Quest)GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(APIConfig.projectId, nbtQuests.getString("id")));
/*     */               
/*     */               try {
/*  88 */                 instance.addFinishedQuest(quest.create());
/*     */               }
/*  90 */               catch (Exception e) {
/*     */                 
/*  92 */                 WyDebug.debug("Unregistered quest: " + nbtQuests.getString("id"));
/*     */               } 
/*     */             } 
/*     */           }
/*     */         },QuestDataBase::new);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IQuestData get(PlayerEntity entity) {
/* 102 */     return (IQuestData)entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
/*     */   }
/*     */ 
/*     */   
/*     */   public static LazyOptional<IQuestData> getLazy(PlayerEntity entity) {
/* 107 */     return entity.getCapability(INSTANCE, null);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\entity\quests\QuestDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */