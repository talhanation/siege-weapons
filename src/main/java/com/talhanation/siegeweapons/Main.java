package com.talhanation.siegeweapons;
import com.talhanation.siegeweapons.client.events.KeyEvents;
import com.talhanation.siegeweapons.config.SiegeWeaponsServerConfig;
import com.talhanation.siegeweapons.init.ModBlocks;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.init.ModMenus;
import com.talhanation.siegeweapons.init.*;
import com.talhanation.siegeweapons.network.MessageLoadAndShootWeapon;
import com.talhanation.siegeweapons.network.MessageUpdateVehicleControl;
import de.maxhenkel.corelib.CommonRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "siegeweapons";
    public static SimpleChannel SIMPLE_CHANNEL;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Main() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SiegeWeaponsServerConfig.SERVER);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RecruitsClientConfig.CLIENT);
        //RecruitsClientConfig.loadConfig(RecruitsClientConfig.CLIENT, FMLPaths.CONFIGDIR.get().resolve("recruits-client.toml"));

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    FMLJavaModLoadingContext.get().getModEventBus().addListener(Main.this::clientSetup);
                    FMLJavaModLoadingContext.get().getModEventBus().addListener(ModShortcuts::registerBindings);
                }
        );

        modEventBus.addListener(this::setup);
        ModBlocks.BLOCKS.register(modEventBus);
        ModPois.POIS.register(modEventBus);
        //ModProfessions.PROFESSIONS.register(modEventBus);
        ModMenus.MENU_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addCreativeTabs);

        //ModSounds.SOUNDS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void setup(final FMLCommonSetupEvent event) {
        //MinecraftForge.EVENT_BUS.register(new RecruitEvents());

        //MinecraftForge.EVENT_BUS.register(new UpdateChecker());
        MinecraftForge.EVENT_BUS.register(this);

        SIMPLE_CHANNEL = CommonRegistry.registerChannel(Main.MOD_ID, "default");

        Class[] messages = {
                MessageUpdateVehicleControl.class,
                MessageLoadAndShootWeapon.class
        };

        for (int i = 0; i < messages.length; i++){
            CommonRegistry.registerMessage(SIMPLE_CHANNEL, i, messages[i]);
        }

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ModMenus::registerMenus);
        MinecraftForge.EVENT_BUS.register(new KeyEvents());
    }

    private void addCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            //event.accept(ModItems.BOWMAN_SPAWN_EGG.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)){
            //event.accept(ModBlocks.RECRUIT_BLOCK.get());
        }
    }
}
