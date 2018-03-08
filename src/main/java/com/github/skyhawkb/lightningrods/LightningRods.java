package com.github.skyhawkb.lightningrods;

import com.github.skyhawkb.lightningrods.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = LightningRods.MODID, name = LightningRods.NAME, version = LightningRods.VERSION)
public class LightningRods {
    public static final String MODID = "lightningrods";
    public static final String NAME = "LightningRods";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static LightningRods instance;

    @SidedProxy(clientSide = "com.github.skyhawkb.lightningrods.ClientProxy", serverSide = "com.github.skyhawkb.lightningrods.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
       @SubscribeEvent
       public static void registerItems(RegistryEvent.Register<Item> e) {
           ModBlocks.registerItems(e.getRegistry());
       }
       @SubscribeEvent
       public static void registerBlocks(RegistryEvent.Register<Block> e) {
           ModBlocks.registerBlocks(e.getRegistry());
       }
       @SubscribeEvent
       public static void registerModels(ModelRegistryEvent e) {
           ModBlocks.registerModels();
       }
       @SubscribeEvent
       public static void registerRecipes(RegistryEvent.Register<IRecipe> e) {
           e.getRegistry().register(
                   new ShapedOreRecipe(null, new ItemStack(ModBlocks.LIGHTNING_ROD),
                           " B ", " B ", "iIi",
                           'B', Blocks.IRON_BARS,
                           'i', OreDictionary.doesOreNameExist("itemSilicon") ? "itemSilicon" : "dustRedstone",
                           'I', OreDictionary.doesOreNameExist("blockSteel") ? "blockSteel" : "blockIron"
                   ).setRegistryName(new ResourceLocation(MODID, "lightning_rod"))
           );
       }
    }

    public static final CreativeTabs MOD_TAB = new CreativeTabs("lightningrods") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.LIGHTNING_ROD);
        }
    };
}
