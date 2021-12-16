package htwk.mechawars.fieldeditor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import htwk.mechawars.MainMenu;
import htwk.mechawars.MechaWars;
import htwk.mechawars.board.Board;
import htwk.mechawars.game.GameScreen;

public class FieldeditGUI implements Screen{
    private Game gameFiEdit;
    private Stage stageFiEdit;
    private Texture img;
    private SpriteBatch batchFiEdit;
    private static Board boardFiEdit;
    private int buttonWidth = 220;
    private int buttonHeight = 75;
    private int funcButtonPosX = Gdx.graphics.getHeight()
            + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 264;
    private int funcButtonPosY = Gdx.graphics.getHeight() - 70;
    private int picButtonSize = 80;
    private int picButtonPosX = Gdx.graphics.getHeight()
            + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 194;;
    private int picButtonPosY = Gdx.graphics.getHeight() - 200;
    private int corner = 1; //corner of ImageButton
    private int side = 1; //side of BarrierButton
//    private int type = 0; //type of BlockadeButton
    private int checkpointNumber = 1; //number of CheckpointButton
    private int startConveyor = 4; //start of ConveyorButton
    private int endConveyor = 2; //end of ConveyorButton
    private int startExpressConveyor = 4; //start of ExpressConveyorButton
    private int endExpressConveyor = 2; //end of ExpressConveyorButton
    private int gearNumber = 1; //number of GearButton
    private int typeLaserNum = 0; //number of type of LaserButton
    private int repairNum = 2; //number of RepairButton
    private int startFieldNumber = 1; //number of StartFieldButton
    private int pusherNumber = 1; //Number of Pusher
    private boolean backToMain = false;
    private boolean mapChange = true;
    private TooltipManager tTM = new TooltipManager() ;
    
    public FieldeditGUI(FieldEditor fieldEditor, String map) {
        
        tTM.instant();
        tTM.animations = false;
        tTM.initialTime = 0;
        initBoardFiEdit(map);
        stageFiEdit = new Stage();
        
        img = new Texture(Gdx.files.internal("background.png"));
        Skin skinFiEdit = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
        Gdx.input.setInputProcessor(stageFiEdit);
        
        TextButton importButton = new TextButton("Import", skinFiEdit);
        
        importButton.setPosition(funcButtonPosX, funcButtonPosY);
        importButton.setSize(buttonWidth, buttonHeight);
        importButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                
            }
//                            importieren(skinFEdit);
        });
        
        TextButton exportButton = new TextButton("Export", skinFiEdit);
        
        exportButton.setPosition(funcButtonPosX, funcButtonPosY - 42);
        exportButton.setSize(buttonWidth, buttonHeight);
        exportButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                    
//                            exportieren(skinFEdit);
            }
        });
        
        TextButton backButton = new TextButton("Zurueck", skinFiEdit);
        
        backButton.setPosition(funcButtonPosX + 180 , funcButtonPosY);
        backButton.setSize(buttonWidth, buttonHeight);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                zurueck();
                
            }
        });
        
        TextButton clearButton = new TextButton("Loeschen", skinFiEdit);
        
        clearButton.setPosition(funcButtonPosX + 180, funcButtonPosY - 42);
        clearButton.setSize(buttonWidth, buttonHeight);
        clearButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                resetField();
                
            }
        });
        
        TextField textFieldInfo = new TextField(":)", skinFiEdit);
        textFieldInfo.setAlignment(Align.center);
        textFieldInfo.setSize(buttonWidth + 100, buttonHeight);
        textFieldInfo.setPosition(funcButtonPosX + 40, funcButtonPosY - 610);
        
        ImageButton barrierCornerButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                        + "BarrierCorner0" + String.valueOf(corner) + ".png"))));
        barrierCornerButton.setSize(picButtonSize, picButtonSize);
        barrierCornerButton.setPosition(picButtonPosX, picButtonPosY);
        barrierCornerButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
