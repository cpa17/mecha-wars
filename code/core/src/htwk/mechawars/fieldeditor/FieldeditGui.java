package htwk.mechawars.fieldeditor;

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

import htwk.mechawars.fieldsfiedit.BarrierCorner;
import htwk.mechawars.fieldsfiedit.BarrierSide;
import htwk.mechawars.fieldsfiedit.BlackHole;
import htwk.mechawars.fieldsfiedit.Checkpoint;
import htwk.mechawars.fieldsfiedit.ConveyorBelt;
import htwk.mechawars.fieldsfiedit.ExpressConveyorBelt;
import htwk.mechawars.fieldsfiedit.Gear;
import htwk.mechawars.fieldsfiedit.Laser;
import htwk.mechawars.fieldsfiedit.Pusher;
import htwk.mechawars.fieldsfiedit.RepairSite;
import htwk.mechawars.fieldsfiedit.StandardField;
import htwk.mechawars.fieldsfiedit.StartField;


/**
 * Class, for the GUI-Elements of the Fieldeditor.
 * 
 * @author -.-
 *
 */ 
public class FieldeditGui implements Screen {

    private Stage stageFiEdit;
    private Texture img;
    private SpriteBatch batchFiEdit;
    private Board boardFiEdit;
    private int buttonWidth = 220;
    private int buttonHeight = 75;
    private int funcButtonPosX = Gdx.graphics.getHeight()
            + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 264;
    private int funcButtonPosY = Gdx.graphics.getHeight() - 70;
    private int picButtonSize = 80;
    private int picButtonPosX = Gdx.graphics.getHeight()
            + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 194;
    private int picButtonPosY = Gdx.graphics.getHeight() - 200;
    private int corner = 1; //corner of ImageButton
    private int side = 1; //side of BarrierButton
    //private int type = 0; type of BlockadeButton
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
    private TooltipManager toolTipManager = new TooltipManager();
    private Skin skinFiEdit = new Skin(Gdx.files.internal("skinMenu/star-soldier-ui.json"));
    private ButtonfunctionsFieldEditor bfFiEdit = new ButtonfunctionsFieldEditor();
    
