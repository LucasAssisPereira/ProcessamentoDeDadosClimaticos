import experiment.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Selecione o número de threads para o experimento:");
            System.out.println("1. 1 Thread");
            System.out.println("2. 3 Threads");
            System.out.println("3. 9 Threads");
            System.out.println("4. 27 Threads");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    SingleThreadExperiment singleThreadExperiment = new SingleThreadExperiment();
                    singleThreadExperiment.runExperiment();
                    break;

                case 2:
                    ThreeThreadExperiment threeThreadExperiment = new ThreeThreadExperiment();
                    threeThreadExperiment.runExperiment();
                    break;

                case 3:
                    NineThreadExperiment nineThreadExperiment = new NineThreadExperiment();
                    nineThreadExperiment.runExperiment();
                    break;

                case 4:
                    TwentySevenThreadExperiment twentySevenThreadExperiment = new TwentySevenThreadExperiment();
                    twentySevenThreadExperiment.runExperiment();
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}

