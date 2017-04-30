package by.vsu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tree
{
    private Node root;
    private HashMap<Character, Integer> alphabet;


    public Tree(HashMap<Character, Integer> alphabet)
    {
        this.alphabet = new HashMap<>(alphabet);

        StringBuilder root = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : alphabet.entrySet())
        {
            root.append(entry.getKey());
        }

        this.root = new Node(root.toString());

        char[] charArray = this.root.getValue().toCharArray();
        int length = charArray.length;

        for (int i = 0; i < length - 1; i++)
        {
            for (int j = 0; j < length - i - 1; j++)
            {
                if (alphabet.get(charArray[j]) < alphabet.get(charArray[j + 1]))
                {
                    char c = charArray[j];
                    charArray[j] = charArray[j + 1];
                    charArray[j + 1] = c;
                }
            }
        }

        setNodes(this.root, charArray);
    }

    private void setNodes(Node node, char[] symbols)
    {
        if (symbols.length > 1)
        {
            int leftSum = alphabet.get(symbols[0]);
            int rightSum = 0;

            for (Map.Entry<Character, Integer> entry : alphabet.entrySet())
            {
                rightSum += entry.getValue();
            }
            rightSum -= leftSum;

            int i = 2;
            for (; i < symbols.length; i++)
            {
                int weight = alphabet.get(symbols[i - 1]);

                if (Math.abs(leftSum - rightSum) > Math.abs((leftSum + weight) - (rightSum - weight)))
                {
                    leftSum += weight;
                    rightSum -= weight;
                    continue;
                }

                break;
            }


            StringBuilder left = new StringBuilder();
            for (int j = 0; j < i - 1; j++)
            {
                left.append(symbols[j]);
            }

            node.setLeft(new Node(left.toString()));
            setNodes(node.getLeft(), Arrays.copyOfRange(symbols, 0, i - 1));


            StringBuilder right = new StringBuilder();
            for (int j = i - 1; j < symbols.length; j++)
            {
                right.append(symbols[j]);
            }

            node.setRight(new Node(right.toString()));
            setNodes(node.getRight(), Arrays.copyOfRange(symbols, i - 1, symbols.length));
        }
        else
        {
            node.setLeft(new Node("" + symbols[0]));
        }
    }

    public Node getRoot()
    {
        return root;
    }

    public void setRoot(Node root)
    {
        this.root = root;
    }

    public String getCode(char symbol)
    {
        if (root != null && root.getValue().indexOf(symbol) != -1)
        {
            if (root.getValue().length() == 1)
            {
                return "0";
            }

            return getCode(root, symbol);
        }

        return null;
    }

    private String getCode(Node node, char symbol)
    {
        if (node.getValue().length() == 1)
        {
            return "";
        }

        if (node.getLeft() != null && node.getLeft().getValue().indexOf(symbol) != -1)
        {
            return "0" + getCode(node.getLeft(), symbol);
        }

        if (node.getRight() != null && node.getRight().getValue().indexOf(symbol) != -1)
        {
            return "1" + getCode(node.getRight(), symbol);
        }

        return null;
    }

    public String getByteMessage(String stringMessage)
    {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < stringMessage.length(); i++)
        {
            res.append(getCode(stringMessage.charAt(i)));
        }

        return res.toString();
    }

    public String getStringMessage(String byteMessage)
    {
        StringBuilder res = new StringBuilder();
        StringBuilder symbol = new StringBuilder();

        for (int i = 0; i < byteMessage.length(); i++)
        {
            symbol.append(byteMessage.charAt(i));

            Character character = getLeaf(symbol.toString());
            if (character != null)
            {
                res.append(character);

                symbol = new StringBuilder();
            }
        }

        return res.toString();
    }

    private Character getLeaf(String byteCode)
    {
        try
        {
            String res = getLeaf(root, byteCode);

            if (res.length() == 1)
            {
                return res.charAt(0);
            }
        }
        catch (NullPointerException e)
        {
            return null;
        }

        return null;
    }

    private String getLeaf(Node node, String byteCode)
    {
        if (byteCode.equals(""))
        {
            return node.getValue();
        }
        else
        {
            if (byteCode.charAt(0) == '0' && node.getLeft() != null)
            {
                StringBuilder newByteCode = new StringBuilder();
                for (int i = 1; i < byteCode.length(); i++)
                {
                    newByteCode.append(byteCode.charAt(i));
                }

                return getLeaf(node.getLeft(), newByteCode.toString());
            }

            if (byteCode.charAt(0) == '1' && node.getRight() != null)
            {
                StringBuilder newByteCode = new StringBuilder();
                for (int i = 1; i < byteCode.length(); i++)
                {
                    newByteCode.append(byteCode.charAt(i));
                }

                return getLeaf(node.getRight(), newByteCode.toString());
            }
        }

        return null;
    }
}
