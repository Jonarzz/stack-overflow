package io.github.jonarzz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class QuestionMain {

    public static void main(String[] args) throws URISyntaxException {
        ClassLoader classLoader = QuestionMain.class.getClassLoader();
        URL resourceDirUrl = classLoader.getResource(".");
        File resourceDirFile = new File(resourceDirUrl.toURI());
        // source file is copied to the `target/classes` directory
        // target file is created in the `target/classes` directory
        editFile(new File(resourceDirFile, "source"), new File(resourceDirFile, "target"));
    }

    private static void editFile(File source, File target) {
        // Source and target encoding
        Charset charset = StandardCharsets.ISO_8859_1;

        // Read the file as a single string
        String fileContent;

        try (Scanner scanner = new Scanner(source, charset)) {
            fileContent = scanner.useDelimiter("\\n").next();
        } catch (IOException exception) {
            System.err.println("Could not read input file as a single String.");
            exception.printStackTrace();
            return;
        }

        // Do some regex substitutions on the fileContent string
        String newContent = regex(fileContent);

        // Write the file back out in target encoding
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), charset))) {
            System.out.println("Writing " + newContent + " to file " + target);
            writer.write(newContent);
        } catch (Exception exception) {
            System.err.println("Could not write out edited file!");
            exception.printStackTrace();
        }
    }

    private static String regex(String fileContent) {
        return fileContent.replaceAll("\\d", "X");
    }

}
