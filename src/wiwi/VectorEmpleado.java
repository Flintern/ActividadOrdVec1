
package wiwi;

import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class VectorEmpleado {
    
    Empleado[] misEmp;
    int tam;
    
    VectorEmpleado(JTextField tamVector) {
        int i;
        try {

            tam = Integer.parseInt(tamVector.getText());
            //Declarar el vector
            misEmp = new Empleado[tam];
            //Instanciar el vector por cada posición de forma
            //vacía
            for (i = 0; i < misEmp.length; i++) {
                misEmp[i] = new Empleado("", "", 0, 0,0,0,LocalDate.of(1,1,1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e + ".  El vector no fue creado! Intente nuevamente.");
            tamVector.setText("");
            tamVector.requestFocus();
            misEmp = null;
        }

    }

    public Empleado[] getMisEmp() {
        return misEmp;
    }

    public void setMisEmp(Empleado[] misEmp) {
        this.misEmp = misEmp;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
  
    
     public int getBuscarID(String id) {
        int i;
        for (i = 0; i < misEmp.length; i++) {
            if (id.equals(misEmp[i].getId())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean llenarVector(JTextField id, JTextField nombre, JTextField genero, JTextField salariob,JTextField horasextra,LocalDate fechavinc, JComboBox estrato, int pos) {
        int b;
        try {
            if ((pos < 0) || (pos >= tam)) {
                JOptionPane.showMessageDialog(null, "Posición invalida!");
                return false;
            } else {
                do {
                    b = getBuscarID(id.getText());
                    if (b != -1) {
                        JOptionPane.showMessageDialog(null, "el ID ya existe.  Intente " + "nuevamente!");
                        id.setText("");
                        id.requestFocus();
                        return false;
                    }
                } while (b != -1);
                int est;
                switch (estrato.getSelectedIndex()) {
                    case 0:
                        est = 1;
                        break;
                    case 1:
                        est = 2;
                        break;
                    case 2:
                        est = 3;
                        break;
                    case 3:
                        est = 4;
                        break;
                    case 4:
                        est = 5;
                        break;
                    default:
                        est = 6;
                        break;
                }
                
                misEmp[pos].setId(id.getText());
                misEmp[pos].setNombre(nombre.getText());
                misEmp[pos].setGenero(Integer.parseInt(genero.getText()));
                misEmp[pos].setSalarioB(Double.parseDouble(salariob.getText()));
                misEmp[pos].setHorasEx(Integer.parseInt(horasextra.getText()));
                misEmp[pos].setFechaVinc(fechavinc);
                misEmp[pos].setEstrato(est);
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e + ". No fue registrado.");
            return false;
        }

    }

    public void setRegistrarFilaJTable(DefaultTableModel miModelo,
            int pFila, int pVec) {

        miModelo.setValueAt(misEmp[pVec].getId(), pFila, 0);
        miModelo.setValueAt(misEmp[pVec].getNombre(), pFila, 1);
        miModelo.setValueAt(misEmp[pVec].getGenero(), pFila, 2);
        miModelo.setValueAt(misEmp[pVec].getSalarioB(), pFila, 3);
        miModelo.setValueAt(misEmp[pVec].getHorasEx(), pFila, 4);
        miModelo.setValueAt(misEmp[pVec].getFechaVinc(), pFila, 5);
        miModelo.setValueAt(misEmp[pVec].getEstrato(), pFila, 6);
        

    }

    public void llenarJTable(JTable tab) {
        int posTabla = 0; //Este índice recorre los elementos de la fila Tabla
        int posVec = 0;  //Este índice para ubicar posición del vector
        DefaultTableModel miModelo = new DefaultTableModel();

        //Creamos los nombres de las columnas de la tabla
        miModelo.addColumn("Id");
        miModelo.addColumn("Nombre");
        miModelo.addColumn("Genero");
        miModelo.addColumn("Salario Basico");
        miModelo.addColumn("Horas Extra");
        miModelo.addColumn("Fecha Vinculacion");
        miModelo.addColumn("Estrato");

        //Recorremos el vector para tomar sus datos
        //y pasarlos al JTable
        while (posVec < misEmp.length) {
            miModelo.addRow(new Object[]{"", "", "", "", "","",""});
            setRegistrarFilaJTable(miModelo, posTabla, posVec);
            posVec++;
            posTabla++;
        }
        tab.setModel(miModelo);
    }
    
    public void setIntercambio(int posA, int posS) {
       
      
        Empleado temp;
        //Copiamos la información de la 
        //posicion anterior
        temp = new Empleado(
                misEmp[posA].getId(),
                misEmp[posA].getNombre(),
                misEmp[posA].getGenero(),
                misEmp[posA].getSalarioB(),
                misEmp[posA].getEstrato(),
                misEmp[posA].getHorasEx(),
                misEmp[posA].getFechaVinc()
                
        );
        //Cambiamos la informacion del anterior
        //por la información del siguiente
        misEmp[posA].setId(misEmp[posS].getId()) ;
        misEmp[posA].setNombre(misEmp[posS].getNombre()) ;
        misEmp[posA].setGenero(misEmp[posS].getGenero()) ;
        misEmp[posA].setSalarioB(misEmp[posS].getSalarioB())  ;
        misEmp[posA].setEstrato(misEmp[posS].getEstrato()) ;
        misEmp[posA].setHorasEx(misEmp[posS].getHorasEx())  ;
        misEmp[posA].setFechaVinc(misEmp[posS].getFechaVinc())  ;
        //Cambiamos la información del siguiente
        //por la informacion que tenía (temp) el anterior
        
                misEmp[posS].setId(temp.getId());
                misEmp[posS].setNombre(temp.getNombre());
                misEmp[posS].setGenero(temp.getGenero());
                misEmp[posS].setSalarioB(temp.getSalarioB());
                misEmp[posS].setEstrato(temp.getEstrato());
                misEmp[posS].setHorasEx(temp.getHorasEx());
                misEmp[posS].setFechaVinc(temp.getFechaVinc());
     
    }
    
     public void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            double pivote = misEmp[fin].calcNeto();
            int i = inicio - 1;
            for (int j = inicio; j < fin; j++) {
                if (misEmp[j].calcNeto() < pivote) {
                    i++;
                    swap(i, j);
                }
            }
            i++;
            swap(i, fin);
            quickSort(inicio, i - 1);
            quickSort(i + 1, fin);
        }
    }
    
    //Método de intercambio para QuickSort
    public void swap(int i, int j) {
        Empleado temp = misEmp[i];
        misEmp[i] = misEmp[j];
        misEmp[j] = temp;
    }
    //metodo ordenamiento seleccion
    public void ordenarNombSeleccion() {
        for (int i = 0; i < misEmp.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < misEmp.length; j++) {
                if (misEmp[j].getNombre().compareTo(misEmp[indiceMenor].getNombre()) < 0) {
                    indiceMenor = j;
                }
            }
            setIntercambio(indiceMenor, i);

        }
    }
    
     public void ordenamientoShellDescendente() {
        int n = misEmp.length;

        for (int salto = n / 2; salto > 0; salto /= 2) {
            for (int i = salto; i < n; i++) {
                Empleado temp = misEmp[i];
                int j;
                for (j = i; j >= salto && misEmp[j - salto].getEstrato()==temp.getEstrato(); j -= salto) {
                    misEmp[j] = misEmp[j - salto];
                }
                misEmp[j] = temp;
            }
        }
    }
    

}
