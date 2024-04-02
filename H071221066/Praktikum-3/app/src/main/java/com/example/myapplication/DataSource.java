package com.example.myapplication;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Instagram> instagrams = generateDummyInstagram();

//    mengembalikan sebuah ArrayList dari objek Instagram
    private static ArrayList<Instagram> generateDummyInstagram() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();
        instagrams1.add(new Instagram("seventeen.17", "Hi, carat!", R.drawable.svtlogo, R.drawable.svtgroup2, R.drawable.svt4,889, 1));
        instagrams1.add(new Instagram("flowerror", "Beli beli", R.drawable.bunga1, R.drawable.bunga2, R.drawable.bunga3, 1350, 45));
        instagrams1.add(new Instagram("shinseul-ki", "Officially S.Kom", R.drawable.shin, R.drawable.shin, R.drawable.shin2, 139, 172));
        instagrams1.add(new Instagram("cat_lovers", "Sunshine", R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, 1234, 1));
        instagrams1.add(new Instagram("informa.id", "Dapatkan ruangan idaman anda", R.drawable.informa, R.drawable.informa2, R.drawable.informa3, 990, 5));
        instagrams1.add(new Instagram("lautkita", "...", R.drawable.laut1, R.drawable.laut2, R.drawable.laut3, 789, 0));
        instagrams1.add(new Instagram("beautyfull", "Ada yang baru nih", R.drawable.mu1, R.drawable.mu2, R.drawable.mu3, 4234, 4));
        instagrams1.add(new Instagram("nail.art", "cutie<3", R.drawable.nail1, R.drawable.nail2, R.drawable.nail3, 998, 10));
        instagrams1.add(new Instagram("upinpin", "Eps baruuuu", R.drawable.ui, R.drawable.ui2, R.drawable.ui3, 1000, 3));
        instagrams1.add(new Instagram("kim_taeri", "Syuting drama 2521", R.drawable.kimtaeri, R.drawable.kimtaeri, R.drawable.kimtaeri2, 5689, 1));
        return instagrams1;
    }
}