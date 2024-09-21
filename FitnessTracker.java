import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract Exercise Class (Base Class)
abstract class Exercise {
    private String exerciseName;
    private int duration; // in minutes

    public Exercise(String exerciseName, int duration) {
        this.exerciseName = exerciseName;
        this.duration = duration;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getDuration() {
        return duration;
    }

    public abstract double calculateCaloriesBurned();
}

// CardioExercise Class - Inherits from Exercise
class CardioExercise extends Exercise {
    private double distance; // in kilometers
    private double speed; // in km/h

    public CardioExercise(String exerciseName, int duration, double distance, double speed) {
        super(exerciseName, duration);
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public double calculateCaloriesBurned() {
        // Simple formula: calories burned = duration * speed * a constant factor
        return getDuration() * speed * 0.1;
    }
}

// StrengthExercise Class - Inherits from Exercise
class StrengthExercise extends Exercise {
    private int sets;
    private int reps;
    private double weight; // in kg

    public StrengthExercise(String exerciseName, int duration, int sets, int reps, double weight) {
        super(exerciseName, duration);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    @Override
    public double calculateCaloriesBurned() {
        // Simple formula: calories burned = sets * reps * weight * a constant factor
        return sets * reps * weight * 0.05;
    }
}

// Workout Class - Encapsulation of Workout details
class Workout {
    private String workoutName;
    private String date;
    private List<Exercise> exercises;

    public Workout(String workoutName, String date) {
        this.workoutName = workoutName;
        this.date = date;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public double totalCaloriesBurned() {
        double totalCalories = 0;
        for (Exercise exercise : exercises) {
            totalCalories += exercise.calculateCaloriesBurned();
        }
        return totalCalories;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getDate() {
        return date;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}

// User Class
class User {
    private String name;
    private int age;
    private double weight; // in kg
    private double height; // in cm
    private String fitnessGoal;

    public User(String name, int age, double weight, double height, String fitnessGoal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public double calculateBMI() {
        // BMI = weight (kg) / (height (m) ^ 2)
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }
}

// Main Class - Example Usage
public class FitnessTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter your height (cm): ");
        double height = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        System.out.print("Enter your fitness goal: ");
        String fitnessGoal = scanner.nextLine();

        User user = new User(name, age, weight, height, fitnessGoal);

        // Workout Input
        System.out.print("Enter workout name: ");
        String workoutName = scanner.nextLine();

        System.out.print("Enter workout date (YYYY-MM-DD): ");
        String workoutDate = scanner.nextLine();

        Workout workout = new Workout(workoutName, workoutDate);

        // Add Exercises
        while (true) {
            System.out.println("Choose exercise type: ");
            System.out.println("1. Cardio Exercise");
            System.out.println("2. Strength Exercise");
            System.out.println("3. Finish adding exercises");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter exercise name: ");
                scanner.nextLine(); // Consume newline
                String exerciseName = scanner.nextLine();

                System.out.print("Enter duration (minutes): ");
                int duration = scanner.nextInt();

                System.out.print("Enter distance (km): ");
                double distance = scanner.nextDouble();

                System.out.print("Enter speed (km/h): ");
                double speed = scanner.nextDouble();

                Exercise exercise = new CardioExercise(exerciseName, duration, distance, speed);
                workout.addExercise(exercise);

            } else if (choice == 2) {
                System.out.print("Enter exercise name: ");
                scanner.nextLine(); // Consume newline
                String exerciseName = scanner.nextLine();

                System.out.print("Enter duration (minutes): ");
                int duration = scanner.nextInt();

                System.out.print("Enter sets: ");
                int sets = scanner.nextInt();

                System.out.print("Enter reps per set: ");
                int reps = scanner.nextInt();

                System.out.print("Enter weight (kg): ");
                double weightPerRep = scanner.nextDouble();

                Exercise exercise = new StrengthExercise(exerciseName, duration, sets, reps, weightPerRep);
                workout.addExercise(exercise);

            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please select again.");
            }
        }

        // Output Results
        System.out.println("Total Calories Burned: " + workout.totalCaloriesBurned());
        System.out.println("User's BMI: " + user.calculateBMI());
    }
}