//                drawOnField(x, y, corner);
                corner = ror(1, 4, corner);
            }
        });
        barrierCornerButton.addListener(new TextTooltip("Barrier Corner", tTM, skinFiEdit));
        
        ImageButton barrierSideButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                        + "BarrierSide" + String.valueOf(side) + ".png"))));
        barrierSideButton.setSize(picButtonSize, picButtonSize);
        barrierSideButton.setPosition(picButtonPosX + 90, picButtonPosY);
        barrierSideButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, side);
              side = ror(1, 4, side);
          }
        });
        barrierSideButton.addListener(new TextTooltip("Barrier Side", tTM, skinFiEdit));
        
        ImageButton blackHoleButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/BlackHole.png"))));
        blackHoleButton.setSize(picButtonSize, picButtonSize);
        blackHoleButton.setPosition(picButtonPosX + 180, picButtonPosY);
        blackHoleButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
          }
        });
        blackHoleButton.addListener(new TextTooltip("Black Hole", tTM, skinFiEdit));
        
        ImageButton checkpointButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                        + checkpointNumber + ".png"))));
        checkpointButton.setSize(picButtonSize, picButtonSize);
        checkpointButton.setPosition(picButtonPosX, picButtonPosY - 90);
        checkpointButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, checkpointNumber);
              checkpointNumber = ror(1, 8, checkpointNumber);
          }
        });
        checkpointButton.addListener(new TextTooltip("Checkpoint", tTM, skinFiEdit));
        
        ImageButton conveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "conveyorBelt/"
                        + "ConveyorBelt" + String.valueOf(startConveyor) 
                        + String.valueOf(endConveyor) + ".png"))));
        conveyorBeltButton.setSize(picButtonSize, picButtonSize);
        conveyorBeltButton.setPosition(picButtonPosX + 90, picButtonPosY - 90);
        conveyorBeltButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, endConveyor);
              startConveyor = ror(0, 9, startConveyor);
              endConveyor = ror(1, 4, endConveyor);
          }
        });
        conveyorBeltButton.addListener(new TextTooltip("Conveyorbelt", tTM, skinFiEdit));
        
        ImageButton expressConveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                        + "ExpressConveyorBelt" + String.valueOf(startExpressConveyor) 
                        + String.valueOf(endExpressConveyor) + ".png"))));
        expressConveyorBeltButton.setSize(picButtonSize, picButtonSize);
        expressConveyorBeltButton.setPosition(picButtonPosX + 180, picButtonPosY - 90);
        expressConveyorBeltButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, endExpressConveyor);
              startExpressConveyor = ror(0, 9, startExpressConveyor);
              endExpressConveyor = ror(1, 4, endExpressConveyor);
          }
        });
        expressConveyorBeltButton.addListener(new TextTooltip("Express Conveyorbelt", 
                tTM, skinFiEdit));
        
        ImageButton backupButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/Checkpoint.png"))));
        backupButton.setSize(picButtonSize, picButtonSize);
        backupButton.setPosition(picButtonPosX, picButtonPosY - 180);
        backupButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
          }
        });
        backupButton.addListener(new TextTooltip("Backup Checkpoint", tTM, skinFiEdit));
        
        ImageButton gearButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber + ".png"))));
        gearButton.setSize(picButtonSize, picButtonSize);
        gearButton.setPosition(picButtonPosX + 90, picButtonPosY - 180);
        gearButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
              gearNumber = ror(1, 2, gearNumber);
//              drawOnField(x, y, gearNumber);
          }
        });
        gearButton.addListener(new TextTooltip("Gear", tTM, skinFiEdit));
        
        ImageButton laserButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "laser/"
                        + "Laser0" + String.valueOf(typeLaserNum) + ".png"))));
        laserButton.setSize(picButtonSize, picButtonSize);
        laserButton.setPosition(picButtonPosX + 180, picButtonPosY - 180);
        laserButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
              typeLaserNum = ror(0, 9, typeLaserNum);
