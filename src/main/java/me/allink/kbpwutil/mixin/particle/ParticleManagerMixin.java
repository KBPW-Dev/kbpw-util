package me.allink.kbpwutil.mixin.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
    @Shadow @Final private Map<ParticleTextureSheet, Queue<Particle>> particles;

    @Shadow @Final private Queue<Particle> newParticles;

    @Inject(method= "addParticle(Lnet/minecraft/client/particle/Particle;)V", at=@At("HEAD"), cancellable = true)

    public void addParticle(Particle particle, CallbackInfo ci) {
        Logger.getGlobal().info("Queue: " + String.valueOf(this.newParticles.size()));
        Logger.getGlobal().info("Particles: " + String.valueOf(this.particles.size()));
        if(this.particles.size() >= 100) {
            ci.cancel();
            Logger.getGlobal().warning("Too many particles!!!");
        }
    }

    @Inject(method= "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", at=@At("HEAD"), cancellable = true)

    public void addParticle(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<Particle> cir) {

        if(this.particles.size() >= 100) {
            cir.cancel();
            Logger.getGlobal().warning("Too many particles!");
        }
    }

    @Inject(method= "createParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", at=@At("HEAD"), cancellable = true)

    public  <T extends ParticleEffect> void createParticle(T parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<Particle> cir) {
        Logger.getGlobal().info("Queue: " + String.valueOf(this.newParticles.size()));
        Logger.getGlobal().info("Particles: " + String.valueOf(this.particles.size()));
        if(this.particles.size() >= 100) {
            cir.cancel();
            Logger.getGlobal().warning("Too many particles!");
        }
    }
}
