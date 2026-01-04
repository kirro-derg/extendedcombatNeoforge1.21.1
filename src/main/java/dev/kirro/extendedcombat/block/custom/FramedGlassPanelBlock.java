package dev.kirro.extendedcombat.block.custom;

import net.minecraft.core.BlockBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class FramedGlassPanelBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static final Map<Direction, VoxelShape> BASE = new EnumMap<>(Direction.class);
    private static final Map<Direction, VoxelShape> HOLDING = new EnumMap<>(Direction.class);

    static {
        BASE.put(Direction.NORTH, Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f / 16.0f));
        BASE.put(Direction.SOUTH, Shapes.create(0.0f, 0.0f, 15.0f / 16.0f, 1.0f, 1.0f, 1.0f));
        BASE.put(Direction.EAST, Shapes.create(15.0f / 16.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BASE.put(Direction.WEST, Shapes.create(0.0f, 0.0f, 0.0f, 1.0f / 16.0f, 1.0f, 1.0f));
        BASE.put(Direction.UP, Shapes.create(0.0f, 15.0f / 16.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BASE.put(Direction.DOWN, Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f / 16.0f, 1.0f));

        HOLDING.put(Direction.NORTH, Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 8.0f / 16.0f));
        HOLDING.put(Direction.SOUTH, Shapes.create(0.0f, 0.0f, 8.0 / 16.0f, 1.0f, 1.0f, 1.0f));
        HOLDING.put(Direction.EAST, Shapes.create(8.0f / 16.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        HOLDING.put(Direction.WEST, Shapes.create(0.0f, 0.0f, 0.0f, 8.0f / 16.0f, 1.0f, 1.0f));
        HOLDING.put(Direction.UP, Shapes.create(0.0f, 8.0f / 16.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        HOLDING.put(Direction.DOWN, Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 8.0f / 16.0f, 1.0f));
    }

    public FramedGlassPanelBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        Direction dir = state.getValue(FACING);
        boolean isHoldingBlock = false;

        if (ctx instanceof EntityCollisionContext entityCtx && entityCtx.getEntity() instanceof LivingEntity living) {
            ItemStack main = living.getMainHandItem();
            ItemStack off = living.getOffhandItem();
            isHoldingBlock = main.is(this.asItem()) || off.is(this.asItem());
        }

        return  isHoldingBlock ? HOLDING.get(dir) : BASE.get(dir);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction side = ctx.getClickedFace().getOpposite();
        BlockPos pos = ctx.getClickedPos();
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockState clickedState = level.getBlockState(pos.relative(side));
        boolean sneaking = player != null && player.isCrouching();

        if (sneaking && !player.isCreative() && level.getBlockState(pos).is(Blocks.WATER) && level.getFluidState(pos).is(Fluids.WATER)) {
            return this.defaultBlockState().setValue(WATERLOGGED, true).setValue(FACING, side);
        } else {
            if (!sneaking && clickedState.getBlock() instanceof FramedGlassPanelBlock && !clickedState.getValue(FACING).getAxis().equals(side.getAxis())) {
                side = clickedState.getValue(FACING);
            }
            FluidState state = level.getFluidState(pos);
            return this.defaultBlockState()
                    .setValue(WATERLOGGED, state.isSourceOfType(Fluids.WATER))
                    .setValue(FACING, side);
        }
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        if (adjacentState.is(this)) {
            Direction adjacentFacing = adjacentState.getValue(FACING);
            Direction currentFacing = state.getValue(FACING);
            return adjacentFacing == direction && currentFacing == direction.getOpposite()
                    || adjacentFacing == direction.getOpposite() && currentFacing == direction
                    || adjacentFacing == currentFacing;
        }
        return super.skipRendering(state, adjacentState, direction);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState() : super.getFluidState(state);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }
}