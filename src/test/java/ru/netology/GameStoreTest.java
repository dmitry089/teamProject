package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {           //добавление одной игры

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddTwoGames() {       //добавление двух игр

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Far cry", "Шутер");

        assertTrue(store.containsGame(game));
        assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldNotContainsGame() {       //проверка на добавление в метод puhlishGame игру store
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = new Game("Far cry", "Шутер", store);
        Game game3 = store.publishGame("Far", "Шут");

        assertFalse(store.containsGame(game2));
    }

    @Test
    public void shouldAddOneHourPlayTime() {       //проверка добавления времени в игре на 1 час

        store.addPlayTime("Counter strike", 1);
        String expected = "Counter strike";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldNotShowAnyPlayer() { //  проверка метода getMostPlayer, если игроки отсутствуют

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldNotAddHoursIfZero() { // проверка добавления времени игры ноль часов

        store.addPlayTime("Far cry", 0);

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldNotAddHoursIfMinus() { // проверка добавления времени с отрицательным значением

        store.addPlayTime("Far cry", -1);
        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldCheckThatTheGameInTheStore() {    //проверка
        Game game1 = store.publishGame("Sims", "симулятор");
        Game game2 = store.publishGame("Battlefield", "Шутер");

        Assertions.assertThrows(RuntimeException.class, () -> {
            store.publishGame("Sims", "симулятор");
        });
    }

    @Test
    public void mustSumUpTheTotalAmountOfTimeOfAllPlayers() {

        store.addPlayTime("Dmitry", 2);
        store.addPlayTime("Lidiya", 5);
        store.addPlayTime("Lena", 4);

        int expected = 11;
        int actual = store.getSumPlayedTime();
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void shouldRecordTheTimeThatPlayerSpentInTheGame() {
        store.addPlayTime("Lena", 4);
        store.addPlayTime("Lena", 5);

        String actual = store.getMostPlayer();
        Assertions.assertEquals("Lena", actual);
    }
}