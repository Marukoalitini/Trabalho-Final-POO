public class Garcom extends Pessoa{
    public double salario;
    public String turno;


    public Garcom(String nome, Mesa mesa, double salario, String turno) {
        super(nome, mesa);
        this.salario = salario;
        this.turno = turno;
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