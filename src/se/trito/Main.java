package se.trito;

import java.util.*;


import static java.lang.Math.max;

public class Main {
    private static Integer vEttor = null,
            vTvaor = null,
            vTreor = null,
            vFyror = null,
            vFemmor = null,
            vSexor = null,
            vPar = null,
            vTvaPar = null,
            vTriss = null,
            vFyrtal = null,
            vLitenStege = null,
            vStorStege = null,
            vKak = null,
            vChans = null,
            vYatzy = null;

    public static void main(String[] args) {
        int rounds = 0; //There are 15 rounds in total. Every round consists of three dice rolls.

        List<Integer> diceList = new ArrayList<>(); // This list contains the five dices.

        while (rounds < 15) {
            //rita ut yatzybrickan
            drawScoreBoard();

            int rolls = 3;
            diceList.clear();
            diceList = rollDices(diceList);    // Slå tärningar
            rolls -= 1;
            while (rolls > 0) {

                /*Här skrivs de fem tärningarna ut på skärmen */
                drawDices(diceList);
                System.out.println("Antal gånger kvar att slå: " + (rolls));
                System.out.println("1-5    Ange vilka tärningar du vill spara.");
                System.out.println("0      Slå om alla tärningar.");
                System.out.println("p      Spara alla tärningar.");

                Scanner sc2 = new Scanner(System.in);
                String input2 = sc2.nextLine();

                if (input2.equals("0")) {
                    diceList.clear();
                    diceList = rollDices(diceList);
                    rolls -= 1;
                } else if (input2.equals("p")) {
                    rolls = 0;
                } else {
                    List<Integer> omslagsArray = new ArrayList<>();
                    for (char ch : input2.toCharArray()) {
                        omslagsArray.add(diceList.get(Character.getNumericValue(ch) - 1));
                    }
                    diceList = rollDices(omslagsArray);
                    rolls -= 1;
                }


            }
            drawDices(diceList);
            rounds += 1;
            storeScore(diceList);

        }
        drawScoreBoard();
        System.out.print("GG! Din totala poäng blev " + countSum());
    }

    private static int countPartSum() {
        int sum = 0;
        if (vEttor != null) {
            sum = sum + vEttor;
        }
        if (vTvaor != null) {
            sum = sum + vTvaor;
        }
        if (vTreor != null) {
            sum = sum + vTreor;
        }
        if (vFyror != null) {
            sum = sum + vFyror;
        }
        if (vFemmor != null) {
            sum = sum + vFemmor;
        }
        if (vSexor != null) {
            sum = sum + vSexor;
        }

        return sum;
    }

    private static int checkBonus() {
        if (countPartSum() < 63) {
            return 0;
        } else {
            return 50;
        }
    }

    private static String checkNull(Integer value) {
        if (value == null) {
            return " ";
        } else {
            return value.toString();
        }

    }

    private static int countSum() {
        int sum = countPartSum() + checkBonus();
      /*  return sum + Stream.of(vPar, vTvaPar, vTriss, vFyrtal, vLitenStege, vStorStege, vKak, vChans, vYatzy)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();*/

        sum += (vPar != null) ? vPar : 0;
        sum += (vTvaPar != null) ? vTvaPar : 0;
        sum += (vTriss != null) ? vTriss : 0;
        sum += (vFyrtal != null) ? vFyrtal : 0;
        sum += (vLitenStege != null) ? vLitenStege : 0;
        sum += (vStorStege != null) ? vStorStege : 0;
        sum += (vKak != null) ? vKak : 0;
        sum += (vChans != null) ? vChans : 0;
        sum += (vYatzy != null) ? vYatzy : 0;
        return sum;
    }

    private static List<Integer> rollDices(List<Integer> diceArray) {
        String input = "a";
        Scanner sc1 = new Scanner(System.in);

        while (!input.equals("s")) {
            System.out.println("Skriv \"s\" och tryck enter för att slå tärningarna.");
            input = sc1.nextLine();
        }
        //slumpa fem tal
        int limit = 5 - diceArray.size();
        int i = 0;
        while (i < limit) {

            Random ran = new Random();
            int x = ran.nextInt(6) + 1;
            diceArray.add(x);
            i = i + 1;
        }
        return diceArray;
    }

