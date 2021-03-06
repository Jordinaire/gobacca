package com.gobacca.enums;

import com.gobacca.utils.Constants;

public enum StartPlatformType
{
	PLATFORM_START	(  40f, Constants.PLATFORM_HEIGHT, 0, (int)Constants.PLATFORM_Y_MIN, Constants.PLATFORM_DENSITY, "rock_start_platform.png");
	

    private float width;
    private float height;
    private int x;
    private int y;
    private float density;
    private String filePath;

    StartPlatformType(float width, float height, int x, int y, float density, String filePath)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.filePath = filePath;
    }

    // GETTERS
    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getDensity()
    {
        return density;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
}
