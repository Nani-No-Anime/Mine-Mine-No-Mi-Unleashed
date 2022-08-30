/*     */ package xyz.pixelatedw.mineminenomi.api.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.loot.LootContext;
/*     */ import net.minecraft.world.storage.loot.LootParameterSets;
/*     */ import net.minecraft.world.storage.loot.LootTable;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public abstract class TraderEntity extends OPEntity {
/*  43 */   protected List<TradeEntry> tradeEntries = new ArrayList<>();
/*     */   
/*     */   private boolean isTrading = false;
/*     */   private boolean canBuy;
/*     */   
/*     */   public TraderEntity(EntityType type, World world) {
/*  49 */     this(type, world, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TraderEntity(EntityType type, World world, String[] textures) {
/*  54 */     super(type, world, textures);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  60 */     super.registerGoals();
/*  61 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  62 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  63 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  64 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  70 */     super.registerAttributes();
/*  71 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
/*  72 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/*  73 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*  74 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  75 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  81 */     super.registerData();
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean canTrade(PlayerEntity paramPlayerEntity);
/*     */ 
/*     */   
/*     */   public abstract String getTradeFailMessage();
/*     */   
/*     */   public abstract ResourceLocation getTradeTable();
/*     */   
/*     */   public abstract Currency getCurrency();
/*     */   
/*     */   public void tick() {
/*  95 */     super.tick();
/*     */     
/*  97 */     List<PlayerEntity> customers = WyHelper.getEntitiesNear(getPosition(), this.world, 3.0D, new Class[] { PlayerEntity.class });
/*  98 */     if (customers.size() > 0) {
/*     */       
/* 100 */       lookAt(EntityAnchorArgument.Type.EYES, ((PlayerEntity)customers.get(0)).getEyePosition(0.0F));
/* 101 */       addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 40, 0, false, false));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT nbt) {
/* 108 */     super.writeAdditional(nbt);
/*     */     
/* 110 */     ListNBT items = new ListNBT();
/* 111 */     for (TradeEntry stack : this.tradeEntries) {
/*     */       
/* 113 */       CompoundNBT nbtTrade = new CompoundNBT();
/* 114 */       nbtTrade = stack.getItemStack().write(nbtTrade);
/* 115 */       items.add(nbtTrade);
/*     */     } 
/* 117 */     nbt.put("Items", (INBT)items);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT nbt) {
/* 123 */     super.readAdditional(nbt);
/*     */     
/* 125 */     ListNBT tradeList = nbt.getList("Items", 10);
/* 126 */     for (int i = 0; i < tradeList.size(); i++) {
/*     */       
/* 128 */       CompoundNBT nbtTrade = tradeList.getCompound(i);
/* 129 */       ItemStack stack = ItemStack.read(nbtTrade);
/* 130 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStacksFromNBT(CompoundNBT nbt) {
/* 137 */     ListNBT tradeList = nbt.getList("Items", 10);
/* 138 */     for (int i = 0; i < tradeList.size(); i++) {
/*     */       
/* 140 */       CompoundNBT nbtTrade = tradeList.getCompound(i);
/* 141 */       ItemStack stack = ItemStack.read(nbtTrade);
/* 142 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 150 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */ 
/*     */     
/* 153 */     LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(getTradeTable());
/* 154 */     LootContext.Builder builder = new LootContext.Builder((ServerWorld)this.world);
/*     */     
/* 156 */     LootContext context = builder.build(LootParameterSets.EMPTY);
/* 157 */     List<ItemStack> stacks = lootTable.generate(context);
/*     */     
/* 159 */     for (ItemStack stack : stacks)
/*     */     {
/* 161 */       this.tradeEntries.add(new TradeEntry(stack));
/*     */     }
/*     */     
/* 164 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TradeEntry> getTradingItems() {
/* 169 */     return this.tradeEntries;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsTrading(boolean flag) {
/* 174 */     this.isTrading = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTradingItems(List<TradeEntry> entries) {
/* 179 */     this.tradeEntries = entries;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBuyFromPlayers() {
/* 184 */     return this.canBuy;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanBuyFromPlayers() {
/* 189 */     this.canBuy = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean processInteract(PlayerEntity player, Hand hand) {
/* 195 */     if (!player.world.isRemote) {
/*     */       
/* 197 */       WyNetwork.sendTo(new SOpenTraderScreenPacket(getEntityId()), player);
/* 198 */       WyNetwork.sendTo(new SUpdateTraderOffersPacket(getEntityId(), this.tradeEntries), player);
/* 199 */       return true;
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\entities\TraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */