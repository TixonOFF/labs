package by.vsu;

import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
        HashMap<Character, Integer> alphabet = new HashMap<>();
        alphabet.put(' ', 13);
        alphabet.put('а', 8);
        alphabet.put('б', 1);
        alphabet.put('в', 4);
        alphabet.put('г', 1);
        alphabet.put('д', 2);
        alphabet.put('е', 8);
        alphabet.put('ё', 1);
        alphabet.put('ж', 1);
        alphabet.put('з', 2);
        alphabet.put('и', 7);
        alphabet.put('й', 1);
        alphabet.put('к', 3);
        alphabet.put('л', 4);
        alphabet.put('м', 3);
        alphabet.put('н', 6);
        alphabet.put('о', 9);
        alphabet.put('п', 3);
        alphabet.put('р', 5);
        alphabet.put('с', 5);
        alphabet.put('т', 6);
        alphabet.put('у', 3);
        alphabet.put('ф', 1);
        alphabet.put('х', 1);
        alphabet.put('ц', 1);
        alphabet.put('ч', 1);
        alphabet.put('ш', 1);
        alphabet.put('щ', 1);
        alphabet.put('ъ', 1);
        alphabet.put('ы', 2);
        alphabet.put('ь', 2);
        alphabet.put('э', 1);
        alphabet.put('ю', 1);
        alphabet.put('я', 2);

        Tree tree = new Tree(alphabet);

        String message = "очень сложное сообщение";

        System.out.println(tree.getByteMessage(message));
        System.out.println(tree.getStringMessage(tree.getByteMessage(message)));
    }
}
