/*
 * Write a program that reads five integer values from the user, then analyzes them as if they were a hand of cards. When your program runs it might look like this (user input is in orange for clarity):

Enter five numeric cards, no face cards. Use 2 - 9.

Card 1: 8 

Card 2: 7

Card 3: 8

Card 4: 2

Card 5: 9

Pair!

(This is a pair, since there are two eights).

Here is a table that shows the different possible types of hands, with an example of each. You are only required to find the hands Pair and Straight. Feel free to work on the others if you are interested.

Required Hands

Description

Example

High Card

If the hand is not a straight, and there are no matches then it is simply called a "high card" hand.

2, 5, 3, 8, 7

Pair (required)

Two of the cards are identical

2, 5, 3, 5, 7

Straight (required)

A sequence of values with no matches and no gaps. They do not need to be in order

3, 4, 7, 6, 5



Optional Hands

Description

Example

Two Pair

Two different pairs

2, 5, 3, 5, 3

Three of a kind

Three matching cards

5, 5, 3, 5, 7

Full House

A pair, and a three of a kind

5, 7, 5, 7, 7

Four of a kind

Four matching cards

2, 5, 5, 5, 5


(A note on straights: a hand is a straight regardless of the order. So the values 3, 4, 5, 6, 7 represent a straight, but so do the values 7, 4, 5, 6, 3)

Your program should read in five values and then print out either "Straight", "Pair", or "High Card".
 */

 // Imports
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Main {
     public static void main(String[] args) {
         Scanner scnr = new Scanner(System.in);
         int[] cards = new int[5];
 
         System.out.println("Enter five numeric cards, no face cards. Use 2 - 9.");
 
         for (int i = 0; i < 5; i++) {
             System.out.println("Card: " + (i + 1));
             cards[i] = scnr.nextInt();
             if (cards[i] < 2 || cards[i] > 9) {
                 System.out.println("Invalid card. Try again.");
                 i--; // Decrement i to retry input
             }
         }
         scnr.close();
 
         Arrays.sort(cards);
 
         // Check for straight
         boolean isStraight = true;
         for (int i = 0; i < 4; i++) {
             if (cards[i] + 1 != cards[i + 1]) {
                 isStraight = false;
                 break;
             }
         }
 
         boolean isFourOfAKind = false, isThreeOfAKind = false;
         int pairs = 0;
 
         for (int i = 0; i < cards.length; i++) {
             int count = 0;
             for (int j = 0; j < cards.length; j++) {
                 if (cards[i] == cards[j]) {
                     count++;
                 }
             }
             if (count == 4) {
                 isFourOfAKind = true;
                 break; // Found a four of a kind, so we can break out of the loop
             } else if (count == 3) {
                 isThreeOfAKind = true;
             } else if (count == 2) {
                 // To ensure we count each pair only once, check if this is the first card of the pair
                 if (i == 0 || (i > 0 && cards[i] != cards[i-1])) {
                     pairs++;
                 }
             }
         }
 
         boolean isFullHouse = isThreeOfAKind && pairs >= 1;
         boolean isTwoDifferentPairs = pairs == 2;
 
         // Print the result
         if (isStraight) {
             System.out.println("Straight");
         } else if (isFourOfAKind) {
             System.out.println("Four of a kind");
         } else if (isFullHouse) {
             System.out.println("Full house");
         } else if (isThreeOfAKind) {
             System.out.println("Three of a kind");
         } else if (isTwoDifferentPairs) {
             System.out.println("Two different pairs");
         } else if (pairs == 1) {
             System.out.println("Pair");
         } else {
             System.out.println("High card");
         }
     }
 }
 
 