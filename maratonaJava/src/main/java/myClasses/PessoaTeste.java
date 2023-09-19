package myClasses;

public class PessoaTeste {
    public static void main(String[] args) {
        // Classe pessoa instanciada na classe de teste
        Pessoa pessoa = new Pessoa();
        
        pessoa.nome = "cesar";
        pessoa.idade = 20;
        pessoa.salario = 5.500;
        
        System.out.println(pessoa.nome);
        System.out.println(pessoa.idade);
        System.out.println(pessoa.salario);
        
        System.out.println(pessoa.nome + " tem " + pessoa.idade + " anos e ganha " + pessoa.salario);
        
        // Carro instanciado e adicionado os valores
        // Dois carros foram criados da classe Carro
        Carro carro1 = new Carro();
        carro1.nome = "Jetta";
        carro1.modelo = "Volkswagen";
        carro1.ano = 2021;
        System.out.println(carro1.nome);
        
        Carro carro2 = new Carro();
        carro2.nome = "Golf";
        carro2.modelo = "Volkswagen";
        carro2.ano = 2020;
        System.out.println(carro2.nome);
    }
}
