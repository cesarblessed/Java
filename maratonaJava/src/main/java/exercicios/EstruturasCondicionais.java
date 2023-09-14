package exercicios;

public class EstruturasCondicionais {
    public static void main(String[] args) {
       int idade = 18;
       
       
       if(idade >= 18){
           System.out.println("Maior de idade");
       }
       
       if(idade <=12){
           System.out.println("Criança");
       }else if(idade >12 && idade <=18){
           System.out.println("Adolescente");
       }else{
           System.out.println("Adulto");
       }
       
       switch(idade){
           case 18:
               System.out.println("Maior de idade");
       }
    }
}