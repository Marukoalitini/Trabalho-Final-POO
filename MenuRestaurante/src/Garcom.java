import java.util.ArrayList;

enum Turno {
    Manhã, Tarde, Noite
}

public class Garcom extends Pessoa {
    private double salario;
    private Turno turno;
    private ArrayList<Mesa> mesas = new ArrayList<>();
    private double gorjetaTotal;

    public Garcom(String nome, double salario, Turno turno) {
        super(nome);
        this.salario = salario;
        this.turno = turno;
        this.gorjetaTotal = 0;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public double getGorjetaTotal() {
        return gorjetaTotal;
    }

    public void adicionarGorjeta(double valor) {
        gorjetaTotal += valor;
    }

    public double calcularGanhoTotal() {
        return salario + gorjetaTotal;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void status() {
        System.out.println("Garçom " + getNome() + " responsável pelas mesas: " + getMesas());
        System.out.println("Salário: R$ " + salario);
        System.out.println("Gorjeta Total: R$ " + gorjetaTotal);
        System.out.println("Total Ganho: R$ " + calcularGanhoTotal());
        System.out.println("Turno: " + turno);
    }
}
