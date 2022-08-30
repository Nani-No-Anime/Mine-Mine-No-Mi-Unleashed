/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.CrewEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ 
/*     */ public class SakeCupItem
/*     */   extends Item
/*     */ {
/*     */   private IItemPropertyGetter filledProperty;
/*     */   
/*     */   public SakeCupItem() {
/*  41 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1).food(ModFoods.ALCOHOL)); this.filledProperty = ((itemStack, world, livingEntity) -> (itemStack.getTag() != null && !WyHelper.isNullOrEmpty(itemStack.getTag().getString("leader"))) ? 1.0F : 0.0F);
/*  42 */     addPropertyOverride(new ResourceLocation("filled"), this.filledProperty);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  48 */     if (getLeader(player.getHeldItemMainhand(), world) != null) {
/*     */       
/*  50 */       player.setActiveHand(hand);
/*     */     }
/*     */     else {
/*     */       
/*  54 */       int slot = WyHelper.getIndexOfItemStack(ModItems.SAKE_BOTTLE, (IInventory)player.inventory);
/*  55 */       if (slot >= 0) {
/*     */         
/*  57 */         ItemStack stack = player.inventory.getStackInSlot(slot);
/*  58 */         stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */ 
/*     */         
/*  61 */         setLeader(player.getHeldItemMainhand(), player);
/*  62 */         return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */       } 
/*     */     } 
/*  65 */     return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/*  71 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*     */       
/*  73 */       PlayerEntity player = (PlayerEntity)entity;
/*  74 */       PlayerEntity leader = getLeader(itemStack, player.world);
/*     */       
/*  76 */       if (leader != null) {
/*     */         
/*  78 */         ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/*  79 */         Crew crew = worldProps.getCrewWithCaptain(leader.getUniqueID());
/*  80 */         if (crew == null) {
/*  81 */           WyDebug.debug("Cannot find a crew for captain " + leader.getName().getFormattedText());
/*     */         } else {
/*     */           
/*  84 */           CrewEvent.Join event = new CrewEvent.Join(player, crew);
/*  85 */           if (!MinecraftForge.EVENT_BUS.post(event))
/*     */           {
/*  87 */             if (!crew.hasMember(player.getUniqueID())) {
/*     */               
/*  89 */               worldProps.addCrewMember(crew, (LivingEntity)player);
/*  90 */               FactionHelper.sendUpdateMessageToCrew(world, crew);
/*  91 */               FactionHelper.sendMessageToCrew(world, crew, (ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_JOIN, new Object[] { player.getName().getFormattedText() }));
/*  92 */               itemStack.getOrCreateTag().putInt("leader", 0);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  98 */       player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModItems.SAKE_CUP));
/*  99 */       itemStack.shrink(1);
/*     */     } 
/*     */     
/* 102 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeader(ItemStack itemStack, PlayerEntity leader) {
/* 107 */     itemStack.setTag(new CompoundNBT());
/* 108 */     itemStack.getTag().putString("leader", leader.getUniqueID().toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getLeader(ItemStack itemStack, World world) {
/* 113 */     if (!itemStack.hasTag())
/* 114 */       itemStack.setTag(new CompoundNBT()); 
/* 115 */     String leaderUUID = itemStack.getTag().getString("leader");
/* 116 */     if (!WyHelper.isNullOrEmpty(leaderUUID))
/* 117 */       return world.getPlayerByUuid(UUID.fromString(leaderUUID)); 
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAction getUseAction(ItemStack stack) {
/* 124 */     return UseAction.DRINK;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\SakeCupItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */