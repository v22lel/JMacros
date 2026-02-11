package dev.v22.jmacros.mvn;

import dev.v22.jmacros.processor.MacroCollector;
import dev.v22.jmacros.processor.MacroProcessor;
import dev.v22.jmacros.processor.extract.MacroCallFinder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

@Mojo(name = "process-macros", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class MacroMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true)
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/macro")
    private File outputDirectory;

    @Parameter(property = "skip", defaultValue = "false")
    private boolean skip;

    @Override
    public void execute() throws MojoExecutionException {
        if (skip) {
            return;
        }

        try {
            Path srcDir = sourceDirectory.toPath();
            Path outDir = outputDirectory.toPath();

            Files.createDirectories(outDir);

            Path macrosTarget = project.getBasedir().toPath().resolve("../Macros/target/classes");
            getLog().warn("macros target: " + macrosTarget);
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{ macrosTarget.toUri().toURL() },
                    Thread.currentThread().getContextClassLoader()
            );

            MacroCollector macroCollector = new MacroCollector(loader);
            macroCollector.scanClassFiles();


            Files.walk(srcDir)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(path -> processFile(path, srcDir, outDir, macroCollector));

            project.getCompileSourceRoots().remove(project.getBuild().getSourceDirectory());

            project.addCompileSourceRoot(outputDirectory.getAbsolutePath());

        } catch (IOException | URISyntaxException e) {
            throw new MojoExecutionException("Macro preprocessing failed", e);
        }
    }

    private void processFile(Path file, Path srcRoot, Path outRoot, MacroCollector macroCollector) {
        try (InputStream in = Files.newInputStream(file)) {

            MacroCallFinder finder = new MacroCallFinder(in, file);
            MacroProcessor processor = new MacroProcessor(finder, macroCollector);

            String transformed = processor.processWholeFile(file);

            Path relative = srcRoot.relativize(file);
            Path outputFile = outRoot.resolve(relative);

            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, transformed);

            getLog().info("Processed: " + file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
