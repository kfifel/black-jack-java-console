package com.youcode.service;

import java.util.*;

public class BlackjackService {

    public List<List<Integer>> creer_cartes() {
        List<List<Integer>> cards = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++)
                cards.add(List.of(j, i));
        }
        return cards;
    }

    public Map<List<Integer>, List<List<Integer>>> extraire_ieme_carte(List<List<Integer>> cards, int index) {
        return new HashMap<>(Map.of(cards.remove(index), cards));
    }

    public void shuffleCards(List<List<Integer>> cards) {
        List<Integer> temporaryList;
        int i = 52;
        while (i-- > 0){
            temporaryList = cards.remove(getRandomInteger(52));
            cards.add(temporaryList);
        }
    }

    private int getRandomInteger(int rang) {
        Random random = new Random();
        return random.nextInt(rang);
    }
}
