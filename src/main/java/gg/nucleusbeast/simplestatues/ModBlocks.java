package gg.nucleusbeast.simplestatues;

import gg.nucleusbeast.simplestatues.blocks.CatStatue;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static gg.nucleusbeast.simplestatues.SimpleStatues.MOD_ID;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block", () ->
            new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5f)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> CAT_STATUE = registerBlock("cat_statue", () ->
            new CatStatue(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5f).noOcclusion()), CreativeModeTab.TAB_MISC);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> _block = BLOCKS.register(name, block);
        registerBlockItem(name, _block, tab);
        return _block;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () ->
                new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
