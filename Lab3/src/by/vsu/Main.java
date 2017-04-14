package by.vsu;

import by.vsu.arrays.ArrayUniDirectionalList;

public class Main
{
    public static void main(String[] args)
    {
        ArrayUniDirectionalList<Integer> arrayUnidirectionalList = new ArrayUniDirectionalList<>(4);
        arrayUnidirectionalList.pushBack(10);
        System.out.println(arrayUnidirectionalList);
        arrayUnidirectionalList.pushBack(20);
        System.out.println(arrayUnidirectionalList);
        arrayUnidirectionalList.pushBack(30);
        System.out.println(arrayUnidirectionalList);
        arrayUnidirectionalList.pushBack(40);
        System.out.println(arrayUnidirectionalList);
    }
}