//              drawOnField(x, y, typeLaser);
          }
        });
        laserButton.addListener(new TextTooltip("Laser", tTM, skinFiEdit));
        
        ImageButton repairSiteButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" + repairNum
                        + ".png"))));
        repairSiteButton.setSize(picButtonSize, picButtonSize);
        repairSiteButton.setPosition(picButtonPosX, picButtonPosY - 270);
        repairSiteButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
              repairNum = ror(1, 2, repairNum);
//              drawOnField(x, y, repairNum);
          }
        });
        repairSiteButton.addListener(new TextTooltip("Repair Site", tTM, skinFiEdit));
        
        ImageButton standardFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/StandardField.png"))));
        standardFieldButton.setSize(picButtonSize, picButtonSize);
        standardFieldButton.setPosition(picButtonPosX + 90, picButtonPosY - 270);
        standardFieldButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
          }
        });
        standardFieldButton.addListener(new TextTooltip("Standard Feld", tTM, skinFiEdit));
        
        ImageButton startFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "startfield/"
                        + "StartField0" + String.valueOf(startFieldNumber) + ".png"))));
        startFieldButton.setSize(picButtonSize, picButtonSize);
        startFieldButton.setPosition(picButtonPosX + 180, picButtonPosY - 270);
        startFieldButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
              startFieldNumber = ror(1, 8, startFieldNumber);
//              drawOnField(x, y, startFieldNumber);
          }
        });
        startFieldButton.addListener(new TextTooltip("Start Feld", tTM, skinFiEdit));

        ImageButton pusherButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "pusher/"
                        + "Pusher0" + String.valueOf(pusherNumber) + ".png"))));
        pusherButton.setSize(picButtonSize, picButtonSize);
        pusherButton.setPosition(picButtonPosX + 90, picButtonPosY - 360);
        startFieldButton.addListener(new ClickListener() {
          public void clicked(InputEvent event, float x, float y) {
              pusherNumber = ror(1, 4, pusherNumber);
//              drawOnField(x, y, startFieldNumber);
          }
        });
        pusherButton.addListener(new TextTooltip("Pusher", tTM, skinFiEdit));
        
        stageFiEdit.addActor(importButton);
        stageFiEdit.addActor(exportButton);
        stageFiEdit.addActor(backButton);
        stageFiEdit.addActor(clearButton);
        stageFiEdit.addActor(barrierCornerButton);
        stageFiEdit.addActor(barrierSideButton);
        stageFiEdit.addActor(blackHoleButton);
        stageFiEdit.addActor(checkpointButton);
        stageFiEdit.addActor(conveyorBeltButton);
        stageFiEdit.addActor(expressConveyorBeltButton);
        stageFiEdit.addActor(backupButton);
        stageFiEdit.addActor(gearButton);
        stageFiEdit.addActor(laserButton);
        stageFiEdit.addActor(repairSiteButton);
        stageFiEdit.addActor(standardFieldButton);
        stageFiEdit.addActor(startFieldButton);
        stageFiEdit.addActor(pusherButton);
        stageFiEdit.addActor(textFieldInfo);
        
    }
    
    /**
     * changeScreen function to switch to GameScreen.
     */
    public void changeScreen() {
        gameFiEdit.setScreen(new MainMenu(gameFiEdit));
        stageFiEdit.dispose();
    }
    
    private static void initBoardFiEdit(String fileName) {
        boardFiEdit = new Board(fileName);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batchFiEdit = (SpriteBatch) stageFiEdit.getBatch();
        batchFiEdit.begin();
        batchFiEdit.draw(img, 0, 0, 1280, 720);
        if(mapChange) {
            Board.toAsset(batchFiEdit, boardFiEdit);
            mapChange = false;
        }
        batchFiEdit.end();

        stageFiEdit.act(delta);
        stageFiEdit.draw();
        
        if (backToMain) {
            //draw main Menu
        }
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
    
    private int ror(int min, int max, int var) {
        if (var < max) {
            var += 1;
        } else {
            var = min;
        }
        //zeichnen !!!!!!!!!
        return var;
    }
}
