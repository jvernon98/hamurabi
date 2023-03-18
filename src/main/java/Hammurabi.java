package hammurabi.src.main.java;
import java.util.Random;
import java.util.Scanner;

    public class Hammurabi {         // must save in a file named Hammurabi.java
        Random rand = new Random();  // this is an instance variable
        Scanner scanner = new Scanner(System.in);

        public static final int startPop = 100;
        public static final int startBushels = 2800;
        public static final int startAcres = 1000;
        public static final int startLValue = 19;
        public static final int maxYears = 10;

        private int year;
        private int population;
        private int acres;
        private int bushels;
        private int landValue;
        private int immigration;
        private int starvedOneYear;
        private int starvedOverall;
        private int ratsDestroyed;

        private enum GAME_STATE {
            startUp,
            initial
        }
        private GAME_STATE gameState;

        private void introduction(){
            System.out.println("""
                                O great Hammurabi!
                                You are in the first year of your ten year rule.
                                In the previous year 0 people starved to death.
                                In the previous year 5 people entered the kingdom.
                                The population is now 100.
                                We harvested 3000 bushels at 3 bushels per acre.
                                Rats destroyed 200 bushels, leaving 2800 bushels in storage.
                                The city owns 1000 acres of land.
                                Land is currently worth 19 bushels per acre.""");
        }

        public static void main(String[] args) { // required in every Java program
            new Hammurabi().playGame();
        }

        void playGame() {
        do {
            switch (gameState){
                case startUp:
                    introduction();
                    gameState = GAME_STATE.initial;
                    break;

                case initial:
                    year = 0;
                    population = startPop;
                    bushels = startBushels;
                    landValue = startLValue;
                    acres = startAcres;
                    immigration =
            }
        }
        }

}
     /*   Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        public static void main(String[] args) { // required in every Java program
            new Hammurabi().playGame();
        }
        void playGame() {
            while (!exit) {
                System.out.println("Welcome to the Hammurabi!");
                System.out.println("Please press one of the following options:\n" +
                        "1. Begin Playing\n" +
                        "2. Explain the Game\n" +
                        "0. Exit\n");
                int choice = scanner.nextInt();
                switch (choice) {

                    case 1:
                        // declare local variables here: grain, population, etc.
                        // statements go after the declarations
                        int people;
                        int grainBushels;
                        int landAcres;
                        int landValue;
                        int year = 1;

                        people = 100;
                        grainBushels = 2800;
                        landAcres = 1000;
                        landValue = 19;

                        while (year <= 10){
                            if (year == 1){
                                printStartSummary(people, grainBushels, landAcres, landValue);
                            }
                        }

                        void pringSta

                        String yearSummary;
                        yearSummary = """
                                O great Hammurabi!
                                You are in the first year of your ten year rule.
                                In the previous year 0 people starved to death.
                                In the previous year 5 people entered the kingdom.
                                The population is now 100.
                                We harvested 3000 bushels at 3 bushels per acre.
                                Rats destroyed 200 bushels, leaving 2800 bushels in storage.
                                The city owns 1000 acres of land.
                                Land is currently worth 19 bushels per acre.""";
                        System.out.println(yearSummary);



                    case 2:
                        System.out.println("Congratulations, you are the newest ruler of ancient Sumer, elected for a ten year term of office.\n" +
                                "Your duties are to dispense food, direct farming, and buy and sell land as needed to support your people.\n" +
                                "Watch out for rat infestations and the plague! Grain is the general currency, measured in bushels. The following will help you in your decisions:\n" +
                                "\n" +
                                "Each person needs at least 20 bushels of grain per year to survive\n" +
                                "Each person can farm at most 10 acres of land\n" +
                                "It takes 2 bushels of grain to farm an acre of land\n" +
                                "The market price for land fluctuates yearly\n" +
                                "Rule wisely and you will be showered with appreciation at the end of your term. Rule poorly and you will be kicked out of office!" +
                                "\n");
                                break;

                    case 0:
                        exit = true;
                        break;

                }
            }
        }
}