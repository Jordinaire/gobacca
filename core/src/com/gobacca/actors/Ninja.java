package com.gobacca.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.audio.Sound;
import com.gobacca.box2d.NinjaUserData;
import com.gobacca.utils.Constants;

public class Ninja extends GameActor
{
    private boolean jumping;
    private boolean hit;
    private Animation runningAnimation;
    private TextureRegion jumpingTexture;
    private TextureRegion hitTexture;
    private float stateTime;
    
    private Sound jumpSound;
    private Sound hitSound;
	
    public Ninja(Body body)
    {
    	 super(body);
         TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
         TextureRegion[] runningFrames = new TextureRegion[Constants.NINJA_RUNNING_REGION_NAMES.length];
         for (int i = 0; i < Constants.NINJA_RUNNING_REGION_NAMES.length; i++)
         {
             String path = Constants.NINJA_RUNNING_REGION_NAMES[i];
             runningFrames[i] = textureAtlas.findRegion(path);
         }
         runningAnimation = new Animation(0.1f, runningFrames);
         stateTime = 0f;
         jumpingTexture = textureAtlas.findRegion(Constants.NINJA_JUMPING_REGION_NAME);
         hitTexture = textureAtlas.findRegion(Constants.NINJA_HIT_REGION_NAME);
         jumpSound = Gdx.audio.newSound(Gdx.files.internal(Constants.RUNNER_JUMPING_SOUND));
         hitSound = Gdx.audio.newSound(Gdx.files.internal(Constants.RUNNER_HIT_SOUND));
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);

        float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        float y = screenRectangle.y;
        float width = screenRectangle.width * 1.2f;

        if(hit)
        {
            // When he's hit we also want to apply rotation if the body has been rotated
            batch.draw(hitTexture, x, y, width * 0.5f, screenRectangle.height * 0.5f, width, screenRectangle.height, 1f,
                    1f, (float) Math.toDegrees(body.getAngle()));
        }
        else if(jumping)
        {
            batch.draw(jumpingTexture, x, y, width, screenRectangle.height);
        }
        else
        {
            // Running
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(runningAnimation.getKeyFrame(stateTime, true), x, y, width, screenRectangle.height);
        }
    }
    
    @Override
    public NinjaUserData getUserData()
    {
        return (NinjaUserData) userData;
    }
    
    public void jump()
    {
        if (!(jumping))
        {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
            jumpSound.play();
        }
    }

    public void landed()
    {
        jumping = false;
    }
    
    public void hit()
    {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
        hitSound.play();
    }

    public boolean isHit()
    {
        return hit;
    }

}