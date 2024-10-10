
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int value = 0;

        while (value != 2) {
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");
            System.out.println("                 Conversor de Moedas                        ");
            System.out.println("                 1 -  Converter Moedas                      ");
            System.out.println("                 2 -  Sair                                  ");
            System.out.println("------------------------------------------------------------");
            System.out.println("------------------------------------------------------------");

            try {

                if (sc.hasNextInt()) {
                    value = sc.nextInt();
                    sc.nextLine();

                    if (value == 1) {
                        System.out.println("Escolha a moeda base para conversão (ex: USD, BRL, EUR, JPY, AED, AMD...): ");
                        String moedaBase = sc.nextLine().toUpperCase();


                        String apiKey = "e9faf6641254bae9a6a1803d";

                        MoedasConversion valores = ApiConversao.buscarTaxas(apiKey, moedaBase);

                        System.out.println("Digite o valor em " + moedaBase + " para converter: ");
                        double valorBase = sc.nextDouble();

                        System.out.println("Escolha uma moeda para converter o valor de " + moedaBase + ":");
                        System.out.println("Digite a moeda que deseja converter (ex: USD, AED, BRL, EUR, GBP, JPY, AMD, CAD...): ");
                        String moedaDestino = sc.next().toUpperCase();


                        if (valores.conversion_rates().containsKey(moedaDestino)) {
                            double taxaConversao = valores.conversion_rates().get(moedaDestino);
                            double valorConvertido = valorBase * taxaConversao;
                            System.out.println("Valor em " + moedaDestino + ": " + valorConvertido);
                        } else {
                            System.out.println("Moeda destino inválida. Tente novamente.");
                        }

                    } else if (value == 2) {
                        System.out.println("Saindo...");
                    } else {
                        System.out.println("Opção inválida. Digite 1 para converter moedas ou 2 para sair.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    sc.next();
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
                sc.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Moeda não encontrada ou erro na resposta da API.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }
}
