package exercicios;

public class Arrays {
    public static void main(String[] args) {
        int[] idades = new int[3];
        idades[0] = 20;
        idades[1] = 10;
        idades[2] = 25;
        
        System.out.println(idades[0]);
        
        String[] nomes = new String[3];
        nomes[0] = "jose";
        nomes[1] = "lucas";
        nomes[2] = "julia";
        
        for(int i = 0;i < nomes.length;i++){
            System.out.println(nomes[i]);
        }
        
        for(String nome : nomes) {
            System.out.println(nome);
        }
        
        int[][] valor = new int[2][2];
        valor[0][0] = 1;
        valor[0][1] = 10;
        
        valor[1][0] = 20;
        valor[1][1] = 30;
        
        for(int i = 0;i < valor.length;i++){
            for(int j = 0;j < valor.length;j++){
                System.out.println(valor[i][j]);
            }
        }
        
    }
    
}
