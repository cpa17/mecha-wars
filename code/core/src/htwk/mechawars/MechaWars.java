package htwk.mechawars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.math.Rectangle;


/**
* Main game class.
*/
public class MechaWars extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture industrialTile;
    private Rectangle recIndustrialTile;
    private int cameraWidth = 1280;
    private int cameraHeight = 720;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        batch = new SpriteBatch();
        industrialTile = new Texture("industrialTile.png");
//        recIndustrialTile = new Rectangle();
//        recIndustrialTile.width = 32;
//        recIndustrialTile.height = 32;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
//        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        int x = 0;
        int i = 0;
        while (i < 12) {
            int y = 0;
            int j = 0;
            while (j < 12) {
                batch.draw(industrialTile, x, y);
                y = y + (cameraHeight / 12);
                j++;
            }
            x = x + (cameraHeight / 12);
            i++;
        }
        batch.end();
    }

    @Override
    public void dispose() {
        industrialTile.dispose();
        batch.dispose();
    }
}
