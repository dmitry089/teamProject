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

        store.addPlayTime("game", 1);
        String expected = "game";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);

    }
}
