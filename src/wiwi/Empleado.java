package wiwi;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class Empleado {

    private String id;
    private String nombre;
    private int genero;
    private double salarioB;
    private int estrato;
    private float horasEx;
    private LocalDate fechaVinc;

    public Empleado(String id, String nombre, int genero, double salarioB, int estrato, float horasEx, LocalDate fechaVinc) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.salarioB = salarioB;
        this.estrato = estrato;
        this.horasEx = horasEx;
        this.fechaVinc = fechaVinc;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalarioB() {
        return salarioB;
    }

    public void setSalarioB(double salarioB) {
        this.salarioB = salarioB;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public float getHorasEx() {
        return horasEx;
    }

    public void setHorasEx(float horasEx) {
        this.horasEx = horasEx;
    }

    public LocalDate getFechaVinc() {
        return fechaVinc;
    }

    public void setFechaVinc(LocalDate fechaVinc) {
        this.fechaVinc = fechaVinc;
    }

    public void getMostrarEmpleado() {
        String info = "Los datos de el empleado son: \n";
        info += "ID: " + id + "\n";
        info += "Nombre: " + nombre + "\n";

        if (genero == 0) {
            info += "Genero: Masculino\n";
        } else {
            info += "Genero: Femenino\n";
        }

        info += "Salario Basico: " + salarioB + "\n";
        info += "Estrato: " + estrato + "\n";
        info += "Horas Extra: " + horasEx + "\n";
        info += "Fecha de vinculacion: " + fechaVinc + "\n";

        JOptionPane.showMessageDialog(null, info);

    }

    public int calcAños() {
        LocalDate hoy = LocalDate.now();
        int años = hoy.getYear() - fechaVinc.getYear();
        return años;
    }
    public double aportSal(){
        double aportSal = 0.4*salarioB;
        return aportSal;
    }
    
    public double aportPen(){
        double aporpen = 0.375*salarioB;
        return aporpen;
    }
    public double arl(){
         double arl = 0.2*salarioB;
         return arl;
    
    }
    public double valorHE(){
        double valHE;
        if (calcAños()>10) {
            valHE = 45000;
        } else if((10>=calcAños())&&(calcAños()>5)){
            valHE= 35000;
        }else if (5>=calcAños()&&calcAños()>3){
            valHE= 30000;
        } else {valHE = 20000;}
        return valHE;
    
    }
    public double totalHE(){
        double totalHE = valorHE() * horasEx;
        
        return totalHE;
    }
    
    public double subsTrans(){
    double subsTra;
        if (estrato==1||estrato==2){
        subsTra= 78000;
        } else {subsTra=0;}
        return subsTra;
        
    }

    public double calcNeto() {
        
        double neto = (salarioB+totalHE()+subsTrans())-(aportSal()+aportPen()+arl());
        return neto;
    }
    
    public void mostrarNomina(){
    String msg="";
    msg+= "Nomina"+"\n";
    msg+= "Sueldo Basico     "+salarioB+"\n";
    msg+= "HE Laboradas     "+horasEx+"\n";
    msg+= "Valor HE     "+valorHE()+"\n";
    msg+= "Total HE     +"+totalHE()+"\n";
    msg+= "Aporte Salud     -"+aportSal()+"\n";
    msg+= "Aporte Pension     -"+aportPen()+"\n";
    msg+= "Aporte ARL     -"+arl()+"\n";
    msg+= "Subsidio TTE     +"+subsTrans();
    msg+= "\n"+"\n"+"Neto a Pagar     "+calcNeto();
    
    JOptionPane.showMessageDialog(null,msg);
    
    
    
    }
}
