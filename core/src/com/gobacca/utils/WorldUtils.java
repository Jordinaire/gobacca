package com.gobacca.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gobacca.actors.Ninja;
import com.gobacca.box2d.*;
import com.gobacca.enums.EnemyType;

public class WorldUtils {

    public static World createWorld()
    {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        shape.dispose();
        return body;
    }
    
    public static Body createNinja(World world)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.NINJA_X, Constants.NINJA_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.NINJA_WIDTH / 2, Constants.NINJA_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.NINJA_GRAVITY_SCALE);
        body.createFixture(shape, Constants.NINJA_DENSITY);
        body.resetMassData();
        body.setUserData(new NinjaUserData(Constants.NINJA_WIDTH, Constants.NINJA_HEIGHT));
        shape.dispose();
        return body;
    }
    
    public static Body createShuriken(World world, Ninja ninja)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.NINJA_X + Constants.SHURIKEN_WIDTH + 0.55f, ninja.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SHURIKEN_WIDTH / 2, Constants.SHURIKEN_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.SHURIKEN_GRAVITY_SCALE);
        body.createFixture(shape, Constants.SHURIKEN_DENSITY);
        body.resetMassData();
        body.setUserData(new ShurikenUserData(Constants.SHURIKEN_WIDTH, Constants.SHURIKEN_HEIGHT));
        shape.dispose();
        return body;
    }
    
    public static Body createEnemy(World world)
    {
        EnemyType enemyType = RandomUtils.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getRegions());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }
}