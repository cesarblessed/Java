package myClasses;

public class PessoaTeste {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        
        pessoa.nome = "cesar";
        pessoa.idade = 20;
        pessoa.salario = 5.500;
        
        System.out.println(pessoa.nome);
        System.out.println(pessoa.idade);
        System.out.println(pessoa.salario);
        
        System.out.println(pessoa.nome + " tem " + pessoa.idade + " anos e ganha " + pessoa.salario);
        
        Carro carro = new Carro();
        carro.nome = "Jetta";
        carro.modelo = "Volkswagen";
        carro.ano = 2021;
        
        System.out.println(carro.nome);
    }
}
