package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Node root;

    public static void main(String[] args) {
        // Create example tree
        Node root = new Node("What type of vehicle are you interested in?");

        Node car = new Node("What type of car are you looking for?");
        car.addChild("Sedan", new Node("What is your budget for a Sedan?")
                .addChild("Under $20,000", new Node(
                        "The best options for you would be a Honda Civic or a Toyota Corolla. Which one would you prefer?",
                        true, "Honda Civic or Toyota Corolla"))
                .addChild("$20,000 to $30,000", new Node(
                        "The best options for you would be a Subaru Impreza or a Mazda3. Which one would you prefer?",
                        true, "Subaru Impreza or Mazda3"))
                .addChild("Over $30,000", new Node(
                        "The best options for you would be a BMW 3 Series or a Audi A4. Which one would you prefer?",
                        true, "BMW 3 Series or Audi A4")));
        car.addChild("SUV", new Node("What is your budget for a SUV?")
                .addChild("Under $30,000", new Node(
                        "The best options for you would be a Honda CR-V or a Toyota RAV4. Which one would you prefer?",
                        true, "Honda CR-V or Toyota RAV4"))
                .addChild("$30,000 to $40,000", new Node(
                        "The best options for you would be a Subaru Outback or a Ford Escape. Which one would you prefer?",
                        true, "Subaru Outback or Ford Escape"))
                .addChild("Over $40,000", new Node(
                        "The best options for you would be a BMW X3 or a Mercedes-Benz GLC. Which one would you prefer?",
                        true, "BMW X3 or Mercedes-Benz GLC")));
        root.addChild("Car", car);

        Node motorcycle = new Node("What type of motorcycle are you looking for?");
        motorcycle.addChild("Cruiser", new Node("What is your budget for a Cruiser?")
                .addChild("Under $10,000", new Node(
                        "The best options for you would be a Honda Rebel or a Yamaha V Star. Which one would you prefer?",
                        true, "Honda Rebel or Yamaha V Star"))
                .addChild("$10,000 to $15,000", new Node(
                        "The best options for you would be a Harley-Davidson Sportster or a Indian Scout. Which one would you prefer?",
                        true, "Harley-Davidson Sportster or Indian Scout"))
                .addChild("Over $15,000", new Node(
                        "The best options for you would be a Harley-Davidson Road King or a Indian Chieftain. Which one would you prefer?",
                        true, "Harley-Davidson Road King or Indian Chieftain")));
        motorcycle.addChild("Sport", new Node("What is your budget for a Sport motorcycle?")
                .addChild("Under $8,000", new Node(
                        "The best options for you would be a Kawasaki Ninja or a Yamaha R3. Which one would you prefer?",
                        true, "Kawasaki Ninja or Yamaha R3"))
                .addChild("$8,000 to $12,000", new Node(
                        "The best options for you would be a Suzuki GSX-R or a Honda CBR. Which one would you prefer?",
                        true, "Suzuki GSX-R or Honda CBR")));
        root.addChild("Motorcycle", motorcycle);

        DecisionTree decisionTree = new DecisionTree(root);
        Scanner input = new Scanner(System.in);
        String answer = decisionTree.evaluate(input);
        System.out.println("Based on your responses, we suggest: " + answer);
        input.close();
    }
}

class Node {
    String question;
    Map<String, Node> children;
    boolean isLeaf;
    String answer;

    public Node(String question, boolean isLeaf, String answer) {
        this.question = question;
        this.children = new HashMap<String, Node>();
        this.isLeaf = isLeaf;
        this.answer = answer;
    }

    public Node(String question) {
        this.question = question;
        this.children = new HashMap<String, Node>();
        // this.isLeaf = isLeaf;
        // this.answer = answer;
    }

    public Node addChild(String answer, Node child) {
        this.children.put(answer, child);
        return this;
    }
}

class DecisionTree {
    Node root;

    public DecisionTree(Node root) {
        this.root = root;
    }

    public String evaluate(Scanner input) {
        Node current = root;
        while (!current.isLeaf) {
            System.out.println(current.question);
            System.out.println("Possible answers: " + current.children.keySet());
            String answer = input.nextLine();
            if (!current.children.containsKey(answer)) {
                System.out.println("Invalid answer, please try again.");
                continue;
            }
            current = current.children.get(answer);
        }
        return current.answer;
    }
}
