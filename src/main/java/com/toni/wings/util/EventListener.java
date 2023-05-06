package com.toni.wings.util;

import com.toni.wings.WingsMod;
import com.toni.wings.client.ReloadListener;
import com.toni.wings.client.renderer.LayerWings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = WingsMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventListener {

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event){
        event.registerReloadListener(new ReloadListener());
    }

    @SubscribeEvent
    public static void renderLiving(EntityRenderersEvent.RegisterRenderers event) {

    }

}
