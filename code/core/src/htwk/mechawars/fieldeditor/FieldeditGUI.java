package htwk.mechawars.fieldeditor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import htwk.mechawars.MainMenu;
import htwk.mechawars.board.Board;
import htwk.mechawars.fields.BarrierCorner;
import htwk.mechawars.fields.BarrierSide;
import htwk.mechawars.fields.BlackHole;
import htwk.mechawars.fields.Checkpoint;
import htwk.mechawars.fields.ConveyorBelt;
import htwk.mechawars.fields.ExpressConveyorBelt;
import htwk.mechawars.fields.Gear;
import htwk.mechawars.fields.Laser;
import htwk.mechawars.fields.Pusher;
import htwk.mechawars.fields.RepairSite;
import htwk.mechawars.fields.StandardField;
import htwk.mechawars.fields.StartField;

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
        
        TextField xPositionChangeField = new TextField("", skinFiEdit);
        xPositionChangeField.setSize(buttonWidth / 2 + 50, buttonHeight / 2);
        xPositionChangeField.setPosition(funcButtonPosX + 40, funcButtonPosY - 575);
        xPositionChangeField.setMessageText("X Position");
        xPositionChangeField.setAlignment(Align.center);
        
        
        TextField yPositionChangeField = new TextField("", skinFiEdit);
        yPositionChangeField.setSize(buttonWidth / 2 + 50, buttonHeight / 2);
        yPositionChangeField.setPosition(funcButtonPosX + 200, funcButtonPosY - 575);
        yPositionChangeField.setMessageText("Y Position");
        yPositionChangeField.setAlignment(Align.center);
        
        
        ImageButton barrierCornerButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                        + "BarrierCorner0" + String.valueOf(corner) + ".png"))));
        barrierCornerButton.setSize(picButtonSize, picButtonSize);
        barrierCornerButton.setPosition(picButtonPosX, picButtonPosY);
        barrierCornerButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "BarrierCorner", corner, 0);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                        new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                                + "BarrierCorner0" + String.valueOf(corner) + ".png")));
                barrierCornerButton.setStyle(styleFiEdit);
                corner = ror(1, 4, corner);
            }
        });
        barrierCornerButton.addListener(new TextTooltip("Barrier Corner", tTM, skinFiEdit));
        
        ImageButton barrierSideButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                        + "BarrierSide" + String.valueOf(side) + ".png"))));
        barrierSideButton.setSize(picButtonSize, picButtonSize);
        barrierSideButton.setPosition(picButtonPosX + 90, picButtonPosY);
        barrierSideButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "BarrierSide", side, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                              + "BarrierSide" + String.valueOf(side) + ".png")));
              barrierSideButton.setStyle(styleFiEdit);
              side = ror(1, 4, side);
          }
        });
        barrierSideButton.addListener(new TextTooltip("Barrier Side", tTM, skinFiEdit));
        
        ImageButton blackHoleButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/BlackHole.png"))));
        blackHoleButton.setSize(picButtonSize, picButtonSize);
        blackHoleButton.setPosition(picButtonPosX + 180, picButtonPosY);
        blackHoleButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "BlackHole", 0, 0);
          }
        });
        blackHoleButton.addListener(new TextTooltip("Black Hole", tTM, skinFiEdit));
        
        ImageButton checkpointButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                        + checkpointNumber + ".png"))));
        checkpointButton.setSize(picButtonSize, picButtonSize);
        checkpointButton.setPosition(picButtonPosX, picButtonPosY - 90);
        checkpointButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "Checkpoint", checkpointNumber, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                              + checkpointNumber + ".png")));
              checkpointButton.setStyle(styleFiEdit);
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
        conveyorBeltButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "ConveyorBelt", startConveyor ,endConveyor);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "conveyorBelt/"
                              + "ConveyorBelt" + String.valueOf(startConveyor) 
                              + String.valueOf(endConveyor) + ".png")));
              conveyorBeltButton.setStyle(styleFiEdit);
              startConveyor = ror(0, 9, startConveyor);
              if (!(((startConveyor == 5) && (endConveyor == 2)) ||
                      (startConveyor == 6) && (endConveyor == 1) ||
                      (startConveyor == 8) && (endConveyor == 4))) {
                  endConveyor = ror(1, 4, endConveyor);
              }
          }
        });
        conveyorBeltButton.addListener(new TextTooltip("Conveyorbelt", tTM, skinFiEdit));
        
        ImageButton expressConveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                        + "ExpressConveyorBelt" + String.valueOf(startExpressConveyor) 
                        + String.valueOf(endExpressConveyor) + ".png"))));
        expressConveyorBeltButton.setSize(picButtonSize, picButtonSize);
        expressConveyorBeltButton.setPosition(picButtonPosX + 180, picButtonPosY - 90);
        expressConveyorBeltButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "ExpressConveyorBelt", startExpressConveyor, endExpressConveyor);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                              + "ExpressConveyorBelt" + String.valueOf(startExpressConveyor) 
                              + String.valueOf(endExpressConveyor) + ".png")));
              expressConveyorBeltButton.setStyle(styleFiEdit);
              startExpressConveyor = ror(0, 9, startExpressConveyor);
              if (!(((startExpressConveyor == 5) && (endExpressConveyor == 2)) ||
                      (startExpressConveyor == 6) && (endExpressConveyor == 1) ||
                      (startExpressConveyor == 8) && (endExpressConveyor == 4))) {
                  endExpressConveyor = ror(1, 4, endExpressConveyor);
              }
          }
        });
        expressConveyorBeltButton.addListener(new TextTooltip("Express Conveyorbelt", 
                tTM, skinFiEdit));
        
        ImageButton backupButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/Checkpoint.png"))));
        backupButton.setSize(picButtonSize, picButtonSize);
        backupButton.setPosition(picButtonPosX, picButtonPosY - 180);
        backupButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "BackupCheckpoint", 0 , 0);
          }
        });
        backupButton.addListener(new TextTooltip("Backup Checkpoint", tTM, skinFiEdit));
        
        ImageButton gearButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber + ".png"))));
        gearButton.setSize(picButtonSize, picButtonSize);
        gearButton.setPosition(picButtonPosX + 90, picButtonPosY - 180);
        gearButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "Gear", gearNumber, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber 
                              + ".png")));
              gearButton.setStyle(styleFiEdit);
              gearNumber = ror(1, 2, gearNumber);
          }
        });
        gearButton.addListener(new TextTooltip("Gear", tTM, skinFiEdit));
        
        ImageButton laserButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "laser/"
                        + "Laser0" + String.valueOf(typeLaserNum) + ".png"))));
        laserButton.setSize(picButtonSize, picButtonSize);
        laserButton.setPosition(picButtonPosX + 180, picButtonPosY - 180);
        laserButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "Laser", typeLaserNum, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "laser/"
                              + "Laser0" + String.valueOf(typeLaserNum) + ".png")));
              laserButton.setStyle(styleFiEdit);
              typeLaserNum = ror(0, 9, typeLaserNum);
          }
        });
        laserButton.addListener(new TextTooltip("Laser", tTM, skinFiEdit));
        
        ImageButton repairSiteButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" + repairNum
                        + ".png"))));
        repairSiteButton.setSize(picButtonSize, picButtonSize);
        repairSiteButton.setPosition(picButtonPosX, picButtonPosY - 270);
        repairSiteButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "RepairSite", repairNum, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" 
                              + repairNum + ".png")));
              repairSiteButton.setStyle(styleFiEdit);
              repairNum = ror(1, 2, repairNum);
          }
        });
        repairSiteButton.addListener(new TextTooltip("Repair Site", tTM, skinFiEdit));
        
        ImageButton standardFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/StandardField.png"))));
        standardFieldButton.setSize(picButtonSize, picButtonSize);
        standardFieldButton.setPosition(picButtonPosX + 90, picButtonPosY - 270);
        standardFieldButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "StandardField", 0, 0);
          }
        });
        standardFieldButton.addListener(new TextTooltip("Standard Field", tTM, skinFiEdit));
        
        ImageButton startFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "startfield/"
                        + "StartField0" + String.valueOf(startFieldNumber) + ".png"))));
        startFieldButton.setSize(picButtonSize, picButtonSize);
        startFieldButton.setPosition(picButtonPosX + 180, picButtonPosY - 270);
        startFieldButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "StartField", startFieldNumber, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "startfield/"
                              + "StartField0" + String.valueOf(startFieldNumber) + ".png")));
              startFieldButton.setStyle(styleFiEdit);
              startFieldNumber = ror(1, 8, startFieldNumber);
          }
        });
        startFieldButton.addListener(new TextTooltip("Start Field", tTM, skinFiEdit));

        ImageButton pusherButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "pusher/"
                        + "Pusher0" + String.valueOf(pusherNumber) + ".png"))));
        pusherButton.setSize(picButtonSize, picButtonSize);
        pusherButton.setPosition(picButtonPosX + 90, picButtonPosY - 360);
        pusherButton.addListener(new ClickListener(Buttons.RIGHT) {
          public void clicked(InputEvent event, float x, float y) {
              drawOnField(xPositionChangeField.getText(), yPositionChangeField.getText(), "Pusher", pusherNumber, 0);
              ImageButtonStyle styleFiEdit = new ImageButtonStyle();
              styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "pusher/"
                              + "Pusher0" + String.valueOf(pusherNumber) + ".png")));
              pusherButton.setStyle(styleFiEdit);
              pusherNumber = ror(1, 4, pusherNumber);
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
        stageFiEdit.addActor(xPositionChangeField);
        stageFiEdit.addActor(yPositionChangeField);
       
        
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
//        if(mapChange) {
            Board.toAsset(batchFiEdit, boardFiEdit);
