package xyz.pixelatedw.mineminenomi.api.entities;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.api.enums.Currency;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class TraderEntity extends OPEntity {
  protected List<TradeEntry> tradeEntries = new ArrayList<>();
  private boolean isTrading = false;
  private boolean canBuy;
  
  public TraderEntity(EntityType type, World world) {
				this(type, world, (String[])null);
   
  }

  
  public TraderEntity(EntityType type, World world, String[] textures) {
    super(type, world, textures);
   }


  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal(this));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
  }


  
  protected void registerData() {
    super.registerData();
  }

  
  public abstract boolean canTrade(PlayerEntity paramPlayerEntity);

  
  public abstract String getTradeFailMessage();
  
  public abstract ResourceLocation getTradeTable();
  
  public abstract Currency getCurrency();
  
  public void tick() {
    super.tick();
    
    List<PlayerEntity> customers = WyHelper.getEntitiesNear(getPosition(), this.world, 3.0D, new Class[] { PlayerEntity.class });
    if (customers.size() > 0) {
      
      lookAt(EntityAnchorArgument.Type.EYES, ((PlayerEntity)customers.get(0)).getEyePosition(0.0F));
      addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 40, 0, false, false));
    } 
  }


  
  public void writeAdditional(CompoundNBT nbt) {
    super.writeAdditional(nbt);
    
    ListNBT items = new ListNBT();
    for (TradeEntry stack : this.tradeEntries) {
      
      CompoundNBT nbtTrade = new CompoundNBT();
      nbtTrade = stack.getItemStack().write(nbtTrade);
      items.add(nbtTrade);
    } 
    nbt.put("Items", (INBT)items);
  }


  
  public void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    
    ListNBT tradeList = nbt.getList("Items", 10);
    for (int i = 0; i < tradeList.size(); i++) {
      
      CompoundNBT nbtTrade = tradeList.getCompound(i);
      ItemStack stack = ItemStack.read(nbtTrade);
      this.tradeEntries.add(new TradeEntry(stack));
    } 
  }


  
  public void setStacksFromNBT(CompoundNBT nbt) {
    ListNBT tradeList = nbt.getList("Items", 10);
    for (int i = 0; i < tradeList.size(); i++) {
      
      CompoundNBT nbtTrade = tradeList.getCompound(i);
      ItemStack stack = ItemStack.read(nbtTrade);
      this.tradeEntries.add(new TradeEntry(stack));
    } 
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

    
    LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(getTradeTable());
    LootContext.Builder builder = new LootContext.Builder((ServerWorld)this.world);
    
    LootContext context = builder.build(LootParameterSets.EMPTY);
    List<ItemStack> stacks = lootTable.generate(context);
    
    for (ItemStack stack : stacks)
    {
      this.tradeEntries.add(new TradeEntry(stack));
    }
    
    return spawnData;
  }

  
  public List<TradeEntry> getTradingItems() {
    return this.tradeEntries;
  }

  
  public void setIsTrading(boolean flag) {
    this.isTrading = flag;
  }

  
  public void setTradingItems(List<TradeEntry> entries) {
    this.tradeEntries = entries;
  }

  
  public boolean canBuyFromPlayers() {
    return this.canBuy;
  }

  
  public void setCanBuyFromPlayers() {
    this.canBuy = true;
  }


  
  protected boolean processInteract(PlayerEntity player, Hand hand) {
    if (!player.world.isRemote) {
      
      WyNetwork.sendTo(new SOpenTraderScreenPacket(getEntityId()), player);
      WyNetwork.sendTo(new SUpdateTraderOffersPacket(getEntityId(), this.tradeEntries), player);
      return true;
    } 
    return false;
  }
}