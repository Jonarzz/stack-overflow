package io.github.jonarzz;

class Question implements Replacer {

    @Override
    public String replace(String input, String[] dictionary) {
        for (int i = 0; i < input.length() - 1; i++) {
            char c = input.charAt(i);
            if (c == '[') {
                int ind = input.indexOf(']', i);
                String name = input.substring(i + 1, ind);
                for (int j = 0; j < dictionary.length; j++) {
                    int ind1 = dictionary[j].indexOf(' ', 4);
                    String name1 = dictionary[j].substring(4, ind1);
                    String p = dictionary[j].substring(0, 3);
                    String help = "Tel";
                    int temp = p.compareTo(help);
                    if (name.equals(name1) == true && temp == 0) {
                        String telephone = dictionary[j].substring(ind1 + 1, dictionary[j].length());
                        input = input.replaceFirst(name, telephone);
                    }
                }
            }
            if (c == '{') {
                int ind = input.indexOf('}', i);
                String name = input.substring(i + 1, ind);
                for (int j = 0; j < dictionary.length; j++) {
                    int ind1 = dictionary[j].indexOf(' ', 5);
                    String name1 = dictionary[j].substring(5, ind1);
                    String p = dictionary[j].substring(0, 4);
                    if (name.equals(name1) && p.compareTo("Mail") == 0) {
                        String mail = dictionary[j].substring(ind1 + 1, dictionary[j].length());
                        input = input.replaceFirst(name, mail);
                    }
                }
            }
        }
        return input;
    }

}
