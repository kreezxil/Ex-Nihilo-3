package exnihilo2.blocks.tileentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBarrel extends ModelBase {
	public ModelRenderer bottom;
	public ModelRenderer side1;
	public ModelRenderer side2;
	public ModelRenderer side3;
	public ModelRenderer side4;

	public ModelBarrel()
	{
		textureWidth = 128;
		textureHeight = 128;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-7F, 0F, -7F, 14, 1, 14);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(128, 128);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);

		side1 = new ModelRenderer(this, 0, 16);
		side1.addBox(-8F, -8F, 0F, 16, 16, 1);
		side1.setRotationPoint(0F, 16F, 7F);
		side1.setTextureSize(128, 128);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);

		side2 = new ModelRenderer(this, 0, 34);
		side2.addBox(-8F, -8F, 0F, 16, 16, 1);
		side2.setRotationPoint(0F, 16F, -8F);
		side2.setTextureSize(128, 128);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);

		side3 = new ModelRenderer(this, 35, 16);
		side3.addBox(0F, -8F, -7F, 1, 16, 14);
		side3.setRotationPoint(-8F, 16F, 0F);
		side3.setTextureSize(128, 128);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);

		side4 = new ModelRenderer(this, 66, 16);
		side4.addBox(0F, -8F, -7F, 1, 16, 14);
		side4.setRotationPoint(7F, 16F, 0F);
		side4.setTextureSize(128, 128);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }

	public void simpleRender(float scale)
	{
		bottom.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		side4.render(scale);
	}
}
