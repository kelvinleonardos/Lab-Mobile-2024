package com.example.h071221045_tuprak3;

import com.example.h071221045_tuprak3.Account;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<Account> accounts=generateDummyAccounts();

    private static ArrayList<Account> generateDummyAccounts() {
        ArrayList<Account> accounts=new ArrayList<>();
        accounts.add(new Account("moon",
                R.drawable.moon_story,
                R.drawable.moon_post,
                "Bulan diatas awan",
                500,
                69,
                R.drawable.moon_profile));
        accounts.add(new Account("sun",
                R.drawable.sun_story,
                R.drawable.sun_post,
                "Menyalah Abangku",
                500,
                69,
                R.drawable.sun_profile));
        accounts.add(new Account("blues",
                R.drawable.biru_story,
                R.drawable.biru_post,
                "biru bergelombang uhui",
                500,
                69,
                R.drawable.biru_profile));
        accounts.add(new Account("pemanjat.handal",
                R.drawable.pemanjat_story,
                R.drawable.pemanjat_post,
                "Tebingnya sulit sangat!",
                500,
                69,
                R.drawable.pemanjat_profile));
        accounts.add(new Account("penikmat_alam",
                R.drawable.view_story,
                R.drawable.view_post,
                "Nature's majesty on display. Witnessing the beauty of the views from a unique perspective. #mountaintravel #travelgram #windowview",
                500,
                69,
                R.drawable.view_profile));
        accounts.add(new Account("eat_foodie",
                R.drawable.food_story,
                R.drawable.food_post,
                "punten makan",
                500,
                69,
                R.drawable.food_profile));
        accounts.add(new Account("clean_water",
                R.drawable.water_story,
                R.drawable.water_post,
                "haus",
                500,
                69,
                R.drawable.water_profile));
        accounts.add(new Account("a_gamer",
                R.drawable.game_story,
                R.drawable.game_post,
                "Try all of this game!",
                500,
                69,
                R.drawable.game_profile));
        accounts.add(new Account("mobil_handal",
                R.drawable.car_story,
                R.drawable.car_post,
                "Mobil keren",
                500,
                69,
                R.drawable.car_profile));
        accounts.add(new Account("batu_ajaib",
                R.drawable.batu_story,
                R.drawable.batu_post,
                "batu ajaib terkemuka",
                500,
                69,
                R.drawable.batu_profile));
        return accounts;
    }
}