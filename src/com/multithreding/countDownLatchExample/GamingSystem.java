package executor.countDownLatchExample;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/*
* Imagine you are developing a race simulation where the race starts only when all runners are ready.
* How would you use CountDownLatch to handle this?
* */
public class GamingSystem {

    public static void main(String[] args) throws InterruptedException {
        Games gameName = Games.CRICKET;

        int players = Game.numberOFPlayers(gameName);
        CountDownLatch latch = new CountDownLatch(players);
        Set<Player> playersSet = new HashSet<>();
        System.out.println("Race started. Wait for all "+players+" players to be ready...");

        for (int i = 1; i <= players; i++) {
            Long playerId = Long.valueOf(i);
            String name = "Player-" + i;
            new Thread(() -> {
                Player player = new Player(playerId, name, "Champion-200" + playerId);
                playersSet.add(player);
                latch.countDown();
            }).start();
        }

        Game game = new Game(1L, gameName, playersSet, LocalDate.now());
        latch.await();

        game.startGame();
        System.out.println("Winner of this Match: " + game.winner());
    }
}

enum Games {
    LUDO,
    CRICKET,
    FOOTBALL,
    CHESS,
}

record Game(Long gameId, Games name, Set<Player> players, LocalDate gameDate) {
    public static int numberOFPlayers(Games game) {
        return switch (game) {
            case CRICKET, FOOTBALL -> 11;
            case CHESS -> 2;
            case LUDO -> 4;
            default -> throw new IllegalArgumentException("Unsupported game: " + game);
        };
    }
    public void startGame() throws InterruptedException {
        players.forEach(System.out::println);
        System.out.println("Match Started");
        Thread.sleep(5000);
        System.out.println("Match stopped.");
    }

    public Player winner() {
        int luckId = new Random().nextInt(players.size())+1;
        return players.stream()
                .filter(player -> player.playerId().intValue() == luckId)
                .findFirst().orElseThrow(() -> new RuntimeException("Match Draw."));
    }
}

record Player(Long playerId, String name, String type) {}
