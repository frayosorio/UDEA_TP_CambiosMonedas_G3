import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import datechooser.beans.DateChooserCombo;

public class FrmCambiosMonedas extends JFrame {

    private JComboBox cmbMoneda;
    private DateChooserCombo dccDesde, dccHasta;
    private JTabbedPane tpCambiosMoneda;
    private JPanel pnlGrafica;
    private JPanel pnlEstadisticas;

    private List<String> monedas;

    public FrmCambiosMonedas() {

        setTitle("Cambios de Monedas");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tb = new JToolBar();

        JButton btnGraficar = new JButton();
        btnGraficar.setIcon(new ImageIcon(getClass().getResource("/iconos/Grafica.png")));
        btnGraficar.setToolTipText("Grafica Cambios vs Fecha");
        btnGraficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGraficarClick();
            }
        });
        tb.add(btnGraficar);

        JButton btnCalcularEstadisticas = new JButton();
        btnCalcularEstadisticas.setIcon(new ImageIcon(getClass().getResource("/iconos/Datos.png")));
        btnCalcularEstadisticas.setToolTipText("Estadísticas de la moneda seleccionada");
        btnCalcularEstadisticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCalcularEstadisticasClick();
            }
        });
        tb.add(btnCalcularEstadisticas);

        // Contenedor con BoxLayout (vertical)
        JPanel pnlCambios = new JPanel();
        pnlCambios.setLayout(new BoxLayout(pnlCambios, BoxLayout.Y_AXIS));

        JPanel pnlDatosProceso = new JPanel();
        pnlDatosProceso.setPreferredSize(new Dimension(pnlDatosProceso.getWidth(), 50)); // Altura fija de 100px
        pnlDatosProceso.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        pnlDatosProceso.setLayout(null);

        JLabel lblMoneda = new JLabel("Moneda");
        lblMoneda.setBounds(10, 10, 100, 25);
        pnlDatosProceso.add(lblMoneda);

        cmbMoneda = new JComboBox();
        cmbMoneda.setBounds(110, 10, 100, 25);
        pnlDatosProceso.add(cmbMoneda);

        dccDesde = new DateChooserCombo();
        dccDesde.setBounds(220, 10, 100, 25);
        pnlDatosProceso.add(dccDesde);

        dccHasta = new DateChooserCombo();
        dccHasta.setBounds(330, 10, 100, 25);
        pnlDatosProceso.add(dccHasta);

        pnlGrafica = new JPanel();
        JScrollPane spGrafica = new JScrollPane(pnlGrafica);

        pnlEstadisticas = new JPanel();

        tpCambiosMoneda = new JTabbedPane();
        tpCambiosMoneda.addTab("Gráfica", spGrafica);
        tpCambiosMoneda.addTab("Estadísticas", pnlEstadisticas);

        // Agregar componentes
        pnlCambios.add(pnlDatosProceso);
        pnlCambios.add(tpCambiosMoneda);

        getContentPane().add(tb, BorderLayout.NORTH);
        getContentPane().add(pnlCambios, BorderLayout.CENTER);

    }

    private void btnGraficarClick() {
        if (cmbMoneda.getSelectedIndex() >= 0) {

            String moneda = (String) cmbMoneda.getSelectedItem();
            LocalDate desde = dccDesde.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate hasta = dccHasta.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


            // Cambiar a la pestaña de Grafica
            tpCambiosMoneda.setSelectedIndex(0);
        }
    }

    private void btnCalcularEstadisticasClick() {
        if (cmbMoneda.getSelectedIndex() >= 0) {

            String moneda = (String) cmbMoneda.getSelectedItem();
            LocalDate desde = dccDesde.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate hasta = dccHasta.getSelectedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


            // Cambiar a la pestaña de estadísticas
            tpCambiosMoneda.setSelectedIndex(1); 

        }
    }

}