    private static void drawScoreBoard() {
        System.out.println("----------------------------------");
        System.out.println("1.  Ettor                     " + checkNull(vEttor));
        System.out.println("2.  Tvåor                     " + checkNull(vTvaor));
        System.out.println("3.  Treor                     " + checkNull(vTreor));
        System.out.println("4.  Fyror                     " + checkNull(vFyror));
        System.out.println("5.  Femmor                    " + checkNull(vFemmor));
        System.out.println("6.  Sexor                     " + checkNull(vSexor));
        System.out.println("----------------------------------");
        System.out.println("DELSUMMA                      " + countPartSum());
        System.out.println("BONUS                         " + checkBonus());
        System.out.println("----------------------------------");
        System.out.println("7.  1 par                     " + checkNull(vPar));
        System.out.println("8.  2 par                     " + checkNull(vTvaPar));
        System.out.println("9.  Triss                     " + checkNull(vTriss));
        System.out.println("10. Fyrtal                    " + checkNull(vFyrtal));
        System.out.println("11. Liten stege               " + checkNull(vLitenStege));
        System.out.println("12. Stor stege                " + checkNull(vStorStege));
        System.out.println("13. Kåk                       " + checkNull(vKak));
        System.out.println("14. Chans                     " + checkNull(vChans));
        System.out.println("15. Yatzy                     " + checkNull(vYatzy));
        System.out.println("----------------------------------");
        System.out.println("SUMMA                         " + countSum());
    }

    private static void drawDices(List<Integer> diceArray) {
        System.out.print("Dina tärningar: ");
        for (int v = 0; v < diceArray.size(); v++) {
            System.out.print((diceArray.get(v)));
            if (v < diceArray.size() - 1) {
                System.out.print(" - ");
            } else {
                System.out.println(" ");
            }
        }
    }

    private static int countOccurrences(List<Integer> diceList, int value) {
        int occurrences = 0;
        for (Integer dice : diceList) {
            if (dice == value) occurrences++;
        }
        return occurrences;
    }

    private static int sum(List<Integer> diceList) {
        int sum = 0;
        for (Integer dice : diceList) {
            sum += dice;
        }
        return sum;
    }

    private static void storeScore(List<Integer> diceArray) {
        //store scores
        System.out.println(("Var vill du spara poängen? "));

        Scanner sc = new Scanner(System.in);
        int input;

        boolean shouldBreak = false;
        while (!shouldBreak) {
            input = sc.nextInt();

            if (input == 1) {
                shouldBreak = calcEttor(diceArray);
            } else if (input == 2) {
                shouldBreak = calcTvaor(diceArray);
            } else if (input == 3) {
                shouldBreak = calcTreor(diceArray);
            } else if (input == 4) {
                shouldBreak = calcFyror(diceArray);
            } else if (input == 5) {
                shouldBreak = calcFemmor(diceArray);
            } else if (input == 6) {
                shouldBreak = calcSexor(diceArray);
            } else if (input == 7) {
                shouldBreak = calcPar(diceArray);
            } else if (input == 8) {
                shouldBreak = calcTvaPar(diceArray);
            } else if (input == 9) {
                shouldBreak = calcTriss(diceArray);
            } else if (input == 10) {
                shouldBreak = calcFyrtal(diceArray);
            } else if (input == 11) {
                shouldBreak = calcLitenStege(diceArray);
            } else if (input == 12) {
                shouldBreak = calcStorStege(diceArray);
            } else if (input == 13) {
                shouldBreak = calcKak(diceArray);
            } else if (input == 14) {
                shouldBreak = calcChans(diceArray);
            } else if (input == 15) {
                shouldBreak = calcYatzy(diceArray);
            }
        }
    }

   /* private static boolean genericCalc(Supplier<Integer> x ) {

    }*/

    private static boolean calcEttor(List<Integer> diceArray) {
        if (vEttor == null) {
            vEttor = countOccurrences(diceArray, 1);
            return true;
        } else {
            System.out.println("Du har redan haft ettor");
            return false;
        }
    }

    private static boolean calcTvaor(List<Integer> diceArray) {
        if (vTvaor == null) {
            vTvaor = countOccurrences(diceArray, 2) * 2;
            return true;
        } else {
            System.out.println("Du har redan haft tvåor");
            return false;
        }
    }

    private static boolean calcTreor(List<Integer> diceArray) {
       if (vTreor == null) {
            vTreor = countOccurrences(diceArray, 3) * 3;
            return true;
        } else {
            System.out.println("Du har redan haft treor");
            return false;
        }
    }

    private static boolean calcFyror(List<Integer> diceArray) {
        if (vFyror == null) {
            vFyror = countOccurrences(diceArray, 4) * 4;
            return true;
        } else {
            System.out.println("Du har redan haft fyror");
            return false;
        }
    }

