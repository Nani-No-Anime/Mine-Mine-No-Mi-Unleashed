/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.IntegerProperty;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.PoneglyphTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PoneglyphBlock extends Block {
/*  38 */   public static final IntegerProperty TEXTURE = IntegerProperty.create("texture", 0, 2);
/*     */ 
/*     */   
/*     */   public PoneglyphBlock() {
/*  42 */     super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(Float.MAX_VALUE).noDrops());
/*  43 */     setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)TEXTURE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initPoneglyph(IWorld world, BlockPos pos) {
/*  55 */     String category = "";
/*  56 */     for (BlockPos checkPos : WyHelper.getNearbyBlocks(pos, world, 1)) {
/*     */       
/*  58 */       TileEntity te = world.getTileEntity(checkPos);
/*  59 */       if (checkPos.equals(pos)) {
/*     */         continue;
/*     */       }
/*  62 */       if (te instanceof PoneglyphTileEntity) {
/*     */         
/*  64 */         category = ((PoneglyphTileEntity)te).getEntryCategory();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  69 */     PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.getTileEntity(pos);
/*  70 */     List<Challenge> challenges = new ArrayList<>();
/*  71 */     ModChallenges.CHALLENGES.getEntries().stream().forEach(ro -> challenges.add((Challenge)ro.get()));
/*  72 */     Map<String, List<Challenge>> map = (Map<String, List<Challenge>>)challenges.stream().collect(Collectors.groupingBy(Challenge::getCategory));
/*  73 */     int max = map.size() - 1;
/*  74 */     int id = (int)WyHelper.randomWithRange(0, max);
/*  75 */     category = (String)map.keySet().toArray()[id];
/*  76 */     tileEntity.setEntryCategory(category);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/*  83 */     BlockState blockstate = getDefaultState();
/*  84 */     blockstate = (BlockState)blockstate.with((IProperty)TEXTURE, Integer.valueOf(context.getWorld().getRandom().nextInt(3)));
/*     */     
/*  86 */     return blockstate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
/*  92 */     PoneglyphTileEntity tileEntity = (PoneglyphTileEntity)world.getTileEntity(pos);
/*     */     
/*  94 */     if (world.isRemote) {
/*  95 */       return ActionResultType.SUCCESS;
/*     */     }
/*  97 */     if (WyHelper.isNullOrEmpty(tileEntity.getEntryCategory())) {
/*  98 */       initPoneglyph((IWorld)world, pos);
/*     */     }
/* 100 */     if (tileEntity.getEntryType() == PoneglyphTileEntity.Type.CHALLENGE) {
/*     */       
/* 102 */       boolean hasPaper = player.inventory.hasAny((Set)ImmutableSet.of(Items.PAPER));
/* 103 */       if (hasPaper) {
/*     */         
/* 105 */         for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */           
/* 107 */           ItemStack stack = player.inventory.getStackInSlot(i);
/* 108 */           if (stack != null && !stack.isEmpty() && stack.getItem() == Items.PAPER && stack.getOrCreateTag().getInt("type") > 0 && stack.getOrCreateTag().getString("challenge").equals(tileEntity.getEntryCategory())) {
/*     */             
/* 110 */             player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION_ALREADY_COPIED, new Object[0]));
/* 111 */             return ActionResultType.SUCCESS;
/*     */           } 
/*     */           
/* 114 */           if (stack != null && !stack.isEmpty() && stack.getItem() == Items.PAPER && stack.getOrCreateTag().getInt("type") <= 0)
/*     */           {
/* 116 */             stack.shrink(1);
/* 117 */             ItemStack note = new ItemStack((IItemProvider)Items.PAPER);
/* 118 */             note.getOrCreateTag().putInt("type", tileEntity.getEntryType().ordinal() + 1);
/* 119 */             note.getOrCreateTag().putString("challenge", tileEntity.getEntryCategory());
/* 120 */             note.getOrCreateTag().putBoolean("encrypted", true);
/* 121 */             note.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_PONEGLYPH_ENCRYPTED_NOTE, new Object[0]));
/* 122 */             player.inventory.addItemStackToInventory(note);
/* 123 */             player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION, new Object[0]));
/* 124 */             return ActionResultType.SUCCESS;
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 130 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_INSCRIPTION_NO_PAPER, new Object[0]));
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     return ActionResultType.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 140 */     return (TileEntity)new PoneglyphTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 152 */     builder.add(new IProperty[] { (IProperty)TEXTURE });
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\PoneglyphBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */