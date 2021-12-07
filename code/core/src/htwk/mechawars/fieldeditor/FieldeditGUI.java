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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private int buttonWidth = 400;
    private int buttonHeight = 100;
    private int buttonPosX = Gdx.graphics.getHeight()
            + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 64;
    private int buttonPosY = Gdx.graphics.getHeight() - 100;
    private int corner = 1; //corner of ImageButton
    private int side = 1; //side of BarrierButton
//    private int type = 0; //type of BlockadeButton
    private int checkpointNumber = 1; //number of CheckpointButton
    private int startConveyor = 4; //start of ConveyorButton
    private int endConveyor = 2; //end of ConveyorButton
    private int startExpressConveyor = 4; //start of ExpressConveyorButton
    private int endExpressConveyor = 2; //end of ExpressConveyorButton
    private int gearNumber = 1; //number of GearButton
    private int typeLaser = 0; //type of LaserButton
    private int repairNum = 2; //number of RepairButton
    private int startFieldNumber = 1; //number of StartFieldButton
    private boolean backToMain = false;
    
    public FieldeditGUI(FieldEditor fieldEditor, String map) {
        
        initBoardFiEdit(map);
        stageFiEdit = new Stage();
        
        img = new Texture(Gdx.files.internal("background.png"));
        Skin skinFiEdit = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
        Gdx.input.setInputProcessor(stageFiEdit);
        
        TextButton importButton = new TextButton("Import", skinFiEdit);
        
        importButton.setPosition(buttonPosX, buttonPosY);
        importButton.setSize(buttonWidth, buttonHeight);
        importButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                
            }
//                            importieren(skinFEdit);
        });
        
        TextButton exportButton = new TextButton("Export", skinFiEdit);
        
        exportButton.setPosition(buttonPosX, buttonPosY + 100);
        exportButton.setSize(buttonWidth, buttonHeight);
        exportButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                    
//                            exportieren(skinFEdit);
            }
        });
        
        TextButton backButton = new TextButton("Zurueck", skinFiEdit);
        
        backButton.setPosition(buttonPosX, buttonPosY + 200);
        backButton.setSize(buttonWidth, buttonHeight);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                zurueck();
                
            }
        });
        
        TextButton clearButton = new TextButton("Loeschen", skinFiEdit);
        
        clearButton.setPosition(buttonPosX, buttonPosY + 200);
        clearButton.setSize(buttonWidth, buttonHeight);
        clearButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

//                resetField();
                
            }
        });
        

        ImageButton barrierCornerButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                        + "BarrierCorner0" + String.valueOf(corner) + ".png"))));
//        barrierCornerButton.setSize(width, height);
//        barrierCornerButton.setPosition(x, y);
        barrierCornerButton.addListener(new ClickListener() {
            public void enter() {
                
            }
            public void exit() {
                
            }
            public void clicked(InputEvent event, float x, float y) {
//                drawOnField(x, y, corner);
            }
        });
        ImageButton barrierSideButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                        + "BarrierSide" + String.valueOf(side) + ".png"))));
//      barrierSideButton.setSize(width, height);
//      barrierSideButton.setPosition(x, y);
//      barrierSideButton.addListener(new ClickListener() {
//          public void enter() {
//                
//          }
//          public void exit() {
//                
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, side);
//          }
//      });
        ImageButton blackHoleButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/BlackHole.png"))));
//      blackHoleButton.setSize(width, height);
//      blackHoleButton.setPosition(x, y);
//      blackHoleButton.addListener(new ClickListener() {
//          public void enter() {
//      
//          }
//          public void exit() {
//      
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
//          }
//      });
        
        
//        ImageButton blockadeButton = new ImageButton(new TextureRegionDrawable(
//                new TextureRegion(new Texture("mapAssets/" + "blockade/"
//                        + "Blockade0" + String.valueOf(type) + ".png"))));
//      blockadeButton.setSize(width, height);
//      blockadeButton.setPosition(x, y);
//      blockadeButton.addListener(new ClickListener() {
//          public void enter() {
//  
//          }
//          public void exit() {
//  
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, type);
//          }
//      });
        
        
        ImageButton checkpointButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                        + checkpointNumber + ".png"))));
//      checkpointButton.setSize(width, height);
//      checkpointButton.setPosition(x, y);
//      checkpointButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, checkpointNumber);
//          }
//      });
        ImageButton conveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "conveyorBelt/"
                        + "ConveyorBelt" + String.valueOf(startConveyor) 
                        + String.valueOf(endConveyor) + ".png"))));
//      conveyorBeltButton.setSize(width, height);
//      conveyorBeltButton.setPosition(x, y);
//      conveyorBeltButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, endConveyor);
//          }
//      });
        ImageButton expressConveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                        + "ExpressConveyorBelt" + String.valueOf(startExpressConveyor) 
                        + String.valueOf(endExpressConveyor) + ".png"))));
//      expressConveyorBeltButton.setSize(width, height);
//      expressConveyorBeltButton.setPosition(x, y);
//      expressConveyorBeltButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, endExpressConveyor);
//          }
//      });
        ImageButton fieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/StandardField.png"))));
//      fieldButton.setSize(width, height);
//      fieldButton.setPosition(x, y);
//      fieldButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
//          }
//      });
        ImageButton gearButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber + ".png"))));
//      gearButton.setSize(width, height);
//      gearButton.setPosition(x, y);
//      gearButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, gearNumber);
//          }
//      });
        ImageButton laserButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "laser/"
                        + "Laser0" + String.valueOf(typeLaser) + ".png"))));
//      laserButton.setSize(width, height);
//      laserButton.setPosition(x, y);
//      laserButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, typeLaser);
//          }
//      });
        ImageButton repairSiteButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" + repairNum
                        + ".png"))));
//      repairSiteButton.setSize(width, height);
//      repairSiteButton.setPosition(x, y);
//      repairSiteButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, repairNum);
//          }
//      });
        ImageButton standardFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/StandardField.png"))));
//      standardFieldButton.setSize(width, height);
//      standardFieldButton.setPosition(x, y);
//      standardFieldButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y);
//          }
//      });
        ImageButton startFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "startfield/"
                        + "StartField0" + String.valueOf(startFieldNumber) + ".png"))));
//      startFieldButton.setSize(width, height);
//      startFieldButton.setPosition(x, y);
//      startFieldButton.addListener(new ClickListener() {
//          public void enter() {
//
//          }
//          public void exit() {
//
//          }
//          public void clicked(InputEvent event, float x, float y) {
//              drawOnField(x, y, startFieldNumber);
//          }
//      });
        
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
        Board.toAsset(batchFiEdit, boardFiEdit);
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
}
