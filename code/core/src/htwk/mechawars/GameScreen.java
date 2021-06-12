package htwk.mechawars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    final MechaWars game;

    Texture industrialTile;
    Texture backButton;
    Texture continueButton;
    OrthographicCamera camera;
    private static final int cameraWidth = 1280;
    private static final int cameraHeight = 720;

    public GameScreen(final MechaWars game) {
        this.game = game;
        industrialTile = new Texture("industrialTile.png");
        backButton = new Texture("backButton.png");
        continueButton = new Texture("continueButton.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
 //       game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawPlayingField();
        drawButtons();
        game.batch.end();
    }

    public void drawPlayingField(){
        int x = 0;
        int i = 0;
        while (i < 12) {
            int y = 0;
            int j = 0;
            while (j < 12) {
                game.batch.draw(industrialTile, x, y);
                y = y + (cameraHeight / 12);
                j++;
            }
            x = x + (cameraHeight / 12);
            i++;
        }
    }

    public void drawButtons(){
        int continueButtonX = cameraHeight + (cameraWidth - cameraHeight)/3 -64;
        int backButtonX = cameraHeight + (((cameraWidth-cameraHeight)*2)/3)-64;
        int continueButtonY = cameraHeight - 100;
        int backButtonY = cameraHeight - 100;
        /*
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (((Gdx.input.getX() <= (continueButtonX + 128)) && (Gdx.input.getX() >= continueButtonX)) && ((Gdx.input.getY() <= (continueButtonY + 43)) && (Gdx.input.getY() >= continueButtonY))) {
                System.out.println("Continue gedrückt!");
            }
            if (((Gdx.input.getX() <= (backButtonX + 128)) && (Gdx.input.getX() >= backButtonX)) && ((Gdx.input.getY() <= (backButtonY + 43)) && (Gdx.input.getY() >= backButtonY))) {
                System.out.println("Back gedrückt!");
            }
        }
        */
        game.batch.draw(continueButton, continueButtonX, continueButtonY);
        game.batch.draw(backButton, backButtonX, backButtonY);
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

    @Override
    public void dispose() {
        industrialTile.dispose();
    }

    @Override
    public void hide() {
    }

}
