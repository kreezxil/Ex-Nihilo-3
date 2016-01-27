package exnihilo2.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ParticleSieve extends EntityFX{
	protected float minU = 0.0f;
	protected float minV = 0.0f;
	protected float maxU = 0.0f;
	protected float maxV = 0.0f;
	
	public ParticleSieve(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, TextureAtlasSprite texture)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.particleIcon = texture;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.setSize(0.04F, 0.04F);
        this.particleScale = 0.07f + (this.rand.nextFloat() * 0.1F);
        this.motionX *= 0.019999999552965164D;
        this.motionY *= -0.4;
        this.motionZ *= 0.019999999552965164D;
        this.particleMaxAge = (int)(20.0D / (Math.random() * 0.2D + 0.2D));
        
        
        //Select a single pixel in the texture to draw.
        float width = (this.particleIcon.getMaxU() - this.particleIcon.getMinU()) / particleIcon.getIconWidth();
        float height = (this.particleIcon.getMaxV() - this.particleIcon.getMinV()) / particleIcon.getIconHeight();
        
        this.minU = this.particleIcon.getMinU() + (rand.nextInt(particleIcon.getIconWidth()) * width);
        this.minV = this.particleIcon.getMinV() + (rand.nextInt(particleIcon.getIconHeight()) * height);
        this.maxU = this.minU + width;
        this.maxV = this.minV + height;
    }

	@Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.99D;
        this.motionY *= 0.99D;
        this.motionZ *= 0.99D;
        
        this.nextTextureIndexX();

        if (this.particleMaxAge-- <= 0)
        {
            this.setDead();
        }
    }
    
    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