//            mapChange = false;
//        }
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
    
    private void drawOnField(String x, String y, String part, int startNum, int endNum) {
        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);
        switch (part) {
        case "BarrierCorner": boardFiEdit.fieldmatrix[xInt][yInt] = new BarrierCorner(0, 0, startNum);
            break;
        case "BarrierSide": boardFiEdit.fieldmatrix[xInt][yInt] = new BarrierSide(0, 0, startNum);
            break;
        case "BlackHole": boardFiEdit.fieldmatrix[xInt][yInt] = new BlackHole(0, 0);
            break;
        case "Checkpoint": boardFiEdit.fieldmatrix[xInt][yInt] = new Checkpoint(0, 0, startNum);
            break;
        case "ConveyorBelt": boardFiEdit.fieldmatrix[xInt][yInt] = new ConveyorBelt(0, 0, startNum, endNum);
            break;
        case "ExpressConveyorBelt": boardFiEdit.fieldmatrix[xInt][yInt] = new ExpressConveyorBelt(0, 0, startNum, endNum);
            break;
        case "BackupCheckpoint": boardFiEdit.fieldmatrix[xInt][yInt] = new Checkpoint(0, 0, startNum);
            break;
        case "Gear": boardFiEdit.fieldmatrix[xInt][yInt] = new Gear(0, 0, startNum);
            break;
        case "Laser": boardFiEdit.fieldmatrix[xInt][yInt] = new Laser(0, 0, startNum);
            break;
        case "RepairSite": boardFiEdit.fieldmatrix[xInt][yInt] = new RepairSite(0, 0, startNum);
            break;
        case "StandardField": boardFiEdit.fieldmatrix[xInt][yInt] = new StandardField(0, 0);
            break;
        case "StartField": boardFiEdit.fieldmatrix[xInt][yInt] = new StartField(0, 0, startNum);
            break;
        case "Pusher": boardFiEdit.fieldmatrix[xInt][yInt] = new Pusher(0, 0, startNum);
            break;
        default: break;

        }
    }
}
