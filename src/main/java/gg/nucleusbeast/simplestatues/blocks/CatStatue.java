package gg.nucleusbeast.simplestatues.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CatStatue extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CatStatue(Properties pProperties) {
        super(pProperties);
    }

    private static final VoxelShape SHAPE = /*Stream.of(
            Block.box(5.5, 11, -6, 10.5, 15, -1),
            Block.box(6.5, 11.02, -7, 9.5, 13.02, -5),
            Block.box(9, 15, -3, 10, 16, -1),
            Block.box(6, 15, -3, 7, 16, -1),
            Block.box(6, 8, -1, 10, 14, 15),
            Block.box(5.9, 3.9, 1, 7.9, 10.9, 3),
            Block.box(8.1, 3.9, 1, 10.1, 10.9, 3),
            Block.box(5.9, 3.9, 12, 7.9, 8.9, 14),
            Block.box(8.1, 4, 12, 10.1, 9, 14),
            Block.box(7.5, 12, 11.5, 8.5, 13, 19.5),
            Block.box(7.5, 12, 16.5, 8.5, 13, 24.5),
            Block.box(0, 0, 0, 16, 4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();*/
            Stream.of(Block.box(0, 0, 0, 16, 16, 16))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
