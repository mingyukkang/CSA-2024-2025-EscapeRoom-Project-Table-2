public class EscapeRoom {
    public static void main(String[] args) {
        // welcome message
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
        System.out.println("pick up all the prizes.\n");

        GameGUI game = new GameGUI();
        game.createBoard();

        // size of move
        int m = 60;

        // individual player moves
        int px = 0;
        int py = 0;
        int score = 0;

        String[] validCommands = { 
            "right", "left", "up", "down", "r", "l", "u", "d",
            "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
            "pickup", "p", "quit", "q", "replay", "help", "?", "teleport", "tp"
        };

        // set up game
        boolean play = true;
        while (play) {
            System.out.print("Enter a command (type 'help' for commands): ");
            String command = UserInput.getValidInput(validCommands);

            switch (command) {
                case "right":
                case "r":
                    score += game.movePlayer(m, 0);
                    break;
                case "left":
                case "l":
                    score += game.movePlayer(-m, 0);
                    break;
                case "up":
                case "u":
                    score += game.movePlayer(0, -m);
                    break;
                case "down":
                case "d":
                    score += game.movePlayer(0, m);
                    break;
                case "jump":
                case "jr":
                    if (!game.isTrap(2*m, 0)) {
                        score += game.movePlayer(2*m, 0);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpleft":
                case "jl":
                    if (!game.isTrap(-2*m, 0)) {
                        score += game.movePlayer(-2*m, 0);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpup":
                case "ju":
                    if (!game.isTrap(0, -2*m)) {
                        score += game.movePlayer(0, -2*m);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "jumpdown":
                case "jd":
                    if (!game.isTrap(0, 2*m)) {
                        score += game.movePlayer(0, 2*m);
                    } else {
                        System.out.println("Cannot jump over a trap!");
                    }
                    break;
                case "pickup":
                case "p":
                    score += game.pickupPrize();
                    break;
                case "quit":
                case "q":
                    play = false;
                    break;
                case "replay":
                    score += game.replay();
                    break;
                case "help":
                case "?":
                    displayHelp();
                    break;
                case "teleport":
                case "tp":
                    int newX = game.getRandomX();
                    int newY = game.getRandomY();
                    score += game.teleportPlayer(newX, newY);
                    break;
            }

            if (game.isTrap(m, 0) || game.isTrap(-m, 0) || game.isTrap(0, m) || game.isTrap(0, -m)) {
                System.out.print("There's a trap nearby! Do you want to spring it? (yes/no): ");
                String springTrap = UserInput.getValidInput(new String[]{"yes", "no"});
                if (springTrap.equals("yes")) {
                    score += game.springTrap(m, 0);
                }
            }

            System.out.println("Current score: " + score);
        }

        score += game.endGame();
        System.out.println("Final score: " + score);
        System.out.println("Total steps: " + game.getSteps());
    }

    private static void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("right/r: Move right");
        System.out.println("left/l: Move left");
        System.out.println("up/u: Move up");
        System.out.println("down/d: Move down");
        System.out.println("jump/jr: Jump right");
        System.out.println("jumpleft/jl: Jump left");
        System.out.println("jumpup/ju: Jump up");
        System.out.println("jumpdown/jd: Jump down");
        System.out.println("pickup/p: Pick up a prize");
        System.out.println("quit/q: End the game");
        System.out.println("replay: Restart the game");
        System.out.println("teleport/tp: Teleport to a random location");
        System.out.println("help/?: Display this help message");
    }
}