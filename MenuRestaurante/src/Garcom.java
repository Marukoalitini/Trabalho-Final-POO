public class Garcom extends Pessoa{
    private double salario;
    private String turno;
    private double totalGorjetas;


    public Garcom(String nome, Mesa mesa, double salario, String turno) {
        super(nome, mesa);
        this.salario = salario;
        this.turno = turno;
    }


    public void receberGorjeta (double gorjeta) {
        if (gorjeta > 0) {
            totalGorjetas += gorjeta;
        } else {
            System.out.println("Valor inválido. Por favor, tente novamente.");
        }
    }


    public double calculaSalario () {
        return salario + totalGorjetas;
    }


    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }


    public void status() {
        System.out.println("Garçom " + nome + " responsável pela mesa: " + mesa);
        System.out.println("Salário: " + salario);
        System.out.println("Turno(s): " + turno);
    }
}