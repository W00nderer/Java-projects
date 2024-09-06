import java.util.*;

public class capitalsQuiz {
    enum Level {
        EASY, MEDIUM, HARD
    }

    private static final String CORRECT_MESSAGE = "Correct!";
    private static final String WRONG_MESSAGE = "Wrong!";

    public static void main(String[] args) {
        Map<Level, Map<String, String>> capitals = new HashMap<>();
        
        Map<String, String> capitalsEasy = new HashMap<>();
        capitalsEasy.put("England", "london");
        capitalsEasy.put("France", "paris");
        capitalsEasy.put("Japan", "tokyo");
        capitalsEasy.put("Mexico", "mexico city");
        capitalsEasy.put("The Netherlands", "amsterdam");
        capitalsEasy.put("Monaco", "monaco");
        capitals.put(Level.EASY, capitalsEasy);

        Map<String, String> capitalsMedium = new HashMap<>();
        capitalsMedium.put("Russia", "moscow");
        capitalsMedium.put("Australia", "canberra");
        capitalsMedium.put("Czech Republic", "prague");
        capitalsMedium.put("Hungary", "budapest");
        capitalsMedium.put("Indonesia", "jakarta");
        capitalsMedium.put("Canada", "ottawa");
        capitals.put(Level.MEDIUM, capitalsMedium);

        Map<String, String> capitalsHard = new HashMap<>();
        capitalsHard.put("Venezuela", "caracas");
        capitalsHard.put("Liechtenstein", "vaduz");
        capitalsHard.put("Vietnam", "hanoi");
        capitalsHard.put("Honduras", "tegucigalpa");
        capitalsHard.put("Uzbekistan", "tashkent");
        capitals.put(Level.HARD, capitalsHard);

        boolean playAgain;
        try (Scanner in = new Scanner(System.in)) {
            do {
                Level selectedLevel = chooseDifficulty(in);
                Map<String, String> selectedCapitals = capitals.get(selectedLevel);
                playQuiz(selectedCapitals, in);

                System.out.println("Do you want to play again? (yes/no)");
                String response = in.nextLine().trim().toLowerCase();
                playAgain = response.equals("yes");
                if (!playAgain) {
                    System.out.println("Exiting the quiz. Thanks for playing!");
                }
            } while (playAgain);
        }
    }

    private static Level chooseDifficulty(Scanner in) {
        while (true) {
            System.out.println("Choose game difficulty (easy/medium/hard):");
            String diff = in.nextLine().trim().toUpperCase();
            try {
                return Level.valueOf(diff);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid difficulty! Please enter 'easy', 'medium', or 'hard'.");
            }
        }
    }

    private static void playQuiz(Map<String, String> capitals, Scanner in) {
        int score = 0;
        int questionNum = 0;
        List<String> countries = new ArrayList<>(capitals.keySet());
        Collections.shuffle(countries);
        System.out.println("Pop Quiz!");

        for (String country : countries) {
            questionNum++;
            System.out.println("What city is the capital of " + country + "?");
            String userGuess = in.nextLine().trim().toLowerCase();
            if (userGuess.equals(capitals.get(country))) {
                System.out.println(CORRECT_MESSAGE);
                score++;
            } else {
                System.out.println(WRONG_MESSAGE);
            }
            if(questionNum==3){
                break;
            }
        }
        System.out.println("Your final score is: " + score);
    }
}
