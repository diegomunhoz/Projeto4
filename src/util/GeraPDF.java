package util;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
*
* @author Diego Munhoz
*/
@SuppressWarnings("serial")
public class GeraPDF extends JDialog {

	private JRViewer result;

	public GeraPDF(JasperPrint jasperPrint) {
		printarGeratorio(jasperPrint);
	}

	private void printarGeratorio(JasperPrint pRelatorio) {

		result = new JRViewer(pRelatorio);
		result.setZoomRatio(0.90f);

		Integer largura = (Toolkit.getDefaultToolkit().getScreenSize().width) - 10;
		Integer altura = (Toolkit.getDefaultToolkit().getScreenSize().height) - 30;

		setTitle("Relatorio");

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(largura, altura));
		this.setSize(largura, altura);
		setLayout(new BorderLayout());
		add(result, BorderLayout.CENTER);

		getRootPane().getActionMap().put("esc", cancelarAction);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.SHIFT_DOWN_MASK), "esc");

	}

	private javax.swing.Action cancelarAction = new javax.swing.AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};

}
