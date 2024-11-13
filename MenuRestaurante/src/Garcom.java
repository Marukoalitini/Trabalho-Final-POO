import java.util.ArrayList;
enum Turno{
    Manhã, Tarde, Noite
}

public class Garcom extends Pessoa {
    private double salario;
    private Turno turno; // Usando o Enum para o turno
    private ArrayList<Mesa> mesas = new ArrayList<>();
    private double gorjetaTotal; // Armazenar a gorjeta total do garçom

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

    // Método para adicionar a gorjeta
    public void adicionarGorjeta(double valor) {
        gorjetaTotal += valor;
    }

    // Método para calcular o total que o garçom ganha (salário + gorjeta)
    public double calcularGanhoTotal() {
        return salario + gorjetaTotal;
    }

    public void status() {
        System.out.println("Garçom " + getNome() + " responsável pelas mesas: " + getMesas());
        System.out.println("Salário: R$ " + salario);
        System.out.println("Gorjeta Total: R$ " + gorjetaTotal);
        System.out.println("Total Ganho: R$ " + calcularGanhoTotal());
        System.out.println("Turno(s): " + turno);
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }
}