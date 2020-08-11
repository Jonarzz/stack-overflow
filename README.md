# Question
https://stackoverflow.com/questions/63363359/reading-and-writing-file-in-iso-8859-1-encoding

#Answer

There is nothing actually wrong with your code. Notepad++ sees the file encoded in UTF-8 because on a basic level there is no difference between UTF-8 and the encoding you're trying to use. Only specific characters differ and some (a lot) are missing from ISO compared to UTF. You can read more [here][1] or by simply searching `ISO-8859-1 vs UTF-8` in Google.

[I've created a simple project with your code][2] and tested it with characters that are different for the ISO encoding - the result is a file that IntelliJ (and probably Notepad++ as well - cannot easily check, I'm on Linux) recognizes as ISO-8859-1. Apart from that, I've added another class that makes use of new (JDK11) features from `Files` class. The `new Scanner(source, charset)` that you've used was added in JDK10, so I think that you may be using 11 already. Here's the simplified code:

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

Feel free to clone the repository or check it on GitHub and use whichever code version you prefer.


  [1]: https://en.wikipedia.org/wiki/ISO/IEC_8859-1
  [2]: https://github.com/Jonarzz/stack-overflow/tree/63363359