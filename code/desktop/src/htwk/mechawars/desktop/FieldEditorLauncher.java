package htwk.mechawars.desktop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import htwk.mechawars.MechaWars;
import htwk.mechawars.fieldeditor.FieldEditor;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Class containing the runner for the desktop frontend.
 * Write .\gradlew.bat desktop:dist in your console,
 * in the writer code in order to provide the jar-file.
 * One can find the jar-file under mecha-wars -> code -> desktop -> build -> libs.
 * Execute the file in your shell with the command
 * java -jar mw.jar, get all options with the command java -jar mw.jar -h.
 */

@Command(
        version = "Mecha Wars 1.0",
        header = "Mecha Wars Field Editor",
        mixinStandardHelpOptions = true)
public class FieldEditorLauncher implements Runnable {

    /**
     * Main class, for the new CommandLine.
     */
    public static void main(String[] args) {
        if (restartJvm(args)) {
            return;
        }
        System.exit(new CommandLine(new FieldEditorLauncher()).execute(args));
    }

    /**
     * Method, running the desktop frontend.
     */
    @Override
    public void run() {
        FieldEditor.setMap("maps/test.txt");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new FieldEditor(), config);
    }

    /**
     * Method, for fixing bug with macOS.
     */
    public static boolean restartJvm(String[] args) {

        String osName = System.getProperty("os.name");

        // if not a mac or linux return false
        if (!osName.startsWith("Mac") && !osName.startsWith("Darwin")) {
            return false;
        }

        // get current jvm process pid
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        // get environment variable on whether XstartOnFirstThread is enabled
        String env = System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" + pid);

        // if environment variable is "1" then XstartOnFirstThread is enabled
        if (env != null && env.equals("1")) {
            return false;
        }

        // restart jvm with -XstartOnFirstThread
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String mainClass = System.getenv("JAVA_MAIN_CLASS_" + pid);
        String jvmPath = System.getProperty("java.home") + separator + "bin" + separator + "java";

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();

        ArrayList<String> jvmArgs = new ArrayList<String>();

        jvmArgs.add(jvmPath);
        jvmArgs.add("-XstartOnFirstThread");
        jvmArgs.addAll(inputArguments);
        jvmArgs.add("-cp");
        jvmArgs.add(classpath);
        jvmArgs.add(mainClass);
        Collections.addAll(jvmArgs, args);

        // if you don't need console output, just enable these two lines
        // and delete bits after it. This JVM will then terminate.
        //ProcessBuilder processBuilder = new ProcessBuilder(jvmArgs);
        //processBuilder.start();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(jvmArgs);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),
                                                                         Charset.forName("UTF-8")));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}



