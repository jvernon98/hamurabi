package hammurabi.src.main.java;
import java.util.Random;
import java.util.Scanner;

    public class Hammurabi {         // must save in a file named Hammurabi.java
        Random rand = new Random();  // this is an instance variable
        Scanner scanner = new Scanner(System.in);

        public static final int startPop = 100;
        public final int startHarvest = 3000;
        public static final int startBushels = 2800;
        public static final int startAcres = 1000;
        public static final int startLValue = 19;
        public static final int maxYears = 10;
        public static final int maxStarved = (startPop * 100) / 45;

        private int year;
        private int population;
        private int acres;
        private int bushels;
        private int landValue;
        private int immigration;
        private int starvedOneYear;
        private int starvedOverall;
        private int ratsDestroyed;
        private boolean chanceOfPlague;
        private int percentChanceOfPlague;
        private int peopleFed;
        private int peopleStarved;
        private int bushelsToFeedPeople;
        private int harvest;
        private int percentStarved;

        private enum GAME_STATE {
            startUp,
            initial,
            yearNum,
            buyAcres,
            sellAcres,
            feedPeople,
            plantSeed,
            calculateHarvest,
            calculateImmigration,
            results,
            gameFinished,
            gameOver
        }

        private GAME_STATE gameState;
        public Hammurabi(){
            gameState = GAME_STATE.startUp;
        }

        private void introduction() {
            System.out.println(" O great Hammurabi!\n"+
                    "You are in the first year of your ten year rule.\n"+
                    "In the previous year 0 people starved to death. \n"+
                    "In the previous year 5 people entered the kingdom. \n"+
                    "The population is now 100.\n"+
                    "We harvested 3000 bushels at 3 bushels per acre.\n" +
                    "Rats destroyed 200 bushels, leaving 2800 bushels in storage.\n"+
                    "The city owns 1000 acres of land.\n" +
                    "Land is currently worth 19 bushels per acre.");
        }

        public static void main(String[] args) { // required in every Java program
            new Hammurabi().playGame();
        }

        void playGame() {
            do {
                switch (gameState) {
                    case startUp: //enhanced switch statement ?
                        introduction();
                        gameState = GAME_STATE.initial;
                        break;

                    case initial:
                        year = 0;
                        population = startPop;
                        bushels = startBushels;
                        landValue = startLValue;
                        acres = startAcres;
                        immigration = startPop;
                        ratsDestroyed = startHarvest - startBushels;
                        starvedOneYear = 0;
                        starvedOverall = 0;
                        chanceOfPlague = false;
                        peopleFed = 0;
                        peopleStarved = 0;
                        bushelsToFeedPeople = 0;

                        gameState = GAME_STATE.yearNum;
                        break;

                    case yearNum:
                        System.out.println();
                        year += 1;
                        if (year > maxYears) {
                            gameState = GAME_STATE.results;
                            break;
                        }
                        System.out.println("Hamurabi, I must deliver the report:\n " +
                                "In year " + year + "," + starvedOneYear + "people starved," + immigration + " people came to the city. ");
                        population += immigration;
                        if (chanceOfPlague) {
                            population /= 2;
                            System.out.println("A horrible plague has struck! Half our population has perished!");
                        }
                        System.out.println("The population is now " + population + ". The city now owns" + acres + ". \n" +
                                "You harvested" + landValue + " bushels per acre. The rats ate" + ratsDestroyed + " bushels. You now have" + bushels + " bushels in store");

                        landValue = (int) (Math.random() * 10) + 17;
                        System.out.println("Land value is currently at " + landValue + " bushels per acre.");

                        gameState = GAME_STATE.buyAcres;
                        break;

                    case buyAcres:
                        int acresToBuy = displayTextGetNum("How many acres do you wish to buy? ");
                        if (acresToBuy < 0) {
                            gameState = GAME_STATE.gameFinished;
                        }
                        if (acresToBuy > 0) {
                            if ((landValue * acresToBuy) > bushels) {
                                notEnoughBushelsMessage();
                            } else {
                                acres += acresToBuy;
                                bushels -= (landValue * acresToBuy);
                                peopleFed = 0;
                                gameState = GAME_STATE.sellAcres;
                            }
                        } else {
                            gameState = GAME_STATE.sellAcres;
                        }
                        break;


                    case sellAcres:
                        int acresToSell = displayTextGetNum("How many acres do you wish to sell? ");
                        if (acresToSell < 0) {
                            gameState = GAME_STATE.gameFinished;
                        }
                        if (acresToSell < acres) {
                            acres -= acresToSell;
                            bushels += (landValue * acresToSell);
                            gameState = GAME_STATE.feedPeople;
                        } else {
                            notEnoughLandMessage();
                        }
                        break;


                    case feedPeople:
                        bushelsToFeedPeople = displayTextGetNum("How many bushels do you wish to feed the people? ");
                        if (bushelsToFeedPeople < 0) {
                            gameState = GAME_STATE.gameFinished;
                        }
                        if (bushelsToFeedPeople <= bushels) {
                            bushels -= bushelsToFeedPeople;
                            peopleFed = 1;
                            gameState = GAME_STATE.plantSeed;
                        } else {
                            notEnoughBushelsMessage();
                        }
                        break;

                    case plantSeed:
                        int acresToPlant = displayTextGetNum("How many acres do you wish to plant with seed? ");
                        if (acresToPlant < 0) {
                            gameState = GAME_STATE.gameFinished;
                        }
                        if (acresToPlant <= acres) {
                            if (acresToPlant / 2 <= bushels) {
                                if (acresToPlant < 10 * population) {
                                    bushels -= acresToPlant / 2;
                                    peopleFed = (int) (Math.random() * 5) + 1;
                                    landValue = peopleFed;
                                    harvest = acresToPlant * landValue;
                                    ratsDestroyed = 0;
                                    gameState = GAME_STATE.calculateHarvest;
                                } else {
                                    notEnoughPeopleMessage(); }
                                } else{
                                    notEnoughBushelsMessage();
                            }
                                } else{
                                notEnoughLandMessage();
                        }
                        break;


                    case calculateHarvest:
                        if ((peopleFed / 2) == peopleFed / 2) {
                            ratsDestroyed = (bushels / peopleFed);
                        }
                        bushels = bushels - ratsDestroyed;
                        bushels += harvest;
                        gameState = GAME_STATE.calculateImmigration;
                        break;


                    case calculateImmigration:
                        immigration = peopleFed * (20 * acres + bushels) / population / 100 + 1; //given method check read me
                        peopleFed = (bushelsToFeedPeople / 20); //each person needs 20 to be happy
//                    chanceOfPlague = percentChanceOfPlague{
//                        if (rand.nextInt(100) < 15){return true;}
                        if (population < peopleFed) {
                            gameState = GAME_STATE.yearNum;
                        }
                        int starved = population - peopleFed;
                        if (starved < 0) {
                            starvedOneYear = 0;
                            gameState = GAME_STATE.yearNum;
                        } else {
                            starvedOneYear = starved;
                            starvedOverall += starvedOneYear;
                            if (starved > maxStarved) {
                                starvedTooManyPeopleMessage(starved);
                                gameState = GAME_STATE.gameFinished;
                            } else {
                                percentStarved = ((year - 1) * percentStarved + starved * 100 / population) / year;
                                population = peopleFed;
                                gameState = GAME_STATE.yearNum;
                            }
                            break;
                        }
                    case results:
                        int acresPerPerson = acres / population;

                        System.out.println("In your ten year office term " + percentStarved + " percent of people starved every year on average.\n" +
                                "A total of " + starvedOverall + " people died of starvation! \n" +
                                "You started with 10 acres per person and ended with " + acresPerPerson + " acres per person.");

                        gameState = GAME_STATE.gameFinished;
                    case gameFinished:
                        System.out.println("Good bye and thank you for playing!");
                        gameState = GAME_STATE.gameOver;

                }
            } while (gameState != GAME_STATE.gameOver) ;

        }
        private void starvedTooManyPeopleMessage ( int starved){
            System.out.println("You starved " + starved + " people in one year!!!\n" +
                    "Due to your terrible management you have been thrown from office and chased out of town!");
        }

        private void notEnoughPeopleMessage () {
            System.out.println("But, you on have" + population + " people to tend to the fields! Be reasonable!");
        }
        private void notEnoughBushelsMessage () {
            System.out.println("Hamurabi, please! You only have " + bushels + " bushels of grain. Please think again.");
        }
        private void notEnoughLandMessage () {
            System.out.println("Hamurabi, please! You only have " + acres + " acres. Please think again.");
        }
        private int displayTextGetNum (String text){
            return Integer.parseInt(displayTextGetInput(text));
        }
        private String displayTextGetInput (String text){
            System.out.print(text);
            return scanner.next();
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
                                "Rule wisely, and you will be showered with appreciation at the end of your term. Rule poorly, and you will be kicked out of office!" +
                                "\n");
                                break;

                    case 0:
                        exit = true;
                        break;

                }
            }

      */