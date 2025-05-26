package de.larsensmods.stl_backport.particles.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FireflyParticle extends TextureSheetParticle {

    FireflyParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.speedUpWhenYMotionIsBlocked = true;
        this.friction = 0.96F;
        this.quadSize *= 0.75F;
        this.yd *= 0.8F;
        this.xd *= 0.8F;
        this.zd *= 0.8F;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.getBlockState(BlockPos.containing(this.x, this.y, this.z)).isAir()) {
            this.remove();
        } else {
            this.setAlpha(this.getFadeAmount(this.getLifetimeProgress((float)this.age), 0.3F, 0.5F));
            if (Math.random() > 0.95 || this.age == 1) {
                this.setParticleSpeed(-0.05F + 0.1F * Math.random(), -0.05F + 0.1F * Math.random(), -0.05F + 0.1F * Math.random());
            }
        }
    }

    @Override
    protected int getLightColor(float partialTick) {
        return (int)(255.0F * getFadeAmount(this.getLifetimeProgress((float)this.age + partialTick), 0.1F, 0.3F));
    }

    private float getLifetimeProgress(float age) {
        return Mth.clamp(age / (float)this.lifetime, 0.0F, 1.0F);
    }

    private float getFadeAmount(float lifetimeProgress, float mod1, float mod2) {
        if (lifetimeProgress >= 1.0F - mod1) {
            return (1.0F - lifetimeProgress) / mod1;
        } else {
            return lifetimeProgress <= mod2 ? lifetimeProgress / mod2 : 1.0F;
        }
    }

    public static class FireflyProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FireflyProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FireflyParticle particle = new FireflyParticle(level, x, y, z, 0.5 - level.random.nextDouble(), level.random.nextBoolean() ? ySpeed : -ySpeed, 0.5 - level.random.nextDouble());

            particle.setLifetime(level.random.nextIntBetweenInclusive(200, 300));
            particle.scale(1.5F);
            particle.pickSprite(this.sprite);
            particle.setAlpha(0.0F);
            return particle;
        }
    }
}