    private static boolean calcFemmor(List<Integer> diceArray) {
        if (vFemmor == null) {
            vFemmor = countOccurrences(diceArray, 5) * 5;
            return true;
        } else {
            System.out.println("Du har redan haft femmor");
            return false;
        }
    }

    private static boolean calcSexor(List<Integer> diceArray) {
        if (vSexor == null) {
            vSexor = countOccurrences(diceArray, 6) * 6;
            return true;
        } else {
            System.out.println("Du har redan haft sexor");
            return false;
        }
    }

    private static boolean calcPar(List<Integer> diceArray) {
        if (vPar == null) {
            vPar = 0;

            for (int dice : diceArray) {
                int count = countOccurrences(diceArray, dice);

                if (count >= 2) {
                    vPar = max(vPar, dice * 2);
                }
            }
            return true;
        } else {
            System.out.println("Du har redan haft par.");
            return false;
        }
    }

    private static boolean calcTvaPar(List<Integer> diceArray) {
        if (vTvaPar == null) {
            vTvaPar = 0;
            boolean seenOnePair = false;
            int tempScore = 0;

            for (Integer diceValue : new HashSet<>(diceArray)) { // Iterate over distinct dice values
                int count = countOccurrences(diceArray, diceValue);

                if (count >= 2 && !seenOnePair) {
                    tempScore = diceValue * 2;
                    seenOnePair = true;
                } else if (count >= 2 && seenOnePair) {
                    vTvaPar = tempScore + diceValue * 2;
                }

            }
            return true;
        } else {
            System.out.println("Du har redan haft två par.");
            return false;
        }
    }

    private static boolean calcTriss(List<Integer> diceArray) {
        if (vTriss == null) {
            vTriss = 0;
            for (Integer dice : diceArray) {
                int count = countOccurrences(diceArray, dice);
                if (count >= 3) {
                    vTriss = dice * 3;
                }
            }
            return true;
        } else {
            System.out.println("Du har redan haft triss.");
            return false;
        }
    }

    private static boolean calcFyrtal(List<Integer> diceArray) {
        if (vFyrtal == null) {
            vFyrtal = 0;
            for (Integer dice : diceArray) {
                int count = countOccurrences(diceArray, dice);
                if (count >= 4) {
                    vFyrtal = dice * 4;
                }
            }
            return true;
        } else {
            System.out.println("Du har redan haft fyrtal.");
            return false;
        }
    }

    private static boolean calcLitenStege(List<Integer> diceArray) {
        if (vLitenStege == null) {
            vLitenStege = 0;

            Collections.sort(diceArray);
            List<Integer> litenStege = Arrays.asList(1, 2, 3, 4, 5);
            if (diceArray.equals(litenStege)) {
                vLitenStege = 15;
            }
            return true;
        } else {
            System.out.println("Du har redan haft liten stege.");
            return false;
        }
    }

    private static boolean calcStorStege(List<Integer> diceArray) {
        if (vStorStege == null) {
            vStorStege = 0;

            Collections.sort(diceArray);
            List<Integer> storStege = Arrays.asList(2, 3, 4, 5, 6);
            if (diceArray.equals(storStege)) {
                vStorStege = 20;
            }
            return true;
        } else {
            System.out.println("Du har redan haft stor stege.");
            return false;
        }
    }

    private static boolean calcKak(List<Integer> diceArray) {
        if (vKak == null) {
            vKak = 0;
            boolean seenPair = false;
            boolean seenTriss = false;

            for (Integer diceValue : new HashSet<>(diceArray)) {
                int count = countOccurrences(diceArray, diceValue);
                if (count == 2) {
                    if (seenTriss) {
                        vKak = sum(diceArray);
                    } else {
                        seenPair = true;
                    }
                } else if (count == 3) {
                    if (seenPair) {
                        vKak = sum(diceArray);
                    } else {
                        seenTriss = true;
                    }
                }
            }
            return true;
        } else {
            System.out.println("Du har redan haft kåk.");
            return false;
        }
    }

    private static boolean calcChans(List<Integer> diceArray) {
        if (vChans == null) {
            vChans = 0;
            vChans = sum(diceArray);
            return true;
        } else {
            System.out.println("Du har redan haft chans.");
            return false;
        }
    }

    private static boolean calcYatzy(List<Integer> diceArray) {
        if (vYatzy == null) {
            vYatzy = 0;

            int count = countOccurrences(diceArray, diceArray.get(0));
            if (count == 5) {
                vYatzy = sum(diceArray);
            }
            return true;
        } else {
            System.out.println("Du har redan haft Yatzy.");
            return false;
        }
    }
}
