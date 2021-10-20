package ativadade1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BuscaBinaria {

    private ArrayList<String[]> clientes = new ArrayList<String[]>();
    private int contCompara = 0;

    public BuscaBinaria(){
        importarClientes();
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        String nome;

        do {
            System.out.println("\nDIGITE UM NOME: ");
            nome = sc.nextLine();

            if (nome.equals("exit")){
                System.out.println("\n*** ADEUS, MERECE UM 10 ***");
                return;
            }

            pesquisarCliente(nome);
            contCompara = 0;

        }while(true);

    }

    public void pesquisarCliente(String nome) {
        System.out.println(pesquisaBinariaRec(nome, 0, clientes.size()-1));
    }

    private String pesquisaBinariaRec(String nome, int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        contCompara++;

        String procurado = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String atual = Normalizer.normalize(clientes.get(meio)[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        if (inicio > fim) {
            return "O nome não existe" + "\n Comparações: " + contCompara ;
        }

        if(procurado.compareToIgnoreCase(atual) == 0){
            return "Informações: " + Arrays.toString(clientes.get(meio)) + "\n Comparações: " + contCompara;
        }

        if (procurado.compareToIgnoreCase(atual) > 0) {
            return pesquisaBinariaRec(nome, meio+1, fim);
        } else {
            return pesquisaBinariaRec(nome, inicio, meio-1);
        }
    }

    private void importarClientes() {
        String arquivoCSV = System.getProperty("user.dir") + "\\src\\ativadade1\\arquivoDados.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ",";
        try {

            br = new BufferedReader(new FileReader(arquivoCSV));
            while ((linha = br.readLine()) != null) {

                String[] cliente = linha.split(csvDivisor);

                clientes.add(cliente);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
