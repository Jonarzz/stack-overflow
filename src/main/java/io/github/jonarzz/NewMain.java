package io.github.jonarzz;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class NewMain {

    public static void main(String[] args) throws URISyntaxException {
        ClassLoader classLoader = QuestionMain.class.getClassLoader();
        URL resourceDirUrl = classLoader.getResource(".");
        File resourceDirFile = new File(resourceDirUrl.toURI());
        // source file is copied to the `target/classes` directory
        // target file is created in the `target/classes` directory
        editFile(new File(resourceDirFile, "source"), new File(resourceDirFile, "target"));
    }

    private static void editFile(File source, File target) {
        Charset charset = StandardCharsets.ISO_8859_1;
        String fileContent;
        try {
            fileContent = Files.readString(source.toPath(), charset);
        } catch (IOException exception) {
            System.err.println("Could not read input file as a single String.");
            exception.printStackTrace();
            return;
        }
        String newContent = regex(fileContent);
        try {
            Files.writeString(target.toPath(), newContent, charset);
        } catch (IOException exception) {
            System.err.println("Could not write out edited file!");
            exception.printStackTrace();
        }
    }

    private static String regex(String fileContent) {
        return fileContent.replaceAll("\\d", "X");
    }

}
