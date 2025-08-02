package com.toni.wings.mixin.client;

import com.toni.wings.server.asm.WingsHooks;
import com.toni.wings.server.asm.WingsHooksClient;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Inject(
			method = "tickHeadTurn",
			at = @At(value = "HEAD")
	)
	public void tickHeadTurn(float pYRot, float pAnimStep, CallbackInfoReturnable<Float> cir) {
		LivingEntity entity = LivingEntity.class.cast(this);
		if(WingsHooks.onUpdateBodyRotation(entity, pYRot)){
			cir.cancel();
		}
	}
}
