package util;

import util.utility.Goomba;
import java.util.ArrayList;

import java.util.List;

public class Loadmap {
    Elements[][][] map;
    Loadmap(int x,int y){
        map=new Elements[x][y][5];
    }

    public static void main(String[] args) {
        Loadmap loadmap=new Loadmap(50000,2000);
        System.out.println(loadmap.map[5][5]);
    }
}
