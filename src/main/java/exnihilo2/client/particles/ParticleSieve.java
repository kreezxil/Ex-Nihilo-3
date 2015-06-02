package exnihilo2.client.particles;

import net.minecraft.client.particle.EntityFX;
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

    @Override
    public void renderParticle(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
    {
        float f10 = 0.1F * this.particleScale;

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)p_180434_3_ - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)p_180434_3_ - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)p_180434_3_ - interpPosZ);
        
        p_180434_1_.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        p_180434_1_.addVertexWithUV((double)(f11 - p_180434_4_ * f10 - p_180434_7_ * f10), (double)(f12 - p_180434_5_ * f10), (double)(f13 - p_180434_6_ * f10 - p_180434_8_ * f10), (double)maxU, (double)maxV);
        p_180434_1_.addVertexWithUV((double)(f11 - p_180434_4_ * f10 + p_180434_7_ * f10), (double)(f12 + p_180434_5_ * f10), (double)(f13 - p_180434_6_ * f10 + p_180434_8_ * f10), (double)maxU, (double)minV);
        p_180434_1_.addVertexWithUV((double)(f11 + p_180434_4_ * f10 + p_180434_7_ * f10), (double)(f12 + p_180434_5_ * f10), (double)(f13 + p_180434_6_ * f10 + p_180434_8_ * f10), (double)minU, (double)minV);
        p_180434_1_.addVertexWithUV((double)(f11 + p_180434_4_ * f10 - p_180434_7_ * f10), (double)(f12 - p_180434_5_ * f10), (double)(f13 + p_180434_6_ * f10 - p_180434_8_ * f10), (double)minU, (double)maxV);
    }
}
