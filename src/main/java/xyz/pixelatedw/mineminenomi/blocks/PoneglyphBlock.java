package xyz.pixelatedw.mineminenomi.blocks;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.init.ModChallenges;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PoneglyphBlock extends Block {
  public static final IntegerProperty TEXTURE = IntegerProperty.create("texture", 0, 2);

  
  public PoneglyphBlock() {
    super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(Float.MAX_VALUE).noDrops());
    setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)TEXTURE, Integer.valueOf(0)));
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  public void initPoneglyph(IWorld world, BlockPos pos) {
    String category = "";
    for (BlockPos checkPos : WyHelper.getNearbyBlocks(pos, world, 1)) {
      
      TileEntity te = world.getTileEntity(checkPos);
      if (checkPos.equals(pos)) {
        continue;
      }
      if (te instanceof PoneglyphTileEntity) {
        
        category = ((PoneglyphTileEntity)te).getEntryCategory();
        
        break;
      } 
    } 
    PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.getTileEntity(pos);
    List<Challenge> challenges = new ArrayList<>();
    ModChallenges.CHALLENGES.getEntries().stream().forEach(ro -> challenges.add((Challenge)ro.get()));
    Map<String, List<Challenge>> map = (Map<String, List<Challenge>>)challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
    int max = map.size() - 1;
    int id = (int)WyHelper.randomWithRange(0, max);
    category = (String)map.keySet().toArray()[id];
    tileEntity.setEntryCategory(category);
  }


  
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    BlockState blockstate = getDefaultState();
    blockstate = (BlockState)blockstate.with((IProperty)TEXTURE, Integer.valueOf(context.getWorld().getRandom().nextInt(3)));
    
    return blockstate;
  }


  
  public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.getTileEntity(pos);
    
    if (world.isRemote) {
      return ActionResultType.SUCCESS;
    }
    if (WyHelper.isNullOrEmpty(tileEntity.getEntryCategory())) {
      initPoneglyph((IWorld)world, pos);
    }
    if (tileEntity.getEntryType() == PoneglyphTileEntity.Type.CHALLENGE) {
      
      boolean hasPaper = player.inventory.hasAny((Set)ImmutableSet.of(Items.PAPER));
      if (hasPaper) {
        
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
          
          ItemStack stack = player.inventory.getStackInSlot(i);
          if (stack != null && !stack.isEmpty() && stack.getItem() == Items.PAPER && stack.getOrCreateTag().getInt("type") > 0 && stack.getOrCreateTag().getString("challenge").equals(tileEntity.getEntryCategory())) {
            
            player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION_ALREADY_COPIED, new Object[0]));
            return ActionResultType.SUCCESS;
          } 
          
          if (stack != null && !stack.isEmpty() && stack.getItem() == Items.PAPER && stack.getOrCreateTag().getInt("type") <= 0)
          {
            stack.shrink(1);
            ItemStack note = new ItemStack((IItemProvider)Items.PAPER);
            note.getOrCreateTag().putInt("type", tileEntity.getEntryType().ordinal() + 1);
            note.getOrCreateTag().putString("challenge", tileEntity.getEntryCategory());
            note.getOrCreateTag().putBoolean("encrypted", true);
            note.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_PONEGLYPH_ENCRYPTED_NOTE, new Object[0]));
            player.inventory.addItemStackToInventory(note);
            player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION, new Object[0]));
            return ActionResultType.SUCCESS;
          }
        
        } 
      } else {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION_NO_PAPER, new Object[0]));
      } 
    } 
    
    return ActionResultType.SUCCESS;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new PoneglyphTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)TEXTURE });
  }
}


