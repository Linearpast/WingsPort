package com.toni.wings.client;

import com.toni.wings.WingsMod;
import com.toni.wings.client.apparatus.WingForm;
import com.toni.wings.client.renderer.LayerWings;
import com.toni.wings.server.effect.WingsEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@OnlyIn(Dist.CLIENT)
public class ReloadListener implements ResourceManagerReloadListener {

    /*@SubscribeEvent
    public static void onModelBakeEvent(RenderLivingEvent.Pre event) {
        if(event.getEntity().hasEffect(WingsEffects.WINGS))
    }*/

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager rm) {

        if(WingForm.isEmpty()){
            WingForm.register(WingsMod.ANGEL_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.ANGEL_WINGS)));
            WingForm.register(WingsMod.PARROT_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.PARROT_WINGS)));
            WingForm.register(WingsMod.BAT_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.BAT_WINGS)));
            WingForm.register(WingsMod.BLUE_BUTTERFLY_WINGS, ClientProxy.createInsectoidWings(WingsMod.WINGS.getKey(WingsMod.BLUE_BUTTERFLY_WINGS)));
            WingForm.register(WingsMod.DRAGON_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.DRAGON_WINGS)));
            WingForm.register(WingsMod.EVIL_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.EVIL_WINGS)));
            WingForm.register(WingsMod.FAIRY_WINGS, ClientProxy.createInsectoidWings(WingsMod.WINGS.getKey(WingsMod.FAIRY_WINGS)));
            WingForm.register(WingsMod.FIRE_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.FIRE_WINGS)));
            WingForm.register(WingsMod.MONARCH_BUTTERFLY_WINGS, ClientProxy.createInsectoidWings(WingsMod.WINGS.getKey(WingsMod.MONARCH_BUTTERFLY_WINGS)));
            WingForm.register(WingsMod.SLIME_WINGS, ClientProxy.createInsectoidWings(WingsMod.WINGS.getKey(WingsMod.SLIME_WINGS)));
            //WingForm.register(WingsMod.METALLIC_WINGS, ClientProxy.createAvianWings(WingsMod.WINGS.getKey(WingsMod.METALLIC_WINGS)));
        }

        Minecraft mc = Minecraft.getInstance();
        EntityRenderDispatcher manager = mc.getEntityRenderDispatcher();
        Stream.concat(manager.getSkinMap().values().stream(), manager.renderers.values().stream())
                .filter(LivingEntityRenderer.class::isInstance)
                .map(r -> (LivingEntityRenderer<?, ?>) r)
                .filter(render -> render.getModel() instanceof HumanoidModel<?>)
                .unordered()
                .distinct()
                .forEach(render -> {
                    ModelPart body = ((HumanoidModel<?>) render.getModel()).body;
                    @SuppressWarnings("unchecked") LivingEntityRenderer<LivingEntity, HumanoidModel<LivingEntity>> livingRender = (LivingEntityRenderer<LivingEntity, HumanoidModel<LivingEntity>>) render;
                    livingRender.addLayer(new LayerWings(livingRender, (player, stack) -> {
                        if (player.isCrouching()) {
                            stack.translate(0.0D, 0.2D, 0.0D);
                        }
                        body.translateAndRotate(stack);
                    }));
                });
    }
}
