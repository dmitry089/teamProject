package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    GameStore store = new GameStore();
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game1 = store.publishGame("Hogwarts Legacy", "Аркады");
    Game game2 = store.publishGame("SKYRIM", "РПГ");
    Game game3 = store.publishGame("GTA", "Шутер");
    Game game4 = store.publishGame("Assassin's Creed", "Приключения");
    Game game5 = store.publishGame("Battlefield", "Шутер");
    Game game6 = store.publishGame("Call of Duty", "Шутер");
    Game game7 = store.publishGame("Portal", "Приключения");
    Game game8 = store.publishGame("Half-Life", "Шутер");
    Game game9 = store.publishGame("The Witcher", "РПГ");

    @Test  // Добавление одной игры к игроку и подсчет времени
    public void shouldSumGenreIfOneGame() {
        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 6);

        int expected = 6;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test // Добавление двух игр к игроку и подсчет общего времени
    public void shouldSumGenreTwoGames() {
        Player player = new Player("Petya");

        player.installGame(game);
        player.installGame(game1);
        player.play(game, 5);
        player.play(game1, 1);

        int expected = 6;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test  // Проверка если нет игр у игрока выдает ошибку RuntimeException
    public void shouldSumGenreNullGames() {
        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game1, 5);
        });
    }

    @Test // Подсчет времени игры с жанром "Шутер"
    public void shouldSumGenrePlayedTwice() {
        Player player = new Player("Petya");

        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game7);
        player.installGame(game8);
        player.installGame(game9);

        player.play(game, 2);
        player.play(game1, 5);
        player.play(game2, 9);
        player.play(game3, 1);
        player.play(game4, 3);
        player.play(game5, 4);
        player.play(game6, 2);
        player.play(game7, 3);
        player.play(game8, 7);
        player.play(game9, 1);

        int expected = 14;
        int actual = player.sumGenre("Шутер");
        assertEquals(expected, actual);
    }

    @Test  // Вывод игры, в которую играют большее количество времени
    public void shouldMostPlayerByGenre() {
        Player player = new Player("Petya");

        player.installGame(game2);
        player.installGame(game5);
        player.installGame(game6);
        player.installGame(game8);

        player.play(game2, 2);
        player.play(game5, 5);
        player.play(game6, 3);
        player.play(game8, 6);

        Game expected = game8;
        Game actual = player.mostPlayerByGenre("Шутер");
        assertEquals(expected, actual);
    }
}