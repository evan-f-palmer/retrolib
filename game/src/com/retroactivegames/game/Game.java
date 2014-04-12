package com.retroactivegames.game;

import retro.input.keys.VirtualKeyPressedListener;
import retro.input.keys.VirtualKeyRelasedListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	private retro.input.InputProcessor input;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		input = new retro.input.InputProcessor();
		
		
		
		input.keyInput.mapVKey("Run", Keys.A);
		input.keyInput.mapVKey("Test Key", Keys.S);
		input.keyInput.mapVKey("Other Key", Keys.D);
		
		input.keyInput.addKeyRelasedListener("Test Key", new VirtualKeyRelasedListener() {
			@Override
			public void keyRelased() {
				System.out.println("Relased the Test Key");
				
			}
		});
		
		input.keyInput.addKeyPressedListener("Test Key", new VirtualKeyPressedListener() {
			@Override
			public void keyPressed() {
				System.out.println("Pressed the Test Key");
			}
		});
		
		input.keyInput.addKeyPressedListener("Other Key", new VirtualKeyPressedListener() {
			@Override
			public void keyPressed() {
				System.out.println("Other was also called");
			}
		});
		
		input.keyInput.setFinalRelasedListener("Test Key", new VirtualKeyRelasedListener() {
			@Override
			public void keyRelased() {
				System.out.println("Final Relase of the Test Key");
				
			}
		});
		input.keyInput.setFirstPressedListener("Test Key", new VirtualKeyPressedListener() {
			
			@Override
			public void keyPressed() {
				System.out.println("First Press of the Test Key");
			}
		});
		
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}
	
	@Override
	public void render() {				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(input.keyInput.isPressed("Test Key")) {
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			sprite.draw(batch);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
