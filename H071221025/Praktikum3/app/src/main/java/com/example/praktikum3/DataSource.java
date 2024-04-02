package com.example.praktikum3;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Main> main = generateDummyMain();

    private static ArrayList<Main> generateDummyMain() {
        ArrayList<Main> main = new ArrayList<>();
        main.add(new Main("Wrinkles", "Melamun", R.drawable.img1, R.drawable.post1, R.drawable.post1,5000000, 1));
        main.add(new Main("Tiger", "Aku punya kacamata baru nih boss!", R.drawable.img10, R.drawable.post10, R.drawable.post10, 5340000, 10));
        main.add(new Main("Si Manis", "Aku si cantik Putih", R.drawable.img5, R.drawable.post5, R.drawable.post5, 10000000, 5));
        main.add(new Main("Peanut", "Permisi Peri dari kayangan Kucing", R.drawable.img4, R.drawable.post4, R.drawable.post4, 7000000, 4));
        main.add(new Main("King Bollywood", "Jangan lupa kasih aku makan karna aku butuh nutrisi yang cukup", R.drawable.img6, R.drawable.post6, R.drawable.post6, 500000, 6));
        main.add(new Main("Dunki", "Foto muka saja dulu sama ayank", R.drawable.img7, R.drawable.post7, R.drawable.post7, 3000000, 7));
        main.add(new Main("Duggu", "Haiii Aku Oyen dan Grey", R.drawable.img9, R.drawable.post9, R.drawable.post9, 50045400, 9));
        main.add(new Main("Mochi", "Apa Lu Liat Liat", R.drawable.img2, R.drawable.post2, R.drawable.post2, 4000000, 2));
        main.add(new Main("Tofu", "Akukan Masih Bocil", R.drawable.img3, R.drawable.post3, R.drawable.post3, 6000000, 3));
        main.add(new Main("Pathan", "Kenalin ini saudara aku yang nama pinky", R.drawable.img8, R.drawable.post8, R.drawable.post8, 2000000, 8));
        return main;
    }
}