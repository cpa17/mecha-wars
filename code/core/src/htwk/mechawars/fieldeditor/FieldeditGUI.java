package htwk.mechawars.fieldeditor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import htwk.mechawars.MainMenu;
import htwk.mechawars.MechaWars;
import htwk.mechawars.game.GameScreen;

public class FieldeditGUI implements Screen{
    private Game gameFEdit;
    private Stage stageFEdit;
    private Texture img;
    private SpriteBatch batchFEdit;
    private int buttonWidth = 400;
    private int buttonHeight = 100;
    
    public FieldeditGUI(String name) {
        img = new Texture(Gdx.files.internal("background.png"));
        stageFEdit = new Stage();
        
        Gdx.input.setInputProcessor(stageFEdit);
        
        Skin skinFEdit = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
        
        TextButton importButton = new TextButton("Import", skinFEdit);
        //wo hin?
        importButton.setPosition(440, 100);
        importButton.setSize(buttonWidth, buttonHeight);
        
        importButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                //No dialog but file system instead!
                Dialog dialogCloseOption = new Dialog("Import", skinFEdit) {

                    @Override
                    protected void result(Object object) {
                        boolean yesNoOpt = (Boolean) object;
                        if (yesNoOpt) {
//                            importieren();
                        } else {
                        }
                    }

                }.show(stageFEdit);

                dialogCloseOption.setSize(450, 110);
                dialogCloseOption.button("Importieren", true);
                dialogCloseOption.button("Zurueck", false);
                //extra Keys?
                dialogCloseOption.key(Input.Keys.ENTER, true);
                dialogCloseOption.key(Input.Keys.ESCAPE, false);                
            }
        });
        
        TextButton exportButton = new TextButton("Export", skinFEdit);
        //wo hin?
        exportButton.setPosition(440, 100);
        exportButton.setSize(buttonWidth, buttonHeight);
        
        exportButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                Dialog dialogCloseOption = new Dialog("Export als txt", skinFEdit) {

                    @Override
                    protected void result(Object object) {
                        boolean yesNoOpt = (Boolean) object;
                        if (yesNoOpt) {
//                            exportieren();
                        } else {
                        }
                    }

                }.show(stageFEdit);

                dialogCloseOption.setSize(450, 110);
                dialogCloseOption.button("Exportieren", true);
                dialogCloseOption.button("Zurueck", false);
                //extra Keys?
                dialogCloseOption.key(Input.Keys.ENTER, true);
                dialogCloseOption.key(Input.Keys.ESCAPE, false);                
            }
        });
        
        TextButton backButton = new TextButton("Zurueck", skinFEdit);
        //wo hin?
        backButton.setPosition(440, 100);
        backButton.setSize(buttonWidth, buttonHeight);
        
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                zurueck();
                
            }
        });
        
        TextButton clearButton = new TextButton("Loeschen", skinFEdit);
        //wo hin?
        clearButton.setPosition(440, 100);
        clearButton.setSize(buttonWidth, buttonHeight);
        
        clearButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                resetField();
                
            }
        });
    }
    
    /**
     * changeScreen function to switch to GameScreen.
     */
    public void changeScreen() {
        gameFEdit.setScreen(new MainMenu(gameFEdit));
        stageFEdit.dispose();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batchFEdit = (SpriteBatch) stageFEdit.getBatch(); 
        batchFEdit.begin();
        batchFEdit.draw(img, 0, 0, 1280, 720);
        batchFEdit.end();

        stageFEdit.act(delta);
        stageFEdit.draw();
        
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
}