    /**
    * Constructor of FieldeditGUI.
    */ 
    public FieldeditGui(FieldEditor fieldEditor, String map) {
        
        toolTipManager.instant();
        toolTipManager.animations = false;
        toolTipManager.initialTime = 0;
        initBoardFiEdit(map);
        bfFiEdit.oneStepDone(boardFiEdit);          // first initial board saved
        stageFiEdit = new Stage();
        
        img = new Texture(Gdx.files.internal("background.png"));
        Gdx.input.setInputProcessor(stageFiEdit);
        
        TextButton importButton = new TextButton("Import", skinFiEdit);
        
        importButton.setPosition(funcButtonPosX, funcButtonPosY);
        importButton.setSize(buttonWidth, buttonHeight);
        importButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String abc = bfFiEdit.importField();
                if (!abc.equals("0")) {
                    initBoardFiEdit(abc);
                }
                if (abc.equals("2")) {
                    System.out.println("Dateiformat nicht lesbar!");
                }
            }
        });
        
        TextButton exportButton = new TextButton("Export", skinFiEdit);
        
        exportButton.setPosition(funcButtonPosX, funcButtonPosY - 42);
        exportButton.setSize(buttonWidth, buttonHeight);
        exportButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                
                bfFiEdit.exportField(boardFiEdit);
            }
        });
        
        TextButton backButton = new TextButton("Zurueck", skinFiEdit);
        
        backButton.setPosition(funcButtonPosX + 180, funcButtonPosY);
        backButton.setSize(buttonWidth, buttonHeight);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                boardFiEdit = bfFiEdit.oneStepBack();
            }
        });
        
        TextButton clearButton = new TextButton("Loeschen", skinFiEdit);
        
        clearButton.setPosition(funcButtonPosX + 180, funcButtonPosY - 42);
        clearButton.setSize(buttonWidth, buttonHeight);
        clearButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                bfFiEdit.oneStepDone(boardFiEdit);
                boardFiEdit = bfFiEdit.resetField(boardFiEdit);
            }
        });
        
        TextField xpositionChangeField = new TextField("", skinFiEdit);
        xpositionChangeField.setSize((int) (buttonWidth / 2.0) + 50, (int) (buttonHeight / 2.0));
        xpositionChangeField.setPosition(funcButtonPosX + 40, funcButtonPosY - 575);
        xpositionChangeField.setMessageText("X Position");
        xpositionChangeField.setAlignment(Align.center);
        
        
        TextField ypositionChangeField = new TextField("", skinFiEdit);
        ypositionChangeField.setSize((int) (buttonWidth / 2.0) + 50, (int) (buttonHeight / 2.0));
        ypositionChangeField.setPosition(funcButtonPosX + 200, funcButtonPosY - 575);
        ypositionChangeField.setMessageText("Y Position");
        ypositionChangeField.setAlignment(Align.center);
        
        
        ImageButton barrierCornerButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                        + "BarrierCorner0" + corner + ".png"))));
        barrierCornerButton.setSize(picButtonSize, picButtonSize);
        barrierCornerButton.setPosition(picButtonPosX, picButtonPosY);
        barrierCornerButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                corner = ror(1, 4, corner);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                        new TextureRegion(new Texture("mapAssets/" + "barriercorner/"
                                + "BarrierCorner0" + corner + ".png")));
                barrierCornerButton.setStyle(styleFiEdit);
            }
        });
        barrierCornerButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "BarrierCorner", corner, 0);
            }
        });
        barrierCornerButton.addListener(new TextTooltip("Barrier Corner",
                toolTipManager, skinFiEdit));
        
        
        ImageButton barrierSideButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                        + "BarrierSide" + side + ".png"))));
        barrierSideButton.setSize(picButtonSize, picButtonSize);
        barrierSideButton.setPosition(picButtonPosX + 90, picButtonPosY);
        barrierSideButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                side = ror(1, 4, side);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "barrierside/"
                              + "BarrierSide" + side + ".png")));
                barrierSideButton.setStyle(styleFiEdit);
            }
        });
        barrierSideButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "BarrierSide", side, 0);
            }
        });
        barrierSideButton.addListener(new TextTooltip("Barrier Side", toolTipManager, skinFiEdit));


        ImageButton blackHoleButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/BlackHole.png"))));
        blackHoleButton.setSize(picButtonSize, picButtonSize);
        blackHoleButton.setPosition(picButtonPosX + 180, picButtonPosY);
        blackHoleButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "BlackHole", 0, 0);
            }
        });
        blackHoleButton.addListener(new TextTooltip("Black Hole", toolTipManager, skinFiEdit));
        
        
        ImageButton checkpointButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                        + checkpointNumber + ".png"))));
        checkpointButton.setSize(picButtonSize, picButtonSize);
        checkpointButton.setPosition(picButtonPosX, picButtonPosY - 90);
        checkpointButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                checkpointNumber = ror(1, 8, checkpointNumber);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/checkpoints/Check" 
                              + checkpointNumber + ".png")));
                checkpointButton.setStyle(styleFiEdit);
            }
        });
        checkpointButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "Checkpoint", checkpointNumber, 0);
            }
        });
        checkpointButton.addListener(new TextTooltip("Checkpoint", toolTipManager, skinFiEdit));
        
        
        ImageButton conveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "conveyorBelt/"
                        + "ConveyorBelt" + startConveyor
                        + endConveyor + ".png"))));
        conveyorBeltButton.setSize(picButtonSize, picButtonSize);
        conveyorBeltButton.setPosition(picButtonPosX + 90, picButtonPosY - 90);
        conveyorBeltButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                startConveyor = ror(0, 9, startConveyor);
                if (!(((startConveyor == 5) && (endConveyor == 2)) ||
                        (startConveyor == 6) && (endConveyor == 1) ||
                        (startConveyor == 8) && (endConveyor == 4))) {
                    endConveyor = ror(1, 4, endConveyor);
                }
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "conveyorBelt/"
                              + "ConveyorBelt" + startConveyor
                              + endConveyor + ".png")));
                conveyorBeltButton.setStyle(styleFiEdit);
            }
        });
        conveyorBeltButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "ConveyorBelt", startConveyor, endConveyor);
            }
        });
        conveyorBeltButton.addListener(new TextTooltip("Conveyorbelt",
                toolTipManager, skinFiEdit));
        
        
        ImageButton expressConveyorBeltButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                        + "ExpressConveyorBelt" + startExpressConveyor
                        + endExpressConveyor + ".png"))));
        expressConveyorBeltButton.setSize(picButtonSize, picButtonSize);
        expressConveyorBeltButton.setPosition(picButtonPosX + 180, picButtonPosY - 90);
        expressConveyorBeltButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                startExpressConveyor = ror(0, 9, startExpressConveyor);
                if (!(((startExpressConveyor == 5) && (endExpressConveyor == 2)) ||
                        (startExpressConveyor == 6) && (endExpressConveyor == 1) ||
                        (startExpressConveyor == 8) && (endExpressConveyor == 4))) {
                    endExpressConveyor = ror(1, 4, endExpressConveyor);
                }
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "expressconveyorBelt/"
                              + "ExpressConveyorBelt" + startExpressConveyor
                              + endExpressConveyor + ".png")));
                expressConveyorBeltButton.setStyle(styleFiEdit);
            }
        });
        expressConveyorBeltButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "ExpressConveyorBelt", startExpressConveyor, endExpressConveyor);
            }
        });
        expressConveyorBeltButton.addListener(new TextTooltip("Express Conveyorbelt", 
                toolTipManager, skinFiEdit));
        
        
        ImageButton backupButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/Checkpoint.png"))));
        backupButton.setSize(picButtonSize, picButtonSize);
        backupButton.setPosition(picButtonPosX, picButtonPosY - 180);
        backupButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "BackupCheckpoint", 0, 0);
            }
        });
        backupButton.addListener(new TextTooltip("Backup Checkpoint", toolTipManager, skinFiEdit));
        
        ImageButton gearButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber + ".png"))));
        gearButton.setSize(picButtonSize, picButtonSize);
        gearButton.setPosition(picButtonPosX + 90, picButtonPosY - 180);
        gearButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                gearNumber = ror(1, 2, gearNumber);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/gear/Gear0" + gearNumber
                              + ".png")));
                gearButton.setStyle(styleFiEdit);
            }
        });
        gearButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(), 
                        "Gear", gearNumber, 0);
            }
        });
        gearButton.addListener(new TextTooltip("Gear", toolTipManager, skinFiEdit));
        
        
        ImageButton laserButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "laser/"
                        + "Laser0" + typeLaserNum + ".png"))));
        laserButton.setSize(picButtonSize, picButtonSize);
        laserButton.setPosition(picButtonPosX + 180, picButtonPosY - 180);
        laserButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                typeLaserNum = ror(0, 9, typeLaserNum);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "laser/"
                              + "Laser0" + typeLaserNum + ".png")));
                laserButton.setStyle(styleFiEdit);
            }
        });
        laserButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(), 
                        "Laser", typeLaserNum, 0);
            }
        });
        laserButton.addListener(new TextTooltip("Laser", toolTipManager, skinFiEdit));
        
        
        ImageButton repairSiteButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" + repairNum
                        + ".png"))));
        repairSiteButton.setSize(picButtonSize, picButtonSize);
        repairSiteButton.setPosition(picButtonPosX, picButtonPosY - 270);
        repairSiteButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                repairNum = ror(1, 2, repairNum);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/repairsite/RepairSite0" 
                              + repairNum + ".png")));
                repairSiteButton.setStyle(styleFiEdit);
            }
        });
        repairSiteButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(), 
                        "RepairSite", repairNum, 0);
            }
        });
        repairSiteButton.addListener(new TextTooltip("Repair Site", toolTipManager, skinFiEdit));
        
        
        ImageButton standardFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/StandardField.png"))));
        standardFieldButton.setSize(picButtonSize, picButtonSize);
        standardFieldButton.setPosition(picButtonPosX + 90, picButtonPosY - 270);
        standardFieldButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(), 
                        "StandardField", 0, 0);
            }
        });
        standardFieldButton.addListener(new TextTooltip("Standard Field",
                toolTipManager, skinFiEdit));
        
        ImageButton startFieldButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "startfield/"
                        + "StartField0" + startFieldNumber + ".png"))));
        startFieldButton.setSize(picButtonSize, picButtonSize);
        startFieldButton.setPosition(picButtonPosX + 180, picButtonPosY - 270);
        startFieldButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                startFieldNumber = ror(1, 8, startFieldNumber);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "startfield/"
                              + "StartField0" + startFieldNumber + ".png")));
                startFieldButton.setStyle(styleFiEdit);
            }
        });
        startFieldButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(), ypositionChangeField.getText(),
                        "StartField", startFieldNumber, 0);
            }
        });
        startFieldButton.addListener(new TextTooltip("Start Field", toolTipManager, skinFiEdit));

        
        ImageButton pusherButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion(new Texture("mapAssets/" + "pusher/"
                        + "Pusher0" + pusherNumber + ".png"))));
        pusherButton.setSize(picButtonSize, picButtonSize);
        pusherButton.setPosition(picButtonPosX + 90, picButtonPosY - 360);
        pusherButton.addListener(new ClickListener(Buttons.RIGHT) {
            public void clicked(InputEvent event, float x, float y) {
                pusherNumber = ror(1, 4, pusherNumber);
                ImageButtonStyle styleFiEdit = new ImageButtonStyle();
                styleFiEdit.up = new TextureRegionDrawable(
                      new TextureRegion(new Texture("mapAssets/" + "pusher/"
                              + "Pusher0" + pusherNumber + ".png")));
                pusherButton.setStyle(styleFiEdit);
            }
        });
        pusherButton.addListener(new ClickListener(Buttons.LEFT) {
            public void clicked(InputEvent event, float x, float y) {
                drawOnField(xpositionChangeField.getText(),
                        ypositionChangeField.getText(), "Pusher", pusherNumber, 0);
            }
        });
        pusherButton.addListener(new TextTooltip("Pusher", toolTipManager, skinFiEdit));
        
        
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
        stageFiEdit.addActor(xpositionChangeField);
        stageFiEdit.addActor(ypositionChangeField);
       
        
    }

    private void initBoardFiEdit(String fileName) {
        boardFiEdit = new Board(fileName);
    }

    @Override
    public void show() {
        
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
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }
    
    private int ror(int min, int max, int var) {
        if (var < max) {
            var += 1;
        } else {
            var = min;
        }
        //zeichnen !!!!!!!!
        return var;
    }
    
    private void drawOnField(String x, String y, String part, int startNum, int endNum) {
        bfFiEdit.oneStepDone(boardFiEdit);
        if (checkChangeField(x, y)) {
            int xtoInt = Integer.parseInt(x) - 1;
            int ytoInt = Integer.parseInt(y) - 1;
            switch (part) {
                case "BarrierCorner": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new BarrierCorner(0, 0, startNum);
                    break;
                case "BarrierSide": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new BarrierSide(0, 0, startNum);
                    break;
                case "BlackHole": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new BlackHole(0, 0);
                    break;
                case "Checkpoint": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new Checkpoint(0, 0, startNum);
                    break;
                case "ConveyorBelt": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt]
                        = new ConveyorBelt(0, 0, startNum, endNum);
                    break;
                case "ExpressConveyorBelt": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt]
                        = new ExpressConveyorBelt(0, 0, startNum, endNum);
                    break;
                case "BackupCheckpoint": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new Checkpoint(0, 0, startNum);
                    break;
                case "Gear": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new Gear(0, 0, startNum);
                    break;
                case "Laser": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new Laser(0, 0, startNum);
                    break;
                case "RepairSite": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new RepairSite(0, 0, startNum);
                    break;
                case "StandardField": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new StandardField(0, 0);
                    break;
                case "StartField": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new StartField(0, 0, startNum);
                    break;
                case "Pusher": 
                    boardFiEdit.fieldmatrix[xtoInt][ytoInt] = new Pusher(0, 0, startNum);
                    break;
                default: break;
            }
        }
    }
    
    private boolean checkChangeField(String x, String y) {
        return x.length() != 0 && y.length() != 0 && x.matches("[+-]?\\d*(\\.\\d+)?") &&
                y.matches("[+-]?\\d*(\\.\\d+)?");
    }
}
