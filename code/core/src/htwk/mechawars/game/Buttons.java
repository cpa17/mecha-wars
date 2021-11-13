package htwk.mechawars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import htwk.mechawars.board.Robot;

public class Buttons extends GameScreen {

    public static Button startButton(Skin skin, Robot player) {
        Button startExecutionButton = new TextButton("Ausfuehrung starten", skin);
        startExecutionButton.setSize(160, 43);

        int startExecutionButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int startExecutionButtonY = Gdx.graphics.getHeight() - 100;

        startExecutionButton.setPosition(startExecutionButtonX, startExecutionButtonY);

        startExecutionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!player.getShutDown()) {
                    //If All Cards are chosen
                    if (cardOrder[4 - damagePoints] != -1) {
                        deactivateButtons();
                        zugInitialisierung.initialisiereBewegung();
                        board.move(zugInitialisierung.getList(), player);
                        zugInitialisierung.resetList();
                        startExecutionButton.setColor(Color.LIGHT_GRAY);
                        cardOrderClear();
                        activateButtons();
                        updateButtons();
                    } else {
                        startExecutionButton.setColor(Color.RED);
                    }
                } else {
                    zugInitialisierung.initialisiereBewegung();
                    board.move(zugInitialisierung.getList(), player);
                    zugInitialisierung.resetList();
                    startExecutionButton.setColor(Color.LIGHT_GRAY);
                    updateButtons();
                }
            }
        });

        return startExecutionButton;
    }

    public static Button endButton(Skin skin) {
        Button endGameButton = new TextButton("Spiel beenden", skin);

        endGameButton.setSize(160, 43);

        int endGameButtonX = Gdx.graphics.getHeight()
                + (((Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) * 2) / 3) - 64;
        int endGameButtonY = Gdx.graphics.getHeight() - 100;

        endGameButton.setPosition(endGameButtonX, endGameButtonY);

        endGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        return endGameButton;
    }

    public static Button removeButton(Skin skin) {
        Button removeCardOrder = new TextButton("Loesche\nKartenreihenfolge", skin);

        removeCardOrder.setSize(128, 43);
        int removeCardOrderX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int removeCardOrderY = Gdx.graphics.getHeight() - 200;

        removeCardOrder.setPosition(removeCardOrderX, removeCardOrderY);

        removeCardOrder.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                cardOrderClear();
                zugInitialisierung.resetList();
            }

        });

        return removeCardOrder;
    }

    private static void deactivateButtons() {
        for (TextButton button : buttons) {
            button.setTouchable(Touchable.disabled);
        }
    }

    private static void activateButtons() {
        for (TextButton button : buttons) {
            button.setTouchable(Touchable.enabled);
        }
    }

    public static Button infoButton(Skin skin) {
        // add Button for hint and infos
        Button buttonInfo = new TextButton("Infos", skin);

        int a = 60;     // width
        int b = 40;     // height

        buttonInfo.setSize(a, b);
        int buttonInfoX = Gdx.graphics.getWidth() - (a + 10);
        int buttonInfoY = Gdx.graphics.getHeight() - (b + 10);

        buttonInfo.setPosition(buttonInfoX, buttonInfoY);

        buttonInfo.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                new Dialog("Infos und Hinweise", skin) {
                    // best constructor for Dialog in LibGDX
                    {
                        int oneLine = 17;
                        int twoLine = 31;
                        int threeLine = 44;
                        int fourLine = 58;
                        int fiveLine = 69;
                        int sixLine = 83;
                        // Cards
                        Label movOne = new Label("1 Vor  ", skin);
                        movOne.setColor(Color.CHARTREUSE);
                        Label movTwo = new Label("2 Vor  ", skin);
                        movTwo.setColor(Color.CHARTREUSE);
                        Label movThree = new Label("3 Vor ", skin);
                        movThree.setColor(Color.CHARTREUSE);
                        Label movBack = new Label("Rueckwaerts  ", skin);
                        movBack.setColor(Color.CHARTREUSE);
                        Label turnL = new Label("Linksdrehung  ", skin);
                        turnL.setColor(Color.CHARTREUSE);
                        Label turnR = new Label("Rechtsdrehung  ", skin);
                        turnR.setColor(Color.CHARTREUSE);
                        Label turnU = new Label("Kehrtwendung  ", skin);
                        turnU.setColor(Color.CHARTREUSE);
                        // Field
                        Label freeTile = new Label("Freies Feld  ", skin);
                        freeTile.setColor(Color.CORAL);
                        Label pitTile = new Label("Grube  ", skin);
                        pitTile.setColor(Color.CORAL);
                        Label wallTile = new Label("Wand  ", skin);
                        wallTile.setColor(Color.CORAL);
                        Label conveyorTile = new Label("Foerderband  ", skin);
                        conveyorTile.setColor(Color.CORAL);
                        Label expressConveyorTile = new Label("Expressband  ", skin);
                        expressConveyorTile.setColor(Color.CORAL);
                        Label rotatingConveyorTile = new Label("Rotierendes "
                                + "Foerderband  ", skin);
                        rotatingConveyorTile.setColor(Color.CORAL);
                        Label rotatingMergingConveyorTile = new Label("Rotierendes "
                                + "und ineinander uebergehendes Foerderband  ", skin);
                        rotatingMergingConveyorTile.setColor(Color.CORAL);
                        Label rotatingDoubleMergingConveyorTile = new Label("Rotierendes "
                                + "und zweisitiges ineinander uebergehendes Foerderband  ", skin);
                        rotatingDoubleMergingConveyorTile.setColor(Color.CORAL);
                        Label pusherTile = new Label("Schieber  ", skin);
                        pusherTile.setColor(Color.CORAL);
                        Label clockwiseTile = new Label("Im Uhrzeigersinn "
                                + "laufende Zahnraeder  ", skin);
                        clockwiseTile.setColor(Color.CORAL);
                        Label counterClockwiseTile = new Label("Gegen den Uhrzeigersinn "
                                + "laufende Zahnraeder  ", skin);
                        counterClockwiseTile.setColor(Color.CORAL);
                        Label laserTile = new Label("Laserbeschuss  ", skin);
                        laserTile.setColor(Color.CORAL);
                        Label checkpointFlagTile = new Label("Checkpoint  ", skin);
                        checkpointFlagTile.setColor(Color.CORAL);
                        Label repairTile = new Label("Reparaturfeld  ", skin);
                        repairTile.setColor(Color.CORAL);
                        Label dockTile = new Label("Dock  ", skin);
                        dockTile.setColor(Color.CORAL);
                        // Field Text
                        TextField movOneText = new TextField("Bewegt den Roboter 1 Feld in "
                                + "Blickrichtung vor.", skin);
                        TextField movTwoText = new TextField("Bewegt den Roboter 2 Felder "
                                + "Blickrichtung vor.", skin);
                        TextField movThreeText = new TextField("Bewegt den Roboter 3 Felder "
                                + "Blickrichtung vor.", skin);
                        TextField movBackText = new TextField("Bewegt den Roboter 1 Feld "
                                + "Blickrichtung zurueck.", skin);
                        TextField turnLtext = new TextField("Dreht den Roboter "
                                + "nach Links.", skin);
                        TextField turnRtext = new TextField("Dreht den Roboter "
                                + "nach Rechts.", skin);
                        TextField turnUtext = new TextField("Dreht den Roboter um.", skin);
                        TextField freeTileText = new TextField("Durch diese Felder koennen "
                                + "sich Roboter ungehindert bewegen.", skin);
                        TextArea pitTileText = new TextArea("Roboter werden zerstoert, "
                                + "wenn sie sich auf diese Felder bewegen oder "
                                + "auf sie bewegt werden. Offene Raender des Spielplans "
                                + "werden ebenfalls wie Gruben behandelt.", skin);
                        TextArea wallTileText = new TextArea("Roboter k�nnen sich nicht durch"
                                + " Waende bewegen oder durch sie hindurch schiessen. "
                                + "Roboter, die versuchen, sich durch Waende zu bewegen, "
                                + "bleiben stehen. Benachbarte Waende zwischen zwei Spielplaenen "
                                + "zaehlen als eine Wand, nicht als zwei.", skin);
                        TextArea conveyorTileText = new TextArea("Normale Foerderbaender "
                                + "bewegen sich in jedem Programmschritt einmal.", skin);
                        TextArea expressConveyorTileText = new TextArea("Expressbaender "
                                + "bewegen sich in jedem Programmschritt zweimal.", skin);
                        TextArea rotatingConveyorTileText = new TextArea("Wenn ein Foerderband "
                                + "einen Roboter auf ein solches Feld bewegt, drehe den Roboter "
                                + "um 90 Grad in Pfeilrichtung.", skin);
                        TextArea rotatingMergingConveyorTileText = new TextArea("Wenn ein "
                                + "ineinander uebergehendes Foerderband einen Roboter von der "
                                + "Seite, aus der der gekruemmte Pfeil kommt, auf ein solches "
                                + "Feld bewegt, drehe den Roboter "
                                + "um 90 Grad in Pfeilrichtung.", skin);
                        TextArea rotatingDoubleMergingConveyorTileText = new TextArea("Wenn "
                                + "ein Foerderband einen Roboter von einer beliebigen Seite auf "
                                + "ein solches Feld bewegt, drehe den Roboter um 90 Grad in "
                                + "Pfeilrichtung.", skin);
                        TextArea pusherTileText = new TextArea("Wenn sich ein Roboter auf diesem "
                                + "Feld befindet, wenn der Schieber aktiv ist, wird der Roboter "
                                + "auf "
                                + "das naechste gegenueberliegende Feld geschoben. Schieber "
                                + "koennen "
                                + "mehrere Roboter bewegen und sind nur in den Programmschritten "
                                + "aktiv, die auf dem Schieber angegeben sind. "
                                + "(Dieser Schieber ist im zweiten und vierten Programmschritt "
                                + "aktiv.)", skin);
                        TextArea clockwiseTileText = new TextArea("Diese Zahnraeder drehen einen"
                                + "Roboter um 90 Grad im "
                                + "Uhrzeigersinn in Richtung der Pfeile.", skin);
                        TextArea counterClockwiseTileText = new TextArea("Diese Zahnraeder drehen "
                                + "einen Roboter um 90 Grad gegen den "
                                + "Uhrzeigersinn in Richtung der "
                                + "Pfeile.", skin);
                        TextArea laserTileText = new TextArea("Roboter, die am Ende eines "
                                + "Programmschritts in einem Laserstrahl ste-hen, erhalten "
                                + "fuer jeden Laserstrahl in diesem Feld 1 Schadenspunkt "
                                + "[Damage Token]."
                                + " Beenden zwei oder mehr Roboter ihre Bewegung im selben "
                                + "Laserstrahl,"
                                + " so erhaelt nur derjenige einen Schadenspunkt, der der "
                                + "Abschussvorrichtung des Lasers am naechsten steht.", skin);
                        TextArea checkpointFlagTileText = new TextArea("Ein Roboter, der sich"
                                + " am Ende eines Programm-schritts auf einem Feld mit einem "
                                + "Checkpoint [Flag] befindet, legt seine Sicherheitskopie "
                                + "[Archive Marker] auf dieses Feld und der Checkpoint [Flag] "
                                + "zaehlt fuer das Erreichen des Sieges im Rennen.\nEin Roboter, "
                                + "der sich am Ende eines Zuges auf einem Feld mit einem "
                                + "Checkpoint befindet, legt 1 Schadenspunkt [Damage Token] "
                                + "ab.", skin);
                        TextArea repairTileText = new TextArea("Ein Roboter, der sich am Ende "
                                + "eines Programm-schritts auf einem beliebigen Reparaturfeld "
                                + "befindet, legt seine Sicherheitskopie [Archive Marker] auf "
                                + "dieses Feld.\nEin Roboter, der sich am Ende eines Zuges auf "
                                + "einem Feld mit einem einzelnen Schraubenschluessel befindet, "
                                + "legt 1 Schadenspunkt [Damage Token] ab. Ein Roboter, der sich "
                                + "auf einem Feld mit gekreuztem Schraubenschluessel und Hammer "
                                + "befindet, legt 1 Scha-denspunkt [Damage Token] ab und zieht "
                                + "1 Optionskarte.", skin);
                        TextArea dockTileText = new TextArea("Die nummerierten Docks auf dem "
                                + "Spielplan Andockstation [Docking Bay Board] werden als "
                                + "Startfelder fuer die Roboter und ihre Sicherheitskopien "
                                + "verwendet. Sie dienen keinem weiteren Zweck und gelten "
                                + "ansonsten als freie Felder.", skin);
                        // TextArea/Field not changeable -> disable
                        movOneText.setDisabled(true);
                        movTwoText.setDisabled(true);
                        movThreeText.setDisabled(true);
                        movBackText.setDisabled(true);
                        turnLtext.setDisabled(true);
                        turnRtext.setDisabled(true);
                        turnUtext.setDisabled(true);
                        freeTileText.setDisabled(true);
                        pitTileText.setDisabled(true);
                        wallTileText.setDisabled(true);
                        conveyorTileText.setDisabled(true);
                        expressConveyorTileText.setDisabled(true);
                        rotatingConveyorTileText.setDisabled(true);
                        rotatingDoubleMergingConveyorTileText.setDisabled(true);
                        rotatingMergingConveyorTileText.setDisabled(true);
                        pusherTileText.setDisabled(true);
                        clockwiseTileText.setDisabled(true);
                        counterClockwiseTileText.setDisabled(true);
                        laserTileText.setDisabled(true);
                        checkpointFlagTileText.setDisabled(true);
                        repairTileText.setDisabled(true);
                        dockTileText.setDisabled(true);
                        Table table = new Table();
                        table.center();
                        // add elements to table
                        table.add(movOne).align(Align.right);
                        table.add(movOneText).width(650);
                        table.row();
                        table.add(movTwo).align(Align.right);
                        table.add(movTwoText).width(650);
                        table.row();
                        table.add(movThree).align(Align.right);
                        table.add(movThreeText).width(650);
                        table.row();
                        table.add(movBack).align(Align.right);
                        table.add(movBackText).width(650);
                        table.row();
                        table.add(turnL).align(Align.right);
                        table.add(turnLtext).width(650);
                        table.row();
                        table.add(turnR).align(Align.right);
                        table.add(turnRtext).width(650);
                        table.row();
                        table.add(turnU).align(Align.right);
                        table.add(turnUtext).width(650);
                        table.row();
                        //
                        table.add(freeTile).align(Align.right);
                        table.add(freeTileText).width(650);
                        table.row();
                        table.add(pitTile).align(Align.right);
                        table.add(pitTileText).width(650).height(oneLine);
                        table.row();
                        table.add(wallTile).align(Align.right);
                        table.add(wallTileText).width(650).height(threeLine);
                        table.row();
                        table.add(conveyorTile).align(Align.right);
                        table.add(conveyorTileText).width(650).height(oneLine);
                        table.row();
                        table.add(expressConveyorTile).align(Align.right);
                        table.add(expressConveyorTileText).width(650).height(oneLine);
                        table.row();
                        table.add(rotatingConveyorTile).align(Align.right);
                        table.add(rotatingConveyorTileText).width(650).height(twoLine);
                        table.row();
                        table.add(rotatingMergingConveyorTile).align(Align.right);
                        table.add(rotatingMergingConveyorTileText).width(650).height(twoLine);
                        table.row();
                        table.add(rotatingDoubleMergingConveyorTile).align(Align.right);
                        table.add(rotatingDoubleMergingConveyorTileText).width(650)
                                .height(twoLine);
                        table.row();
                        table.add(pusherTile).align(Align.right);
                        table.add(pusherTileText).width(650).height(fourLine);
                        table.row();
                        table.add(clockwiseTile).align(Align.right);
                        table.add(clockwiseTileText).width(650).height(oneLine);
                        table.row();
                        table.add(counterClockwiseTile).align(Align.right);
                        table.add(counterClockwiseTileText).width(650).height(oneLine);
                        table.row();
                        table.add(laserTile).align(Align.right);
                        table.add(laserTileText).width(650).height(fourLine);
                        table.row();
                        table.add(checkpointFlagTile).align(Align.right);
                        table.add(checkpointFlagTileText).width(650).height(fiveLine);
                        table.row();
                        table.add(repairTile).align(Align.right);
                        table.add(repairTileText).width(650).height(sixLine);
                        table.row();
                        table.add(dockTile).align(Align.right);
                        table.add(dockTileText).width(650).height(threeLine);
                        table.row();
                        add(table);

                        button("Schliessen", "Button pressed");
                    }

                    @Override
                    protected void result(Object object) {
                        //super.result(object);
                        System.out.println(object);
                    }

                }.show(stage); //.setHeight(600);
            }
        });

        return buttonInfo;
    }

    public static Button wakeUpButton(Skin skin, Robot player) {
        Button wakeUpButton = new TextButton("WakeUp", skin);

        wakeUpButton.setSize(160, 43);

        int wakeUpButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int wakeUpButtonY = Gdx.graphics.getHeight() - 600;

        wakeUpButton.setPosition(wakeUpButtonX, wakeUpButtonY);

        wakeUpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (player.getNextRound()) {
                    player.setNextRound(false);
                    wakeUpButton.setColor(Color.GREEN);
                    System.out.println("wake up");
                } else {
                    player.setNextRound(true);
                    wakeUpButton.setColor(Color.LIGHT_GRAY);
                    System.out.println("off");
                }
            }
        });

        return wakeUpButton;
    }

    public static Button shutDownButton(Skin skin, Robot player) {
        Button shutDownButton = new TextButton("ShutDown", skin);

        shutDownButton.setSize(160, 43);

        int shutDownButtonX = Gdx.graphics.getHeight()
                + (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 3 - 64;
        int shutDownButtonY = Gdx.graphics.getHeight() - 400;

        shutDownButton.setPosition(shutDownButtonX, shutDownButtonY);

        shutDownButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!player.getNextRound()) {
                    player.setNextRound(true);
                    shutDownButton.setColor(Color.GREEN);
                    System.out.println("shutdown");
                } else {
                    player.setNextRound(false);
                    shutDownButton.setColor(Color.LIGHT_GRAY);
                    System.out.println("awake");
                }
            }
        });

        return shutDownButton;
    }
}
